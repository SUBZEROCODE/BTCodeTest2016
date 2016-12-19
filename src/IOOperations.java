import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;

//Class to perform input and output operations on classes in RouterCheck.java.
public class IOOperations{

	public static ArrayList<String> readFile(String filename) throws IOException {

		File file = new File(filename);

		ArrayList<String> lines = new ArrayList<String>();

		FileInputStream fis = new FileInputStream(file);
 
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
 
		String line = null;

		while ((line = br.readLine()) != null) {
			lines.add(line);
		}

		return lines;
 
	}


	public static void printInformationFromArray(int lineNo,String[] informationArray){

		System.out.println("\nLine" + lineNo);

		for(int i = 0; i < informationArray.length ; i++ ){

			if(i < informationArray.length - 1){
				System.out.print(informationArray[i] + ",");
			}

			else{
				System.out.print(informationArray[i]);
			}
			
		}
	}

	//Removes blank space and makes lowerCase
	public static String simplifyString(String inputString){
		return inputString.toLowerCase().trim();
	}

}