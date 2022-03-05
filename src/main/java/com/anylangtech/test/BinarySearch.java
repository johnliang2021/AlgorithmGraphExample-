package com.anylangtech.test;

// 二分法
public class BinarySearch {
    public static int binarySearch(int[] sortedArray, int e) {
        int low = 0;
        int high = sortedArray.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (e == sortedArray[mid]) {
                return mid;
            }
            if (e < sortedArray[mid]) {
                high = mid - 1;
            }
            if (e > sortedArray[mid]) {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,5,9,47,49,85,456,2584};
        int i = binarySearch(a,2584);
        System.out.println(i);
    }
}
