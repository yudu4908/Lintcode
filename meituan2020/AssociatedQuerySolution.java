package meituan2020;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一张员工表datalist1，存储员工ID，员工姓名。
给定一张员工工作时长表datalist2，存储员工ID，月份，工时。
计算每个员工1-3月每月工时及总工时。

输入描述：
[[员工ID,员工姓名],[员工ID,员工姓名],...]
[[员工ID,月份,工时,月份,工时,月份,工时],[员工ID,月份,工时,月份,工时,月份,工时],...]

输出描述：
[[员工姓名,一月份工时，二月份工时，三月份工时，总工时],[员工姓名,一月份工时，二月份工时，三月份工时，总工时],...]
两张表均按员工ID从小到大依次给出相关数据，返回的表中的数据也应按员工ID从小到大依次排列。
 */
public class AssociatedQuerySolution {
    //由于两张表都是按ID从小到大给出的数据，因此把员工姓名和员工每月工作时长一一对应加入新的列表，同时计算总工作时长加入列表即可。
        /**
     * @param datalist1: a list represents the employee table
     * @param datalist2: a list represents the employee hours table
     * @return: Returns a list of strings represents the datalist3
     */
    public List<List<String>> getList(List<List<String>> datalist1, List<List<String>> datalist2) {
        int n = datalist1.size(); //employees total number
        List<List<String>> datalist3 = new ArrayList();
        for (int i = 0; i < n; i++) {
            datalist3.add(new ArrayList());
            datalist3.get(i).add(datalist1.get(i).get(1)); //employee name
            //use sum to strore the 3 months' total salary
            int sum = 0;
            for (int j = 2; j < 7; j += 2) {  // j = 2, 4, 6分别代表三个月的工时
                datalist3.get(i).add(datalist2.get(i).get(j));
                sum += Integer.parseInt(datalist2.get(i).get(j));
            } 
            datalist3.get(i).add("" + sum); //sum 转换回string
            //datalist3.get(i).add(Integer.toString(sum)); //sum 转换回string
            
        }
        return datalist3;
    }
}