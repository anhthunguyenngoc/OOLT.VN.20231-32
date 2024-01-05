package screen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class MenuScreenController {


    @FXML
    private Button playButton;

    @FXML
    private Button ruleButton;

    @FXML
    private Button quitButton;

    @FXML
    public void initialize() {
    }

    @FXML
    private void handlePlayButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("play.fxml"));
            Parent playScreen = loader.load();
            Scene playScene = new Scene(playScreen);
            
            Stage stage = (Stage) playButton.getScene().getWindow();
            stage.setScene(playScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRuleButton(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("rule.fxml"));
            Parent ruleScreen = loader.load();
            Scene ruleScene = new Scene(ruleScreen);
            
            Stage stage = (Stage) ruleButton.getScene().getWindow();
            stage.setScene(ruleScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleQuitButton(ActionEvent event) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }
}
