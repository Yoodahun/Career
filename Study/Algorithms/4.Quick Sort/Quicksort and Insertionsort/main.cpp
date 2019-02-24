//
//  main.cpp
//  Algorithm_04_QuickAndInsertion
//
//  Created by 유다훈 on 2017. 10. 12..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>
#include <stdlib.h>
#include <time.h>

void swap(int array[], int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

int partition(int array[], int pivot, int right) {
    int x = array[right];
    int i = pivot-1;
    
    for (int j = pivot; j <= right-1 ; j++) {
        if (array[j] < x ) {
            i++;
            swap(array, i, j);
        }
    }
    swap(array, i+1, right);
    return i+1;
}

void quicksort(int array[], int pivot, int right){
    if (pivot < right) {
        int q = partition(array, pivot, right);
        quicksort(array, pivot, q-1);
        quicksort(array, q+1, right);
        
    }
}
void testingQuickSort(int array[], int pivot, int right, int k) {
    if (right - pivot > k) {
        int q = partition(array, pivot, right);
        testingQuickSort(array, pivot, q-1, k);
        testingQuickSort(array, q+1, right, k);
        
    }
}

void insertionSort(int array[], int pivot, int right) {
    int i, j, key;
    
    for(j= pivot+1; j<right; j++) {
        key = array[j];
        for(i = j-1; i >= pivot && array[i] > key; i--) {
            array[i+1] = array[i];
        }
        array[i+1] = key;
    }
}

void quickSortAndInsertionSort(int array[], int pivot, int right, int k) {
    testingQuickSort(array, pivot, right, k);
    insertionSort(array, pivot, right);
    
}


int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    
    
    FILE *rf = fopen("test1.txt", "r");
//    FILE *wf = fopen("out2.txt", "w");
    
    if ( rf == NULL)
        printf("read file open error\n");
//    if ( wf == NULL)
//        printf("write file open error\n");
    
    int array[50000];
    int index=0;
    
    while (!feof(rf)) {
        int value = 0;
        fscanf(rf, "%d", &value);
        array[index++] = value;
        // printf("%d\n", value);
    }
    clock_t start1, end1, start2, end2;
    double sortingTime;
    
    int arrayLength = (sizeof(array) / sizeof(array[0])); //배열의 길이계산
    
    start1 = clock();
    quicksort(array, 0, 49999);
    end1 = clock();
    sortingTime = (double)(end1 - start1) / CLOCKS_PER_SEC; //시간측정
    printf("QuickSort");
    printf("%f\n", sortingTime);
    
    start2 = clock();
    quickSortAndInsertionSort(array, 0, 49999, 49955);
    end2 = clock();
    sortingTime = (double)(end2 - start2) / CLOCKS_PER_SEC; //시간측정
    printf("QuickSort and InsertionSort");
    printf("%f\n", sortingTime);
    
    return 0;
}
