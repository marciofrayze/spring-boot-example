#!/usr/bin/env bash
./gradlew clean restdoc jacocoTestReport checkstyleMain checkstyleTest build --stacktrace --info --warning-mode=all
