#!/usr/bin/env bash

# This script was based on: https://sigmoidal.io/automatic-code-quality-checks-with-git-hooks/

# If any command inside script returns error, exit and return that error
set -e

# Magic line to ensure that we're always inside the root of our application,
# no matter from which directory we'll run script.
# Thanks to it we can just enter `./scripts/run-tests.bash`
cd "${0%/*}/.."

./gradlew build test
