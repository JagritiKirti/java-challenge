package jp.co.axa.apidemo.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.model.EmployeeDetails;
import jp.co.axa.apidemo.services.EmployeeService;

@SpringBootTest
public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getEmployeesTest() throws Exception {
		List<Employee> employees = new ArrayList<>();
		Employee empOne = new Employee(1L, "Bob", 1000000.0, "IT");
		Employee empTwo = new Employee(2L, "Adam", 2000000.0, "HR");
		employees.add(empOne);
		employees.add(empTwo);

		when(employeeService.retrieveEmployees()).thenReturn(employees);
		ResponseEntity<List<EmployeeDetails>> response =  employeeController.getEmployees();
		assertNotNull(response);
		verify(employeeService, times(1)).retrieveEmployees();
	}
	
	@Test
	public void getEmployeeTest() {
		Employee employee = new Employee(1L, "Bob", 1000000.0, "IT");
		
		when(employeeService.getEmployee(1L)).thenReturn(employee);
		ResponseEntity<EmployeeDetails> response = employeeController.getEmployee(1L);
		assertNotNull(response);
		verify(employeeService, times(1)).getEmployee(1L);
	}
	
	@Test
	public void saveEmployeeTest() throws ParseException {
		
		Employee employee = new Employee(1L, "Ron", 1000000.0, "IT");
		EmployeeDetails employeeDetails = new EmployeeDetails(employee);
		ResponseEntity<String> response = employeeController.saveEmployee(employeeDetails);
		assertEquals(response.getBody(), "Employee Saved Successfully");
	}
	
	@Test
	public void deleteEmployeeTest() {
		ResponseEntity<String> response = employeeController.deleteEmployee(1L);
		assertEquals(response.getBody(), "Employee Deleted Successfully");
		verify(employeeService, times(1)).deleteEmployee(1L);
	}
	
	@Test
	public void updateEmployeeTest() throws ParseException {
		
		EmployeeDetails employeeDetails = new EmployeeDetails();
		employeeDetails.setId(1L);
		employeeDetails.setName("Adam");
		employeeDetails.setSalary(1000000.0);
		employeeDetails.setDepartment("IT");
		Employee employee = new Employee(employeeDetails);
		
		when(employeeService.getEmployee(1L)).thenReturn(employee);	
		ResponseEntity<String> response = employeeController.updateEmployee(employeeDetails, 1L);		
		assertEquals(response.getBody(), "Employee record updated successfully");		
	}
}
