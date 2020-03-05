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
 */
public class AOB {

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
        statisticsResult.add(getNearestStar());
        // sort starList in ascending order of magnitude
        starList.sort(new Comparator<Star>() {
            @Override
            public int compare(Star o1, Star o2) {
                return o1.getMagnitude().compareTo(o2.getMagnitude());
            }
        });
        //Q7. Which is the brightest star?
        //the brighter an object is, the lower its magnitude
        statisticsResult.add(getBrightestStars());
        //Q8. Which is the faintest star?
        //the fainter an object is, the higher its magnitude
        statisticsResult.add(getFaintestStars());
        //save all constellations into map and record the number of their members
        Map<String, Integer> constellationMap = new HashMap<>();
        countMembersOfConstellation(constellationMap);
        //use the number of members as key, copy constellationMap into TreeMap.
        TreeMap<Integer, List<String>> memberCountMap = new TreeMap<>();
        // the constellations have the same number of members will be saved in the same list
        constellationMap.forEach((constellation, memberCount) -> {
            List<String> constellationList = memberCountMap.get(memberCount);
            if (constellationList == null) {
                constellationList = new ArrayList<>();
            }
            constellationList.add(constellation);
            memberCountMap.put(memberCount, constellationList);
        });
        //Q9. How many constellations are there?
        Integer maxMemberCount = memberCountMap.lastKey();
        statisticsResult.add(constellationMap.size() + "");
        //Q10. Which constellation has the largest number of members?
        String q10AnswerStr = getConstellationsWithMaxMembers(memberCountMap.get(maxMemberCount));
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
     * @param maxMemberConstellations the list consists of constellations‘ names which have the most members
     * @return formatted answer of Q10
     */
    private String getConstellationsWithMaxMembers(List<String> maxMemberConstellations) {
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
        messierList.forEach(star -> {
            Integer oldVal = constellationMap.get(star.getConstellation());
            if (oldVal == null) {
                oldVal = 0;
            }
            oldVal++;
            constellationMap.put(star.getConstellation(), oldVal);
        });
    }

    /**
     * @return the faintest Stars' names
     */
    private String getFaintestStars() {
        StringBuilder q8Answer = new StringBuilder();
        List<Star> faintestStars = getElementsWithSamePropertyFromList(starList, "magnitude", false);
        for (Star starCandidate : faintestStars) {
            q8Answer.append(starCandidate.getCatalogueNumber()).append(",");
        }
        return q8Answer.substring(0, q8Answer.length() - 1);
    }

    /**
     * @return the brightest Stars' names
     */
    private String getBrightestStars() {
        StringBuilder q7Answer = new StringBuilder();
        List<Star> brightestStars = getElementsWithSamePropertyFromList(starList, "magnitude", true);
        for (Star starCandidate : brightestStars) {
            q7Answer.append(starCandidate.getCatalogueNumber()).append(",");
        }
        return q7Answer.substring(0, q7Answer.length() - 1);
    }

    /**
     * @return the nearest stars' names
     */
    private String getNearestStar() {
        StringBuilder q6Answer = new StringBuilder();
        List<Star> nearestStars = getElementsWithSamePropertyFromList(starList, "distanceFromEarth", true);
        for (Star starCandidate : nearestStars) {
            q6Answer.append(starCandidate.getCatalogueNumber()).append(",");
        }
        return q6Answer.substring(0, q6Answer.length() - 1);
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
        if (maxDistance.compareTo(maxPlanetDistance) == 0) {
            List<Planet> planetCandidates = getElementsWithSamePropertyFromList(planetList, "distanceFromEarth", false);
            for (Planet starCandidate : planetCandidates) {
                q5Answer.append(starCandidate.getName()).append(",");
            }
        }
        if (maxDistance.compareTo(maxStarDistance) == 0) {
            List<Star> starCandidates = getElementsWithSamePropertyFromList(starList, "distanceFromEarth", false);
            for (Star starCandidate : starCandidates) {
                q5Answer.append(starCandidate.getCatalogueNumber()).append(",");
            }
        }
        if (maxDistance.compareTo(maxMessierDistance) == 0) {
            List<Messier> messierCandidates = getElementsWithSamePropertyFromList(messierList, "distanceFromEarth", false);
            for (Messier starCandidate : messierCandidates) {
                q5Answer.append(starCandidate.getCatalogueNumber()).append(",");
            }
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
            List<Planet> planetCandidates = getElementsWithSamePropertyFromList(planetList, "distanceFromEarth", true);
            for (Planet starCandidate : planetCandidates) {
                q4Answer.append(starCandidate.getName()).append(",");
            }
        }
        if (minDistance.compareTo(minStarDistance) == 0) {
            List<Star> starCandidates = getElementsWithSamePropertyFromList(starList, "distanceFromEarth", true);
            for (Star starCandidate : starCandidates) {
                q4Answer.append(starCandidate.getCatalogueNumber()).append(",");
            }
        }
        if (minDistance.compareTo(minMessierDistance) == 0) {
            List<Messier> messierCandidates = getElementsWithSamePropertyFromList(messierList, "distanceFromEarth", true);
            for (Messier starCandidate : messierCandidates) {
                q4Answer.append(starCandidate.getCatalogueNumber()).append(",");
            }
        }
        return q4Answer.substring(0, q4Answer.length() - 1);
    }

    /**
     * get elements with the same property value from the head or the end of ascending sorted list;
     *
     * @param list         ascending sorted list
     * @param propertyName the property name
     * @param fromStart    if true, this method get elements from start, otherwise from end
     * @return elements have the same property value
     */
    private <T extends AstronomicalObject> List<T> getElementsWithSamePropertyFromList(List<T> list, String propertyName, boolean fromStart) {
        List<T> tList = new ArrayList<>();
        int startIndex;
        // if from the head, use the first element as criterion, otherwise, use the last
        if (fromStart) {
            startIndex = 0;
        } else {
            startIndex = list.size() - 1;
        }
        T t = list.get(startIndex);
        tList.add(t);
        PropertyDescriptor descriptor = null;
        Method readMethod = null;
        Object extremeValue = null;
        T other;
        try {
            //get the read method of specific property
            descriptor = new PropertyDescriptor(propertyName, t.getClass());
            readMethod = descriptor.getReadMethod();
            // get the min/max value of this property
            extremeValue = readMethod.invoke(t);
            // if other objects has the same property, add to return list.
            if (fromStart) {
                for (int i = 1; i < list.size(); i++) {
                    other = list.get(i);
                    Object property = readMethod.invoke(other);
                    if (extremeValue.equals(property)) {
                        tList.add(other);
                    } else {
                        return tList;
                    }
                }
            } else {
                for (int i = list.size() - 2; i >= 0; i--) {
                    other = list.get(i);
                    Object property = readMethod.invoke(other);
                    if (extremeValue.equals(property)) {
                        tList.add(other);
                    } else {
                        return tList;
                    }
                }
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            ExceptionUtil.exceptionExit(e, "failed to compare the value of " + propertyName);
        }
        return tList;
    }

    /**
     * read file into fields
     *
     * @param fileNames filename's array
     */
    private void readFilesToList(String[] fileNames) {
        if (fileNames.length < 3) {
            System.err.println("you should give 3 formatted files");
            System.exit(0);
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
    class DistanceComparator<T extends AstronomicalObject> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.getDistanceFromEarth().compareTo(o2.getDistanceFromEarth());
        }
    }

}
