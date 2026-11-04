package edu.monmouth.hw2;

public class HW2Constants
{
	// command line arguments
	public static final int REQUIREDARGS = 3;
	public static final int OUTPUTFILEARG = 0;
	public static final int STRINGFILEARG = 1;
	public static final int VEHICLEFILEARG = 2;

	// exit codes
	public static final int BADARGS = 1;
	public static final int BADREDIRECT = 2;
	public static final int BADINPUTFILE = 3;

	// layout of a line in the vehicle file
	public static final String SEPARATOR = ",";
	public static final int VEHICLEFIELDS = 3;
	public static final int TYPEFIELD = 0;
	public static final int POWERFIELD = 1;
	public static final int SIZEFIELD = 2;

	// vehicle types in the vehicle file
	public static final String CARTYPE = "c";
	public static final String BOATTYPE = "b";

	// list index passed to remove()
	public static final int REMOVEINDEX = 1;

	private HW2Constants() {}
}
