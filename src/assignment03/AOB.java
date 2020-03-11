package assignment03;

import assignment03.model.AstronomicalObject;
import assignment03.model.Messier;
import assignment03.model.Planet;
import assignment03.model.Star;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * Reading file and store data.
 *
 * @author ZQJ
 */
public class AOB {

    public static final String DISTANCE_FROM_EARTH = "distanceFromEarth";
    public static final String MAGNITUDE = "magnitude";

    /**
     * all stars
     */
    private List<Star> starList = new ArrayList<>();
    /**
     * all planets
     */
    private List<Planet> planetList = new ArrayList<>();
    /**
     * all messier objects
     */
    private List<Messier> messierList = new ArrayList<>();
    /**
     * statistics results, the sequence is corresponding to questions sequence
     */
    private List<String> statisticsResult = new ArrayList<>();

    /**
     * Read starts, messier, planet and print statistics info
     *
     * @param args file name list
     */
    public static void main(String[] args) {
        AOB aob = new AOB();
        // read all files into corresponding lists
        aob.readFilesToList(args);
        // get statistics info and save into instance field statisticsResult
        aob.getSummaryStatistics();
        // printout answers
        System.out.println(aob);
    }

    /**
     * Answer Q1~Q10 and save answers into statistics result.
     */
    public void getSummaryStatistics() {
        //Q1: how many planets are there?
        int planetCount = planetList.size();
        statisticsResult.add(String.valueOf(planetCount));
        //Q2: how many Messier objects are there?
        int messierCount = messierList.size();
        statisticsResult.add(String.valueOf(messierCount));
        //Q3: how many starts are there?
        int starCount = starList.size();
        statisticsResult.add(String.valueOf(starCount));
        //sort all object lists in ascending order of distance
        starList.sort(new DistanceComparator<>());
        messierList.sort(new DistanceComparator<>());
        planetList.sort(new DistanceComparator<>());
        //Q4. Which object is nearest to the Earth?
        statisticsResult.add(getNearestObjects());
        //Q5. Which object is furthest from the Earth?
        statisticsResult.add(getFurthestObjects());
        //Q6. Which is the nearest star?
        statisticsResult.add(getElementsWithSamePropertyFromList(starList, DISTANCE_FROM_EARTH, true));
        // sort starList in ascending order of magnitude
        starList.sort(Comparator.comparing(AstronomicalObject::getMagnitude));
        //Q7. Which is the brightest star?
        //the brighter an object is, the lower its magnitude
        statisticsResult.add(getElementsWithSamePropertyFromList(starList, MAGNITUDE, true));
        //Q8. Which is the faintest star?
        //the fainter an object is, the higher its magnitude
        statisticsResult.add(getElementsWithSamePropertyFromList(starList, MAGNITUDE, false));
        //save all constellations into map and record the number of their members
        Map<String, Integer> constellationMap = new HashMap<>(starList.size() >>> 2);
        countMembersOfConstellation(constellationMap);
        //Q9. How many constellations are there?
        statisticsResult.add(constellationMap.size() + "");
        //Q10. Which constellation has the largest number of members?
        String q10AnswerStr = getConstellationsWithMaxMembers(constellationMap);
        statisticsResult.add(q10AnswerStr);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < statisticsResult.size(); i++) {
            stringBuilder.append("Q")
                    .append(i + 1)
                    .append(". ")
                    .append(statisticsResult.get(i))
                    .append(" ");
        }
        return stringBuilder.toString();
    }

    /**
     * get constellations which have the most members
     *
     * @param map the map of Constellations' statistics information
     * @return formatted answer of Q10
     */
    private String getConstellationsWithMaxMembers(Map<String, Integer> map) {
        ArrayList<String> maxMemberConstellations = new ArrayList<>();
        int max = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            // find a bigger number, clear the List and update max
            if (max < entry.getValue()) {
                max = entry.getValue();
                maxMemberConstellations.clear();
                maxMemberConstellations.add(entry.getKey());
            } else if (max == entry.getValue()) {
                maxMemberConstellations.add(entry.getKey());
            }
        }
        StringBuilder q10Answer = new StringBuilder();
        for (String str : maxMemberConstellations) {
            q10Answer.append(str).append(",");
        }
        return q10Answer.substring(0, q10Answer.length() - 1);
    }

    /**
     * save all constellations and the number of their members into map
     *
     * @param constellationMap the container
     */
    private void countMembersOfConstellation(Map<String, Integer> constellationMap) {
        starList.forEach(star -> {
            Integer oldVal = constellationMap.get(star.getConstellation());
            if (oldVal == null) {
                oldVal = 0;
            }
            oldVal++;
            constellationMap.put(star.getConstellation(), oldVal);
        });
        messierList.forEach(messier -> {
            Integer oldVal = constellationMap.get(messier.getConstellation());
            if (oldVal == null) {
                oldVal = 0;
            }
            oldVal++;
            constellationMap.put(messier.getConstellation(), oldVal);
        });
    }

    /**
     * @return the furthest objects' name
     */
    private String getFurthestObjects() {
        StringBuilder q5Answer = new StringBuilder();
        // get the nearest object from different types of object list separately.
        BigDecimal maxStarDistance = starList.get(starList.size() - 1).getDistanceFromEarth();
        BigDecimal maxMessierDistance = messierList.get(messierList.size() - 1).getDistanceFromEarth();
        BigDecimal maxPlanetDistance = planetList.get(planetList.size() - 1).getDistanceFromEarth();
        // find the nearest distance among three types of objects
        BigDecimal maxDistance = maxStarDistance.max(maxPlanetDistance.max(maxMessierDistance));
        // check whether other objects have the same max distance exist.
        // Use compareTo() because equal() considers two BigDecimal objects equal only if they are equal in value and scale, 2.0 2.00
        if (maxDistance.compareTo(maxPlanetDistance) == 0) {
            q5Answer.append(getElementsWithSamePropertyFromList(planetList, DISTANCE_FROM_EARTH, false)).append(",");
        }
        if (maxDistance.compareTo(maxStarDistance) == 0) {
            q5Answer.append(getElementsWithSamePropertyFromList(starList, DISTANCE_FROM_EARTH, false)).append(",");
        }
        if (maxDistance.compareTo(maxMessierDistance) == 0) {
            q5Answer.append(getElementsWithSamePropertyFromList(messierList, DISTANCE_FROM_EARTH, false)).append(",");
        }
        return q5Answer.substring(0, q5Answer.length() - 1);
    }

    /**
     * get nearest to Earth objects
     *
     * @return result String consists of the objects' names
     */
    private String getNearestObjects() {
        StringBuilder q4Answer = new StringBuilder();
        // get the nearest object from different types of object list separately.
        BigDecimal minStarDistance = starList.get(0).getDistanceFromEarth();
        BigDecimal minMessierDistance = messierList.get(0).getDistanceFromEarth();
        BigDecimal minPlanetDistance = planetList.get(0).getDistanceFromEarth();
        // find the nearest distance among three types of objects
        BigDecimal minDistance = minStarDistance.min(minPlanetDistance.min(minMessierDistance));
        // check whether other objects have the same min distance exist.
        if (minDistance.compareTo(minPlanetDistance) == 0) {
            q4Answer.append(getElementsWithSamePropertyFromList(planetList, DISTANCE_FROM_EARTH, true)).append(",");
        }
        if (minDistance.compareTo(minStarDistance) == 0) {
            q4Answer.append(getElementsWithSamePropertyFromList(starList, DISTANCE_FROM_EARTH, true)).append(",");
        }
        if (minDistance.compareTo(minMessierDistance) == 0) {
            q4Answer.append(getElementsWithSamePropertyFromList(messierList, DISTANCE_FROM_EARTH, true)).append(",");
        }
        return q4Answer.substring(0, q4Answer.length() - 1);
    }

    /**
     * get elements with the same property value from the head or the end of ascending sorted list;
     *
     * @param list         ascending sorted list
     * @param propertyName the property name
     * @param fromStart    if true, this method get elements from start, otherwise from end
     * @return String consists of names or CatalogueNumbers of elements which have the same value for assigned property
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private <T extends AstronomicalObject> String getElementsWithSamePropertyFromList(List<T> list, String propertyName, boolean fromStart) {
        List<T> answerList = new ArrayList<>();
        int startIndex;
        // if from the head, use the first element's property value as criterion, otherwise, use the last
        if (fromStart) {
            startIndex = 0;
        } else {
            startIndex = list.size() - 1;
        }
        T t = list.get(startIndex);
        answerList.add(t);
        PropertyDescriptor descriptor = null;
        Method readMethod = null;
        Comparable extremeValue = null;
        T other;
        try {
            //get the read method of specific property
            descriptor = new PropertyDescriptor(propertyName, t.getClass());
            readMethod = descriptor.getReadMethod();
            // get the min/max value of this property (first/last element's property value)
            extremeValue = (Comparable) readMethod.invoke(t);
            // if other objects has the same property, add to answer list.
            if (fromStart) {
                for (int i = 1; i < list.size(); i++) {
                    other = list.get(i);
                    Object property = readMethod.invoke(other);
                    if (extremeValue.compareTo(property) == 0) {
                        answerList.add(other);
                    } else {
                        // find different value, stop loop;
                        break;
                    }
                }
            } else {
                for (int i = list.size() - 2; i >= 0; i--) {
                    other = list.get(i);
                    Object property = readMethod.invoke(other);
                    if (extremeValue.compareTo(property) == 0) {
                        answerList.add(other);
                    } else {
                        break;
                    }
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            ExceptionUtil.exceptionExit(e, "failed to compare the value of:" + propertyName);
        } catch (ClassCastException e) {
            ExceptionUtil.exceptionExit(e, "this property cannot be compared:" + propertyName);
        }
        // format answerList to String
        StringBuilder stringBuilder = new StringBuilder();
        for (T starCandidate : answerList) {
            stringBuilder.append(starCandidate.getCatalogueNumberOrName()).append(",");
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    /**
     * read file into fields
     *
     * @param fileNames filename's array
     */
    private void readFilesToList(String[] fileNames) {
        if (fileNames.length < 3) {
            ExceptionUtil.exceptionExit(new Exception("ArgumentsException"), "you should give 3 formatted files");
        }
        // create FileReaderTool instances and read files into different lists according to astronomical type.
        FormattedFileRead<Star> starFileRead = new FormattedFileRead<>(fileNames[0]);
        FormattedFileRead<Messier> messierFileRead = new FormattedFileRead<>(fileNames[1]);
        FormattedFileRead<Planet> planetFileRead = new FormattedFileRead<>(fileNames[2]);
        starFileRead.readFileToList(starList, Star.class);
        messierFileRead.readFileToList(messierList, Messier.class);
        planetFileRead.readFileToList(planetList, Planet.class);
    }

    /**
     * the Distance from Earth comparator for AstronomicalObject
     *
     * @param <T>
     */
    static class DistanceComparator<T extends AstronomicalObject> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.getDistanceFromEarth().compareTo(o2.getDistanceFromEarth());
        }
    }
}
