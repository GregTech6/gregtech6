#!/bin/sh

SCRIPT_PATH="$(dirname "$(readlink -f "$0")")"
PROJECT_PATH="$(dirname "$(dirname "$SCRIPT_PATH")")" # Go up two levels: .meta/ci
cd "$PROJECT_PATH" || exit 42
    
gradle --no-daemon -g ../.gradle uploadArchives

