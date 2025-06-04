#!/bin/bash
javac -classpath ".:pi4j-2.3.0/lib/*" ControlGpioExample.java
sudo java -classpath ".:pi4j-2.3.0/lib/*" ControlGpioExample