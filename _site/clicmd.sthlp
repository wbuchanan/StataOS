{smcl}
{* *! version 0.0.1  07mar2016}{...}
{cmd:help clicmd}
{hline}

{title:Title}

{p 4 4 4}{hi:clicmd {hline 2}} is a Java plugin used in a manner similar to
the {help shell} command.  The major difference is that {hi:cli} returns
the output from {it:stdout} in local macros and includes options that can be
used to parse/clean the returned results using regular expressions (which is
most useful when the CLI command would return a set of key-value pairs to
stdout).{p_end}

{p 2 2 2}{title:Syntax}{p_end}

{p 4 4 4}{cmd:clicmd} {it:command} [, {cmdab:d:isplay}
{cmdab:par:se[(}{it:string}{opt )]} {cmdab:cl:ean[(}{it:string}{opt )]}
{cmdab:grp:arse(}{it:numlist}{opt )} {cmdab:grc:lean(}{it:numlist}{opt )}]
{p_end}{break}

{marker options}{p 2 2 2}{title:Options}{p_end}
{p 4 4 8}{cmdab:d:isplay} is an option to print the returned results to
the command window. {p_end}

{p 4 4 8}{cmdab:par:se} is an optional argument that can be used either
to trigger default regular expressions used to parse {it:stdout} into key
value pairs.  You can also pass your own regular expression to the parameter
for parsing the returned results.  See {help clicmd##defaults:default settings}
for more information.{p_end}

{p 4 4 8}{cmdab:cl:ean} is an optional argument that can be used either
to trigger default regular expressions used to clean the elements parsed by
the regex passed to parse or to use a customized regular expression of your
definition.  See {help clicmd##defaults:default settings} for more information.{p_end}

{p 4 4 8}{cmdab:grp:arse} in the case of key value pairs, the values
passed to grparse should indicate the groups defined in the regular
expression from the parse option.  The first value passed to the numlist
will be used to extract the key and the second value passed to the
numlist will be used to extract the value.  See {help clicmd##defaults:default settings}
 for more information.{p_end}

{p 4 4 8}{cmdab:grc:lean} is used to select the groups that would be
returned from the regular expression used to clean the returned results.
The default values used by the Java plugin are used to select only valid
"word" characters from the parsed results.  See {help clicmd##defaults:default settings}
 for more information.{p_end}

{marker defaults}{p 2 2 2}{title:Default settings}{p_end}
{p 4 4 8} The Java plugin sets default values that can be triggered by using the
parse and/or clean options.  These are described below for convenience. {p_end}


     {hline 60}
{p2colset 8 30 30 8}{p2col:Parameter}Default{p_end}
     {hline 60}
{p2colset 8 30 30 8}{p2col:{cmdab:par:se}}((.*)(\s{1,}+)(.*)){p_end}
{p2colset 8 30 30 8}{p2col:{cmdab:cl:ean}}(^([\w _]{1,}+)(\W{1,}.*)$){p_end}
{p2colset 8 30 30 8}{p2col:{cmdab:grp:arse}}2 4{p_end}
{p2colset 8 30 30 8}{p2col:{cmdab:grc:lean}}2 4{p_end}
     {hline 60}

{marker examples}{p 2 2 2}{title:Examples}{p_end}
{p 4 4 8}Display Mach virtual memory statistics on *nix like systems{p_end}
{p 8 8 12}{stata clicmd vm_stat, d par cl:clicmd vm_stat, d par cl}{p_end}

{p 4 4 4}In the example above, it may not be quite so easy to determine the
meaning of the keys.  For convenience, the entries below have been copied
from the man page for the {it:vm_stat} *nix command.{p_end}

     {hline 100}
{p2colset 8 45 45 8}{p2col:Name}Meaning{p_end}
     {hline 100}
{p2colset 8 45 45 8}{p2col:pages_free}the total number of free pages in the system.{p_end}
{p2colset 8 45 45 8}{p2col:pages_active}the total number of pages currently in use and pageable{p_end}
{p2colset 8 45 45 8}{p2col:pages_inactive}the total number of pages on the inactive list{p_end}
{p2colset 8 45 45 8}{p2col:pages_speculative}the total number of pages on the speculative list{p_end}
{p2colset 8 45 45 8}{p2col:pages_throttled}the total number of pages on the throttled list (not wired but not pageable){p_end}
{p2colset 8 45 45 8}{p2col:pages_wired_down}the total number of pages wired down.  That is, pages that cannot be paged out{p_end}
{p2colset 8 45 45 8}{p2col:pages_purgeable}the total number of purgeable pages{p_end}
{p2colset 8 45 45 8}{p2col:translation_faults}the number of times the "vm_fault" routine has been called{p_end}
{p2colset 8 45 45 8}{p2col:pages_copy}the number of faults that caused a page to be copied (generally caused by copy-on-write faults){p_end}
{p2colset 8 45 45 8}{p2col:pages_zero_filled}the total number of pages that have been zero-filled on demand{p_end}
{p2colset 8 45 45 8}{p2col:pages_reactivated}the total number of pages that have been moved from the inactive list to the active list (reactivated){p_end}
{p2colset 8 45 45 8}{p2col:pages_purged}the total number of pages that have been purged{p_end}
{p2colset 8 45 45 8}{p2col:file}the total number of pages that are file-backed (non-swap){p_end}
{p2colset 8 45 45 8}{p2col:anonymous_pages}the total number of pages that are anonymous{p_end}
{p2colset 8 45 45 8}{p2col:pages_stored_in_compressor*}the total number of pages (uncompressed) held within the compressor{p_end}
{p2colset 8 45 45 8}{p2col:pages_occupied_by_compressor*}the number of pages used to store compressed VM pages{p_end}
{p2colset 8 45 45 8}{p2col:decompressions*}the total number of pages that have been decompressed by the VM compressor{p_end}
{p2colset 8 45 45 8}{p2col:compressions*}the total number of pages that have been compressed by the VM compressor{p_end}
{p2colset 8 45 45 8}{p2col:pageins}the total number of requests for pages from a pager (such as the inode pager){p_end}
{p2colset 8 45 45 8}{p2col:pageouts}the total number of pages that have been paged out{p_end}
{p2colset 8 45 45 8}{p2col:swapins}the total number of compressed pages that have been swapped out to disk{p_end}
{p2colset 8 45 45 8}{p2col:swapouts}the total number of compressed pages that have been swapped back in from disk{p_end}
     {hline 100}

{p 4 4 8}{it: * these entries are my best guess as to the match in the man page}{p_end}

{marker author}{p 2 2 2}{title: Author}{p_end}{break}
{p 4 4 8}William R. Buchanan, Ph.D.{p_end}
{p 4 4 8}Data Scientist{p_end}
{p 4 4 8}{browse "http://mpls.k12.mn.us":Minneapolis Public Schools}{p_end}
{p 4 4 8}William.Buchanan at mpls [dot] k12 [dot] mn [dot] us{p_end}
