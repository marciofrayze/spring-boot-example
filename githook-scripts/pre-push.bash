#!/usr/bin/env bash

echo "Executing pre-push hook"
./githook-scripts/run-tests.bash

# $? stores exit value of the last command
if [ $? -ne 0 ]; then
 echo "ERROR: Test must be green before you do push!"
 exit 1
fi
