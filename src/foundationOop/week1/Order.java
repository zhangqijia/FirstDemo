package foundationOop.week1;

import java.util.Scanner;

public class OrderString {
	
	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("type in some words OK? please split words use whitespace");
		String nextLine = scanner.nextLine();
		
		String[] split = nextLine.split(" ");
		String ex;
		
		for(int i =0 ; i< split.length-2 ;i++) {
			for(int j =1 ; j< split.length-1 ;j++) {
				char charAtI = split[i].charAt(0);
				char charAtJ = split[j].charAt(0);
				if(charAtI > charAtJ) {
					ex = split[i];
					split[i] = split[j];
					split[j]=ex;
				}
			}	
		}
		
		for(String s : split) {
			System.out.println((byte)s.charAt(0));
		}
		
		
	}

}
