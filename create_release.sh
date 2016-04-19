#!/usr/bin/env bash

if [ "$#" -ne 2 ]
then
    echo "Please specify the release version number and the next snapshot for this release:"
    echo " ./create_release.sh 1.8 1.9-SNAPSHOT"
    exit
fi

# Exit when a command fails.
set -e

echo $1 > src/main/java/com/rolfje/anonimatron/version.txt
mvn versions:set -DnewVersion=$1
mvn clean assembly:assembly
mvn versions:commit

#git add pom.xml src/main/java/com/rolfje/anonimatron/version.txt
#git commit -m "Release $1"
#git tag "v$1"

echo $2 > src/main/java/com/rolfje/anonimatron/version.txt
mvn versions:set -DnewVersion=$2
mvn versions:commit

#git add pom.xml src/main/java/com/rolfje/anonimatron/version.txt
#git commit -m "Update version to $2"
