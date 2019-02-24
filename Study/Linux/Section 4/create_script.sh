#! /bin/bash

read -p "name of script to create: " name_s

touch ${name_s}

echo " #! /bin/bash " >> ${name_s}
echo " ###### Automatically Created Script ######" >> ${name_s}
echo " pwd " >> ${name_s}
echo " cal " >> ${name_s}


chmod +x ${name_s}

echo " DONE "
