package ContactBook.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contact {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty phoneCell;
    private final StringProperty address;
    private final StringProperty emailPrimary;
    private final StringProperty emailSecondary;


    public Contact() {
        this(null, null, null, null, null, null);
    }

    public Contact(String firstName, String lastName, String phoneCell, String address, String emailPrimary, String emailSecondary) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        //dummy data
        this.phoneCell = new SimpleStringProperty("1234567890");
        this.address = new SimpleStringProperty("1234 test st. Dummy, XX 56789");
        this.emailPrimary = new SimpleStringProperty("test1@example.com");
        this.emailSecondary = new SimpleStringProperty("test2@example.com");
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getPhoneCell() {
        return phoneCell.get();
    }

    public void setPhoneCell(String phoneCell) {
        this.phoneCell.set(phoneCell);
    }

    public StringProperty PhoneCellProperty() {
        return phoneCell;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty AddressProperty() {
        return address;
    }

    public String getEmailPrimary() {
        return emailPrimary.get();
    }

    public void setEmailPrimary(String emailPrimary) {
        this.emailPrimary.set(emailPrimary);
    }

    public StringProperty emailPrimaryProperty() {
        return emailPrimary;
    }

    public String getEmailSecondary() {
        return emailSecondary.get();
    }

    public void setEmailSecondary(String emailSecondary) {
        this.emailSecondary.set(emailSecondary);
    }

    public StringProperty emailSecondaryProperty() {
        return emailSecondary;
    }
}