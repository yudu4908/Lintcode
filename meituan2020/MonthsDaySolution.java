package meituan2020;

/**
 * 1. 闰年为29天,不闰年为28天
2. 普通年能被4整除且不能被100整除的为闰年.（如2004年就是闰年,1901年不是闰年） 地球公转示意图
3. 世纪年能被400整除的是闰年.(如2000年是闰年,1900年不是闰年)
4. 对于数值很大的年份,这年如果能整除3200,并且能整除172800则是闰年.如172800年是闰年,86400年不是闰年(因为虽然能整除3200,但不能整除172800)（此按一回归年365天5h48'45.5''计算）.
 */

public class MonthsDaySolution {
    //Given a year and month, return the days of that month.
    /**
     * 1 <= year <= 10000
     * 1 <= month <= 12
     * Input: 
            2020 
            2
       Output: 
            29
        Input: 
        2020 
        3
        Output: 
        31
     */
     /**
     * @param year: a number year
     * @param month: a number month
     * @return: Given the year and the month, return the number of days of the month.
     */
    public int getTheMonthDays(int year, int month) {
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; //12个月对应的天数, 0只是为了补位满足月份和下标对应关系
        if (month != 2) {
            return days[month];
        }
        if (year % 400 == 0) {
            return 29;
        } else if (year % 4 == 0 && year % 100 != 0){ //1900就是不能被400整除，但是可以被4和100整除的
            return 29;
        }
        return 28;
    }
}