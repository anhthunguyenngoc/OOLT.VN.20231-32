package screen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class RuleController {

    @FXML
    private Button backButton;

    @FXML
    private Button playButton;

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        System.out.println("Back button clicked");
    }

    @FXML
    private void handlePlayButtonAction(ActionEvent event) {
        System.out.println("Play button clicked");
    }
    

}
