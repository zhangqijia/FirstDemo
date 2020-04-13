package stage.query;

import static stage.model.DataStore.*;

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
     * the index of 'where' in query string array
     */
    public static final int INDEX_WHERE = 2;
    /**
     * the index of first 'and' in query string array
     */
    public static final int INDEX_FIRST_AND = 6;
    /**
     * the number of words in per criterion.
     * e.g. 'and a > 100'
     */
    public static final int LENGTH_CRITERION = 4;

    /**
     * execute one query sentence.
     * If this query sentence is malformed, this method will throw out SyntaxIllegalException.
     *
     * @param queryStr query sentence
     * @return the result which contains a answer list
     */
    public static QueryResults run(String queryStr) throws IllegalAccessException, IntrospectionException, InvocationTargetException, SyntaxIllegalException {
        QueryResults queryResults = null;
        ArrayList<Criterion> queries = new ArrayList<>();
        // split this query sentence by whitespaces
        String[] strArray = queryStr.split("\\s+");
        // verify key words in the query sentence
        if (!SELECT.equals(strArray[0])) {
            throw new SyntaxIllegalException(queryStr);
        }
        // get object type name and retrieve this name from pre-defined Data Dictionary
        String objectName = strArray[1];
        // if this type name is illegal throw out exception
        if (ConstantMap.getProperty(objectName) == null) {
            throw new SyntaxIllegalException(queryStr);
        }
        int strArrayLength = strArray.length;
        /* check the length of query sentence and the number of 'and'.
           The structure of a query sentence should be:
           select [typeName] (where [propertyName  symbol propertyValue] (and [pN s pV])... ).
           So, the length of this array should obey following rules:
           1. Array length = 6 + 4n, n>=0;
           'n' is the number of criteria except the first criterion after 'where'.
           NB: 'where' should appear once at where index=2; 'and' should appear at where index=6+4k, k=1,2,...n-1;
           2. Array length = 2;
           this is no criteria. For this situation, program has checked typeName and keyword 'select'.
        */
        if (strArrayLength > INDEX_FIRST_AND) {
            if ((strArrayLength - INDEX_FIRST_AND) % LENGTH_CRITERION != 0) {
                throw new SyntaxIllegalException(queryStr);
            }
            for (int i = 6; i < strArrayLength; i += INDEX_FIRST_AND) {
                if (!"and".equals(strArray[i])) {
                    throw new SyntaxIllegalException(queryStr);
                }
            }
        }
        // check and extract criteria
        if (strArrayLength > INDEX_WHERE) {
            if (!WHERE.equals(strArray[INDEX_WHERE])) {
                throw new SyntaxIllegalException(queryStr);
            }
            // check criterion (propertyName and symbol) by pre-defined data table
            for (int i = 3; i < strArrayLength; i += LENGTH_CRITERION) {
                String property = ConstantMap.getProperty(objectName, strArray[i]);
                String symbol = ConstantMap.getSymbol(strArray[i + 1]);
                if (property != null && symbol != null) {
                    queries.add(new Criterion(property, symbol, strArray[i + 2]));
                } else {
                    throw new SyntaxIllegalException(queryStr);
                }
            }
        }
        // execute the query
        switch (objectName) {
            case "stars":
                queryResults = invokeQuery(STAR_LIST, queries, Star.class);
                break;
            case "messiers":
                queryResults = invokeQuery(MESSIER_LIST, queries, Messier.class);
                break;
            case "planets":
                queryResults = invokeQuery(PLANET_LIST, queries, Planet.class);
                break;
            default:
                break;
        }
        return queryResults;
    }

    /**
     * execute the query
     *
     * @param objectList the astronomical meta data list
     * @param queries    the criteria
     * @param tClass     the Object's Class which is being queried
     * @param <T>        the type of list element
     * @return queryResults
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static <T> QueryResults invokeQuery(List<T> objectList, ArrayList<Criterion> queries, Class<?> tClass) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor propertyDescriptor;
        // use every criterion to filter resultList
        for (Criterion query : queries) {
            // based on key name, get the value of this property by reflection and compare with criterion
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
                // the type of property value might be String or BigDecimal
                if (propertyValue instanceof String) {
                    originalStr = (String) propertyValue;
                    queryStr = query.getValue();
                    // compare this object's property value with the value in query
                    compareResult = originalStr.compareTo(queryStr);
                    // judge whether this object meets criterion by the returned int value of compareTo method and the symbol in query
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

    /**
     * judge whether this compare result according to the compare symbol
     *
     * @param compareResult the value returned by Comparator
     * @param symbol        <, > ...
     * @return boolean
     */
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
