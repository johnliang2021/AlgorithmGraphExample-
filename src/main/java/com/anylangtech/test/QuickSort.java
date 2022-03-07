package com.anylangtech.test;

import java.util.Arrays;

/**
 * @author
 * @version V1.0
 * @description 快速排序
 * @date 2022/3/5 15:30
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5,2,6,9,7,19,59,74,25,1,2,25,23,65,85,2,3,1,5,1,0,0,1};
        quickSort(arr,0,arr.length-1);
        Arrays.stream(arr).forEach(p -> System.out.println(p));
//        Stream.of(arr).forEach(p -> System.out.println(p));
      /*  for (int a:arr) {
            System.out.println(a);
        }*/
    }

    public static void quickSort(int[] arr,int start, int end) {
        if (start >= end) {
            return;
        }
        int left = start;
        int right = end;
        int pivot = arr[start];
        int temp;
        while (left < right) {
            //如果左边的先走,那么每一轮最后一个必然是比基数大的数,然后右边的指针移动到和左边的指针同一个位置的时候,
            //跳出外层循环,轴值和碰头处的元素交换,而这个元素是大于轴值的,顺序就乱了
            //如果右边的先走,那么每一轮最后一个必然是比基数小的数,当两个指针碰头时,这个数和基数互换是没问题的,
            //因为这个数小于基数
            // 从右端开始依次比较找出小于基准值的元素
            while (arr[right] >= pivot && left < right) {
                right--;
            }
            // 当右端找到目标值后,左端开始找大于基准值的元素
            while (arr[left] <= pivot && left < right) {
                left++;
            }
            // 左右两端都找到目标值后,交换两个元素的位置
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        // left = right 时,交换基准值和left处的元素
        if (left == right) {
            arr[start] = arr[left];
            arr[left] = pivot;

        }
        // 基准值归位后分别对左右子数组递归调用本函数
        quickSort(arr,start,left-1);
        quickSort(arr,left+1,end);
    }
}
