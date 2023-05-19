package ContactBook.View;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ContactBook.Model.Contact;

public class ContactEditController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField phoneCellField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailPrimaryField;
    @FXML
    private TextField emailSecondaryField;


    private Stage dialogStage;
    private Contact contact;
    private boolean okClicked = false;

    /**
     * 컨트롤러 클래스를 초기화한다.
     * 이 메서드는 fxml 파일이 로드된 후 자동으로 호출된다.
     */
    @FXML
    private void initialize() {
    }

    /**
     * 이 다이얼로그의 스테이지를 설정한다.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * 다이얼로그에서 변경될 연락처를 설정한다.
     *
     * @param contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;

        firstNameField.setText(contact.getFirstName());
        lastNameField.setText(contact.getLastName());
        phoneCellField.setText(contact.getPhoneCell());
        addressField.setText(contact.getAddress());
        emailPrimaryField.setText(contact.getEmailPrimary());
        emailSecondaryField.setText(contact.getEmailSecondary());
    }

    /**
     * 사용자가 OK를 클릭하면 true를, 그 외에는 false를 반환한다.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * 사용자가 OK를 클릭하면 호출된다.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            contact.setFirstName(firstNameField.getText());
            contact.setLastName(lastNameField.getText());
            contact.setPhoneCell(phoneCellField.getText());
            contact.setAddress(addressField.getText());
            contact.setEmailPrimary(emailPrimaryField.getText());
            contact.setEmailSecondary(emailSecondaryField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * 사용자가 cancel을 클릭하면 호출된다.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * 텍스트 필드로 사용자 입력을 검사한다.
     *
     * @return true if the input is valid
     */

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (phoneCellField.getText() == null || phoneCellField.getText().length() == 0) {
            errorMessage += "No valid phone number!\n";
        }

        if (addressField.getText() == null || addressField.getText().length() == 0) {
            errorMessage += "No valid address\n";
        }

        if (emailPrimaryField.getText() == null || emailPrimaryField.getText().length() == 0) {
            errorMessage += "No valid email!\n";
        }

        if (emailSecondaryField.getText() == null || emailSecondaryField.getText().length() == 0) {
            errorMessage += "No valid email!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // 오류 메시지를 보여준다.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}