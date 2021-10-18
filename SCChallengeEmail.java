import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Scanner;

public class SCChallengeEmail {
	/**
	 * This program allows the user to enter an email ID of an ECS staff member and
	 * it then returns their full name
	 * @throws IOException
	 */
    public static void main (String[] args) throws IOException{
		while (true){
			SCChallengeEmail email = new SCChallengeEmail();
			Scanner input = new Scanner(System.in);
			System.out.println("Welcome to the ID retrieval service, enter N for the name or Q to exit");
			String menuChoice = input.nextLine().toUpperCase();
			if(!menuChoice.equals("N")&&!menuChoice.equals("Q")){ // checks if an incorrect choice has been made
				System.out.println("You have made an incorrect choice, please try again");
				continue;
			}else if(menuChoice.equals("Q")){
				break;
			}else{
				email.htmlRetrieval();
			}
		}
    }
	public void htmlRetrieval() throws IOException{
		while(true){
			Scanner userInput = new Scanner(System.in);
			System.out.println("Please enter your departmental email ID:");
			String emailId = userInput.nextLine();
			String URL = "https://www.ecs.soton.ac.uk/people/" + emailId;
			System.out.println(URL);
			Document doc = Jsoup.connect(URL).get(); // using the jsoup library to retrieve the HTML of the page  
			String title = doc.title(); // assigns the variable title to the attribute title of the HTML
			title = title.split("[|]")[0]; // simple regex to cut string at the first pipe character
			if(title.trim().equals("People")){
				System.out.println("The email ID you entered has not been found, please try again:");
				continue;
			} else{
				System.out.println("The ID " + emailId + " is linked to " + title );
				break;
			}
		}
	}
}
