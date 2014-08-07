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

import org.springframework.jdbc.core.RowMapper;

import net.bhira.sample.model.Company;

/**
 * Helper class used to map a single row in jdbc ResultSet to {@link net.bhira.sample.model.Company}
 * object.
 * 
 * @author Baldeep Hira
 */
public class CompanyRowMapper implements RowMapper<Company> {

	/**
	 * Constructor for CompanyRowMapper that creates an instance of
	 * {@link net.bhira.sample.model.Company} from row represented by rowNum in the given ResultSet.
	 * 
	 * @param rs
	 *            an instance of ResultSet to be processed.
	 * @param rowNum
	 *            integer representing the row number in ResultSet.
	 */
	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		Company company = new Company();
		company.setId(rs.getLong("id"));
		company.setName(rs.getString("name"));
		company.setIndustry(rs.getString("industry"));
		company.setBillingAddress(rs.getString("billingaddr"));
		company.setShippingAddress(rs.getString("shippingaddr"));
		company.setCreated(rs.getTimestamp("created"));
		company.setModified(rs.getTimestamp("modified"));
		company.setCreatedBy(rs.getString("createdby"));
		company.setModifiedBy(rs.getString("modifiedby"));
		return company;
	}

}