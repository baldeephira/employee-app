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
import org.springframework.dao.DataIntegrityViolationException;
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
			+ " (name, industry, billingaddr, shippingaddr, contactinfo, created, modified, createdby, modifiedby)"
			+ " values (?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "update company set name = ?, industry = ?, billingaddr = ?,"
			+ "shippingaddr = ?, contactinfo = ?, modified = ?, modifiedby = ? where id = ?";
	private static final String SQL_DELETE = "delete from company where id = ?";

	@Autowired
	DataSource dataSource;

	@Autowired
	AddressDao addressDao;

	@Autowired
	ContactInfoDao contactInfoDao;

	/**
	 * @see net.bhira.sample.api.dao.CompanyDao#load(long)
	 */
	@Override
	public Company load(long companyId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Company> list = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { companyId },
				new CompanyRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded company, count = {}, id = {}", count, companyId);

		if (count > 0) {
			Company company = list.get(0);
			if (company.getBillingAddress() != null) {
				company.setBillingAddress(addressDao.load(company.getBillingAddress().getId()));
			}
			if (company.getShippingAddress() != null) {
				company.setShippingAddress(addressDao.load(company.getShippingAddress().getId()));
			}
			if (company.getContactInfo() != null) {
				company.setContactInfo(contactInfoDao.load(company.getContactInfo().getId()));
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
		try {
			if (company == null) {
				throw new InvalidObjectException("Company object is null.");
			}
	
			company.initForSave();
			company.validate();
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	
			// load old model, for cleaning dependent tables
			Company oldModel = null;
			if (!company.isNew()) {
				List<Company> list = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { company.getId() },
						new CompanyRowMapper());
				if (list != null && !list.isEmpty()) {
					oldModel = list.get(0);
				}
			}
	
			// save contained objects in dependent tables
			if (company.getBillingAddress() != null) {
				if (oldModel != null && oldModel.getBillingAddress() != null) {
					company.getBillingAddress().setId(oldModel.getBillingAddress().getId());
				}
				addressDao.save(company.getBillingAddress());
			}
			if (company.getShippingAddress() != null) {
				if (oldModel != null && oldModel.getShippingAddress() != null) {
					company.getShippingAddress().setId(oldModel.getShippingAddress().getId());
				}
				addressDao.save(company.getShippingAddress());
			}
			if (company.getContactInfo() != null) {
				if (oldModel != null && oldModel.getContactInfo() != null) {
					company.getContactInfo().setId(oldModel.getContactInfo().getId());
				}
				contactInfoDao.save(company.getContactInfo());
			}
	
			int count = 0;
			if (company.isNew()) {
				// for new company, construct SQL insert statement
				KeyHolder keyHolder = new GeneratedKeyHolder();
				count = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT,
								Statement.RETURN_GENERATED_KEYS);
						pstmt.setString(1, company.getName());
						pstmt.setString(2, company.getIndustry());
						if (company.getBillingAddress() == null) {
							pstmt.setNull(3, java.sql.Types.BIGINT);
						} else {
							pstmt.setLong(3, company.getBillingAddress().getId());
						}
						if (company.getShippingAddress() == null) {
							pstmt.setNull(4, java.sql.Types.BIGINT);
						} else {
							pstmt.setLong(4, company.getShippingAddress().getId());
						}
						if (company.getContactInfo() == null) {
							pstmt.setNull(5, java.sql.Types.BIGINT);
						} else {
							pstmt.setLong(5, company.getContactInfo().getId());
						}
						pstmt.setTimestamp(6, new Timestamp(company.getCreated().getTime()));
						pstmt.setTimestamp(7, new Timestamp(company.getModified().getTime()));
						pstmt.setString(8, company.getCreatedBy());
						pstmt.setString(9, company.getModifiedBy());
						return pstmt;
					}
				}, keyHolder);
	
				// fetch the newly created auto-increment ID
				company.setId(keyHolder.getKey().longValue());
				LOG.debug("inserted company, count = {}, id = {}", count, company.getId());
	
			} else {
				// for existing company, construct SQL update statement
				Long bAddrId = company.getBillingAddress() == null ? null : company.getBillingAddress().getId();
				Long sAddrId = company.getShippingAddress() == null ? null : company.getShippingAddress().getId();
				Long cInfoId = company.getContactInfo() == null ? null : company.getContactInfo().getId();
				Object[] args = new Object[] { company.getName(), company.getIndustry(), bAddrId, sAddrId,
						cInfoId, company.getModified(), company.getModifiedBy(), company.getId() };
				count = jdbcTemplate.update(SQL_UPDATE, args);
				LOG.debug("updated company, count = {}, id = {}", count, company.getId());
			}
	
			// if insert/update has 0 count value, then rollback
			if (count <= 0) {
				throw new ObjectNotFoundException("Company with ID "+company.getId()+" was not found.");
			}
	
			// check if any dependent table entries need to be deleted
			if (oldModel != null) {
	
				// delete the old billing address entry (no longer in new model)
				if (company.getBillingAddress() == null && oldModel.getBillingAddress() != null) {
					addressDao.delete(oldModel.getBillingAddress().getId());
				}
	
				// check if shippingAddress key needs to be updated
				if (company.getShippingAddress() == null && oldModel.getShippingAddress() != null) {
					addressDao.delete(oldModel.getShippingAddress().getId());
				}
	
				// delete the old contact info entry (no longer in new model)
				if (company.getContactInfo() == null && oldModel.getContactInfo() != null) {
					contactInfoDao.delete(oldModel.getContactInfo().getId());
				}
			}
		} catch (DataIntegrityViolationException dive) {
			String msg = dive.getMessage();
			if (msg != null) {
				if (msg.contains("fk_company_baddr")) {
					throw new InvalidReferenceException("Invalid reference for attribute 'billingAddress'", dive);
				} else if (msg.contains("fk_company_saddr")) {
					throw new InvalidReferenceException("Invalid reference for attribute 'shippingAddress'", dive);
				} else if (msg.contains("fk_company_cinfo")) {
					throw new InvalidReferenceException("Invalid reference for attribute 'contactInfo'", dive);
				}
			}
			throw dive;
		}
	}

	/**
	 * @see net.bhira.sample.api.dao.CompanyDao#delete(long)
	 */
	@Override
	public boolean delete(long companyId) {
		int count = 0;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		// load the existing company from database
		List<Company> companies = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { companyId },
				new CompanyRowMapper());

		if (companies != null && !companies.isEmpty()) {
			Company company = companies.get(0);

			// delete the row from company table
			count = jdbcTemplate.update(SQL_DELETE, new Object[] { companyId });
			LOG.debug("deleted company, count = {}, id = {}", count, companyId);

			// delete all rows from dependent tables
			if (company.getBillingAddress() != null) {
				addressDao.delete(company.getBillingAddress().getId());
			}
			if (company.getShippingAddress() != null) {
				addressDao.delete(company.getShippingAddress().getId());
			}
			if (company.getContactInfo() != null) {
				contactInfoDao.delete(company.getContactInfo().getId());
			}
		}
		return (count > 0);
	}

	/**
	 * @see net.bhira.sample.api.dao.CompanyDao#loadAll()
	 */
	@Override
	public List<Company> loadAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Company> list = jdbcTemplate.query(SQL_LOAD_ALL, new CompanyRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded all companies, count = {}", count);
		return list;
	}

}