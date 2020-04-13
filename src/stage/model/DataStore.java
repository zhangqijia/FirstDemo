package stage.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZQJ
 * @date 4/13/2020
 */
public interface DataStore {

    /**
     * all stars
     */
    List<Star> STAR_LIST = new ArrayList<>();
    /**
     * all planets
     */
    List<Planet> PLANET_LIST = new ArrayList<>();
    /**
     * all messier objects
     */
    List<Messier> MESSIER_LIST = new ArrayList<>();
    /**
     * statistics results, the sequence is corresponding to questions sequence
     */
    List<String> STATISTICS_RESULT = new ArrayList<>();
}
