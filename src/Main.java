import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 被搜索文件需要保存为UTF-8格式
        Scanner in = new Scanner(System.in);
        int page = 1;
        if(true){
            while (true){
                System.out.println("请输入搜索词：（输入quit可退出搜索）");
                String word = in.next();

                if(word.equals("quit")){
                    System.out.println("已关闭引擎");
                    break;
                }
                System.out.println("请输入搜索第几页:");
                page = in.nextInt();
        //        SearchBaidu.getInstance().search(word,1);
                try {
                    List<WebPage> webPages = SearchBaidu.getInstance().getSearchPage(word, page, 60000);

                    for (WebPage webPage : webPages) System.out.println(webPage.getTitle() + " " + webPage.getUrl());
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println(e.toString());
                }

            }
            return;
        }
        InvertedSort invertedSort = new InvertedSort();


        String searchPath = "F:\\Code\\python\\page\\C000023";
        Map<String, List<InvertedSort.WordPosition>> wordTable
                = invertedSort.getInvertedTable(searchPath);
        String targetWord = "";
        while (true){
            System.out.println("请输入搜索词：（输入quit可退出搜索）");
            targetWord = in.next();
            if(targetWord.equals("quit")) {
                System.out.println("已关闭引擎");
                break;
            }
            else {
                List<InvertedSort.WordPosition> targetList = invertedSort.searchForWord(wordTable, targetWord);
                int size = targetList.size();
                if(size == 0) System.out.println("无结果");
                for(int i = 0; i < size; i++){
                    System.out.println(targetList.get(i).getFileSite() + "位置:" + targetList.get(i).getPositionInFile());
                }
                System.out.println();
            }
        }
    }
}