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
      "communicator" => "50100003",
      "compute" => "my_reduce_compute_amount",
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
      "MPI_Allgatherv" => "", # allGatherV Uggly hack :)
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
      my(@fh)=();

      open(INPUT,$prv) or die "Failed to open $prv:$!\n";


      # Start parsing the header to get the trace hierarchy. 
      # We should get something like
      # #Paraver (dd/mm/yy at hh:m):ftime:0:nAppl:applicationList[:applicationList]

      $line=<INPUT>; chomp $line;
      $line=~/^\#Paraver / or die "Invalid header '$line'\n";
      my $header=$line;
      $header =~ s/^[^:\(]*\([^\)]*\):// or die "Invalid header '$line'\n";
      $header =~ s/(\d+):(\d+)([^\(])/$1\_$2$3/g;
      $header =~ s/,\d+$//g;
      my ($max_duration,$resource,$nb_app,@appl) = split(/:/,$header);
      $max_duration =~ s/_.*$//g;
      $resource =~ /^(.*)\((.*)\)$/ or die "Invalid resource description '$resource'\n";
      my($nb_nodes,$cpu_list)= ($1,$2);

      $nb_app==1 or die "I can handle only one application type at the moment\n";

      my @cpu_list=split(/,/,$cpu_list);

      print("$max_duration --> '$nb_nodes' '@cpu_list'    $nb_app  @appl \n");
      my(%Appl);
      my($nb_task);
      foreach my $app (1..$nb_app) {
          my($task_list);
          $appl[$app-1] =~ /^(.*)\((.*)\)$/ or die "Invalid resource description '$resource'\n";
          ($nb_task,$task_list) = ($1,$2);
          print $appl[$app-1]."\n";
          print "\t '$nb_task' '$task_list'\n";

          my(@task_list) = split(/,/,$task_list);


          my(%mapping);
          my($task);
          foreach $task (1..$nb_task) {
              my($nb_thread,$node_id) = split(/_/,$task_list[$task-1]);
              if(!defined($mapping{$node_id})) { $mapping{$node_id}=[]; }
              push @{$mapping{$node_id}},[$task,$nb_thread];
          }
          $Appl{$app}{nb_task}=$nb_task;
          $Appl{$app}{mapping}=\%mapping;
      }

      print(Dumper(%Appl));
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
              print OUTPUT "Container, 0, 0, 0.0, $max_duration, $max_duration, 0\n";
              foreach my $node (1..$nb_nodes) {
                  print OUTPUT "Container, 0, N, 0.0, $max_duration, $max_duration, node_$node\n";
              }
              foreach my $app (values(%Appl)) {
                  print Dumper($app);
                  
                  foreach my $node (keys%{$$app{mapping}}) {
                      print "$node\n";
                      foreach my $t (@{$$app{mapping}{$node}}) {
                          print OUTPUT "Container, node_$node, P, 0.0, $max_duration, $max_duration, MPI_Rank_$$t[0]\n";
                          foreach my $thread (1..$$t[1]) {
                              print OUTPUT "Container, MPI_Rank_$$t[0], T, 0.0, $max_duration, $max_duration, Thread_$$t[0]_$thread\n";
                          }
                      }
                  }
              }
              last;
          }
          if(/^tit$/) {
              my $nb_proc = 0;
              foreach my $node (@{$$resource_name{NODE}}) { 
                  my $filename = $output."_$nb_proc.tit";
                  open($fh[$nb_proc], "> $filename") or die "Cannot open > $filename: $!";
                  $nb_proc++;
              }
              last;
          }
          die "Invalid format '$format'\n";
      }
      

      # Now, let's process the records 

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
                      if($tit_translate{$sname} =~ /V$/) { # Really Uggly hack because of "poor" tracing of V operations
                          print "WTF!!!! $line \n";
                          $event_list{$pcf_coll_arg{"send"}} = 100000;
                          $event_list{$pcf_coll_arg{"recv"}} = 100000;
                          $tit_translate{$sname} =~ s/V$//;
                      }

                      if($tit_translate{$sname} eq "reduce") { # Uggly hack because the amount of computation is not given
                          $event_list{$pcf_coll_arg{"compute"}} = 1;
                      }
                      if($tit_translate{$sname} eq "gather") { # Uggly hack because the amount of receive does not make sense here
                          $event_list{$pcf_coll_arg{"recv"}} = $event_list{$pcf_coll_arg{"send"}};
                          $event_list{$pcf_coll_arg{"root"}} = 1; # Uggly hack. AAAAARGH
                      }
                      if($tit_translate{$sname} eq "reduceScatter") { # Uggly hack because of "poor" tracing
                          $event_list{$pcf_coll_arg{"recv"}} = $event_list{$pcf_coll_arg{"send"}}; 
                          my $foo=$event_list{$pcf_coll_arg{"recv"}};
                          $event_list{$pcf_coll_arg{"recv"}}="";
                          for (1..$nb_task) { $event_list{$pcf_coll_arg{"recv"}} .= $foo." "; }
                          $event_list{$pcf_coll_arg{"compute"}} = 1;
                      }

                      foreach $t ("send","recv", "compute", "root") {
                          if(defined($event_list{$pcf_coll_arg{$t}}) &&
                             $event_list{$pcf_coll_arg{$t}} ne "0") {
                              if($t eq "root") { $event_list{$pcf_coll_arg{$t}}--; }
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
                  print OUTPUT "State, Thread_${task}_$thread, STATE, $begin_time, $end_time, ".
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
                        if($t eq "root") { $event_list{$pcf_coll_arg{$t}}--; }
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
              foreach my $f (@fh) {
                  close($f) or die "Failed closing file descriptor. $!\n";
              }
              print "Generated [[file:${output}_0.tit]] among other ones\n";
              last;
          }
          die "Invalid format '$format'\n";
      }
  }

  main();
