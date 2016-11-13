#! /bin/bash
cd ..
APP_HOME=$(cd "$(dirname "$0")"; pwd)
JAVA_HOME=/usr/java/jdk1.6.0_31
LIB_HOME=$APP_HOME/lib
CONF_HOME=$APP_HOME/conf
MAIN=com.lenovo.push.marketing.lestat.core.App

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

#CLASSPATH=$CLASSPATH:/data/cloudera/parcels/HADOOP_LZO/lib/hadoop/lib/hadoop-lzo.jar

#somehow we need an extra "/" for spring config file when using absolute path
$JAVA_HOME/bin/java -Dlestat.home=$APP_HOME -Dlogfile.name=lestat-db.0.log $MAIN db $@