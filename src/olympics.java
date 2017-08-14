/*Author: Winsome Yuen 

This program asks the user whether they want to see
the scoreboard for the amount of medals and then
prints out the medal scoreboard. The user is then asked
the amount of medals won for the day in seperate methods 
and then asks what medal score they would like to see. The
user asked about the medals for the day are looped till
the user says they would not like to update the medal table.

*/

import java.util.*; 

class olympics6
{
    public static void main (String[] param)
    {		
        question();
        System.exit(0);
		
    } // END main

//method asks user to input whether they want to see the medal scoreboard
    public static void question ()
    {
        String askQuestion;
		
		askQuestion = (inputString("What country medals do you want to see/ update? (Japan, Canada, Germany or UK)").toLowerCase());
		
		if (askQuestion.equals("japan"))
		{
			medalTotal();
		}
		else if (askQuestion.equals("canada"))
		{
		}
		else if (askQuestion.equals("germany"))
		{
		}
		else if (askQuestion.equals("uk"))
		{
			
		}
		else
		{
			print("Please enter a country from the list");
		}
		
		//put option to view a different country
		return;
		
    } // END question
	

//the method prints out the total amount of medals
//method asks user to input whether they want to see the medal scoreboard
//then asks for the daily medals i a loop and asks which medal score they want to see
    public static void medalTotal ()
    {
		Medal m1 = new Medal();

		m1 = setCountry(m1, "Japan");
		m1 = setGold(m1, 6);
		m1 = setSilver(m1,15);
		m1 = setBronze(m1, 9);
		m1 = setTotal(m1, getGold(m1), getSilver(m1), getBronze(m1));
		
		
		String medals1 = "      G   S   B  Total";
        String medals2 = getCountry(m1) + " " + getGold(m1) +"   "+ getSilver(m1) +"  "+ getBronze(m1) +"   "+ getTotal(m1);
		
		print("The current medal score (day 1):\n" + medals1 + "\r\n" + medals2);
		
		int counter = 2;
					
		int gold = getGold(m1);
		int silver = getSilver(m1);
		int bronze = getBronze(m1);
		int total = 0;
		
		//sets up an array and passes the medal data to be stored into the array
		String [] storemedals = initialmedals();
		storemedals = initArray(storemedals, medals2, 0);
		
		for (int i = 1; i <counter; i++)
		{
			int addGold = inputInt("How many Gold's were won today?");
			int addSilver= inputInt("How many Silver's were won today?");
			int addBronze = inputInt("How many Bronze's were won today?");
			
			if (addGold<0)
			{
				print("Please enter a number equal to 0 or  above for the Gold Medal's:");
				addGold = inputInt("How many Gold's were won today?");
			}
			else if (addSilver<0)
			{
				print("Please enter a number equal to 0 or  above for the Silver Medal's:");
				addSilver = inputInt("How many Silver's were won today?");
			}
			else if (addBronze<0)
			{
				print("Please enter a number equal to 0 or  above for the Bronze Medal's:");
				addBronze = inputInt("How many Bronze's were won today?");
			}
			
			
			gold = medalTotal(gold, addGold);
			silver = medalTotal(silver, addSilver);
			bronze = medalTotal(bronze, addBronze);
			total = calculateTotal(gold, silver, bronze);
			medals2 = getCountry(m1) + " "+ gold + "  " + silver + "   " + bronze + "   " + total;
			
			//update array
			storemedals = initArray(storemedals, medals2, i);
			
			String individualmedals = (inputString("Would you like to see gold, silver or broze medals, or all of the medals").toLowerCase());		
			if (individualmedals.equals("gold"))
			{
				print("Gold: " + gold);
			}
			else if (individualmedals.equals("silver"))
			{
				print("Silver: " + silver);
			}
			else if (individualmedals.equals("bronze"))
			{
				print("Bronze: " + bronze);
			}
			else if (individualmedals.equals("all"))
			{
				print(medals1 + "\r\n" + medals2);
			}
			else
			{
				print("Option invalid, please try again:");
				individualmedals = (inputString("Would you like to see gold, silver or broze medals, or all of the medals").toLowerCase());		
			}
			
			int nextDay = i+2;
			int askLoop = (inputInt("\nDo you want to:\n1.Update medal table for day " + nextDay + "?\n2.See medal from past day\n3.Exit program"));
			if (askLoop==1)
			{
				counter++;
			}
			else if(askLoop==2)
			{
				int day = inputInt("Would you like to see a 1.single day or 2.all days?");
				if (day==1)
				{	
					int searchKey = (inputInt("What medals do you want to see out of the "+ counter + " days?")-1);		//day looked for
					String result = search(searchKey, storemedals);
					
					print(medals1 +"\n" + result);
				}
				else if (day==2)
				{
					print("   " + medals1);
					printtable(storemedals);
				}
				else
				{
					print("Please enter 1 or 2:");
				}
			}
			else if (askLoop==3)
			{
				print("Bye!");
				System.exit(0);
			}
			else
			{
				print("Please enter a number between 1-3 for the options:");
			}
			
		} //END loop
		

        return;
    } // END medalTotal
	

	
	//Getter methods for Medal records type
	public static String getCountry (Medal m)
	{
		return m.country;
	}
	
	public static int getGold (Medal m) 
	{
		return m.gold;
	}
	
	public static int getSilver (Medal m)
	{
		return m.silver;
	}
	
	public static int getBronze (Medal m)
	{
		return m.bronze;
	}
	
	public static int getTotal (Medal m)
	{
		return  m.total;
	}
	
	
	//Seter methods for Medal record type
	public static Medal setCountry (Medal m, String medalcountry)
	{
		m.country = medalcountry;
		return m;
	}
	public static Medal setGold (Medal m, int medalgold)
	{
		m.gold = medalgold;
		return m;
	}
	public static Medal setSilver (Medal m, int medalsilver)
	{
		m.silver = medalsilver;
		return m;
	}
	public static Medal setBronze (Medal m, int medalbronze)
	{
		m.bronze = medalbronze;
		return m;
	}
	public static Medal setTotal (Medal m, int mgold, int msilver, int mbronze)
	{
		m.total = mgold + msilver + mbronze;
		return m;
	}

	//adds the old and new number of medals for the day
	public static int medalTotal(int m1, int m2)
	{
		int newmedal;
		
		newmedal = m1 + m2;
		
		return newmedal;
	} //END medalTotal
	
	
	//given the gold, silver and bronze total, returns the total number of medals
	public static int calculateTotal(int g, int s, int b)
	{
		int totalMedal;
		
		totalMedal = g + s + b;
		
		return totalMedal;
	} //END calculateTotal

	
	//sets up an array that can store 16 medal data for every day of the olympics
	public static String[] initialmedals()
	{
		String [] medalsstored = {"","","","","","","","","","","","","","","",""};
		return medalsstored;

	}
	
	//Create and Fill an array of a given size with user input value
    public static String[] initArray(String[] m, String currentmedals, int num)
    {
		m[num]= currentmedals;
		return m;    //pass whole array back
	}

	//The day user picks to view medal scores for
    public static String search(int key, String[] m)
    {
        for (int i=0; i<m.length; i++)
        {
           if (i ==key)
           {
              return m[i];
           }
       }
	   return "Not Found";    //NOT FOUND
    } // END search
	
	
	//Prints out the whole of array list
	public static void printtable(String[] m)
	{
		for(int i = 0; i<m.length; i++)
		{
			print((i+1) + ". " + m[i]);
		}
	} 
	
	// method allows user to input integers and returns them
    public static int inputInt (String message)
    {
		return Integer.parseInt(inputString(message));

    } // END inputInt
	
	
	//method to allow user to input Strings and returns them
	public static String inputString(String message)
	{
		String answermedals;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		print(message);
		answermedals = input.nextLine();
		return answermedals;
		
	} //END inputString
	
	//method to print
	public static void print(String message)
	{
		System.out.println(message);
	}
	
	
} // END class olympics

//new class to define the variables in the Medal records
class Medal
{
	String country;
	int gold;
	int silver;
	int bronze;
	int total;
} //END class medal

