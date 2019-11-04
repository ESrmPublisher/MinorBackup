package com.example.minorbackup;

public class Users {

    String regno;

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    String fname;
    String lname;
    String pass;
    String emailid;
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Users(){

    }

    public Users(String regno, String fname, String lname, String pass, String emailid, String role) {
        this.regno = regno;
        this.fname = fname;
        this.lname = lname;
        this.pass = pass;
        this.emailid = emailid;
        this.role = role;
    }
}
