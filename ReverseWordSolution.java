public class ReverseWordSolution {
    /**
     * @param s: A string
     * @return: A string
     */
    public String reverseWords(String s) {
        if (s.length() == 0 || s == null) { //string调用的是length()方法
            return ""; //答案返回的是空格是不对的，就应该返回空字符串
        }
        //按照空格将s切分
        String[] array = s.split(" ");
        StringBuilder sb = new StringBuilder();
        //从后往前遍历array， 在sb中插入单词
        for (int i = array.length - 1; i >= 0; i--) { //array用的是length属性
            if(!array[i].equals("")) { //如果字符串中包含多个空格那么按照空格划分还是有可能是空格？
                if (sb.length() > 0) { //如果已经有数据了那么插入新数据前应该先放个空格
                    sb.append(" ");
                }

                sb.append(array[i]);
            }
        }
        return sb.toString();
    }
}