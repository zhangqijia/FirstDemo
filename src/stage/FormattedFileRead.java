package stage;

import stage.model.AstronomicalObject;

import java.io.*;
import java.util.List;

/**
 * Read formatted Files
 * USER: ZQJ
 * DATE: 2/23/2020
 * TIME: 5:59 PM
 */
public class FormattedFileRead<T extends AstronomicalObject> {

    private String lineSeparator;

    /**
     * file reader
     */
    private BufferedReader fileReader;

    /**
     * @param filename the name of file which you want to read
     */
    public FormattedFileRead(String filename) {
        try {
            fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        } catch (FileNotFoundException e) {
            ExceptionUtil.exceptionExit(e, "Read file failed");
        }
    }

    /**
     * read file's content to a list which stores according objects described by this file
     *
     * @param list  the container list
     * @param clazz the class of the type which is described in this file
     */
    public void readFileToList(List<T> list, Class<T> clazz) {
        int lineNum = 1;
        String line = null;
        try {
            while ((line = fileReader.readLine()) != null) {
                T t = clazz.newInstance();
                // extract properties' array from String
                t.setProperties(getSplit(line));
                list.add(t);
                lineNum++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ExceptionUtil.exceptionExit(e, "please check the content of your file, lineNum: " + lineNum);
        } catch (NumberFormatException | IllegalAccessException e) {
            ExceptionUtil.exceptionExit(e, "read file failed, please check the content of your file, lineNum: " + lineNum);
        } catch (InstantiationException e) {
            ExceptionUtil.exceptionExit(e, "unmatched class type, please check the type of class is according to your file content, lineNum: " + lineNum);
        } catch (IOException e) {
            ExceptionUtil.exceptionExit(e, "read file failed, please check the format of your file, lineNum: " + lineNum);
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                ExceptionUtil.exceptionExit(e, "reader close exception");
            }
        }
    }

    /**
     * analyse String and split it by separator.
     *
     * @param line the String should be splited.
     * @return String array
     */
    private String[] getSplit(String line) {
        if (lineSeparator == null) {
            if (line.contains("|")) {
                lineSeparator = "\\|";
            } else {
                lineSeparator = "\\s+";
            }
        }
        return line.split(lineSeparator);
    }
}
