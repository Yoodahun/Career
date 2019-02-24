//
//  main.cpp
//  APSP_and_Matrix_Multiplication
//
//  Created by 유다훈 on 2017. 11. 28..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>

int ** extend_shortest_paths(int ** l, int ** matrix, int n, int maxValue) {
   
    int ** newl; //새롭게 데이터 값을 저장할 2차원 배열
     newl = (int **)malloc(sizeof(int*) * n);
    for(int i=0; i<n; i++) {
            newl[i] = (int *)malloc(sizeof(int*) * n);
    }
    /* 2차원배열 동적배열 끝 */
    
    /* Extend Shortest Paths Algorithms */
    for(int i=0; i<n ; i++) {
        for(int j=0; j<n; j++) {
            newl[i][j] = maxValue;
            for(int k=0; k<n; k++) {
                if( newl[i][j] > l[i][k] + matrix[k][j]) {
                    newl[i][j] = l[i][k] + matrix[k][j];
                    
                }
            }
        }
    }
    /* Extend Shortest Paths Algorithms */
   
    //갱신 상태 출력
    for(int i=0; i<n ; i++) {
        for(int j=0; j<n; j++) {
            printf("%d ", newl[i][j]);
        }
        printf("\n");
    }
    
    return newl; //Return data value
}

void slow_all_pairs_shortest_paths(int **matrix, int n, int maxValue) {
    int ** l; //데이터 값을 저장할 배열 L
    
    l = (int **)malloc(sizeof(int*) * n);
    for (int i = 0; i < n; i++) {
        l[i] = (int *)malloc(sizeof(int*) * n);
     }
    /* 2차원 배열 동적배열 종료 */
    
    /* 데이터값 옮기기 */
    for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++) {
            l[i][j] = matrix[i][j];
        }
    }
    
    /* --- */
    
    for(int m = 1; m<n-1 ; m++) {
        printf("%d 번째 가중치 갱신\n", m);
       l = extend_shortest_paths(l, matrix, n, maxValue);
    }
}

void fast_all_pairs_shortest_paths(int **matrix, int n, int maxValue) {
    int m = 0;
    
    /* 데이터를 담을 2차원 배열의 동적할당 */
    int ** l;
    l = (int **)malloc(sizeof(int*) * n);
    for (int i = 0; i < n; i++) {
        l[i] = (int *)malloc(sizeof(int*) * n);
    }
    /* 데이터를 담을 2차원 배열의 동적할당 끝 */
    
    /* 데이터 옮겨담기 */
    for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++) {
            l[i][j] = matrix[i][j];
        }
    }
    /* 데이터 옮겨담기 끝*/
    
    int count=1;
    
    /* m*2 씩 반복횟수를 상승시키며 Extend Shortest Path 실행 */
    while( m < n-1) {
         printf("%d 번째 가중치 갱신\n", count++);
        l = extend_shortest_paths(l, l, n, maxValue);
        if(m == 0) {
            m = m+1;
        }
        m = 2*m;
    }
}

int main(int argc, const char * argv[]) {
    // insert code here...
    
    FILE *rf = fopen("graph_sample_directed.txt", "r");
    if ( rf == NULL)
        printf("read file open error");
    int i,n;
    int maxValue = 0x7fffff; //최대값
    
    // Struct Graph
    fscanf(rf, "%d", &n);
    int ** adjmatrix;
    adjmatrix = (int **)malloc(sizeof(int*) * n);
    for (i = 0; i < n; i++)
        adjmatrix[i] = (int *)malloc(sizeof(int*) * n);
    // Initialization
    for (i = 0; i < n; i++){
        for (int j = 0; j < n; j++)
            if( i == j) {
                adjmatrix[i][j]=0;
            } else {
                adjmatrix[i][j] = maxValue;
            }
        
    }
    while (!feof(rf)) {
        int x, y, v;
        fscanf(rf, "%d ", &x);
        fscanf(rf, "%d ", &y);
        fscanf(rf, "%d ", &v);
        adjmatrix[x][y] = v;
    }
    fclose(rf);
    
    
    /* 초기 데이터 값 출력 */
    printf("초기 데이터값\n");
    for (int i = 0; i < n; i++){
        for (int j = 0; j < n; j++){
            printf("%d ", adjmatrix[i][j]);
        }
        printf("\n");
    }
    printf("초기 데이터값 끝 -------- \n\n");
    /* 초기 데이터 값 출력 끝 */
    
    
    
    printf("Slow All Pairs Shortest Paths --------- \n");
    slow_all_pairs_shortest_paths(adjmatrix, n, maxValue);
    printf("\nFast all Pairs Shortest Paths --------- \n");
    fast_all_pairs_shortest_paths(adjmatrix, n, maxValue);
    std::cout << "Hello, World!\n";
    return 0;
}
