{smcl}
{* *! version 0.0.1  13FEB2016}{...}
{cmd:help sysresources}
{hline}

{title:Title}

{p 4 4 4}{hi:sysresources {hline 2}} a program to return/access available system resources
such as memory, swap space, and CPU load in Stata.  Unlike shelling out and using system 
calls, this program returns results in scalars so they can be used by programmers/users 
afterwards.{p_end}

{title:Syntax}

{p 4 4 4}{cmd:sysresources} [, {cmdab:d:isplay}] {break}

{title:Description}

{p 4 4 4}{cmd:sysresouces} returns a series of available system resources.{p_end}

{p 4 4 4}{cmdab:d:isplay} is an option that prints the information retrieved by the sysresources 
command to the results pane.  {p_end}

{marker examples}{title:Examples}{break}
{p 4 4 8}Example without returning results to the results pane.{p_end} {break}
{p 8 8 12}{stata sysresources}{p_end}
{p 8 8 12}{stata ret li}{p_end}{break}
{col 12}{hline 60}
{col 12}{hi:Scalars} {col 30}{hi:Contents}
{col 12}{hline 60}
{col 12}{hi:r(pctfreemem) {col 27}= }{col 30}51.64885520935059
{col 12}{hi:r(procload) {col 27}= }{col 30}0
{col 12}{hi:r(cpuload) {col 27}= }{col 30}0
{col 12}{hi:r(totmem) {col 27}= }{col 30}17179869184
{col 12}{hi:r(freemem) {col 27}= }{col 30}8873205760
{col 12}{hi:r(proctime) {col 27}= }{col 30}305952000
{col 12}{hi:r(totalswap) {col 27}= }{col 30}0
{col 12}{hi:r(freeswap) {col 27}= }{col 30}0
{col 12}{hi:r(commitmem) {col 27}= }{col 30}5250781184
{col 12}{hline 60}{break}

{p 4 4 8}Example printing results to the results pane.{p_end} {break}
{p 8 8 12}{stata sysresources, d}{p_end}{break}
{p 12 12 12}{res}Total committed memory is : 4.9 GiB{p_end}
{p 12 12 12}{res}Free Swap Space is : 0 B{p_end}
{p 12 12 12}{res}Total Available Swap Space is : 0 B{p_end}
{p 12 12 12}{res}Process CPU Time is : 325204000{p_end}
{p 12 12 12}{res}Free Physical Memory Available is : 8.3 GiB{p_end}
{p 12 12 12}{res}Total Physical Memory Available is : 16.0 GiB{p_end}
{p 12 12 12}{res}System CPU Load is : 0.04186617556783303{p_end}
{p 12 12 12}{res}Process Load is : 2.2559178862587172E-4{p_end}
{p 12 12 12}{res}% Free Physical Memory is : 51.66430473327637{p_end}{text}

{marker return}{title:Returned Results}

{col 10}{hline 85}
{col 10}{hi:Scalars} {col 35}{hi:Contents}
{col 10}{hline 85}
{col 10}{hi:pctfreemem}{col 25}Percent free physical memory
{col 10}{hi:procload}{col 25}Current process CPU load
{col 10}{hi:cpuload}{col 25}System CPU load
{col 10}{hi:totmem}{col 25}Total physical memory available
{col 10}{hi:freemem}{col 25}Free physical memory available
{col 10}{hi:proctime}{col 25}Current process CPU time
{col 10}{hi:totalswap}{col 25}Total available swap space
{col 10}{hi:freeswap}{col 25}Free swap space 
{col 10}{hi:commitmem}{col 25}Committed memory
{col 10}{hline 85}{break}


{title: Author}{break}
{p 4 4 8}William R. Buchanan, Ph.D.{p_end}
{p 4 4 8}Data Scientist{p_end}
{p 4 4 8}{browse "http://mpls.k12.mn.us":Minneapolis Public Schools}{p_end}
{p 4 4 8}William.Buchanan at mpls [dot] k12 [dot] mn [dot] us{p_end}
