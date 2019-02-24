#! /bin/bash

# Condition to check if a file EXISTS
read -p "Give me the name of file" myfile

if [ -e $myfile ]; then
	echo "$myfile EXISTS"
fi

#NEGATE A Condition
if [ ! -e $myfile ]; then
	echo "$myfile does not exists"
fi

#Condition to check if a file is a directory

if [ -d $myfile ]; then
	echo "$myfile is directory "
else
	echo "$myfile is not a directory"
fi


#Condition to check if a file is READABLE
if [ -r $myfile ]; then
	echo "$myfile is a Readable"
fi

#Condition to check if a file is writable
if [ -w $myfile ]; then
	echo "$myfile is writable"
else
	echo "$myfile is not writable"
fi

#Condition to check if a file executable
if [ -x $myfile ]; then
	echo "$myfile is executable"
else
	echo "$myfile is NOT executable"
fi

#Condition to check if a file is NOT EMPTY

if [ -s $myfile ]; then
	echo "$myfile is not empty"
else 
	echo "$myfile is empty"
fi


