#!/bin/sh

SCRIPT_PATH="$(dirname "$(readlink -f "$0")")"
PROJECT_PATH="$(dirname "$(dirname "$SCRIPT_PATH")")" # Go up two levels: .meta/ci
cd "$PROJECT_PATH" || exit 42

# Only `build`ing to get dependencies, the `dependencies` task only grabs the POM's...
gradle --no-daemon -g ../.gradle --refresh-dependencies setupCIWorkspace build

