//
//  main.cpp
//  Algorithm_05_RadixSort
//
//  Created by 유다훈 on 2017. 10. 14..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>

void counting_sort(int array[], int arrayLength, int n ) {
    int arrayB[500];
    int max = 0;
    int arrayDigit[500];
 
    
    for (int i=0; i <arrayLength; i++) {
        int a = array[i] >> n & 0xff;
            arrayDigit[i] = a;
    }
    for(int i = 0; i < arrayLength; i++) {
        if (arrayDigit[i] > max) {
            max = arrayDigit[i];
        }
    }
    int arrayC[max+2];
    
    for (int i =0; i < max+2; i++) {
        arrayC[i] =0;
        arrayB[i] =0;
    }
    for(int j =0; j  < 500; j++) {
        arrayC[ arrayDigit[j] ] += 1;
        
    }
    for(int i= 1; i < max+2; i++) {
        arrayC[i] = arrayC[i] + arrayC[i-1];
    }
    for (int i = 499; i>=0; i--) {
        arrayB[ arrayC[ arrayDigit[i] ] ] = array[i];
        arrayC[arrayDigit[i]] -= 1;
    }
    for (int i =0; i<500; i++) {
        array[i] = arrayB[i];
    }
}

void radixsort(int array[],  int arrayLength) {
    
 
    for(int n=0; n<=1 ; n++) {
        int z = 0;
        if (n != 0) {
            z= n*8;
        }
        counting_sort(array, arrayLength, z );
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
    int index = 0;
    int arrayLength = (sizeof(array) / sizeof(array[0])); //배열의 길이계산
    
    
    int array_b[500]; //256 자리수 4
    
    while (!feof(rf)) {
        int value = 0;
        fscanf(rf, "%d", &value);
        array[index++] = value;
//        printf("%d\n", value);
    }
    
    
    radixsort(array, arrayLength);
//
//    for(int i = 1; i <=2 ; i++) {
//        if (i == 1) {
//
//        }
//    }
//
    
//    int a = 2147;
//
//    printf("%x\n", a);
//    printf("%x\n", a>>0  & 0xff );
//    int b = (a >> 8);
    
//    printf("%x\n", (b << 7));
//    printf("%x\n", (a >> 16) & 0xff);
//    printf("%x\n", (a >> 24) & 0xff);
    //a값에서 8비트 옆으로 이동. 절삭. 마스크를 씌우는 것.
    /*
     
     a = 1239e인데, 8비트 옆으로 옮기면 23만 출력. 9는 절삭.
     0 8 16 24
     3자리 수라면 첫번째 자리수는 그냥 마스킹하기
     2번째 자리수라면 >> 8
     3번째 자리수라면 >> 16
     4번째 자리수라면 >> 24
     
     첫번째 자리수들만 뽑아서 정렬데이터 배열에 넣어주고 정렬을 하고,
     그 정렬된 값들의 앞자리 또한 정렬을 해주어야함.
     
     자리수 정렬 대상 배열의 인덱스를 원래 데이터 배열의 인덱스로도 한 번에 같이 정렬을 진행함.
     
     
     */
    
    //0x0123e9
    //0x0000ff
    //0x0000e9
    
    //0x000123
    //0x0000ff
    //0x000023
    
//    int b = 1;
//    int temp = ((e[b] >> 8) & 0xff);// a[b] >> 8
    
    printf("%s\n", "출력합니다.");
    printf("%s\n", "출력합니다.");
    printf("%s\n", "출력합니다.");
    printf("%s\n", "출력합니다.");
    //    printf("%f\n", sortingTime);
    for(int i=2; i < arrayLength ; i++) {
        printf("%d\n", array[i]);
        fprintf(wf, "%d\n", array[i]);
    }
    
    
    return 0;
}
