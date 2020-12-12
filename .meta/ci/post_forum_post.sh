#!/bin/bash

# Get base pathing
SCRIPT_PATH="$(dirname "$(readlink -f "$0")")"
PROJECT_PATH="$(dirname "$(dirname "$SCRIPT_PATH")")" # Go up two levels: .meta/ci

if [ ! -z "$FORUM_SECRETS" ]; then
    SECRETS_ARRAY=($FORUM_SECRETS)
    [[ "${SECRETS_ARRAY[@]}" == 3 ]]
    FORUM_ENDPOINT=${SECRETS_ARRAY[0]}
    FORUM_API_KEY=${SECRETS_ARRAY[1]}
    FORUM_API_USERNAME=${SECRETS_ARRAY[2]}
fi

[ -z "$FORUM_ENDPOINT" ] && echo '$FORUM_ENDPOINT is not set' && exit 1
[ -z "$FORUM_API_KEY" ] && echo '$FORUM_API_KEY is not set' && exit 2
[ -z "$FORUM_API_USERNAME" ] && echo '$FORUM_API_USERNAME is not set' && exit 3

post="$($SCRIPT_PATH/forum_post.sh)"

curl -X POST "$FORUM_ENDPOINT/posts.json" \
    -L -s -S \
    -H "Content-Type: multipart/form-data; charset=UTF-8" \
    -H "Api-Key: $FORUM_API_KEY" \
    -H "Api-Username: $FORUM_API_USERNAME" \
    -F "topic_id=34" \
    -F "raw=$post"

