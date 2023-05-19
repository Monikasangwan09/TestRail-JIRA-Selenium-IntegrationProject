package org.test.testrailsetup;

/**
 * Custom exception class to throw for TestRail API related exceptions.
 * May 18, 2023
 * @author Mandeep Sheoran
 * @version 1.0
 * @since 1.0
 * @see TestRailAPIClient
 */
@SuppressWarnings("serial")
public class TestRailAPIException extends Exception {

	public TestRailAPIException(String message)
	{
		super(message);
	}
}
