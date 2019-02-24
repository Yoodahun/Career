//
//  main.cpp
//  Algorithm_10_Floyd_Warshall
//
//  Created by 유다훈 on 2017. 11. 24..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>


void floyd_Warshall (int **matrix, int n, int maxValue) {
    int ** d= matrix; //매트릭스값 복사
    int parent[n][n]; // 노드의 부모노드를 저장할 2차원 배열
    
    /* 부모노드의 초기화 */
    for(int i=0; i<n; i++) {
        for(int j=0; j<n; j++) {
            if(i==j or matrix[i][j] == maxValue){
                parent[i][j] = NULL;
            } else if(i != j and matrix[i][j] < maxValue) {
                parent[i][j] = i;
            }
        }
    }
    /* 부모노드의 초기화 끝 */
    //초기값 출력
    printf("초기 데이터값\n");
    for(int i=0; i<n ; i++) {
        for(int j=0; j<n; j++) {
            printf("%d ", d[i][j]);
           
        }
        printf("\n");
    }
    printf("부모값\n");
    for(int i=0; i<n ; i++) {
        for(int j=0; j<n; j++) {
            printf("%d ", parent[i][j]);
        }
        printf("\n");
    }
    printf("--------------\n\n");
    /* 초기값 출력 끝*/
    
    /* Floyd and Warshall Algorithms */
    for(int k=0; k<n; k++) { //모든 노드에 대해서 모든 노드로 향하는 최단거리 검색
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(d[i][k] + d[k][j] < d[i][j]  ) { //현재 가중치가 현재 노드로 들어오는 부모노드와 나가는 노드의 합보다 높다면
                    d[i][j] = d[i][k] + d[k][j]; //해당 합값으로 가중치를 바꿈
                    parent[i][j] = parent[k][j]; //부모노드의 갱신
                }
            }
        }
        /* 가중치와 부모노드가 갱신되는 과정을 알고리즘에 포함시켜서 출력 */
        printf("%d 번째 가중치 갱신\n", k+1);
        for(int i=0; i<n ; i++) {
            for(int j=0; j<n; j++) {
                printf("%d ", d[i][j]);
            }
            printf("\n");
        }
        printf("%d 번째 노드의 부모 갱신\n", k+1);
        for(int i=0; i<n ; i++) {
            for(int j=0; j<n; j++) {
                printf("%d ", parent[i][j]);
            }
            printf("\n");
        }
        printf("\n");
        /* 가중치와 부모노드가 갱신되는 과정을 알고리즘에 포함시켜서 출력 */
    }
    /* Floyd and Warshall Algorithms */
}


int main(int argc, const char * argv[]) {
    // insert code here...
    // File Open
    FILE *rf = fopen("graph_sample_directed.txt", "r");
    if ( rf == NULL)
        printf("read file open error");
    int i,n;
    int maxValue = 0x7fffff; //무한대값
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
  
    
    
    
    floyd_Warshall(adjmatrix, n,  maxValue);
    
    
    std::cout << "Hello, World!\n";
    return 0;
}
