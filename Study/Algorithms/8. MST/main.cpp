//
//  main.cpp
//  Algorithm_08_MST
//
//  Created by 유다훈 on 2017. 11. 9..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>
#include <cmath>
#include <string.h>

struct subset
{
    int parent;
    int value;
    
};

struct edge {
    int vertex1, vertex2;
    int weight;
};
struct node {
    int value;
    struct node *next;
};

struct node * queue;

/* Queue */
void enQueue(int value);
int deQueue();

void Make_set(int x,subset *subset);
int Find_set(int x, subset *parent);
void Union(int x,int y, subset *parent);

void enQueue(int value) {
    struct node * temp = queue;
    while (temp -> next != NULL)
        temp = temp->next;
    temp->next = (struct node *) malloc(sizeof(struct node));
    temp->next->value = value;
    temp->next->next = NULL;
}
int deQueue() {
    if (queue->next == NULL)
        return queue->value;
    struct node * temp = queue->next;
    int return_value = temp->value;
    queue->next = temp->next;
    return return_value;
}

/* Queue */

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


// 서로소인 Subset을 하나의 Subset으로 만드는 함수이다.
// x와 y가 속한 Subset의 대표값 (root값) 이 같은지로 서로소인지 판별 할 수 있다.
void Union(int x, int y, subset *parent)
{
    int x_root = Find_set(x,parent) ;
    int y_root = Find_set(y, parent);
    int x_root_rank = parent[x_root].value;
    int y_root_rank = parent[y_root].value;
    
    if (x_root_rank > y_root_rank)
    {
        parent[y_root].parent = x_root;
    }
    else if (x_root_rank < y_root_rank)
    {
        parent[x_root].parent = y_root;
    }
    else // rank가 서로 같다면 x와 y 둘중에 아무거나 하나를 선택하여 parent로 삼고 해당 루트의 rank를 증가시킨다.
    {
        parent[x_root].parent = y_root;
        parent[y_root].value++;
    }
}
//int x가 속한 set의 root값을 출력하는 함수
//Vertex의 Parent 정보가 기록된 Subset parent Array를 받아
//parent를 거슬러 올라가 x와 일치하는 root를 반환
int Find_set(int x, subset *parent)
{
    if (parent[x].parent != x)
    {
        parent[x].parent= Find_set(parent[x].parent, parent);
    }
    return parent[x].parent;
}
//최초에 원소한의 Subset으로 초기화하기 위한 함수이다.
void Make_set(int x, subset * subset)
{
    subset[x].parent = x;
    subset[x].value = 0;
}


//Prim's Algorithms.
void prim(int **matrix, int n) {
    int visited[n];
    int heapArray[n];
    int a=0;
    
    
    for (int i =0; i < n; i++) {
        visited[i] = 0; //배열의 초기화. 0은 방문하지 않은 것, 0이외의 값은 가중치
        //처음에는 모두 방문하지 않았음.
    }
    for (int i = 0; i < n; i++) {
        
        enQueue(i); //모든 노드값을 큐에 저장
    }
    
    
    int i = deQueue(); //초기 시작값
    int minSum=0;
    printf("Prim's Algorithm\n");
    while(queue->value != -1 || queue->next != NULL) {
        //큐가 비어있지 않을 동안 혹은 큐의 다음값이 널이 아닌 동안
        for(int z = 0; z<n ; z++) {
            heapArray[z] = matrix[i][z]; //추출한 노드값의 가중치를 임시로 힙에 넘겨줄 배열에 저장
        }
        
        int min = heap_extract_min(heapArray, n); //가중치 중 제일 작은 녀석을 추출
        
        if (visited[i] == 0 ) { //방문을 안한 녀석이라면
            visited[i] = min; //해당 노드값의 최소 가중치값을 해당 노드값에 저장
        }
        for (int z =0; z<n; z++) {
            if(matrix[i][z] == min){ //만약 매트릭스의 z인덱스 값의 위치의 가중치와 최소값이 같다면
                a = z; //해당 값으로 이동해야하므로 z값을 따로 저장
                minSum += min;
                break; //반복문 탈출
            }
        }
        printf("%d : %d => %d\n", i, a, min);
        printf("최소가중치 합: %d\n", minSum);//현재 노드값에서 최소값으로 진행할 다음 노드를 출력
        i = deQueue(); //다음 노드값 저장 후 while문 반복실행
    }
    
    
}


void kurusukal(int **matrix, int n){
     struct subset *subsets = (struct subset*) malloc( n * sizeof(struct subset) );
    int count=0;
    
    /* Kuruskal 알고리즘을 구현하기 전 준비작업  */
    
    //Edge의 개수세기
    for(int i=0; i<n; i++) {
        for (int j=0; j<n ; j++) {
            if (matrix[i][j] != 0) {
                count++;
            }
        }
    }
    
    edge edge[count]; //Edge의 정보를 저장할 구조체 instance 선언
    
    int index=0;
    
    //Edge의 정보저장
    for(int i=0; i<n; i++) {
        for (int j=0; j<n ; j++) {
            if (matrix[i][j] != 0) {
                edge[index].weight = matrix[i][j];
                edge[index].vertex1 = i;
                edge[index].vertex2 = j;
                index++;
            }
        }
       
    }
    
    /* Kuruskal 알고리즘을 구현하기 전 준비작업 끝 */
    
    //각각의 Vertex를 부분집합 만들어주기.
    for (int i=0; i < n; i++) {
        Make_set(i, subsets);
    }
    
    //가중치의 오름차순 정렬을 위한 삽입정렬
    for(int i=1; i<count; i++) {
        struct edge key = edge[i];
        int j = i-1;
        while(j>=0 && edge[j].weight > key.weight ) {
            edge[j+1] = edge[j];
            j--;
        }
        edge[j+1] = key;
    }

    struct edge result[n];
    int z=0;
    
    //Union해가면서 트리만들기
    for(int i=0; i<count ; i++) {
        int q = Find_set(edge[i].vertex1, subsets);
        int w = Find_set(edge[i].vertex2, subsets);
        if(q!= w){
            result[z++] = edge[i];
            Union(q, w, subsets);
           
        }
    }
    int minSum=0;
    //결과출력
    printf("\n\nKuruskal's Algorithm \n");
    for(int i=0; i<z; i++) {
        printf("%d : %d => %d\n", result[i].vertex1, result[i].vertex2, result[i].weight);
        minSum += result[i].weight;
        printf("최소가중치 합: %d\n", minSum);
    }
  

}


int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    
    FILE *rf = fopen("graph.txt", "r");
    if ( rf == NULL)
        printf("read file open error\n");
    
    int i, j, n;
    
    fscanf(rf, "%d", &n);
    
    int ** adjmatrix;
    adjmatrix = (int **)malloc(sizeof(int*) * n);
    for (i = 0; i < n; i++)
        adjmatrix[i] = (int *)malloc(sizeof(int*) * n);
    for (i = 0; i < n; i++)
        for (j = 0; j < n; j++)
            fscanf(rf, "%d ", &adjmatrix[i][j]);
    fclose(rf);
    
    queue = (struct node *) malloc(sizeof(struct node));
    queue -> value = -1;
    queue->next = NULL;
    
    prim(adjmatrix, n);
    kurusukal(adjmatrix, n);
//
//    for (i = 0; i < n; i++) {
//        for (j = 0; j < n; j++) {
//            printf("%d ", adjmatrix[i][j]);
//        }
//        printf("\n");
//    }
    
    return 0;
}
