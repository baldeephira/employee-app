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

import net.bhira.sample.api.jdbc.DepartmentRowMapper;
import net.bhira.sample.common.exception.DuplicateNameException;
import net.bhira.sample.common.exception.InvalidObjectException;
import net.bhira.sample.common.exception.InvalidReferenceException;
import net.bhira.sample.common.exception.ObjectNotFoundException;
import net.bhira.sample.model.Department;

/**
 * Implementation for Department DAO. It implements CRUD operations for
 * {@link net.bhira.sample.model.Department}. The client code should always invoke methods in
 * Service class {@link net.bhira.sample.api.service.DepartmentService} as service wraps the DAO
 * persistence calls with transaction semantics.
 * 
 * @author Baldeep Hira
 */
@Repository("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao {

	/**
	 * private singleton instance of the SLF4J logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentDaoImpl.class);

	private static final String SQL_LOAD_BY_ID = "select * from department where id = ?";
	private static final String SQL_LOAD_BY_COMPANY = "select * from department where companyid = ?";
	private static final String SQL_INSERT = "insert into department"
			+ " (companyid, name, billingaddr, shippingaddr, created, modified, createdby, modifiedby)"
			+ " values (?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "update department set companyid = ?, name = ?, billingaddr = ?,"
			+ "shippingaddr = ?, modified = ?, modifiedby = ? where id = ?";
	private static final String SQL_DELETE = "delete from department where id = ?";
	private static final String SQL_CINFO_REL_LOAD = "select contactinfoid from department_cinfo where departmentid = ?";
	private static final String SQL_CINFO_REL_INSERT = "insert into department_cinfo (departmentid, contactinfoid) values (?,?)";
	private static final String SQL_CINFO_REL_DELETE = "delete from department_cinfo where departmentid = ?";

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	ContactInfoDao contactInfoDao;

	/**
	 * @see net.bhira.sample.api.dao.DepartmentDao#load(long)
	 */
	@Override
	public Department load(long departmentId) {
		List<Department> list = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { departmentId },
				new DepartmentRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded department, count = {}, id = {}", count, departmentId);

		if (count > 0) {
			Department department = list.get(0);
			List<Long> cinfoIds = jdbcTemplate.queryForList(SQL_CINFO_REL_LOAD, Long.class,
					new Object[] { departmentId });
			if (cinfoIds != null && !cinfoIds.isEmpty()) {
				department.setContactInfo(contactInfoDao.load(cinfoIds.get(0)));
			}
			return department;
		} else {
			return null;
		}
	}

	/**
	 * @see net.bhira.sample.api.dao.DepartmentDao#save(net.bhira.sample.model.Department)
	 */
	@Override
	public void save(Department department) throws ObjectNotFoundException, DuplicateNameException,
			InvalidObjectException, InvalidReferenceException {
		try {
			if (department == null) {
				throw new InvalidObjectException("Department object is null.");
			}

			department.initForSave();
			department.validate();
			boolean isNew = department.isNew();
			int count = 0;

			if (isNew) {
				// for new department, construct SQL insert statement
				KeyHolder keyHolder = new GeneratedKeyHolder();
				count = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection)
							throws SQLException {
						PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT,
								Statement.RETURN_GENERATED_KEYS);
						pstmt.setLong(1, department.getCompanyId());
						pstmt.setString(2, department.getName());
						pstmt.setString(3, department.getBillingAddress());
						pstmt.setString(4, department.getShippingAddress());
						pstmt.setTimestamp(5, new Timestamp(department.getCreated().getTime()));
						pstmt.setTimestamp(6, new Timestamp(department.getModified().getTime()));
						pstmt.setString(7, department.getCreatedBy());
						pstmt.setString(8, department.getModifiedBy());
						return pstmt;
					}
				}, keyHolder);

				// fetch the newly created auto-increment ID
				department.setId(keyHolder.getKey().longValue());
				LOG.debug("inserted department, count = {}, id = {}", count, department.getId());

			} else {
				// for existing department, construct SQL update statement
				Object[] args = new Object[] { department.getCompanyId(), department.getName(),
						department.getBillingAddress(), department.getShippingAddress(),
						department.getModified(), department.getModifiedBy(), department.getId() };
				count = jdbcTemplate.update(SQL_UPDATE, args);
				LOG.debug("updated department, count = {}, id = {}", count, department.getId());
			}

			// if insert/update has 0 count value, then rollback
			if (count <= 0) {
				throw new ObjectNotFoundException("Department with ID " + department.getId()
						+ " was not found.");
			}

			// update dependent entries, as needed
			if (isNew) {

				// for new model if there is contact info, save it to contact info table and then
				// add entry in relationship table
				if (department.getContactInfo() != null) {
					contactInfoDao.save(department.getContactInfo());
					Object[] args = new Object[] { department.getId(),
							department.getContactInfo().getId() };
					jdbcTemplate.update(SQL_CINFO_REL_INSERT, args);
				}

			} else {
				// for existing model, fetch contact info ID from relationship table
				List<Long> cinfoIds = jdbcTemplate.queryForList(SQL_CINFO_REL_LOAD, Long.class,
						new Object[] { department.getId() });
				Long cinfoId = (cinfoIds != null && !cinfoIds.isEmpty()) ? cinfoIds.get(0) : null;

				if (department.getContactInfo() == null) {
					// clean up old contact info entry, if needed
					if (cinfoId != null) {
						jdbcTemplate.update(SQL_CINFO_REL_DELETE,
								new Object[] { department.getId() });
						contactInfoDao.delete(cinfoId);
					}

				} else {
					// insert/update contact info entry
					if (cinfoId != null) {
						department.getContactInfo().setId(cinfoId);
						contactInfoDao.save(department.getContactInfo());
					} else {
						contactInfoDao.save(department.getContactInfo());
						Object[] args = new Object[] { department.getId(),
								department.getContactInfo().getId() };
						jdbcTemplate.update(SQL_CINFO_REL_INSERT, args);
					}
				}
			}

		} catch (DataIntegrityViolationException dive) {
			String msg = dive.getMessage();
			if (msg != null) {
				if (msg.contains("uq_department")) {
					throw new DuplicateNameException("Duplicate department name "
							+ department.getName(), dive);
				} else if (msg.contains("fk_department_compy")) {
					throw new InvalidReferenceException(
							"Invalid reference for attribute 'companyId'", dive);
				}
			}
			throw dive;
		}
	}

	/**
	 * @see net.bhira.sample.api.dao.DepartmentDao#delete(long)
	 */
	@Override
	public boolean delete(long departmentId) {

		// load ID from contact info relationship table
		List<Long> cinfoIds = jdbcTemplate.queryForList(SQL_CINFO_REL_LOAD, Long.class,
				new Object[] { departmentId });
		Long cinfoId = (cinfoIds != null && !cinfoIds.isEmpty()) ? cinfoIds.get(0) : null;

		// delete relationship entry & contact info entry, if needed
		if (cinfoId != null) {
			jdbcTemplate.update(SQL_CINFO_REL_DELETE, new Object[] { departmentId });
			contactInfoDao.delete(cinfoId);
		}

		// delete the row from department table
		int count = jdbcTemplate.update(SQL_DELETE, new Object[] { departmentId });
		LOG.debug("deleted department, count = {}, id = {}", count, departmentId);

		return (count > 0);
	}

	/**
	 * @see net.bhira.sample.api.dao.DepartmentDao#loadByCompany(long)
	 */
	@Override
	public List<Department> loadByCompany(long companyId) {
		List<Department> list = jdbcTemplate.query(SQL_LOAD_BY_COMPANY, new Object[] { companyId },
				new DepartmentRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded departments by company, count = {}, companyId = {}", count, companyId);
		return list;
	}

}