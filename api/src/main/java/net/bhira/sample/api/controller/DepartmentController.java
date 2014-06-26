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

import net.bhira.sample.api.service.DepartmentService;
import net.bhira.sample.common.JsonUtil;
import net.bhira.sample.model.Department;

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
 * Controller class for department resource. It services all API requests for department resource.
 * 
 * @author Baldeep Hira
 */
@Controller("departmentController")
public class DepartmentController {

	/**
	 * private singleton instance of the SLF4J logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DepartmentController.class);

	@Autowired
	DepartmentService departmentService;

	/**
	 * Fetch all the departments for the given company ID. It will return a light weight version of
	 * {@link net.bhira.sample.model.Department} model without the address and contactInfo objects.
	 * 
	 * @param companyId
	 *            the ID for {@link net.bhira.sample.model.Company}.
	 * @param response
	 *            the http response to which the results will be written.
	 * @return an array of {@link net.bhira.sample.model.Department} instances as JSON.
	 */
	@RequestMapping(value = "/department/company/{companyId}", method = RequestMethod.GET)
	@ResponseBody
	public Callable<String> getDepartmentsByCompany(@PathVariable long companyId, HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				String body = "";
				try {
					LOG.debug("servicing GET department/company/{}", companyId);
					List<Department> list = departmentService.loadByCompany(companyId);
					int count = (list == null) ? 0 : list.size();
					LOG.debug("GET department/company/{} count = {}", companyId, count);
					body = JsonUtil.createGson().toJson(list);
				} catch (Exception ex) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					body = ex.getLocalizedMessage();
					LOG.warn("Error loading department/company/{}. {}", companyId, body);
					LOG.debug("Load error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

	/**
	 * Fetch the instance of {@link net.bhira.sample.model.Department} represented by given
	 * departmentId and return it as JSON object.
	 * 
	 * @param departmentId
	 *            the ID for {@link net.bhira.sample.model.Department}.
	 * @param response
	 *            the http response to which the results will be written.
	 * @return an instance of {@link net.bhira.sample.model.Department} as JSON.
	 */
	@RequestMapping(value = "/department/{departmentId}", method = RequestMethod.GET)
	@ResponseBody
	public Callable<String> getDepartment(@PathVariable long departmentId, HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				String body = "";
				try {
					LOG.debug("servicing GET department/{}", departmentId);
					Department department = departmentService.load(departmentId);
					LOG.debug("GET department/{}, found = {}", departmentId, department != null);
					if (department == null) {
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					} else {
						body = JsonUtil.createGson().toJson(department);
					}
				} catch (Exception ex) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					body = ex.getLocalizedMessage();
					LOG.warn("Error loading department/{}. {}", departmentId, body);
					LOG.debug("Load error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

	/**
	 * Save the given instance of {@link net.bhira.sample.model.Department}. It will create a new
	 * instance of the department does not exist, otherwise it will update the existing instance.
	 * 
	 * @param request
	 *            the http request containing JSON payload in its body.
	 * @param response
	 *            the http response to which the results will be written.
	 * @return the error message, if save was not successful.
	 */
	@RequestMapping(value = "/department", method = RequestMethod.POST)
	@ResponseBody
	public Callable<String> saveDepartment(HttpServletRequest request, HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				String body = "";
				try {
					LOG.debug("servicing POST department");
					Gson gson = JsonUtil.createGson();
					Department department = gson.fromJson(request.getReader(), Department.class);
					LOG.debug("POST department received json = {}", gson.toJson(department));
					departmentService.save(department);
					LOG.debug("POST department/ successful with return ID = {}", department.getId());
				} catch (Exception ex) {
					if (ex instanceof JsonSyntaxException) {
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					} else {
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					}
					body = ex.getLocalizedMessage();
					LOG.warn("Error saving department. {}", body);
					LOG.debug("Save error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

	/**
	 * Delete the instance of {@link net.bhira.sample.model.Department} represented by given
	 * departmentId. In case of an error return the error message.
	 * 
	 * @param departmentId
	 *            the ID for {@link net.bhira.sample.model.Department}.
	 * @param response
	 *            the http response to which the results will be written.
	 * @return the error message, if save was not successful.
	 */
	@RequestMapping(value = "/department/{departmentId}", method = RequestMethod.DELETE)
	@ResponseBody
	public Callable<String> deleteDepartment(@PathVariable long departmentId, HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				LOG.debug("servicing DELETE department/{}", departmentId);
				String body = "";
				try {
					boolean success = departmentService.delete(departmentId);
					LOG.debug("DELETE department/{} status = {}", departmentId, success);
					if (!success) {
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					}
				} catch (Exception ex) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					body = ex.getLocalizedMessage();
					LOG.warn("Error deleting department/{}. {}", departmentId, body);
					LOG.debug("Delete error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

}