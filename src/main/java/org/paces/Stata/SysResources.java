package org.paces.Stata;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * @author Billy Buchanan
 * @version 0.0.0
 */
public class SysResources {

	private static OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

	public static void main(String[] args) {
		resources(args);
	}

	public static int resources(String[] args) {
		Long committedMemory = osBean.getCommittedVirtualMemorySize();
		Long totalSwap = osBean.getTotalSwapSpaceSize();
		Long freeSwap = osBean.getFreeSwapSpaceSize();
		Long processCPUTime = osBean.getProcessCpuTime();
		Long freePhysicalMem = osBean.getFreePhysicalMemorySize();
		Long totalPhysicalMem = osBean.getTotalPhysicalMemorySize();
		Double systemCPULoad = osBean.getSystemCpuLoad();
		Double processLoad = osBean.getProcessCpuLoad();
		System.out.println("Total committed memory is : " + prettyBytes(committedMemory));
		System.out.println("Free Swap Space is : " + prettyBytes(freeSwap));
		System.out.println("Total Available Swap Space is : " + prettyBytes(totalSwap));
		System.out.println("Process CPU Time is : " + String.valueOf(processCPUTime));
		System.out.println("Free Physical Memory Available is : " + prettyBytes(freePhysicalMem));
		System.out.println("Total Physical Memory Available is : " + prettyBytes(totalPhysicalMem));
		System.out.println("System CPU Load is : " + String.valueOf(systemCPULoad));
		System.out.println("Process Load is : " + String.valueOf(processLoad));
		System.out.println("% Free Physical Memory is : " + String.valueOf(freePhysicalMem / totalPhysicalMem));
		return 0;
	}

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
