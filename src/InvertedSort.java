import sun.nio.cs.UTF_32;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class InvertedSort {

    public List<WordPosition> searchForPath(String absolutepath, String targetWord){
        Map<String, List<WordPosition>> wordTable = getInvertedTable(absolutepath);
        return searchForWord(wordTable, absolutepath);
    }
    public Map<String, List<WordPosition>> getInvertedTable(String absolutepath){
        Map<String, List<WordPosition>> wordTable = null;
        File dir = new File(absolutepath);
        String[] files;
        List<File> fileList = FileUtil.getInstance().travelalAllFile(absolutepath);
        wordTable = getInvertedTable(fileList);
        return wordTable;
    }

    private Map<String, List<WordPosition>> getInvertedTable(List<File> fileList){
        Map<String, List<WordPosition>> wordTable = new HashMap<String, List<WordPosition>>();
        if(fileList == null) return wordTable;
        BufferedReader br = null;
        String line = "";
        for(File file : fileList){
            String fileName = file.getName();
            System.out.println(fileName);
            try {
                StringBuilder fileStr = new StringBuilder();
                br = new BufferedReader(new FileReader(file));
                do{
                    line = br.readLine();
                    if(line != null)
                        fileStr.append(line);

                }while (line != null);
                String[] words = fileStr.toString().split(" |[,.，。、：:<>《》?？!！”“''\"\"]");

                for(int i = 0; i < words.length; i++){

                    WordPosition wordPosition = new WordPosition(file.getName(), i);
                    if(!wordTable.containsKey(words[i])){
                        List<WordPosition> wordPositionList = new LinkedList<WordPosition>();
                        wordPositionList.add(wordPosition);
                        wordTable.put(words[i], wordPositionList);
                    }
                    else {
                        wordTable.get(words[i]).add(wordPosition);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return wordTable;
    }
    public List<WordPosition> searchForWord(Map<String, List<WordPosition>> wordTable, String targetWord){
        List<WordPosition> targetList = new LinkedList<WordPosition>();
        if(wordTable == null) return targetList;
        if(wordTable.containsKey(targetWord)) return wordTable.get(targetWord);
        else return targetList;
    }
    class WordPosition{
        public WordPosition(String fileSite, int positionInFile){
            this.fileSite = fileSite;
            this.positionInFile = positionInFile;
        }
        private String fileSite = null;
        private int positionInFile = -1;
        public String getFileSite(){
            return fileSite;
        }
        public int getPositionInFile(){
            return positionInFile;
        }
        public void setFileSite(String fileSite){
            this.fileSite = fileSite;
        }
        public void setPositionInFile(int positionInFile){
            this.positionInFile = positionInFile;
        }
    }
}
