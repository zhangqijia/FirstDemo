package foundationOop.week8;

public class Book extends ReadingMatter {
    protected String author;
    protected String[] chapterTitles;

    public Book(String t, String a, int numChapters, int p) {
        title = t;
        pageCount = p;
        author = a;
        chapterTitles = new String[numChapters];
        for (int i=0; i<numChapters; i++)
            chapterTitles[i]="not yet known";
    }

    public void addChapter(int c, String t) {
        if (c<=chapterTitles.length)
            chapterTitles[c-1] = t;
    }

    public String toString() {
        return (title + " by " + author + " (" + chapterTitles.length +
                " chapters, " + pageCount + " pages).");
    }
}
