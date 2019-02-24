#include <stdio.h>
#include <cstdlib>
#include <iostream>

struct node {
	int value;
 
	struct node *next;
};


struct node * queue;
struct node * stack;
void enQueue(int value);
int deQueue();
void PushStack(int value);
int PopStack();

void DFS_Visit(int **matrix, int *visited, int i, int n) {
    
    visited[i] = 1;
    printf("%d\n", i);
    
   
        for(int j=0; j<n; j++) {
            if (visited[j] == 0 && matrix[i][j] == 1) {
                DFS_Visit(matrix, visited, j, n);
            }
    }
    
}
void Depth_First_Search(int **matrix, int n) {
    
    int visited[n]; //방문한 vertex를 재방문하지 않기 위한 확인 방법. 배열선언.
    
    for (int i =0; i < 8; i++) {
        visited[i] = 0; //배열의 초기화. 0은 방문하지 않은 것, 1은 이미 방문 한 것.
    }
    
        DFS_Visit(matrix, visited, 0, n);
    
}


void Breadth_First_Search(int **matrix, int n ) {
    
    int visited[n]; //방문한 vertex를 재방문하지 않기 위한 확인 방법. 배열선언.
    
    for (int i =0; i < 8; i++) {
        visited[i] = 0; //배열의 초기화. 0은 방문하지 않은 것, 1은 이미 방문 한 것.
                        //처음에는 모두 방문하지 않았음.
    }
    
    enQueue(0);
    visited[0] = 1; //첫 방문은 임의로 시작하나 이번 과제에서는 0번 노드부터 시작.

    while(queue->value != -1 || queue->next != NULL) {
                                            //큐가 비어있지 않을 동안 혹은 큐의 다음값이 널이 아닌 동안
        int i = deQueue();                  //큐에서 값 하나를 추출
        printf("%d\n", i);                  //출력
        for (int j = 0; j < n; j++){        //데이터의 갯수만큼 진행
            if (visited[j] == 0 && matrix[i][j] == 1) { //방문을 안했으면서 연결된 버텍스라면
                enQueue(j); //큐에 집어넣고
                visited[j] = 1; //큐에 집어넣은 것은 방문한 것
            }
    }

}
}

int main() {
	// File Open
	FILE *rf = fopen("graph.txt", "r");
    if ( rf == NULL)
        printf("read file open error\n");

	int i, j, n;

	// Struct Graph
	fscanf(rf, "%d", &n);
	int ** adjmatrix;
	adjmatrix = (int **)malloc(sizeof(int*) * n);
	for (i = 0; i < n; i++)
		adjmatrix[i] = (int *)malloc(sizeof(int*) * n);
	for (i = 0; i < n; i++)
		for (j = 0; j < n; j++)
		fscanf(rf, "%d", &adjmatrix[i][j]);
	fclose(rf);	

	queue = (struct node *) malloc(sizeof(struct node));
	stack = (struct node *) malloc(sizeof(struct node));
	queue -> value = -1;
	queue->next = NULL;
	stack -> value = -1;
	stack->next = NULL;
	// using of 2d matrix   ex) adjmatrix[4][5];
	

	/*
	*
	* Your code
	*
	*/
    
    printf("%s\n", "BFS");
    Breadth_First_Search(adjmatrix, n);
    printf("%s\n", "DFS");
    Depth_First_Search(adjmatrix, n);

    
	
	return 0;
}

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
void PushStack(int value) {
	struct node * temp = stack->next;
	stack -> next = (struct node *) malloc(sizeof(struct node));
	stack->next->value = value;
	stack->next->next = temp;
}
int PopStack() {
	if (stack->next == NULL)
		return stack->value;
	struct node * temp = stack->next;
	int return_value = temp->value;
	stack->next = temp->next;
	return return_value;
}


/*
node array[8];
int white = 0;
int gray = 1;
int black = 2;

struct node* insertNode = NULL;

for (int i = 0; i < n; i++){
    for (int j = 0; j < n; j++) {
        if(matrix[i][j] == 1){
            
            struct node *newNode = (struct node *) malloc(sizeof(struct node));
            newNode->value = j;
            newNode->next = NULL;
            newNode->color = white;
            newNode->parent = NULL;
            newNode->distance = -1000;
            
            if (insertNode == NULL)
                insertNode = newNode;
                else if (insertNode != NULL) {
                    while(insertNode != NULL) {
                        insertNode = insertNode->next;
                    }
                    insertNode->next = newNode;
                }
            
        } else {
            continue;
        }
        array[i] = *insertNode;
    }
    
}

array[0].color = gray;
array[0].distance = 0;
enQueue(array[0].value);

while (queue->value != -1) {
    struct node * u =  (struct node *) malloc(sizeof(struct node));
    u->value = deQueue();
    
}
*/
