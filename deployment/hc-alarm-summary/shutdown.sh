#!/bin/bash

pid=`ps -ef | grep -i 'com.hcss.message.MsgMain'| grep -v "grep"|awk '{print $2}'`
kill -9 ${pid}
echo 'kill sendMsg success!'