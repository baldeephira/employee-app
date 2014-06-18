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
package net.bhira.sample.api.controller;

import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bhira.sample.api.service.CompanyService;
import net.bhira.sample.api.util.JsonUtil;
import net.bhira.sample.model.Company;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Controller class for company resource. It services all API requests for company resource.
 * 
 * @author Baldeep Hira
 */
@Controller("companyController")
public class CompanyController {

	/**
	 * private singleton instance of the SLF4J logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	CompanyService companyService;

	/**
	 * Fetch all the companies in the system. It will return a light weight version of
	 * {@link net.bhira.sample.model.Company} model without the address and contactInfo objects.
	 * 
	 * @param response
	 *            the http response to which the results will be written.
	 * @return an array of {@link net.bhira.sample.model.Company} instances as JSON.
	 */
	@RequestMapping(value = "/company", method = RequestMethod.GET)
	@ResponseBody
	public Callable<String> getAll(HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				String body = "";
				try {
					LOG.debug("servicing GET company/");
					List<Company> list = companyService.loadAll();
					int count = (list == null) ? 0 : list.size();
					LOG.debug("GET company/ count = {}", count);
					body = JsonUtil.createGson().toJson(list);
				} catch (Exception ex) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					body = ex.getLocalizedMessage();
					LOG.warn("Error loading companies. {}", body);
					LOG.debug("Load error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

	/**
	 * Fetch the instance of {@link net.bhira.sample.model.Company} represented by given companyId
	 * and return it as JSON object.
	 * 
	 * @param companyId
	 *            the ID for {@link net.bhira.sample.model.Company}.
	 * @param response
	 *            the http response to which the results will be written.
	 * @return an instance of {@link net.bhira.sample.model.Company} as JSON.
	 */
	@RequestMapping(value = "/company/{companyId}", method = RequestMethod.GET)
	@ResponseBody
	public Callable<String> getCompany(@PathVariable long companyId, HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				String body = "";
				try {
					LOG.debug("servicing GET company/{}", companyId);
					Company company = companyService.load(companyId);
					LOG.debug("GET company/{}, found = {}", companyId, company != null);
					if (company == null) {
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					} else {
						body = JsonUtil.createGson().toJson(company);
					}
				} catch (Exception ex) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					body = ex.getLocalizedMessage();
					LOG.warn("Error loading company/{}. {}", companyId, body);
					LOG.debug("Load error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

	/**
	 * Save the given instance of {@link net.bhira.sample.model.Company}. It will create a new
	 * instance of the company does not exist, otherwise it will update the existing instance.
	 * 
	 * @param request
	 *            the http request containing JSON payload in its body.
	 * @param response
	 *            the http response to which the results will be written.
	 * @return the error message, if save was not successful.
	 */
	@RequestMapping(value = "/company", method = RequestMethod.POST)
	@ResponseBody
	public Callable<String> saveCompany(HttpServletRequest request, HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				String body = "";
				try {
					LOG.debug("servicing POST company");
					Gson gson = JsonUtil.createGson();
					Company company = gson.fromJson(request.getReader(), Company.class);
					LOG.debug("POST company received json = {}", gson.toJson(company));
					company = companyService.save(company);
					LOG.debug("POST company/ successful with return ID = {}", company.getId());
				} catch (Exception ex) {
					if (ex instanceof JsonSyntaxException) {
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					} else {
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					}
					body = ex.getLocalizedMessage();
					LOG.warn("Error saving company. {}", body);
					LOG.debug("Save error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

	/**
	 * Delete the instance of {@link net.bhira.sample.model.Company} represented by given companyId.
	 * In case of an error return the error message.
	 * 
	 * @param companyId
	 *            the ID for {@link net.bhira.sample.model.Company}.
	 * @param response
	 *            the http response to which the results will be written.
	 * @return the error message, if save was not successful.
	 */
	@RequestMapping(value = "/company/{companyId}", method = RequestMethod.DELETE)
	@ResponseBody
	public Callable<String> deleteCompany(@PathVariable long companyId, HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				LOG.debug("servicing DELETE company/{}", companyId);
				String body = "";
				try {
					boolean success = companyService.delete(companyId);
					LOG.debug("DELETE company/{} status = {}", companyId, success);
					if (!success) {
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					}
				} catch (Exception ex) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					body = ex.getLocalizedMessage();
					LOG.warn("Error deleting company/{}. {}", companyId, body);
					LOG.debug("Delete error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

}