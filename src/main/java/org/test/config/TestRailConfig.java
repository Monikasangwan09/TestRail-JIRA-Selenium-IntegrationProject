package org.test.config;

import org.aeonbits.owner.Config;


/**
 * Configuration interface to provide method name for TestRail related details.
 * May 17, 2023
 * @author Mandeep Sheoran
 * @version 1.0
 * @see Config
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({ 
	"system:properties", 
	"system:env",
	"file:${user.dir}/src/test/resources/testrail.properties"
	})
public interface TestRailConfig extends Config {
	
	@Key("testrailurl")
	String testrailurl();
	
	@Key("testrailusername")
	String testrailusername();
	
	@Key("testrailpassword")
	String testrailpassword();
}
