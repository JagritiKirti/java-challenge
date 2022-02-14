package jp.co.axa.apidemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jp.co.axa.apidemo.model.EmployeeDetails;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	
	//Default Constructor
	public Employee() {
		
	}
	
    public Employee(EmployeeDetails employeeDetails) {
		super();
		this.id = employeeDetails.getId();
		this.name = employeeDetails.getName();
		this.salary = employeeDetails.getSalary();
		this.department = employeeDetails.getDepartment();
	}

	@Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_NAME")
    private String name;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_SALARY")
    private Double salary;

    @Getter
    @Setter
    @Column(name="DEPARTMENT")
    private String department;

}
