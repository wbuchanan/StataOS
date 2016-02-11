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
		Resources r = new Resources(osBean);
		System.out.println("Total committed memory is : " + r.prettyBytes(r.committedMemory));
		System.out.println("Free Swap Space is : " + r.prettyBytes(r.freeSwap));
		System.out.println("Total Available Swap Space is : " + r.prettyBytes(r.totalSwap));
		System.out.println("Process CPU Time is : " + String.valueOf(r.processCPUTime));
		System.out.println("Free Physical Memory Available is : " + r.prettyBytes(r.freePhysicalMem));
		System.out.println("Total Physical Memory Available is : " + r.prettyBytes(r.totalPhysicalMem));
		System.out.println("System CPU Load is : " + String.valueOf(r.systemCPULoad));
		System.out.println("Process Load is : " + String.valueOf(r.processLoad));
		System.out.println("% Free Physical Memory is : " + String.valueOf(r.pctFreeMemory));
	}


	public static int compResources(String[] args) {
		Resources sysresource = new Resources(osBean);
		if (args.length == 1) {
			sysresource.print();
			sysresource.returnToStata();
			return 0;
		} else {
			sysresource.returnToStata();
			return 0;
		}
	}




}
