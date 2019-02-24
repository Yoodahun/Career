//
//  main.cpp
//  Algorithm_12_Knapsack_float
//
//  Created by 유다훈 on 2017. 12. 13..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>
struct Item {
    float weight;
    int b;
    float valuePerWeight;
};


void print_knapsack(int ** B, int n, float a, Item floatItem[]) {
    
    
    int w = a*10-1;
    if(n == 0) {
        return;
    }
    if(B[n][w] > B[n-1][w] ){
        printf("%d, %f, %f\n",n,floatItem[n].weight, a-floatItem[n].weight);
        return print_knapsack(B, n-1, a-floatItem[n].weight,floatItem);
    } else {
        return print_knapsack(B, n-1, a,floatItem);
    }
    
    
}
void insertionSort( Item intItem[], int itemLength ) {
    
    for( int i = 2; i < itemLength ; i++) {
        Item key = intItem[i];
        int k = i;
        
        for (int j= i-1; j>=1; j--) {
            if(key.valuePerWeight > intItem[j].valuePerWeight){
                intItem[j+1] = intItem[j];
                k = j;
            }
            intItem[k] = key;
        }
        
    }
    
    printf("무게당 가치\n");
    
    //
    for(int i =1; i<itemLength; i++) {
        printf("%d, %f, ", intItem[i].b, intItem[i].weight);
        printf("%f\n", intItem[i].valuePerWeight);
    }
    //
}

void fractional_knapsack(float bw, int itemLength, Item floatItem[]) {
    float bagWeight = bw;
    int item = itemLength;
    
    float totalWeight =0.0;
    
    for(int i =1 ; i< itemLength; i++) {
        floatItem[i].valuePerWeight = float(floatItem[i].weight)/ float(floatItem[i].b);
    }
    insertionSort(floatItem, item);
    
    for(int i = 1; i<itemLength ; i++) {
        if(floatItem[i].weight + totalWeight <= bagWeight) {
            totalWeight += floatItem[i].weight;
            printf("%d번째 아이템, value : %d, weight : %f, TotalWeight : %f\n", i, floatItem[i].b, floatItem[i].weight, totalWeight);
        } else {
            float a = bagWeight - totalWeight;
            float value = a * (floatItem[i].b) / floatItem[i].weight;
            totalWeight += a;
            printf("%d번째 아이템 value : %d, Fractioanlvalue : %f, weight : %f, TotalWeight : %f\n", i, floatItem[i].b, value, a, totalWeight);
            break;
        }
    }
}

void knapsack(float bw, int itemLength, Item floatItem[]) {
    
    int bagWeight = bw*10;
    int item = itemLength;
    
    int ** B;
    B = (int **)malloc(sizeof(int*) * item);
    for (int i = 0; i < item; i++)
        B[i] = (int *)malloc(sizeof(int*) * bagWeight);
    
    //    int B[item][bagWeight];
    
    for(int w = 0 ; w<bagWeight; w++) {
        B[0][w] = 0;
    }
    for(int i = 0 ; i <item; i++) {
        B[i][0] = 0;
    }
    
    for(int i=1; i<item; i++) {
        for(int w =1; w<bagWeight; w++) {
            if(floatItem[i].weight <= w) {
                if (floatItem[i].b + B[i-1][w-int(floatItem[i].weight*10)] > B[i-1][w]) {
                    int q =  floatItem[i].b + B[i-1][w-int(floatItem[i].weight*10)];
                    B[i][w] = q;
                } else {
                    B[i][w] = B[i-1][w];
                }
            } else {
                B[i][w] = B[i-1][w];
            }
            
        }
        //         printf("\n");
    }
    
    for(int i=0; i<item ; i++) {
        for(int j=0; j<bagWeight; j++) {
            printf("%d ",B[i][j] );
            
        }
        printf("\n");
    }
    
    printf("배낭에 들어가는 아이템과 남는 무게는??\n");
    print_knapsack(B, item-1, bw, floatItem);
    
    
}


int main(int argc, const char * argv[]) {
    // insert code here...
    
    /*
     
     Homework #2-1, #2-3
     
     
     */
    
    
   
    Item floatItem[8] = {{0.0, 0}, {0.20, 3}, {0.15, 2}, {0.25, 4}, {0.13, 2}, {0.22, 3}, {0.27, 4},
        {0.30, 5} };
    float bagWeight = 1.00;
    int itemWeight[8];
    
    
    int arrayLength = (sizeof(itemWeight) / sizeof(itemWeight[0]));
    
//        knapsack(bagWeight, arrayLength, floatItem); //#2-2
    fractional_knapsack(bagWeight, arrayLength, floatItem); //#2-4
    
    std::cout << "Hello, World!\n";
    return 0;
}

