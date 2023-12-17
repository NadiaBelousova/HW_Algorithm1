package org.example;

import java.util.Arrays;



public class Main {
    public static int[] generateRandomArray1 () {
        java.util.Random random = new java.util.Random();
        int[] arr1 = new int[30];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = random.nextInt(50_000) + 50_000;
        }
        return arr1;
    }

    public static int[] generateRandomArray2 () {
        return Arrays.copyOf(generateRandomArray1(), generateRandomArray1().length);
    }

    public static int[] generateRandomArray3 () {
        return Arrays.copyOf(generateRandomArray1(), generateRandomArray1().length);
    }
    private static void swapElements ( int[] arr1, int indexA, int indexB){
        int tmp = arr1[indexA];
        arr1[indexA] = arr1[indexB];
        arr1[indexB] = tmp;
        ;
    }

    private static void sortArray ( int[] arr1, int indexA, int indexB){
        long start = System.currentTimeMillis();
        Arrays.sort(arr1);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void sortBubble ( int[] arr3){
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr3.length - 1; i++) {
            for (int j = 0; j < arr3.length - 1 - i; j++) {
                if (arr3[j] > arr3[j + 1]) {
                    swapElements(arr3, j, j + 1);
                }
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void sortSelection ( int[] arr){
        long start = System.currentTimeMillis();

        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void sortInsertion ( int[] arr){
        long start = System.currentTimeMillis();

        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
        System.out.println(System.currentTimeMillis() - start);
    }
    public static void main(String[] args) {
        generateRandomArray1();
        generateRandomArray2();
        generateRandomArray3();
        sortBubble(generateRandomArray1());
        sortInsertion(generateRandomArray1());
        sortSelection(generateRandomArray1());


    }
    }








