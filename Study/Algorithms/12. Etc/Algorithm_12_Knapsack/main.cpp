//
//  main.cpp
//  Algorithm_12_Knapsack_Homework #2-1, #2-3
//
//  Created by 유다훈 on 2017. 12. 11..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>
struct Item {
    int weight;
    int b;
    float valuePerWeight;
};


void print_knapsack(int ** B, int n, int w, Item intItem[]) {
    if(n == 0) {
        return;
    }
    if(B[n][w] > B[n-1][w] ){
        printf("%d번 아이템, 무게: %d \n", n, intItem[n].weight);
        return print_knapsack(B, n-1, w-intItem[n].weight,intItem);
    } else {
        return print_knapsack(B, n-1, w,intItem);
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
        printf("%d, %d, ", intItem[i].b, intItem[i].weight);
        printf("%f\n", intItem[i].valuePerWeight);
    }
//
}

void fractional_knapsack(int bw, int itemLength, Item intItem[]) {
    int bagWeight = bw;
    int item = itemLength;
    
    int totalWeight =0;
    
    for(int i =1 ; i< itemLength; i++) {
        intItem[i].valuePerWeight =  float(intItem[i].b) / float(intItem[i].weight) ;
    }
    insertionSort(intItem, item); //무게당 가치를 큰값이 앞으로 오게끔 내림차순 정렬
    
    for(int i = 1; i<itemLength ; i++) {
        if(intItem[i].weight + totalWeight <= bagWeight) {//현재 아이템의 무게 + 총 무게가 가방무게보다 작을 경우
            totalWeight += intItem[i].weight; //무게당 가치가 큰 아이템들 부터 그냥 저장
            printf("%d번째 아이템, value : %d, weight : %d, TotalWeight : %d\n",i, intItem[i].b, intItem[i].weight, totalWeight);
        } else { //가방 무게보다 클 경우
            int a = bagWeight - totalWeight; //현재 남은 무게 계산
            float value = a * (intItem[i].b / intItem[i].weight); //단위 무게당 가치에 남은 무게를 곱하여 가치 계산.
            totalWeight += a;
            printf("%d번째 아이템, value : %d, Fractioanlvalue : %f, weight : %d, TotalWeight : %d\n", i,intItem[i].b, value, a, totalWeight);
            break; //더이상 가방에 아이템을 담으면 안되므로 반복문 종료
        }
    }
}

void knapsack(int bw, int itemLength, Item intItem[]) {
    
    int bagWeight = bw;
    int item = itemLength;
    
    int ** B;
    B = (int **)malloc(sizeof(int*) * item);
    for (int i = 0; i < item; i++)
        B[i] = (int *)malloc(sizeof(int*) * bagWeight);
    
    for(int w = 0 ; w<bagWeight; w++) {
        B[0][w] = 0;
    }
    for(int i = 0 ; i <item; i++) {
        B[i][0] = 0;
    }
    /* 0-1 Knapsack Problem */
    for(int i=1; i<item; i++) {
        for(int w =1; w<bagWeight; w++) {
            if(intItem[i].weight <= w) { //현재 아이템의 무게가 현재 무게보다 작거나 같다면
                if (intItem[i].b + B[i-1][w-intItem[i].weight] > B[i-1][w]) {
                    //현재 아이템의 가치 + 이전 아이템의 가치 가 이전아이템의 가치보다 크다면
                    int q =  intItem[i].b + B[i-1][w-intItem[i].weight];
                    B[i][w] = q; //헤당 값을 저장
                } else {
                    B[i][w] = B[i-1][w]; //그렇지 않다면 그냥 이전 아이템의 가치를 저장.
                }
            } else {
                B[i][w] = B[i-1][w];
            }

       }
//         printf("\n");
    }
    /* 0-1 Knapsack Problem */
    
    for(int i=0; i<item ; i++) {
        for(int j=0; j<bagWeight; j++) {
            printf("%d ",B[i][j] );

        }
        printf("\n");
    }
    
    printf("배낭에 들어가는 아이템은?\n");
    print_knapsack(B, item-1, bagWeight-1, intItem);
     printf("\n");
    
}


int main(int argc, const char * argv[]) {
    // insert code here...
    
    /*
     
     Homework #2-1, #2-3
     
     
     */
    
    
    Item intItem[8] = {{0,0}, {1,1}, {2,3}, {3,5},{3,6}, {4, 8}, {4,9}, {5,11}};
   
    int bagWeight = 10;
    int itemWeight[8];

    
    int arrayLength = (sizeof(itemWeight) / sizeof(itemWeight[0]));
    
//        knapsack(bagWeight+1, arrayLength, intItem); //#2-1
    fractional_knapsack(bagWeight, arrayLength, intItem); //#2-3
    
    std::cout << "Hello, World!\n";
    return 0;
}
