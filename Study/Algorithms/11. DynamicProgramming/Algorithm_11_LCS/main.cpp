//
//  main.cpp
//  Algorithm_11_LCS
//
//  Created by 유다훈 on 2017. 12. 5..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>


void print_LCS(int ** b, char * firstSequence, int firstM, int secondN) { //부분문자열의 순서를 출력하는 함수
    if ( firstM == 0 || secondN == 0) {
        return;
    }
    if(b[firstM][secondN] == 1) {
        print_LCS(b, firstSequence, firstM-1, secondN-1);
        printf("%c", firstSequence[firstM-1]);
    } else if (b[firstM][secondN] == 0) {
        print_LCS(b, firstSequence, firstM-1, secondN);
    } else {
        print_LCS(b, firstSequence, firstM, secondN-1);
    }
}

void LCS_Length(char * firstSequence, char * secondSequence, int firstM, int secondN, int maxValue) {
    
    int ** b;
    int ** c;
    
    b = (int **)malloc(sizeof(int*) * firstM); //첫번째 문자열
    c = (int **)malloc(sizeof(int*) * firstM); //두번째 문자열
    for (int i = 0; i < firstM; i++) {
        b[i] = (int *)malloc(sizeof(int *) * secondN);
        c[i] = (int *)malloc(sizeof(int *) * secondN);
    }
    
    for(int i=0; i<firstM; i++) {
        for(int j=0; j<secondN; j++) {
            b[i][j] =maxValue; //값을 제대로 검사하기 위해서 모든 값들을 최대값으로 채워넣고 시작.
        }
    }
    /* 배열의 초기화 */
    for(int i = 0; i<firstM; i++) {
        c[i][0] =0;
    }
    for (int j=0; j<secondN; j++) {
        c[0][j] = 0;
    }
    /* 배열의 초기화 */
    
    /* Longest Common Subsequence */
    for(int i = 1; i<firstM; i++) {
        for (int j = 1; j<secondN ; j++ ) {
            if(firstSequence[i-1] == secondSequence[j-1]) { //첫번째 문자열을 기준으로 두번쨰 문자열을 비교해나가기 시작
                c[i][j] = c[i-1][j-1] + 1; // 만일 같은 값이라면 대각선 이전 위치의 값에 +1을 더함
                b[i][j] = 1; // 순서를 저장할 배열 b에 1 저장
            } else if (c[i-1][j] >= c[i][j-1]) { //그렇지 않다면, 이전행의 값이 이전열의 값보다 크다면
                c[i][j] = c[i-1][j]; //이전 행의 값을 옮겨 저장
                b[i][j] = 0; //순서를 저장할 배열 b에 0 저장
            } else {
                c[i][j]= c[i][j-1]; //그렇지 않다면, 이전열의 값 저장
                b[i][j] = -1; //순서를 저장할 배열 b에 -1저장
            }
        }
    }
    /* Longest Common Subsequence */
    
    for(int i=0; i<firstM; i++) {
        for(int j=0; j<secondN; j++) {
            printf("%d ", c[i][j]);
        }
        printf("\n");
    }
    printf("\n");
    
    for(int i=0; i<firstM; i++) {
        for(int j=0; j<secondN; j++) {
            printf("%d ", b[i][j]);
        }
        printf("\n");
    }
    printf("\n");
    
    
    print_LCS(b, firstSequence, firstM-1, secondN-1);
    
}

int main(int argc, const char * argv[]) {
    // insert code here...
    int maxValue = 0x7fffff; //무한대값
    
    // File Open
    FILE *rf = fopen("sample_lcs1.txt", "r");
    if ( rf == NULL)
        printf("read file open error");
    int i,n,m;
    
    // Struct Graph
    fscanf(rf, "%d\n", &n);
    char * firstSequence;
    firstSequence = (char *)malloc(sizeof(char) * n);
    
    // Initialization
    for (i = 0; i < n; i++){
        fscanf(rf, "%c", &firstSequence[i]); //첫번째 수열
    }
   
    fscanf(rf, "%d\n", &m);
   
    char * secondSequence = (char *)malloc(sizeof(char) * m);
    for (i = 0; i < m; i++){
        fscanf(rf, "%c", &secondSequence[i]); //두번째 수열
    }
    for (i = 0; i < n; i++){
        printf("%c", firstSequence[i]);
    }
    printf("\n");
    for (i = 0; i < m; i++){
        printf("%c", secondSequence[i]);
    }
    printf("\n");
    
    fclose(rf);
    
    LCS_Length(firstSequence, secondSequence, n+1, m+1, maxValue);
    std::cout << "\nHello, World!\n";
    return 0;
}
