#!/bin/sh

SOURCE_DIR=`dirname $0`

export LICENSE_HEADER="$(cat "$SOURCE_DIR/LICENSE.header")"

if [ -z "$1" ]; then
	find "$SOURCE_DIR/../src/main/java" -iname "*.java" -exec "$SOURCE_DIR/format_source_files.sh" {} \;
else
	for f in "${@}"; do
	 	"$SOURCE_DIR"/format_source_files.sh "$f"
	done
fi

echo "Done formatting Source Files"
