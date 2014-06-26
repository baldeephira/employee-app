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

import net.bhira.sample.api.dao.DepartmentDao;
import net.bhira.sample.model.Department;

/**
 * Implementation of {@link net.bhira.sample.api.service.DepartmentService}. It provides CRUD
 * operations for {@link net.bhira.sample.model.Department}.
 * 
 * @author Baldeep Hira
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentDao departmentDao;

	/**
	 * @see net.bhira.sample.api.service.DepartmentService#load(long)
	 */
	@Override
	public Department load(long departmentId) {
		return departmentDao.load(departmentId);
	}

	/**
	 * @see net.bhira.sample.api.service.DepartmentService#save(net.bhira.sample.model.Department)
	 */
	@Override
	public void save(Department department) {
		departmentDao.save(department);
	}

	/**
	 * @see net.bhira.sample.api.service.DepartmentService#delete(long)
	 */
	@Override
	public boolean delete(long departmentId) {
		return departmentDao.delete(departmentId);
	}

	/**
	 * @see net.bhira.sample.api.service.DepartmentService#loadByCompany()
	 */
	@Override
	public List<Department> loadByCompany(long companyId) {
		return departmentDao.loadByCompany(companyId);
	}

}