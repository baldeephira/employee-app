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
 * User class that is used to define all the users in the system.  Most of the
 * app is available only to authenticated users. User class is used for
 * authentication and for auditing various actions in the app.
 * 
 * @author Baldeep Hira
 */
public class User extends BaseModel {

	private static final long serialVersionUID = 20140520L;

	private long companyId;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String timeZoneId;
	

	/**
	 * Default public constructor for User.
	 */
	public User() {
		super();
	}
	
	
	//-------------------------GETTERS AND SETTERS-------------------------

	/**
	 * Get the ID of the company to which the user belongs.
	 * @return the ID of the company to which the user belongs.
	 */
	public long getCompanyId() {
		return companyId;
	}
	
	/**
	 * Set the ID of the company to which the user belongs.
	 * @param companyId the ID of the company to which the user belongs.
 	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	
	/**
	 * Get the unencrypted password for the user.
	 * @return the unencrypted password for the user.
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Set the unencrypted password for the user.
	 * @param password the unencrypted password for the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Get the first name of the user.
	 * @return the first name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Set the first name of the user.
	 * @param firstName the first name of the user.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Get the last name of the user.
	 * @return the last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Set the last name of the user.
	 * @param lastName the last name of the user.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Get the email for the user.
	 * @return the email for the user.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Set the email for the user.
	 * @param email the email for the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Get the time zone ID for the user as defined in the String[]
	 * returned by TimeZone.getAvailableIDs().
	 * @return the time zone ID.
	 */
	public String getTimeZoneId() {
		return timeZoneId;
	}
	
	/**
	 * Set the time zone ID for the user as defined in the the String[]
	 * returned by TimeZone.getAvailableIDs().
	 * @param timeZoneId the time zone ID.
	 */
	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}
	
}