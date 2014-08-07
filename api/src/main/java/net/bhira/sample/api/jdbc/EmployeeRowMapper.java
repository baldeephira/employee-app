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
package net.bhira.sample.api.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import net.bhira.sample.model.Employee;

/**
 * Helper class used to map a single row in jdbc ResultSet to
 * {@link net.bhira.sample.model.Employee} object.
 * 
 * @author Baldeep Hira
 */
public class EmployeeRowMapper implements RowMapper<Employee> {

	/**
	 * Constructor for EmployeeRowMapper that creates an instance of
	 * {@link net.bhira.sample.model.Employee} from row represented by rowNum in the given
	 * ResultSet.
	 * 
	 * @param rs
	 *            an instance of ResultSet to be processed.
	 * @param rowNum
	 *            integer representing the row number in ResultSet.
	 */
	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		Employee employee = new Employee();
		employee.setId(rs.getLong("id"));
		employee.setCompanyId(rs.getLong("companyid"));
		employee.setDepartmentId(rs.getLong("departmentid"));
		employee.setName(rs.getString("name"));
		employee.setManagerId(rs.getLong("managerid"));
		employee.setSalutation(rs.getString("salutation"));
		employee.setDOB(rs.getDate("dob"));
		employee.setTitle(rs.getString("title"));

		String sex = rs.getString("sex");
		if (sex != null) {
			employee.setSex(Employee.Sex.valueOf(sex));
		}

		employee.setAddress(rs.getString("addr"));
		employee.setCreated(rs.getTimestamp("created"));
		employee.setModified(rs.getTimestamp("modified"));
		employee.setCreatedBy(rs.getString("createdby"));
		employee.setModifiedBy(rs.getString("modifiedby"));
		return employee;
	}

}