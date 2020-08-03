package com.api.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.conference.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
