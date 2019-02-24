#include <stdlib.h>
#include "ds.h"

void insertNode(Node **ListHead, int item){
	Node *newNode = (Node *)malloc(sizeof(Node));
	Node *prev;
	newNode->item = item;
	newNode->next = NULL;
	
	if(*ListHead == NULL){
		*ListHead = newNode;
	}
	else{
		prev = *ListHead;
		while(prev->next != NULL)
			prev = prev->next;
		
		prev->next = newNode;
	}
}

int getNodeItem(Node **ListHead, int index){
	Node *current = *ListHead;
	int i = 0;
	while(current != NULL && i < index){
		current = current->next;
		i++;
	}
	return current->item;
}
