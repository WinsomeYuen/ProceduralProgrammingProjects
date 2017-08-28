import java.util.*;

public class dinosaur {
	
	public static void main(String[] param){
		print("Welcome to the dinosaur pet game!");
		
		String name[] = new String[3];
		String species[] = new String[3];
		for(int i=0; i<3; i++){
			name[i] = inputString("\nPlease name your pet dino: ");
			species[i] = randSpecies();
		}
		
		int thirst[][] = new int[3][5];
		int hunger[][] = new int[3][5];
		int irratability[][] = new int[3][5];
		
		for(int i=0; i<3; i++){
			thirst[i][0] = randLevel();
			hunger[i][0] = randLevel();
			irratability[i][0] = randLevel();
		}
		
		Pet p = new Pet();
		p = setName(p, name);
		p = setSpecies(p, species);
		p = setThirst(p, thirst);
		p = setHunger(p, hunger);
		p = setIrratability(p, irratability);
		
		simulate(p);
	}
	
	public static void simulate(Pet p){
		int pick = inputInt("\nWhich pet do you want to play with? (1-3)")-1;
		int[][] emotionalstate = new int[3][3];
		
		startgame(p, emotionalstate, pick);
		
		print("\nThe species of your pet is : " + getSpecies(p)[pick]);
		
		boolean play = true;
		int timestep = 0;
		while(play){
			play = false;
			for(int n=0; n<3; n++) {
				print(getName(p)[pick] + "'s thirst level is " + emotionalstate[n][0] + "/10 and hunger level is " + 
						emotionalstate[n][1] + "/10 and irratability level is " + emotionalstate[n][2] + "/10");
	
				
				int level = angerLevel(emotionalstate[n][0] , emotionalstate[n][1]);
				if(level == 1){
					timestep = dead(p, pick);
					play = true;
					break;
				}
				
				int input = inputInt("Pick a option to look after pet: \n1. Feed nibbles \n2. Sing a song \n3. Give water");
				int nextRound = n+1;
				if(nextRound == 3){
					nextRound-=1;
				}
				while(input<1 | input>3){
					input = inputInt("Please pick an option between 1-3");
				}
				if(input == 1) {
					int state = stateofmind(emotionalstate[n][1]);
					emotionalstate[nextRound][1] = emotionalstate[n][1]-state;
					emotionalstate[nextRound][2] = emotionalstate[n][2];
					emotionalstate[nextRound][0] = emotionalstate[n][0];
					print(getName(p)[pick] + "'s hunger decreased to " + emotionalstate[nextRound][1] + "/10");
				}else if(input == 2) {
					int state = stateofmind(emotionalstate[n][2]);
					emotionalstate[nextRound][2] = emotionalstate[n][2]-state;
					emotionalstate[nextRound][1] = emotionalstate[n][1];
					emotionalstate[nextRound][0] = emotionalstate[n][0];
					print(getName(p)[pick] + "'s irratability decreased to " + emotionalstate[nextRound][2] + "/10");
				}else if(input == 3){
					int state = stateofmind(emotionalstate[n][0]);
					emotionalstate[nextRound][0] = emotionalstate[n][0]-state;
					emotionalstate[nextRound][2] = emotionalstate[n][2];
					emotionalstate[nextRound][1] = emotionalstate[n][1];
					print(getName(p)[pick] + "'s thirst decreased to " + emotionalstate[nextRound][0] + "/10");
				}
			}
			timestep+=1;
			
			String cont = inputString("Do you want to continue playing with your pet?").toLowerCase();
			if(cont.equals("yes")) {
				play = true;
				getThirst(p)[pick][timestep] = randLevel();
				p = setThirst(p, getThirst(p));
				getHunger(p)[pick][timestep] = randLevel();
				p = setHunger(p, getHunger(p));
				getIrratability(p)[pick][timestep] = randLevel();
				p = setIrratability(p, getIrratability(p)); 
				setStep(p, emotionalstate, timestep, pick);
			}
		}
		print("Thankyou for playing! :)");
	}
	
	//initialises emotional state of pet
	public static void startgame(Pet p, int emotionalstate[][], int choice){
		int startlevel = angerLevel(getThirst(p)[choice][0], getHunger(p)[choice][0]);
		while(startlevel == 1) {
			getThirst(p)[choice][0] = randLevel();
			p = setThirst(p, getThirst(p));
			getHunger(p)[choice][0] = randLevel();
			p = setHunger(p, getHunger(p));
			getIrratability(p)[choice][0] = randLevel();
			p = setIrratability(p, getIrratability(p)); 
			startlevel = angerLevel(getThirst(p)[choice][0], getHunger(p)[choice][0]);
		}
		setStep(p, emotionalstate, 0, choice);
	}
	
	public static void setStep(Pet p, int emotionalstate[][], int timestep, int choice) {
		emotionalstate[0][0] = getThirst(p)[choice][timestep];
		emotionalstate[0][1] = getHunger(p)[choice][timestep];
		emotionalstate[0][2] = getIrratability(p)[choice][timestep];
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
	
	//pet died
	public static int dead(Pet p, int choice){
		print("Sorry your pet has been put down becuase it became ill :(");
		String reload = inputString("Do you want to load your pet from a time step? ").toLowerCase();
		int timestep = -1;
		boolean load = true;
		while(load){
			if(!reload.equals("yes") || !reload.equals("no")) {
				print("Please enter yes or no");
			} else if(reload.equals("no")) {
				System.exit(0);
			} else {
				timestep = inputInt("Please pick a time step(1-5): ")-1;
				load = false;
			}
		}
		return timestep;
	}
	
	//getter methods for Pet record type
	public static String[] getName(Pet p){
		return p.name;
	}
	public static String[] getSpecies(Pet p){
		return  p.species;
	}
	public static int[][] getThirst(Pet p){
		return  p.thirst;
	}
	public static int[][] getHunger(Pet p){
		return  p.hunger;
	}
	public static int[][] getIrratability(Pet p){
		return  p.irratability;
	}
		
	//setter methods for pet record type
	public static Pet setName(Pet p, String[] petname){
		p.name = petname;
		return p;
	}
	public static Pet setSpecies(Pet p, String[] petspecies){
		p.species = petspecies;
		return p;
	}
	public static Pet setThirst(Pet p, int[][] petthirst){
		p.thirst = petthirst;
		return p;
	}
	public static Pet setHunger(Pet p, int[][] pethunger){
		p.hunger = pethunger;
		return p;
	}
	public static Pet setIrratability(Pet p, int[][] petirratability){
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
	String name[];
	String species[];
	int hunger[][];
	int thirst[][];
	int irratability[][];
}
