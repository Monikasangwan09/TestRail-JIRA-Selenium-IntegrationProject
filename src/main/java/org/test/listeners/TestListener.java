package org.test.listeners;


import org.test.testrailsetup.TestRailAPIException;
import org.test.testrailsetup.TestRailInitialization;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;
import java.rmi.AccessException;
import java.time.Instant;

/**
 * Test Listener class to control the test execution as well as initiate/flush extent report.
 * Mar 4, 2023
 * @author Mandeep Sheoran
 * @version 1.0
 * @since 1.0
 * @see ExtentReport
 * @see ExtentLogger
 */
public class TestListener implements ITestListener, ISuiteListener {	
	
	
	/**
	 * Method to create TestRail Class object to initialize the TestRail and framework integration.
	 * @author Mandeep Sheoran
	 * @see TestRailInitialization
	 */
	public void onStart(ITestContext context) {
		System.out.println("Before test start");
		TestRailInitialization TI = new TestRailInitialization();

			try {
				TI.initTestRailSetup(context);
				System.out.println("Before test start ended");
			} catch (AccessException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TestRailAPIException e) {
				e.printStackTrace();
			}
	}


	public  void onTestStart(ITestContext ctx, Method method, ITestResult result) {
		System.out.println("On test start");
		TestRailInitialization TI = new TestRailInitialization();
		try {
			TI.preTestRunSetup(ctx, method, result);
			System.out.println("On test start ended");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to TestRail after execution completion.
	 * @author Mandeep Sheoran
	 */
	public void onFinish(ITestContext context,ITestResult result) {
		System.out.println("On finish");
		TestRailInitialization TI = new TestRailInitialization();	
			try {
				TI.postTestSteup(result, context);
				System.out.println("On finish ended");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TestRailAPIException e) {
				e.printStackTrace();
			}
	}
	
}
