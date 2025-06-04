#!/bin/bash
javac -classpath "unirest-java-1.4.9.jar" RESTCall.java
sudo java -classpath ".:unirest-java-1.4.9.jar" RESTCall