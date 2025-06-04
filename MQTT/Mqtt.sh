#!/bin/bash
javac -cp .:org.eclipse.paho.client.mqttv3-1.2.5.jar MqttExample.java
java -classpath .:org.eclipse.paho.client.mqttv3-1.2.5.jar MqttExample