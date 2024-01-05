package screen;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class MenuScreenController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button playBtn;
    @FXML
    private Button ruleBtn;
    @FXML
    private Button quitBtn;
    @FXML
    public void initialize() {
    }
    @FXML
    private void playBtnClicked() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("play.fxml"));
            Scene playScene = new Scene(loader.load());

            Stage stage = (Stage) playBtn.getScene().getWindow();
            stage.setScene(playScene);
            stage.show();
    }
    @FXML
    private void ruleBtnClicked(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rule.fxml"));
            Parent ruleScreen = loader.load();
            Scene ruleScene = new Scene(ruleScreen);
            
            Stage stage = (Stage) ruleBtn.getScene().getWindow();
            stage.setScene(ruleScene);
            stage.show();
    
    }
    @FXML
    private void quitBtnClicked(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("You're about to quit");
        alert.setContentText("Do you want this ?");
        if (alert.showAndWait().get()== ButtonType.OK){
        Stage stage = (Stage) quitBtn.getScene().getWindow();
        stage.close();
        }
    }
}
