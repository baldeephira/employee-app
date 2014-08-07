/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Baldeep Hira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.bhira.sample.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import net.bhira.sample.api.jdbc.CompanyRowMapper;
import net.bhira.sample.common.exception.InvalidObjectException;
import net.bhira.sample.common.exception.InvalidReferenceException;
import net.bhira.sample.common.exception.ObjectNotFoundException;
import net.bhira.sample.model.Company;

/**
 * Implementation for Company DAO. It implements CRUD operations for
 * {@link net.bhira.sample.model.Company}. The client code should always invoke methods in Service
 * class {@link net.bhira.sample.api.service.CompanyService} as service wraps the DAO persistence
 * calls with transaction semantics.
 * 
 * @author Baldeep Hira
 */
@Repository("companyDao")
public class CompanyDaoImpl implements CompanyDao {

	/**
	 * private singleton instance of the SLF4J logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CompanyDaoImpl.class);

	private static final String SQL_LOAD_BY_ID = "select * from company where id = ?";
	private static final String SQL_LOAD_ALL = "select * from company";
	private static final String SQL_INSERT = "insert into company"
			+ " (name, industry, billingaddr, shippingaddr, created, modified, createdby, modifiedby)"
			+ " values (?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "update company set name = ?, industry = ?, billingaddr = ?,"
			+ "shippingaddr = ?, modified = ?, modifiedby = ? where id = ?";
	private static final String SQL_DELETE = "delete from company where id = ?";
	private static final String SQL_CINFO_REL_LOAD = "select contactinfoid from company_cinfo where companyid = ?";
	private static final String SQL_CINFO_REL_INSERT = "insert into company_cinfo (companyid, contactinfoid) values (?,?)";
	private static final String SQL_CINFO_REL_DELETE = "delete from company_cinfo where companyid = ?";

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	ContactInfoDao contactInfoDao;

	/**
	 * @see net.bhira.sample.api.dao.CompanyDao#load(long)
	 */
	@Override
	public Company load(long companyId) {
		List<Company> list = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { companyId },
				new CompanyRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded company, count = {}, id = {}", count, companyId);

		if (count > 0) {
			Company company = list.get(0);
			List<Long> cinfoIds = jdbcTemplate.queryForList(SQL_CINFO_REL_LOAD, Long.class,
					new Object[] { companyId });
			if (cinfoIds != null && !cinfoIds.isEmpty()) {
				company.setContactInfo(contactInfoDao.load(cinfoIds.get(0)));
			}
			return company;
		} else {
			return null;
		}
	}

	/**
	 * @see net.bhira.sample.api.dao.CompanyDao#save(net.bhira.sample.model.Company)
	 */
	@Override
	public void save(Company company) throws ObjectNotFoundException, InvalidObjectException,
			InvalidReferenceException {
		if (company == null) {
			throw new InvalidObjectException("Company object is null.");
		}

		company.initForSave();
		company.validate();
		boolean isNew = company.isNew();
		int count = 0;

		if (isNew) {
			// for new company, construct SQL insert statement
			KeyHolder keyHolder = new GeneratedKeyHolder();
			count = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection)
						throws SQLException {
					PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT,
							Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, company.getName());
					pstmt.setString(2, company.getIndustry());
					pstmt.setString(3, company.getBillingAddress());
					pstmt.setString(4, company.getShippingAddress());
					pstmt.setTimestamp(5, new Timestamp(company.getCreated().getTime()));
					pstmt.setTimestamp(6, new Timestamp(company.getModified().getTime()));
					pstmt.setString(7, company.getCreatedBy());
					pstmt.setString(8, company.getModifiedBy());
					return pstmt;
				}
			}, keyHolder);

			// fetch the newly created auto-increment ID
			company.setId(keyHolder.getKey().longValue());
			LOG.debug("inserted company, count = {}, id = {}", count, company.getId());

		} else {
			// for existing company, construct SQL update statement
			Object[] args = new Object[] { company.getName(), company.getIndustry(),
					company.getBillingAddress(), company.getShippingAddress(),
					company.getModified(), company.getModifiedBy(), company.getId() };
			count = jdbcTemplate.update(SQL_UPDATE, args);
			LOG.debug("updated company, count = {}, id = {}", count, company.getId());
		}

		// if insert/update has 0 count value, then throw exception
		if (count <= 0) {
			throw new ObjectNotFoundException("Company with ID " + company.getId()
					+ " was not found.");
		}

		// update dependent entries, as needed
		if (isNew) {

			// for new model if there is contact info, save it to contact info table and then
			// add entry in relationship table
			if (company.getContactInfo() != null) {
				contactInfoDao.save(company.getContactInfo());
				Object[] args = new Object[] { company.getId(), company.getContactInfo().getId() };
				jdbcTemplate.update(SQL_CINFO_REL_INSERT, args);
			}

		} else {
			// for existing model, fetch contact info ID from relationship table
			List<Long> cinfoIds = jdbcTemplate.queryForList(SQL_CINFO_REL_LOAD, Long.class,
					new Object[] { company.getId() });
			Long cinfoId = (cinfoIds != null && !cinfoIds.isEmpty()) ? cinfoIds.get(0) : null;

			if (company.getContactInfo() == null) {
				// clean up old contact info entry, if needed
				if (cinfoId != null) {
					jdbcTemplate.update(SQL_CINFO_REL_DELETE, new Object[] { company.getId() });
					contactInfoDao.delete(cinfoId);
				}

			} else {
				// insert/update contact info entry
				if (cinfoId != null) {
					company.getContactInfo().setId(cinfoId);
					contactInfoDao.save(company.getContactInfo());
				} else {
					contactInfoDao.save(company.getContactInfo());
					Object[] args = new Object[] { company.getId(),
							company.getContactInfo().getId() };
					jdbcTemplate.update(SQL_CINFO_REL_INSERT, args);
				}
			}
		}
	}

	/**
	 * @see net.bhira.sample.api.dao.CompanyDao#delete(long)
	 */
	@Override
	public boolean delete(long companyId) {
		int count = 0;

		// load ID from contact info relationship table
		List<Long> cinfoIds = jdbcTemplate.queryForList(SQL_CINFO_REL_LOAD, Long.class,
				new Object[] { companyId });
		Long cinfoId = (cinfoIds != null && !cinfoIds.isEmpty()) ? cinfoIds.get(0) : null;

		// delete relationship entry & contact info entry, if needed
		if (cinfoId != null) {
			jdbcTemplate.update(SQL_CINFO_REL_DELETE, new Object[] { companyId });
			contactInfoDao.delete(cinfoId);
		}

		// delete the row from company table
		count = jdbcTemplate.update(SQL_DELETE, new Object[] { companyId });
		LOG.debug("deleted company, count = {}, id = {}", count, companyId);

		return (count > 0);
	}

	/**
	 * @see net.bhira.sample.api.dao.CompanyDao#loadAll()
	 */
	@Override
	public List<Company> loadAll() {
		List<Company> list = jdbcTemplate.query(SQL_LOAD_ALL, new CompanyRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded all companies, count = {}", count);
		return list;
	}

}