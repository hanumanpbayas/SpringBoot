package com.csi.service;

import com.csi.model.Employee;
import com.csi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepositoryImpl;

    public Employee signUp(Employee employee){
        return employeeRepositoryImpl.save(employee);
    }

    public boolean signIn(String empUserName,String empPassword){
        boolean flag =false;
        for (Employee employee1:employeeRepositoryImpl.findAll()){
            if (employee1.getEmpUserName().equals(empUserName) && employee1.getEmpPassword().equals(empPassword)){
                flag=true;
            }
        }
        return flag;
    }

    public List<Employee> getAllData(){
        return employeeRepositoryImpl.findAll();
    }

    public Optional<Employee> getDataById(int empId){
        return employeeRepositoryImpl.findById(empId);
    }


    public Employee getDataByContactNumber(String empContactNumber){
        return employeeRepositoryImpl.findByEmpContactNumber(empContactNumber);
    }

    public List<Employee> getDataByAnyKey(Employee employee){

        List<Employee> employeeList = new ArrayList<Employee>();
        for (Employee employee1:employeeRepositoryImpl.findAll()){
            if (employee1.getEmpId()== employee.getEmpId() ||
            employee1.getEmpName().equals(employee.getEmpName()) ||
            employee1.getEmpUserName().equals(employee.getEmpUserName()) ||
            employee1.getEmpSalary()==employee.getEmpSalary() ||
            employee1.getEmpDOB()==employee.getEmpDOB() ||
            employee1.getEmpContactNumber().equals(employee.getEmpContactNumber())){
                employeeList.add(employee1);
            }
        }
    return employeeList;

    }

    public Employee updateData(Employee employee){
        return employeeRepositoryImpl.save(employee);
    }

    public List<Employee> filterDataBySalary(double empSalary){
        return getAllData().stream().filter(employee -> employee.getEmpSalary()<=empSalary).collect(Collectors.toList());
    }

    public List<Employee> sortByEmpAge(){
        return getAllData().stream().sorted(Comparator.comparingLong(Employee::getEmpAge)).collect(Collectors.toList());
    }

    public  List<Employee> sortByEmpSalary(){
        return getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList());
    }

    public List<Employee> sortByEmpDOB(){
        return getAllData().stream().sorted((e1,e2)->e1.getEmpDOB().compareTo(e2.getEmpDOB())).collect(Collectors.toList());
    }


    public void deleteDataById(int empId){
        employeeRepositoryImpl.deleteById(empId);
    }
}
