//
//  main.cpp
//  Algorithm_05_RandomizedSelect
//
//  Created by 유다훈 on 2017. 10. 24..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>
#include <stdlib.h>

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

int randomizedPartition(int array[], int pivot, int right) {
    
    
    int i = (int)(( rand() % (pivot-right)+pivot ));
    swap(array, right, i);
    //printf("%d", array[pivot]);
    return  partition(array, i, right);
}


void quicksort(int array[], int pivot, int right){
    if (pivot < right) {
        int q = randomizedPartition(array, pivot, right);
        quicksort(array, pivot, q-1);
        quicksort(array, q+1, right);
        
    }
}
int randomized_select(int array[], int pivot, int right, int index) {
    if (pivot == right)
        return array[pivot];
    int q = randomizedPartition(array, pivot, right);
    int k = q - pivot +1;
    
    if (index == k )
        return array[q];
    
    if (index < k)
        return randomized_select(array, pivot, q-1, index);
    else
        return randomized_select(array, q+1, right, index-k);
}


int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    
    FILE *rf = fopen("input.txt", "r");
    FILE *wf = fopen("output.txt", "w");
    
    if ( rf == NULL)
        printf("read file open error\n");
    if ( wf == NULL)
        printf("write file open error\n");
    
    int array[500];
    int index = 0;
    
    while (!feof(rf)) {
        int value = 0;
        fscanf(rf, "%d", &value);
        array[index++] = value;
        printf("%d\n", value);
    }
    int arrayLength = (sizeof(array) / sizeof(array[0])); //배열의 길이계산
    
    printf("2번째로 작은 값\n");
    printf("%d\n", randomized_select(array, 1, 499, 2));
    
    for(int i=1; i < arrayLength ; i++) {
//        printf("%d\n", array_b[i]);
        fprintf(wf, "%d\n", array[i]);
    }
    
    return 0;
}
