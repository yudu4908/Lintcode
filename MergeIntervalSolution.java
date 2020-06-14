import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Definition of Interval:
 * public class Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */


public class MergeIntervalSolution {
    //给出若干闭合区间， 合并所有重叠部分
    /**
     * 将区间按起点从小到大排序，然后从左到右扫一遍找最远的右端点。
       交错或包含的区间就合并。
     */
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        Collections.sort(intervals, new IntervalComparator());//按照区间的start从小到大排序

        List<Interval> result = new ArrayList<Interval>();
        Interval last = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curt = intervals.get(i);
            if (curt.start <= last.end) {
                last.end = Math.max(last.end, curt.end);
            } else {
                result.add(last);
                last = curt;
            }
        }
        result.add(last);
        return result;
    }

    private class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }
}