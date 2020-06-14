package NetEase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
小明的公司的员工的个人每个月的薪酬是xi元。
现在小明的老板向小明提了几次询问, 每次询问老板都会给出一个整数k,小明要快速回答老板工资等于k的员工的数量。

1≤wage.size()≤80000
1≤ask.size()≤100000
1≤wage[i]≤500,000,000
 */
public class PeopleCounting {
    /**
    输入: wage = [6,2,1,2,6,2,5],ask = [6,5,8,2]
输出: [2,1,0,3]
     */
    /**
     这是一道简单题，我们可以用map记录每个工资的人数
每有一个人的工资相同，map对应的value+1
     */
        /**
     * @param wage: Salaries of all employees
     * @param ask: Number of inquiries
     * @return: Every time an answer is asked
     */
    public List<Integer> peopleCounting(List<Integer> wage, List<Integer> ask) {
        HashMap<Integer, Integer> wageNumber = new HashMap<>();
        for (int i = 0; i < wage.size(); i++) {
            wageNumber.put(wage.get(i), wageNumber.getOrDefault(wage.get(i), 0) + 1); //如果wageNumber中已经有了某个工资，
                //那么就在value基础上加1，没有的话就赋值1           
        }
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < ask.size(); i++) {
            if (wageNumber.get(ask.get(i)) == null) {
                res.add(0);
            } else {
                res.add(wageNumber.get(ask.get(i)));
            }
        }
        return res;
    }
}