#!/bin/sh
# Do not use unless you are Greg!

echo "Formatting all Source Files"

SOURCE_DIR=$(dirname "$0")

LICENSE_HEADER="$(cat "$SOURCE_DIR/LICENSE.header")"
export LICENSE_HEADER

if [ -z "$1" ]; then
	# The 1am thing takes Gregs Timezone into account.
	# DO NOT FORGET TO CHANGE LICENSE.header WHEN INCREMENTING YEAR!!!
	find "$SOURCE_DIR/../src/main/java" -newermt "2021-05-15 01:00:00" -iname "*.java" -exec "$SOURCE_DIR/format_source_file.sh" {} \;
# 	find "$SOURCE_DIR/../src/main/java" -mtime -40 -iname "*.java" -exec "$SOURCE_DIR/format_source_file.sh" {} \;
	find "$SOURCE_DIR/../src/main/resources" -iname "*.png" -mtime -10 -exec optipng -o7 -nc -clobber -silent {} \;
else
	for f in "${@}"; do
	 	"$SOURCE_DIR"/format_source_files.sh "$f"
	done
fi

echo "Done formatting all Source Files"
