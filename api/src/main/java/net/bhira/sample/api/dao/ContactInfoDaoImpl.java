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

import net.bhira.sample.api.jdbc.ContactInfoRowMapper;
import net.bhira.sample.model.ContactInfo;

/**
 * Implementation for ContactInfo DAO. It implements CRUD operations for
 * {@link net.bhira.sample.model.ContactInfo}.
 * 
 * @author Baldeep Hira
 */
@Repository("contactInfoDao")
public class ContactInfoDaoImpl implements ContactInfoDao {

	/**
	 * private singleton instance of the SLF4J logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ContactInfoDaoImpl.class);

	private static final String SQL_LOAD_BY_ID = "select * from contactinfo where id = ?";
	private static final String SQL_INSERT = "insert into contactinfo (phone,fax,email,website) values (?,?,?,?)";
	private static final String SQL_UPDATE = "update contactinfo set phone = ?, fax = ?, email = ?, website = ? where id = ?";
	private static final String SQL_DELETE = "delete from contactinfo where id = ?";

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * @see net.bhira.sample.api.dao.ContactInfoDao#load(long)
	 */
	@Override
	public ContactInfo load(long contactInfoId) {
		List<ContactInfo> list = jdbcTemplate.query(SQL_LOAD_BY_ID, new Object[] { contactInfoId },
				new ContactInfoRowMapper());
		int count = (list == null) ? 0 : list.size();
		LOG.debug("loaded contactInfo, count = {}, id = {}", count, contactInfoId);
		return (count == 0) ? null : list.get(0);
	}

	/**
	 * @see net.bhira.sample.api.dao.ContactInfoDao#save(net.bhira.sample.model.ContactInfo)
	 */
	@Override
	public void save(ContactInfo contactInfo) {
		if (contactInfo == null) {
			return;
		}

		if (contactInfo.isNew()) {
			// for new address, use SQL insert statement

			KeyHolder keyHolder = new GeneratedKeyHolder();
			int count = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection connection)
						throws SQLException {
					PreparedStatement pstmt = connection.prepareStatement(SQL_INSERT,
							Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, contactInfo.getPhone());
					pstmt.setString(2, contactInfo.getFax());
					pstmt.setString(3, contactInfo.getEmail());
					pstmt.setString(4, contactInfo.getWebsite());
					return pstmt;
				}
			}, keyHolder);

			// fetch the newly created auto-increment ID
			contactInfo.setId(keyHolder.getKey().longValue());
			LOG.debug("inserted contactInfo, count = {}, id = {}", count, contactInfo.getId());

		} else {
			// for existing address, construct the SQL update statement
			int count = jdbcTemplate.update(SQL_UPDATE, new Object[] { contactInfo.getPhone(),
					contactInfo.getFax(), contactInfo.getEmail(), contactInfo.getWebsite(),
					contactInfo.getId() });
			LOG.debug("updated contactInfo, count = {}, id = {}", count, contactInfo.getId());
		}
	}

	/**
	 * @see net.bhira.sample.api.dao.ContactInfoDao#delete(long)
	 */
	@Override
	public boolean delete(long contactInfoId) {
		int count = jdbcTemplate.update(SQL_DELETE, new Object[] { contactInfoId });
		LOG.debug("deleted contactInfo, count = {}, id = {}", count, contactInfoId);
		return (count > 0);
	}

}