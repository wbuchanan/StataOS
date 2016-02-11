---
layout: page
title: about
permalink: /about/
---

Provides access to system resources from within Stata.  No helpfile yet, but 
the examples below illustrate all of the functionality.


# Example 1.  Getting the system resource data

```
. sysresources

. ret li

scalars:
         r(pctfreemem) =  51.64885520935059
           r(procload) =  0
            r(cpuload) =  0
             r(totmem) =  17179869184
            r(freemem) =  8873205760
           r(proctime) =  305952000
          r(totalswap) =  0
           r(freeswap) =  0
          r(commitmem) =  5250781184
```

## Example 2.  Getting and displaying the system resource data

```
. sysresources, d
Total committed memory is : 4.9 GiB
Free Swap Space is : 0 B
Total Available Swap Space is : 0 B
Process CPU Time is : 325204000
Free Physical Memory Available is : 8.3 GiB
Total Physical Memory Available is : 16.0 GiB
System CPU Load is : 0.04186617556783303
Process Load is : 2.2559178862587172E-4
% Free Physical Memory is : 51.66430473327637
```


