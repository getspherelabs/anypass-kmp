#!/bin/sh

. /Users/a1234/StudioProjects/anypass-kmp/cleanup.sh

cd iosApp || exit

pod deintegrate

pod install
