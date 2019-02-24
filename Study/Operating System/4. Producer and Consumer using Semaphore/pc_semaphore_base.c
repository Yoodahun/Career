#include <stdio.h>
#include <stdlib.h>
#include <sys/ipc.h> 
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h> 
#include <unistd.h> 
#include <semaphore.h>

#define Q_SIZE 10   //버퍼 크기
#define P_COUNT 6   //생산자의 생산 개수
#define C_COUNT 15  //소비자의 소비 개수

//공유 메모리를 이루는 구조체
typedef struct shared {
    //사용할 세마포어 
	sem_t sem;
	sem_t empty;
    sem_t full;
	int CQ[Q_SIZE]; //버퍼
	int front;  //다음 소비할 위치
	int rear;   //다음 저장 위치
	int CQ_count;   //버퍼에 저장된 데이터 수
}shared;

int shmid;  //공유 메모리 변수
int pid;    //프로세스 변수

//공유 메모리 변수
shared* memory;
void *shared_memory = (void *)0;

int pc = 0;
int cc = 0;

void producer();
void consumer();
int getQ(shared* memory);
int addQ(shared* memory, int input);

int main()
{
	int state;
    int i;
	// 공유 메모리 생성
	shmid = shmget((key_t)1234, sizeof(shared), 0666 | IPC_CREAT);

	if (shmid == -1)
	{
		perror("공유 메모리 생성 실패\n");
		exit(0);
	}

	// 공유 메모리 첨부
	shared_memory = shmat(shmid, (void *)0, 0);
	if (shared_memory == (void *)-1)
	{
		perror("공유 메모리 첨부 실패\n");
		exit(0);
	}

    //첨부한 공유 메모리 초기화
	memory = (shared *)shared_memory;
	memory->front = 0;
	memory->rear = 0;
	memory->CQ_count = 0;

    /*세마포어 값 적당한 수로 초기화*/
	sem_init(&memory->sem, 1, 1);   //뮤텍스처럼 버퍼에 생산자 소비자 중 하나만 접근하도록 제어하는 세마포어
	sem_init(&memory->empty, 1, 2); //버퍼가 비어있으면 실행 안되도록 제어하는 세마포어
        sem_init(&memory->full, 1, 5);  //버퍼가 가득차있으면 실행 안되도록 제어하는 세마포어

    //생산자 및 소비자 생성
	for (i = 0; i<5; i++) {
		producer();
	}
	for (i = 0; i<2; i++) {
		consumer();
	}
	
    //자식 프로세스 종료 대기
    wait(&state);
	sleep(3);

	printf("Shared memory removed\n");
	shmctl(shmid, IPC_RMID, 0);//공유 메모리 제거

	return 0;
}

//생산사 프로세스
void producer() {
	int i;
	int id = ++pc;
	int input;
	int state;

	pid = fork();//자식 프로세스 생성
	if (pid == 0)
	{
		shmid = shmget((key_t)1234, sizeof(shared), 0);
		if (shmid == -1)
		{
			perror("공유 메모리 접근 실패\n");
			exit(0);
		}
		shared_memory = shmat(shmid, (void *)0, 0666 | IPC_CREAT);
		if (shared_memory == (void *)-1)
		{
			perror("공유 메모리 첨부 실패\n");
			exit(0);
		}
		memory = (shared *)shared_memory;

		for (i = 0; i<P_COUNT; i++)
		{
			input = random() % 100 + id;
			usleep(input);
			
			/* 적절한 세마포어 사용*/
			while(memory->CQ_count == 10){
				
			}
			sem_wait(&memory->full);
			sem_wait(&memory->sem);

			state = addQ(memory, input);//공유 메모리 버퍼에 아이템 생산
			printf("producer %d add Q %d\n", id, input);
            		sem_post(&memory->sem);
			sem_post(&memory->empty);
			
			
            /* 적절한 세마포어 사용*/
		}
		printf("****Producer %d END****\n",id);
		exit(0);
	}
}

//소비자 프로세스
void consumer() {
	int i;
	int id = ++cc;
	int output;

	pid = fork();//자식 프로세스 생성
	if (pid == 0)
	{
		shmid = shmget((key_t)1234, sizeof(shared), 0);
		if (shmid == -1)
		{
			perror("공유 메모리 접근 실패\n");
			exit(0);
		}
		shared_memory = shmat(shmid, (void *)0, 0666 | IPC_CREAT);
		if (shared_memory == (void *)-1)
		{
			perror("공유 메모리 첨부 실패\n");
			exit(0);
		}
		memory = (shared *)shared_memory;
		for (i = 0; i<C_COUNT; i++)
		{
			usleep((random() % 100));

            /* 적절한 세마포어 사용*/
			while(memory->CQ_count == 0){
			}
			sem_wait(&memory->empty);
			sem_wait(&memory->sem);
			output = getQ(memory);//공유 메모리 버퍼에서 아이템 소비
			printf("consumer %d get Q %d\n", id, output);
			sem_post(&memory->sem);
			sem_post(&memory->full);
			

            /* 적절한 세마포어 사용*/
		}
		printf("****Consumer %d END****\n", id);
		exit(0);
	}
}

//버퍼에서 데이터 하나를 꺼내는 함수
int getQ(shared* memory) 
{
	int output = 99;
	if (memory->CQ_count == 0)
	{
		printf("CQ is empty\n");
		return -1;
	}
	else {
		output = memory->CQ[memory->rear];
		memory->rear++;
		memory->CQ_count--;
		if (memory->rear == 10)
		{
			memory->rear = 0;
		}
		return output;
	}
}

//버퍼에 데이터를 넣는 함수
int addQ(shared* memory, int input) 
{
	if (memory->CQ_count <10)
	{
		memory->CQ[memory->front] = input;
		memory->front++;
		if (memory->front == 10)
		{
			memory->front = 0;
		}
		memory->CQ_count++;
		return 1;
	}
	else {
		printf("CQ is full\n");
		return -1;
	}
}
