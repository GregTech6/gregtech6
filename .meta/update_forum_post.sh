#!/bin/bash

# Get the current Caption
CAPTION="$(curl -s https://gregtech.overminddl1.com/imagecaption.adoc)"
CAPTION="${CAPTION:2:${#CAPTION}-2}"

# Write the Post
echo 'Another GregTech Update has been released!' > .post.txt
echo "https://gregtech.overminddl1.com/1.7.10/" >> .post.txt
echo "" >> .post.txt
echo 'Cue the fully automatic Changelog:' >> .post.txt
echo '![Screenshot](https://gregtech.overminddl1.com'"$(curl -s https://gregtech.overminddl1.com/gallery/LATEST.image.url))" >> .post.txt
echo "$CAPTION" >> .post.txt
echo '[quote]' >> .post.txt
sed -n '/Not released yet/d;/^[0-9]\+.[0-9]\+.[0-9]\+:/{p;:l n;p;/^\r*$/q;b l}' ~/Minecraft/GT6/CHANGELOG.md >> .post.txt
echo '[/quote]' >> .post.txt
echo "And as always, the [Patreon Post](https://www.patreon.com/gregoriust/overview) should follow up later today or somewhen tomorrow. ;)" >> .post.txt

# Upload the Post
curl -X PUT --netrc-file ~/Websites/gregtech.overminddl1.com/.netrc https://maven.gregtech.overminddl1.com/upload/POST --upload-file .post.txt
