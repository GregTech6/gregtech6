#!/bin/bash

# Get base pathing
SCRIPT_PATH="$(dirname "$(readlink -f "$0")")"
PROJECT_PATH="$(dirname "$(dirname "$SCRIPT_PATH")")" # Go up two levels: .meta/ci

# Get the current Caption
CAPTION="$(curl -s https://gregtech.mechaenetia.com/imagecaption.adoc)"
CAPTION="${CAPTION:1:${#CAPTION}-1}"

# Write the Post
echo 'An Update to GregTech has been released!'
echo "https://gregtech.mechaenetia.com/1.7.10/"
echo ""
echo 'Cue the fully automatic Changelog:'
echo '![Screenshot](https://gregtech.mechaenetia.com'"$(curl -s https://gregtech.mechaenetia.com/gallery/LATEST.image.url))"
echo "$CAPTION"
echo '[quote]'
sed -n '/Not released yet/d;/^[0-9]\+.[0-9]\+.[0-9]\+:/{p;:l n;p;/^\r*$/q;b l}' "$PROJECT_PATH/CHANGELOG.md"
echo '[/quote]'
