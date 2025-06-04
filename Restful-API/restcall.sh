#!/bin/bash
javac -classpath .:unirest-java-3.13.11-standalone.jar RESTCall.java
java -classpath .:unirest-java-1.4.9.jar RESTCall