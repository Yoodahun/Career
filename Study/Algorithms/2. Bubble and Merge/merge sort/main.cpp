//
//  main.cpp
//  algorithms_02_merge
//
//  Created by 유다훈 on 2017. 9. 18..
//  Copyright © 2017년 yoo_dahun. All rights reserved.
//

#include <iostream>
#include <time.h>


int main(int args, const char * argv[]) {
    // insert code here...
    
    // File Open
//    FILE *rf = fopen("test_10.txt", "r");
//    FILE *rf = fopen("test_100.txt", "r");
//    FILE *rf = fopen("test_1000.txt", "r");
    FILE *rf = fopen("test_10000.txt", "r");
    
//    FILE *wf = fopen("out_10.txt", "w");
//    FILE *wf = fopen("out_100.txt", "w");
//    FILE *wf = fopen("out_1000.txt", "w");
    FILE *wf = fopen("out_10000.txt", "w");
    if ( rf == NULL)
        printf("read file open error\n");
    if ( rf == NULL) printf("write file open error\n");
    
    // Read file and create array
//    int array[10];
//    int array[100];
//    int array[1000];
    int array[10000];
    
    int index = 0;
    
    while (!feof(rf)) {
        int value = 0;
        fscanf(rf, "%d", &value);
        array[index++] = value;
        printf("%d\n", value);
    }
    
    clock_t start, end;
    double sortingTime;
    
    /*
     *
     * your code
     *
     *
     */
    
    int arrayLength = (sizeof(array) / sizeof(array[0])); //배열의 길이 계산
     //정렬할때 이용할 임시배열.
//    int tempArray[10];
//    int tempArray[100];
//    int tempArray[1000];
    int tempArray[10000];
    
    
    /* Iterative Merge sort */
    
    //사용할 변수들 선언
    int right, rend;
    int i,j,m;
    
    start = clock();
    //반복하며 정렬 시작
    
    for (int k=1; k < arrayLength; k = k*2 ){
        
        for (int left = 0; left+k < arrayLength; left = left + (k*2)){
            right = left + k;
            rend = right + k;
            if (rend > arrayLength)
                rend = arrayLength;
            m = left;
            i = left;
            j = right;
            
            while (i < right && j < rend) {
                if (array[i] <= array[j]) {
                    tempArray[m] = array[i];
                    i++;
                } else {
                    tempArray[m] = array[j];
                    j++;
                }
                m++;
            }
            while (i < right) {
                tempArray[m] = array[i];
                i++;
                m++;
            }
            while (j < rend) {
                tempArray[m] = array[j];
                j++;
                m++;
            }
            for (m=left; m < rend; m++) {
                array[m] = tempArray[m];
            }
       
        }
    }
    
    end = clock();
    sortingTime = (double)(end - start) / CLOCKS_PER_SEC;

    
    
    /* Iterative Merge Sort END */
  
    /* File Output */
    printf("%s\n", "출력합니다.");
    printf("%f\n", sortingTime);
    for(int i=0; i < arrayLength ; i++) {
//        printf("%d\n", array[i]);
                fprintf(wf, "%d\n", array[i]);
    }
    
    
    // File Close
    fclose(rf);
    fclose(wf);
    return 0;
}
