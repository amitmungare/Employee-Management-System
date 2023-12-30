package com.codeloop.EmpMangSys.repository;

import com.codeloop.EmpMangSys.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {



}
