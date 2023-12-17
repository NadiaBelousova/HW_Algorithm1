package org.example;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private Integer[] storage;


    private int size;

    public IntegerListImpl() {
        storage = new Integer[10];
    }

    public IntegerListImpl(int initSize) {

        storage = new Integer[initSize];
    }

    @Override
    public Integer add(Integer item) {

        growIfNeeded();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {

        growIfNeeded();
        validateItem(item);
        validateIndex(index);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {

        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {

        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public Integer remove(int index) {

        validateIndex(index);
        Integer item = storage[index];
        if (index != size) {
            System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {

        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {

        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {

        validateIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;

    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void growIfNeeded() {
        if (size == storage.length) {
            grow();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    private void sort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private boolean binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void grow() {
        storage = Arrays.copyOf(storage, size + size / 2);
    }

    public static int[] generateRandomArray1() {
        java.util.Random random = new java.util.Random();
        int[] arr1 = new int[100000];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = random.nextInt(50_000) + 50_000;
        }
        return arr1;
    }

    public static Integer[] copyRandomArray() {
        return Arrays.copyOf(generateRandomArray1(), generateRandomArray1().length);
    }


    private static void swapElements(Integer[] arr1, int i1, int i2) {
        int tmp = arr1[i1];
        arr1[i1] = arr1[i2];
        arr1[i2] = tmp;
        ;
    }

    public static void sortArray(Integer[] arr) {
        long start = System.currentTimeMillis();
        Arrays.sort(arr);
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void sortBubble(Integer[] arr) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    public static void sortSelection(Integer[] arr) {
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



    private  void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private Integer partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, end);
        return i + 1;
    }

    public  void sortInsertion(Integer[] arr) {
        long start = System.currentTimeMillis();
        quickSort(arr, 0,arr.length -1);
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

}


