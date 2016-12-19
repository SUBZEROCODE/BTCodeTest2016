import org.junit.Test;
import org.junit.ComparisonFailure;
import static org.junit.Assert.assertEquals;

public class JUnitTests{
	
	public static void runtests(){

		junitTest1();

	}

	public static void junitTest1(){

      try{
            String expectedResult = "Junit is working fine";
            assertEquals("Junit is working finet",expectedResult);
      }
      catch(ComparisonFailure e){
        System.err.println("assertequals fail");
      }


         // /  assertEquals("Mistake",1);

      }

}