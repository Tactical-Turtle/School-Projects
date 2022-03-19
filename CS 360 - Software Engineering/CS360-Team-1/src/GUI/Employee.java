/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Cool IT help
 */
public class Employee {
    private String firstName, lastName, accType, userName;


   public Employee(String firstName, String lastName, String accType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accType = accType;
       // this.userName = userName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;

    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setAccType(String accType){
        this.accType = accType;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getAccType(){
        return accType;
    }
    public String getUserName(){
        return userName;
    }

}




