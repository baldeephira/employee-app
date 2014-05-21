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

import org.junit.Test;

/**
 * Test class for {@link net.bhira.sample.model.ContactInfo}.
 * It uses JUnit 4 for generating test cases.
 * 
 * @author Baldeep Hira
 */
public class ContactInfoTest {

	/**
	 * Test method for {@link net.bhira.sample.model.ContactInfo#isNew()}.
	 */
	@Test
	public void testIsNew() {
		ContactInfo contactInfo = new ContactInfo();
		assertTrue(contactInfo.isNew());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.ContactInfo#getId()}.
	 */
	@Test
	public void testGetId() {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setId(123);
		assertEquals(123, contactInfo.getId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.ContactInfo#setId(long)}.
	 */
	@Test
	public void testSetId() {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setId(123);
		assertEquals(123, contactInfo.getId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.ContactInfo#getPhone()}.
	 */
	@Test
	public void testGetPhone() {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setPhone("800-100-1000");
		assertEquals("800-100-1000", contactInfo.getPhone());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.ContactInfo#setPhone(java.lang.String)}.
	 */
	@Test
	public void testSetPhone() {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setPhone("800-100-1000");
		assertEquals("800-100-1000", contactInfo.getPhone());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.ContactInfo#getFax()}.
	 */
	@Test
	public void testGetFax() {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setFax("800-100-1000");
		assertEquals("800-100-1000", contactInfo.getFax());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.ContactInfo#setFax(java.lang.String)}.
	 */
	@Test
	public void testSetFax() {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setFax("800-100-1000");
		assertEquals("800-100-1000", contactInfo.getFax());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.ContactInfo#getEmail()}.
	 */
	@Test
	public void testGetEmail() {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setEmail("abc@xyz.com");
		assertEquals("abc@xyz.com", contactInfo.getEmail());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.ContactInfo#setEmail(java.lang.String)}.
	 */
	@Test
	public void testSetEmail() {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setEmail("abc@xyz.com");
		assertEquals("abc@xyz.com", contactInfo.getEmail());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.ContactInfo#getWebsite()}.
	 */
	@Test
	public void testGetWebsite() {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setWebsite("http://bhira.net");
		assertEquals("http://bhira.net", contactInfo.getWebsite());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.ContactInfo#setWebsite(java.lang.String)}.
	 */
	@Test
	public void testSetWebsite() {
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setWebsite("http://bhira.net");
		assertEquals("http://bhira.net", contactInfo.getWebsite());
	}

}