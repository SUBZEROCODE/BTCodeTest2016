import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//Validation and sanity checks to look for whether a given requirement is satisfied.
public class RouterUpdateChecker{

	Boolean osversionSatisfied = false; // If false then need to update.
	Boolean uniqueHostnameSatisfied = false; //If true then valid to update.
	Boolean uniqueIPAddressSatisfied = false; //If true then valid to update.
	Boolean isRouterPatchedSatisfied = false; //If false then need to update.
	Boolean updateRequired = false;
	Boolean illegalInput = false; //If true then don't print that line.
	RouterPatchInformation routerPatchInfo = new RouterPatchInformation();

	//Constructor to create an instance of a RouterUpdateChecker based on a set of RouterInformation and precomputed dictionaries.
	RouterUpdateChecker(String[] routerInformation, HashMap<String,Integer> hostnames,HashMap<String,Integer> ipaddresses){

			Boolean osversionSatisfied = false; // If false then need to update.
			Boolean uniqueHostnameSatisfied = false; //If true then valid to update.
			Boolean uniqueIPAddressSatisfied = false; //If true then valid to update.
			Boolean isRouterPatchedSatisfied = false; //If false then need to update.
			Boolean updateRequired = false; //If true then update will be required.

			String hostname = ""; //a.example.com
			String ipaddress = ""; //1.1.1.1
			String patchedInfo = ""; //yes
			String osversion = ""; //12
			String notes = "";

			int totalLen = routerInformation.length;

			// //Simple prints to check program execution.
			//IOOperations.printInformationFromArray(i++,routerInformation);

			if(totalLen >= 1){
				hostname = routerInformation[0];

				//One hostname as that key.
     			if(hostnames.get(hostname) == 1){
     				uniqueHostnameSatisfied = true;
     			}

     			//Duplicate hostname
     			else{
     				//System.out.println("\nDuplicate hostname detected");
     				uniqueHostnameSatisfied = false;
     			}

     		}

			if(totalLen >= 2){
			 	ipaddress = routerInformation[1];

			 	//One i.p address as that key.
			 	if(ipaddresses.get(ipaddress) == 1){	
			 		uniqueIPAddressSatisfied = true;
			 	}

			 	//Duplicate hostname
			 	else{
			 		//System.out.println("\nDuplicate hostname detected");
			 		uniqueIPAddressSatisfied = false;
			 	}

			 }


			if(totalLen >= 3){
				patchedInfo = routerInformation[2];
				isRouterPatchedSatisfied = checkIfRouterPatched(patchedInfo);
			}

			if(totalLen >= 4){
				osversion = routerInformation[3];
				osversionSatisfied = checkIfCurrentVersionGreaterThan12(osversion);
			}

			if(totalLen >= 5){
				notes = routerInformation[4].substring(0, 1).toUpperCase() + routerInformation[4].substring(1);
			}

			illegalInput = checkForIllegalInput(hostname,ipaddress,patchedInfo,osversion);

			if(illegalInput){
				//System.out.println("Illegal input found");
			}

			this.osversionSatisfied = osversionSatisfied;
			this.isRouterPatchedSatisfied =isRouterPatchedSatisfied;
			this.uniqueIPAddressSatisfied = uniqueIPAddressSatisfied;
			this.uniqueHostnameSatisfied = uniqueHostnameSatisfied;
			this.illegalInput = illegalInput;

			this.updateRequired = checkIfUpdateRequired(osversionSatisfied,isRouterPatchedSatisfied,uniqueHostnameSatisfied,uniqueIPAddressSatisfied);

			this.routerPatchInfo = new RouterPatchInformation(hostname,ipaddress,patchedInfo,osversion,notes);

	}

	//Function to check whether the data extracted seems sensible using regex.
	public static Boolean checkForIllegalInput(String hostname, String ipaddress, String patched, String osversion){

		Boolean illegalInput = true;

		//Hostname check : if false does not match a "x.y.z.n ip"
		if(ipaddress.matches("[0-9]\\.[0-9]\\.[0-9]\\.[0-9]") == false){
			//System.out.println("not a valid ip address");

			if(hostname.matches("[a-zA-Z0-9]+\\.[a-zA-Z0-9]+\\.[a-zA-Z0-9]+") == false){
				//System.out.println("not a valid hostname");

				//This means that this line is not what we are looking to classify with 
				if(((patched.equals("yes") == false) && (patched.equals("YES") == false))){

					//System.out.println("Likely to be a csv of different text to expected");

					illegalInput = true;
					//e.g. the title fields.
				}
			}
		}

		else{
			illegalInput = false;
		}

		return illegalInput;

	}

	//Check whether current text states that the router is already patched.
	public Boolean checkIfRouterPatched(String routerPatched){

		if(routerPatched.equals("yes") || routerPatched.equals("YES")){
			return true;
		}

		else if(routerPatched.equals("no") || routerPatched.equals("NO")){
			//System.out.println("\nRouter not patched");
			return false;
		}

		else{

			//Making the assumption that any other input will mean that the router needs to be patched.
			return false;
		}

	}

	//Function to check whether the current os version exceeds the requirement of being >=12
	public Boolean checkIfCurrentVersionGreaterThan12(String osversionInput){

		double osversion = 0.00;

		//Checks whether the osversion can be converted to a double.
		
		try{
  			osversion = Double.parseDouble(osversionInput);

  			if(osversion < 12.00){
				return true;

			}

			else{
				//System.out.println("Router is not greater than 12");
				return false;
			}
		}

		catch(NumberFormatException e){
	 	 	return false;
		}


	}

	public Boolean checkIfUpdateRequired(Boolean osversionSatisfied,Boolean isRouterPatchedSatisfied,Boolean uniqueHostnameSatisfied,Boolean uniqueIPAddressSatisfied){

			//Check whether any of booleans failed a requirement satisfaction: if did then an update is required.
			if(osversionSatisfied == false && isRouterPatchedSatisfied == false && uniqueHostnameSatisfied == true && uniqueIPAddressSatisfied == true){
				updateRequired = true;
			}

			else{
				updateRequired = false;
			}

			return updateRequired;
	}

	public Boolean isUpdateRequired(){
		return this.updateRequired;
	}

	public Boolean isAnyIllegalInput(){
		return this.illegalInput;
	}

	public RouterPatchInformation getRouterPatchInfo(){
		return this.routerPatchInfo;
	}




}