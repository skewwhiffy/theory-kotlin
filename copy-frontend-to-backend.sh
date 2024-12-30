#!/usr/bin/env sh

backend_source="./backend/src/main/resources/public"
rm -rf "$backend_source"
mkdir "$backend_source"
cp -r ./web/build/* "$backend_source"
