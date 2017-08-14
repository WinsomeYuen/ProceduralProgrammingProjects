import java.util.*;

public class dinosaur {
	
	public static void main(String[] param){
		print("Welcome to the dinosaur pet game!");
		String name = inputString("\nPlease name your pet dino: ");
		
		Pet p = new Pet();
		p = setName(p, name);
		p = setSpecies(p, randSpecies());
		p = setThirst(p, randLevel());
		p = setHunger(p, randLevel());
		p = setIrratability(p, randLevel());
		
		simulate(p);
	}
	
	public static void simulate(Pet p){
		//int[] emotionalstate = new int[3];
		print("\nThe species of your pet is : " + getSpecies(p));
		
		boolean play = true;
		while(play){
			play = false;
			print(getName(p) + "'s thirst level is " + getThirst(p) + "/10 and hunger level is " + 
					getHunger(p) + "/10 and irratability level is " + getIrratability(p) + "/10");
			
			int level = angerLevel(getThirst(p), getHunger(p));
			if(level == 1){
				print("Sorry your pet has been put down becuase it became ill :(");
				System.exit(0);
			}
			
			int input = inputInt("Pick a option to look after pet: \n1. Feed nibbles \n2. Sing a song \n3. Give water");
			while(input<1 | input>3){
				if(input == 1) {
					int state = stateofmind(getHunger(p));
					p = setHunger(p, getHunger(p)-state);
					print(getName(p) + "'s hunger decreased by " + getHunger(p));
				}else if(input == 2) {
					int state = stateofmind(getIrratability(p));
					p = setIrratability(p, getIrratability(p)-state);
					print(getName(p) + "'s irratability decreased by " + getIrratability(p));
				}else if(input == 3){
					int state = stateofmind(getThirst(p));
					p = setThirst(p, getThirst(p)-state);
					print(getName(p) + "'s thirst decreased by " + getThirst(p));
				}else{
					print("Please pick an option between 1-3");
				}
			}
			String cont = inputString("Do you want to continue playing with your pet?").toLowerCase();
			if(cont.equals("yes")) {
				play = true;
				p = setThirst(p, randLevel());
				p = setHunger(p, randLevel());
				p = setIrratability(p, randLevel());
			}
		}
		print("Thankyou for playing! :)");
	}
	
	//changes pet state of mind depending on option
	public static int stateofmind(int num){
		Random rand = new Random();
		int n = rand.nextInt(num) + 1;
		return n;
	}
	
	//picks random species from the list
	public static String randSpecies(){
		String[] species = {"Tyrannosaurus Rex","Velociraptor", "Stegosaurus", "Triceratops", "Dilophosaurus"};
		Random random = new Random();
		int s = random.nextInt(species.length);
		return species[s];
	}
	
	//generate random thirst and hunger level
	public static int randLevel(){
		Random rand = new Random();
		int  n = rand.nextInt(10) + 1;
		return n;
	}
	
	//works out anger level of pet
	public static int angerLevel(int t, int h){
		int combo = t + h;
		
		if(combo <= 10){
			print("Mood: Serene");
		} else if(combo <=15){
			print("Mood: grouchy");
		} else{
			print("Mood: Dangerous");
			return 1;
		}
		return 0;
	}
	
	//getter methods for Pet record type
	public static String getName(Pet p){
		return p.name;
	}
	public static String getSpecies(Pet p){
		return  p.species;
	}
	public static int getThirst(Pet p){
		return  p.thirst;
	}
	public static int getHunger(Pet p){
		return  p.hunger;
	}
	public static int getIrratability(Pet p){
		return  p.irratability;
	}
		
	//setter methods for pet record type
	public static Pet setName(Pet p, String petname){
		p.name = petname;
		return p;
	}
	public static Pet setSpecies(Pet p, String petspecies){
		p.species = petspecies;
		return p;
	}
	public static Pet setThirst(Pet p, int petthirst){
		p.thirst = petthirst;
		return p;
	}
	public static Pet setHunger(Pet p, int pethunger){
		p.hunger = pethunger;
		return p;
	}
	public static Pet setIrratability(Pet p, int petirratability){
		p.irratability = petirratability;
		return p;
	}
	
	// method allows user to input integers and returns them
    public static int inputInt (String message){
		return Integer.parseInt(inputString(message));
    } // END inputInt
	
	//method to allow user to input Strings and returns them
	public static String inputString(String message){
		String answer;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
			
		print(message);
		answer = input.nextLine();
		return answer;
	} //END inputString
	
	//method to print
	public static void print(String message){
		System.out.println(message);
	}
}

class Pet{
	String name;
	String species;
	int hunger;
	int thirst;
	int irratability;
}
