package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Cacheable(cacheNames = "employeeCache" ,key = "#id")
    public Employee getEmployee(Long id) {
        Optional<Employee> optEmp = employeeRepository.findById(id);
        return optEmp.get();
    }

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    @CacheEvict(cacheNames = "employeeCache" ,key = "#id")
    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    @CacheEvict(cacheNames = "employeeCache" ,key = "#employee.id")
    @CachePut(cacheNames = "employeeCache" ,key = "#employee.id")
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}