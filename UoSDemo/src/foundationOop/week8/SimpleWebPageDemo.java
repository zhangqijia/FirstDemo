package foundationOop.week8;

/*
  A sample code for SimpleWebPageDemo
*/

public class SimpleWebPageDemo {
    public static void main(String[] args) {

        // literals for a webpage
        String title = "A life in Sheffield";
        String author = "Tony";
        ADate lastModified = new ADate(30, 11, 2023);

        // create a simple web page
        SimpleWebPage webPage = new SimpleWebPage(title, author, lastModified);

        // show the web page attributes
        System.out.println(webPage);
    }
}