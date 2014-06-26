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

import net.bhira.sample.api.jdbc.DepartmentRowMapper;
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
			+ " (companyid, name, billingaddr, shippingaddr, contactinfo, created, modified, createdby, modifiedby)"
			+ " values (?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "update department set companyid = ?, name = ?, billingaddr = ?,"
			+ "shippingaddr = ?, contactinfo = ?, modified = ?, modifiedby = ? where id = ?";
	private static final String SQL_DELETE = "delete from department where id = ?";

	@Autowired
	DataSource dataSource;

	@Autowired
	AddressDao addressDao;

	@Autowired
	ContactInfoDao contactInfoDao;

	/**
	 * @see net.bhira.sample.api.dao.DepartmentDao#load(long)
	 */
	@Override
	public Department load(long departmentId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Department> list = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { departmentId },
				new DepartmentRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded department, count = {}, id = {}", count, departmentId);

		if (count > 0) {
			Department department = list.get(0);
			if (department.getBillingAddress() != null) {
				department.setBillingAddress(addressDao.load(department.getBillingAddress().getId()));
			}
			if (department.getShippingAddress() != null) {
				department.setShippingAddress(addressDao.load(department.getShippingAddress().getId()));
			}
			if (department.getContactInfo() != null) {
				department.setContactInfo(contactInfoDao.load(department.getContactInfo().getId()));
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
	public void save(Department department) {
		if (department == null) {
			return;
		}

		department.initForSave();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		// load old model, for cleaning dependent tables
		Department oldModel = null;
		if (!department.isNew()) {
			List<Department> list = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { department.getId() },
					new DepartmentRowMapper());
			if (list != null && !list.isEmpty()) {
				oldModel = list.get(0);
			}
		}

		// save contained objects in dependent tables
		if (department.getBillingAddress() != null) {
			if (oldModel != null && oldModel.getBillingAddress() != null) {
				department.getBillingAddress().setId(oldModel.getBillingAddress().getId());
			}
			addressDao.save(department.getBillingAddress());
		}
		if (department.getShippingAddress() != null) {
			if (oldModel != null && oldModel.getShippingAddress() != null) {
				department.getShippingAddress().setId(oldModel.getShippingAddress().getId());
			}
			addressDao.save(department.getShippingAddress());
		}
		if (department.getContactInfo() != null) {
			if (oldModel != null && oldModel.getContactInfo() != null) {
				department.getContactInfo().setId(oldModel.getContactInfo().getId());
			}
			contactInfoDao.save(department.getContactInfo());
		}

		int count = 0;
		if (department.isNew()) {
			// for new department, construct SQL insert statement
			KeyHolder keyHolder = new GeneratedKeyHolder();
			count = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT,
							Statement.RETURN_GENERATED_KEYS);
					pstmt.setLong(1, department.getCompanyId());
					pstmt.setString(2, department.getName());
					if (department.getBillingAddress() == null) {
						pstmt.setNull(3, java.sql.Types.BIGINT);
					} else {
						pstmt.setLong(3, department.getBillingAddress().getId());
					}
					if (department.getShippingAddress() == null) {
						pstmt.setNull(4, java.sql.Types.BIGINT);
					} else {
						pstmt.setLong(4, department.getShippingAddress().getId());
					}
					if (department.getContactInfo() == null) {
						pstmt.setNull(5, java.sql.Types.BIGINT);
					} else {
						pstmt.setLong(5, department.getContactInfo().getId());
					}
					pstmt.setTimestamp(6, new Timestamp(department.getCreated().getTime()));
					pstmt.setTimestamp(7, new Timestamp(department.getModified().getTime()));
					pstmt.setString(8, department.getCreatedBy());
					pstmt.setString(9, department.getModifiedBy());
					return pstmt;
				}
			}, keyHolder);

			// fetch the newly created auto-increment ID
			department.setId(keyHolder.getKey().longValue());
			LOG.debug("inserted department, count = {}, id = {}", count, department.getId());

		} else {
			// for existing department, construct SQL update statement
			Long bAddrId = department.getBillingAddress() == null ? null : department.getBillingAddress()
					.getId();
			Long sAddrId = department.getShippingAddress() == null ? null : department.getShippingAddress()
					.getId();
			Long cInfoId = department.getContactInfo() == null ? null : department.getContactInfo().getId();
			Object[] args = new Object[] { department.getCompanyId(), department.getName(), bAddrId, sAddrId,
					cInfoId, department.getModified(), department.getModifiedBy(), department.getId() };
			count = jdbcTemplate.update(SQL_UPDATE, args);
			LOG.debug("updated department, count = {}, id = {}", count, department.getId());
		}

		// if insert/update has 0 count value, then rollback
		if (count <= 0) {
			throw new RuntimeException("Department instance was not saved.");
		}

		// check if any dependent table entries need to be deleted
		if (oldModel != null) {

			// delete the old billing address entry (no longer in new model)
			if (department.getBillingAddress() == null && oldModel.getBillingAddress() != null) {
				addressDao.delete(oldModel.getBillingAddress().getId());
			}

			// check if shippingAddress key needs to be updated
			if (department.getShippingAddress() == null && oldModel.getShippingAddress() != null) {
				addressDao.delete(oldModel.getShippingAddress().getId());
			}

			// delete the old contact info entry (no longer in new model)
			if (department.getContactInfo() == null && oldModel.getContactInfo() != null) {
				contactInfoDao.delete(oldModel.getContactInfo().getId());
			}
		}
	}

	/**
	 * @see net.bhira.sample.api.dao.DepartmentDao#delete(long)
	 */
	@Override
	public boolean delete(long departmentId) {
		int count = 0;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		// load the existing department from database
		List<Department> departments = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { departmentId },
				new DepartmentRowMapper());

		if (departments != null && !departments.isEmpty()) {
			Department department = departments.get(0);

			// delete the row from department table
			count = jdbcTemplate.update(SQL_DELETE, new Object[] { departmentId });
			LOG.debug("deleted department, count = {}, id = {}", count, departmentId);

			// delete all rows from dependent tables
			if (department.getBillingAddress() != null) {
				addressDao.delete(department.getBillingAddress().getId());
			}
			if (department.getShippingAddress() != null) {
				addressDao.delete(department.getShippingAddress().getId());
			}
			if (department.getContactInfo() != null) {
				contactInfoDao.delete(department.getContactInfo().getId());
			}
		}
		return (count > 0);
	}

	/**
	 * @see net.bhira.sample.api.dao.DepartmentDao#loadByCompany(long)
	 */
	@Override
	public List<Department> loadByCompany(long companyId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Department> list = jdbcTemplate.query(SQL_LOAD_BY_COMPANY, new Object[] { companyId },
				new DepartmentRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded departments by company, count = {}, companyId = {}", count, companyId);
		return list;
	}

}