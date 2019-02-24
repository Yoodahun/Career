#! /bin/bash

echo "line 1"
echo "line 2"
echo "line 3"
echo "line 4"

read -p "How old are you?" age

if [ $age -lt 0 ]; then
	echo "You cannot have a negative age!"
	exit
fi

echo " "
echo "Ok let's continue with the script:"
echo "line 6"
echo "lin 7....."
