package foundationOop.week8;

public class LectureSlides extends ReadingMatter {

    // variables
    String[] sectionTitles;
    int[] numOfPage;
    int[] numOfSection;
    int count = 0;

    private static final int MAX = 20;

    // CODE MISSING


    public LectureSlides() {
        sectionTitles = new String[MAX];
        numOfPage = new int[MAX];
        numOfSection = new int[MAX];
    }


    public LectureSlides(String title, int page, int serialNumber) {
        super.title = title;
        super.pageCount = page;
        super.serialNumber = serialNumber;
        sectionTitles = new String[MAX];
        numOfPage = new int[MAX];
        numOfSection = new int[MAX];
    }

    public void addSection(int section, String title, int page) {
        if (count < MAX) {
            this.numOfSection[count] = section;
            this.numOfPage[count] = page;
            this.sectionTitles[count] = title;
            count++;
        } else {
            System.out.println(" the size of slides is up to limit");
        }
    }

    public void printableContents() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title :").append(super.title)
                .append("\t pageCount :").append(super.pageCount)
                .append("\t serialNumber :").append(serialNumber).append("\n");
        System.out.println(sb);

        for (int i = 0; i < count; i++) {
            String format = String.format("%s%-25s %s%-5d%s%-5d ", "SectionTitles:", sectionTitles[i],
                    "page:", numOfPage[i],
                    "section:", numOfSection[i]);
            System.out.println(format);
        }
    }

}