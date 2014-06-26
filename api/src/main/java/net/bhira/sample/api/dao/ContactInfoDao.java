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

import net.bhira.sample.model.ContactInfo;

/**
 * Interface for ContactInfo DAO. It provides CRUD operations for
 * {@link net.bhira.sample.model.ContactInfo}.
 * 
 * @author Baldeep Hira
 */
public interface ContactInfoDao {

	/**
	 * Load {@link net.bhira.sample.model.ContactInfo} instance from repository identified by the
	 * specified contactInfoId.
	 * 
	 * @param contactInfoId
	 *            the ID of {@link net.bhira.sample.model.ContactInfo}.
	 * @return an instance of {@link net.bhira.sample.model.ContactInfo}.
	 */
	public ContactInfo load(long contactInfoId);

	/**
	 * Save the specified {@link net.bhira.sample.model.ContactInfo} to repository. It will take
	 * care of both insert and update scenarios. In case of insert the newly assigned ID is set back
	 * in the passed contactInfo model.
	 * 
	 * @param contactInfo
	 *            an instance of {@link net.bhira.sample.model.ContactInfo}.
	 */
	public void save(ContactInfo contactInfo);

	/**
	 * Delete the instance of {@link net.bhira.sample.model.ContactInfo} identified by given
	 * contactInfoId. It will take care of deleting all dependent models.
	 * 
	 * @param contactInfoId
	 *            the ID of {@link net.bhira.sample.model.ContactInfo}.
	 * @return true if delete was successful, else return false.
	 */
	public boolean delete(long contactInfoId);

}
