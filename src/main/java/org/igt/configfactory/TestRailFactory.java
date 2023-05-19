package org.igt.configfactory;

import org.aeonbits.owner.ConfigCache;
import org.test.config.TestRailConfig;

/**
 * Factory class to provide get method to fetch configuration values from configuration interface for TestRail.
 * May 17, 2023
 * @author Mandeep Sheoran
 * @version 1.0
 * @see ConfigCache
 */
public class TestRailFactory {
	
	private TestRailFactory() {}
	
	public static TestRailConfig getConfig() {	
		return ConfigCache.getOrCreate(TestRailConfig.class);
	}
}
