package foundationOop.week1;/*
  Cleaned up: 31/08/06 by YG
*/

import java.util.Calendar;

public class Hello {
    public static void main(String[] args) {
	String str = "abc";
	String str2 = "abc";
	char[] chars = {'a','b','c', 'd'};
	String str1 = new String(chars);
	str1 = str1.substring(0,3);
	
	System.out.println("str:" + str);
	System.out.println("str1:" + str1);
	System.out.println(str == str1);
	System.out.println(str == str2);
	
	System.out.println("charAt: " + str.charAt(0));
	
	Calendar c = Calendar.getInstance();
   String s = String.format("Duke's Birthday: %1$tm %1$te,%1$tY", c);
	
		
    }
}
