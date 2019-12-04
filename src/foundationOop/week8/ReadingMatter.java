package foundationOop.week8;

public class ReadingMatter {
    protected int pageCount;
    protected int serialNumber;
    protected String title;
    protected String author;
    protected ADate lastModified;

    public ReadingMatter() {
    }
    public ReadingMatter(String ti, int pc) {
        pageCount = pc;
        title = ti;
    }

    public void setPageCount(int p) {
        pageCount = p;
    }
    public void setSerialNumber(int s) {
        serialNumber = s;
    }
    public void setTitle(String t) {
        title = t;
    }

    public int getPageCount() {
        return pageCount;
    }
    public int getSerialNumber() {
        return serialNumber;
    }
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ADate getLastModified() {
        return lastModified;
    }

    public void setLastModified(ADate lastModified) {
        this.lastModified = lastModified;
    }

    public String toString() {
        return ("Reading matter entitled: " + title +
                " (" + pageCount + " pages).");
    }
}