package com.skypro.employees_mockito_hw;

import org.springframework.util.StringUtils;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private int department;
    private int salary;

    public Employee(String lastName, String firstName, int department, int salary) {
        this.lastName = StringUtils.capitalize(lastName.toLowerCase());
        this.firstName = StringUtils.capitalize(firstName.toLowerCase());
        this.department = department;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartment() {
        return department;
    }

    public String getFullName() {
        return firstName + lastName;
    }

    @Override
    public String toString() {
        return "com.courseworkto.streamapihw.Employee{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", department=" + department + ", salary=" + salary + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && salary == employee.salary && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, department, salary);
    }
}
