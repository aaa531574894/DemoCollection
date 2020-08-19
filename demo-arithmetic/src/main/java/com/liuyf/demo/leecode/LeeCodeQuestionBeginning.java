package com.liuyf.demo.leecode;


import java.util.Arrays;

public class LeeCodeQuestionBeginning {
    public static void main(String[] args) {


    }


    /**
     * <b>题目: 只出现一次的数字</b>
     *
     * <p>给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。</p>
     * <br/>
     * <b>说明：</b>
     *
     * <p>你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？</p>
     *
     * <b>示例 1:</b>
     * <br/>
     * 输入: <br/>
     * [2,2,1]<br/>
     * 输出: 1<br/>
     * 示例 2:<br/>
     * <br/>
     * 输入: <br/>
     * [4,1,2,1,2]<br/>
     * 输出: 4<br/>
     * <p>
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xm0u83/
     * <p>
     * <p>
     * 答案:
     * 答案是使用位运算。对于这道题，可使用异或运算 \oplus⊕。异或运算有以下三个性质。
     * <p>
     * 任何数和 00 做异或运算，结果仍然是原来的数，
     * 任何数和其自身做异或运算，结果是 0
     */
    static class Solution {
        public int singleNumber(int[] nums) {
            int result = nums[0];
            for (int index = 1; index < nums.length; index++) {
                result ^= nums[index];
            }
            return result;
        }
    }


    /**
     * 题目: 多数元素
     * <p>
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * <p>
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     * <p>
     * 输入: [2,2,1,1,1,2,2]
     * 输出: 2
     * <p>
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xm77tm/
     */
    static class Solution2 {
        public int majorityElement(int[] nums) {

            final int middleIndex = nums.length / 2;
            Arrays.sort(nums);
            return nums[middleIndex];
        }
    }


    /**
     * 搜索二维矩阵 II
     * <p>
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     * <p>
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * 示例:
     * <p>
     * 现有矩阵 matrix 如下：
     * <p>
     * [
     * [1,   4,  7, 11, 15],
     * [2,   5,  8, 12, 19],
     * [3,   6,  9, 16, 22],
     * [10, 13, 14, 17, 24],
     * [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     * <p>
     * 给定 target = 20，返回 false。
     * <p>
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xmlwi1/
     * <p>
     * 答案:
     * 对角线 二分 + 每列二分
     */

    static class Solution3 {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) return false;

            for (int i = 0; i < matrix.length; i++) {
                if (target < matrix[i][0]) continue;

                int left = 0, right = matrix[i].length - 1;
                int middle;
                while (left <= right) {
                    middle = (left + right) / 2;
                    if (matrix[i][middle] == target) {
                        return true;
                    } else if (matrix[i][middle] > target) {
                        right = middle - 1;
                    } else if (matrix[i][middle] < target) {
                        left = middle + 1;
                    }
                }
            }
            return false;
        }
    }

    /**
     * 合并两个有序数组
     * <p>
     * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
     * <p>
     *  
     * <p>
     * 说明:
     * <p>
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     *  
     * <p>
     * 示例:
     * <p>
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * <p>
     * 输出: [1,2,2,3,5,6]
     * <p>
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xmi2l7/
     *
     * 答案:
     * 思路: 两个有序数组合并, 典型的归并排序~
     * 但是有个优化方案,可以不用浪费额外的空间,那就是从尾部向头部开始比较!!! 然后插入 num1 的队尾
     */
    static class Solution4 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] tempResult = new int[m + n];

            for (int i1 = 0, i2 = 0, i = 0; i < tempResult.length; i++) {
                if (i1 == m) { //nums1 empty
                    System.arraycopy(nums2, i2, tempResult, i, n - i2 );
                    break;
                } else if (i2 == n) { //nums2 empty
                    System.arraycopy(nums1, i1, tempResult, i, m - i1 );
                    break;
                } else if (nums1[i1] <= nums2[i2]) {
                    tempResult[i] = nums1[i1];
                    i1++;
                } else {
                    tempResult[i] = nums2[i2];
                    i2++;
                }
            }
            System.arraycopy(tempResult, 0, nums1, 0, m + n);
        }

    }

    /**
     * 鸡蛋掉落
     *
     * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
     *
     * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
     *
     * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
     *
     * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
     *
     * 你的目标是确切地知道 F 的值是多少。
     *
     * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
     *
     *  
     *
     * 示例 1：
     *
     * 输入：K = 1, N = 2
     * 输出：2
     *
     * 解释：
     *
     * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
     * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
     * 如果它没碎，那么我们肯定知道 F = 2 。
     * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
     * 示例 2：
     *
     * 输入：K = 2, N = 6
     * 输出：3
     * 示例 3：
     *
     * 输入：K = 3, N = 14
     * 输出：4
     *  
     *
     * 提示：
     *
     * 1 <= K <= 100
     * 1 <= N <= 10000
     *
     * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xmup75/
     */
    static class Solution5{
        //太难了

    }


}
