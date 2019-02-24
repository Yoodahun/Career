//
//  main.cpp
//  Algorithm_09_BellmanFord
//
//  Created by 유다훈 on 2017. 11. 20..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>
struct edge { //Edge의 정보를 담을 구조체
    int vertex1, vertex2; //엣지간의 버텍스
    int weight; //가중치
};
struct vertex {
    int distance; //버텍스가 같은 키값
    int parent; //버텍스의 이전 버텍스
};

void initialize_Single_source(int **matrix, int n, vertex *v) { //버텍스를 초기화시켜주는 작업

    
    for(int i=0; i<n; i++) {
        v[i].distance = 1000000;
        v[i].parent = NULL;
    }
    v[0].distance = 0;
    
}

void relax(vertex *vertex, edge * edge, int j) { //가중치를 최적화시키는 릴렉스 작업
    int u = edge[j].vertex1;
    int v = edge[j].vertex2;
    if(vertex[v].distance > (vertex[u].distance + edge[j].weight) ) {
        vertex[v].distance = vertex[u].distance + edge[j].weight;
        vertex[v].parent = u;
    }
    printf("%d : %d and v.distance %d\n", u, v, vertex[v].distance );
    
}

void bellman_ford(int **matrix, vertex *vertex, edge *edge, int n , int count) {
    initialize_Single_source(matrix, n, vertex); //버텍스 초기화
    
    printf("결과 출력\n");
    printf("u to v : distance\n");
    
    for(int i =0 ; i<n ; i++) { //이중 for문을 이용한 릴렉스 작업
        for(int j=0; j<count ; j++) {
            relax(vertex, edge, j);
        }
    }
    
    for(int i=0; i<count; i++) { //다른 경로가 있는지 재확인하는 방법
        int u = edge[i].vertex1;
        int v = edge[i].vertex2;
        if(vertex[v].distance > (vertex[u].distance + edge[i].weight) ) {
            printf("음수 가중치가 있습니다");
            break;
        }
    }
   
    for(int i=0; i<n ; i++) {

        printf("%d to %d : %d, %d\n", 0, i, vertex[i].distance, vertex[i].parent); //각 버텍스들의 키값과 부모 출력

    }
    
}

int main(int argc, const char * argv[]) {
    // insert code here...
    
    // File Open
    FILE *rf = fopen("BellmanInput.txt", "r");
    if ( rf == NULL)
        printf("read file open error");
    int i,n;
    
    // Struct Graph
    fscanf(rf, "%d", &n);
    int ** adjmatrix;
    adjmatrix = (int **)malloc(sizeof(int*) * n);
    for (i = 0; i < n; i++)
        adjmatrix[i] = (int *)malloc(sizeof(int*) * n);
    // Initialization
    for (i = 0; i < n; i++){
        for (int j = 0; j < n; j++)
            adjmatrix[i][j] = 0;
    }
    while (!feof(rf)) {
        int x, y, v;
        fscanf(rf, "%d ", &x);
        fscanf(rf, "%d ", &y);
        fscanf(rf, "%d ", &v);
        adjmatrix[x][y] = v;
    }
    fclose(rf);
    int count=0;

    /* 엣지의 개수를 세는 작업*/
    for(int i=0; i<n; i++){
        for(int j=0; j<n ; j++) {
            if(adjmatrix[i][j] != 0) {
                count++;
            }
        }
    }
    struct edge edge[count]; //엣지개수만큼의 정보를 담을 수 있는 구조체배열 선언
    int index =0;
    
    /* 엣지의 개수 입력 */
    for(int i=0; i<n; i++){
        for(int j=0; j<n ; j++) {
            if(adjmatrix[i][j] != 0) {
                edge[index].vertex1 = i;
                edge[index].vertex2 = j;
                edge[index].weight = adjmatrix[i][j];
                index++;
            }
        }
    }
    
  
    struct vertex v[n]; //버텍스의 키값과 부모값을 저장하는 버텍스 배열 선언

    bellman_ford(adjmatrix, v, edge, n, count);
    
    std::cout << "Hello, World!\n";
    return 0;
}
