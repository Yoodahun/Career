//
//  main.cpp
//  Algorithm_03_maxHeap
//
//  Created by 유다훈 on 2017. 9. 21..
//  Copyright © 2017년 yoo_dahun. All rights reserved.
//

#include <iostream>
#include <time.h>
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
void min_heapify(int array[], int i, int arrayLength){
    
    int left = Left(i);
    int right = Right(i);
    int smallest;
    
    if (left < arrayLength && array[left] <= array[i]) {
        smallest = left;
    } else {
        smallest = i;
    }
    if (right < arrayLength && array[right] <= array[smallest])
        smallest = right;
    if (smallest != i) {
        Swap(array, i, smallest);
        
        min_heapify(array, smallest, arrayLength);
    }
    
    
}

void build_min_heap(int array[], int arrayLength) {
    for (int i  = floor(arrayLength/2); i >= 0; i-- ){
        min_heapify(array, i, arrayLength);
    }
}

int heap_extract_min(int array[], int arrayLength) {
    build_min_heap(array, arrayLength);
    int minValue = array[0];
    Swap(array, 0, arrayLength-1);
    arrayLength = arrayLength-1;
    min_heapify(array, 1, arrayLength);
    return minValue;
}

void min_heapsort(int array[], int arrayLength) {
    build_min_heap(array, arrayLength);
    
    for(int i= arrayLength-1; i >= 0; i--) {
        Swap(array, 0, i);
        arrayLength = arrayLength-1;
        min_heapify(array, 0, arrayLength-1);
    }
}

void min_heap_decrease_key(int array[], int i, int key) {
    if (key >= array[i]){
        array[i] = key;
        while( i > 0 && array[Parent(i)] >= array[i]) {
            Swap(array, i, Parent(i));
            i = Parent(i);
        }
    }
}

void min_heap_insert(int array[], int key, int arrayLength) {
    array = (int*)realloc(array, (arrayLength) * sizeof(int));
    array[arrayLength-1] = -11111111;
    min_heap_decrease_key(array, arrayLength-1, key);
}


int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    FILE *rf = fopen("test_10000.txt", "r");
    FILE *wf = fopen("out_10000.txt", "w");
    
    if ( rf == NULL)
        printf("read file open error\n");
    if ( wf == NULL)
        printf("write file open error\n");
    
    int index = 0;
    int size = 10000;
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
    
    start6 = clock();
    min_heapify(array, 0, arrayLength);
    end6 = clock();
    sortingTime = (double)(end6 - start6) / CLOCKS_PER_SEC; //시간측정
    printf("%s\n", " min heapify 정렬시간");
    printf("%lf\n", sortingTime);
    
    /* min heapify END */
    
    
    
    /* build min heap */
    
    start1 = clock();
    build_min_heap(array, arrayLength);
    end1 = clock();
    sortingTime = (double)(end1 - start1) / CLOCKS_PER_SEC; //시간측정
    printf("%s\n", " build min heap 정렬시간");
    printf("%lf\n", sortingTime);
    
    /* build min heap END */
    
    
    
    /* heap extract min*/
    start2 = clock();
    printf("%d\n", heap_extract_min(array, arrayLength));
    end2 = clock();
    sortingTime = (double)(end2 - start2) / CLOCKS_PER_SEC; //시간측정
    printf("%s\n", "heap extract min 정렬시간");
    printf("%f\n", sortingTime);
    /* heap extract min END */
    
//    build_min_heap(array, arrayLength); //추출하게되면 추출된 원소는 배열의 맨 뒤로 가므로 다음 작업을 위해 초기화해주는 작업.
    
    /* min heap insert */
    start3 = clock();
    arrayLength++;
    min_heap_insert(array, 10000000, arrayLength); //임의의 값 10000입력.
    end3 = clock();
    sortingTime = (double)(end3- start3) / CLOCKS_PER_SEC;
    printf("%s\n", "minheap insert 정렬시간");
    printf("%f\n", sortingTime);
    /* min heap insert  END */
    
    /* min heap increase */
    start5 = clock();
    min_heap_decrease_key(array, 9, 100000001); //임의의 값 10000입력.
    end5 = clock();
    sortingTime = (double)(end5- start5) / CLOCKS_PER_SEC;
    printf("%s\n", "min heap increase key 정렬시간");
    printf("%f\n", sortingTime);
    /* min heap increase  END */
    
    
    /* min heap sort */
    
    start4 = clock();
    min_heapsort(array, arrayLength);
    end4 = clock();
    
    sortingTime = (double)(end4- start4) / CLOCKS_PER_SEC;
    printf("%s\n", "min heap sort 정렬시간");
    printf("%f\n", sortingTime);
    
    /* min heap sort END */
    
    
    
    printf("%s\n", "출력합니다.");
    //    printf("%f\n", sortingTime);
    for(int i=0; i < arrayLength ; i++) {
//        printf("%d\n", array[i]);
        fprintf(wf, "%d\n", array[i]);
    }
    
    
    
    return 0;
}

