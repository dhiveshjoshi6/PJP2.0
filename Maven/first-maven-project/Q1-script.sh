#!/bin/bash
echo $PWD
echo "================================================"
echo "++++++++++++++ SHOWING MVN GOALS +++++++++++++++"
echo "================================================"
mvn help:describe -Dcmd=install