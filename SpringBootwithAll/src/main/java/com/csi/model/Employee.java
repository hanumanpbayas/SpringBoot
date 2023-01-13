package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="employee")
public class Employee {

    @GeneratedValue
    @Id
    @Column(name = "emp_Id")
    private int empId;

    @NonNull
    @Column(name = "emp_First_Name")
    @Pattern(regexp = "[A-Za-z]*", message = "Name Should Not be a Special Characters or Space")
    private String empName;

    @NonNull
    @Column(name = "emp_User_Name")
    @Pattern(regexp = "[A-Za-z]*",message = "UserName Should Not be a Space or Spacial Characters")
    private String empUserName;

    @Column(name = "emp_Address")
    private String empAddress;

    @Column(name = "emp_Salary")
    private double empSalary;

    @NonNull
    @Column(name = "emp_Age")

    private int empAge;

    @NonNull
    @Column(name = "emp_Contact_Number",unique = true)
    @Pattern(regexp = "(\\+91|0)[0-9]{10}",message = "Please Enter Correct Mobile Number")
    private String empContactNumber;

    @Column(name = "emp_DOB")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date empDOB;

    @NonNull
    @Column(name = "emp_Email_Id",unique = true)
    @Email(message ="Email Must Be Valid/Correct")
    private String empEmailId;

    @Size(min = 4, message = "Password should at list 4 digit")
    @Column(name = "emp_Password")
    private String empPassword;

}
