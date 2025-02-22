package com.skypro.employees_mockito_hw.service;

import com.skypro.employees_mockito_hw.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    public String welcome();
    public List<Employee> getEmployeesByDep(int id);
    public int sumSalary(int id);
    public Employee maxSalary(int id);
    public Employee minSalary(int id);
    public Map<Integer, List<Employee>> allEmployeesByDeps();

}
