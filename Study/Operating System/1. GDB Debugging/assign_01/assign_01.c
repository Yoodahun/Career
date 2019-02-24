#include <stdio.h>
#include <stdlib.h>
#include "ds.h"

int main(void){
	Node *NodeList1 = (Node*)malloc(sizeof(Node));
	Node *NodeList2 = (Node*)malloc(sizeof(Node));
	NodeList1 = NULL;	
	NodeList2 = NULL;
	
	//MakeNodeList1
	insertNode(&NodeList1, 36);
	insertNode(&NodeList1, 38);
	insertNode(&NodeList1, 41);
	insertNode(&NodeList1, 42);
	insertNode(&NodeList1, 35);
	//Make NodeList2
	insertNode(&NodeList2, 32);
	insertNode(&NodeList2, 42);
	insertNode(&NodeList2, 37);
	insertNode(&NodeList2, 47);
	insertNode(&NodeList2, 39);

	//You may correct getNodeItem Function?	
	if(getNodeItem(&NodeList1, 0) * getNodeItem(&NodeList2, 5) == 1599)
		printf("This is Assign 1\n");
	
	while((getNodeItem(&NodeList2, 0) != 39) || 
	      (getNodeItem(&NodeList1, 0) > 36)){
		//You Can't Escape
	}
	printf("You've Passed Assign 1\n");
}
