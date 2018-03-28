import java.util.Scanner;

public class StringInputUtil {
    private static StringInputUtil instance = new StringInputUtil();
    private static Scanner in = new Scanner(System.in);
    private StringInputUtil(){

    }

    public static StringInputUtil getInstance() {
        return instance;
    }
    public String readLine(){
        return in.nextLine();
    }
}
