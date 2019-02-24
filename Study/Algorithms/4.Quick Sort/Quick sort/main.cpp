//
//  main.cpp
//  Algorithm_04_QuickSort
//
//  Created by 유다훈 on 2017. 9. 28..
//  Copyright © 2017년 yoo_dahun. All rights reserved.
//

#include <iostream>

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


int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    
    
    
    FILE *rf = fopen("test2.txt", "r");
    FILE *wf = fopen("out2.txt", "w");
    
    if ( rf == NULL)
        printf("read file open error\n");
    if ( wf == NULL)
        printf("write file open error\n");
    
    int array[50000];
    int index=0;
    
    while (!feof(rf)) {
        int value = 0;
        fscanf(rf, "%d", &value);
        array[index++] = value;
//        printf("%d\n", value);
    }
    
    int arrayLength = (sizeof(array) / sizeof(array[0])); //배열의 길이계산
    
    quicksort(array, 0, 49999);
    
    printf("%s\n", "출력합니다.");
    //    printf("%f\n", sortingTime);
    for(int i=0; i < arrayLength ; i++) {
        printf("%d\n", array[i]);
        fprintf(wf, "%d\n", array[i]);
    }
    
    

    
    
    return 0;
}
