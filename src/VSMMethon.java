import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VSMMethon {
    public static double calCosSim(Map<String, Double> v1, Map<String, Double> v2) {

        double sclar = 0.0,norm1=0.0,norm2=0.0,similarity=0.0;

        Set<String> v1Keys = v1.keySet();
        Set<String> v2Keys = v2.keySet();

        Set<String> both= new HashSet<>();
        both.addAll(v1Keys);
        both.retainAll(v2Keys);
        System.out.println(both);

        for (String str1 : both) {
            sclar += v1.get(str1) * v2.get(str1);
        }

        for (String str1:v1.keySet()){
            norm1+=Math.pow(v1.get(str1),2);
        }
        for (String str2:v2.keySet()){
            norm2+=Math.pow(v2.get(str2),2);
        }

        similarity=sclar/Math.sqrt(norm1*norm2);
        System.out.println("sclar:"+sclar);
        System.out.println("norm1:"+norm1);
        System.out.println("norm2:"+norm2);
        System.out.println("similarity:"+similarity);
        return similarity;
    }
}
