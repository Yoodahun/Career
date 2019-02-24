#! /bin/bash

echo "hello"

if [ "abCd" == "abcd" ]; then
	echo "EQUAL !"
fi

echo "moving on"


if [ "abcd" != "aBcd" ]; then
	echo " Different "
fi

echo "End"
