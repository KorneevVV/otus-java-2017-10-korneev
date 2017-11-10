#!/usr/bin/env bash

MEMORY="-Xms512m -Xmx512m "

#SERIAL GC

GC="-XX:+UseSerialGC"
GC_LOG=" -verbose:gc -Xloggc:log/gc_serial.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"

java ${MEMORY} ${GC} ${GC_LOG} -jar target/hmw04.jar

#Parallel GC

GC="-XX:+UseParallelGC"
GC_LOG=" -verbose:gc -Xloggc:log/gc_parallel.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"

java ${MEMORY} ${GC} ${GC_LOG} -jar target/hmw04.jar

#CMS GC

GC="-XX:+UseConcMarkSweepGC"
GC_LOG=" -verbose:gc -Xloggc:log/gc_cms.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"

java ${MEMORY} ${GC} ${GC_LOG} -jar target/hmw04.jar

#G1 GC

GC="-XX:+UseG1GC"
GC_LOG=" -verbose:gc -Xloggc:log/gc_g1.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M"

java ${MEMORY} ${GC} ${GC_LOG} -jar target/hmw04.jar
