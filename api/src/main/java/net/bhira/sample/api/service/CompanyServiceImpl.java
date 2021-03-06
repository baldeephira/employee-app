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
package net.bhira.sample.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bhira.sample.api.dao.CompanyDao;
import net.bhira.sample.common.exception.InvalidObjectException;
import net.bhira.sample.common.exception.InvalidReferenceException;
import net.bhira.sample.common.exception.ObjectNotFoundException;
import net.bhira.sample.model.Company;

/**
 * Implementation of {@link net.bhira.sample.api.service.CompanyService}. It provides CRUD
 * operations for {@link net.bhira.sample.model.Company}.
 * 
 * @author Baldeep Hira
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDao companyDao;

	/**
	 * @see net.bhira.sample.api.service.CompanyService#load(long)
	 */
	@Override
	public Company load(long companyId) {
		return companyDao.load(companyId);
	}

	/**
	 * @see net.bhira.sample.api.service.CompanyService#save(net.bhira.sample.model.Company)
	 */
	@Override
	public void save(Company company) throws ObjectNotFoundException, InvalidObjectException,
			InvalidReferenceException {
		companyDao.save(company);
	}

	/**
	 * @see net.bhira.sample.api.service.CompanyService#delete(long)
	 */
	@Override
	public boolean delete(long companyId) {
		return companyDao.delete(companyId);
	}

	/**
	 * @see net.bhira.sample.api.service.CompanyService#loadAll()
	 */
	@Override
	public List<Company> loadAll() {
		return companyDao.loadAll();
	}

}