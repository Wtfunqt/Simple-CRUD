import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Date;

/* CRUD - Multiple Create, Update, Delete
This application should run with one of the following parameters:
-c name1 sex1 birthday1 name2 sex2 birdthday2 ...
-u id1 name1 sex1 birthday1 id2 name2 sex2 birthday2 ...
-d id1 id2 id3 ...
-i id1 id2 id3 ...

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
		allPeople.add(Person.createFemale("Emma Watson", new Date()));
		allPeople.add(Person.createMale("Vladimir Rozin", new Date()));
	}
	
	public static void main(String[] args) throws ParseException {
		//create and add person to the list of people
		if (args[0].equalsIgnoreCase("-c") && args.length <= 4) {
			int a = 1;
			String name = "";
			for (int i = 1; i < args.length; i++) {
				if (args[i].equalsIgnoreCase("m") || args[i].equalsIgnoreCase("f")) {
					a = i;
					break;
				}
				else 
					name = (name.isEmpty()) ? args[i] : name + " " + args[i];
			}
			
			String date = args[a+1];
			SimpleDateFormat inputFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
			Date result = inputFormat.parse(date);
			
			if (args[a].equalsIgnoreCase("m")) allPeople.add(Person.createMale(name, result));
			else if (args[a].equalsIgnoreCase("f")) allPeople.add(Person.createFemale(name, result));
		}
		//update information of a person with the following id
		else if (args[0].equalsIgnoreCase("-u") && args.length <= 5) {
			Person person1 = allPeople.get(Integer.parseInt(args[1]) - 1);
			
			String name = "";
			for (int i = 2; i < args.length; i++) {
				if (args[i].equalsIgnoreCase("m") || args[i].equalsIgnoreCase("f")) {
					break;
				}
				name = (name.isEmpty()) ? args[i] : name + " " + args[i];
			}
			person1.setName(name);
			
			person1.setSex(args[3] == "m" ? Sex.MALE : Sex.FEMALE);
			
			SimpleDateFormat inputFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
			person1.sexBirthDay(inputFormat.parse(args[4]));
			
			SimpleDateFormat outputFormat = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
			for (Person person : allPeople) {
				System.out.println(person.getName() + " "
						+ (person.getSex() == Sex.MALE ? "Male" : "Female") + " " 
						+ outputFormat.format(person.getBirthDay()));
			}
		}
		
		//remove a person with the following id from the list of people
		else if (args[0].equalsIgnoreCase("-d") && args.length == 2) {
			allPeople.remove(Integer.parseInt(args[1]) - 1);
		}
		
		//review information of a person with the following id
		else if (args[0].equalsIgnoreCase("-i") && args.length == 2) {
			SimpleDateFormat outputFormat = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
			Person person = allPeople.get(Integer.parseInt(args[1]) - 1);
			System.out.println(person.getName() + " "
					+ (person.getSex() == Sex.MALE ? "Male" : "Female") + " " 
					+ outputFormat.format(person.getBirthDay()));		
		}
	}
	
}
