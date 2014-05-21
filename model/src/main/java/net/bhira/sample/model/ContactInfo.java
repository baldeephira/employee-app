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

import java.io.Serializable;

/**
 * Class used to capture generic contact information.  An entity e.g.
 * Company or Department or Employee can be communicated with using one
 * or more properties defined in ContactInfo class. It is a second class
 * entity and is always contained within another entity like Company,
 * Department or Employee.
 * 
 * @author Baldeep Hira
 */
public class ContactInfo implements Serializable {

	private static final long serialVersionUID = 20140520L;

	private long id;
	private String phone;
	private String fax;
	private String email;
	private String website;

	
	//-------------------------UTILITY METHODS----------------------------
	
	/**
	 * Check this model is a new instance.
	 * @return true if the model is a new instance.
	 */
	public boolean isNew() {
		return (id == 0);
	}

	
	//-------------------------GETTERS AND SETTERS-------------------------
	
	/**
	 * Get the ID for the model.
	 * @return the ID of the model.
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Set the ID for the model.
	 * @param id the ID of the model.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Get the phone number for the entity.
	 * @return the phone number for the entity.
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Set the phone number for the entity.
	 * @param phone the phone number for the entity.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * Get the fax number for the entity.
	 * @return the fax number for the entity.
	 */
	public String getFax() {
		return fax;
	}
	
	/**
	 * Set the fax number for the entity.
	 * @param fax the fax number for the entity.
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	/**
	 * Get the email for the entity.
	 * @return the email for the entity.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Set the email for the entity.
	 * @param email the email for the entity.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Get the website url for the entity.
	 * @return the website url for the entity.
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * Set the website url for the entity.
	 * @param website the website url for the entity.
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

}