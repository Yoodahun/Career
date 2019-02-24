
#test1

touch 1.txt 2.txt

ls -l >> 1.txt
read -p " prefix to add to 1.txt? " pref

mv 1.txt ${pref}1.txt

echo "done"
