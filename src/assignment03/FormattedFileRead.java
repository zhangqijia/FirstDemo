package assignment03;

<<<<<<< HEAD:src/assignment03/FormattedFileRead.java
import assignment03.model.AstronomicalObject;
=======
import assignment03.function.SetProperties;
>>>>>>> 666736168789bb5e6d8d0ebe38c2caa015e42138:UoSDemo/src/assignment03/FormattedFileRead.java

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
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
<<<<<<< HEAD:src/assignment03/FormattedFileRead.java
     * read file's content to a list which stores according objects described by this file
     *
     * @param list  the container list
     * @param clazz the class of the type which is described in this file
=======
     * read file info to a T's list, T is the type of a specific class
     *
     * @param list
     * @param clazz
>>>>>>> 666736168789bb5e6d8d0ebe38c2caa015e42138:UoSDemo/src/assignment03/FormattedFileRead.java
     */
    public void readFileToList(List<T> list, Class<T> clazz) {
        String line = null;
        try {
            while ((line = fileReader.readLine()) != null) {
                T t = clazz.newInstance();
                t.setProperties(line);
                list.add(t);
            }
        } catch (IllegalAccessException e) {
            System.err.println("read file failed, please check the content of your file");
            System.exit(0);
        } catch (InstantiationException e) {
            System.err.println("unmatched class type, please check the type of class is according to your file content");
            System.exit(0);
        } catch (IOException e) {
            System.err.println("read file failed, please check the format of your file");
            System.exit(0);
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

<<<<<<< HEAD:src/assignment03/FormattedFileRead.java
    /*public void readFileToListByProperties(List<T> list, Class<T> clazz) {
=======
    /**
     * read file info to a T's list
     *
     * @param list
     * @param clazz
     */
    public void readFileToListByProperties(List<T> list, Class<T> clazz) {
>>>>>>> 666736168789bb5e6d8d0ebe38c2caa015e42138:UoSDemo/src/assignment03/FormattedFileRead.java
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
