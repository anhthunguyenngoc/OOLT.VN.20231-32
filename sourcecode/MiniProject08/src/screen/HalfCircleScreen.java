package screen;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import squares.HalfCircle;

public class HalfCircleScreen extends AnchorPane{
	private Arc arc = new Arc();
	private Label label = new Label();
	private VBox vbox = new VBox();
	private FlowPane flowPane = new FlowPane();
	
	public HalfCircleScreen(HalfCircle hfC) {
		Group group = new Group();
		
		arc.setLength(180.0);
		arc.setRadiusX(75);
		arc.setRadiusY(77);
		arc.setFill(Color.rgb(205, 161, 128));
		arc.setStrokeWidth(5);
    	arc.setStroke(Color.rgb(102, 66, 40));
    	arc.setType(ArcType.ROUND);
    	
    	if(hfC.getId()==0) {
			arc.setStartAngle(90.0);
			label.setTranslateX(-68); label.setTranslateY(-10);
			flowPane.setTranslateX(-50); flowPane.setTranslateY(-50);
		}
		else if(hfC.getId()==6){
			arc.setStartAngle(270.0);
			label.setTranslateX(54); label.setTranslateY(-10);
			flowPane.setTranslateX(5); flowPane.setTranslateY(-50);
			flowPane.setAlignment(Pos.TOP_RIGHT);
		}
    	
    	label.setFont(Font.font("System", FontWeight.BOLD, 15));
    	label.setTextFill(Color.rgb(102, 66, 40));   
    	label.setText(""+hfC.getPoint());
    
    	flowPane.setPrefWidth(45.0);
    	flowPane.setPrefHeight(100.0);	
    	flowPane.getChildren().add(new Circle(6.0));
    	for(int i=0; i<hfC.getNumberOfSmallGems(); i++) {
    		flowPane.getChildren().add(new Circle(3.0));
    	}
    	
    	group.getChildren().add(arc);
    	group.getChildren().add(label);
    	group.getChildren().add(flowPane);
    	
    	vbox.setAlignment(Pos.CENTER);
    	vbox.setPrefSize(75.0, 200.0);
    	vbox.getChildren().add(group);
    	
    	this.getChildren().add(vbox);
	}
	
	public void getGemsInSquare() {
		flowPane.getChildren().clear();
		label.setText(""+0);
 
	}
	public void spreadGems() {
		flowPane.getChildren().add(new Circle(3.0));
	
		int newPoint = Integer.parseInt(label.getText()) + 1;
		label.setText(String.valueOf(newPoint));
	}
}
