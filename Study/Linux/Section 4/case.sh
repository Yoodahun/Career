#! /bin/bash

echo " select an option..."

echo " type 1 to Do A"
echo " type 2 to Do B"
echo " type 3 to Do C"
echo " type 4 to do D"

read choice

case $choice in 
	1)
		echo "You choose A ";;
	2)
		echo "You choose B";;
	3)
		echo "You choose C";;
	4)
		echo "You choose D";;

	*)
		echo " NONE of The Above";;
esac
