#ifndef __DS_H__
#define __DS_H__

typedef struct _Node{
	int item;
	struct _Node* next;
}Node;

void insertNode(Node** ,int );
int getNodeItem(Node**, int );

#endif
