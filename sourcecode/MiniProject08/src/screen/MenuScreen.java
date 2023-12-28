package screen;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

public class MenuScreen extends Application{
  
	public static final double WIDTH = 1100;
	public static final double HEIGHT = 700;
	
	public static double width, height;
	
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			stage.setMinWidth(150.0);
			stage.setMinHeight(150.0);
			
			Parent root = FXMLLoader.load(getClass().getResource("play.fxml"));
	
			Scene scene = new Scene(root, WIDTH, HEIGHT);
			scene.setFill(Color.LIGHTSTEELBLUE);
			width = scene.getWidth()/600.0;
			height = scene.getHeight()/400.0;
			
			Scale scale = new Scale(width, height, 0, 0);
			
			
			scene.widthProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
					width = scene.getWidth()/600.0;
					if(width>1) scale.setX(width);
					
				}
				
			});
			scene.heightProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
					height = scene.getHeight()/400.0;
					System.out.println(""+height);
					if(height>1) scale.setY(height);
				}
				
			});
					
			scene.getRoot().getTransforms().setAll(scale);
			
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String[] arg) {
		launch(arg);
	}

}
