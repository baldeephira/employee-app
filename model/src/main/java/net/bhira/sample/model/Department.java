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
package net.bhira.sample.model;

import net.bhira.sample.common.exception.InvalidObjectException;

/**
 * Class that represents a department within a Company. There can be many departments in the
 * Company, but each department has a unique name. The department list is flat (not hierarchical)
 * i.e. departments cannot contain departments.
 * 
 * @author Baldeep Hira
 */
public class Department extends BaseModel {

	private static final long serialVersionUID = 20140520L;

	private long companyId;
	private Address billingAddress;
	private Address shippingAddress;
	private ContactInfo contactInfo;

	/**
	 * Default public constructor for Department.
	 */
	public Department() {
		super();
	}

	// -------------------------UTILITY METHODS----------------------------

	/**
	 * Check if this instance of Department is valid. It throws and exception with detailed message
	 * in case of instance is invalid.
	 * 
	 * @throws InvalidObjectException
	 *             if instance is invalid.
	 */
	public void validate() throws InvalidObjectException {
		super.validate();
		if (companyId <= 0) {
			throw new InvalidObjectException("The required attribute 'companyId' is missing.");
		}
	}

	// -------------------------GETTERS AND SETTERS-------------------------

	/**
	 * Get the ID of the company to which the department belongs.
	 * 
	 * @return the ID of the company to which the department belongs.
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * Set the ID of the company to which the department belongs.
	 * 
	 * @param companyId
	 *            the ID of the company to which the department belongs.
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * Get the billing address for the department.
	 * 
	 * @return the billing address for the department.
	 */
	public Address getBillingAddress() {
		return billingAddress;
	}

	/**
	 * Set the billing address for the department.
	 * 
	 * @param address
	 *            the billing address for the department.
	 */
	public void setBillingAddress(Address address) {
		billingAddress = address;
	}

	/**
	 * Get the shipping address for the department.
	 * 
	 * @return the shipping address for the department.
	 */
	public Address getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * Set the shipping address for the department.
	 * 
	 * @param address
	 *            the shipping address for the department.
	 */
	public void setShippingAddress(Address address) {
		shippingAddress = address;
	}

	/**
	 * Get the contact information for the department.
	 * 
	 * @return the contact information for the department.
	 */
	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	/**
	 * Set the contact information for the department.
	 * 
	 * @param contactInfo
	 *            the contact information for the department.
	 */
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

}