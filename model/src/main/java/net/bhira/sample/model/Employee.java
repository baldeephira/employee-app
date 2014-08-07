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

import java.util.Date;

import net.bhira.sample.common.exception.InvalidObjectException;

/**
 * Class used to define an Employee. The employee class is hierarchical, where an Employee X can
 * have a manager Y (who is also an employee of the same Company), and Y's manager is Employee Z. An
 * employee must always belong to one and only one Company and optionally may belong to one
 * Department as well.
 * 
 * @author Baldeep Hira
 */
public class Employee extends BaseModel {

	public enum Sex {
		FEMALE, MALE
	};

	private static final long serialVersionUID = 20140520L;

	private long companyId;
	private long departmentId;
	private long managerId;
	private String salutation;
	private Sex sex;
	private Date dob;
	private String title;
	private String address;
	private ContactInfo contactInfo;

	/**
	 * Default public constructor for Employee.
	 */
	public Employee() {
		super();
	}

	// -------------------------UTILITY METHODS----------------------------

	/**
	 * Check if this instance of Employee is valid. It throws and exception with detailed message in
	 * case of instance is invalid.
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
	 * Get the ID of the company to which the employee belongs.
	 * 
	 * @return the ID of the company to which the employee belongs.
	 */
	public long getCompanyId() {
		return companyId;
	}

	/**
	 * Set the ID of the company to which the employee belongs.
	 * 
	 * @param companyId
	 *            the ID of the company to which the employee belongs.
	 */
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	/**
	 * Get the ID of the department to which the employee belongs.
	 * 
	 * @return the ID of the department to which the employee belongs.
	 */
	public long getDepartmentId() {
		return departmentId;
	}

	/**
	 * Set the ID of the department to which the employee belongs.
	 * 
	 * @param departmentId
	 *            the ID of the department to which the employee belongs.
	 */
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * Get the employee ID of the manager for this employee.
	 * 
	 * @return the employee ID of the manager.
	 */
	public long getManagerId() {
		return managerId;
	}

	/**
	 * Set the employee ID of the manager for this employee.
	 * 
	 * @param managerId
	 *            the employee ID of the manager.
	 */
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}

	/**
	 * Get the salutation for the employee.
	 * 
	 * @return the salutation for the employee.
	 */
	public String getSalutation() {
		return salutation;
	}

	/**
	 * Set the salutation for the employee.
	 * 
	 * @param salutation
	 *            the salutation for the employee.
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	/**
	 * Get the sex of the employee.
	 * 
	 * @return the sex of the employee as represented by Employee.Sex
	 */
	public Sex getSex() {
		return sex;
	}

	/**
	 * Set the sex of the employee.
	 * 
	 * @param sex
	 *            the sex of the employee as represented by Employee.Sex
	 */
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	/**
	 * Get the date of birth for the employee.
	 * 
	 * @return the date of birth for the employee.
	 */
	public Date getDOB() {
		return dob;
	}

	/**
	 * Set the date of birth for the employee.
	 * 
	 * @param dob
	 *            the date of birth for the employee.
	 */
	public void setDOB(Date dob) {
		this.dob = dob;
	}

	/**
	 * Get the title of the employee.
	 * 
	 * @return the title of the employee.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the title of the employee.
	 * 
	 * @param title
	 *            the title of the employee.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get the address for the employee.
	 * 
	 * @return the address for the employee.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the address for the employee.
	 * 
	 * @param address
	 *            the address for the employee.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Get the contact information for the employee.
	 * 
	 * @return the contact information for the employee.
	 */
	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	/**
	 * Set the contact information for the employee.
	 * 
	 * @param contactInfo
	 *            the contact information for the employee.
	 */
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

}