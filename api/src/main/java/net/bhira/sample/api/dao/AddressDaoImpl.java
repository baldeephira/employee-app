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
package net.bhira.sample.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import net.bhira.sample.api.jdbc.AddressRowMapper;
import net.bhira.sample.model.Address;

/**
 * Implementation for Address DAO. It implements CRUD operations for
 * {@link net.bhira.sample.model.Address}.
 * 
 * @author Baldeep Hira
 */
@Repository("addressDao")
public class AddressDaoImpl implements AddressDao {

	/**
	 * private singleton instance of the SLF4J logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AddressDaoImpl.class);

	private static final String SQL_LOAD_BY_ID = "select * from address where id = ?";
	private static final String SQL_INSERT = "insert into address (street,city,state,zip,country) values (?,?,?,?,?)";
	private static final String SQL_UPDATE = "update address set street = ?, city = ?, state = ?, zip = ?, country = ? where id = ?";
	private static final String SQL_DELETE = "delete from address where id = ?";

	@Autowired
	DataSource dataSource;

	/**
	 * @see net.bhira.sample.api.dao.AddressDao#load(long)
	 */
	@Override
	public Address load(long addressId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Address> list = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { addressId },
				new AddressRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded address, count = {}, id = {}", count, addressId);
		return (count == 0) ? null : list.get(0);
	}

	/**
	 * @see net.bhira.sample.api.dao.AddressDao#save(net.bhira.sample.model.Address)
	 */
	@Override
	public Address save(Address address) {
		if (address == null) {
			return null;
		}

		boolean isNew = address.isNew();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		if (isNew) {
			// for new address, use SQL insert statement

			KeyHolder keyHolder = new GeneratedKeyHolder();
			int count = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT,
							Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, address.getStreet());
					pstmt.setString(2, address.getCity());
					pstmt.setString(3, address.getState());
					pstmt.setString(4, address.getZipcode());
					pstmt.setString(5, address.getCountry());
					return pstmt;
				}
			}, keyHolder);

			// fetch the newly created auto-increment ID
			address.setId(keyHolder.getKey().longValue());
			LOG.debug("inserted address, count = {},  id = {}", count, address.getId());

		} else {
			// for existing address, construct the SQL update statement
			int count = jdbcTemplate.update(SQL_UPDATE, new Object[] { address.getStreet(),
					address.getCity(), address.getState(), address.getZipcode(), address.getCountry(),
					address.getId() });
			LOG.debug("updated address, count = {},  id = {}", count, address.getId());
		}

		return address;
	}

	/**
	 * @see net.bhira.sample.api.dao.AddressDao#delete(long)
	 */
	@Override
	public boolean delete(long addressId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.update(SQL_DELETE, new Object[] { addressId });
		LOG.debug("deleted address, count = {}, id = {}", count, addressId);
		return (count > 0);
	}

}