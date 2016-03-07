
*! cli
*! v 0.0.1
*! 07mar2016


cap prog drop clicmd

prog def clicmd, rclass

	version 13

	syntax anything(name=clicmd id="CLI Command to Execute") [, Display 	 ///
	PARse PARse2(string asis) CLean CLean2(string asis)						 ///
	GRParse(numlist min=2 max=2) GRClean(numlist min=2 max=2) ]

	if mi(`"`grparse'"') == 1 loc grparse `""""'
	else loc grparse `: subinstr loc grparse " " ",", all'

	if mi(`"`grclean'"') == 1 loc grclean `""""'
	else loc grclean `: subinstr loc grclean " " ",", all'

	if `"`parse2'"' == "" loc parsestr `""""'
	if `"`clean2'"' == "" loc cleanstr `""""'
	if mi(`"`parse'"') == 1 loc parse false
	else loc parse true
	if mi(`"`clean'"') == 1 loc clean false
	else loc clean true

	javacall org.paces.Stata.SysResources cliOutput, args(`clicmd' `parse'  ///
	`clean' `grparse' `grclean' `parsestr' `cleanstr')

	ret clear

	foreach v of loc retnames {
		ret loc `v' ``v''
	}

	if `"`display'"' != "" {

		di "{hline 85}" _n "{p2colset 8 50 50 8}{p2col:Key}Value{p_end}"	 ///
		"{hline 85}"

		loc b raw

		loc retvals : list retnames - b

		foreach v of loc retvals {

			di "{p2colset 8 50 50 8}{p2col:`v'}``v''{p_end}"

		}

		di "{hline 85}"

	}

end

