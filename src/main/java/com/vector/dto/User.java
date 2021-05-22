package com.vector.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/***
 * User dto
 */
public class User {

    public User(){

    }

    public User(@NotBlank(message = "email is required") @Size(min = 1, max = 100) String email, @NotBlank(message = "password is required") @Size(min = 1, max = 100) String password, @Size(min = 1, max = 100) String firstName, @Size(min = 1, max = 100) String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NotBlank(message = "email is required")
    @Size(min=1,max=100)
    private String email;

    @NotBlank(message = "password is required")
    @Size(min=1,max=100)
    private String password;

    @Size(min=1,max=100)
    private String firstName;

    @Size(min=1,max=100)
    private String lastName;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    /**
     * Create a db model object
     * @return a com.vector.model.User object
     * */
    public com.vector.model.User createUserModel(){
        return new com.vector.model.User(this.email,this.password,this.firstName,this.lastName);
    }
}
