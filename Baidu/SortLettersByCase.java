package Baidu;
/**
Given a string which contains only letters. Sort it by lower case first and upper case second.
It's NOT necessary to keep the original order of lower-case letters and upper case letters.
Example 1:
	Input:  "abAcD"
	Output:  "acbAD"

Example 2:
	Input: "ABC"
	Output:  "ABC"

 */
public class SortLettersByCase {
    public void sortletters(char[] chars) {
        int i = 0, j = chars.length - 1;
        while (i <= j) {
            while (i <= j && Character.isLowerCase(chars[i])) {
                i++;
            }//find out the first Uppercase from front to back
            while (i <= j && Character.isUpperCase(chars[i])) {
                j--;
            }//find out the first lowercase reversely 

            if (i <= j) {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }
    }
}