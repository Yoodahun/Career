//
//  main.cpp
//  Algorithm_11_Matrix_Chain_Multiplication
//
//  Created by 유다훈 on 2017. 12. 5..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>

void print_optimal_parens(int ** s, int i, int j) { //행렬의 결합법칙을 출력하는 함수
    if( i == j) {
        printf("%d", i);
    } else {
        printf("(");
        print_optimal_parens(s, i, s[i][j]);
        print_optimal_parens(s, s[i][j]+1, j);
        printf(")");
    }
}

void matrix_chain_order(int *p, int n, int maxValue) {
    int ** m;
    int ** s;
    int length = n+1; //길이
    
    /*
     
     행렬의 가로세로를 정하는 수는 0~6까지 총 7개이다.
     행렬의 개수는 총 6개이다.
     인덱스를 1에서부터 시작하기 위해 길이를 7로 지정하고 시작한다.
     
     */

    /* 배열의 초기화 */
    
    m = (int **)malloc(sizeof(int*) * length);
    s= (int **)malloc(sizeof(int*) * length);
    for (int i = 1; i < length; i++) {
        m[i] = (int *)malloc(sizeof(int *) * length);
    }
    for (int i = 1; i < length; i++) {
        s[i] = (int *)malloc(sizeof(int *) * length);
    }
    for(int i=1; i<length; i++) {
        m[i][i] = 0;
    }
    
    /* 배열의 초기화 */
    
    /*Matrix Chain Order*/
    for(int l = 2 ; l <length; l++) { //대각선으로 이동하기 위한 index
        for (int i = 1 ; i< length-l+1; i++) { //대각선에 계산해야할 값들의 개수만큼 진행
            int j = i+l-1; //열 index지정. 대각선으로 진행해야하므로 결과적으로는 대각선 아래방향으로 내려간다.
            m[i][j] = maxValue;
            for(int k=i; k<= j-1; k++) { //어떠한 값 k에 대하여 계산
                 int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                if( m[i][j]>q) { //최소값을 계산하여 삽입.
                    m[i][j] = q;
                    s[i][j] = k;
                }
            }
        }
    }
    /*Matrix Chain Order*/
    
    
    for(int i=1; i<length; i++) {
        for(int j=1; j<length; j++) {
            printf("%d ", m[i][j]);
        }
        printf("\n");
    }
    printf("\n");
    
    for(int i=1; i<length; i++) {
        for(int j=1; j<length; j++) {
            printf("%d ", s[i][j]);
        }
        printf("\n");
    }
    printf("\n");
    
    print_optimal_parens(s, 1, 6);
    printf("\n");
    printf("cost : %d\n", m[1][n]);
}

int main(int argc, const char * argv[]) {
    // insert code here...
    int maxValue = 0x7fffff; //무한대값
    
    // File Open
    FILE *rf = fopen("sample_mat1.txt", "r");
    if ( rf == NULL)
        printf("read file open error");
    int i,n;
    
    // Struct Graph
    fscanf(rf, "%d\n", &n);
    int * matrix;
    matrix = (int *)malloc(sizeof(int) * n+2);
    
    // Initialization
    for (i = 0; i <= n; i++){
        fscanf(rf, "%d", &matrix[i]); //첫번째 수열
    }
    fclose(rf);
    
    for (i = 0; i <= n; i++){
        printf("%d ", matrix[i]);
    }
    printf("\n\n");
    
    matrix_chain_order(matrix, n, maxValue);
    
    std::cout << "Hello, World!\n";
    return 0;
}
