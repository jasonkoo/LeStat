#!/bin/sh
cd ..
APP_HOME=$(cd "$(dirname "$0")"; pwd)
#JAVA_HOME=/home/gegw/jdk/jdk1.6
#LANG=zh_CN.GB18030
LIB_HOME=$APP_HOME/lib
CONFIG_HOME=$APP_HOME/config
#MAIN=com.leadtone.test.ad.App
MAIN=com.liuhongkai.test.App

cd $LIB_HOME
for l in `ls`
do
    CLASSPATH=$CLASSPATH:$LIB_HOME/$l
done

export APP_HOME JAVA_HOME LANG CLASSPATH LIB_HOME MAIN

echo JAVA_HOME $JAVA_HOME
echo APP_HOME $APP_HOME
echo LIB_HOME $LIB_HOME
echo CONFIG_HOME $CONFIG_HOME
echo CLASSPATH $CLASSPATH

#somehow we need an extra "/" for spring config file when using absolute path
$JAVA_HOME/bin/java  $MAIN /$CONFIG_HOME/applicationContext.xml $CONFIG_HOME/log4j.xml 
