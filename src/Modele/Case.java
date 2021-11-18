package Modele;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Case extends ImageView {
	private int x = 0 ,y = 0,value;
	private boolean c = false;
	
	
	
	public Case(int value){
		this.value = value;
		/*try {
			//this.setImage(new Image(new FileInputStream("images/dame_vide.png"), 50, 50, false, true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	public void OnCliq() {
		this.setRotate(90);
	}
	
	public void OffCliq() {
		this.setRotate(0);
	}
	

	public void UpdateImage(String imageurl){
		Image image = null;
		try {
			image = new Image(new FileInputStream(imageurl), 50, 50, false, true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setImage(image);
	}
	
	public int getA() {return this.x;}
	public int getO() {return this.y;}
	public int getV() {return this.value;}
	public boolean getC() {return this.c;}
	
	public void setA(int x) {this.x = x;}
	public void setO(int y) {this.y = y;}
	public void setC(boolean c) {this.c = c;}
	public void setV(int value) {
		if(value == 1)UpdateImage("images/dame_blanche.jfif");
		//if(value == 0)UpdateImage("images/dame_vide.png");
		if(value == 2)UpdateImage("Images/dame_noir.jfif");
		this.value = value;
	}
	
}
