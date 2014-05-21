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

/**
 * This model captures the definition for a Company. It includes the company details
 * like name, address (both billing and shipping) and other contact properties like
 * phone, email and web site.
 * 
 * @author Baldeep Hira
 */
public class Company extends BaseModel {

	private static final long serialVersionUID = 20140520L;

	private String industry;
	private Address billingAddress;
	private Address shippingAddress;
	private ContactInfo contactInfo;
	
	/**
	 * Default public constructor for Company. 
	 */
	public Company() {
		super();
	}
	
	
	//-------------------------GETTERS AND SETTERS-------------------------
	
	/**
	 * Get the industry category to which this company belongs.
	 * @return the industry category to which this company belongs.
	 */
	public String getIndustry() {
		return industry;
	}
	
	/**
	 * Set the industry category to which this company belongs.
	 * @param industry the industry category to which this company belongs.
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	
	/**
	 * Get the billing address for the company.
	 * @return the billing address for the company.
	 */
	public Address getBillingAddress() {
		return billingAddress;
	}
	
	/**
	 * Set the billing address for the company.
	 * @param address the billing address for the company.
	 */
	public void setBillingAddress(Address address) {
		billingAddress = address;
		if (billingAddress != null) {
			billingAddress.setType(Address.AddressType.BILLING);
		}
	}
	
	/**
	 * Get the shipping address for the company.
	 * @return the shipping address for the company.
	 */
	public Address getShippingAddress() {
		return shippingAddress;
	}
	
	/**
	 * Set the shipping address for the company.
	 * @param address the shipping address for the company.
	 */
	public void setShippingAddress(Address address) {
		shippingAddress = address;
		if (shippingAddress != null) {
			shippingAddress.setType(Address.AddressType.SHIPPING);
		}
	}

	/**
	 * Get the contact information for the company.
	 * @return the contact information for the company.
	 */
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	
	/**
	 * Set the contact information for the company.
	 * @param contactInfo the contact information for the company.
	 */
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}
	
}