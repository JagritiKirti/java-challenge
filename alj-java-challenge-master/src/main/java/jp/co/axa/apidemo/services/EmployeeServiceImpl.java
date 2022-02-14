package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> retrieveEmployees() {
    	LOG.info("Inside EmployeeServiceImpl - retrieveEmployees");
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Cacheable(cacheNames = "employeeCache" ,key = "#id")
    public Employee getEmployee(Long id) {
    	LOG.info("Inside EmployeeServiceImpl - getEmployee -- employeeId {} ", id);
        Optional<Employee> optEmp = employeeRepository.findById(id);
        return optEmp.get();
    }

    public void saveEmployee(Employee employee){
    	LOG.info("Inside EmployeeServiceImpl - saveEmployee");
        employeeRepository.save(employee);
    }

    @CacheEvict(cacheNames = "employeeCache" ,key = "#id")
    public void deleteEmployee(Long id){
    	LOG.info("Inside EmployeeServiceImpl - deleteEmployee -- employeeId {}", id);
        employeeRepository.deleteById(id);
    }

    @CacheEvict(cacheNames = "employeeCache" ,key = "#employee.id")
    @CachePut(cacheNames = "employeeCache" ,key = "#employee.id")
    public void updateEmployee(Employee employee) {
    	LOG.info("Inside EmployeeServiceImpl - saveEmployee");
        employeeRepository.save(employee);
    }
}