package stage.query;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static stage.model.DataStore.*;

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

    static final Pattern PATTERN = Pattern.compile("[\\w\\s]+\\W+[\\w\\s.]+");

    /**
     * execute one query sentence.
     * If this query sentence is malformed, this method will throw out SyntaxIllegalException.
     *
     * @param queryStr query sentence
     * @return the result which contains a answer list
     */
    public static QueryResults run(String queryStr) throws IllegalAccessException,
            IntrospectionException, InvocationTargetException, SyntaxIllegalException {
        QueryResults queryResults = null;
        ArrayList<Criterion> queries = new ArrayList<>();
        // split this query sentence by whitespaces
        String[] strArray = queryStr.split("\\s+");
        // verify query syntax
        verifyQuerySyntax(queryStr, queries, strArray);
        // execute the query
        switch (strArray[1]) {
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
     * verify query sentences syntax
     * 1. doesn't contain description
     * 2. contain description
     *
     * @param queryStr query string
     * @param queries  list used to store query criteria
     * @param strArray String array which contains all words in query string
     * @throws SyntaxIllegalException if the query sentence doesn't meet syntax demands, throw out exception
     */
    private static void verifyQuerySyntax(String queryStr, ArrayList<Criterion> queries, String[] strArray) throws SyntaxIllegalException {
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
           3. Array contains "description".
           For 1,3, split criteria by 'and', then check the structure of every criterion. They should meet this
           "[\w\s]+\W+[\w\s.]+" regex expression. One arithmetic symbol between two character sequences.
        */
        if (strArrayLength > INDEX_WHERE) {
            // check spell of 'where'
            if (!WHERE.equals(strArray[INDEX_WHERE])) {
                throw new SyntaxIllegalException(queryStr);
            }
            int whereIndex = queryStr.indexOf("where");
            // get criteria from query and split it by 'and'
            String criteriaStr = queryStr.substring(whereIndex + 6).trim();
            String[] criteriaStrArr = criteriaStr.split("and\\s+");
            for (String criterionStr : criteriaStrArr) {
                // check every criterion's structure
                Matcher matcher = PATTERN.matcher(criterionStr);
                if (!matcher.matches()) {
                    throw new SyntaxIllegalException(criterionStr);
                }
                // split criterion string at the index of first space to get property name
                int firstSpace = criterionStr.indexOf(" ");
                String property = criterionStr.substring(0, firstSpace);
                // get arithmetic symbol
                int secondSpace = criterionStr.indexOf(" ", firstSpace + 1);
                String symbol = criterionStr.substring(firstSpace, secondSpace);
                // check property name and symbol  according predefined data map
                property = ConstantMap.getProperty(objectName, property.trim());
                symbol = ConstantMap.getSymbol(symbol.trim());
                if (property == null || symbol == null) {
                    throw new SyntaxIllegalException(queryStr);
                }
                String value = criterionStr.substring(secondSpace).trim();
                // create new criterion and add into queries
                queries.add(new Criterion(property, symbol, value));
            }
        }
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
    private static <T> QueryResults invokeQuery(List<T> objectList, ArrayList<Criterion> queries, Class<?> tClass)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {
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
