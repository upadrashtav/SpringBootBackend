package com.example.sprintbootbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Employee;
import com.example.springbootbackend.repository.EmployeeRepository;
import com.example.springbootbackend.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeRepository employeeRepository;
		
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}



	@Override
	public Employee saveEmployee(Employee employee) {
		//TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}



	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}



	@Override
	public Employee getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}else {
			throw new ResourceNotFoundException("Employee","id",id);
		}
	}



	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// TODO Auto-generated method stub
		Optional<Employee> existingEmployee = employeeRepository.findById(id);
		if(existingEmployee.isPresent()) {
			continue;
		}else {
			throw new ResourceNotFoundException("Employee","id",id);
		}
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}



	@Override
	public void deleteEmployee(long id) {

		//check whether an employee existis or not
		
		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","id",id));
		employeeRepository.deleteById(id);
	}

}
