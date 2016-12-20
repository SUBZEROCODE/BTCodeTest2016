/*
System to read input from a file to check whether a given router needs to be patched based on a set of criteria:

1)The router has not already been patched
2)The current version of the router OS is 12 or above
3)There are no other routers which share the same IP address
4)There are no other routers which share the same hostname
*/

//Imports
import java.util.ArrayList;
import java.util.HashMap;

public class RouterCheck{

	//Constructor to add a fileName for testing purposes.
	RouterCheck(){

	}

	//Main where program execution begins
	public static void main(String args[]){

		String filename = "";
		//Check if there are command line arguments
		if(args.length == 0){
			System.out.println("Please supply some arguments to the program");
		}

		//Otherwise assign the filename
		else{
			filename = args[0];
		}

		beginRouterCheck(filename);
	}
		
	public static String beginRouterCheck(String filename){
		String finalResult = "";

		//Declarations to be used later on: examples
		ArrayList<String> inputLines = new ArrayList<String>(); // ["d.example.com,1.1.1.4,yes,14","c.example.com,1.1.1.5,no,12,Case a bit loose"]

		//Store results of boolean validation tests for each line
		ArrayList<RouterUpdateChecker> linesAsBooleans = new ArrayList<RouterUpdateChecker>();

		//Store information associated with a given line
		ArrayList<RouterPatchInformation> linesAsInformation = new ArrayList<RouterPatchInformation>();

		//System.out.println(filename);

		//Try to get all lines from the file as a ArrayList of Strings.
		try{
			inputLines = IOOperations.readFile(filename);
		}

		//If no lines or error then issue error message.
		catch(Exception ex){
			System.out.println("File could not be read");
		}

		//Precompute ipaddresses & hostnames dictionaries with values equal to the number of occurences.
		HashMap<String,Integer> hostnames = getAllElementsFromArrayAtIndex(0, inputLines);
		HashMap<String,Integer> ipaddresses = getAllElementsFromArrayAtIndex(1, inputLines);

		//System.out.println(ipaddresses); //System.out.println(hostnames);

		//Set i to count the lines.
		int i = 1;

		//Get each line of input and split by comma delimiter.
		for(String inputLine: inputLines){

			//Make all requirements satisfied booleans false: change if a requirement is satisfied.
			Boolean osversionSatisfied = false; // If false then need to update.
			Boolean uniqueHostnameSatisfied = false; //If true then valid to update.
			Boolean uniqueIPAddressSatisfied = false; //If true then valid to update.
			Boolean isRouterPatchedSatisfied = false; //If false then need to update.
			Boolean illegalInput = false; //If true then don't print that line.

			//Possible future ideas: store all booleans - this is done in an Object currently.
			//ArrayList<Boolean> booleansUsed = new ArrayList<Boolean>();

			//Get result of split operation as a String array.
			String[] routerInformation = IOOperations.simplifyString(inputLine).split(",");
			int totalLen = routerInformation.length;

			linesAsBooleans.add(new RouterUpdateChecker(routerInformation,hostnames,ipaddresses));

			//Get the latest instance of the validation checker for this line
			RouterUpdateChecker thisLinesValidationChecker = linesAsBooleans.get(i - 1);

			Boolean updateRequired = false;
			updateRequired = thisLinesValidationChecker.isUpdateRequired();

			illegalInput = thisLinesValidationChecker.isAnyIllegalInput();

			//System.out.println("Was there any unexpected entries?:" + illegalInput);

			if(illegalInput == false && updateRequired == true){
				//System.out.println("Update required(no errors):" + updateRequired);
			}

			RouterPatchInformation linesRouterPatchInfo = thisLinesValidationChecker.getRouterPatchInfo();

			linesAsInformation.add(linesRouterPatchInfo);

			//Only prints the lines which need an update and are not full of illegalInputs

			i=i+1;

			int updateCounter = 0;
			if(updateRequired && illegalInput == false){

				String result = linesRouterPatchInfo.printRouterPatchInformation();

				if(updateCounter==0){
					finalResult = finalResult + result;
				}

				else{
					finalResult = finalResult + result;
				}
				
				updateCounter = updateCounter + 1;
				
			}

		}

		//System.out.println(finalResult);

		if(finalResult.equals("")){
			return "No results";
		}

		else{ 
			return finalResult;
		}

		
		
	}

	//Returns a hashmap of hostname/ipaddress and a count of the number of times that element occurs.
	private static HashMap<String,Integer> getAllElementsFromArrayAtIndex(int i, ArrayList<String> inputLines){

		HashMap<String,Integer> retrievedDict = new HashMap<String,Integer>();

		for(String inputLine: inputLines){

			//Get result of split operation as a String array: regex to remove spaces.
			String[] routerInformation = IOOperations.simplifyString(inputLine).split(",");

			String elementAtPositioni = routerInformation[i];

			try{

				if(retrievedDict.get(elementAtPositioni).intValue() >= 1){
					retrievedDict.put(elementAtPositioni, retrievedDict.get(elementAtPositioni) + 1);
				}
			}

			catch(NullPointerException n){
				retrievedDict.put(elementAtPositioni,1);
			}
		}

		return retrievedDict;

	}


}