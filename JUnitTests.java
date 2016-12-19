import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnitTests{
	
	public static void runtests(){

		Boolean test1Result = junitTest1();


	}

	public static Boolean junitTest1{

	  String expectedResult = "Junit is working fine";
      Boolean testResult =  assertEquals("Junit is working fine",str);

      if(testResult == true){
      	 System.out.println("Tests passed")
      }

      else{
      	System.out.println("Failure in JUnit test 1")
      }

      System.out.println("Result of testing:" + result);
      return result;

	}


}