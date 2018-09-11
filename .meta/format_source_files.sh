#!/bin/sh

SOURCE_DIR=`dirname $0`

[ -z "$LICENSE_HEADER" ] && export LICENSE_HEADER="$(cat "$SOURCE_DIR/LICENSE.header")"

ORIGINAL_HEADER="$(sed '/^package/Q' "$1")"

if [ "$LICENSE_HEADER" = "$ORIGINAL_HEADER" ]; then
	echo "Already has header: $1"
else
	echo "Applying header to: $1"
	cp "$SOURCE_DIR/LICENSE.header" "$1.tmp"
	echo "" >> "$1.tmp"
fi

awk '/^package/{i++}i' "$1" >> "$1.tmp"
unexpand --tabs=4 "$1.tmp" > "$1"
rm "$1.tmp"
