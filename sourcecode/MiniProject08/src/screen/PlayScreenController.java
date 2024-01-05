package screen;

import java.util.ArrayList;



import boardgame.BoardGame;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import match.Match;
import squares.*;

public class PlayScreenController {
	@FXML
    private HBox hBox1;
	@FXML
    private HBox hBox2;
	@FXML
    private AnchorPane sPRAncPane;

	    @FXML
	    private ImageView imageView1;  
	    @FXML
	    private ImageView imageView2;
	    @FXML
	    private ImageView imageView3;
	    @FXML
	    private AnchorPane hfCircleAncPane0;
	    @FXML
	    private AnchorPane hfCircleAncPane6;
	    @FXML
	    private HBox boardHbox;
	    @FXML
	    private Label player1TurnLabel;
	    @FXML
	    private Label player2TurnLabel;
	    @FXML
	    private Label point1Label;
	    @FXML
	    private Label point2Label;
	    @FXML
	    private Label turnLabel;
	    @FXML
	    private Label winnerLabel;
	    @FXML
	    private Label winScore1;
	    @FXML
	    private Label winScore2;
	    @FXML
	    private AnchorPane pauseAncPane0;
	    @FXML
	    private AnchorPane pauseAncPane1;
	    @FXML
	    private AnchorPane playAncPane;
	    @FXML
	    private AnchorPane resultAncPane0;
	    @FXML
	    private AnchorPane resultAncPane1;
	    @FXML
	    private AnchorPane turnAncPane;
	    @FXML
	    private HBox hboxSPR2;
	    @FXML
	    private HBox hboxSPR1;

	public static Match match = new Match();
	private BoardGame board = match.getBoard();
	
	private static ArrayList<AnchorPane> row = new ArrayList<>();
	private int squareId;
	private final static double SPREAD_TIME = 1;
	private final static double TURN_APPEAR_TIME = 1;
	
	@FXML
	private void initialize() {
		//set turn
		setTurn((int)(Math.random() * 2) + 1);
		
		//set boardgame
		row.add(new HalfCircleScreen( (HalfCircle) board.getSquare(0)));
		hfCircleAncPane0.getChildren().add(row.get(0));
		for(int i=1; i<=5; i++) {
			row.add(new NormalSquareScreen( (NormalSquare) board.getSquare(i)));
			hBox1.getChildren().add(row.get(i));
		}

		row.add(new HalfCircleScreen( (HalfCircle) board.getSquare(6)));
		hfCircleAncPane6.getChildren().add(row.get(6));
		for(int i=7; i<=11; i++) {
			row.add(new NormalSquareScreen( (NormalSquare) board.getSquare(i)));
			hBox2.getChildren().add(0, row.get(i));
		}	
		//set player "Your turn"'s visible
		newTurn();
		match.getTurn().addListener((observable, oldValue, newValue) -> {
			if(match.getTurn().get()!=0) newTurn();
			else {
				int winner=0;
				if(match.getPlayerPoint(1).get() > match.getPlayerPoint(2).get()) {
					winner = 1;
				}else if(match.getPlayerPoint(1).get() < match.getPlayerPoint(2).get()) {
					winner = 2;
				}else if(match.getPlayerPoint(1).get() == match.getPlayerPoint(2).get()) {
					winner = 0;
				}
				if(winner!=0) winnerLabel.setText("Player " + winner);
				else {}
				winScore1.setText(""+match.getPlayerPoint(1).get());
				winScore2.setText(""+match.getPlayerPoint(2).get());

				playAncPane.setEffect(new GaussianBlur());
				resultAncPane0.setVisible(true);
			}
        });
		
		point1Label.setText(""+match.getPlayerPoint(1).get());
		point2Label.setText(""+match.getPlayerPoint(2).get());
		
		match.getPlayerPoint(1).addListener((observable, oldValue, newValue) -> {
			point1Label.setText(""+match.getPlayerPoint(1).get());
		});
		
		match.getPlayerPoint(2).addListener((observable, oldValue, newValue) -> {
			point2Label.setText(""+match.getPlayerPoint(2).get());
		});
		
		resultAncPane1.setBorder(new Border(new BorderStroke(Color.rgb(102, 66, 40), 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5.0))));
		
		pauseAncPane1.setBorder(new Border(new BorderStroke(Color.rgb(102, 66, 40), 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5.0))));
		
		sPRAncPane.setVisible(false);
	}
	
	public void resetToDefaultSquare() {
		for(int i=0; i < row.size(); i++) {
			if(row.get(i) instanceof NormalSquareScreen) {
				NormalSquareScreen squareScreen = (NormalSquareScreen) row.get(i);
				if( squareScreen.isClicked) {
					squareScreen.resetToDefault();
				}
			}
		}
	}
	
	public void getGemsInSquare(int i) {
		if(row.get(i) instanceof NormalSquareScreen) {
			NormalSquareScreen squareScreen = (NormalSquareScreen) row.get(i);
				squareScreen.getGemsInSquare();
		}
		if(row.get(i) instanceof HalfCircleScreen) {
			HalfCircleScreen squareScreen = (HalfCircleScreen) row.get(i);
				squareScreen.getGemsInSquare();
		}
		//update player point
	}
	public void setTurn(int turn) {
		match.newTurn(turn);
	}
	public void turnAnnounce() {
		
		turnAncPane.setVisible(true);
		TranslateTransition translate = new TranslateTransition();
		translate.setNode(turnAncPane);
		translate.setDuration(Duration.seconds(TURN_APPEAR_TIME));
		translate.setFromX(-600);
		translate.setToX(0);
		translate.play();
		
		TranslateTransition translate2 = new TranslateTransition();
		translate2.setNode(turnAncPane);
		translate2.setDelay(Duration.seconds(TURN_APPEAR_TIME));
		translate2.setDuration(Duration.seconds(TURN_APPEAR_TIME));
		translate2.setFromX(0);
		translate2.setToX(600);
		
		translate.setOnFinished(event -> {
			translate2.play();
		});
		
		translate2.setOnFinished(event -> {
			turnAncPane.setVisible(false);
		});
	}
	
	
	public void newTurn() {
		turnAnnounce();
		if(match.getTurn().get() == 1) {
			player1TurnLabel.setVisible(true);
			player2TurnLabel.setVisible(false);
			turnLabel.setText("PLAYER 1");	
		}  
		else if(match.getTurn().get() == 2) {
			player2TurnLabel.setVisible(true);
			player1TurnLabel.setVisible(false);
			turnLabel.setText("PLAYER 2");		
		}
		
		double waitTime = 0;
		if(match.rowNoGem(match.getTurn().get())) {
			waitTime = SPREAD_TIME*7;
			PauseTransition delay = new PauseTransition(Duration.seconds(TURN_APPEAR_TIME*3));
			delay.setOnFinished(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
		
					spreadRowNoGem(match.getTurn().get());
					
				}
			});
			delay.play();
		}

		PauseTransition delay = new PauseTransition(Duration.seconds(waitTime));
		delay.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				for(int i=1; i<=5; i++) {
					((NormalSquareScreen) row.get(i)).resetCursor();
				}
				for(int i=7; i<=11; i++) {
					((NormalSquareScreen) row.get(i)).resetCursor();
				}
			}
		});
		delay.play();
	}

	public void changeColorWhenSpreadG(int squareId) {
		squareId = convertSquareId(squareId);
		if(squareId == 0 || squareId == 6) {
			((HalfCircleScreen) row.get(squareId)).isSpreaded();
		}else ((NormalSquareScreen) row.get(squareId)).isSpreaded();
		squareId = convertSquareId(squareId - match.getDirection());
		resetToDefaultColor(squareId);
	}
	
	public void resetToDefaultColor(int squareId) {
		if(squareId == 0 || squareId == 6) {
			((HalfCircleScreen) row.get(squareId)).resetToDefault();
		}else ((NormalSquareScreen) row.get(squareId)).resetToDefault();
	}

	public void resetBoardToDefaultColor() {
		for(int i=0; i<=11; i++) {
			if(i == 0 || i == 6) {
				((HalfCircleScreen) row.get(i)).resetToDefault();
			}else ((NormalSquareScreen) row.get(i)).resetToDefault();
		}
	}
	
	public void speardGems() {
		squareId = match.getSquareId();
		
		int direction = match.getDirection();
		
		Timeline spreadGemTimeLine = new Timeline();
		spreadGemTimeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(SPREAD_TIME), (ActionEvent event1) -> {
			squareId += direction;
			squareId=convertSquareId(squareId);
			
			match.spreadGems(squareId);
			
			changeColorWhenSpreadG(squareId);
			if(squareId == 0 || squareId == 6) {
				((HalfCircleScreen) row.get(squareId)).spreadGems();
			}else ((NormalSquareScreen) row.get(squareId)).spreadGems();
		}));
		
		spreadGemTimeLine.setCycleCount(match.getGemInHand());
		spreadGemTimeLine.play();
		
		spreadGemTimeLine.setOnFinished(event -> {
			PauseTransition delay1 = new PauseTransition(Duration.seconds(SPREAD_TIME));
			delay1.setOnFinished(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent event) {
			    	squareId+=direction;
					squareId=convertSquareId(squareId);
					changeColorWhenSpreadG(squareId);
					
			if(match.stopSpreadGem(squareId)==0) {		//stop false
				PauseTransition delay = new PauseTransition(Duration.seconds(SPREAD_TIME));
				delay.setOnFinished(new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent event) {
				    	changeColorWhenSpreadG(squareId);
				       ((NormalSquareScreen) row.get(squareId)).getGemsInSquare();
				    }
				});
				delay.play();
			}
			else if(match.stopSpreadGem(squareId)==1) {//player get point
				PauseTransition delay = new PauseTransition(Duration.seconds(SPREAD_TIME));
				delay.setOnFinished(new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent event) {
				squareId += direction;
				squareId=convertSquareId(squareId);
				changeColorWhenSpreadG(squareId);
				
				match.getPoint(squareId, match.getTurn().get());
				getGemsToPoint(squareId);
				
				getPointLoop();
				
				    }
				});
				delay.play();
			}else { 

				resetToDefaultColor(squareId);
				if(!match.stopMatch()) match.newTurn(); 
				else {
					getScoreInBoard();
				}
			}
			}
			});
			delay1.play();
			
		});
    }
	
	public void getPointLoop() {
		
		PauseTransition delay1 = new PauseTransition(Duration.seconds(SPREAD_TIME));
		delay1.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				int direction = match.getDirection();
				System.out.println("Get loop: " + squareId); 
				
				changeColorWhenSpreadG(squareId);
				squareId+=direction;
				squareId=convertSquareId(squareId);
				changeColorWhenSpreadG(squareId);
				if(board.getSquare(squareId).getPoint()!=0) {
					System.out.println("Stop !=0: " + squareId); 
					resetToDefaultColor(squareId);
					if(!match.stopMatch()) match.newTurn();
					else {
						getScoreInBoard();
					}
				}
	
			PauseTransition delay2 = new PauseTransition(Duration.seconds(SPREAD_TIME));
			delay2.setOnFinished(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					if(board.getSquare(squareId).getPoint()==0) {
				
					squareId+=direction;
					squareId=convertSquareId(squareId);
					changeColorWhenSpreadG(squareId);
						if(board.getSquare(squareId).getPoint()==0)
						{
							System.out.println("Stop ==0 ==0: " + squareId);
							resetToDefaultColor(squareId);
							if(!match.stopMatch()) match.newTurn();
							else {
								getScoreInBoard();
							}
						}else 
						{
							System.out.println("Get: " + squareId);
							match.getPoint(squareId, match.getTurn().get());//player get point
							getGemsToPoint(squareId);
							getPointLoop();
						}
					}
				}
			});
			delay2.play();
			
			}
			
		});
		delay1.play();
		
	}
	
	int iRowNoGem;
	public void spreadRowNoGem(int turn) {
		match.selectDirection(1);
		if(turn == 1) {
			iRowNoGem=1;
		}  
		else if(turn == 2) {
			iRowNoGem=7;
			
		}	
			Timeline spreadGemTimeLine = new Timeline();
			spreadGemTimeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(SPREAD_TIME), (ActionEvent event1) -> {
				match.spreadGems(iRowNoGem);
				((NormalSquareScreen) row.get(iRowNoGem)).spreadGems();
				
				changeColorWhenSpreadG(iRowNoGem);
				iRowNoGem++;
			}));
			spreadGemTimeLine.setCycleCount(5);
			spreadGemTimeLine.play();
			spreadGemTimeLine.setOnFinished(event -> {
				resetToDefaultColor(iRowNoGem-1);
			});
	}

	int playerId=1;
	public void getScoreInBoard() {

			iRowNoGem=1;
			match.selectDirection(1);
			
			Timeline spreadGemTimeLine = new Timeline();
			spreadGemTimeLine.getKeyFrames().add(new KeyFrame(Duration.seconds(SPREAD_TIME), (ActionEvent event1) -> {
				changeColorWhenSpreadG(iRowNoGem);
				match.getPoint(iRowNoGem, playerId);
				((NormalSquareScreen) row.get(iRowNoGem)).getGemsToPoint();
				if(iRowNoGem==5) {
					resetToDefaultColor(iRowNoGem);
					iRowNoGem=7;
					playerId=2;
				}else iRowNoGem++;
				
			}));
			spreadGemTimeLine.setCycleCount(10);
			spreadGemTimeLine.play();
			spreadGemTimeLine.setOnFinished(event -> {
				resetToDefaultColor(iRowNoGem-1);
				PauseTransition delay = new PauseTransition(Duration.seconds(SPREAD_TIME*2));
				delay.setOnFinished(new EventHandler<ActionEvent>() {
				    @Override
				    public void handle(ActionEvent event) {
				    	setTurn(0);
				    }
				});
				delay.play();
			});
			
	}
	public void getGemsToPoint(int squareId) {
		match.getGemsToPoint(squareId);
		if(squareId == 0 || squareId == 6) {
			((HalfCircleScreen) row.get(squareId)).getGemsToPoint();
		}else {
			((NormalSquareScreen) row.get(squareId)).getGemsToPoint();
		}
	}

	public int convertSquareId(int squareId) {
		if(squareId==12) squareId = 0;
		if(squareId==-1)  squareId = 11;
		return squareId;
		
	}
	
    @FXML
    void pauseBackClicked(MouseEvent event) {

    }

    @FXML
    void pauseContinueClicked(MouseEvent event) {
    	playAncPane.setEffect(null);
    	pauseAncPane0.setVisible(false);
    }

    @FXML
    void pauseImageClicked(MouseEvent event) {
    	playAncPane.setEffect(new GaussianBlur());
    	pauseAncPane0.setVisible(true);
    }
}
