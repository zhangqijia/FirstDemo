package foundationOop.week8;

import foundationOop.week1.Simple;

public class ReadingMatterDemo {
    public static void main(String[] args) {

        // literals for a newspaper
        String newspaperTitle = "The Daily Planet";
        int newspaperPageCount = 48;
        String headline = "Superman does it again";

        // literals for a book
        String bookTitle = "Core Java";
        String bookAuthor = "Horstmann";
        int numChapters = 15;
        int bookPageCount = 985;

        // literals for a webpage
        String title = "A life in Sheffield";
        String author = "Tony";
        ADate lastModified = new ADate(30, 11, 2023);

//         create a reading list
        ReadingMatter[] readingList
                = {new Newspaper(newspaperTitle, newspaperPageCount, headline),
                new Book(bookTitle, bookAuthor, numChapters, bookPageCount),
                new SimpleWebPage(title, author, lastModified)};

        // display the reading list

        for (ReadingMatter readingMatter : readingList) {
            if (readingMatter instanceof Book)
                System.out.println((Book) readingMatter);
            if (readingMatter instanceof Newspaper)
                System.out.println((Newspaper) readingMatter);
            if (readingMatter instanceof SimpleWebPage)
                System.out.println((SimpleWebPage) readingMatter);
        }

    }
}