package BiliBili;
/**
Now there is a string, the first character represents the first-level separator, 
separating different key-value pairs, the second character represents the second-level separator, 
separating key and value and the next string represents the string to be processed.
Please give all valid key-value pairs.

Valid key-value pairs are key-value pairs that neither key nor value is empty.
The problem ensures that the separator is not letters or numbers, 
and the string to be processed contains only two types of separators, lowercase letters and numbers.
There can be at most one second-level separator between two first-level separators.
The length of the string in problem is no more than 1000.
 */
/**
eg1
input:"#:a:3#b:8#c:9"
output:[["a","3"],["b","8"],["c","9"]]

eg2.
input:"#:aa:3#b:853#:9"
output:[["aa","3"],["b","853"]]
不valid的key pair不能输出
 */

import java.util.ArrayList;
import java.util.List;

public class StringSegmentation {
    /**
     * @param str: the string need to be processed
     * @return: all the valid key-value pairs.
     */
    public List<List<String>> StringSeg(char[] str) {
        List<List<String>> ans = new ArrayList<>();
        List<String> key_val = new ArrayList<>();
        String tmp = "";

        if (str.length < 2) {
            return ans;
        }
        char separator1 = str[0];
        char separator2 = str[1];
        for (int i = 2; i < str.length; i++) {
            if (str[i] == separator1) {  //想象成字典中的逗号
                if (tmp != "") {
                    key_val.add(tmp);
                }
                tmp = "";
                if (key_val.size() == 2) { 
                    List<String> a = new ArrayList<>();
                    a.add(key_val.get(0));
                    a.add(key_val.get(1));
                    ans.add(a); //? add(key_val)???注意， 这里每一次单独执行到时a和key_val的值是完全相等的，
                    //但是如果直接ans.add(key_val)的话，因为key_val是在for循环外面定义好的， ans中第一轮循环加的key_val是[a,3]
                    //第二轮加的比如说是[b, 4]，我们期望ans = [[a, 3], [b, 4]]， 但是实际结果会是ans = [[b,4], [b, 4]]
                }
                key_val.clear();
            } else if (str[i] == separator2) { //想象成字典中的冒号
                if (tmp != "") {
                    key_val.add(tmp);
                }
                tmp = "";
            } else {
                tmp = tmp + str[i];
            }
        }
        if (tmp != "") { //用于处理最后一个value，它的后面不会再有separator1了
            key_val.add(tmp);
        }
        if (key_val.size() == 2) {
            ans.add(key_val);
        }
        return ans;
       
    }
}

