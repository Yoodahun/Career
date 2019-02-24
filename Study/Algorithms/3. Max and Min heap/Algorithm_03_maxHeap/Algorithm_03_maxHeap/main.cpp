//
//  main.cpp
//  Algorithm_03_maxHeap
//
//  Created by 유다훈 on 2017. 9. 21..
//  Copyright © 2017년 yoo_dahun. All rights reserved.
//

#include <iostream>
#include <time.h>
#include <stdlib.h>
#include <cmath>


int Parent(int i) {
    if( (i%2) == 0) {
        return (i-2)/2;
    } else {
        return (i-1)/2;
    }
}

int Left(int i) {
    return 2*i+1;
}
int Right(int i) {
    return i*2+2;
}
void Swap(int array[], int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}
void max_heapify(int array[], int i, int arrayLength){
  
    int left = Left(i);
    int right = Right(i);
    int largest;
    
    if (left < arrayLength && array[left] > array[i]) {
        largest = left;
    } else {
        largest = i;
    }
    if (right < arrayLength && array[right] > array[largest])
        largest = right;
    if (largest != i) {
        Swap(array, i, largest);
        
        max_heapify(array, largest, arrayLength);
    }
    
    
}

void build_max_heap(int array[], int arrayLength) {
    for (int i  = floor(arrayLength/2); i >= 0; i-- ){
        max_heapify(array, i, arrayLength);
    }
}

int heap_extract_max(int array[], int arrayLength) {
    build_max_heap(array, arrayLength);
    int maxValue = array[0];
    Swap(array, 0, arrayLength-1);
    arrayLength = arrayLength-1;
    max_heapify(array, 1, arrayLength);
    return maxValue;
}

void max_heapsort(int array[], int arrayLength) {
    build_max_heap(array, arrayLength);
    
    for(int i= arrayLength-1; i > 0; i--) {
        Swap(array, 0, i);
        arrayLength = arrayLength-1;
        max_heapify(array, 0, arrayLength);
    }
}

void max_heap_increase_key(int array[], int i, int key) {
    if (key > array[i]){
        array[i] = key;
        while( i>0 && array[Parent(i)] < array[i]) {
            Swap(array, i, Parent(i));
            i = Parent(i);
        }
    }
}

void max_heap_insert(int array[], int key, int arrayLength) {
    array = (int *)realloc(array, (arrayLength * sizeof(int)));
    array[arrayLength-1] = -1111111;
    max_heap_increase_key(array, arrayLength-1, key);
}


int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    
     FILE *rf = fopen("test_10.txt", "r");
    FILE *wf = fopen("out_10.txt", "w");
    
    if ( rf == NULL)
        printf("read file open error\n");
    if ( wf == NULL)
        printf("write file open error\n");
    
    int index = 0;
    int size = 10;
    int *array = (int*)malloc(sizeof(int) * size);
    
    
    while (!feof(rf)) {
        int value = 0;
        fscanf(rf, "%d", &value);
        array[index++] = value;
//        printf("%d\n", value);
    }
    int arrayLength = size; //배열의 길이 계산
    
    /* 시간 측정에 사용할 변수들 선언 */
    clock_t start1, end1, start2, end2, start3, end3, start4, end4, start5, end5, start6, end6;
    double sortingTime;
  
    
    
    /* max heapify */
    
    start1 = clock();
    max_heapify(array, 0, arrayLength);
    end1 = clock();
    sortingTime = (double)(end1 - start1) / CLOCKS_PER_SEC; //시간측정
    printf("%s\n", " max heapify 정렬시간");
    printf("%lf\n", sortingTime);
    
    /* max heapify END */
    
    
    
    /* build max heap */
    
    start2 = clock();
    build_max_heap(array, arrayLength);
    end2 = clock();
    sortingTime = (double)(end2 - start2) / CLOCKS_PER_SEC; //시간측정
    printf("%s\n", " build max heap 정렬시간");
    printf("%lf\n", sortingTime);
    
    /* build max heap END */
    
    
    
    /* heap extract max*/
    start3 = clock();
    printf("%d\n", heap_extract_max(array, arrayLength));
    end3 = clock();
    sortingTime = (double)(end3 - start3) / CLOCKS_PER_SEC; //시간측정
    printf("%s\n", "heap extract max 정렬시간");
    printf("%f\n", sortingTime);
    /* heap extract max END */
    
 
    
//    build_max_heap(array, arrayLength); //추출하게되면 추출된 원소는 배열의 맨 뒤로 가므로 다음 작업을 위해 초기화해주는 작업.
    

    
    /* max heap insert */
    start5 = clock();
    arrayLength++;
    max_heap_insert(array, 100000001, arrayLength);//임의의 값 10000입력.
    end5 = clock();
    sortingTime = (double)(end5- start5) / CLOCKS_PER_SEC;
    printf("%s\n", "max heap insert 정렬시간");
    printf("%f\n", sortingTime);
    /* max heap insert  END */
    
    /* max heap increase */
    start6 = clock();
    max_heap_increase_key(array, 9, 100000000); //임의의 값
    end6 = clock();
    sortingTime = (double)(end6- start6) / CLOCKS_PER_SEC;
    printf("%s\n", "max heap increase key 정렬시간");
    printf("%f\n", sortingTime);
    /* max heap increase  END */
    
    
    /* max heap sort */
    
    start4 = clock();
    printf("%d", arrayLength);
    max_heapsort(array, arrayLength);
    end4 = clock();
    
    sortingTime = (double)(end4- start4) / CLOCKS_PER_SEC;
    printf("%s\n", "max heap sort 정렬시간");
    printf("%f\n", sortingTime);
    //
    /* max heap sort END */
    

    
    

    printf("%s\n", "출력합니다.");
//    printf("%f\n", sortingTime);
    for(int i=0; i < arrayLength; i++) {
        printf("%d\n", array[i]);
        fprintf(wf, "%d\n", array[i]);
    }
    
 
    
    return 0;
}

