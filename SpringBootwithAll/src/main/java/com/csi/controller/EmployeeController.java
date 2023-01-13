package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("/signUp")
    public ResponseEntity<Employee> signUp(@Valid @RequestBody Employee employee){
        Employee employee1 =employeeServiceImpl.signUp(employee);
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);
    }

    @GetMapping("/getAllData")
    public ResponseEntity<List<Employee>> getAllData(){
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @GetMapping("/signIn/{empUserName}/{empPassword}")
    public ResponseEntity<String> signIn(@PathVariable String empUserName,@PathVariable String empPassword){
        employeeServiceImpl.signIn(empUserName,empPassword);
        return ResponseEntity.ok("SignIn Successfully");
    }

    @GetMapping("/getDataById/{empId}")
    public ResponseEntity<Optional<Employee>> getDataById(@PathVariable int empId){
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/getDataBYAnyKey")
    public ResponseEntity<List<Employee>> getDataByAnyInput(@RequestBody Employee employee){
        return ResponseEntity.ok(employeeServiceImpl.getDataByAnyKey(employee));
    }

    @PutMapping("/updateData/{empId}")
    public ResponseEntity<Employee> updateData(@RequestBody Employee employee,@PathVariable int empId) throws RecordNotFoundException {
        Employee employee1=employeeServiceImpl.getDataById(empId).orElseThrow(()-> new RecordNotFoundException("Invalid Employee Id"));
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpUserName(employee.getEmpUserName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpAge(employee.getEmpAge());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

    return ResponseEntity.ok(employeeServiceImpl.updateData(employee1));
    }

    @GetMapping("/sortByEmpSalary")
    public ResponseEntity<List<Employee>> sortByEmpSalary(){
        return ResponseEntity.ok(employeeServiceImpl.sortByEmpSalary());
    }

    @GetMapping("/sortByEmpAge")
    public ResponseEntity<List<Employee>> sortByEmpAge() {
    return ResponseEntity.ok(employeeServiceImpl.sortByEmpAge());
    }

    @GetMapping("/sortByempDOB")
    public ResponseEntity<List<Employee>> sortBYEmpDOB(){
        return ResponseEntity.ok(employeeServiceImpl.sortByEmpDOB());
    }

    @GetMapping("/getDataByEmpContactNumber/{empContactNumber}")
    public ResponseEntity<Employee> getDataByEmpContactNumber(@PathVariable String empContactNumber){
        return ResponseEntity.ok(employeeServiceImpl.getDataByContactNumber(empContactNumber));
    }


    @GetMapping("/filterDataBySalary/{empSalary}")
    public ResponseEntity<List<Employee>> filerDataBySalary(@PathVariable double empSalary){
        return ResponseEntity.ok(employeeServiceImpl.filterDataBySalary(empSalary));
    }

    @DeleteMapping("/deleteDataById/{empId}")
    public ResponseEntity<String> deleteData(@PathVariable int empId){
        return ResponseEntity.ok("Delete Data Successfully");
    }
}
