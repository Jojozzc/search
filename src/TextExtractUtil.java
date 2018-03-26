import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextExtractUtil {
    // 从网页抽取信息
    private static TextExtractUtil instance = new TextExtractUtil();
    private TextExtractUtil(){

    }
    public static TextExtractUtil getInstance() {
        return instance;
    }

    private static final int blocksWidth = 3;
    private static final boolean flag = false;
    private static int threshold = 86;

    public static void setthreshold(int value) {
        threshold = value;
    }
    public String parseHtml(String html){
        return getText(html);
    }
    private static String  preProcess(String html) {
        html = html.replaceAll("(?is)<!DOCTYPE.*?>", "");
        html = html.replaceAll("(?is)<!--.*?-->", "");              // remove html comment
        html = html.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
        html = html.replaceAll("(?is)<style.*?>.*?</style>", "");   // remove css
        html = html.replaceAll("&.{2,5};|&#.{2,5};", " ");          // remove special char
        html = html.replaceAll("(?is)<.*?>", "");
        return html;
        //<!--[if !IE]>|xGv00|9900d21eb16fa4350a3001b3974a9415<![endif]-->
    }
    private String getText(String html){
        List<String> lines = Arrays.asList(html.split("\n"));
        ArrayList<Integer> indexDistribution = new ArrayList<Integer>();
        int start;
        int end;
        StringBuilder text = new StringBuilder();
        boolean boolstart = false, boolend = false;

        html = preProcess(html);

        for (int i = 0; i < lines.size() - blocksWidth; i++) {
            int wordsNum = 0;
            for (int j = i; j < i + blocksWidth; j++) {
                lines.set(j, lines.get(j).replaceAll("\\s+", ""));
                wordsNum += lines.get(j).length();
            }
            indexDistribution.add(wordsNum);
        }
        start = -1;
        end = -1;
        text.setLength(0);
        for (int i = 0; i < indexDistribution.size() - 1; i++) {
            if (indexDistribution.get(i) > threshold && !boolstart) {
                if (indexDistribution.get(i + 1).intValue() != 0
                        || indexDistribution.get(i + 2).intValue() != 0
                        || indexDistribution.get(i + 3).intValue() != 0) {
                    boolstart = true;
                    start = i;
                    continue;
                }
            }
            if (boolstart) {
                if (indexDistribution.get(i).intValue() == 0
                        || indexDistribution.get(i + 1).intValue() == 0) {
                    end = i;
                    boolend = true;
                }
            }
            StringBuilder tmp = new StringBuilder();
            if (boolend) {
                for (int ii = start; ii <= end; ii++) {
                    if (lines.get(ii).length() < 5) {
                        continue;
                    }
                    tmp.append(lines.get(ii)).append("\n");
                }
                String str = tmp.toString();
                if (str.contains("Copyright") || str.contains("版权所有")) {
                    continue;
                }
                text.append(str);
                boolstart = boolend = false;
            }
        }
        return text.toString();

    }

}
