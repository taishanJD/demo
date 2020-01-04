package com.example.algorithm.ready;

/**
 * [Jedi] 移动数组元素
 * 2020-1-4
 * <p>
 * 问题
 * 给一个int类型数组arr，将数组中非0的元素移动到最前面，并返回非0元素数量: N。
 * 要求 在原数组上操作、移动完成后元素可以是任意顺序，除前N个位置外的元素任意。时间复杂度O(n), 空间O(1)
 * 例如：
 * 对于数组[1, 0, 2, 0, 3, 4] ，其中一种解为 返回值=4， 移动后数组为：[1, 2, 3, 4, 3, 4]
 */
public class MoveArrayElement {

    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 0, 0, 3, 4};
//        int res1 = move_1(arr);
//        System.out.println("move_1: res == "+res1);
        int res2 = move_2(arr);
        System.out.println("move_2: res == " + res2);

        for (int i : arr) {
            System.out.print(i + ",");
        }
    }

    //思路一：用一个i计数和记录位置，然后遍历数组，遇到不为0的元素，把该元素放到i位置，然后i++；遇到为0的元素i不变
    private static int move_1(int[] arr) {
        int i = 0;
        for (int element : arr) {
            if (element != 0) {
                arr[i++] = element;
            }
        }
        return i;
    }

    //思路二：
    // 用i从首位开始往后，遇到不为零的元素跳过+1，直到找到为0的元素，
    // 用j从末尾开始往前，遇到为零的元素跳过-1，直到找到不为0的元素，
    // 此时将j位置上不为零的元素放到i位置上，然后i继续往后，j继续往前，直到i,j相遇
    private static int move_2(int[] arr) {
        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            while (i < j && arr[i] != 0) {
                i++;
            }
            while (i < j && arr[j] == 0) {
                j--;
            }
            if (i < j) {
                arr[i++] = arr[j--];
            }
        }
        return i;
    }
}
