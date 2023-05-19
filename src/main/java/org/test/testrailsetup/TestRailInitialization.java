package org.test.testrailsetup;

import java.io.IOException;
import java.lang.reflect.Method;
import java.rmi.AccessException;
import java.util.HashMap;
import java.util.Map;
import org.igt.configfactory.TestRailFactory;
import org.json.simple.JSONObject;
import org.test.annotationinterface.TestRailAnnotation;
import org.testng.ITestContext;
import org.testng.ITestResult;


/**
 * Class to read and set TestRail URL, credentials, Project ID and other
 * configurations. May 18, 2023
 * 
 * @author Mandeep Sheoran
 * @version 1.0
 * @since 1.0
 * @see TestRailAPIClient
 */
public class TestRailInitialization {

	public final int TEST_CASE_PASSED_STATUS = 1;
	public final int TEST_CASE_FAILED_STATUS = 5;
	TestRailAPIClient client = null;

	public void initTestRailSetup(ITestContext ctx) throws IOException, AccessException, TestRailAPIException {

		String PROJECT_ID = "1";

		client = new TestRailAPIClient(TestRailFactory.getConfig().testrailurl());
		client.setUser(TestRailFactory.getConfig().testrailusername());
		client.setPassword(TestRailFactory.getConfig().testrailpassword());

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("include_all", true);
		data.put("name", "Test Run " + System.currentTimeMillis());
		JSONObject c = null;
		c = (JSONObject) client.sendPost("add_run/" + PROJECT_ID, data);
		Long suite_id = (Long) c.get("id");
		ctx.setAttribute("suiteId", suite_id);
	}

	public void preTestRunSetup(ITestContext ctx, Method method, ITestResult result) throws NoSuchMethodException {
		Method m = result.getMethod().getConstructorOrMethod().getMethod();
		if (m.isAnnotationPresent(TestRailAnnotation.class)) {
			TestRailAnnotation ta = m.getAnnotation(TestRailAnnotation.class);
			System.out.println("Test case is: " + ta.id());
			ctx.setAttribute("caseId", ta.id());
		}
	}

	public void postTestSteup(ITestResult result, ITestContext ctx) throws IOException, TestRailAPIException {
		Map<String, Object> data = new HashMap<String, Object>();
		if (result.isSuccess()) {
			data.put("status_id", TEST_CASE_PASSED_STATUS);
		} else {
			data.put("status_id", TEST_CASE_FAILED_STATUS);
			data.put("comment", result.getThrowable().toString());
		}
		String caseId = (String) ctx.getAttribute("caseId");
		Long suiteId = (Long) ctx.getAttribute("suiteId");
		System.out.println("Case Id is: " + caseId + " and suiteID is: " + suiteId);
		client.sendPost("add_result_for_case/" + suiteId + "/" + caseId, data);
	}
}
