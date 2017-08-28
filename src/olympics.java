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
import java.io.*;

class olympics
{
    public static void main (String[] param)throws IOException{		
        question();
        System.exit(0);
		
    } // END main

//method asks user to input whether they want to see the medal score board
    public static void question () throws IOException
    {
        String askQuestion;
		boolean loop = true;
		Medal m1 = new Medal();
		Medal m2 = new Medal();
		Medal m3 = new Medal();
		Medal m4 = new Medal();
		String[] countries = {"Japan", "Canada", "Germany", "UK"};
		int[] total = {30,21,25,65};
		
		while(loop){
			askQuestion = (inputString("What country medals do you want to see/ update? (Japan, Canada, Germany or UK) or print medal table or exit").toLowerCase());
			
			if (askQuestion.equals("japan")){
				m1 = setCountry(m1, "Japan");
				medalTotal(m1, 6, 15, 9);
				currentMedals(m1, countries, total);
			}
			else if (askQuestion.equals("canada")){
				m2 = setCountry(m2, "Canada");
				medalTotal(m2, 9, 5, 7);
				currentMedals(m2, countries, total);
			}
			else if (askQuestion.equals("germany")){
				m3 = setCountry(m3, "Germany");
				medalTotal(m3, 11, 4, 10);
				currentMedals(m3, countries, total);
			}
			else if (askQuestion.equals("uk")){
				m4 = setCountry(m4, "Uk");
				medalTotal(m4, 23, 28, 14);
				currentMedals(m4, countries, total);
			} 
			else if(askQuestion.equals("medal table")){
				boolean sorted = true;
				while(sorted){
					sorted = false;
					for(int i=0; i<total.length-1; i++){
						if(total[i]>total[i+1]){
							int temp = total[i+1];
							String temp2 = countries[i+1];
							total[i+1]=total[i];
							countries[i+1]=countries[i];
							total[i]=temp;
							countries[i]=temp2;
							sorted = true;
						}
					}
				}
				for(int j=0; j<total.length; j++){
					System.out.format(countries[j] + ": %d%n", total[j]);
				}
			} else if(askQuestion.equals("exit")){
				print("Thanks for filling out the medal table");
				loop = false;
			}else{
				print("Please enter a country from the list or exit");
			}
		}		
    } // END question
    
	//fills in the new total medals
    public static void currentMedals(Medal m, String[] countries, int[] total){
    	for(int i=0; i<getTotal(m)[i]; i++){
    		if(getTotal(m)[i] != -1){
    			for(int j=0; j<countries.length; j++){
    				if(countries[j].equals(getCountry(m))){
    					total[j]=getTotal(m)[i];
    				}
    			}
    		}
    	}
    }
    
	//the method prints out the total amount of medals
	//method asks user to input whether they want to see the medal score board
    //then asks for the daily medals i a loop and asks which medal score they want to see
    public static void medalTotal (Medal m, int g, int s, int b) throws IOException{	
    	int[] gold = initialmedals();
		int[] silver = initialmedals();
		int[] bronze = initialmedals();
		int[] total = initialmedals();
    	
    	String load = inputString("Do you want to load file for " + getCountry(m) + "?");
    	if(load.equals("yes")){
    		BufferedReader inStream = new BufferedReader(new FileReader(getCountry(m)+".txt"));

            //the gold medal stored 
    		String line = inStream.readLine();
        	String[] split1 = line.split(" ");
        	//the silver medal stored 
    		line = inStream.readLine();
        	String[] split2 = line.split(" ");
        	//the bronze medal stored 
    		line = inStream.readLine();
        	String[] split3 = line.split(" ");
        	//the total medal stored 
    		line = inStream.readLine();
        	String[] split4 = line.split(" ");
            
        	for(int n=0; n<16; n++){
            	gold[n] = Integer.parseInt(split1[n]); 
            	silver[n] = Integer.parseInt(split2[n]); 
            	bronze[n] = Integer.parseInt(split3[n]); 
            	total[n] = Integer.parseInt(split4[n]); 
            }           
            inStream.close();
            m = setGold(m, gold);
	    	m = setSilver(m, silver);
	    	m = setBronze(m, bronze);
	    	m = setTotal(m, total);
    	}
		
		int start = findStart(m);
		//sets up an array and passes the medal data to be stored into the array
		if(start > 1){
			gold = getGold(m);
			silver = getSilver(m);
			bronze = getBronze(m);
			total = getTotal(m);
		} else {
			gold = initArray(gold, g, 0);
			silver = initArray(silver, s, 0);
			bronze = initArray(bronze, b, 0);
			total = initArray(total, gold[0]+silver[0]+bronze[0], 0);
			
	    	m = setGold(m, gold);
	    	m = setSilver(m, silver);
	    	m = setBronze(m, bronze);
	    	m = setTotal(m, total);
		}
    	
		String medals1 = "      G   S   B  Total";
        String medals2 = getCountry(m) + " " + getGold(m)[start-1] +"   "+ getSilver(m)[start-1] +"  "+ getBronze(m)[start-1] +"   "+ getTotal(m)[start-1];
		
		print("The current medal score for Day " + (start) + ":\n" + medals1 + "\r\n" + medals2);
		
		int counter = 16;
		
		for (int i = start; i <counter; i++){
			int addGold = inputInt("How many Gold's were won today?");
			int addSilver= inputInt("How many Silver's were won today?");
			int addBronze = inputInt("How many Bronze's were won today?");
			
			while(addGold<0){
				print("Please enter a number equal to 0 or  above for the Gold Medal's:");
				addGold = inputInt("How many Gold's were won today?");
			}
			while(addSilver<0){
				print("Please enter a number equal to 0 or  above for the Silver Medal's:");
				addSilver = inputInt("How many Silver's were won today?");
			}
			while(addBronze<0){
				print("Please enter a number equal to 0 or  above for the Bronze Medal's:");
				addBronze = inputInt("How many Bronze's were won today?");
			}
			
			gold[i] = medalAdd(gold[i-1], addGold);
			silver[i] = medalAdd(silver[i-1], addSilver);
			bronze[i] = medalAdd(bronze[i-1], addBronze);
			total[i] = calculateTotal(gold[i], silver[i], bronze[i]);
			medals2 = getCountry(m) + " "+ gold + "  " + silver + "   " + bronze + "   " + total;
			
			//update array
			m = setGold(m, gold);
			m = setSilver(m, silver);
			m = setBronze(m, bronze);
			m = setTotal(m, total);
			
			dailyMedals(m, i, medals1);
			
			int nextDay = i+2;
			int askLoop = (inputInt("\nDo you want to:\n1.Update medal table for day " + nextDay + "?\n2.See medal from past day\n3.Exit program\n4.Pick different country\n5. Save in file"));
			if (askLoop==1){
				counter++;
			} else if(askLoop==2){
				int day = inputInt("Would you like to see a 1.single day or 2.all days?");
				if (day==1){	
					int searchKey = (inputInt("What medals do you want to see out of the "+ counter + " days?")-1);		//day looked for
					search(searchKey, m, medals1);
				} else if (day==2){
					print("   " + medals1);
					printtable(m);
				} else{
					print("Please enter 1 or 2:");
				}
			} else if (askLoop==3){
				print("Bye!");
				System.exit(0);
			} else if(askLoop==4) {
				break;
			} else if(askLoop==5){
				save(m);
				break;
			}else{
				print("Please enter a number between 1-3 for the options:");
			}	
		} //END loop
        print("End of olympics reuslt: ");
        print("   " + medals1);
        printtable(m);
    } // END medalTotal
	
	//Getter methods for Medal records type
	public static String getCountry (Medal m){
		return m.country;
	}
	
	public static int[] getGold (Medal m) {
		return m.gold;
	}
	
	public static int[] getSilver (Medal m){
		return m.silver;
	}
	
	public static int[] getBronze (Medal m){
		return m.bronze;
	}
	
	public static int[] getTotal (Medal m){
		return  m.total;
	}
	
	//Setter methods for Medal record type
	public static Medal setCountry (Medal m, String medalcountry){
		m.country = medalcountry;
		return m;
	}
	public static Medal setGold (Medal m, int medalgold[]){
		m.gold = medalgold;
		return m;
	}
	public static Medal setSilver (Medal m, int medalsilver[]){
		m.silver = medalsilver;
		return m;
	}
	public static Medal setBronze (Medal m, int medalbronze[]){
		m.bronze = medalbronze;
		return m;
	}
	public static Medal setTotal (Medal m, int medaltotal[]){
		m.total = medaltotal;
		return m;
	}
	
	//finds the first empty slot in the array
	public static int findStart(Medal m){
		int day=1;
		try{
			for(int i=0; i<getTotal(m).length; i++){
				if(getTotal(m)[i] == -1){
					day=i;
					break;
				}
			}
		}catch(NullPointerException e){
			day=1;
		}
		return day;
	}

	//adds the old and new number of medals for the day
	public static int medalAdd(int m1, int m2){
		int newmedal;
		newmedal = m1 + m2;
		return newmedal;
	} //END medalTotal
	
	
	//given the gold, silver and bronze total, returns the total number of medals
	public static int calculateTotal(int g, int s, int b){
		int totalMedal;
		
		totalMedal = g + s + b;
		
		return totalMedal;
	} //END calculateTotal

	
	//sets up an array that can store 16 medal data for every day of the olympics
	public static int[] initialmedals(){
		int [] medalsstored = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		return medalsstored;

	}
	
	//Create and Fill an array of a given size with user input value
    public static int[] initArray(int[] m, int currentmedals, int num){
		m[num]= currentmedals;
		return m;    //pass whole array back
	}
    
    //view daily medals after update
    public static void dailyMedals(Medal m, int day, String medals1){
    	String individualmedals = (inputString("Would you like to see gold, silver or broze medals, or all of the medals").toLowerCase());		
		
    	while(!individualmedals.equals("gold") || !individualmedals.equals("silver") || !individualmedals.equals("bronze") || !individualmedals.equals("all")){
			if (individualmedals.equals("gold")){
				print("Gold: " + getGold(m)[day]);
				break;
			}
			else if (individualmedals.equals("silver")){
				print("Silver: " + getSilver(m)[day]);
				break;
			}
			else if (individualmedals.equals("bronze")){
				print("Bronze: " + getBronze(m)[day]);
				break;
			}
			else if (individualmedals.equals("all")){
				search(day, m, medals1);
				break;
			}
			else{
				print("Option invalid, please try again:");
				individualmedals = (inputString("Would you like to see gold, silver or broze medals, or all of the medals").toLowerCase());		
			}
		}
    }

	//The day user picks to view medal scores for
    public static void search(int key, Medal m, String medals){
        print(medals);
    	print(getCountry(m)+ " " +getGold(m)[key] +"  "+getSilver(m)[key]+"  "+getBronze(m)[key]+"  "+getTotal(m)[key]);
    } // END search
	
	
	//Prints out the whole of array list
	public static void printtable(Medal m){
		for(int i = 0; i<getGold(m).length; i++){
			if(getGold(m)[i] != -1){
				print("        " +(i+1)+". " +getGold(m)[i]+"  "+getSilver(m)[i]+"  "+getBronze(m)[i]+"  "+getTotal(m)[i]);
			}
		}
	} 
	
	//saves country in a file
	public static void save(Medal m) throws IOException{
		PrintWriter outputStream = new PrintWriter(new FileWriter(getCountry(m)+".txt"));
            
        for (int i=0; i<getGold(m).length; i++){
                outputStream.print(getGold(m)[i]+" ");
        }
        
        outputStream.println("");
        
        for (int i=0; i<getSilver(m).length; i++){
            outputStream.print(getSilver(m)[i]+" ");
        }
    
        outputStream.println("");
        
        for (int i=0; i<getBronze(m).length; i++){
            outputStream.print(getBronze(m)[i]+" ");
        }
    
        outputStream.println("");
        
        for (int i=0; i<getTotal(m).length; i++){
            outputStream.print(getTotal(m)[i]+" ");
        }
    
        outputStream.println("");
        outputStream.close();
	}
	
	// method allows user to input integers and returns them
    public static int inputInt (String message){
		return Integer.parseInt(inputString(message));
    } // END inputInt
	
	
	//method to allow user to input Strings and returns them
	public static String inputString(String message){
		String answermedals;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		print(message);
		answermedals = input.nextLine();
		return answermedals;
		
	} //END inputString
	
	//method to print
	public static void print(String message){
		System.out.println(message);
	}
	
	
} // END class Olympics

//new class to define the variables in the Medal records
class Medal
{
	String country;
	int gold[];
	int silver[];
	int bronze[];
	int total[];
} //END class medal

