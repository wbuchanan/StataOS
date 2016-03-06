package org.paces.Stata;

import com.stata.sfi.*;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Method used to provide access to more OS resources.  Used to call command
 * from the shell and parse/clean/return the results to Stata.
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class CLIout {

	private static final Integer[] DEFAULT_INT_ARRAY = new Integer[]{2, 4};
	private static final Pattern DEFAULT_PARSER = Pattern.compile("((.*)(\\s{1,}+)(.*))");
	private static final Pattern DEFAULT_CLEANER = Pattern.compile("(^([\\w _]{1,}+)(\\W{1,}.*)$)");
	private static final List<Integer> DEFAULT_PARSER_IDS = Arrays.asList(DEFAULT_INT_ARRAY);
	private static final List<Integer> DEFAULT_CLEANER_IDS = Arrays.asList(DEFAULT_INT_ARRAY);
	private Pattern parser;
	private Pattern cleaner;
	private List<String> rawResults = new ArrayList<String>();
	private Map<String, String> parsedResults = new HashMap<String, String>();
	private List<Integer> keyValueIDs = new ArrayList<Integer>();
	private List<Integer> cleanerIDs = new ArrayList<Integer>();


	/**
	 * Class constructor
	 * @param args Passed from the args option of Java call
	 */
	public CLIout(String[] args) {
		String cmd = args[0];
		if (!args[1].isEmpty()) this.parser = Pattern.compile(args[1]);
		else this.parser = DEFAULT_PARSER;
		if (!args[2].isEmpty()) this.cleaner = Pattern.compile(args[2]);
		else this.cleaner = DEFAULT_CLEANER;
		if (args[3].isEmpty()) this.keyValueIDs = DEFAULT_PARSER_IDS;
		else this.keyValueIDs = parseGroups(args[3]);
		if (args[4].isEmpty()) this.cleanerIDs = DEFAULT_CLEANER_IDS;
		else this.cleanerIDs = parseGroups(args[4]);
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
			cleanResults(processCommand(proc), this.keyValueIDs, this.cleanerIDs);
		} catch (IOException e){
			e.printStackTrace();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Method used to clean the parsed result set
	 * @param cmdResult The list of strings containing the lines of stdout
	 *                     from the cli command
	 * @param kv The list of integers used to extract the key/value pairs
	 * @param cln The list of integers used to extract clean key and value
	 *               elements
	 */
	private void cleanResults(List<String> cmdResult, List<Integer> kv, List<Integer> cln) {
		for(String i : cmdResult) {
			Matcher line = this.parser.matcher(i);
			if (line.find()) {
				String k = line.group(kv.get(0));
				String v = line.group(kv.get(1));
				Matcher mk = this.cleaner.matcher(k);
				Matcher mv = this.cleaner.matcher(v);
				if (mk.find() && mv.find()) this.parsedResults.put(mk.group(cln.get(0)), mv.group(cln.get(0)));
			}
		}
	}

	/**
	 * Method to get the keys from the parsed and cleaned results set
	 * @return A set object with the unique strings identifying elements in
	 * the Map object containing the results set
	 */
	public Set<String> getParsedKeys() {
		return this.parsedResults.keySet();
	}

	/**
	 * Method used to parse a string value for group IDs into a list of integers
	 * @param groupIds The string containing the comma delimited string
	 *                    literal integers to use for group extraction
	 * @return A list of integer values
	 */
	private List<Integer> parseGroups(String groupIds) {
		List<String> vals = Arrays.asList(groupIds.split(","));
		List<Integer> retval = new ArrayList<Integer>();
		if (vals.size() == 2) {
			for (String i : vals) {
				if (i.matches("\\d")) retval.add(Integer.valueOf(i));
			}
			return retval;
		} else {
			return DEFAULT_PARSER_IDS;
		}
	}

	/**
	 * Processes the command line interface command
	 * @param proc A process object representing the call out to the system's
	 *                shell
	 * @return A list of Strings read from stdout of the command
	 */
	private List<String> processCommand(Process proc) {
		BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
		List<String> resultString = new ArrayList<String>();
		while (hasLine(br)) {
			try {
				resultString.add(br.readLine());
				this.rawResults = resultString;
			} catch (IOException e) {
			}
		}
		return resultString;
	}

	private Boolean hasLine(BufferedReader br) {
		try {
			br.mark(120);
			Boolean retval = br.readLine() != null;
			br.reset();
			return retval;
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Method to access the regular expression used to parse the results
	 * @return The regex string used for parsing the results
	 */
	public String getParserPattern() {
		return this.parser.pattern();
	}

	/**
	 * Method to access the regular expression used to clean the results set
	 * @return The regex string used for cleaning the parsed results
	 */
	public String getCleanerPattern() {
		return this.cleaner.pattern();
	}


	/**
	 * Method used to get the string representation of the raw results
	 * @return Returns the raw result string
	 */
	public String getRawResults() {
		StringBuilder s = new StringBuilder();
		for (String i : this.rawResults) {
			s.append(i);
		}
		return s.toString();
	}

	/**
	 * Method to get the group ID values used to parse the result strings
	 * @return A String containing the group IDs used for parsing
	 */
	public String getParserGroups() {
		return String.valueOf(keyValueIDs).replaceAll("(\\[)|(])", "");
	}

	/**
	 * Method to get the group ID values used to clean the result strings
	 * @return A String containing the group IDs used for cleaning
	 */
	public String getCleanerGroups() {
		return String.valueOf(cleanerIDs).replaceAll("(\\[)|(])", "");
	}

	/**
	 * Method to return results to Stata
	 */
	public void toStata() {
		StringJoiner retnames = new StringJoiner(" ");
		retnames.add("parser").add("cleaner").add("pgroups").add("clgroups")
			.add("raw");
		Macro.setLocal("parser", getParserPattern());
		Macro.setLocal("cleaner", getCleanerPattern());
		Macro.setLocal("pgroups", getParserGroups());
		Macro.setLocal("clgroups", getCleanerGroups());
		Macro.setLocal("raw", getRawResults());
		for (String k : getParsedKeys()) {
			String key = k.replaceAll("\\W", "_");
			retnames.add(key);
			Macro.setLocal(key.substring(0, Math.min(key.length(), 31)),
				this.parsedResults.get(k));
		}
		Macro.setLocal("retnames", retnames.toString());
	}



}
