#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

#define Q_SIZE 10
#define P_COUNT 6
#define C_COUNT 15

//사용하지 않는 변수가 있을 수 있음
pthread_cond_t slots = PTHREAD_COND_INITIALIZER;
pthread_cond_t items = PTHREAD_COND_INITIALIZER;

pthread_mutex_t slot_lock = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t item_lock = PTHREAD_MUTEX_INITIALIZER;

int CQ[Q_SIZE];
int front = 0;
int rear = 0;
int CQ_count = 0;

int pc=1;
int cc=1;

int getQ() //버퍼에서 데이터 하나를 꺼내는 함수
{
	int output = 99;
	if(CQ_count == 0 )
	{
		printf("CQ is empty\n");
		return -1;
	}
	else{
		output = CQ[rear];
		rear++;
		CQ_count--;
		if(rear == 10)
		{
			rear = 0;
		}
		return output;
	}
}
int addQ(int input) //버퍼에 데이터를 넣는 함수
{
	if(CQ_count !=10)
	{
		CQ[front] = input;
		front++;
		if(front == 10)
		{
			front = 0;
		}
		CQ_count++;
		return 1;
	}
	else{
		printf("CQ is full\n");
		return -1;
	}
}
//생산자 쓰레드 함수
void *producer(void *arg)
{
	//필요한 변수 선언
	int i;
	int input;
	int id;
	id = pc++;
	
	for(i = 0; i<P_COUNT; i++)
	{
		input = random()%100; //생산할 랜덤 숫자
		usleep(input);
		
		/*뮤텍스를 이용하여 버퍼에 삽입하는 함수 구현*/
		printf("producer %d add Q %d\n",id, input);
		/*뮤텍스를 이용하여 버퍼에 삽입하는 함수 구현*/
	}
}
//소비자 쓰레드 함수
void *consumer(void *arg)
{
	//필요한 변수 선언
	int i;
	int output;
	int id;
	id = cc++;
	
	for(i = 0; i<C_COUNT; i++)
	{
		usleep(random()%100);
                
		/*뮤텍스를 이용하여 버퍼에서 꺼내오는 함수 구현*/
		printf("consumer %d get Q %d\n",id, output);
		/*뮤텍스를 이용하여 버퍼에서 꺼내오는 함수 구현*/
	}
}

int main()
{
	pthread_t p_thread[7];
	int thread_id[7];
	int status;
	int i;

	for(i = 0; i < 5 ; i++)
	{
		thread_id[i] = pthread_create(&p_thread[i], NULL, producer, NULL);
		if(thread_id[i] < 0 )
		{
			exit(0);
		}
	}
	for(i = 5; i < 7; i++)
	{
		thread_id[i] = pthread_create(&p_thread[i], NULL, consumer, NULL);
		if(thread_id[i] <0)
		{
			exit(0);
		}
	}

	pthread_join(p_thread[0], (void **)&status);
	pthread_join(p_thread[1], (void **)&status);
	pthread_join(p_thread[2], (void **)&status);
	pthread_join(p_thread[3], (void **)&status);
	pthread_join(p_thread[4], (void **)&status);
	pthread_join(p_thread[5], (void **)&status);
	pthread_join(p_thread[6], (void **)&status);

	return 0;
}
