package com;

public class Student{
    // Declaring Student Variables
    private String rollNo;
    private String name;
    private String contact;
    private String email;
    private int year;
    private String branch;
    private String section;
    // Constructor
    public Student(String rollNo, String name, String contact, String email, int year, String branch, String section){
        this.rollNo=rollNo;
        this.name=name;
        this.contact=contact;
        this.email=email;
        this.year=year;
        this.branch=branch;
        this.section=section;
    }
    // Getters and Setters
    public String getRollNo(){
        return rollNo;
    }
    public void setRollNo(String rollNo){
        this.rollNo=rollNo;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getContact(){
        return contact;
    }
    public void setContact(String contact){
        this.contact=contact;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year=year;
    }
    public String getBranch(){
        return branch;
    }
    public void setBranch(String branch){
        this.branch=branch;
    }
    public String getSection(){
        return section;
    }
    public void setSection(String section){
        this.section=section;
    }
    // Main Method
    public static void main(String[] args){

    }
}