#! /bin/bash

read -p "Type something: " str

if [ -z "$str" ]; then
	echo "this is an empty String."
	exit
fi

echo "moving on "
