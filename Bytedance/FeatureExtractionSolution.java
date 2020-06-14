package Bytedance;

import java.util.HashMap;

/**
小明想从猫咪的视频里挖掘一些猫咪的运动信息。为了提取运动信息，他需要从视频的每一帧提取“猫咪特征”。
一个猫咪特征是一个两维的vector<x, y>。如果x_1=x_2 and y_1=y_2，那么这俩是同一个特征。
如果喵咪特征连续一致，可以认为喵咪在运动。也就是说，如果特征<a, b>在持续帧里出现，那么它将构成特征运动。
比如，特征<a, b>在第2/3/4/7/8帧出现，那么该特征将形成两个特征运动2-3-4 和7-8。
现在，给定每一帧的特征，特征的数量可能不一样。小明期望能找到最长的特征运动。
每行代表一帧。其中，第一个数字是该帧的特征个数，接下来的数字每两个为一对<x,y>
特征总数不超过 10^5
示例1:

输入: 
features:
[[2,1,1,2,2],
[2,1,1,1,4],
[2,1,1,2,2],
[2,2,2,1,4],
[0],
[0],
[1,1,1],
[1,1,1]]
输出:3
解释:特征<1,1>在连续的帧中连续出现3次，相比其他特征连续出现的次数大，所以输出3
 */
public class FeatureExtractionSolution {
    /**
     这道题不难，如果你去暴力遍历肯定会超时。所以需要选取合适的数据结构快速处理数据。
我们用两个map一个记录上一帧的数据，一个记录当前帧的数据，然后从上一帧找是否有和当前帧相同的，如果有的话更新值
不断更新最大值count
每层结束将上一层的清除，
将这层的复制到用来记录上一层的map，将当前层的map清除
     */
    public int FeatureExtraction(int[][] frames) {
        //存储上一行键值对出现的次数信息
        HashMap<String, Integer> preFeaTimes = new HashMap<String, Integer>();//java中的Map类似于Python中的dict
        //存储本行键值对在上一行的基础上出现的次数信息
        HashMap<String, Integer> feaTimes = new HashMap<String, Integer>();
        //保存结果
        int count = 0;
        for (int i = 0; i < frames.length; i++) {
            for (int j = 0; j < frames[i][0]; j++) {
                String xy = String.valueOf(frames[i][j * 2 + 1]) + String.valueOf(frames[i][j * 2 + 2]);
                if (preFeaTimes.containsKey(xy)) {
                    //判断如果本次输入的键值对在上一行出现过，那么该键值对的次数
                    //等于上一行中记录的出现次数+1
                    feaTimes.put(xy, preFeaTimes.get(xy) + 1);
                }
                else {
                    //如果该键值对没有出现过或者没在上一行出现过，说明该键值对
                    //要么根本没出现过要么中间断了，所以重新置为1
                    feaTimes.put(xy, 1);
                }
                count = Math.max(count, feaTimes.get(xy));
            }
            //清理：是因为本行已经遍历完了，键值对的更新信息已经保存在feaTimes中了
            preFeaTimes.clear();
             //因为要继续判断下一行的键值对信息，下一行是依赖本行的
            preFeaTimes.putAll(feaTimes);
            feaTimes.clear();
        }
        return count;
    }
}