#!/bin/bash

shopt -s dotglob

mv "$2"/* "$1"/

shift 2

"${@}"

