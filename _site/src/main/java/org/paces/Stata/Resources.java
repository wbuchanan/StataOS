package org.paces.Stata;

import com.stata.sfi.SFIToolkit;
import com.stata.sfi.Scalar;
import com.sun.management.OperatingSystemMXBean;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class Resources {

	/**
	 * Member used to store the committed Memory value
	 */
	public Long committedMemory;

	/**
	 * Member used to store the total swap size available value
	 */
	public Long totalSwap;

	/**
	 * Member used to store the free swap size available value
	 */
	public Long freeSwap;

	/**
	 * Member used to store the CPU time of the current process
	 */
	public Long processCPUTime;

	/**
	 * Member used to store the free physical memory
	 */
	public Long freePhysicalMem;

	/**
	 * Member used to store the total physical memory
	 */
	public Long totalPhysicalMem;

	/**
	 * Member used to store the system CPU load
	 */
	public Double systemCPULoad;

	/**
	 * Member used to store the current process load
	 */
	public Double processLoad;

	/**
	 * Member used to store the perent of available physical memory that is
	 * freely available
	 */
	public Double pctFreeMemory;

	/**
	 * Class constructor
	 * @param osBean An operating system bean that allows access to OS
	 *                  resource information
	 */
	public Resources(OperatingSystemMXBean osBean) {
		this.committedMemory = osBean.getCommittedVirtualMemorySize();
		this.totalSwap = osBean.getTotalSwapSpaceSize();
		this.freeSwap = osBean.getFreeSwapSpaceSize();
		this.processCPUTime = osBean.getProcessCpuTime();
		this.freePhysicalMem = osBean.getFreePhysicalMemorySize();
		this.totalPhysicalMem = osBean.getTotalPhysicalMemorySize();
		this.systemCPULoad = osBean.getSystemCpuLoad();
		this.processLoad = osBean.getProcessCpuLoad();
		this.pctFreeMemory = (100 *
							(Double.valueOf(this.freePhysicalMem) /
							Double.valueOf(this.totalPhysicalMem)));
	}

	/**
	 * Method to print the data to the screen
	 */
	public void print() {
		SFIToolkit.displayln("Total committed memory is : " + prettyBytes(this.committedMemory));
		SFIToolkit.displayln("Free Swap Space is : " + prettyBytes(this.freeSwap));
		SFIToolkit.displayln("Total Available Swap Space is : " + prettyBytes(this.totalSwap));
		SFIToolkit.displayln("Process CPU Time is : " + String.valueOf(this.processCPUTime));
		SFIToolkit.displayln("Free Physical Memory Available is : " + prettyBytes(this.freePhysicalMem));
		SFIToolkit.displayln("Total Physical Memory Available is : " + prettyBytes(this.totalPhysicalMem));
		SFIToolkit.displayln("System CPU Load is : " + String.valueOf(this.systemCPULoad));
		SFIToolkit.displayln("Process Load is : " + String.valueOf(this.processLoad));
		SFIToolkit.displayln("% Free Physical Memory is : " + String.valueOf(this.pctFreeMemory));
	}

	/**
	 * Method used to set scalar values back in Stata
	 */
	public void returnToStata() {
		Scalar.setValue("commitmem", Double.valueOf(this.committedMemory));
		Scalar.setValue("freeswap", Double.valueOf(this.freeSwap));
		Scalar.setValue("totalswap", Double.valueOf(this.totalSwap));
		Scalar.setValue("proctime", Double.valueOf(this.processCPUTime));
		Scalar.setValue("freemem", Double.valueOf(this.freePhysicalMem));
		Scalar.setValue("totmem", Double.valueOf(this.totalPhysicalMem));
		Scalar.setValue("cpuload", this.systemCPULoad);
		Scalar.setValue("procload", this.processLoad);
		Scalar.setValue("pctfreemem", this.pctFreeMemory);
	}

	/**
	 * Wrapper around the humanReadableByteCount method below
	 * @param bytes The byte value to translate into MB
	 * @return A string prepped for printing to the console
	 */
	public static String prettyBytes(long bytes) {
		return humanReadableByteCount(bytes, false);
	}

	/**
	 * Method copied from response on :
	 * http://stackoverflow.com/questions/3758606/how-to-convert-byte-size-into-human-readable-format-in-java
	 * From users BalusC and aioobe
	 * @param bytes The byte value to convert
	 * @param si The base value to use.  If false will assume 1024 bytes per MB
	 * @return A formatted string containing the byte value
	 */
	private static String humanReadableByteCount(long bytes, boolean si) {
		int unit = si ? 1000 : 1024;
		if (bytes < unit) return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

}
