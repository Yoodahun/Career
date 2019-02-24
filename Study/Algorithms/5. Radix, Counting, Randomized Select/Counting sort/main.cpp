//
//  main.cpp
//  Algorithm_05_CountingSort
//
//  Created by 유다훈 on 2017. 10. 14..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>

void counting_sort(int array[], int arrayB[], int arrayC[], int max ) {
    for (int i =0; i < max; i++) {
        arrayC[i] =0;
    }
    for(int j =0; j  < 500; j++) {
        arrayC[ array[j] ] += 1;
        
    }
    for(int i= 1; i < max; i++) {
        arrayC[i] = arrayC[i] + arrayC[i-1];
    }
    for (int i = 499; i>=0; i--) {
        arrayB[ arrayC[ array[i] ] ] = array[i];
        arrayC[array[i]] -= 1;
    }
    
    
   
    
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
    int array_b[500]; //256 자리수 4
    
    int index = 0;
    int arrayLength = (sizeof(array) / sizeof(array[0])); //배열의 길이계산
    
    while (!feof(rf)) {
        int value = 0;
        fscanf(rf, "%d", &value);
        array[index++] = value;
        //        printf("%d\n", value);
    }
    int max = 0;
    
    for(int i = 0; i < arrayLength; i++) {
        if (array[i] > max) {
            max = array[i];
        }
    }
    int array_c[max];
    
    counting_sort(array, array_b, array_c, max);
    
    
    
    
    printf("%s\n", "출력합니다.");
    //    printf("%f\n", sortingTime);
    for(int i=1; i < arrayLength ; i++) {
        printf("%d\n", array_b[i]);
        fprintf(wf, "%d\n", array[i]);
    }
    
    
    return 0;
}
