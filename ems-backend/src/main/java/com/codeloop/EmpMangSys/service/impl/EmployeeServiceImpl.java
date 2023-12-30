package com.codeloop.EmpMangSys.service.impl;

import com.codeloop.EmpMangSys.dto.EmployeeDto;
import com.codeloop.EmpMangSys.entity.Employee;
import com.codeloop.EmpMangSys.exception.ResourceNotFoundException;
import com.codeloop.EmpMangSys.mapper.EmployeeMapper;
import com.codeloop.EmpMangSys.repository.EmployeeRepository;
import com.codeloop.EmpMangSys.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto); //convert dto to emp

        Employee savedEmployee = employeeRepository.save(employee); //   select * from table-name

        return EmployeeMapper.mapToEmployeeDto(savedEmployee); //convert emp to dto
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {

        List<Employee> allEmployees = employeeRepository.findAll();

        return allEmployees
                .stream().map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found"));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployeeDto) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found"));

        employee.setFirstName(updateEmployeeDto.getFirstName());
        employee.setLastName(updateEmployeeDto.getLastName());
        employee.setEmail(updateEmployeeDto.getEmail());

        Employee updatedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {

        // to check if the employee is there in the database or not
        employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found"));

        employeeRepository.deleteById(employeeId);

    }


}
