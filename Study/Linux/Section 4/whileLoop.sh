#! /bin/bash

echo "some lines of code here"

number1=1

while [ $number1 -le 7 ];
do
	echo "number is $number1"
	number1=$(( number1 + 2 ))
done


echo " "
echo "bye"

echo "we continue..."

