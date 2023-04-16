package com.xwei.demo_restful_api_2023.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addNewEmployee(Employee employee) {
        Optional<Employee> employeeByEmail = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if (employeeByEmail.isPresent()) {
            throw new IllegalStateException("This Email has been taken!");
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) {
            throw new IllegalStateException("Employee with id " + employeeId + " doesn't exist.");
        }
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public Employee updateEmployee(Long employeeId, Employee employee) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) {
            throw new IllegalStateException("Employee with id " + employeeId + " doesn't exist.");
        }

        Employee employeeFromDB = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalStateException("Employee with id " + employeeId + " doesn't exist."));

        if (employee.getName() != null && !Objects.equals(employeeFromDB.getName(), employee.getName())) {
            employeeFromDB.setName(employee.getName());
        }

        if (employee.getDob() != null && !Objects.equals(employeeFromDB.getDob(), employee.getDob())) {
            employeeFromDB.setDob(employee.getDob());
        }

        if (employee.getEmail() != null && !Objects.equals(employeeFromDB.getEmail(), employee.getEmail())) {
            Optional<Employee> employeeByEmail = employeeRepository.findEmployeeByEmail(employee.getEmail());
            if (employeeByEmail.isPresent()) {
                throw new IllegalStateException("This Email has been taken!");
            }
            employeeFromDB.setEmail(employee.getEmail());
        }

        return employeeFromDB;
    }
}
