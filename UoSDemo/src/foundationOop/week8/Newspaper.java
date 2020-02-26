package foundationOop.week8;

/*
  Cleaned up: 31/08/06 by YG
*/

public class Newspaper extends ReadingMatter {
    protected String headline;

    public Newspaper(String t, int p, String h) {
        title = t;
        pageCount = p;
        headline = h;
    }

    public void setHeadline(String s) {
        headline = s;
    }
    public String getHeadline() {
        return headline;
    }

    public String toString() {
        return ("Edition of " + title + " with headline \"" +
                headline + "\" (" + pageCount + " pages).");
    }
}