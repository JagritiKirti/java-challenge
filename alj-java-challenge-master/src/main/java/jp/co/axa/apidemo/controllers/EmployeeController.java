package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.model.EmployeeDetails;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/employees")
    public List<EmployeeDetails> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        List<EmployeeDetails> employeesResponse = employees.stream().map(
        		e -> new EmployeeDetails(e)).collect(Collectors.toList());
        return employeesResponse;
    }

    @GetMapping("/employees/{employeeId}")
    public EmployeeDetails getEmployee(@PathVariable(name="employeeId")Long employeeId) {    	
    	
        return new EmployeeDetails(employeeService.getEmployee(employeeId));
    }

    @PostMapping("/employees")
    public void saveEmployee(@RequestBody EmployeeDetails employeeDetails){
    	
        employeeService.saveEmployee(new Employee(employeeDetails));
        System.out.println("Employee Saved Successfully");
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
    }

    @PutMapping("/employees/{employeeId}")
    public void updateEmployee(@RequestBody EmployeeDetails employeeDetails,
                               @PathVariable(name="employeeId")Long employeeId){
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.updateEmployee(new Employee(employeeDetails));
        }

    }

}
