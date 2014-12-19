#!/bin/bash
                                                                                        
#####################################################################
# Upload the importers update site.
# 
# This is simply a convenience shortcut for the upload-site.sh script.
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

SCRIPT="../../../soctrace-inria.github.io/updatesite/upload-site.sh"
REPO="../fr.inria.soctrace.importers.maven.repository/target/repository/"
PROJECT="importers"
$SCRIPT $REPO $PROJECT
