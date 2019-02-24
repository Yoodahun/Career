//
//  main.cpp
//  Algorithm_09_Dijkstra
//
//  Created by 유다훈 on 2017. 11. 21..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>
#include <cmath>

struct edge {
int vertex1, vertex2;
int weight;
};
struct vertex {
    int distance;
    int parent;
};

/* Heap */
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

/* Heap */


void initialize_Single_source(int **matrix, int n, vertex *v) {
    
    
    for(int i=0; i<n; i++) {
        v[i].distance = 1000000;
        v[i].parent = NULL;
    }
    v[0].distance = 0;
    
}

void relax(vertex *vertex, edge * edge, int j) {
    int u = edge[j].vertex1;
    int v = edge[j].vertex2;
    if(vertex[v].distance > (vertex[u].distance + edge[j].weight) ) {
        vertex[v].distance = vertex[u].distance + edge[j].weight;
        vertex[v].parent = u;
    }
    printf("%d : %d and v.distance %d\n", u, v, vertex[v].distance );
    
}

void dijkstra(int **matrix, int n, vertex *v, edge *edge, int c){
    
    initialize_Single_source(matrix, n, v); //vertex의 초기화

    int a=0;//vertex number;
    int visitied[n]; //방문했는지 안했는지를 검사하기 위한 배열
    int minDistance[n]; //distance값들을 따로 저장하여 min값을 뽑아내기 위한 배열공간
    
    /*배열 초기화*/
    for(int i =0; i<n; i++) {
        visitied[i] = 0;
        minDistance[i] = v[i].distance; //초기값으로 각 vertex의 distance값을 다 넣어준다.
    }
    /*배열 초기화*/

    int count=0; //while문을 제어용.

    while(count < n) { //Vertex의 개수만큼 반복실행
        for(int i=0; i<n ;i++) {
            if(visitied[i] != 1) { //방문하지 않았을 때,
                minDistance[i] = v[i].distance; //해당 vertex의 distance를 저장한다.
            } else { //방문했다면
                minDistance[i] = 10000; //그냥 임의로 엄청 높은 값을 넣어준다. 최소값을 뽑는데 방해되지않도록.
            }
        }
        
        int u = heap_extract_min(minDistance, n); //최소값 추출

        for(int i=0; i<n; i++) {
            if( u == v[i].distance && visitied[i] != 1 ) { //최소값과 i번의 vertex의 distance값이 같으면서 방문을 하지 않았다면,
                a=i; //해당 vertex의 인덱스를 저장하고
                visitied[i]=1; //방문처리
            }
        }
        for(int i=0; i<c; i++) { //엣지의 개수만큼 반복
            if( a == edge[i].vertex1){ //해당 버텍스를 vertex1(출발지점)으로 가진 edge를 찾는다면
                relax(v, edge, i); //릴렉스 작업 시작
            }
        }

        count++; //while문 반복을 위한 카운터 증가.
    }
 
  
    for(int i=0; i<n ; i++) {
        printf("%d to %d : %d, %d\n", 0, i, v[i].distance, v[i].parent); //각 버텍스들의 키값과 부모 출력
        
    }
    
    
}

int main(int argc, const char * argv[]) {
    // insert code here...
    
    // File Open
    FILE *rf = fopen("DijkstraInput.txt", "r");
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
    
    
    for(int i=0; i<n; i++){
        for(int j=0; j<n ; j++) {
            if(adjmatrix[i][j] != 0) {
                count++;
            }
        }
    }
    struct edge edge[count];
    int index =0;
    
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
    
    
    struct vertex v[n];
    dijkstra(adjmatrix, n, v, edge, count);
    
    std::cout << "Hello, World!\n";
    return 0;
}
