#!/bin/sh

SOURCE_DIR=`dirname $0`

export LICENSE_HEADER="$(cat "$SOURCE_DIR/LICENSE.header")"

if [ -z "$1" ]; then
	find "$SOURCE_DIR/../src/main/java" -iname "*.java" -exec "$SOURCE_DIR/update_license_header.sh" {} \;
else
	for f in "${@}"; do
	 	"$SOURCE_DIR"/update_license_header.sh "$f"
	done
fi
