//
//  main.cpp
//  Algorithm_12_ActivitySelection
//
//  Created by 유다훈 on 2017. 12. 11..
//  Copyright © 2017년 유다훈. All rights reserved.
//

#include <iostream>

struct activitySelection {
    int number;
    int start;
    int finish;
    int value;
};

void finishTime_insertionSort( activitySelection * activity_select ) {
    /* finish sorting*/
    for( int i = 1; i < 12 ; i++) {
        activitySelection finishKey = activity_select[i];
        int k = i;
        
        for (int j= i-1; j>=1; j--) {
            if(finishKey.finish < activity_select[j].finish) {
                activity_select[j+1] = activity_select[j];
                k = j;
            }
            activity_select[k] = finishKey;
        }
        
    }
    /*finish sorting*/
    
    printf("종료시간 기준 정렬\n");
    
    for(int i =1; i<12; i++) {
                printf("%d, %d, ", activity_select[i].number, activity_select[i].start);
                printf("%d\n", activity_select[i].finish);
            }
    
}

void startTime_insertionSort( activitySelection * activity_select ) {
    /* start sorting*/
    for( int i = 1; i < 12 ; i++) {
        activitySelection startKey = activity_select[i];
        int k = i;
        
        for (int j= i-1; j>=1; j--) {
            if(startKey.start < activity_select[j].start) {
                activity_select[j+1] = activity_select[j];
                k = j;
            }
            activity_select[k] = startKey;
        }
        
    }
    /* start sorting */
    
    printf("시작시간 기준 정렬\n");
    
    for(int i =1; i<12; i++) {
        printf("%d, %d, ", activity_select[i].number, activity_select[i].start);
        printf("%d\n", activity_select[i].finish);
    }
    
}

void print_activity(int ** c,int ** t, activitySelection * activity_select ,int s, int f) {
    /*
     Activity들의 출력
     */
    int k=0;
    if (c[s][f] > 0) {
        k = t[s][f];
        printf("%d, ", activity_select[k].number);
        print_activity(c, t, activity_select, s, k);
        print_activity(c, t, activity_select, k, f);
    }
}

void dynamic_activity_selector(activitySelection * activity_select, int n, int maxValue) {
    int ** c;
    int ** s;
    int length = n+1; //dynamic Programming 의 방법은 0 ~ n+1
   
    /* 배열의 초기화 */
    c = (int **)malloc(sizeof(int*) * length);
    s = (int **)malloc(sizeof(int*) * length);
    for (int i = 0; i < length; i++) {
        c[i] = (int *)malloc(sizeof(int *) * length);
        s[i] = (int *)malloc(sizeof(int*) * length);
    }
    for(int i=0; i<length; i++) {
        c[i][i] = 0;
        s[i][i] =0;
    }
    /* 배열의 초기화 */
    
    /* Dynamic Activity Selector */
    for(int l = 1; l<=length; l++) {
        for(int i = 0; i<=n-l+1; i++) {
            int j = i+l;
            c[i][j]=0; //스케줄링에 따른 activity의 개수를 적어나갈 매트릭스
            s[i][j]=0; // 어떠한 activity를 수행하게 될지 기록하는 매트릭스.
                for(int k = 2 ; k<=j-1; k++) {
                    if(activity_select[k].start >= activity_select[i].finish &&
                       activity_select[k].finish <= activity_select[j].start) {
                        int q = c[i][k] + c[k][j]+1;
                        if(q > c[i][j]) {
                            c[i][j] = q;
                            s[i][j] = k;
                    }
                }
            }
        }

    }
    /* Dynamic Activity Selector */
    
    /* 매트릭스의 출력과 해당  Activity의 출력 */
    for(int i=0; i<length; i++) {
        for(int j=0; j<length; j++) {
            printf("%d ", c[i][j]);
        }
        printf("\n");
    }
    printf("\n");

    
    for(int i=0; i<length; i++) {
        for(int j=0; j<length; j++) {
            printf("%d ", s[i][j]);
        }
        printf("\n");
    }

    printf("\n");
    
    printf("총 스케줄의 개수 : %d\n" , c[0][length-1]);
    print_activity(c,s,activity_select, 0, 12);
    
    printf("\n");
  
}



void greedy_Activity_Selector( activitySelection * activity_select, int arrayLength) {
    int n= arrayLength;
    int total = 0; //액티비티의 총 갯수를 저장할 변수
    
    activitySelection a[n]; //해당되는 액티비티의 번호를 저장할 배열.
    a[1] = activity_select[1]; //비교를 위해 첫번째 스케줄을 넣고 시작.
    int index = 2; //해당되는 액티비티의 배열을 조절할 인덱스
    int k = 1;
    
    for (int m = 2; m<n; m++) {
        if (activity_select[m].start >= activity_select[k].finish) {
            // m값의 스타트가 k값의 피니쉬보다 크거나 같다면?
            a[index] = activity_select[m]; //selection에 추가
            k = m; //k값을 m값으로 변경.
            index++; //index 증가
            total++; //토탈 증가
        }
    }
    
    printf(" 총 스케줄의 개수 : %d\n" , total);
    for(int i=1; i<index; i++) {
        if(a[i].number == NULL) {
            break;
        }
        printf("%d, " , a[i].number);
    }
     printf("\n");
    
}

int main(int argc, const char * argv[]) {
    // insert code here...
    
    FILE *rf = fopen("activity_input.txt", "r");
    if ( rf == NULL)
        printf("read file open error");
    
    int maxValue = 0x7fffff; //무한대값
    // Struct Graph
    
    
    
    activitySelection * activity_select;
    activity_select = (activitySelection *)malloc(sizeof(activitySelection) * 13);
    
    
    activity_select[0].start=0;
    activity_select[0].finish = 0;
    
    
    // Initialization
    for (int i = 1; i < 12; i++){
        activity_select[i].number = i;
        fscanf(rf, "%d", &activity_select[i].start);
        fscanf(rf, "%d", &activity_select[i].finish);
        fscanf(rf, "%d", &activity_select[i].value); //첫번째 수열
    }
    
    activity_select[12].start = maxValue;
    
//     int arrayLength = (sizeof(activity_Select) / sizeof(activitySelection));
  
//    printf("끝나는 시간 순서로 정렬한 값\n");
//    finishTime_insertionSort(activity_select); //#1-3
    
//    printf("시작하는 시간 순서로 정렬한 값\n");
//      startTime_insertionSort(activity_select); //#1-4
 
    
//        dynamic_activity_selector(activity_select, 12, maxValue); //#1-1
    greedy_Activity_Selector(activity_select, 13); //#1-3, 4, 5
    
    std::cout << "Hello, World!\n";
    return 0;
}
