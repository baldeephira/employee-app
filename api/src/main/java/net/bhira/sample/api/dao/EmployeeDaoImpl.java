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
import java.sql.Date;
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

import net.bhira.sample.api.jdbc.EmployeeRowMapper;
import net.bhira.sample.common.exception.InvalidObjectException;
import net.bhira.sample.common.exception.InvalidReferenceException;
import net.bhira.sample.common.exception.ObjectNotFoundException;
import net.bhira.sample.model.Employee;

/**
 * Implementation for Employee DAO. It implements CRUD operations for
 * {@link net.bhira.sample.model.Employee}. The client code should always invoke methods in Service
 * class {@link net.bhira.sample.api.service.EmployeeService} as service wraps the DAO persistence
 * calls with transaction semantics.
 * 
 * @author Baldeep Hira
 */
@Repository("employeeDao")
public class EmployeeDaoImpl implements EmployeeDao {

	/**
	 * private singleton instance of the SLF4J logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeDaoImpl.class);

	private static final String SQL_LOAD_BY_ID = "select * from employee where id = ?";
	private static final String SQL_LOAD_BY_COMPANY = "select * from employee where companyid = ?";
	private static final String SQL_LOAD_BY_DEPARTMENT = "select * from employee where departmentid = ?";
	private static final String SQL_INSERT = "insert into employee"
			+ " (companyid, departmentid, name, managerid, salutation, sex, dob,"
			+ "  title, addr, contactinfo, created, modified, createdby, modifiedby)"
			+ " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "update employee set companyid = ?, departmentid = ?, name = ?, "
			+ "managerid = ?, salutation = ?, sex = ?, dob = ?, title = ?, addr = ?, "
			+ "contactinfo = ?, modified = ?, modifiedby = ? where id = ?";
	private static final String SQL_DELETE = "delete from employee where id = ?";

	@Autowired
	DataSource dataSource;

	@Autowired
	AddressDao addressDao;

	@Autowired
	ContactInfoDao contactInfoDao;

	/**
	 * @see net.bhira.sample.api.dao.EmployeeDao#load(long)
	 */
	@Override
	public Employee load(long employeeId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Employee> list = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { employeeId },
				new EmployeeRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded employee, count = {}, id = {}", count, employeeId);

		if (count > 0) {
			Employee employee = list.get(0);
			if (employee.getAddress() != null) {
				employee.setAddress(addressDao.load(employee.getAddress().getId()));
			}
			if (employee.getContactInfo() != null) {
				employee.setContactInfo(contactInfoDao.load(employee.getContactInfo().getId()));
			}
			return employee;
		} else {
			return null;
		}
	}

	/**
	 * @see net.bhira.sample.api.dao.EmployeeDao#save(net.bhira.sample.model.Employee)
	 */
	@Override
	public void save(Employee employee) throws ObjectNotFoundException, InvalidObjectException,
			InvalidReferenceException {
		try {
			if (employee == null) {
				throw new InvalidObjectException("Employee object is null.");
			}

			employee.initForSave();
			employee.validate();

			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

			// load old model, for cleaning dependent tables
			Employee oldModel = null;
			if (!employee.isNew()) {
				List<Employee> list = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { employee.getId() },
						new EmployeeRowMapper());
				if (list != null && !list.isEmpty()) {
					oldModel = list.get(0);
				}
			}

			// save contained objects in dependent tables
			if (employee.getAddress() != null) {
				if (oldModel != null && oldModel.getAddress() != null) {
					employee.getAddress().setId(oldModel.getAddress().getId());
				}
				addressDao.save(employee.getAddress());
			}
			if (employee.getContactInfo() != null) {
				if (oldModel != null && oldModel.getContactInfo() != null) {
					employee.getContactInfo().setId(oldModel.getContactInfo().getId());
				}
				contactInfoDao.save(employee.getContactInfo());
			}

			int count = 0;
			if (employee.isNew()) {
				// for new employee, construct SQL insert statement
				KeyHolder keyHolder = new GeneratedKeyHolder();
				count = jdbcTemplate.update(new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection)
							throws SQLException {
						PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT,
								Statement.RETURN_GENERATED_KEYS);
						pstmt.setLong(1, employee.getCompanyId());
						if (employee.getDepartmentId() == 0) {
							pstmt.setNull(2, java.sql.Types.BIGINT);
						} else {
							pstmt.setLong(2, employee.getDepartmentId());
						}
						pstmt.setString(3, employee.getName());
						if (employee.getManagerId() == 0) {
							pstmt.setNull(4, java.sql.Types.BIGINT);
						} else {
							pstmt.setLong(4, employee.getManagerId());
						}
						pstmt.setString(5, employee.getSalutation());
						pstmt.setString(6, employee.getSex() == null ? null : employee.getSex().toString());
						pstmt.setDate(7, employee.getDOB() == null ? null : new Date(employee.getDOB()
								.getTime()));
						pstmt.setString(8, employee.getTitle());
						if (employee.getAddress() == null) {
							pstmt.setNull(9, java.sql.Types.BIGINT);
						} else {
							pstmt.setLong(9, employee.getAddress().getId());
						}
						if (employee.getContactInfo() == null) {
							pstmt.setNull(10, java.sql.Types.BIGINT);
						} else {
							pstmt.setLong(10, employee.getContactInfo().getId());
						}
						pstmt.setTimestamp(11, new Timestamp(employee.getCreated().getTime()));
						pstmt.setTimestamp(12, new Timestamp(employee.getModified().getTime()));
						pstmt.setString(13, employee.getCreatedBy());
						pstmt.setString(14, employee.getModifiedBy());
						return pstmt;
					}
				}, keyHolder);

				// fetch the newly created auto-increment ID
				employee.setId(keyHolder.getKey().longValue());
				LOG.debug("inserted employee, count = {}, id = {}", count, employee.getId());

			} else {
				// for existing employee, construct SQL update statement
				Long deptId = employee.getDepartmentId() == 0 ? null : employee.getDepartmentId();
				Long mgrId = employee.getManagerId() == 0 ? null : employee.getManagerId();
				String sex = employee.getSex() == null ? null : employee.getSex().toString();
				Date dob = employee.getDOB() == null ? null : new Date(employee.getDOB().getTime());
				Long bAddrId = employee.getAddress() == null ? null : employee.getAddress().getId();
				Long cInfoId = employee.getContactInfo() == null ? null : employee.getContactInfo().getId();
				Object[] args = new Object[] { employee.getCompanyId(), deptId, employee.getName(), mgrId,
						employee.getSalutation(), sex, dob, employee.getTitle(), bAddrId, cInfoId,
						employee.getModified(), employee.getModifiedBy(), employee.getId() };
				count = jdbcTemplate.update(SQL_UPDATE, args);
				LOG.debug("updated employee, count = {}, id = {}", count, employee.getId());
			}

			// if insert/update has 0 count value, then rollback
			if (count <= 0) {
				throw new ObjectNotFoundException("Employee with ID " + employee.getId() + " was not found.");
			}

			// check if any dependent table entries need to be deleted
			if (oldModel != null) {

				// delete the old address entry (no longer in new model)
				if (employee.getAddress() == null && oldModel.getAddress() != null) {
					addressDao.delete(oldModel.getAddress().getId());
				}

				// delete the old contact info entry (no longer in new model)
				if (employee.getContactInfo() == null && oldModel.getContactInfo() != null) {
					contactInfoDao.delete(oldModel.getContactInfo().getId());
				}
			}
		} catch (DataIntegrityViolationException dive) {
			String msg = dive.getMessage();
			if (msg != null) {
				if (msg.contains("fk_employee_compy")) {
					throw new InvalidReferenceException("Invalid reference for attribute 'companyId'", dive);
				} else if (msg.contains("fk_employee_dept")) {
					throw new InvalidReferenceException("Invalid reference for attribute 'departmentId'",
							dive);
				} else if (msg.contains("fk_employee_mgr")) {
					throw new InvalidReferenceException("Invalid reference for attribute 'managerId'", dive);
				} else if (msg.contains("fk_employee_addr")) {
					throw new InvalidReferenceException("Invalid reference for attribute 'address'", dive);
				} else if (msg.contains("fk_employee_cinfo")) {
					throw new InvalidReferenceException("Invalid reference for attribute 'contactInfo'", dive);
				}
			}
			throw dive;
		}
	}

	/**
	 * @see net.bhira.sample.api.dao.EmployeeDao#delete(long)
	 */
	@Override
	public boolean delete(long employeeId) {
		int count = 0;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		// load the existing employee from database
		List<Employee> employees = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { employeeId },
				new EmployeeRowMapper());

		if (employees != null && !employees.isEmpty()) {
			Employee employee = employees.get(0);

			// delete the row from employee table
			count = jdbcTemplate.update(SQL_DELETE, new Object[] { employeeId });
			LOG.debug("deleted employee, count = {}, id = {}", count, employeeId);

			// delete all rows from dependent tables
			if (employee.getAddress() != null) {
				addressDao.delete(employee.getAddress().getId());
			}
			if (employee.getContactInfo() != null) {
				contactInfoDao.delete(employee.getContactInfo().getId());
			}
		}
		return (count > 0);
	}

	/**
	 * @see net.bhira.sample.api.dao.EmployeeDao#loadByCompany(long)
	 */
	@Override
	public List<Employee> loadByCompany(long companyId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Employee> list = jdbcTemplate.query(SQL_LOAD_BY_COMPANY, new Object[] { companyId },
				new EmployeeRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded employees by company, count = {}, companyId = {}", count, companyId);
		return list;
	}

	/**
	 * @see net.bhira.sample.api.dao.EmployeeDao#loadByDepartment(long)
	 */
	@Override
	public List<Employee> loadByDepartment(long departmentId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Employee> list = jdbcTemplate.query(SQL_LOAD_BY_DEPARTMENT, new Object[] { departmentId },
				new EmployeeRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded employees by department, count = {}, departmentId = {}", count, departmentId);
		return list;
	}

}