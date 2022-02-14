package jp.co.axa.apidemo.model;

import jp.co.axa.apidemo.entities.Employee;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class EmployeeDetails {
	
	//Default Constructor
	public EmployeeDetails() {
		
	}
	
	public EmployeeDetails (Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.salary = employee.getSalary();
		this.department = employee.getDepartment();
	}

	public EmployeeDetails(Long id, String name, Double salary, String department) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.department = department;
	}
	
	@Getter
	@Setter
	private Long id;

	@Getter
	@Setter
	private String name;

	@Getter
	@Setter
	private Double salary;

	@Getter
	@Setter
	private String department;

	@Override
	public String toString() {
		return "EmployeeDetails [id=" + id + ", name=" + name + ", salary=" + salary + ", department=" + department
				+ "]";
	}
}
