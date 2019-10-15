package foundationOop.week1;

import sheffield.*;

public class SimpleSquare{
	
	public static void main(String[] args0){
	
	//create instances
	EasyReader read = new EasyReader();
	// read the length of window
	read.readInt("please input the length of window");
	EasyGraphics g  = new EasyGraphics(500,500);
	
	
	int length = read.readInt("please input the length");
	
	//draw graphics
	g.moveTo(250-length/2, 250-length/2);
	g.lineTo(250-length/2, 250+length/2);
	g.lineTo(250+length/2, 250+length/2);
	g.lineTo(250+length/2, 250-length/2);
	g.lineTo(250-length/2, 250-length/2);
	
	
	
		
	}
	
	
}