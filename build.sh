#!/usr/bin/env sh

cd ./web && yarn build
cd ..

backend_source="./backend/src/main/resources/public"
rm -rf "$backend_source"
mkdir "$backend_source"
cp -r ./web/build/* "$backend_source"
