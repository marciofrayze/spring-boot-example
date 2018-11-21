#!/usr/bin/env bash
./gradlew clean restdoc jacocoTestReport bootRun --stacktrace --info --warning-mode=all
