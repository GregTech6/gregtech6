#!/bin/sh
# Do not use unless you are Greg!

echo "Formatting all Source Files"

SOURCE_DIR=$(dirname "$0")

LICENSE_HEADER="$(cat "$SOURCE_DIR/LICENSE.header")"
export LICENSE_HEADER

if [ -z "$1" ]; then
	# If there is a different Date mentioned than the 01 01, then that just means Greg might've gotten a new SSD or Computer.
	# The 1am thing takes Gregs Timezone into account.
	# DO NOT FORGET TO CHANGE LICENSE.header WHEN INCREMENTING YEAR!!!
	find "$SOURCE_DIR/../src/main/java" -newermt "2025-01-01 01:00:00" -iname "*.java" -exec chmod 664 {} \; -exec "$SOURCE_DIR/format_source_file.sh" {} \;
# 	find "$SOURCE_DIR/../src/main/java" -mtime -40 -iname "*.java" -exec "$SOURCE_DIR/format_source_file.sh" {} \;
	find "$SOURCE_DIR/../src/main/resources" -iname "*.sh"  -mtime -10 -exec chmod 774 {} \;
	find "$SOURCE_DIR/../src/main/resources" -iname "*.png" -mtime -10 -exec chmod 664 {} \; -exec optipng -o7 -nc -clobber -silent {} \;
else
	for f in "${@}"; do
	 	"$SOURCE_DIR"/format_source_files.sh "$f"
	done
fi

echo "Done formatting all Source Files"
