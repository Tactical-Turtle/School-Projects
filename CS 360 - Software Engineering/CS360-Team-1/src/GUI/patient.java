package GUI;
import java.awt.*;
import java.util.Date;

public class patient
{
    private String firstName;
    private String lastName;
    private String primaryDoc;
    private String patientType;
    private int roomNumber;
    private Date firstVisit;
    private Date lastVisit;
    private Appointment appointmentDate;
    private String[] allergies;
    private String notes;

    public patient() {
    }


    public String getPrimaryDoc(){ return primaryDoc;}

    public void setPrimaryDoc(String primaryDoc){ this.primaryDoc= primaryDoc;}

    public String getPatientType() {
        return patientType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getFirstVisit() {
        return firstVisit;
    }

    public void setFirstVisit(Date firstVisit) {
        this.firstVisit = firstVisit;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String[] getAllergies() {
        return allergies;
    }

    public void setAllergies(String[] allergies) {
        this.allergies = allergies;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
}
