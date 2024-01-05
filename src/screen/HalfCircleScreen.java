package screen;

import javafx.beans.property.SimpleIntegerProperty;
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
	private Label point = new Label();
	private VBox vbox = new VBox();
	private FlowPane flowPane = new FlowPane();
	private HalfCircle hfC = new HalfCircle();
	
	public HalfCircleScreen(HalfCircle hfC) {
		Group group = new Group();
		this.hfC = hfC;
		
		arc.setLength(180.0);
		arc.setRadiusX(75);
		arc.setRadiusY(77);
		arc.setFill(Color.rgb(205, 161, 128));
		arc.setStrokeWidth(5);
    	arc.setStroke(Color.rgb(102, 66, 40));
    	arc.setType(ArcType.ROUND);
    	
    	if(hfC.getId()==0) {
			arc.setStartAngle(90.0);
			point.setTranslateX(-68); point.setTranslateY(-10);
			flowPane.setTranslateX(-50); flowPane.setTranslateY(-50);
		}
		else if(hfC.getId()==6){
			arc.setStartAngle(270.0);
			point.setTranslateX(54); point.setTranslateY(-10);
			flowPane.setTranslateX(5); flowPane.setTranslateY(-50);
			flowPane.setAlignment(Pos.TOP_RIGHT);
		}
    	
    	point.setFont(Font.font("System", FontWeight.BOLD, 15));
    	point.setTextFill(Color.rgb(102, 66, 40));   
    	point.setText(""+hfC.getPoint());
    
    	flowPane.setPrefWidth(45.0);
    	flowPane.setPrefHeight(100.0);	
    	flowPane.getChildren().add(new Circle(6.0));
    	for(int i=0; i<hfC.getNumberOfSmallGems(); i++) {
    		flowPane.getChildren().add(new Circle(3.0));
    	}
    	
    	group.getChildren().add(arc);
    	group.getChildren().add(point);
    	group.getChildren().add(flowPane);
    	
    	vbox.setAlignment(Pos.CENTER);
    	vbox.setPrefSize(75.0, 200.0);
    	vbox.getChildren().add(group);
    	
    	this.getChildren().add(vbox);
	}
	
	public void getGemsInSquare() {
		flowPane.getChildren().clear();
		point.setText(""+0);
    }
	
	public void spreadGems() {
		if(hfC.getId()==0) {
			flowPane.getChildren().add(new Circle(3.0));
		}
		else if(hfC.getId()==6) {
			flowPane.getChildren().add(0, new Circle(3.0));
		}
    	point.textProperty().bind(new SimpleIntegerProperty(hfC.getPoint()).asString());
    }
	
	public void getGemsToPoint() {
    	flowPane.getChildren().clear();
    	point.textProperty().bind(new SimpleIntegerProperty(hfC.getPoint()).asString());
	}
}
