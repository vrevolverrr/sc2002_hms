#!/bin/bash

SOURCE_DIR="src"
OUTPUT_DIR="out"

OUTPUT_DIR_DOCS="docs"
PACKAGES="controller:model:repository:services:utils:view:lib"

JAR_FILE="HMSApp.jar"
MAIN_CLASS="HMSApp"

# Clean the output directory
rm -rf $OUTPUT_DIR
mkdir -p $OUTPUT_DIR

rm -rf $OUTPUT_DIR_DOCS
mkdir -p $OUTPUT_DIR_DOCS

echo "Buidling application..."

# Compile source files
javac --release 16 -d $OUTPUT_DIR -sourcepath $SOURCE_DIR $(find $SOURCE_DIR -name "*.java")

# Check for compilation errors
if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
fi

# Create the manifest file
echo "Main-Class: $MAIN_CLASS" > $OUTPUT_DIR/MANIFEST.MF

# Create JAR file with the manifest
jar --create --file $JAR_FILE --manifest $OUTPUT_DIR/MANIFEST.MF -C $OUTPUT_DIR .

# Clean up the manifest file
rm $OUTPUT_DIR/MANIFEST.MF

echo
echo "Build successful. Bundled JAR file at $JAR_FILE"

echo
echo "Generating Javadocs..."

# Generate Javadocs
javadoc -d $OUTPUT_DIR_DOCS -sourcepath $SOURCE_DIR -subpackages $PACKAGES -private

if [ $? -ne 0 ]; then
    echo "Javadoc generation failed."
    exit 1
fi

echo
echo "Javadocs generated successfully in $OUTPUT_DIR"