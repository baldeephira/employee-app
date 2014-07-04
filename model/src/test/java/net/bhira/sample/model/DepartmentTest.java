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
 * Test class for {@link net.bhira.sample.model.Department}. It uses JUnit 4 for generating test
 * cases.
 * 
 * @author Baldeep Hira
 */
public class DepartmentTest {

	/**
	 * Test method for {@link net.bhira.sample.model.Department#Department()}.
	 */
	@Test
	public void testDepartment() {
		Department department = new Department();
		assertTrue(department.isNew());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Department#getCompanyId()}.
	 */
	@Test
	public void testGetCompanyId() {
		Department department = new Department();
		department.setCompanyId(1234);
		assertEquals(1234, department.getCompanyId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Department#setCompanyId(long)}.
	 */
	@Test
	public void testSetCompanyId() {
		Department department = new Department();
		department.setCompanyId(1234);
		assertEquals(1234, department.getCompanyId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Department#getBillingAddress()}.
	 */
	@Test
	public void testGetBillingAddress() {
		Department department = new Department();
		Address address = new Address();
		address.setStreet("123 Main Street");
		address.setCity("San Francisco");
		address.setState("CA");
		address.setZipcode("94024");
		address.setCountry("USA");
		department.setBillingAddress(address);
		assertEquals(address, department.getBillingAddress());
	}

	/**
	 * Test method for
	 * {@link net.bhira.sample.model.Department#setBillingAddress(net.bhira.sample.model.Address)}.
	 */
	@Test
	public void testSetBillingAddress() {
		Department department = new Department();
		Address address = new Address();
		address.setStreet("123 Main Street");
		address.setCity("San Francisco");
		address.setState("CA");
		address.setZipcode("94024");
		address.setCountry("USA");
		department.setBillingAddress(address);
		assertEquals(address, department.getBillingAddress());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Department#getShippingAddress()}.
	 */
	@Test
	public void testGetShippingAddress() {
		Department department = new Department();
		Address address = new Address();
		address.setStreet("123 Main Street");
		address.setCity("San Francisco");
		address.setState("CA");
		address.setZipcode("94024");
		address.setCountry("USA");
		department.setShippingAddress(address);
		assertEquals(address, department.getShippingAddress());
	}

	/**
	 * Test method for
	 * {@link net.bhira.sample.model.Department#setShippingAddress(net.bhira.sample.model.Address)}.
	 */
	@Test
	public void testSetShippingAddress() {
		Department department = new Department();
		Address address = new Address();
		address.setStreet("123 Main Street");
		address.setCity("San Francisco");
		address.setState("CA");
		address.setZipcode("94024");
		address.setCountry("USA");
		department.setShippingAddress(address);
		assertEquals(address, department.getShippingAddress());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Department#getContactInfo()}.
	 */
	@Test
	public void testGetContactInfo() {
		Department department = new Department();
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setPhone("800-100-1234");
		contactInfo.setFax("800-100-1235");
		contactInfo.setEmail("xyz@xyz.com");
		contactInfo.setWebsite("http://bhira.net");
		department.setContactInfo(contactInfo);
		assertEquals(contactInfo, department.getContactInfo());
	}

	/**
	 * Test method for
	 * {@link net.bhira.sample.model.Department#setContactInfo(net.bhira.sample.model.ContactInfo)}.
	 */
	@Test
	public void testSetContactInfo() {
		Department department = new Department();
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setPhone("800-100-1234");
		contactInfo.setFax("800-100-1235");
		contactInfo.setEmail("xyz@xyz.com");
		contactInfo.setWebsite("http://bhira.net");
		department.setContactInfo(contactInfo);
		assertEquals(contactInfo, department.getContactInfo());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#isNew()}.
	 */
	@Test
	public void testIsNew() {
		Department department = new Department();
		assertTrue(department.isNew());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#initForSave()}.
	 */
	@Test
	public void testInitForSave() {
		Department department = new Department();
		department.setId(123);
		department.initForSave();
		assertNotNull(department.getModified());
		assertNotNull(department.getModifiedBy());
		assertNull(department.getCreated());
		assertNull(department.getCreatedBy());

		department = new Department();
		department.initForSave();
		assertNotNull(department.getModified());
		assertNotNull(department.getModifiedBy());
		assertNotNull(department.getCreated());
		assertNotNull(department.getCreatedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getId()}.
	 */
	@Test
	public void testGetId() {
		Department department = new Department();
		department.setId(123);
		assertEquals(123, department.getId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setId(long)}.
	 */
	@Test
	public void testSetId() {
		Department department = new Department();
		department.setId(123);
		assertEquals(123, department.getId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getName()}.
	 */
	@Test
	public void testGetName() {
		Department department = new Department();
		department.setName("HR Department");
		assertEquals("HR Department", department.getName());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		Department department = new Department();
		department.setName("HR Department");
		assertEquals("HR Department", department.getName());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getCreated()}.
	 */
	@Test
	public void testGetCreated() {
		Department department = new Department();
		Date now = new Date();
		department.setCreated(now);
		assertEquals(now, department.getCreated());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setCreated(java.new.Date)}.
	 */
	@Test
	public void testSetCreated() {
		Department department = new Department();
		Date now = new Date();
		department.setCreated(now);
		assertEquals(now, department.getCreated());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getModified()}.
	 */
	@Test
	public void testGetModified() {
		Department department = new Department();
		Date now = new Date();
		department.setModified(now);
		assertEquals(now, department.getModified());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setModified(java.util.Date)}.
	 */
	@Test
	public void testSetModified() {
		Department department = new Department();
		Date now = new Date();
		department.setModified(now);
		assertEquals(now, department.getModified());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getCreatedBy()}.
	 */
	@Test
	public void testGetCreatedBy() {
		Department department = new Department();
		department.setCreatedBy("User A");
		assertEquals("User A", department.getCreatedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public void testSetCreatedBy() {
		Department department = new Department();
		department.setCreatedBy("User A");
		assertEquals("User A", department.getCreatedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getModifiedBy()}.
	 */
	@Test
	public void testGetModifiedBy() {
		Department department = new Department();
		department.setModifiedBy("User A");
		assertEquals("User A", department.getModifiedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setModifiedBy(java.lang.String)}.
	 */
	@Test
	public void testSetModifiedBy() {
		Department department = new Department();
		department.setModifiedBy("User A");
		assertEquals("User A", department.getModifiedBy());
	}

}