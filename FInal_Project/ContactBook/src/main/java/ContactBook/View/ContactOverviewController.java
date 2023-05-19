package ContactBook.View;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ContactBook.Model.Contact;
import ContactBook.MainApp;

public class ContactOverviewController {
    @FXML
    private TableView<Contact> contactTable;
    @FXML
    private TableColumn<Contact, String> firstNameColumn;
    @FXML
    private TableColumn<Contact, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label phoneCellLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label emailPrimaryLabel;
    @FXML
    private Label emailSecondaryLabel;


    private MainApp mainApp;

    public ContactOverviewController() {
    }

    @FXML
    private void initialize() {
        // Reset top two columns
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        showContactDetails(null);

        contactTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showContactDetails(newValue));
    }

    private void showContactDetails(Contact contact) {
        if (contact != null) {
            firstNameLabel.setText(contact.getFirstName());
            lastNameLabel.setText(contact.getLastName());
            phoneCellLabel.setText(contact.getPhoneCell());
            addressLabel.setText(contact.getAddress());
            emailPrimaryLabel.setText(contact.getEmailPrimary());
            emailSecondaryLabel.setText(contact.getEmailSecondary());
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            phoneCellLabel.setText("");
            addressLabel.setText("");
            emailPrimaryLabel.setText("");
            emailSecondaryLabel.setText("");
        }
    }

    private void handleNewContact() {
        Contact tempPerson = new Contact();
        boolean okClicked = mainApp.showContactEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getContactData().add(tempPerson);
        }
    }

    /**
     * 사용자가 edit 버튼을 클릭할 때 호출된다.
     * 선택한 연락처 정보를 변경하기 위해 다이얼로그를 연다.
     */
    @FXML
    private void handleEditContact() {
        Contact selectedPerson = contactTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showContactEditDialog(selectedPerson);
            if (okClicked) {
                showContactDetails(selectedPerson);
            }

        } else {
            // 아무것도 선택하지 않았다.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    public void handleDeleteContact() {
        int selectedIndex = contactTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            contactTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // 테이블에 observable 리스트 데이터를 추가한다.
        contactTable.setItems(mainApp.getContactData());
    }
}