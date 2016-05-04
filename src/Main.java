import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/* CRUD - Create, Update, Delete
This application should run with one of the following parameters:
-c name sex birthday
-u id name sex birthday
-d id
-i id

Parameters:
name - String
sex - "m" or "f", one letter
birthday - date in format of dd/mm/yyyy

-c adds person with defined parameters in the end of list of people, prints out the id of this person
-u updates person information with following id
-d removes person with following id from the list of people
-i prints out information of the person with following id: name, sex(m/f), birthday(dd-Mmm-yyyy)

id equals index in the list

Example of parameters: -c Gotov m 07/03/1992

*/
public class Main {
	public static List<Person> allPeople = new ArrayList<Person>();
	static {
		allPeople.add(Person.createFemale("Emma", new Date()));
		allPeople.add(Person.createMale("Vladimir", new Date()));
	}
	
	public static void main(String[] args) {
		
	}
	
}
