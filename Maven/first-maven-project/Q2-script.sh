#!/bin/sh
echo "================================================"
echo "++++++++++++++ LOCAL REPOSITORY ++++++++++++++++"
echo "================================================"

output=$(mvn help:evaluate -Dexpression=settings.localRepository -q -DforceStdout)
echo $output
cd $output
ls



