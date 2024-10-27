package org.nic.bug_tracker_system.enums;


public interface Http {

	/* status code */
	public static final int OK= 200;
	public static final int ACCEPTED= 202;
	public static final int EXCEPTION= 417;
	public static final int TEA_POT= 418;
	public static final int BAD_REQUEST = 400;
	public static final int INTERNAL_SERVER_ERROR = 500;
	public static final int NOT_FOUND = 404;
	/* status message */
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILURE =  "FAILURE";
	
	
	
	
}
