#!/bin/bash
                                                                                        
#####################################################################
# Change the version number to the importers project.
# 
# This is simply a convenience shortcut for the change_version.sh
# script.
#
# IMPORTANT
#
# It works having the framesoc.importers and the 
# soctrace-inria.github.io clones in the same root directory:
# ./somedir/framesoc.importers
# ./somedir/soctrace-inria.github.io
#
# Author: Generoso Pagano
#####################################################################

SCRIPT="../../soctrace-inria.github.io/updatesite/change_version.sh"
MASTER="."
FEATURE="../fr.inria.soctrace.features.importers/feature.xml"
CATEGORY="../fr.inria.soctrace.importers.maven.repository/category.xml"

# parameter check is done in the change_version.sh script
NEW=$1
$SCRIPT $MASTER $FEATURE $CATEGORY $NEW

