package application;
	
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Modele.Case;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application implements Initializable{
	
	@FXML
	private VBox Control;
	@FXML
	private GridPane grid;
	
	private int pnt1 = 0, pnt2 = 0;
	private ImageView p1, p2;
	private Text t1, t2;
	
	private Text text;
	private Font font  = new Font("Arial" , 20);
	
	private boolean player1 = true, coup1 = true, player2 = false, coup2 = true;
	
	private Case[][] map = new Case[10][10];

	
	private Case temp;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			text = new Text("");
			text.setFont(font);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vue/Welcome.fxml"));
			loader.setController(this);
			Pane root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//scene.getStylesheets().add(getClass().getResource("../Css/application.css").toExternalForm());
			primaryStage.setScene(new Scene(root, 600, 500));
			primaryStage.setTitle("Dame Game");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		InitGame();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	void InitGame() {
		PntGestion();
		Control.setAlignment(Pos.CENTER);
		Control.setSpacing(40);
		text.setText("Pls 1 .");
		grid.setAlignment(Pos.CENTER);
		grid.getStylesheets().add(getClass().getResource("../Css/application.css").toExternalForm());
		grid.setId("grid");
		int i = 0, j = 0;
		for(i = 0 ; i < 10 ; i++) {
			for(j = 0 ; j < 10 ; j++) {
				Case a = new Case(0);
				a.setA(i);
				a.setO(j);
				a.setOnMouseClicked(new Game());
				grid.add(a, i, j);
				map[i][j] = a;
				if(((i+j) % 2) != 0)a.UpdateImage("images/dame_vide.jfif");
				else {
					a.UpdateImage("images/dame_vide.png");
					a.setV(5);
				}
			}
		}
		InitMap();
	}
	
	void add1() {
		pnt1++;
		t1.setText(String.valueOf(pnt1));
	}
	
	void add2() {
		pnt2++;
		t2.setText(String.valueOf(pnt2));
	}

	void M1() {
		p1.setScaleX(2.5);
	}
	
	void M2() {
		p2.setScaleX(2.5);
	}
	
	void m1() {
		p1.setScaleX(1);
	}
	
	void m2() {
		p2.setScaleX(1);
	}
	
	
	void PntGestion() {
		p1 = new ImageView();
		p2 = new ImageView();
		try {
			p1.setImage(new Image(new FileInputStream("images/dame_blanche.jfif"), 50, 50 , false , true));
			p1.setRotate(90);
			p2.setImage(new Image(new FileInputStream("images/dame_noir.jfif"), 50 , 50, false, true));
			p2.setRotate(90);
			t1 = new Text(String.valueOf(pnt1));
			t2 = new Text(String.valueOf(pnt2));
			t1.setFont(font);
			t2.setFont(font);
			Control.getChildren().addAll(p1,t1,t2,p2);
			M1();
		} catch (FileNotFoundException e) {}
	}
	
	void InitMap() {
		map[1][0].setV(1);
		map[3][0].setV(1);
		map[5][0].setV(1);
		map[7][0].setV(1);
		map[9][0].setV(1);
		
		map[0][1].setV(1);
		map[2][1].setV(1);
		map[4][1].setV(1);
		map[6][1].setV(1);
		map[8][1].setV(1);
		
		map[1][2].setV(1);
		map[3][2].setV(1);
		map[5][2].setV(1);
		map[7][2].setV(1);
		map[9][2].setV(1);
		
		map[0][9].setV(2);
		map[2][9].setV(2);
		map[4][9].setV(2);
		map[6][9].setV(2);
		map[8][9].setV(2);
		
		map[1][8].setV(2);
		map[3][8].setV(2);
		map[5][8].setV(2);
		map[7][8].setV(2);
		map[9][8].setV(2);
		
		map[0][7].setV(2);
		map[2][7].setV(2);
		map[4][7].setV(2);
		map[6][7].setV(2);
		map[8][7].setV(2);
	}
	
	boolean Move1(Case temp, Case a) {
		System.out.println(a.getV());
		if(temp.getO() < a.getO() && temp.getO() - a.getO() == -1 && Math.abs(temp.getA() - a.getA()) == 1 && a.getV() == 0) {
			System.out.println("kk");
			temp.UpdateImage("images/dame_vide.jfif");
			a.UpdateImage("images/dame_blanche.jfif");	
			temp.setV(0);
			a.setV(1);
			return true;
		}else if(Math.abs(temp.getO() - a.getO()) == 2 && Math.abs(temp.getA() - a.getA()) == 2 && a.getV() == 0) {
			if(temp.getO() < a.getO() && temp.getA() < a.getA() && map[temp.getA() + 1][temp.getO() + 1].getV() == 2) {
				map[temp.getA() + 1][temp.getO() + 1].UpdateImage("images/dame_vide.jfif");
				map[temp.getA() + 1][temp.getO() + 1].setV(0);
				temp.UpdateImage("images/dame_vide.jfif");
				a.UpdateImage("images/dame_blanche.jfif");	
				temp.setV(0);
				a.setV(1);
				add1();
				return true;
			}else if(temp.getO() > a.getO() && temp.getA() > a.getA() && map[temp.getA() - 1][temp.getO() - 1].getV() == 2) {
				map[temp.getA() - 1][temp.getO() - 1].UpdateImage("images/dame_vide.jfif");
				map[temp.getA() - 1][temp.getO() - 1].setV(0);
				temp.UpdateImage("images/dame_vide.jfif");
				a.UpdateImage("images/dame_blanche.jfif");
				temp.setV(0);
				a.setV(1);
				add1();
				return true;
			}else if(temp.getO() > a.getO() && temp.getA() < a.getA() && map[temp.getA() + 1][temp.getO() - 1].getV() == 2) {
				map[temp.getA() + 1][temp.getO() - 1].UpdateImage("images/dame_vide.jfif");
				map[temp.getA() + 1][temp.getO() - 1].setV(0);
				temp.UpdateImage("images/dame_vide.jfif");
				a.UpdateImage("images/dame_blanche.jfif");
				temp.setV(0);
				a.setV(1);
				add1();
				return true;
			}else if(temp.getO() < a.getO() && temp.getA() > a.getA() && map[temp.getA() - 1][temp.getO() + 1].getV() == 2) {
				map[temp.getA() - 1][temp.getO() + 1].UpdateImage("images/dame_vide.jfif");
				map[temp.getA() - 1][temp.getO() + 1].setV(0);
				temp.UpdateImage("images/dame_vide.jfif");
				a.UpdateImage("images/dame_blanche.jfif");		
				temp.setV(0);
				a.setV(1);
				add1();
				return true;
			}
		}
		return false;
	}
	
	boolean Move2(Case temp, Case a) {
		if(temp.getO() > a.getO() && temp.getO() - a.getO() == 1 && Math.abs(temp.getA() - a.getA()) == 1 && a.getV() == 0) {
			temp.UpdateImage("images/dame_vide.jfif");
			a.UpdateImage("images/dame_noir.jfif");	
			temp.setV(0);
			a.setV(2);
			return true;
		}else if(Math.abs(temp.getO() - a.getO()) == 2 && Math.abs(temp.getA() - a.getA()) == 2 && a.getV() == 0) {
			if(temp.getO() < a.getO() && temp.getA() < a.getA() && map[temp.getA() + 1][temp.getO() + 1].getV() == 1) {
				map[temp.getA() + 1][temp.getO() + 1].UpdateImage("images/dame_vide.jfif");
				map[temp.getA() + 1][temp.getO() + 1].setV(0);
				temp.UpdateImage("images/dame_vide.jfif");
				a.UpdateImage("images/dame_blanche.jfif");	
				temp.setV(0);
				a.setV(2);
				add2();
				return true;
			}else if(temp.getO() > a.getO() && temp.getA() > a.getA() && map[temp.getA() - 1][temp.getO() - 1].getV() == 1) {
				map[temp.getA() - 1][temp.getO() - 1].UpdateImage("images/dame_vide.jfif");
				map[temp.getA() - 1][temp.getO() - 1].setV(0);
				temp.UpdateImage("images/dame_vide.jfif");
				a.UpdateImage("images/dame_blanche.jfif");
				temp.setV(0);
				a.setV(2);
				add2();
				return true;
			}else if(temp.getO() > a.getO() && temp.getA() < a.getA() && map[temp.getA() + 1][temp.getO() - 1].getV() == 1) {
				map[temp.getA() + 1][temp.getO() - 1].UpdateImage("images/dame_vide.jfif");
				map[temp.getA() + 1][temp.getO() - 1].setV(0);
				temp.UpdateImage("images/dame_vide.jfif");
				a.UpdateImage("images/dame_blanche.jfif");	
				temp.setV(0);
				a.setV(2);
				add2();
				return true;
			}else if(temp.getO() < a.getO() && temp.getA() > a.getA() && map[temp.getA() - 1][temp.getO() + 1].getV() == 1) {
				map[temp.getA() - 1][temp.getO() + 1].UpdateImage("images/dame_vide.jfif");
				map[temp.getA() - 1][temp.getO() + 1].setV(0);
				temp.UpdateImage("images/dame_vide.jfif");
				a.UpdateImage("images/dame_blanche.jfif");	
				temp.setV(0);
				a.setV(2);
				add2();
				return true;
			}
		}
		return false;
	}
	
	public class Game implements EventHandler{
		@Override
		public void handle(Event event) {
			Case a = (Case)event.getSource();
			int x = a.getA(), y = a.getO(),v = a.getV();
			
			if(player1) {
				
				if(coup1 && v == 1) {
					temp = a;
					coup1 = false;
					temp.OnCliq(); 
				}else if (!coup1 && v != 1 && v != 2) {
					if(Move1(temp, a)) {
						player1 = false;
						coup1 = true;
						player2 = true;
						coup2 = true;
						temp.OffCliq();
						m1();
						M2();
					}else {
						temp.OffCliq();
						coup1 = true;
					}
				}else if(!coup1) {
					temp.OffCliq();
					coup1 = true;
				}
				
			}else if(player2) {
				
				if(coup2 && v == 2) {
					temp = a;
					coup2 = false;
					temp.OnCliq(); 
				}else if(!coup2 && v != 1 && v != 2) {
					if(Move2(temp, a)) {
						player2 = false;
						coup2 = true;
						player1 = true;
						coup1 = true;
						temp.OffCliq();
						m2();
						M1();
					}else{
						temp.OffCliq();
						coup2 = true;
					}
				}else if(!coup2) {
					temp.OffCliq();
					coup2 = true;
				}
				
			}
		}
		
	}
	
}
