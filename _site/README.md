# Class to access operating system data
Based on response to question posed on the [StataList](http://www.statalist.org/forums/forum/general-stata-discussion/general/1326359-finding-out-available-memory-in-a-unix-system).

Here is an example of the output that could be provided by the class:

```
Total committed memory is : 7.9 GiB
Free Swap Space is : 0 B
Total Available Swap Space is : 0 B
Process CPU Time is : 812383000
Free Physical Memory Available is : 4.1 GiB
Total Physical Memory Available is : 16.0 GiB
System CPU Load is : 0.0
Process Load is : 0.0
% Free Physical Memory is : 0
```


# Command line processing 
The program now also can return values from the shell in local macro's.  This was also in response to the thread above and was the easiest solution for providing more robust access to system resource information (e.g., size of cached memory).  With no user intervention, the command will attempt to parse the lines of `stdout` into key value pairs and also returns the raw output in case users wanted to process the data differently and/or in Stata.  It will also return the regular expressions used to both parse the input into the key/value pairs, as well as the regular expression used to further munge the key/value elements.  

```
. sysresources vm_stat

. ret li

macros:
       r(Pages_purged) : "1853972"
  r(Mach_Virtual_Memo
    ry_Statistics)     : "bytes"
               r(File) : "202582"
            r(Swapins) : "0"
    r(Pages_purgeable) : "284216"
  r(Pages_occupied_by
    _compressor)       : "867800"
  r(Pages_stored_in_c
    ompressor)         : "1619871"
       r(Pages_active) : "1340421"
           r(Pageouts) : "753"
           r(Swapouts) : "0"
       r(Compressions) : "7624001"
  r(Pages_reactivated) : "1366631"
         r(Pages_copy) : "11097418"
   r(Pages_wired_down) : "504969"
            r(Pageins) : "4262570"
    r(Anonymous_pages) : "1276410"
     r(Pages_inactive) : "56581"
  r(Pages_zero_filled) : "119304436"
         r(Pages_free) : "1340925"
    r(Pages_throttled) : "0"
     r(Decompressions) : "4826559"
  r(Pages_speculative) : "81990"
                r(raw) : "Mach Virtual Memory Statistics: (page size of 4096 bytes)Pages free:                             1340925.Pages active:                       .."
           r(clgroups) : "2, 4"
            r(pgroups) : "2, 4"
            r(cleaner) : "(^([\w _]{1,}+)(\W{1,}.*)$)"
             r(parser) : "((.*)(\s{1,}+)(.*))"
```

The major difference is that the end user is required to know which shell commands are needed to return the information of interest from the operating system.

# Additional Info
Users can also use this with more sophisticated command line calls, but are responsible for knowing the location and options needed to start a terminal process:

```
. clicmd "/bin/sh -c ps -x | grep StataMP.app", d
-------------------------------------------------------------------------------------
       Key                                       Value
-------------------------------------------------------------------------------------
       parser                                    .*
       cleaner                                   .*
       pgroups                                   0, 0
       clgroups                                  0, 0
       exec                                      /bin/sh -c ps -x | grep StataMP.app
       line3                                      7056 ttys000 0:00.36 stata-mp
       line2                                      6869 ttys000 0:00.08 /bin/bash --login
       line1                                      PID TTY TIME CMD
-------------------------------------------------------------------------------------
```

Notice in the case above that the command is wrapped in regular quotation marks.  This allows the entire command string to be passed to the Java plugin as a single element of a String array object created by the `args()` optional parameter of the `javacall` command.