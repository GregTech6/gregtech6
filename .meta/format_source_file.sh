#!/bin/sh

SOURCE_DIR=$(dirname "$0")
TAB=$(printf '\t')

if [ -z "$LICENSE_HEADER" ]; then
	LICENSE_HEADER="$(cat "$SOURCE_DIR/LICENSE.header")"
	export LICENSE_HEADER
fi

ORIGINAL_HEADER="$(sed '/^package/Q' "$1")"

if [ "$LICENSE_HEADER" = "$ORIGINAL_HEADER" ] && ! grep -Eq "(^    |[^$TAB]$TAB)" "$1"; then
	# echo "Already formatted: $1"
	exit 0
else
	echo "$(tput bold)Formatting: $1$(tput sgr0)"
	# Start with license
	cp "$SOURCE_DIR/LICENSE.header" "$1.tmp"
	# Add a newlin
	echo "" >> "$1.tmp"
	# Remove anything above the `^package` line and append rest to tmp
	awk '/^package/{i++}i' "$1" >> "$1.tmp"
	# Convert 4-leading-spaces to tabs and write to original file
	# Specify --first-only as some systems don't default to that as the man page says it should
	expand --tabs=4 "$1.tmp" | unexpand --first-only --tabs=4 > "$1"
	# And finally remove the tmp file
	rm "$1.tmp"
fi
