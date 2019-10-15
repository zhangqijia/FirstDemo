package foundationOop.week1;

import sheffield.*;

public class CorpTwoArea{
	
	/*
	that reads a
temperature in Fahrenheit (F) from a file fahrenheit.txt (on MOLE), calculates the equivalent temperature in Celsiu
s (C), and finally writes the result to a file celsius.txt. The formula for the conversion is
C = (F − 32)× 5/9. The program should display the temperature in Celsius to the accuracy of 4 decimal
places.

	*/

public static void main(String[] args){
	EasyReader keyboard = new EasyReader("foundationOop/week1/fahrenheit.txt");
	double fahrenheit = keyboard.readDouble();
	double celsius = (fahrenheit-32) * 5/9;
	EasyWriter writer = new EasyWriter("foundationOop/week1/celsius.txt");
	writer.println(celsius, 4, 10);
	
}

}