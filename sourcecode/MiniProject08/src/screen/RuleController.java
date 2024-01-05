package screen;

import java.io.IOError;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RuleController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button ruleplayBtn;

    @FXML
    private Button backBtn;
    @FXML
    public void initialize() {
    }
    @FXML
    private void ruleplayBtnClicked(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("play.fxml"));
            Parent playScreen = loader.load();
            Scene playScene = new Scene(playScreen);
            
            Stage stage = (Stage) ruleplayBtn.getScene().getWindow();
            stage.setScene(playScene);
            stage.show();
    }
    @FXML
    private void backBtnClicked(ActionEvent event) throws IOException {
      
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent menuScreen = loader.load();
            Scene menuScene = new Scene(menuScreen);
            
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(menuScene);
            stage.show();
    } 
}