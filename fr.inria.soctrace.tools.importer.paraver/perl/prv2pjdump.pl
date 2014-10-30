  my $input;
  my $output;
  my $format;

  use strict;
  use Data::Dumper;

  my $power_reference=286.087E-3; # in flop/mus

  sub main {
      # default values for $input, $output and $format may have be
      # defined when tangling from babel but command line arguments
      # should always override them.
      my($arg);

      while(defined($arg=shift(@ARGV))) {
          for ($arg) {
              if (/^-i$/) { $input = shift(@ARGV); last; }
              if (/^-o$/) { $output = shift(@ARGV); last; }
              if (/^-f$/) { $format = shift(@ARGV); last; }
              print "unrecognized argument '$arg'";
          }
      }

      if(!defined($input) || $input eq "") { die "No valid input file provided.\n"; }
      if(!defined($output) || $output eq "") { die "No valid input file provided.\n"; }
      
      print "Input: '$input'\n";
      print "Output: '$output'\n";
      print "Format: '$format'\n";

      my($state_name,$event_name) = parse_pcf($input.".pcf");
      my($resource_name) = parse_row($input.".row");
      convert_prv($input.".prv",$state_name,$event_name,$resource_name,$output,$format);
  }

  sub parse_row {
      my($row) = shift;
      my $line;
      my(%resource_name);

      open(INPUT,$row) or die "Cannot open $row. $!";
      while(defined($line=<INPUT>)) {
          chomp $line;
          if($line =~ /^LEVEL (.*) SIZE/) {
              my $type = $1;
              $resource_name{$type}= [];
              while((defined($line=<INPUT>)) &&
                    !($line =~ /^\s*$/g)) {
                  chomp $line;
                  push @{$resource_name{$type}}, $line;
              }
          }
      }

      return (\%resource_name);
  }

  sub parse_pcf {
      my($pcf) = shift;
      my $line;
      my(%state_name, %event_name) ;
      open(INPUT,$pcf) or die "Cannot open $pcf. $!";
      while(defined($line=<INPUT>)) {
          chomp $line;
          if($line =~ /^STATES$/) {
              while((defined($line=<INPUT>)) &&
                    ($line =~ /^(\d+)\s+(.*)/g)) {
                  $state_name{$1} = $2;
              }
          }
          if($line =~ /^EVENT_TYPE$/) {
              while($line=<INPUT>) {
                  if($line =~ /VALUES/g) {last;}
                  $line =~ /[6|9]\s+(\d+)\s+(.*)/g or next; #E.g. , EVENT_TYPE\n 1    50100001    Send Size in MPI Global OP
                  my($id)=$1;
                  $event_name{$id}{type} = $2;
              }
              while((defined($line=<INPUT>)) &&
                    ($line =~ /^(\d+)\s+(.*)/g)) {
                  my($id);
                  foreach $id (keys %event_name) {
                      $event_name{$id}{value}{$1} = $2;
                  }
              }
          }
      }
      # print Dumper(\%state_name);
      # print Dumper(\%event_name);
      return (\%state_name,\%event_name);
  }

  my(%pcf_coll_arg) = (
      "send" => "50100001",
      "recv" => "50100002",
      "root" => "50100003",
      "communicator" => "50100003"
  );

  my(%tit_translate) = (
      "Running" => "compute",
      "Not created" => "", # skip me
      "I/O" => "",         # skip me
      "Synchronization" => "", # skip me
      "MPI_Comm_size" => "",   # skip me
      "MPI_Comm_rank" => "",   # skip me
      "Outside MPI" => "",     # skip me
      "End" => "",             # skip me
      "MPI_Init" => "init",
      "MPI_Bcast" => "bcast",
      "MPI_Allreduce" => "allReduce",
      "MPI_Alltoallv" => "allToAllV",
      "MPI_Alltoall" => "allToAll",
      "MPI_Reduce" => "reduce",
      "MPI_Allgatherv" => "allGatherV",
      "MPI_Gather" => "gather",
      "MPI_Gatherv" => "gatherV",
      "MPI_Reduce_scatter" => "reduceScatter",
      "MPI_Finalize" => "finalize",
      "MPI_Barrier" => "barrier",
   );

  sub convert_prv {
      my($prv,$state_name,$event_name,$resource_name,$output,$format) = @_;
      my $line;
      my (%event);
      my $nb_proc = 1;
      my(@fh)=();

      for ($format) {
          if (/^csv$/) { 
              $output .= ".csv";
              open(OUTPUT,"> $output") or die "Cannot open $output. $!"; 
              last; 
          } 
          if (/^pjdump$/) { 
              $output .= ".pjdump";
              open(OUTPUT,"> $output"); 
              my @tab = split(/:/,`tail -n 1 $prv`);
              my $max_duration = $tab[5];
              print OUTPUT "Container, 0, 0, 0.0, $max_duration, $max_duration, 0\n";
              $nb_proc = 1;
              my $node;
              foreach $node (@{$$resource_name{NODE}}) { 
                  print OUTPUT "Container, 0, N, 0.0, $max_duration, $max_duration, $node\n";
                  print OUTPUT "Container, $node, T, 0.0, $max_duration, $max_duration, MPI Rank $nb_proc\n";
                  $nb_proc++;
              }
              last;
          }
          if(/^tit$/) {
              $nb_proc = 0;
              foreach my $node (@{$$resource_name{NODE}}) { 
                  my $filename = $output."_$nb_proc.tit";
                  open($fh[$nb_proc], "> $filename") or die "Cannot open > $filename: $!";
                  $nb_proc++;
              }
              last;
          }
          die "Invalid format '$format'\n";
      }
      
      open(INPUT,$prv) or die "Failed to open $prv:$!\n";

      # We should read something like:
      # #Paraver (dd/mm/yy at hh:m):ftime:0:nAppl:applicationList[:applicationList]
      # but we ignore this information for the moment

      while(defined($line=<INPUT>)) {
          chomp($line);
          # State records 1:cpu:appl:task:thread : begin_time:end_time : state
          if($line =~ /^1/) {
              my($sname);
              my($sname_param);
              my($record,$cpu,$appli,$task,$thread,$begin_time,$end_time,$state) =
                  split(/:/,$line);
              if($$state_name{$state} =~ /Group/ || $$state_name{$state} =~ /Others/ ) {
                  $line=<INPUT>;
                  chomp $line;
                  my($event,$ecpu,$eappli,$etask,$ethread,$etime,%event_list) =
                      split(/:/,$line);
                  (($event==2) && ($ecpu eq $cpu) && ($eappli eq $appli) && 
                   ($etask eq $task) && ($ethread eq $thread) &&
                   ($etime >= $begin_time) && ($etime <= $end_time)) or
                   die "Invalid event!";

                  if($$state_name{$state} =~ /Group/) {
                      $sname = $$event_name{50000002}{value}{$event_list{50000002}};
                      my $t;
                      foreach $t ("send","recv","root") {
                          if(defined($event_list{$pcf_coll_arg{$t}}) &&
                             $event_list{$pcf_coll_arg{$t}} ne "0") {
                              $sname_param.= "$event_list{$pcf_coll_arg{$t}} ";
                          }
                      }
                  } else {
                      $sname = $$event_name{50000003}{value}{$event_list{50000003}};
                  }
              } else {
                  $sname = $$state_name{$state};
              }

              if($sname eq "Running") { $sname_param.= (($end_time-$begin_time)*$power_reference); }

              if($format eq "csv") {
                  print OUTPUT "State, $task, MPI_STATE, $begin_time, $end_time, ".
                      ($end_time-$begin_time).", 0, ".
                      $sname."\n";
              } 
              if($format eq "pjdump") {
                  print OUTPUT "State, MPI Rank $task, STATE, $begin_time, $end_time, ".
                      ($end_time-$begin_time).", 0, ".
                      $sname."\n";
              }
              if($format eq "tit") {
                  $task=$task-1;                  
                  defined($tit_translate{$sname}) or die "Unknown state '$sname' for tit\n";
                  if($tit_translate{$sname} ne "") {
                      print { $fh[$task] } "$task $tit_translate{$sname} $sname_param\n",
                  }
              }
          } elsif ($line =~ /^2/) {
            # Event records 2:cpu:appl:task:thread : time : event_type:event_value
            my($event,$cpu,$appli,$task,$thread,$time,%event_list) =
                    split(/:/,$line);
            my($sname);
            my($sname_param);

            if(defined($event_list{50000002})) { # collective operation
                $sname = $$event_name{50000002}{value}{$event_list{50000002}};
                my $t;
                foreach $t ("send","recv","root") {
                    if(defined($event_list{$pcf_coll_arg{$t}}) &&
                       $event_list{$pcf_coll_arg{$t}} ne "0") {
                        $sname_param.= "$event_list{$pcf_coll_arg{$t}} ";
                    }
                }
            } elsif(defined($event_list{50000003})) { # MPI other
                $sname = $$event_name{50000003}{value}{$event_list{50000003}};
            } else { # This is application of trace flushing event
                     # and hardware counter, user function, ...
                my($warn)=1;
                for (40000018,40000003,40000001,
                     42009999,42001003,42001010,42001015,300,
                     70000001,70000002,70000003,80000001,80000002,80000003, 
                     45000000) {
                  if(defined($event_list{$_})) {$warn=0; last;}
                }
                if($warn) { print "Skipping event $line\n"; }
                next;
            }

            if($format eq "tit") {
                $task=$task-1;                  
                defined($tit_translate{$sname}) or die "Unknown state '$sname' for tit:\n\t$line\n";
                if($tit_translate{$sname} ne "") {
                    print { $fh[$task] } "$task $tit_translate{$sname} $sname_param\n",
                }
            }
          } elsif($line =~ /^3/) { 
              # Communication records 3: cpu_send:ptask_send:task_send:thread_send : logical_time_send: actual_time_send: cpu_recv:ptask_recv:task_recv:thread_recv : logical_time_recv: actual_time_recv: size: tag
              print STDERR "Skipping this communication event\n";
          }
          if($line =~ /^c/) {
              # Communicator record c: app_id: communicator_id: number_of_process : thread_list (e.g., 1:2:3:4:5:6:7:8)
              print STDERR "Skipping communicator definition\n";
          }
      }

      for ($format) {
          if (/^csv$/) { 
              close(OUTPUT); print "Generated [[file:$output]]\n";
              last; 
          }
          if (/^pjdump$/) { 
              close(OUTPUT); print "Generated [[file:$output]]\n";
              last; 
          }
          if(/^tit$/) {
              foreach my $r (0..$nb_proc-1) {
                  close($fh[$r]) or die "Failed closing fh[$r]. $!\n";
              }
              print "Generated [[file:${output}_0.tit]] among other ones\n";
              last;
          }
          die "Invalid format '$format'\n";
      }
  }

  main();
