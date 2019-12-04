package foundationOop.week8;

public class SimpleWebPage extends ReadingMatter {

    public SimpleWebPage(String title, String author, ADate date) {
        super.title = title;
        super.author = author;
        super.lastModified = date;
    }

    @Override
    public String toString() {
        return String.format("\"%s\" by %s, last modified (%s, %s, %s)", title, author, lastModified.getDay(),
                lastModified.getMonth(), lastModified.getYear());
    }
}
