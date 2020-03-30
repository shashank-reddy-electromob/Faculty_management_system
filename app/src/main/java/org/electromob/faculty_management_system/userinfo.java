package org.electromob.faculty_management_system;

public class userinfo {

    public String firstname;
    public String number;
    public String email;
    public String password;
    public String confirmpassword;

    public userinfo(String firstname, String number, String email, String password, String confirmpassword) {
        this.firstname = firstname;
        this.number = number;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public userinfo() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
}
