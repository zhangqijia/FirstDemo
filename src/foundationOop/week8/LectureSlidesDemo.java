package foundationOop.week8;

/*
  Written by: YG
  First written: 15/09/07
  Last modified: 30/11/07
*/

public class LectureSlidesDemo {
    public static void main(String[] args) {

        // store the table of contents
        LectureSlides ls = new LectureSlides("Foundations of OOP", 180, 14);
        ls.addSection(1, "Introduction to Java", 2);
        ls.addSection(2, "Numeric Variables", 2);
        ls.addSection(3, "Input and Output", 4);
        ls.addSection(4, "Characters and Strings", 4);
        ls.addSection(5, "Graphics", 2);
        ls.addSection(6, "Selection", 3);
        ls.addSection(7, "Repetition", 6);
        ls.addSection(8, "Arrays", 5);
        ls.addSection(9, "Classes and Objects", 2);
        ls.addSection(10, "Methods and Parameters", 5);
        ls.addSection(11, "Object Composition", 8);
        ls.addSection(12, "Abstract Data Types", 4);
        ls.addSection(13, "Inheritance and Polymorphism", 20);
        ls.addSection(14, "Abstract Classes and Interfaces", 10);

        // display the contents
        System.out.println(ls);
        ls.printableContents();
    }
}