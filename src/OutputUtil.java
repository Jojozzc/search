public class OutputUtil implements Output{
    private static OutputUtil instance = new OutputUtil();
    private OutputUtil(){

    }
    public static OutputUtil getInstance() {
        return instance;
    }

    @Override
    public void print(Object o){
        System.out.print(o);
    }
    @Override
    public void println(Object o){
        System.out.println(o);
    }

}