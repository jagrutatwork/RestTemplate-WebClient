package com.employeeapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.employeeapp.entity.Employee;
import com.employeeapp.repo.EmployeeRepo;
import com.employeeapp.response.AddressResponse;
import com.employeeapp.response.EmployeeResponse;


@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WebClient webClient;
	
//	@Value("${addressservice,base.url}")
//	private String addressBaseURL;
	
//	@Autowired
//	RestTemplate restTemplate;
//	
//	public EmployeeService(@Value("${addressservice,base.url}") String addressBaseURL, RestTemplateBuilder builder) {
//		// TODO Auto-generated constructor stub
//		this.restTemplate = builder.rootUri(addressBaseURL).build();
//
//	}
//	
	public EmployeeResponse getEmployeeById(int id) {
		Employee employee = employeeRepo.findById(id).orElse(null);
		
		
		//set address data using a REST call
		
		
		
		
//		EmployeeResponse employeeResponse = new EmployeeResponse();
//		employeeResponse.setId(employee.getId());
//		employeeResponse.setName(employee.getName());
//		employeeResponse.setEmail(employee.getEmail());
//		employeeResponse.setBloodGroup(employee.getBloodGroup());
		
		
		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		
//		addressResponse = restTemplate.getForObject("/address/{id}", AddressResponse.class, id);
		AddressResponse addressResponse  = webClient.get().uri("/address/"+id)
															.retrieve()
															.bodyToMono(AddressResponse.class)
															.block();//expicitly use block() to make this blocking, as webclient is by default non blocking
		employeeResponse.setAddressResponse(addressResponse);
		return employeeResponse;
	}

}
