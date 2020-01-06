#!/bin/sh

echo "Formatting all Source Files"

SOURCE_DIR=$(dirname "$0")

LICENSE_HEADER="$(cat "$SOURCE_DIR/LICENSE.header")"
export LICENSE_HEADER

if [ -z "$1" ]; then
	find "$SOURCE_DIR/../src/main/java" -newermt 20200101 -iname "*.java" -exec "$SOURCE_DIR/format_source_file.sh" {} \;
# 	find "$SOURCE_DIR/../src/main/java" -mtime -40 -iname "*.java" -exec "$SOURCE_DIR/format_source_file.sh" {} \;
	find "$SOURCE_DIR/../src/main/resources" -iname "*.png" -mtime -10 -exec optipng -o7 -nc -clobber -silent {} \;
else
	for f in "${@}"; do
	 	"$SOURCE_DIR"/format_source_files.sh "$f"
	done
fi

echo "Done formatting all Source Files"
