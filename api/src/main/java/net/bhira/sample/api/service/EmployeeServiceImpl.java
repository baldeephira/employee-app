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
package net.bhira.sample.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bhira.sample.api.dao.EmployeeDao;
import net.bhira.sample.common.exception.InvalidObjectException;
import net.bhira.sample.common.exception.InvalidReferenceException;
import net.bhira.sample.common.exception.ObjectNotFoundException;
import net.bhira.sample.model.Employee;

/**
 * Implementation of {@link net.bhira.sample.api.service.EmployeeService}. It provides CRUD
 * operations for {@link net.bhira.sample.model.Employee}.
 * 
 * @author Baldeep Hira
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	/**
	 * @see net.bhira.sample.api.service.EmployeeService#load(long)
	 */
	@Override
	public Employee load(long employeeId) {
		return employeeDao.load(employeeId);
	}

	/**
	 * @see net.bhira.sample.api.service.EmployeeService#save(net.bhira.sample.model.Employee)
	 */
	@Override
	public void save(Employee employee) throws ObjectNotFoundException, InvalidObjectException,
			InvalidReferenceException {
		employeeDao.save(employee);
	}

	/**
	 * @see net.bhira.sample.api.service.EmployeeService#delete(long)
	 */
	@Override
	public boolean delete(long employeeId) {
		return employeeDao.delete(employeeId);
	}

	/**
	 * @see net.bhira.sample.api.service.EmployeeService#loadByCompany()
	 */
	@Override
	public List<Employee> loadByCompany(long companyId) {
		return employeeDao.loadByCompany(companyId);
	}

	/**
	 * @see net.bhira.sample.api.service.EmployeeService#loadByDepartment()
	 */
	@Override
	public List<Employee> loadByDepartment(long departmentId) {
		return employeeDao.loadByDepartment(departmentId);
	}

}