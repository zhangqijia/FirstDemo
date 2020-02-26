package assignment03;

import assignment03.model.AstronomicalObject;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
            System.err.println("Read file failed");
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * read file's content to a list which stores according objects described by this file
     *
     * @param list  the container list
     * @param clazz the class of the type which is described in this file
     */
    public void readFileToList(List<T> list, Class<T> clazz) {
        String line = null;
        try {
            while ((line = fileReader.readLine()) != null) {
                T t = clazz.newInstance();
                // extract properties' array from String
                t.setProperties(getSplit(line));
                list.add(t);
            }
        } catch (IllegalAccessException e) {
            System.err.println("read file failed, please check the content of your file");
            System.err.println(e.getMessage());
            System.exit(0);
        } catch (InstantiationException e) {
            System.err.println("unmatched class type, please check the type of class is according to your file content");
            System.err.println(e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.err.println("read file failed, please check the format of your file");
            System.err.println(e.getMessage());
            System.exit(0);
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
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
                lineSeparator = "\\s";
            }
        }
        return line.split(lineSeparator);
    }



    /*public void readFileToListByProperties(List<T> list, Class<T> clazz) {
        String line = null;
        Field[] fields = clazz.getDeclaredFields();
        PropertyDescriptor[] propertyDescriptors = new PropertyDescriptor[fields.length];
        Method[] methods = new Method[fields.length];

        for (int i = 0; i < fields.length; i++) {
            String name = fields[i].getName();

            PropertyDescriptor propertyDescriptor = null;
            try {
                propertyDescriptor = new PropertyDescriptor(name, clazz);
            } catch (IntrospectionException e) {
                e.printStackTrace();
            }
            Method writeMethod = propertyDescriptor.getWriteMethod();
            methods[i] = writeMethod;
        }

        try {
            while ((line = fileReader.readLine()) != null) {
                T t = clazz.newInstance();
                String[] split = line.split("\\|");
                for (int i = 0; i < split.length; i++) {
                    Class<?>[] parameterTypes = methods[i].getParameterTypes();
                    Object cast = parameterTypes[0].cast(split[i].trim());
                    methods[i].invoke(t, cast);
                }
                list.add(t);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }*/
}
