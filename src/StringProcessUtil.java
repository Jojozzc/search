public class StringProcessUtil {
    private static StringProcessUtil instance = new StringProcessUtil();
    private StringProcessUtil(){}
    private static final String PUNCTUATION_REGEX
            ="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】《》‘；：”“’。，、？|-]";
    public static StringProcessUtil getInstance() {
        return instance;
    }
    public String removePunctuation(String str){
        if(str == null) return "";
        String regEx = PUNCTUATION_REGEX;
        return str.replaceAll(regEx, "");
    }
}

