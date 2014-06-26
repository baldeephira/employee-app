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

import net.bhira.sample.api.service.EmployeeService;
import net.bhira.sample.common.JsonUtil;
import net.bhira.sample.model.Employee;

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
 * Controller class for employee resource. It services all API requests for employee resource.
 * 
 * @author Baldeep Hira
 */
@Controller("employeeController")
public class EmployeeController {

	/**
	 * private singleton instance of the SLF4J logger for this class.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeService employeeService;

	/**
	 * Fetch all the employees for the given company ID. It will return a light weight version of
	 * {@link net.bhira.sample.model.Employee} model without the address and contactInfo objects.
	 * 
	 * @param companyId
	 *            the ID for {@link net.bhira.sample.model.Company}.
	 * @param response
	 *            the http response to which the results will be written.
	 * @return an array of {@link net.bhira.sample.model.Employee} instances as JSON.
	 */
	@RequestMapping(value = "/employee/company/{companyId}", method = RequestMethod.GET)
	@ResponseBody
	public Callable<String> getEmployeesByCompany(@PathVariable long companyId, HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				String body = "";
				try {
					LOG.debug("servicing GET employee/company/{}", companyId);
					List<Employee> list = employeeService.loadByCompany(companyId);
					int count = (list == null) ? 0 : list.size();
					LOG.debug("GET employee/company/{} count = {}", companyId, count);
					body = JsonUtil.createGson().toJson(list);
				} catch (Exception ex) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					body = ex.getLocalizedMessage();
					LOG.warn("Error loading employee/company/{}. {}", companyId, body);
					LOG.debug("Load error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

	/**
	 * Fetch all the employees for the given department ID. It will return a light weight version of
	 * {@link net.bhira.sample.model.Employee} model without the address and contactInfo objects.
	 * 
	 * @param departmentId
	 *            the ID for {@link net.bhira.sample.model.Department}.
	 * @param response
	 *            the http response to which the results will be written.
	 * @return an array of {@link net.bhira.sample.model.Employee} instances as JSON.
	 */
	@RequestMapping(value = "/employee/department/{departmentId}", method = RequestMethod.GET)
	@ResponseBody
	public Callable<String> getEmployeesByDepartment(@PathVariable long departmentId,
			HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				String body = "";
				try {
					LOG.debug("servicing GET employee/department/{}", departmentId);
					List<Employee> list = employeeService.loadByDepartment(departmentId);
					int count = (list == null) ? 0 : list.size();
					LOG.debug("GET employee/department/{} count = {}", departmentId, count);
					body = JsonUtil.createGson().toJson(list);
				} catch (Exception ex) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					body = ex.getLocalizedMessage();
					LOG.warn("Error loading employee/department/{}. {}", departmentId, body);
					LOG.debug("Load error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

	/**
	 * Fetch the instance of {@link net.bhira.sample.model.Employee} represented by given employeeId
	 * and return it as JSON object.
	 * 
	 * @param employeeId
	 *            the ID for {@link net.bhira.sample.model.Employee}.
	 * @param response
	 *            the http response to which the results will be written.
	 * @return an instance of {@link net.bhira.sample.model.Employee} as JSON.
	 */
	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.GET)
	@ResponseBody
	public Callable<String> getEmployee(@PathVariable long employeeId, HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				String body = "";
				try {
					LOG.debug("servicing GET employee/{}", employeeId);
					Employee employee = employeeService.load(employeeId);
					LOG.debug("GET employee/{}, found = {}", employeeId, employee != null);
					if (employee == null) {
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					} else {
						body = JsonUtil.createGson().toJson(employee);
					}
				} catch (Exception ex) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					body = ex.getLocalizedMessage();
					LOG.warn("Error loading employee/{}. {}", employeeId, body);
					LOG.debug("Load error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

	/**
	 * Save the given instance of {@link net.bhira.sample.model.Employee}. It will create a new
	 * instance of the employee does not exist, otherwise it will update the existing instance.
	 * 
	 * @param request
	 *            the http request containing JSON payload in its body.
	 * @param response
	 *            the http response to which the results will be written.
	 * @return the error message, if save was not successful.
	 */
	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	@ResponseBody
	public Callable<String> saveEmployee(HttpServletRequest request, HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				String body = "";
				try {
					LOG.debug("servicing POST employee");
					Gson gson = JsonUtil.createGson();
					Employee employee = gson.fromJson(request.getReader(), Employee.class);
					LOG.debug("POST employee received json = {}", gson.toJson(employee));
					employeeService.save(employee);
					LOG.debug("POST employee/ successful with return ID = {}", employee.getId());
				} catch (Exception ex) {
					if (ex instanceof JsonSyntaxException) {
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					} else {
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					}
					body = ex.getLocalizedMessage();
					LOG.warn("Error saving employee. {}", body);
					LOG.debug("Save error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

	/**
	 * Delete the instance of {@link net.bhira.sample.model.Employee} represented by given
	 * employeeId. In case of an error return the error message.
	 * 
	 * @param employeeId
	 *            the ID for {@link net.bhira.sample.model.Employee}.
	 * @param response
	 *            the http response to which the results will be written.
	 * @return the error message, if save was not successful.
	 */
	@RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.DELETE)
	@ResponseBody
	public Callable<String> deleteEmployee(@PathVariable long employeeId, HttpServletResponse response) {
		return new Callable<String>() {
			public String call() throws Exception {
				LOG.debug("servicing DELETE employee/{}", employeeId);
				String body = "";
				try {
					boolean success = employeeService.delete(employeeId);
					LOG.debug("DELETE employee/{} status = {}", employeeId, success);
					if (!success) {
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					}
				} catch (Exception ex) {
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					body = ex.getLocalizedMessage();
					LOG.warn("Error deleting employee/{}. {}", employeeId, body);
					LOG.debug("Delete error stacktrace: ", ex);
				}
				return body;
			}
		};
	}

}