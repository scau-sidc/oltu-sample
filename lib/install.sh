#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

mvn install:install-file -Dfile="$DIR/nyafx-3.4.0.jar" -DgroupId=com.github.cuter44 -DartifactId=nyafx -Dversion=3.4.0 -Dpackaging=jar
#mvn install:install-file -Dfile="$DIR/wxpay-sdk-0.7.3-dev.jar" -DgroupId=com.github.cuter44 -DartifactId=wxpay-sdk -Dversion=0.7.3-dev -Dpackaging=jar