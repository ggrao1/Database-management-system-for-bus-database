
package com.ask.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "empId",
    "empName",
    "empDob",
    "empSex",
    "busNo",
    "empSalary"
})
public class Employee {

    @JsonProperty("empId")
    private int empId;
    @JsonProperty("empName")
    private String empName;
    @JsonProperty("empDob")
    private String empDob;
    @JsonProperty("empSex")
    private String empSex;
    @JsonProperty("busNo")
    private int busNo;
    @JsonProperty("empSalary")
    private int empSalary;

    @JsonProperty("empId")
    public int getEmpId() {
        return empId;
    }

    @JsonProperty("empId")
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    @JsonProperty("empName")
    public String getEmpName() {
        return empName;
    }

    @JsonProperty("empName")
    public void setEmpName(String empName) {
        this.empName = empName;
    }

    @JsonProperty("empDob")
    public String getEmpDob() {
        return empDob;
    }

    @JsonProperty("empDob")
    public void setEmpDob(String empDob) {
        this.empDob = empDob;
    }

    @JsonProperty("empSex")
    public String getEmpSex() {
        return empSex;
    }

    @JsonProperty("empSex")
    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }

    @JsonProperty("busNo")
    public int getBusNo() {
        return busNo;
    }

    @JsonProperty("busNo")
    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    @JsonProperty("empSalary")
    public int getEmpSalary() {
        return empSalary;
    }

    @JsonProperty("empSalary")
    public void setEmpSalary(int empSalary) {
        this.empSalary = empSalary;
    }

}
