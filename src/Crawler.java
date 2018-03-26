import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {
    public Crawler(){

    }
    public static List<String> getMailsByWeb(String urlStr) throws IOException{
        URL url = new URL(urlStr);
        BufferedReader bufr = new BufferedReader(new InputStreamReader(url.openStream()));
        String mail_regex = "\\w+@\\w+(\\.[a-zA-Z]{2,3}){1,3}";// 模式

        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(mail_regex);
        String line = null;
        while ((line = bufr.readLine()) != null){
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()){
                list.add(line);
            }
        }
        return list;
    }

    public static List<String> getMailsByWeb(String urlStr, String regex) throws IOException{
        URL url = new URL(urlStr);
        BufferedReader bufr = new BufferedReader(new InputStreamReader(url.openStream()));

        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        String line = null;
        while ((line = bufr.readLine()) != null){
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()){
                list.add(line);
            }
        }
        return list;
    }

    public static List<String> getMails() throws IOException{
        //1,读取源文件。
        BufferedReader bufr=new BufferedReader(new FileReader("c:\\mail.html"));
        //2,对读取的数据进行规则的匹配。从中获取符合规则的数据.
        String mail_regex = "\\w+@\\w+(\\.\\w+)+";
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile(mail_regex);
        String line = null;
        while((line=bufr.readLine())!=null){
            Matcher m = p.matcher(line);
            while(m.find()){
                //3,将符合规则的数据存储到集合中。
                list.add(m.group());
            }
        }
        return list;
    }
}
