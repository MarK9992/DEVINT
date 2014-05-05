package level;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 09/04/14.
 */
public class Way {
    private List<Direction> path;


    public Way(List<Direction> path){
        this.path=path;
    }

    public Way(){
        this(new ArrayList<Direction>());
    }

    public boolean isEmpty(){
        return path.isEmpty();
    }

}
