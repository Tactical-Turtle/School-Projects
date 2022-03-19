package GUI;

import java.util.Date;

public class Person {
    // Person attributes
    private String lastName;
    private String firstName;
    private String middleName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String emailAddress;

    private Date dateOfBirth;
    private char sex;

    private int SSN;
    private int ZIP;
    private int cellPhoneNumber;
    private int workPhoneNumber;


    // Getter and Setter methods for each variable
    // Last Name
    String getLastName()
    {
        return lastName;
    }

    void setLastName(String setLastName)
    {
        lastName = setLastName;
    }

    // First Name
    String getFirstName()
    {
        return firstName;
    }

    void setFirstName(String setFirstName)
    {
        firstName = setFirstName;
    }

    // Middle Name
    private String getMiddleName()
    {
        return middleName;
    }

    private void setMiddleName(String setMiddleName)
    {
        middleName = setMiddleName;
    }


    // Address1
    private String getAddress1()
    {
        return address1;
    }

    private void setAddress1(String setAddress1)
    {
        address1 = setAddress1;
    }

    // Address2
    private String getAddress2()
    {
        return address2;
    }

    private void setAddress2(String setAddress2)
    {
        address2 = setAddress2;
    }


    // City
    private String getCity()
    {
        return city;
    }

    private void setCity(String setCity)
    {
        city = setCity;
    }

    // State
    private String getState()
    {
        return state;
    }

    private void setState(String setState)
    {
        state = setState;
    }

    // Email
    private String getEmailAddress()
    {
        return emailAddress;
    }

    private void setEmailAddress(String setEmailAddress)
    {
        emailAddress = setEmailAddress;
    }


    // Date of Birth
    private Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    private void setDateOfBirth(Date setDateOfBirth)
    {
        dateOfBirth = setDateOfBirth;
    }


    // Sex
    private char getSex()
    {
        return sex;
    }

    private void setSex(char setSex)
    {
        sex = setSex;
    }


    // SSN
    private int getSSN()
    {
        return SSN;
    }

    private void setSSN(int setSSN)
    {
        SSN = setSSN;
    }

    // ZIP Code
    private int getZIP()
    {
        return ZIP;
    }

    private void setZIP(int setZIP)
    {
        ZIP = setZIP;
    }

    // Cell phone number
    private int getCellPhoneNumber()
    {
        return cellPhoneNumber;
    }

    private void setCellPhoneNumber(int setCellPhoneNumber)
    {
        cellPhoneNumber = setCellPhoneNumber;
    }

    // Work phone number
    private int getWorkPhoneNumber()
    {
        return workPhoneNumber;
    }

    private void setWorkPhoneNumber(int setWorkPhoneNumber)
    {
        workPhoneNumber = setWorkPhoneNumber;
    }

}
