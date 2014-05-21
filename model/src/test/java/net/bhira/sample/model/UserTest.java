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

import java.time.LocalDateTime;

import org.junit.Test;

/**
 * Test class for {@link net.bhira.sample.model.User}.
 * It uses JUnit 4 for generating test cases.
 * 
 * @author Baldeep Hira
 */
public class UserTest {

	/**
	 * Test method for {@link net.bhira.sample.model.User#User()}.
	 */
	@Test
	public void testUser() {
		User user = new User();
		assertTrue(user.isNew());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.User#getCompanyId()}.
	 */
	@Test
	public void testGetCompanyId() {
		User user = new User();
		user.setCompanyId(1234);
		assertEquals(1234, user.getCompanyId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.User#setCompanyId(long)}.
	 */
	@Test
	public void testSetCompanyId() {
		User user = new User();
		user.setCompanyId(1234);
		assertEquals(1234, user.getCompanyId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.User#getPassword()}.
	 */
	@Test
	public void testGetPassword() {
		User user = new User();
		user.setPassword("password");
		assertEquals("password", user.getPassword());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.User#setPassword(java.lang.String)}.
	 */
	@Test
	public void testSetPassword() {
		User user = new User();
		user.setPassword("password");
		assertEquals("password", user.getPassword());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.User#getFirstName()}.
	 */
	@Test
	public void testGetFirstName() {
		User user = new User();
		user.setFirstName("Baldeep");
		assertEquals("Baldeep", user.getFirstName());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.User#setFirstName(java.lang.String)}.
	 */
	@Test
	public void testSetFirstName() {
		User user = new User();
		user.setFirstName("Baldeep");
		assertEquals("Baldeep", user.getFirstName());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.User#getLastName()}.
	 */
	@Test
	public void testGetLastName() {
		User user = new User();
		user.setLastName("Hira");
		assertEquals("Hira", user.getLastName());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.User#setLastName(java.lang.String)}.
	 */
	@Test
	public void testSetLastName() {
		User user = new User();
		user.setLastName("Hira");
		assertEquals("Hira", user.getLastName());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.User#getEmail()}.
	 */
	@Test
	public void testGetEmail() {
		User user = new User();
		user.setEmail("abc@xyz.com");
		assertEquals("abc@xyz.com", user.getEmail());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.User#setEmail(java.lang.String)}.
	 */
	@Test
	public void testSetEmail() {
		User user = new User();
		user.setEmail("abc@xyz.com");
		assertEquals("abc@xyz.com", user.getEmail());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.User#getTimeZoneId()}.
	 */
	@Test
	public void testGetTimeZoneId() {
		User user = new User();
		user.setTimeZoneId("America/Los_Angeles");
		assertEquals("America/Los_Angeles", user.getTimeZoneId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.User#setTimezoneId(java.lang.String)}.
	 */
	@Test
	public void testSetTimezoneId() {
		User user = new User();
		user.setTimeZoneId("America/Los_Angeles");
		assertEquals("America/Los_Angeles", user.getTimeZoneId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#isNew()}.
	 */
	@Test
	public void testIsNew() {
		User user = new User();
		assertTrue(user.isNew());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#initModified()}.
	 */
	@Test
	public void testInitModified() {
		User user = new User();
		LocalDateTime before = LocalDateTime.now();
		user.initModified();
		LocalDateTime after = LocalDateTime.now();
		assertNotNull(user.getModified());
		assertNull(user.getCreated());
		assert(before.compareTo(user.getModified()) <= 0);
		assert(after.compareTo(user.getModified()) >= 0);
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#initCreatedModified()}.
	 */
	@Test
	public void testInitCreatedModified() {
		User user = new User();
		LocalDateTime before = LocalDateTime.now();
		user.initCreatedModified();
		LocalDateTime after = LocalDateTime.now();
		assertNotNull(user.getModified());
		assertNotNull(user.getCreated());
		assertEquals(user.getModified(), user.getCreated());
		assert(before.compareTo(user.getModified()) <= 0);
		assert(after.compareTo(user.getModified()) >= 0);
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getId()}.
	 */
	@Test
	public void testGetId() {
		User user = new User();
		user.setId(123);
		assertEquals(123, user.getId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setId(long)}.
	 */
	@Test
	public void testSetId() {
		User user = new User();
		user.setId(123);
		assertEquals(123, user.getId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getName()}.
	 */
	@Test
	public void testGetName() {
		User user = new User();
		user.setName("baldeephira");
		assertEquals("baldeephira", user.getName());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		User user = new User();
		user.setName("baldeephira");
		assertEquals("baldeephira", user.getName());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getCreated()}.
	 */
	@Test
	public void testGetCreated() {
		User user = new User();
		LocalDateTime now = LocalDateTime.now();
		user.setCreated(now);
		assertEquals(now, user.getCreated());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setCreated(java.time.LocalDateTime)}.
	 */
	@Test
	public void testSetCreated() {
		User user = new User();
		LocalDateTime now = LocalDateTime.now();
		user.setCreated(now);
		assertEquals(now, user.getCreated());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getModified()}.
	 */
	@Test
	public void testGetModified() {
		User user = new User();
		LocalDateTime now = LocalDateTime.now();
		user.setModified(now);
		assertEquals(now, user.getModified());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setModified(java.time.LocalDateTime)}.
	 */
	@Test
	public void testSetModified() {
		User user = new User();
		LocalDateTime now = LocalDateTime.now();
		user.setModified(now);
		assertEquals(now, user.getModified());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getCreatedBy()}.
	 */
	@Test
	public void testGetCreatedBy() {
		User user = new User();
		user.setCreatedBy("Baldeep Hira");
		assertEquals("Baldeep Hira", user.getCreatedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public void testSetCreatedBy() {
		User user = new User();
		user.setCreatedBy("Baldeep Hira");
		assertEquals("Baldeep Hira", user.getCreatedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#getModifiedBy()}.
	 */
	@Test
	public void testGetModifiedBy() {
		User user = new User();
		user.setModifiedBy("Baldeep Hira");
		assertEquals("Baldeep Hira", user.getModifiedBy());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.BaseModel#setModifiedBy(java.lang.String)}.
	 */
	@Test
	public void testSetModifiedBy() {
		User user = new User();
		user.setModifiedBy("Baldeep Hira");
		assertEquals("Baldeep Hira", user.getModifiedBy());
	}

}