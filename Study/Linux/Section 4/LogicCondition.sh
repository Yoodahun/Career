#! /bin/bash

read -p "How old are you?" age

if [ $age -lt 0 -o $age -gt 200 ]; then
	echo "Number Not Acceptable"
	exit
fi

if [ $age -lt 64 -a $age -gt 26 ]; then
	 echo "you are between 26 and 64"
  	exit
 fi




echo "let us continue"
