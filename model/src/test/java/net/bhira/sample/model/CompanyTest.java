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
 * Test class for {@link net.bhira.sample.model.Company}. It uses JUnit 4 for generating test cases.
 * 
 * @author Baldeep Hira
 */
public class CompanyTest {

	/**
	 * Test method for {@link net.bhira.sample.model.Company#Company()}.
	 */
	@Test
	public void testCompany() {
		Company company = new Company();
		assertTrue(company.isNew());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Company#getIndustry()}.
	 */
	@Test
	public void testGetIndustry() {
		Company company = new Company();
		company.setIndustry("Computers/Software");
		assertEquals("Computers/Software", company.getIndustry());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Company#setIndustry(java.lang.String)}.
	 */
	@Test
	public void testSetIndustry() {
		Company company = new Company();
		company.setIndustry("Computers/Software");
		assertEquals("Computers/Software", company.getIndustry());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Company#getBillingAddress()}.
	 */
	@Test
	public void testGetBillingAddress() {
		Company company = new Company();
		company.setBillingAddress("123 Main Street, San Francisco, CA 94024, USA");
		assertEquals("123 Main Street, San Francisco, CA 94024, USA", company.getBillingAddress());
	}

	/**
	 * Test method for
	 * {@link net.bhira.sample.model.Company#setBillingAddress(net.bhira.sample.model.Address)}.
	 */
	@Test
	public void testSetBillingAddress() {
		Company company = new Company();
		company.setBillingAddress("123 Main Street, San Francisco, CA 94024, USA");
		assertEquals("123 Main Street, San Francisco, CA 94024, USA", company.getBillingAddress());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Company#getShippingAddress()}.
	 */
	@Test
	public void testGetShippingAddress() {
		Company company = new Company();
		company.setShippingAddress("123 Main Street, San Francisco, CA 94024, USA");
		assertEquals("123 Main Street, San Francisco, CA 94024, USA", company.getShippingAddress());
	}

	/**
	 * Test method for
	 * {@link net.bhira.sample.model.Company#setShippingAddress(net.bhira.sample.model.Address)}.
	 */
	@Test
	public void testSetShippingAddress() {
		Company company = new Company();
		company.setShippingAddress("123 Main Street, San Francisco, CA 94024, USA");
		assertEquals("123 Main Street, San Francisco, CA 94024, USA", company.getShippingAddress());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Company#getContactInfo()}.
	 */
	@Test
	public void testGetContactInfo() {
		Company company = new Company();
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setPhone("800-100-1234");
		contactInfo.setFax("800-100-1235");
		contactInfo.setEmail("xyz@xyz.com");
		contactInfo.setWebsite("http://bhira.net");
		company.setContactInfo(contactInfo);
		assertEquals(contactInfo, company.getContactInfo());
	}

	/**
	 * Test method for
	 * {@link net.bhira.sample.model.Company#setContactInfo(net.bhira.sample.model.ContactInfo)}.
	 */
	@Test
	public void testSetContactInfo() {
		Company company = new Company();
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setPhone("800-100-1234");
		contactInfo.setFax("800-100-1235");
		contactInfo.setEmail("xyz@xyz.com");
		contactInfo.setWebsite("http://bhira.net");
		company.setContactInfo(contactInfo);
		assertEquals(contactInfo, company.getContactInfo());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#isNew()}.
	 */
	@Test
	public void testIsNew() {
		Company company = new Company();
		assertTrue(company.isNew());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#initForSave()}.
	 */
	@Test
	public void testInitForSave() {
		Company company = new Company();
		company.setId(123);
		company.initForSave();
		assertNotNull(company.getModified());
		assertNotNull(company.getModifiedBy());
		assertNull(company.getCreated());
		assertNull(company.getCreatedBy());

		company = new Company();
		company.initForSave();
		assertNotNull(company.getModified());
		assertNotNull(company.getModifiedBy());
		assertNotNull(company.getCreated());
		assertNotNull(company.getCreatedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getId()}.
	 */
	@Test
	public void testGetId() {
		Company company = new Company();
		company.setId(123);
		assertEquals(123, company.getId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setId(long)}.
	 */
	@Test
	public void testSetId() {
		Company company = new Company();
		company.setId(123);
		assertEquals(123, company.getId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getName()}.
	 */
	@Test
	public void testGetName() {
		Company company = new Company();
		company.setName("ABC Inc.");
		assertEquals("ABC Inc.", company.getName());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		Company company = new Company();
		company.setName("ABC Inc.");
		assertEquals("ABC Inc.", company.getName());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getCreated()}.
	 */
	@Test
	public void testGetCreated() {
		Company company = new Company();
		Date now = new Date();
		company.setCreated(now);
		assertEquals(now, company.getCreated());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setCreated(java.new.Date)}.
	 */
	@Test
	public void testSetCreated() {
		Company company = new Company();
		Date now = new Date();
		company.setCreated(now);
		assertEquals(now, company.getCreated());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getModified()}.
	 */
	@Test
	public void testGetModified() {
		Company company = new Company();
		Date now = new Date();
		company.setModified(now);
		assertEquals(now, company.getModified());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setModified(java.util.Date)}.
	 */
	@Test
	public void testSetModified() {
		Company company = new Company();
		Date now = new Date();
		company.setModified(now);
		assertEquals(now, company.getModified());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getCreatedBy()}.
	 */
	@Test
	public void testGetCreatedBy() {
		Company company = new Company();
		company.setCreatedBy("User A");
		assertEquals("User A", company.getCreatedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public void testSetCreatedBy() {
		Company company = new Company();
		company.setCreatedBy("User A");
		assertEquals("User A", company.getCreatedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getModifiedBy()}.
	 */
	@Test
	public void testGetModifiedBy() {
		Company company = new Company();
		company.setModifiedBy("User A");
		assertEquals("User A", company.getModifiedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setModifiedBy(java.lang.String)}.
	 */
	@Test
	public void testSetModifiedBy() {
		Company company = new Company();
		company.setModifiedBy("User A");
		assertEquals("User A", company.getModifiedBy());
	}

}