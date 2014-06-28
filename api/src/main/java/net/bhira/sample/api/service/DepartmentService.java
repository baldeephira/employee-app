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

import net.bhira.sample.common.exception.DuplicateNameException;
import net.bhira.sample.common.exception.InvalidObjectException;
import net.bhira.sample.common.exception.InvalidReferenceException;
import net.bhira.sample.common.exception.ObjectNotFoundException;
import net.bhira.sample.model.Department;

/**
 * Interface for Department Service. It provides CRUD operations for
 * {@link net.bhira.sample.model.Department}.
 * 
 * @author Baldeep Hira
 */
public interface DepartmentService {

	/**
	 * Load {@link net.bhira.sample.model.Department} instance from repository identified by the
	 * specified departmentId.
	 * 
	 * @param departmentId
	 *            the ID of {@link net.bhira.sample.model.Department}.
	 * @return an instance of {@link net.bhira.sample.model.Department}.
	 */
	public Department load(long departmentId);

	/**
	 * Save the specified {@link net.bhira.sample.model.Department} to repository. It will take care
	 * of both insert and update scenarios. In case of insert the newly assigned ID is set back in
	 * the passed department model.
	 * 
	 * @param department
	 *            an instance of {@link net.bhira.sample.model.Department}.
	 * @throws ObjectNotFoundException
	 *             if the department instance being saved is not found in repository.
	 * @throws DuplicateNameException
	 *             if the department name being used is duplicate and is already in use.
	 * @throws InvalidObjectException
	 *             if the department instance being saved is invalid.
	 * @throws InvalidReferenceException
	 *             if the department instance being saved has invalid references.
	 */
	public void save(Department department) throws ObjectNotFoundException, DuplicateNameException,
			InvalidObjectException, InvalidReferenceException;;

	/**
	 * Delete the instance of {@link net.bhira.sample.model.Department} identified by given
	 * departmentId. It will take care of deleting all dependent models.
	 * 
	 * @param departmentId
	 *            the ID of {@link net.bhira.sample.model.Department}.
	 * @return true if delete was successful, else return false.
	 */
	public boolean delete(long departmentId);

	/**
	 * Load all the {@link net.bhira.sample.model.Department} instances for the given companyId from
	 * repository. It returns a shallow instance of Department class and does not load the dependent
	 * objects like billingAddress, shippingAddress and contactInfo
	 * 
	 * @param companyId
	 *            the ID of {@link net.bhira.sample.model.Company}.
	 * @return List of {@link net.bhira.sample.model.Department} instances.
	 */
	public List<Department> loadByCompany(long companyId);

}