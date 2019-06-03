#!/bin/sh

echo "Formatting all Source Files"

SOURCE_DIR=$(dirname "$0")

LICENSE_HEADER="$(cat "$SOURCE_DIR/LICENSE.header")"
export LICENSE_HEADER

if [ -z "$1" ]; then
	find "$SOURCE_DIR/../src/main/java" -iname "*.java" -exec "$SOURCE_DIR/format_source_file.sh" {} \;
	find "$SOURCE_DIR/../src/main/resources" -iname "*.png" -exec optipng -clobber -silent {} \;
else
	for f in "${@}"; do
	 	"$SOURCE_DIR"/format_source_files.sh "$f"
	done
fi

echo "Done formatting all Source Files"

