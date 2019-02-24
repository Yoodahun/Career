#! /bin/bash

read -p "How old are you?" age

if [ $age -gt 100 ]; then
	echo "you are not very young"

else 
	echo "You are still young"
fi

echo "bye"
