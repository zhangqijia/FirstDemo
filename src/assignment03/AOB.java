package assignment03;

import assignment03.model.Messier;
import assignment03.model.Planet;
import assignment03.model.Star;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Reading file and store data.
 */
public class AOB<K, V> implements Map<K, V> {

    private List<Star> starList = new ArrayList<>();
    private List<Planet> planetList = new ArrayList<>();
    private List<Messier> messierList = new ArrayList<>();


    /**
     * starts, messier, planet
     *
     * @param args file name list
     */
    public static void main(String[] args) {
        AOB<String, String> aob = new AOB<String, String>();
        aob.readFilesToList(args);

    }

    public void readFilesToList(String[] fileNames) {
        if (fileNames.length < 3) {
            System.err.println("you should give 3 formatted files");
            System.exit(0);
        }
        FormattedFileRead<Star> starFileRead = new FormattedFileRead<>(fileNames[0]);
        FormattedFileRead<Messier> messierFileRead = new FormattedFileRead<>(fileNames[1]);
        FormattedFileRead<Planet> planetFileRead = new FormattedFileRead<>(fileNames[2]);
        starFileRead.readFileToList(starList, Star.class);
        messierFileRead.readFileToList(messierList, Messier.class);
        planetFileRead.readFileToList(planetList, Planet.class);
        System.out.println(starList);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
