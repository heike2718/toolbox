#!bin/bash

exec java -jar -Dlog4j.configurationFile=/home/heike/git/toolbox/monitoringapp/src/test/resources/log4j2-test.xml -Dmonitoring.configurationFile=/home/heike/git/toolbox/monitoringapp/src/test/resources/.monitoring.json ../target/monitoringapp.jar
