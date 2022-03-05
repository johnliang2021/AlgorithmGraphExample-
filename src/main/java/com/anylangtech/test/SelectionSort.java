package com.anylangtech.test;


import java.util.Arrays;

// 选择排序
public class SelectionSort {
    public static void selectionSort(int[] arr) {
        // 从数组一端开始，依次比较相邻两元素的大小，如果左边大，则交换位置，一轮比较完成后，可以将最大元素送到最右端
        // 重复以上方法，比较剩余元素
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1-i; j++) {
                if (arr[j+1] < arr[j]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,5,85,1,2,4,5,6,9,6,8,5,4,5,2,555,66,258,65,5,74,852,45,2,3,6,9,5,5,5,4};
        selectionSort(arr);
        Arrays.stream(arr).forEach(p -> System.out.println(p));
//        Stream.of(arr).forEach(p -> System.out.println(p));
      /*  for (int a:arr) {
            System.out.println(a);
        }*/
    }
}
