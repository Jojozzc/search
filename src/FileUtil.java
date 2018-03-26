import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static FileUtil instance = new FileUtil();
    private FileUtil(){
    }
    public static FileUtil getInstance() {
        return instance;
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



}
