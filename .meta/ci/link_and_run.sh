#!/bin/sh

ln -s "$1" "$2"

shift 2

"${@}"

