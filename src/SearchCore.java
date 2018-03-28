import java.util.ArrayList;
import java.util.List;

public class SearchCore {
    public SearchCore(){

    }
    public List<WebPage> getResult(String query){
        List<WebPage> result = new ArrayList<>();
        if(query == null || query.equals("")) return result;
        return result;
    }

    

}
