#!/usr/bin/env bash
./gradlew clean restdoc jacocoTestReport checkstyleMain checkstyleTest dependencyCheckAnalyze build --stacktrace --info --warning-mode=all
