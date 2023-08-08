package com.numerty;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveEmployeeTest()
	{
		Employee employee = Employee.builder()
				.firstName("Akanksha")
				.lastName("Yambadwar")
				.email("akanksha@gmail.com")
				.build();
		
		employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
	}
	
	@Test
	@Order(2)
	public void saveEmployeeTest2()
	{
		Employee employee = Employee.builder()
				.firstName("Medha")
				.lastName("Yambadwar")
				.email("medha@gmail.com")
				.build();
		
		employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void getEmployeeTest() {
	    Optional<Employee> optionalEmployee = employeeRepository.findById(1L);

	    if (optionalEmployee.isPresent()) {
	        Employee employee = optionalEmployee.get();
	        System.out.println("Employee retrieved: " + employee);
	        Assertions.assertThat(employee.getId()).isEqualTo(1L);
	    }

	}
	

	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void deleteEmployeeTest() {
	    // Fetch the employee by ID
	    Optional<Employee> optionalEmployee = employeeRepository.findById(2L);

	    // Make sure the employee with ID 1L exists
	    assertTrue(optionalEmployee.isPresent(), "Employee with ID 2L not found");

	    // Delete the employee
	    Employee employee = optionalEmployee.get();
	    employeeRepository.delete(employee);

	    // Try to find the employee by email
	    Optional<Employee> deletedEmployee = employeeRepository.findByEmail("medha@gmail.com");

	    // Assert that the deleted employee is not found
	    assertFalse(deletedEmployee.isPresent(), "Deleted employee found by email");

	    // You can also assert additional conditions as needed
	}

	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void saveEmployeeTest3()
	{
		Employee employee = Employee.builder()
				.firstName("Akku")
				.lastName("Yambadwar")
				.email("akku@gmail.com")
				.build();
		
		employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);
	}

	@Test
	@Order(6)
	@Rollback(value = false)
	public void updateEmployeeTest() {
	    // Fetch the employee with ID 1L
	    Optional<Employee> optionalEmployee = employeeRepository.findById(3L);
	    
	    if (optionalEmployee.isPresent()) {
	        Employee employee = optionalEmployee.get();

	        // Update the email
	        employee.setEmail("mau@gmail.com");

	        // Save the updated employee
	        Employee employeeUpdated = employeeRepository.save(employee);

	        // Check if the email has been updated
	        assertEquals("mau@gmail.com", employeeUpdated.getEmail());
	    } else {
	        fail("Employee with ID 1L not found");
	    }
	}
	
	
}        		