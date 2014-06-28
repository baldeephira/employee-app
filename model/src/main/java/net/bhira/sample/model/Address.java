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

import net.bhira.sample.common.exception.InvalidObjectException;

/**
 * Generic address class that is used to track any kind of address. It is a second class entity and
 * is always contained within another entity like Company, Department or Employee.
 * 
 * @author Baldeep Hira
 */
public class Address implements Serializable {

	private static final long serialVersionUID = 20140520L;

	private long id;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	private String country;

	// -------------------------UTILITY METHODS----------------------------

	/**
	 * Check this model is a new instance.
	 * 
	 * @return true if the model is a new instance.
	 */
	public boolean isNew() {
		return (id == 0);
	}

	/**
	 * Check if this instance of Address is valid. It throws and exception with detailed message in
	 * case of instance is invalid.
	 * 
	 * @throws InvalidObjectException
	 *             if instance is invalid.
	 */
	public void validate() throws InvalidObjectException {
		// all fields are optional in this class
		// so validate never throws exception.
	}

	// -------------------------GETTERS AND SETTERS-------------------------

	/**
	 * Get the ID for the model.
	 * 
	 * @return the ID of the model.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Set the ID for the model.
	 * 
	 * @param id
	 *            the ID of the model.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Get the street name.
	 * 
	 * @return the name of the street.
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * Set the street name.
	 * 
	 * @param street
	 *            the name of the street.
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Get the city name.
	 * 
	 * @return the name of the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Set the city name.
	 * 
	 * @param city
	 *            the name of the city.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Get the name of the state or province.
	 * 
	 * @return the name of the state or province.
	 */
	public String getState() {
		return state;
	}

	/**
	 * Set the name of the state or province.
	 * 
	 * @param state
	 *            the name of the state or province.
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Get the zip or postal code.
	 * 
	 * @return the zip or postal code.
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * Set the zip or postal code.
	 * 
	 * @param zipcode
	 *            the zip or postal code.
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * Get the country name.
	 * 
	 * @return the name of the country.
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Set the country name.
	 * 
	 * @param country
	 *            the name of the country.
	 */
	public void setCountry(String country) {
		this.country = country;
	}

}