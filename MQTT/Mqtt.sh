#!/bin/bash
javac -classpath ".:org.eclipse.paho.client.mqttv3-1.2.5.jar“ MqttExample.java
sudo java -classpath ".:org.eclipse.paho.client.mqttv3-1.2.5.jar“ MqttExample