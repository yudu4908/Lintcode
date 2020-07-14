import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) {
        /** 
        int[] a = {1, 2, 3};
        //System.out.println(a[-1]);
        System.out.println("Hello World");
        System.out.println((int)('a'));
        System.out.println(a.length);
        int n = 4;
        System.out.println(n << 2);
        System.out.println(n);
        System.out.println(5 >> 1);

        List<List<String>> ans = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        tmp.add("aa");
        ans.add(tmp);
        System.out.println("ans is: ");
        System.out.println(ans);
        tmp.clear();
        tmp.add("bb");
        ans.add(tmp);
        System.out.println("ans is: ");
        System.out.println(ans);   
        */
        // System.out.println(Math.sqrt(10)); 
        // System.out.println(Math.sqrt(25)); 
        // System.out.println(Math.sqrt(49)); 
        // System.out.println(Math.pow(2, 3)); 
        // System.out.println(Math.pow(3, 2));
        // System.out.println(Math.pow(27, 1/3)); 
        // System.out.println(Math.pow(27, 1.0/3)); 
        // System.out.println(Math.pow(49, 1.0/2)); 
        // System.out.println("345".compareTo("24"));//1 
        // System.out.println("345".compareTo("4")); //-1
  

        String interval_A = "[a,b]";
        String interval_B = "[b,c]";

        String[] left = interval_A.split("\\]|\\(|\\,|\\)|\\[");
        String[] right = interval_B.split("\\]|\\(|\\,|\\)|\\[");
        for (int i = 0; i < left.length; i++) {
            System.out.println(left[i]);
        }
        System.out.println("left[0]");
        System.out.println(left[0]);
        for (int i = 0; i < right.length; i++) {
            System.out.println(right[i]);
        }





    }
    
}