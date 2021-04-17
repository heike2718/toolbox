# monitoringapp

Anwendung fragt eine konfigurierbare Menge von URLs ab und sendet bei Fehlern eine Mail an eine konfigurierbare Menge an Mailadressen.

Es kann gewählt werden zwischen einmaligen Abfragen oder Pollen. Einmalige Abfragen sind sinnvoll, wenn man der Aufruf durch
 cron schedulen lassen möchte. Als pollintervalMinutes wählt man dann einfach 0.

# Aufruf Dev:
java -jar -Dlog4j.configurationFile=/home/heike/git/konfigurationen/monitoringapp/log4j2.xml -Dmonitoring.configurationFile=/home/heike/git/konfigurationen/monitoringapp/monitoring.json target/monitoringapp.jar

# Aufruf Prod:

$JAVA_HOME/bin/java -jar -Dlog4j.configurationFile=/usr/local/share/monitoringapp/config/log4j2.xml -Dmonitoring.configurationFile=/usr/local/share/monitoringapp/config/monitoring.json /usr/local/share/monitoringapp/monitoringapp.jar

crontab -e
*/20 * * * * /bin/bash /usr/local/share/monitoringapp/run.sh

scheduled die app alle 20 Minuten

## Relesenotes

[Release-Notes](RELEASE-NOTES.md)
