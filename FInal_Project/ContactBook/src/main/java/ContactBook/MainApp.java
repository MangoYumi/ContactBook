package ContactBook;

import java.io.IOException;

import javafx.stage.Modality;
import ContactBook.Model.Contact;
import ContactBook.View.ContactEditController;
import ContactBook.View.ContactOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    private final ObservableList<Contact> contactData = FXCollections.observableArrayList();

    public MainApp() {
        // Contact data
        contactData.add(new Contact("Whitney", "Baird", "5305101920", "66 Vine St. Gastonia, NC 28052", "stembo@snapboosting.com", "nhc8487@maill.dev"));
        contactData.add(new Contact("Madeleine", "Wallace", "7068817391", "674 Boston Dr. Hudsonville, MI 49426", "liz3070@bdredemptionservices.com", "wegadevil666@alertslit.top"));
        contactData.add(new Contact("Olin", "Snow", "9703683827", "9605 Sheffield Drive Indiana, PA 15701", "ramandeepsromi@postimel.com", "anf4187@yonaki.xyz"));
        contactData.add(new Contact("Nicholas", "Sanchez", "5820185022", "8263 School Road Summerfield, FL 34491", "dinarapsatarov@realedoewcenter.com", "muhammettm89@changaji.com"));
        contactData.add(new Contact("Manuela", "Bond", "6720840884", "9099 Grand St. Paramus, NJ 07652", "scales2669@quequeremos.com", "slp72004@sharkslasers.com"));
    }

    public ObservableList<Contact> getContactData() {
        return contactData;
    }
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showContactOverview();
    }

    /**
     * Restart root layout
     */
    public void initRootLayout() {
        try {
            // load `rootLayout.fxml`
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/RootLayout.fxml"));
            System.out.println(getClass().getResource("/RootLayout.fxml"));
            rootLayout = loader.load();

            // display scene including root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displaying `ContactOverview.fxml` on top of `RootLayout.fxml`
     */
    public void showContactOverview() {
        try {
            // Load contact summary
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ContactOverview.fxml"));
            AnchorPane ContactOverview = loader.load();

            // Center `ContactOverview.fxml`
            rootLayout.setCenter(ContactOverview);

            ContactOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showContactEditDialog(Contact contact) {
        try {
            // fxml 파일을 로드하고 나서 새로운 스테이지를 만든다.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ContactEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // 다이얼로그 스테이지를 만든다.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // person을 컨트롤러에 설정한다.
            ContactEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setContact(contact);

            // 다이얼로그를 보여주고 사용자가 닫을 때까지 기다린다.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Return to main stage
     * @return
     */
    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}