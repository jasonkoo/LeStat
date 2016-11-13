#! /bin/bash
cd ..
APP_HOME=$(cd "$(dirname "$0")"; pwd)
JAVA_HOME=/usr/java/jdk1.6.0_31
LIB_HOME=$APP_HOME/lib
CONF_HOME=$APP_HOME/conf
MAIN=com.lenovo.push.marketing.hive.util.JdbcUtil

cd $LIB_HOME
for l in `ls`
do
    CLASSPATH=$CLASSPATH:$LIB_HOME/$l
done



export APP_HOME JAVA_HOME CLASSPATH LIB_HOME MAIN

echo JAVA_HOME $JAVA_HOME
echo APP_HOME $APP_HOME
echo LIB_HOME $LIB_HOME
echo CONF_HOME $CONF_HOME
echo CLASSPATH $CLASSPATH

#somehow we need an extra "/" for spring config file when using absolute path
#for i in 0 1 2 3 4 5
#do
#    $JAVA_HOME/bin/java -Xms2g -Xmx2g -Xmn1g -Dlestat.home=$APP_HOME -Dlogfile.name=lestat-kafka.$i.log $MAIN kafka $i 6 20140608101010 &
#done

HADOOP_HOME=/etc/hadoop
HIVE_HOME=/etc/hive


#CLASSPATH=$CLASSPATH:$HIVE_HOME/conf

$JAVA_HOME/bin/java -Dlestat.home=$APP_HOME $MAIN
