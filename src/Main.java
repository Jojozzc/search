import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 被搜索文件需要保存为UTF-8格式
        Scanner in = new Scanner(System.in);
        int pageSize = 12;
        if(true){
            while (true){
                System.out.println("请输入搜索词：（输入quit可退出搜索）");
                String word = in.next();
                if(word.equals("quit")){
                    System.out.println("已关闭引擎");
                    break;
                }
                SearchBaidu.getInstance().search(word, pageSize);
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

//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int m = in.nextInt();
//        if(n <= 0 || m <= 0) System.out.print(0);
//        int[] a = new int[n];
//        int[] b = new int[m];
//        boolean isABig = false;
//        Comparator<Integer> comparator = new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1 - o2;
//            }
//        };
//        int peekA;
//        int peekB;
//        int num;
//        PriorityQueue<Integer> heapA = new PriorityQueue<>(comparator);
//        PriorityQueue<Integer> heapB = new PriorityQueue<>(comparator);
//        HashSet<Integer> hashSetA = new HashSet<Integer>();
//        HashSet<Integer> hashSetB = new HashSet<Integer>();
//        int count = 0;
//        for(int i = 0; i < n; i++){
//            num = in.nextInt();
//            heapA.offer(num);
//            hashSetA.add(num);
//        }
//        for(int i = 0; i < m; i++){
//            num = in.nextInt();
//            heapB.offer(num);
//            hashSetB.add(num);
//        }
//        while ((!(heapA.size() <= 1 && heapB.size() <= 1)) && (heapA.size() > 0 && heapB.size() > 0)){
//            peekA = heapA.peek();
//            peekB = heapB.peek();
//            if(peekA == peekB){
//                heapA.poll();
//                heapB.poll();
//                hashSetA.remove(peekA);
//                hashSetB.remove(peekB);
//            }
//            else {
//                if(peekA > peekB){
//                    if(heapA.size() == 1){
//                        heapB.poll();
//                        hashSetB.remove(peekB);
//                        continue;
//                    }
//                    if(hashSetB.contains(peekA)){
//                        heapA.poll();
//                        hashSetA.remove(peekA);
//                        continue;
//                    }
//                    else {
//                        count++;
//                        heapB.offer(heapA.poll());
//                        hashSetA.remove(peekA);
//                        hashSetB.add(peekA);
//                    }
//                }
//                else {
//                    if(heapB.size() == 1){
//                        heapA.poll();
//                        hashSetA.remove(peekA);
//                        continue;
//
//                    }
//                    if(hashSetA.contains(peekB)){
//                        heapB.poll();
//                        hashSetB.remove(peekB);
//                        continue;
//                    }
//                    else {
//                        count++;
//                        heapA.offer(heapB.poll());
//                        hashSetB.remove(peekB);
//                        hashSetA.add(peekB);
//                    }
//                }
//            }
//        }
//        System.out.print(count);
    }
}