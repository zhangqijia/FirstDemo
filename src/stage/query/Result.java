package stage.query;

import stage.model.AstronomicalObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZQJ
 * @date 3/20/2020
 */
public class Result<T extends AstronomicalObject> implements QueryResults {

    private List<T> list;
    private ArrayList<String> idList;
    private Exception exception;

    public Result(List<T> list) {
        this.list = list;
    }

    @Override
    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public ArrayList<String> getIDs() {
        if (idList == null) {
            idList = new ArrayList<>(list.size());
            for (T t : list) {
                idList.add(t.getCatalogueNumberOrName());
            }
        }
        return idList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (idList == null) {
            getIDs();
        }
        sb.append("[ ");
        for (int i = 0; i < idList.size(); i++) {
            if (i == idList.size() - 1) {
                sb.append(idList.get(i));
            } else {
                sb.append(idList.get(i)).append(", ");
            }
        }
        sb.append(" ]");
        String s = exception == null ? " " : exception.toString();
        System.out.println(s);
        return sb.toString();
    }
}
