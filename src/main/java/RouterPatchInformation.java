
//Object which stores the router patching information
public class RouterPatchInformation{

	//Declarations
	String hostname =  "";
	String ipaddress = "";
	String patchedInfo = "";
	String osversion = "";
	String notes = "";

	//Constructor for simple RouterPatchInformation object: needs setters if this is chosen
	RouterPatchInformation(){
	}

	//Constructor for complex RouterPatchInformation object.
	RouterPatchInformation(String hostname,String ipaddress,String patchedInfo,String osversion,String notes){

		this.hostname  = hostname;
		this.ipaddress = ipaddress;
		this.patchedInfo = patchedInfo;
		this.osversion = osversion;
		this.notes = notes;

	}

	// String getHostname(){
	// 	return this.hostname;
	// }

	// String getIPaddress(){
	// 	return this.ipaddress;
	// }

	// String getPatchedInfo(){
	// 	return this.patchedInfo;
	// }

	// String getOSVersion(){
	// 	return this.osversion;
	// }

	// String getNotes(){
	// 	return this.notes;
	// }

	public String printRouterPatchInformation(){

		String finalResult = this.hostname + " (" + this.ipaddress + "), " + "OS version " + this.osversion;

			if(notes.equals("")){

				System.out.println(finalResult);
			}

			else{
				finalResult = finalResult + " [" + this.notes + "]";

				System.out.println(finalResult);
			}

		return finalResult;
			
	}

}