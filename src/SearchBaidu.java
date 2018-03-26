import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchBaidu {
    private static SearchBaidu instance = new SearchBaidu();
    final int pageSize = 10;
    private SearchBaidu(){

    }

    public static SearchBaidu getInstance() {
        return instance;
    }
    public void search(String keyWord, int page){
        try {
            String  url = "https://www.baidu.com/s?wd=" + keyWord;
            Document doc = Jsoup.connect(url).timeout(60000).get();
//            System.out.println("------------------测试-----------------------");
//            System.out.println(TextExtractUtil.getInstance().parseHtml(doc.html()));
//            System.out.println("------------------测试-----------------------");
            Elements clicks = doc.select("a[data-click]");
            String regEx = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
            String regExwww = "https?://www.*";
            Pattern linkPattern = Pattern.compile(regEx);
            Pattern usefulUrlPattern = Pattern.compile(regExwww);
     //       System.out.println(doc);
            for(Element click : clicks){
                System.out.println("【data begin】");
                System.out.println(click.toString());

                Matcher matcher = linkPattern.matcher(click.toString());
                if(matcher.find()){
                    String possibleUrl = matcher.group(1).split("\"")[1];
                    matcher = usefulUrlPattern.matcher(possibleUrl);

                    if(matcher.find()){
                        String usefulUrl = matcher.group(0);
                        System.out.println("【link:】");
                        System.out.println(usefulUrl); // 百度生成的加密URL 不是最后的URL

                        // 获得真实的URL
                        String realUrl = Jsoup.connect(usefulUrl)
                                .timeout(50000)
                                .method(Connection.Method.GET)
                                .followRedirects(false)
                                .execute().header("Location");
                        System.out.println("[-------------------真实地址-------------------]" + "\n" + realUrl);

                   //     Document usefulDoc = Jsoup.connect(usefulUrl).get();
                   //     System.out.println(usefulDoc.title());

                    }
                }
                System.out.println("【---------------------data end-------------------------】");
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
