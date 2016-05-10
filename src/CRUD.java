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

Example of parameters: -c Gotov m 07/03/1992 Perron m 09/02/1995

*/
public class CRUD {
	public static List<Person> allPeople = new ArrayList<Person>();
	static {
		allPeople.add(Person.createFemale("Emma Watson", new Date()));
		allPeople.add(Person.createMale("Vladimir Rozin", new Date()));
		allPeople.add(Person.createFemale("Vita Jonson", new Date()));
		allPeople.add(Person.createMale("Mark Perron", new Date()));
		allPeople.add(Person.createFemale("Jane Clark", new Date()));
		allPeople.add(Person.createMale("Evan Kirk", new Date()));
	}
	
	public static void main(String[] args) throws ParseException{
		//create and add person to the list of people
		if (args[0].equalsIgnoreCase("-c")) {
			String name = "";
			SimpleDateFormat inputFormat = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
			for (int i = 1; i < args.length - 1; i++) {
				if (args[i].equalsIgnoreCase("m") || args[i].equalsIgnoreCase("f")) {
					String date = args[i+1];
					Date result = inputFormat.parse(date);
					if (args[i].equalsIgnoreCase("m")) allPeople.add(Person.createMale(name, result));
					else if (args[i].equalsIgnoreCase("f")) allPeople.add(Person.createFemale(name, result));
					i++;
					name = "";
				}
				else 
					name = (name.isEmpty()) ? args[i] : name + " " + args[i];
			}
		}
		//update information of a person with the following id
		else if (args[0].equalsIgnoreCase("-u")) {
			String name = "";
			int index = 1;
			SimpleDateFormat inputDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
			
			for (int i = 2; i < args.length - 2; i++) {
				if (args[i].equalsIgnoreCase("m") || args[i].equalsIgnoreCase("f")) {
					Person person = allPeople.get(Integer.parseInt(args[index])); 
					person.setName(name);
					if (args[i].equalsIgnoreCase("m"))
						person.setSex(Sex.MALE);
					else if (args[i].equalsIgnoreCase("f"))
						person.setSex(Sex.FEMALE);
					person.setBirthDay(inputDate.parse(args[i+1]));
					name = "";
					index = i + 2;
					i = index + 1;
				}
				name = (name.isEmpty()) ? args[i] : name + " " + args[i];
			}
		}
		
		//remove a person with the following id from the list of people
		else if (args[0].equalsIgnoreCase("-d")) {
			for (int i = 1; i < args.length; i++) {
				Person person = allPeople.get(Integer.parseInt(args[i]));
				person.setName(null);
				person.setSex(null);
				person.setBirthDay(null);
			}
		}
		
		//review information of a person with the following id
		else if (args[0].equalsIgnoreCase("-i")) {
			SimpleDateFormat outputFormat = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
			for (int i = 1; i < args.length; i++) {
				Person person = allPeople.get(Integer.parseInt(args[i]));
				System.out.println(person.getName() + " "
						+ (person.getSex() == Sex.MALE ? "Male" : "Female") + " " 
						+ outputFormat.format(person.getBirthDay()));		
			}
		}
	}
}
