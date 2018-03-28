import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchBaidu implements HTMLProvider{
    private static SearchBaidu instance = new SearchBaidu();
    final int pageSize = 10;
    private SearchBaidu(){

    }
    @Override
    public String getHtmlContent(WebPage page){
        if(page == null) return "";
        try {
            Document html = Jsoup.connect(page.getUrl()).get();
            return html.text();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";

    }

    public static SearchBaidu getInstance() {
        return instance;
    }

    public List<WebPage> getSearchPage(String keyWord, int page, int timeOut) throws Exception{
        List<WebPage> resultWebPateList = new ArrayList<WebPage>();
        if(timeOut <= 0) throw new Exception("TimeOut must be positive.");
        try {
            int pagePara = (page - 1) * pageSize;
            String  url = "https://www.baidu.com/s?pn=" + pagePara  + "&wd="+ keyWord;
            Document doc = Jsoup.connect(url).timeout(timeOut).get();

            Elements clicks = doc.select("a[data-click]");
            String regEx = "<a[^>]*href=(\"([^\"]*)\"|\'([^\']*)\'|([^\\s>]*))[^>]*>(.*?)</a>";
            String regExwww = "https?://www.*";
            Pattern linkPattern = Pattern.compile(regEx);
            Pattern usefulUrlPattern = Pattern.compile(regExwww);
            for(Element click : clicks){
                Matcher matcher = linkPattern.matcher(click.toString());
                if(matcher.find()){
                    String possibleUrl = matcher.group(1).split("\"")[1];
                    matcher = usefulUrlPattern.matcher(possibleUrl);

                    if(matcher.find()){
                        String usefulUrl = matcher.group(0);
                        // 获得真实的URL

                        String realUrl = Jsoup.connect(usefulUrl)
                                .timeout(timeOut)
                                .method(Connection.Method.GET)
                                .followRedirects(false)
                                .execute().header("Location");

                        System.out.println("实际：" + realUrl);


                        if(realUrl != null){
                            Document realHtml = Jsoup.connect(realUrl)
                                    .timeout(timeOut)
                                    .get();

                            System.out.println("-----------------------------正文测试-----------------------------");
                            System.out.println(realHtml.text("p"));
                            System.out.println("-----------------------------------------------------------------");
                            resultWebPateList.add(new WebPage()
                                    .buildUrl(realUrl)
                                    .buildTitle(realHtml.title()));
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultWebPateList;
    }
    public void search(String keyWord, int page){
        try {
            int pagePara = page * pageSize - 1;
       //     String  url = "https://www.baidu.com/s?wd=" + keyWord;
            String  url = "https://www.baidu.com/s?pn=" + pagePara  + "&wd="+ keyWord;
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
            //    System.out.println("【data begin】");
            //    System.out.println(click.toString());

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
