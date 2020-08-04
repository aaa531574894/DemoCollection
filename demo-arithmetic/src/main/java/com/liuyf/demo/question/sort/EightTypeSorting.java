package com.liuyf.demo.question.sort;

import java.util.Random;

/**
 * 8大排序
 * 参考动态图：https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
 * <p>
 * <p>
 * Created by liuyf on 2020/8/3.
 */

public class EightTypeSorting {
    public static final boolean print = true;

    public static void main(String[] args) {

        int length = 50;
        int[] testData2 = new int[length];
        Random randomNumber = new Random();
        for (int i = 0; i < length; i++) {
            testData2[i] = randomNumber.nextInt(20);
        }
        print(bubbleSort(testData2), print);
        print(insertSort(testData2), print);
        print(shellSort(testData2), print);
        print(simpleSelectSort(testData2), print);
        print(heapSort(testData2), print);
        print(mergingSort(testData2), print);
        print(quickSort(testData2), print);
        System.out.println(binarySearch(quickSort(testData2),10));
    }


    /**
     * 冒泡排序<br/>
     * <p>
     * 每次都从数组的第一个元素开始，两两相邻比较，下个元素比自己小，就交换位置，然后继续比较。
     * 直到最后一趟冒泡时，没有任何元素交换，结束排序
     */
    public static int[] bubbleSort(int[] fromArray) {
        System.out.println("冒泡排序");
        int[] unsortedArray = new int[fromArray.length];
        System.arraycopy(fromArray, 0, unsortedArray, 0, unsortedArray.length);
        long beginTime = System.currentTimeMillis();

        boolean changed;
        while (true) {
            changed = false;
            for (int index = 1; index < unsortedArray.length; index++) {
                if (unsortedArray[index - 1] > unsortedArray[index]) {
                    swap(unsortedArray, index - 1, index);
                    changed = true;
                }
            }
            if (!changed) break;
        }
        long timeCost = System.currentTimeMillis() - beginTime;
        System.out.println("耗时：" + timeCost);
        return unsortedArray;
    }


    /**
     * 简单选择排序
     * <br/>
     * 在无序部分中做遍历，找到最小的元素，和无序部分中的首位做位置交换！
     */
    public static int[] simpleSelectSort(int[] fromArray) {
        System.out.println("简单选择排序");
        int[] sortedArray = new int[fromArray.length];
        System.arraycopy(fromArray, 0, sortedArray, 0, fromArray.length);
        long beginTime = System.currentTimeMillis();


        for (int index = 0; index < sortedArray.length; index++) {
            int toSwapIndex = index;
            int minmum = sortedArray[index];
            for (int current = index + 1; current < sortedArray.length; current++) {
                if (sortedArray[current] < minmum) {
                    minmum = sortedArray[current];
                    toSwapIndex = current;
                }
            }
            sortedArray[toSwapIndex] = sortedArray[index];
            sortedArray[index] = minmum;
        }
        long timeCost = System.currentTimeMillis() - beginTime;
        System.out.println("耗时：" + timeCost);
        return sortedArray;
    }


    /**
     * 插入排序
     * <br/>
     * 每一步都拿临时变量值去和已经排好序的数组去比对，如果比他小，则被对比的元素后移；
     * 如果比他大，则停止，将临时值插入
     */
    public static int[] insertSort(int[] fromArray) {
        System.out.println("简单插入排序");
        int[] sortedArray = new int[fromArray.length];
        System.arraycopy(fromArray, 0, sortedArray, 0, fromArray.length);
        long beginTime = System.currentTimeMillis();
        for (int index = 0; index < sortedArray.length; index++) {
            int temp = sortedArray[index];

            // 3,4,7,8,  5
            int pre = index - 1, cur = index;

            for (; pre >= 0 && sortedArray[pre] > sortedArray[cur]; cur--, pre--) {
                swap(sortedArray, pre, cur);  //前一元素交换方式
            }

            /*for (; pre >= 0 && sortedArray[pre] > temp; pre--) {
                sortedArray[pre + 1] = sortedArray[pre];  //前一元素后移
            }
            sortedArray[pre + 1] = temp;*/
        }
        long timeCost = System.currentTimeMillis() - beginTime;
        System.out.println("耗时：" + timeCost);
        return sortedArray;
    }

    /**
     * 希尔排序
     * <br/>
     * 由插入排序演进而来，利用了插入排序在对基本有序的数组排序时效率高的特点进行了优化<br/>
     * 希尔排序是设定一定的增量，增量相同的元素为子数组
     * 做一此插入排序，使得子序列逐渐有序
     * 然后增量-1
     * 再在子数组内做一次插入排序
     * 直到增量为1时，数组基本有序，最后做一次全数组的插入排序即可
     */
    public static int[] shellSort(int[] fromArray) {
        System.out.println("希尔排序");
        int[] sortedArray = new int[fromArray.length];
        System.arraycopy(fromArray, 0, sortedArray, 0, fromArray.length);
        long beginTime = System.currentTimeMillis();

        int increment = (fromArray.length / 2) + 1;
        for (; increment > 0; increment--) {
            for (int index = increment; index < fromArray.length; index += increment) {
                for (int j = index; j - increment >= 0 && sortedArray[j] < sortedArray[j - increment]; j -= increment) {
                    swap(sortedArray, j, j - increment);
                }
            }
        }
        long timeCost = System.currentTimeMillis() - beginTime;
        System.out.println("耗时：" + timeCost);
        return sortedArray;
    }


    /**
     * 堆排序
     * <br/>
     * 高级版选择排序， 把数组看做一颗无序的平衡二叉树，所有元素从上到下从左到右按顺序排放，
     * 然后第一次，从下至上，从右往左，建堆，叶子节点比父节点大的值，都交换位置；如果叶子节点变了，要再判断一次子堆是否要调整
     * 建完第一次堆之后，把堆顶的最大元素与堆尾的元素互换，删除堆尾的最大值，然后开始循环 互换位置，调整堆！
     */
    public static int[] heapSort(int[] fromArray) {
        System.out.println("堆排序");
        int[] sortedArray = new int[fromArray.length];
        System.arraycopy(fromArray, 0, sortedArray, 0, fromArray.length);
        long beginTime = System.currentTimeMillis();

        int index = sortedArray.length - 1;

        //第一次建堆
        for (int current = index; current >= 2; current--) {
            int leftIndex = current % 2 > 0 ? current : current - 1;

            if (Math.max(sortedArray[current], sortedArray[leftIndex]) > sortedArray[parentIndex(current)]) {
                if (sortedArray[current] >= sortedArray[leftIndex]) {
                    swap(sortedArray, current, parentIndex(current));
                    adjustChildHeap(sortedArray, current, index);
                } else {
                    swap(sortedArray, leftIndex, parentIndex(leftIndex));
                    adjustChildHeap(sortedArray, leftIndex, index);
                }
            }
            if (current % 2 == 0) current--;
        }
        //循环重建堆，移动最大值到队尾
        while (index >= 0) {
            swap(sortedArray, 0, index);
            adjustChildHeap(sortedArray, 0, --index);
        }

        long timeCost = System.currentTimeMillis() - beginTime;
        System.out.println("耗时：" + timeCost);
        return sortedArray;
    }

    private static int parentIndex(int current) {
        return (current + 1) / 2 - 1;
    }

    private static int leftChildIndex(int parentIndex) {
        return (parentIndex * 2) + 1;
    }

    // end以后的元素已有序，不可再拿出来排序
    private static void adjustChildHeap(int[] fromArray, int changedParentIndex, int end) {
        int leftChildIndex = leftChildIndex(changedParentIndex);
        int rightChildIndex = leftChildIndex + 1;

        if (rightChildIndex > end) rightChildIndex = leftChildIndex;

        if (leftChildIndex <= end && Math.max(fromArray[leftChildIndex], fromArray[rightChildIndex]) > fromArray[changedParentIndex]) {
            if (fromArray[leftChildIndex] >= fromArray[rightChildIndex]) {
                swap(fromArray, leftChildIndex, changedParentIndex);
                adjustChildHeap(fromArray, leftChildIndex, end);
            } else {
                swap(fromArray, rightChildIndex, changedParentIndex);
                adjustChildHeap(fromArray, rightChildIndex, end);
            }
        }

    }

    private static void swap(int[] fromArray, int a, int b) {
        int temp = fromArray[a];
        fromArray[a] = fromArray[b];
        fromArray[b] = temp;
    }


    /**
     * 归并排序<br/>
     * 将长度为N的数组分散至1，然后 1 1 合并排序为2，然后2 2 合并排序为4，直至再次全部整合为N
     */

    public static int[] mergingSort(int[] fromArray) {
        System.out.println("归并排序");
        int[] unsortedArray = new int[fromArray.length];
        System.arraycopy(fromArray, 0, unsortedArray, 0, unsortedArray.length);
        long beginTime = System.currentTimeMillis();

        split(unsortedArray, 0, fromArray.length - 1);
        long timeCost = System.currentTimeMillis() - beginTime;

        System.out.println("耗时：" + timeCost);
        return unsortedArray;
    }


    //0 1 2 3 4 5 6  7 8 9
    private static void split(int[] fromArray, int startIndex, int endIndex) {
        int length = endIndex - startIndex + 1;
        if (length >= 2) {
            int middle = startIndex + (length - 1) / 2;
            split(fromArray, startIndex, middle);
            split(fromArray, middle + 1, endIndex);
            merge(fromArray, startIndex, middle, middle + 1, endIndex);
        }

    }

    // 合并两个子集
    private static void merge(int[] fromArray, int leftStartIndex, final int leftEndIndex, int rightStartIndex, final int rightEndIndex) {
        final int begin = leftStartIndex;
        if (leftStartIndex == rightEndIndex) return;
        int[] tempResult = new int[rightEndIndex - leftStartIndex + 1];

        for (int index = 0; leftStartIndex <= leftEndIndex && rightStartIndex <= rightEndIndex; index++) {
            if (fromArray[leftStartIndex] <= fromArray[rightStartIndex]) {
                tempResult[index] = fromArray[leftStartIndex];
                leftStartIndex++;
            } else {
                tempResult[index] = fromArray[rightStartIndex];
                rightStartIndex++;
            }
            if (leftStartIndex > leftEndIndex) { //left empty
                while (rightStartIndex <= rightEndIndex) {
                    tempResult[++index] = fromArray[rightStartIndex];
                    rightStartIndex++;
                }
            } else if (rightStartIndex > rightEndIndex) { //right empty
                while (leftStartIndex <= leftEndIndex) {
                    tempResult[++index] = fromArray[leftStartIndex];
                    leftStartIndex++;
                }
            }


        }
        System.arraycopy(tempResult, 0, fromArray, begin, tempResult.length);
    }


    /**
     * 快速排序<br/>
     * 冒泡排序法的升级版
     */

    public static int[] quickSort(int[] fromArray) {
        System.out.println("快速排序");
        int[] unsortedArray = new int[fromArray.length];
        System.arraycopy(fromArray, 0, unsortedArray, 0, unsortedArray.length);
        long beginTime = System.currentTimeMillis();

        quickSort(unsortedArray, 0, unsortedArray.length -1 );

        long timeCost = System.currentTimeMillis() - beginTime;
        System.out.println("耗时：" + timeCost);
        return unsortedArray;
    }

    private static void quickSort(int[] fromArray,int startIndex,int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int idx = compareAndSwap(fromArray, startIndex, endIndex);
        quickSort(fromArray, startIndex, idx - 1);
        quickSort(fromArray, idx, endIndex);

    }
    //返回pivot交换位置后的基准索引值
    private static int compareAndSwap(int[] fromArray,int start,int end){
        //在这里将数组第一个元素选取为Pivot
        final int pivot = start;
        start ++;
        int result = pivot;
        for (; start <= end; ) {
            while (fromArray[start] <= fromArray[pivot] && start != end) {
                start++;
            }
            while (fromArray[end] >= fromArray[pivot] && start != end) {
                end--;
            }
            swap(fromArray, start, end);
            if(start == end) {
                result = start;
                break;
            }
        }
        if (fromArray[result] > fromArray[pivot]) {
            swap(fromArray, pivot, result - 1);
        } else if (fromArray[result] < fromArray[pivot]) {
            swap(fromArray, pivot, result);
        }
        return result;
    }




    private static void print(int[] fromArray, boolean print) {
        if (!print) return;
        for (int i : fromArray) {
            System.out.print(i + " ");

        }
        System.out.println("");
    }


    public static Integer binarySearch(int[] array, int value) {
        return bs(array, 0, array.length - 1, value);
    }

    // 0 1 2 3 4 5 6  2
    private static Integer bs(int[] array, int start, int end ,int value) {
        if(start>end) return null;

        int middleIndex = (start + end) / 2;
        if(array[middleIndex] == value) {
            return middleIndex;
        }else if (array[middleIndex] > value) {
            end = middleIndex-1;
        }else {
            start = middleIndex+1;
        }
        return bs(array, start, end, value);
    }
}
