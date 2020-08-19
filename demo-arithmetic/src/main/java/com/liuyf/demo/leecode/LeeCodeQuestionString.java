package com.liuyf.demo.leecode;

import java.util.List;

/**
 * leecode 字符串篇
 * <p>
 * <p>
 * Created by liuyf on 2020/8/19.
 */

public class LeeCodeQuestionString {
    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome("race a car"));
    }


    /**
     * 验证回文串
     * <p>
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * <p>
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: "race a car"
     * 输出: false
     * <p>
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xah8k6/
     */

    static class Solution {
        public boolean isPalindrome(String s) {
            int i = 0, j = s.length() - 1;
            for (; i <= j; ) {
                char l = s.charAt(i), r = s.charAt(j);

                if (!Character.isLetterOrDigit(l)) {
                    i++;
                    continue;
                }
                if (!Character.isLetterOrDigit(r)) {
                    j--;
                    continue;
                }

                if ((Character.isDigit(l) || Character.isDigit(r)) && l != r) {  //有任何一个是数字 , 但是不相等,
                    return false;
                }
                if (Character.toLowerCase(l) != Character.toLowerCase(r)) {
                    return false;
                }
                i++;
                j--;
            }
            return true;
        }
    }


    /**
     * 分割回文串
     *
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     *
     * 返回 s 所有可能的分割方案。
     *
     * 示例:
     *
     * 输入: "aab"
     * 输出:
     * [
     *   ["aa","b"],
     *   ["a","a","b"]
     * ]
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xaxi62/
     */

    static class Solution2 {
        public List<List<String>> partition(String s) {

            return null;
        }
    }
}
