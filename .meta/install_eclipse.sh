#!/bin/bash
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"
cd "$SCRIPT_DIR/.." || exit 42
./gradlew --refresh-dependencies setupDecompWorkspace build eclipse

