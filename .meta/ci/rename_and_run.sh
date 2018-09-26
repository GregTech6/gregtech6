#!/bin/sh

mv "$1" "$2"

shift 2

"${@}"

