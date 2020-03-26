package stage.query;

import stage.AOB;
import stage.exception.SyntaxIllegalException;
import stage.model.Messier;
import stage.model.Planet;
import stage.model.Star;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZQJ
 * @date 3/20/2020
 */
public class QueryRunner {

    public static final String SELECT = "select";
    public static final String WHERE = "where";

    /**
     * execute one query sentence
     *
     * @param queryStr query sentence
     * @param aob      aob object which contains meta list
     * @return the result which contains a answer list
     */
    public static QueryResults run(String queryStr, AOB aob) throws IllegalAccessException, IntrospectionException, InvocationTargetException, SyntaxIllegalException {
        QueryResults queryResults = null;
        ArrayList<Criterion> queries = new ArrayList<>();
        String[] strArray = queryStr.split("\\s+");
        // verify key words in query sentence
        if (!SELECT.equals(strArray[0])) {
            throw new SyntaxIllegalException(queryStr);
        }
        //get object type
        String objectName = strArray[1];
        if (ConstantMap.getProperty(objectName) == null) {
            throw new SyntaxIllegalException(queryStr);
        }
        int strArrayLength = strArray.length;
        // check the length of query sentence and the number of 'and'
        if (strArrayLength > 6) {
            if ((strArrayLength - 6) % 4 != 0) {
                throw new SyntaxIllegalException(queryStr);
            }
            for (int i = 6; i < strArrayLength; i += 4) {
                if (!"and".equals(strArray[i])) {
                    throw new SyntaxIllegalException(queryStr);
                }
            }
        }
        // check and extract criteria
        if (strArrayLength > 2) {
            if (!WHERE.equals(strArray[2])) {
                throw new SyntaxIllegalException(queryStr);
            }
            // check criterion (key and symbol) by pre-defined data table
            for (int i = 3; i < strArrayLength; i += 4) {
                String property = ConstantMap.getProperty(objectName, strArray[i]);
                String symbol = ConstantMap.getSymbol(strArray[i + 1]);
                if (property != null && symbol != null) {
                    queries.add(new Criterion(property, symbol, strArray[i + 2]));
                } else {
                    throw new SyntaxIllegalException(queryStr);
                }
            }
        }
        // execute query
        switch (objectName) {
            case "stars":
                List<Star> starList = aob.getStarList();
                queryResults = invokeQuery(starList, queries, Star.class);

                break;
            case "messiers":
                List<Messier> messierList = aob.getMessierList();
                queryResults = invokeQuery(messierList, queries, Messier.class);
                break;
            case "planets":
                List<Planet> planetList = aob.getPlanetList();
                queryResults = invokeQuery(planetList, queries, Planet.class);
                break;
            default:
                break;
        }
        return queryResults;
    }

    /**
     * execute query
     *
     * @param objectList the meta data list
     * @param queries    the criteria
     * @param tClass     the Object's Class which is being queried
     * @param <T>        the type of list element
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T> QueryResults invokeQuery(List<T> objectList, ArrayList<Criterion> queries, Class<?> tClass) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor propertyDescriptor;
        // use every query to filter resultList
        for (Criterion query : queries) {
            // based on key name, get the value of this property and compare with criterion
            propertyDescriptor = new PropertyDescriptor(query.getKey(), tClass);
            Method readMethod = propertyDescriptor.getReadMethod();
            List<T> resultList = new ArrayList<>();
            String originalStr;
            String queryStr;
            BigDecimal originalNum;
            BigDecimal queryNum;
            int compareResult;
            // compare every element in objectList and store qualified elements
            for (T t : objectList) {
                Object propertyValue = readMethod.invoke(t);
                if (propertyValue instanceof String) {
                    originalStr = (String) propertyValue;
                    queryStr = query.getValue();
                    compareResult = originalStr.compareTo(queryStr);
                    if (checkQueryCriterion(compareResult, query.getSymbol())) {
                        resultList.add(t);
                    }
                } else if (propertyValue instanceof BigDecimal) {
                    originalNum = (BigDecimal) propertyValue;
                    queryNum = new BigDecimal(query.getValue());
                    compareResult = originalNum.compareTo(queryNum);
                    if (checkQueryCriterion(compareResult, query.getSymbol())) {
                        resultList.add(t);
                    }
                }
            }
            objectList = resultList;
        }
        return new Result(objectList);
    }

    private static boolean checkQueryCriterion(int compareResult, String symbol) {
        switch (symbol) {
            case ">":
                if (compareResult > 0) {
                    return true;
                }
                break;
            case ">=":
                if (compareResult >= 0) {
                    return true;
                }
                break;
            case "=":
                if (compareResult == 0) {
                    return true;
                }
                break;
            case "!=":
                if (compareResult != 0) {
                    return true;
                }
                break;
            case "<=":
                if (compareResult <= 0) {
                    return true;
                }
                break;
            case "<":
                if (compareResult < 0) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }
}
