package foundationOop.week1;

import foundationOop.assignment03.sheffield.EasyReader;

public class StringHandle{
	
	public static void main(String[] args0){
		String response = "m";
        EasyReader keyboard = new EasyReader();
        do {
            int number = keyboard.readInt("Enter a number: ");
            System.out.println("The square of your number is "
                    + number*number);
            response = keyboard.readString("Another try? (y/n) ");
        } while (response.equals("y"));
        System.out.println("Finished");
		
	}
	
	
}