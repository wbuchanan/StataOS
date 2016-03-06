
*! sysresources
*! v 0.0.1
*! 11feb2016

// Drop program from memory if it exists
cap prog drop sysresources

// Define the program with r-class properties
prog def sysresources, rclass

    // Program requires Stata 13 to execute (nothing is different between the
    // APIs from what I remember)
    version 13

    // Defines the syntax for the program
    syntax [anything(name=clicmd id="Command Line Command to Execute")]		 ///
    [, Display PARse(string asis) CLean(string asis)						 ///
    GRParse(numlist min=2 max=2) GRClean(numlist min=2 max=2) NUMIFpossible ]

	if `"`clicmd'"' == "" {

		// Call the java method
		javacall org.paces.Stata.SysResources compResources, args(`display')

		// Clear existing returned results
		ret clear

		// Set the returned scalar values
		ret sca commitmem = commitmem
		ret sca freeswap = freeswap
		ret sca totalswap = totalswap
		ret sca proctime = proctime
		ret sca freemem = freemem
		ret sca totmem = totmem
		ret sca cpuload = cpuload
		ret sca procload = procload
		ret sca pctfreemem = pctfreemem

	}

	else {

		if mi(`"`grparse'"') == 1 loc grparse `""""'
		else loc grparse `: subinstr loc grparse " " ",", all'

		if mi(`"`grclean'"') == 1 loc grclean `""""'
		else loc grclean `: subinstr loc grclean " " ",", all'

		if mi(`"`parse'"') == 1 loc parse `""""'
		if mi(`"`clean'"') == 1 loc clean `""""'
		if mi(`"`numifpossible'"') == 1 loc numifpossible false
		else loc numifpossible true

		javacall org.paces.Stata.SysResources cliOutput, args(`clicmd' `parse'  ///
		`clean' `grparse' `grclean' `numifpossible')

		foreach v of loc retnames {
			ret loc `v' ``v''
		}

	}

// End of program
end

