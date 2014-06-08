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

import net.bhira.sample.model.Address;

/**
 * Interface for Address DAO. It provides CRUD operations for {@link net.bhira.sample.model.Address}
 * .
 * 
 * @author Baldeep Hira
 */
public interface AddressDao {

	/**
	 * Load {@link net.bhira.sample.model.Address} instance from repository identified by the
	 * specified addressId.
	 * 
	 * @param addressId
	 *            the ID of {@link net.bhira.sample.model.Address}.
	 * @return an instance of {@link net.bhira.sample.model.Address}.
	 */
	public Address load(long addressId);

	/**
	 * Save the specified {@link net.bhira.sample.model.Address} to repository. It will take care of
	 * both insert and update scenarios. It returns the updated
	 * {@link net.bhira.sample.model.Address} with assigned ID.
	 * 
	 * @param address
	 *            an instance of {@link net.bhira.sample.model.Address}.
	 * @return an instance of {@link net.bhira.sample.model.Address} with assigned ID.
	 */
	public Address save(Address address);

	/**
	 * Delete the instance of {@link net.bhira.sample.model.Address} identified by given addressId.
	 * It will take care of deleting all dependent models.
	 * 
	 * @param addressId
	 *            the ID of {@link net.bhira.sample.model.Address}.
	 * @return true if delete was successful, else return false.
	 */
	public boolean delete(long addressId);

}