package com.twu.biblioteca.beans;

import com.twu.biblioteca.utils.UserFileUtil;

public class User {
    private String username;
    private String password;
    private String email;
    private String tel;

    private Role role;

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

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

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, Role role, String email, String tel) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.tel = tel;
    }

    public boolean login() {
        UserFileUtil user = new UserFileUtil();
        Role role = user.getUser(this);
        this.role = role;
        if (role != Role.NONE)
            return true;
        return false;
    }

    public String toString() {
        return this.username + " :: " +
                this.getRole().toString() + " :: " +
                this.getEmail() + " :: " +
                this.getTel();
    }

}
