import org.ansj.splitWord.analysis.ToAnalysis;

public class TextDividerImplement implements TextDivider {
    private static TextDividerImplement instance = new TextDividerImplement();
    private TextDividerImplement(){

    }

    public static TextDividerImplement getInstance() {
        return instance;
    }

    @Override
    public String divide(String str){
        if(str == null || str.length() == 0) return "";
        return ToAnalysis.parse(preProcess(str)).toString();
    }
    @Override
    public String[] divideToStringArray(String str){
        String dividedStr = divide(str);
        if(dividedStr.equals("")) return null;
        return dividedStr.split("/[a-z]*[,，]?");
    }
    @Override
    public String preProcess(String str){
        if(str == null || str.length() == 0) return "";
        return str.replaceAll("\\s*", "");
    }
}
