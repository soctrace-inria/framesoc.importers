#!/bin/bash
# Script for producing  from .org files

# Read parameters
all=0
simple=0
links=0
help_script()
{
    cat << EOF
Usage: $0 options

First parameter is input file, second parameter is output file, third parameter is the path to pj_dump.

OPTIONS:
   -h      Show this message
   -a      Keep whole trace not only State
   -s      For using simple trace files as input (not .org)   
   -l      Export links as well
EOF
}
# Parsing options
while getopts "aslh" opt; do
    case $opt in
	a)
	    all=1
	    ;;
	h)
	    help_script
	    exit 4
	    ;;
        s)
	    simple=1
	    ;;
        l)
	    links=1
	    ;;
	\?)
	    echo "Invalid option: -$OPTARG"
	    help_script
	    exit 3
	    ;;
    esac
done
shift $((OPTIND - 1))
inputfile=$1
outputfile=$2
pjdump_path=$3
if [[ $# != 3 ]]; then
    echo 'ERROR!'
    help_script
    exit 2
fi

# Remove previous files if necessary
rm -rf tmp.trace
rm -rf $outputfile.trace

# Get the trace from .org file
if [[ $simple == 0 ]]; then
    sed -n '/* PAJE TRACE:/,/####/{/####/!p}' $inputfile >> tmp.trace
    tail -n +2 tmp.trace > $outputfile.trace
else
    cp $inputfile $outputfile.trace
fi

# Fixing formatof .trace file
sed -i -e 's/SourceContainer/StartContainer/' -e 's/DestContainer/EndContainer/' -e 's/[\t]ContainerType/\tType/' -e 's/EntityType/Type/'  $outputfile.trace

# Divide trace in preambule and the real trace
grep -e '^\(\(%\)\|\(\(1\|2\|3\|4\|5\|6\|7\|9\)\>\)\)' $outputfile.trace > start.trace
grep -e '^\(\(%\)\|\(\(1\|2\|3\|4\|5\|6\|7\|9\)\>\)\)' -v  $outputfile.trace > end.trace

# Deleting links if necessary
if [[ $links == 0 ]]; then
    sed "/^6/d" end.trace | sed '/^13/d' | sed '/^18/d' | sed '/^19/d' > outputDel.trace
else 
    cp end.trace outputDel.trace
fi

# Sorting, merging and dumping trace
sort -s -V --key=2,2 outputDel.trace > endSorted.trace
cat start.trace endSorted.trace > outputSorted.trace
$pjdump_path -u -n outputSorted.trace > $outputfile

# Sometimes only interested in States not other fields
if [[ $all == 0 ]]; then
    perl -i -ne 'print if /^State/' $outputfile
   # Adding a header by hand, so it is easier to load the file in R
    sed -i '1s/^/Nature, ResourceId, Type, Start, End, Duration, Depth, Value, Footprint , JobId , Params, Size, Tag\n/' $outputfile
fi

# Delete temporary files
rm outputSorted.trace
rm outputDel.trace
rm start.trace
rm end.trace
rm endSorted.trace

# Remove temporary files
rm -rf tmp.trace
rm -rf $outputfile.trace
