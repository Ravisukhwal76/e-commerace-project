package com.ravi.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.major.entity.Employee;
import com.ravi.major.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	 public List<Employee> getEmployee(){
	        return employeeRepository.findAll();
	    }
//	    public void addCategory(Categories categories){
//	    	employeeRepository.save(categories);
//	    }

//	    public void removeById(int id){
//	    	employeeRepository.deleteById((long) id);
//	    }

	    public Optional<Employee> getEmployeeById(int id){
	        return employeeRepository.findById((long) id);
	    }

}
