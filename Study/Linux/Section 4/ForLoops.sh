#! /bin/bash

echo "some lines of code here"

for i in {1,"dog",-5,"danger","hello"}
do
	echo "hello this is $i"
	if [ $i == "danger" ]; then
		echo "WE have to stop the loop here! because it is $i"
		break;
	fi
done

echo "we continue..."


for i in ./*
do
	echo "name of file is: $i"
done


