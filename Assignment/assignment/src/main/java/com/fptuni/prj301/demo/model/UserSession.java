/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.prj301.demo.model;

import java.util.Date;

/**
 *
 * @author DUNGHUYNH
 */
public class UserSession {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String role;
    private Date active_date;
    private int user_id;  

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getActive_date() {
        return active_date;
    }

    public void setActive_date(Date active_date) {
        this.active_date = active_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public UserSession(String username, String password, String firstname, String lastname, String role, Date active_date, int user_id) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.active_date = active_date;
        this.user_id = user_id;
    }
    
    
   
    @Override
    public String toString() {
        return "Account{" + "username=" + username + "," + "firstname=" + firstname + "," + "lastname=" + lastname + ", password=" + password + ", rolet=" + role + ", active_date=" + active_date  + '}';
    }
}