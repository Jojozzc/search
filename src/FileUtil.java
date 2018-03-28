import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileUtil {
    final static String UTF8 = "utf-8";
    final static String GBK = "gbk";
    final static String ASCII = "ascii";
    final static String TESTDIR = "F:\\Code\\Crawler\\page\\";
    private static FileUtil instance = new FileUtil();
    private FileUtil(){}
    public static FileUtil getInstance() {
        return instance;
    }
    public String readTexByAbsolutepath(String absolutepath){
        File file = new File(absolutepath);
        return readTxtByFile(file);
    }
    public String readTxtByFile(File file){
        String fileName = file.getName();
        System.out.println(fileName);
        String[] spits = fileName.split("\\.");
        if(spits.length < 0 || !spits[spits.length - 1].equals("txt")){
            return "";
        }

        try {
            StringBuilder fileStr = new StringBuilder();
            InputStreamReader read = new InputStreamReader(
                                             new FileInputStream(file), UTF8);
            BufferedReader reader = new BufferedReader(read);
            String line;
            while ((line = reader.readLine()) != null) {
                fileStr.append(line);
            }
            read.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    public List<File> travelalAllFile(String absolutepath) {
        File file = null;
        List<File> res = new ArrayList<File>();
        try{
            file = new File(absolutepath);
            travelalFile(file, res);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    private void travelalFile(File file, List<File> res){
        if(file == null) return;
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files) {
                travelalFile(f, res);
            }
        }
        else {
            res.add(file);
        }
    }

    public void writeFile(String fileName, String dirPath, String fileContent) {
        try {
            File f = new File(dirPath + fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(
                    new FileOutputStream(f), UTF8);
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(fileContent);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void writeFile(String fileName, String dirPath, String[] fileContent) {
        try {
            File f = new File(dirPath + fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(
                    new FileOutputStream(f), UTF8);
            BufferedWriter writer = new BufferedWriter(write);
            for (String string : fileContent)
                writer.write(string);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void writeFileWithAddingSpace(String fileName, String dirPath, String[] fileContent) {
        try {
            File f = new File(dirPath + fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(
                    new FileOutputStream(f), UTF8);
            BufferedWriter writer = new BufferedWriter(write);
            for (String string : fileContent)
                writer.write(string + " ");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
