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
package net.bhira.sample.api.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.bhira.sample.model.ContactInfo;

import org.springframework.jdbc.core.RowMapper;

/**
 * Helper class used to map a single row in jdbc ResultSet to
 * {@link net.bhira.sample.model.ContactInfo} object. 
 * 
 * @author Baldeep Hira
 */
public class ContactInfoRowMapper implements RowMapper<ContactInfo> {

	/**
	 * Constructor for ContactInfoRowMapper that creates an instance of
	 * {@link net.bhira.sample.model.ContactInfo} from row represented
	 * by rowNum in the given ResultSet. 
	 * @param rs an instance of ResultSet to be processed.
	 * @param rowNum integer representing the row number in ResultSet.
	 */
	@Override
    public ContactInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
    	ContactInfo contactInfo = new ContactInfo();
    	contactInfo.setId(rs.getLong("id"));
    	contactInfo.setPhone(rs.getString("phone"));
    	contactInfo.setFax(rs.getString("fax"));
    	contactInfo.setEmail(rs.getString("email"));
    	contactInfo.setWebsite(rs.getString("website"));
        return contactInfo;
    }

}