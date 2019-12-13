
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Driver2 {

	String xTC[][], xTS[][];
	String xlPath = "C:\\Eclipe Workspace 2\\KDF\\KDT_Test1.xls";
	int xTC_r, xTS_r;
	String xTC_TCID, xTC_Execute;
	String xTS_TCID, xTS_KW, xTS_EID, xTS_TestData;

	@Before
	public void myBefore() throws Exception {

		// Read the Excel from 2D of the Array type

		System.out.println(">>>>>>>>>>>>>>>  Start Reading Excel>>>>>>>>>>>>>>>>>");

		xTC = Utilities.readXL(xlPath, "TestCases");
		xTS =Utilities. readXL(xlPath, "TestSteps");

		xTC_r = xTC.length;

		System.out.println(">>>>>>>>>>>>>>> Number of TestCases>>>>>>>>>>>>>>>>>>" + xTC_r);

		xTS_r = xTC.length;
		System.out.println(">>>>>>>>>>>>>>> Number of TestCases>>>>>>>>>>>>>>>>>>" + xTS_r);

	}

	@Test
	public void myTest() {
		System.out.println(">>>>>>>>>>>>>>>  Start the Test>>>>>>>>>>>>>>>>>");
		// Go through the each rows (Maintest)
		for (int i = 1; i < xTC_r; i++) {
			// See execute is Y
			xTC_TCID = xTC[i][0];
			xTC_Execute = xTC[i][3];
			if (xTC_Execute.equalsIgnoreCase("Y")) {
				// Then go to Each row in the steps

				System.out.println(">>>>>>Executing TCID:" + xTC_TCID);
				for (int j = 0; j < xTS_r; j++) {

					xTS_TCID = xTS[j][0];
					xTS_KW = xTS[j][3];
					xTS_EID = xTS[j][4];
					xTS_TestData = xTS[j][5];
					
					if (xTC_TCID.equals(xTS_TCID)) {
						// Print the details
						System.out.println(" >>>>>>>>>>> Print the Keyword:" + xTS_KW);
						System.out.println(" >>>>>>>>>>> Print the ElD" + xTS_EID);
						System.out.println(" >>>>>>>>>>> Print the TestData:" + xTS_TestData);
					}

					else {
						System.out.println("****** Skipping :" + xTC_TCID);
					}
					System.out.println(">>>>>>>>>>>>>>> End the Test>>>>>>>>>>>>>>>>>>");
				}
			}
		}
	}

	@After
	public void myAfter() {

		System.out.println(">>>>>>>>>>>>>>> Start Writing Excel>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>> End Writing Excel >>>>>>>>>>>>>>>>>>");

	}

	
}
