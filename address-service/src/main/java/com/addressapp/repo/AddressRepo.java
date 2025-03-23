package com.addressapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.addressapp.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {
	
	
	//find addess based on employee id
	@Query(nativeQuery = true, value ="Select ea.id, ea.line1, ea.line2, ea.state, ea.zip from microservice1.address ea\n"
			+ "join microservice1.employee e \n"
			+ "on e.id = ea.employee_id\n"
			+ "where ea.employee_id = 1;")
	Address findAddressByEmployeeId(@Param("employeeId") int employeeId);
	
	


}
