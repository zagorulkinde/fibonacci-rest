#!/usr/bin/env bash
cd ..
export CLASS_PATH="config:lib/*"
export JAVA_OPTS="-server -showversion $MEM_OPTS $GC_OPTS -Dfile.encoding=utf-8"

java -classpath $CLASS_PATH $JAVA_OPTS com.emc.fibonacci.Main %*