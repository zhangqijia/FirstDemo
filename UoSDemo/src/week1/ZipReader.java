package week1;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipReader {

    private ZipFile zipFile;
    private BufferedReader reader = null;

    public ZipReader(String zipPath) {
        try {
            zipFile = new ZipFile(zipPath);
        } catch (IOException e) {
            System.err.println("find not exist");
            System.exit(0);
        }
    }

    public ZipEntry getEntryInZip(String entryName) {
        return zipFile.getEntry(entryName);
    }

    public BufferedReader getEntryBufferedReader(ZipEntry entry) {
        try {
            reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reader;
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void statisticCount(BufferedReader reader) {
        String line;
        int lineCount = 0;
        int characterCount = 0;
        int wordCount = 0;
        String[] split;
        try {
            while (((line = reader.readLine()) != null)) {
                lineCount++;
                characterCount += line.length();
                split = line.split("\\b|\\n|\\r|\\t");
                wordCount += split.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("line count: " + lineCount);
        System.out.println("word count: " + wordCount);
        System.out.println("character count: " + characterCount);
    }

    public void copyAndToLowercase(BufferedReader reader) {
        PrintWriter bufferedWriter = null;
        String line;
        String lowercase;
        try {
            bufferedWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream("lowerCase.txt")));
            while (((line = reader.readLine()) != null)) {
                lowercase = line.toLowerCase();
                bufferedWriter.println(lowercase);
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

//            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("code4LabsLectures.zip"));
//            ZipFile zipFile = new ZipFile("code4LabsLectures.zip");
//            ZipEntry entry = zipFile.getEntry("labCode_doNotChange/gooseberries.txt");
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(entry)));
        ZipReader zipReader = new ZipReader(args[0]);
        ZipEntry entryInZip = zipReader.getEntryInZip(args[1]);
        BufferedReader reader = zipReader.getEntryBufferedReader(entryInZip);
//        zipReader.statisticCount(reader);
        zipReader.copyAndToLowercase(reader);
        zipReader.close();
    }
}
