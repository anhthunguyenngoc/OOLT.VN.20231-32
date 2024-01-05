package screen;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void showMenuScene() {
        loadScene("menu.fxml");
    }

    public static void showPlayScreenScene() {
        loadScene("play.fxml");
    }

    public static void showRuleScene() {
        loadScene("rule.fxml");
    }

    private static void loadScene(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource(fxmlFileName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
