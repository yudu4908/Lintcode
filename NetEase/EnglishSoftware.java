package NetEase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
小林是班级的英语课代表，他想开发一款软件来处理班上同学的成绩
小林的软件有一个神奇的功能，能够通过一个百分数来反应你的成绩在班上的位置。“成绩超过班级 …% 的同学”。
设这个百分数为 p，考了 s 分，则可以通过以下式子计算得出 p：
p = ( 分数不超过 s 的人数 - 1) / 班级总人数 * 100%
请你设计一下这个软件
 */
/**
给出score数组代表第i个人考了score[i]分
给出ask数组代表询问第i个人的成绩百分数
每询问一次输出对应的成绩百分数，不需要输出百分号
答案向下取整
输入: score = [100,98,87], ask=[1,2,3]
输出: [66,33,0] 
解释:
第一个人考了100分，超过了66%的学生
 */

class Student {
    public int id;
    public double score;
    public Student(int _id, double _score) {
        this.id = _id;
        this.score = _score;
    }
} 

public class EnglishSoftware {
    /**
     这是一道简单题目，实际上我们可以用排序解决
我们先记录这个人当前的位置，对于成绩做一个排序
根据一个人拍完序后的位置/总人数得到成绩对应的百分比
最后根据ask位置所对应的成绩来得到百分比的值，
这里可以用map来记录其当前位置和之前位置，复杂度nlogn
     */
    /**
     * @param score: Each student's grades
     * @param ask: A series of inquiries
     * @return: Find the percentage of each question asked
     */
    //这题其实不简单。。
    public List<Integer> englishSoftware(List<Integer> score, List<Integer> ask) {
        Map<Integer, Double> scoreMap = new HashMap<Integer, Double>();
        List<Student> sortStudent = new ArrayList<Student>();
        for (int i = 0; i < score.size(); i++) {
            scoreMap.put(i + 1, (double)score.get(i)); //存放第一个人和第一个人的score。。。
            sortStudent.add(new Student(i, (double)score.get(i))); //sortStudent存放的是student对象
        }
        sortStudent.sort((o1, o2) -> (int)o1.score - (int)o2.score); //分数从小到大排序， 为什么要转换为int型？因为不加的话lambda expression会报错bad return type
        Map<Double, Double> percentMap = new HashMap<Double, Double>();
        for (int i = 0; i < sortStudent.size(); i++) {
            percentMap.put(sortStudent.get(i).score, (double)i * 100 / sortStudent.size()); //percentMap存放分数和百分比的对应关系
        } 
        List<Integer> ans = new ArrayList<Integer>();
        for (int k : ask) {
            ans.add((int)(Math.floor(percentMap.get(scoreMap.get(k)))));//scoreMap.get(k)取出第k个人的分数，percentMap取出这个分数对应的班级百分比
        }
        return ans;
    }

}