import junit.framework.TestCase;

import java.util.ArrayList;

public class JUnitTest extends TestCase{

        public JUnitTest(String name) {
                super( name );
        }

      	
	public static void testJunit() throws Exception{

	 	assertEquals("HI","HI");

	}

	public static void testOutput() throws Exception{

            String systemResult = RouterCheck.beginRouterCheck("sampleTests.csv");

            String expectedResult = "b.example.com (1.1.1.2), OS version 13 [Behind the other routers so no one sees it]f.example.com (1.1.1.7), OS version 12.200";

            assertEquals(expectedResult, systemResult );
      }

      // public static void testRead() throws Exception{
      //       ArrayList<String> readResult = IOOperations.readFile("C:/Users/Sean/Documents/GitHub/BTCodeTest2016/src/main/java/sampleTests.csv");

      //       ArrayList<String> readTest = new ArrayList<String>();

      //       assertEquals(readResult, readTest);
      // }


}