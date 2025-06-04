#!/bin/bash
javac -classpath ".:pi4j-core.jar" ControlGpioExample.java
sudo java -classpath ".:pi4j-core.jar" ControlGpioExample