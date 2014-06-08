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

import net.bhira.sample.model.Company;

/**
 * Interface for Company Service. It provides CRUD operations for
 * {@link net.bhira.sample.model.Company}.
 * 
 * @author Baldeep Hira
 */
public interface CompanyService {

	/**
	 * Load {@link net.bhira.sample.model.Company} instance from repository identified by the
	 * specified companyId.
	 * 
	 * @param companyId
	 *            the ID of {@link net.bhira.sample.model.Company}.
	 * @return an instance of {@link net.bhira.sample.model.Company}.
	 */
	public Company load(long companyId);

	/**
	 * Save the specified {@link net.bhira.sample.model.Company} to repository. It will take care of
	 * both insert and update scenarios. It returns the updated
	 * {@link net.bhira.sample.model.Company} with assigned ID.
	 * 
	 * @param company
	 *            an instance of {@link net.bhira.sample.model.Company}.
	 * @return an instance of {@link net.bhira.sample.model.Company} with assigned ID.
	 */
	public Company save(Company company);

	/**
	 * Delete the instance of {@link net.bhira.sample.model.Company} identified by given companyId.
	 * It will take care of deleting all dependent models.
	 * 
	 * @param companyId
	 *            the ID of {@link net.bhira.sample.model.Company}.
	 * @return true if delete was successful, else return false.
	 */
	public boolean delete(long companyId);

	/**
	 * Load all the {@link net.bhira.sample.model.Company} instances from repository. It returns a
	 * shallow instance of Company class and does not load the dependent objects like
	 * billingAddress, shippingAddress and contactInfo
	 * 
	 * @return List of {@link net.bhira.sample.model.Company} instances.
	 */
	public List<Company> loadAll();
}