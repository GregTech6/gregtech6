#!/bin/sh

SCRIPT_PATH="$(dirname "$(readlink -f "$0")")"
PROJECT_PATH="$(dirname "$(dirname "$SCRIPT_PATH")")" # Go up two levels: .meta/ci
cd "$PROJECT_PATH"
    
gradle -g ../.gradle --refresh-dependencies setupCIWorkspace build

