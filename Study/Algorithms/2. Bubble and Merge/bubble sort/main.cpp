//
//  main.cpp
//  algorithm_02_bubble
//
//  Created by 유다훈 on 2017. 9. 18..
//  Copyright © 2017년 yoo_dahun. All rights reserved.
//

#include <iostream>
#include <time.h>

int main(int argc, const char * argv[]) {

        // insert code here...
        
        // File Open
//        FILE *rf = fopen("test_10.txt", "r");
//        FILE *rf = fopen("test_100.txt", "r");
//        FILE *rf = fopen("test_1000.txt", "r");
        FILE *rf = fopen("test_10000.txt", "r");
    
//        FILE *wf = fopen("out_10.txt", "w");
//        FILE *wf = fopen("out_100.txt", "w");
//        FILE *wf = fopen("out_1000.txt", "w");
        FILE *wf = fopen("out_10000.txt", "w");
        if ( rf == NULL)
            printf("read file open error\n");
        if ( rf == NULL) printf("write file open error\n");
        
        // Read file and create array
//        int array[10];
//        int array[100];
//        int array[1000];
        int array[10000];
    
    int index = 0;
        while (!feof(rf)) {
            int value = 0;
            fscanf(rf, "%d", &value);
            array[index++] = value;
            printf("%d\n", value);
        }
    
        clock_t start, end;
        double sortingTime;
    
    
        /*
         *
         *	your code
         *
         */
        
        int arrayLength = (sizeof(array) / sizeof(array[0]));
        
        /* Bubble sort */
    
        start = clock();
    
        for(int i = 0; i < arrayLength-1; i++ ) {
            
            for(int z = 0; z <= (arrayLength-i); z++) {
                if(array[z] > array[z+1]) {
                    int temp = array[z];
                    array[z] = array[z+1];
                    array[z+1] = temp;
                }
                
            }
        }
    
        end = clock();
        sortingTime = (double)(end - start) / CLOCKS_PER_SEC;
    
        /* Bubble sort end */
        /**/
        // Print to Write File
        printf("%s\n", "출력합니다.");
        printf("%f\n", sortingTime);
        for(int i=0; i < arrayLength ; i++) {
            fprintf(wf, "%d\n", array[i]);
        }
        
        
        // File Close
        fclose(rf);
        fclose(wf);
    
        
    
    return 0;
}
