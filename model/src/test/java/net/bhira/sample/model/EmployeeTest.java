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

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/**
 * Test class for {@link net.bhira.sample.model.Employee}. It uses JUnit 4 for generating test
 * cases.
 * 
 * @author Baldeep Hira
 */
public class EmployeeTest {

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#Employee()}.
	 */
	@Test
	public void testEmployee() {
		Employee employee = new Employee();
		assertTrue(employee.isNew());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#getCompanyId()}.
	 */
	@Test
	public void testGetCompanyId() {
		Employee employee = new Employee();
		employee.setCompanyId(1234);
		assertEquals(1234, employee.getCompanyId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#setCompanyId(long)}.
	 */
	@Test
	public void testSetCompanyId() {
		Employee employee = new Employee();
		employee.setCompanyId(1234);
		assertEquals(1234, employee.getCompanyId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#getDepartmentId()}.
	 */
	@Test
	public void testGetDepartmentId() {
		Employee employee = new Employee();
		employee.setDepartmentId(1234);
		assertEquals(1234, employee.getDepartmentId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#setDepartmentId(long)}.
	 */
	@Test
	public void testSetDepartmentId() {
		Employee employee = new Employee();
		employee.setDepartmentId(1234);
		assertEquals(1234, employee.getDepartmentId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#getManagerId()}.
	 */
	@Test
	public void testGetManagerId() {
		Employee employee = new Employee();
		employee.setManagerId(1234);
		assertEquals(1234, employee.getManagerId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#setManagerId(long)}.
	 */
	@Test
	public void testSetManagerId() {
		Employee employee = new Employee();
		employee.setManagerId(1234);
		assertEquals(1234, employee.getManagerId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#getSalutation()}.
	 */
	@Test
	public void testGetSalutation() {
		Employee employee = new Employee();
		employee.setSalutation("Dr.");
		assertEquals("Dr.", employee.getSalutation());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#setSalutation(java.lang.String)}.
	 */
	@Test
	public void testSetSalutation() {
		Employee employee = new Employee();
		employee.setSalutation("Dr.");
		assertEquals("Dr.", employee.getSalutation());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#getSex()}.
	 */
	@Test
	public void testGetSex() {
		Employee employee = new Employee();
		employee.setSex(Employee.Sex.FEMALE);
		assertEquals(Employee.Sex.FEMALE, employee.getSex());
	}

	/**
	 * Test method for
	 * {@link net.bhira.sample.model.Employee#setSex(net.bhira.sample.model.Employee.Sex)}.
	 */
	@Test
	public void testSetSex() {
		Employee employee = new Employee();
		employee.setSex(Employee.Sex.FEMALE);
		assertEquals(Employee.Sex.FEMALE, employee.getSex());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#getDOB()}.
	 */
	@Test
	public void testGetDOB() {
		Employee employee = new Employee();
		Date date = new Date();
		employee.setDOB(date);
		assertEquals(date, employee.getDOB());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#setDOB(java.util.Date)}.
	 */
	@Test
	public void testSetDOB() {
		Employee employee = new Employee();
		Date date = new Date();
		employee.setDOB(date);
		assertEquals(date, employee.getDOB());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		Employee employee = new Employee();
		employee.setTitle("Software Engineer");
		assertEquals("Software Engineer", employee.getTitle());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#setTitle(java.lang.String)}.
	 */
	@Test
	public void testSetTitle() {
		Employee employee = new Employee();
		employee.setTitle("Software Engineer");
		assertEquals("Software Engineer", employee.getTitle());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#getAddress()}.
	 */
	@Test
	public void testGetAddress() {
		Employee employee = new Employee();
		Address address = new Address();
		address.setStreet("123 Main Street");
		address.setCity("San Francisco");
		address.setState("CA");
		address.setZipcode("94024");
		address.setCountry("USA");
		employee.setAddress(address);
		assertEquals(address, employee.getAddress());
	}

	/**
	 * Test method for
	 * {@link net.bhira.sample.model.Employee#setAddress(net.bhira.sample.model.Address)}.
	 */
	@Test
	public void testSetAddress() {
		Employee employee = new Employee();
		Address address = new Address();
		address.setStreet("123 Main Street");
		address.setCity("San Francisco");
		address.setState("CA");
		address.setZipcode("94024");
		address.setCountry("USA");
		employee.setAddress(address);
		assertEquals(address, employee.getAddress());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Employee#getContactInfo()}.
	 */
	@Test
	public void testGetContactInfo() {
		Employee employee = new Employee();
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setPhone("800-100-1234");
		contactInfo.setFax("800-100-1235");
		contactInfo.setEmail("xyz@xyz.com");
		contactInfo.setWebsite("http://bhira.net");
		employee.setContactInfo(contactInfo);
		assertEquals(contactInfo, employee.getContactInfo());
	}

	/**
	 * Test method for
	 * {@link net.bhira.sample.model.Employee#setContactInfo(net.bhira.sample.model.ContactInfo)}.
	 */
	@Test
	public void testSetContactInfo() {
		Employee employee = new Employee();
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setPhone("800-100-1234");
		contactInfo.setFax("800-100-1235");
		contactInfo.setEmail("xyz@xyz.com");
		contactInfo.setWebsite("http://bhira.net");
		employee.setContactInfo(contactInfo);
		assertEquals(contactInfo, employee.getContactInfo());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#isNew()}.
	 */
	@Test
	public void testIsNew() {
		Employee employee = new Employee();
		assertTrue(employee.isNew());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#initForSave()}.
	 */
	@Test
	public void testInitForSave() {
		Employee employee = new Employee();
		employee.setId(123);
		employee.initForSave();
		assertNotNull(employee.getModified());
		assertNotNull(employee.getModifiedBy());
		assertNull(employee.getCreated());
		assertNull(employee.getCreatedBy());

		employee = new Employee();
		employee.initForSave();
		assertNotNull(employee.getModified());
		assertNotNull(employee.getModifiedBy());
		assertNotNull(employee.getCreated());
		assertNotNull(employee.getCreatedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getId()}.
	 */
	@Test
	public void testGetId() {
		Employee employee = new Employee();
		employee.setId(123);
		assertEquals(123, employee.getId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setId(long)}.
	 */
	@Test
	public void testSetId() {
		Employee employee = new Employee();
		employee.setId(123);
		assertEquals(123, employee.getId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getName()}.
	 */
	@Test
	public void testGetName() {
		Employee employee = new Employee();
		employee.setName("Baldeep Hira");
		assertEquals("Baldeep Hira", employee.getName());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		Employee employee = new Employee();
		employee.setName("Baldeep Hira");
		assertEquals("Baldeep Hira", employee.getName());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getCreated()}.
	 */
	@Test
	public void testGetCreated() {
		Employee employee = new Employee();
		Date now = new Date();
		employee.setCreated(now);
		assertEquals(now, employee.getCreated());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setCreated(java.util.Date)}.
	 */
	@Test
	public void testSetCreated() {
		Employee employee = new Employee();
		Date now = new Date();
		employee.setCreated(now);
		assertEquals(now, employee.getCreated());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getModified()}.
	 */
	@Test
	public void testGetModified() {
		Employee employee = new Employee();
		Date now = new Date();
		employee.setModified(now);
		assertEquals(now, employee.getModified());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setModified(java.util.Date)}.
	 */
	@Test
	public void testSetModified() {
		Employee employee = new Employee();
		Date now = new Date();
		employee.setModified(now);
		assertEquals(now, employee.getModified());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getCreatedBy()}.
	 */
	@Test
	public void testGetCreatedBy() {
		Employee employee = new Employee();
		employee.setCreatedBy("Baldeep Hira");
		assertEquals("Baldeep Hira", employee.getCreatedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public void testSetCreatedBy() {
		Employee employee = new Employee();
		employee.setCreatedBy("Baldeep Hira");
		assertEquals("Baldeep Hira", employee.getCreatedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getModifiedBy()}.
	 */
	@Test
	public void testGetModifiedBy() {
		Employee employee = new Employee();
		employee.setModifiedBy("Baldeep Hira");
		assertEquals("Baldeep Hira", employee.getModifiedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setModifiedBy(java.lang.String)}.
	 */
	@Test
	public void testSetModifiedBy() {
		Employee employee = new Employee();
		employee.setModifiedBy("Baldeep Hira");
		assertEquals("Baldeep Hira", employee.getModifiedBy());
	}

}