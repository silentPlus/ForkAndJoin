#!/bin/sh
export CLASSPATH=.:./conf
for jarpath in `ls lib/*.jar`
do
   CLASSPATH=$CLASSPATH:$jarpath   
done
export CLASSPATH=$CLASSPATH
echo $JRE_HOME
nohup ../jdk1.7.0_79/bin/java -server -Xmx1024m -Xms1024m com.hcss.message.MsgMain $* &
