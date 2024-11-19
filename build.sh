#!/bin/bash

SOURCE_DIR="src"
OUTPUT_DIR="out"
JAR_FILE="HMSApp.jar"

# Compile source files
javac --release 17 -d $OUTPUT_DIR -sourcepath $SOURCE_DIR $(find $SOURCE_DIR -name "*.java")

# Create JAR file
jar --create --file $JAR_FILE -C $OUTPUT_DIR .