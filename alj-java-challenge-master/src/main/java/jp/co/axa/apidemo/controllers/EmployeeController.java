package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.model.EmployeeDetails;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "This method is used to fetch the list of employees")
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDetails>> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        List<EmployeeDetails> employeesResponse = employees.stream().map(
        		e -> new EmployeeDetails(e)).collect(Collectors.toList());
        return ResponseEntity.ok().body(employeesResponse);
    }

    @ApiOperation(value = "This method is used to fetch the employee data for given employee ID")
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDetails> getEmployee(@PathVariable(name="employeeId")Long employeeId) {    	
    	EmployeeDetails employee = new EmployeeDetails(employeeService.getEmployee(employeeId));
        return ResponseEntity.ok().body(employee);      
    }

    @ApiOperation(value = "This method is used to save employee record")
    @PostMapping("/employees")
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeDetails employeeDetails){
    	
        employeeService.saveEmployee(new Employee(employeeDetails));
        return ResponseEntity.ok().body("Employee Saved Successfully");
    }

    @ApiOperation(value = "This method is used to delete the employee record based on employee ID")
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String>  deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok().body("Employee Deleted Successfully");
    }

    @ApiOperation(value = "This method is used to employee record")
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDetails employeeDetails,
                               @PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.updateEmployee(new Employee(employeeDetails));
            return ResponseEntity.ok().body("Employee record updated successfully");
        }      
        return ResponseEntity.badRequest().body("No employee record found");
    }
}
