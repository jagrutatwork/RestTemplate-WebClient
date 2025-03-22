package com.employeeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeapp.response.EmployeeResponse;
import com.employeeapp.service.EmployeeService;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id)
	{
		System.out.println("abcd started ");
		EmployeeResponse EmployeeResponse =  employeeService.getEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(EmployeeResponse);
		
	}
}
