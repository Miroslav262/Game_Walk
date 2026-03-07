package files.WayElements;

import java.util.List;

public class Way {
    private List<WayElement> elements;

    public Way(List<WayElement> elements){
        this.elements = elements;
    }

    public List<WayElement> getElements(){
        return this.elements;
    }


}
