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
 * Test class for {@link net.bhira.sample.model.Address}.
 * It uses JUnit 4 for generating test cases.
 * 
 * @author Baldeep Hira
 */
public class AddressTest {

	/**
	 * Test method for {@link net.bhira.sample.model.Address#isNew()}.
	 */
	@Test
	public void testIsNew() {
		Address address = new Address();
		assertTrue(address.isNew());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Address#getId()}.
	 */
	@Test
	public void testGetId() {
		Address address = new Address();
		address.setId(12345);
		assertEquals(12345, address.getId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Address#setId(long)}.
	 */
	@Test
	public void testSetId() {
		Address address = new Address();
		address.setId(987);
		assertEquals(987, address.getId());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Address#getStreet()}.
	 */
	@Test
	public void testGetStreet() {
		Address address = new Address();
		address.setStreet("123 Main Street");
		assertEquals("123 Main Street", address.getStreet());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Address#setStreet(java.lang.String)}.
	 */
	@Test
	public void testSetStreet() {
		Address address = new Address();
		address.setStreet("123 Main Street");
		assertEquals("123 Main Street", address.getStreet());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Address#getCity()}.
	 */
	@Test
	public void testGetCity() {
		Address address = new Address();
		address.setCity("San Francisco");
		assertEquals("San Francisco", address.getCity());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Address#setCity(java.lang.String)}.
	 */
	@Test
	public void testSetCity() {
		Address address = new Address();
		address.setCity("San Francisco");
		assertEquals("San Francisco", address.getCity());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Address#getState()}.
	 */
	@Test
	public void testGetState() {
		Address address = new Address();
		address.setState("CA");
		assertEquals("CA", address.getState());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Address#setState(java.lang.String)}.
	 */
	@Test
	public void testSetState() {
		Address address = new Address();
		address.setState("CA");
		assertEquals("CA", address.getState());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Address#getZipcode()}.
	 */
	@Test
	public void testGetZipcode() {
		Address address = new Address();
		address.setZipcode("94024");
		assertEquals("94024", address.getZipcode());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Address#setZipcode(java.lang.String)}.
	 */
	@Test
	public void testSetZipcode() {
		Address address = new Address();
		address.setZipcode("94024");
		assertEquals("94024", address.getZipcode());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Address#getCountry()}.
	 */
	@Test
	public void testGetCountry() {
		Address address = new Address();
		address.setCountry("USA");
		assertEquals("USA", address.getCountry());
	}

	/**
	 * Test method for {@link net.bhira.sample.model.Address#setCountry(java.lang.String)}.
	 */
	@Test
	public void testSetCountry() {
		Address address = new Address();
		address.setCountry("USA");
		assertEquals("USA", address.getCountry());
	}

}