//
//  main.cpp
//  Algorithm_06_BST
//
//  Created by 유다훈 on 2017. 10. 19..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>

/* 배열 정렬을 위한 퀵소트*/
void swap(int array[], int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

int partition(int array[], int pivot, int right) {
    int x = array[right];
    int i = pivot-1;
    
    for (int j = pivot; j <= right-1 ; j++) {
        if (array[j] < x ) {
            i++;
            swap(array, i, j);
        }
    }
    swap(array, i+1, right);
    return i+1;
}

void quicksort(int array[], int pivot, int right){
    if (pivot < right) {
        int q = partition(array, pivot, right);
        quicksort(array, pivot, q-1);
        quicksort(array, q+1, right);
        
    }
}
/* 배열 정렬을 위한 퀵소트*/



struct node { //노드
    int value; //4byte
    struct node *parent;
    struct node *left; //4byte
    struct node *right; //4byte
};

void inorder_tree_walk(node* head) { //inorder traverse
    if (head != NULL) {
        inorder_tree_walk(head->left);
        printf("%d\n", head->value);
        inorder_tree_walk(head->right);
    }
}

node* tree_search(node* head, int searchValue) { //tree search
    if (  searchValue == head->value) {
        return head;
    }
    if (searchValue <head->value ) {
        return tree_search(head->left, searchValue);
    } else {
        return tree_search(head->right, searchValue);
    }
}

void tree_insert(node* head, int z){ //tree insert
    
    struct node *insertNode = (struct node *) malloc(sizeof(struct node));
    insertNode->value = z;
    insertNode->parent = NULL;
    insertNode->left = NULL;
    insertNode->right = NULL;

    if (head->value < z) {
        if(head->right != NULL) {
            tree_insert(head->right, z);
        } else {
            insertNode->parent = head;
            head->right = insertNode;
        }
    } else if (head->value > z) {
        if (head->left != NULL) {
            tree_insert(head->left, z);
        } else {
            insertNode->parent = head;
            head->left = insertNode;
        }
    }
}

node* tree_minimum(node *x) { //minimum of tree
    while(x->left != NULL) {
        x = x->left;
        
    }
    return x;
}
node*  tree_maximum(node *x) { //maximum of tree
    while(x->right != NULL) {
        x = x->right;
    }
    return x;
}

void transplant(node *head, node *deleteNode, node *deleteNodeChildren) { //트리의 노드 바꿔치는 작업
    if (deleteNode->parent == NULL) {
        head = deleteNodeChildren;
    } else if ( deleteNode == deleteNode->parent->left) {
        deleteNode->parent->left = deleteNodeChildren;
    } else {
        deleteNode->parent->right = deleteNodeChildren;
    }
    if (deleteNodeChildren != NULL) {
        deleteNodeChildren->parent = deleteNode->parent;
    }
//    free(deleteNode);
}
node* tree_successor (node* x) { //직후 노드 찾기
    if (x->right != NULL) {
        return tree_minimum(x->right);
    }
    node *y = x->parent;
    while (y != NULL && x == y->right) {
        x = y ;
        y = y->parent;
    }
    return y;
}


void tree_delete(node *head, int deleteValue) { //트리에서 노드 삭제작업
    
    node* delNode = tree_search(head, deleteValue); //삭제할 값을 가진 노드 찾기
    
    if (delNode->left == NULL) { //오른쪽노드만 가진 노드
        transplant(head, delNode, delNode->right);
        
    } else if (delNode->right == NULL) { //왼쪽 노드를 가진 노드
        transplant(head, delNode, delNode->left);
    } else { //둘다 가진 노드 삭제?
        node* y = tree_minimum(delNode->right);
        if (y->parent != delNode) {
            transplant(head, y, y->right);
            y->right = delNode->right;
            y->right->parent = y;
        }
        transplant(head, delNode, y);
        y->left = delNode->left;
        y->left->parent = y;
    }
    delete delNode;
}

void recursively_tree_insert (int array[], int start ,int arrayLength, node *head) {
    
    if (start < arrayLength) {
        int last = arrayLength;
        
        int mid = (last+start) /2 ;
         tree_insert(head, array[mid]);
        
        recursively_tree_insert(array, start, mid-1, head);
        recursively_tree_insert(array, mid+1, arrayLength, head);
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
    
    
    int array[1000];
    int index = 0;
    node *head = (node *) malloc(sizeof(node));
    
    while (!feof(rf)) {
        int value = 0;
        fscanf(rf, "%d", &value);
//        tree_insert(head, value); //주어진 배열을 이용하여 바로 트리를 만드는 작업
        array[index++] = value; //주어진 배열을 이용하여 트리를 만들기 전에 정렬을 하기 위한 배열초기화
//        printf("%d\n", value);
    }
     int arrayLength = (sizeof(array) / sizeof(array[0])); //배열의 길이계산
    
    /* 정렬된 배열을 만들기 위한 퀵소팅 작업*/
    quicksort(array, 0, 999);
    
    /*재귀적으로 반복해가며 중간값부터 트리 삽입*/
    recursively_tree_insert(array, 0, arrayLength-1, head);
    

    tree_delete(head, 26 ); //트리에서 원소삭제문. 삭제하고싶은 값을 넣어 해당 값이 들어있는 노드를 삭제한다.
    inorder_tree_walk(head); //자료구조가 제대로 생성되었는지, 삭제하고 싶은 값은 잘 삭제되는지 확인하기 위한 inorder Traverse
//
//    printf("%d\n", tree_minimum(head)->value);
    for(int i=1; i < arrayLength ; i++) {
        //        printf("%d\n", array_b[i]);
//        fprintf(wf, "%d\n", array[i]);
    }
    
    
    return 0;
}
