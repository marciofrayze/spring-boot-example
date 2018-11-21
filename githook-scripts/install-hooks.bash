#!/usr/bin/env bash

GIT_DIR=$(git rev-parse --git-dir)

echo "Installing hooks..."
# This command creates symlink to our pre-commit script
ln -s ../../githook-scripts/pre-push.bash $GIT_DIR/hooks/pre-push
echo "done!"

