import java.util.*;

public class music{
	
	//initialises record
	public static void main (String[] param){
		print("Welcome to the kpop music quiz!");
		int team = inputInt("How many players are on your team?");
		Quiz q = new Quiz();
		String[] questions = {"What is the oldest member of BTS called? (full name)",
							"Which group sang the song *Hola Hola*? ",
							"When was the group got7 formed"};
		String[] answers = {"kim seokjin",
							"kard",
							"2014"};
		
		q = setQuestion(q, questions);
		q = setAnswer(q, answers);
		
		start(q, team);
	}
	
	//starts quiz, checks answers and prints out score totals
	public static void start(Quiz a, int players){
		int score = 0;
		int members[][] = new int[players][3];
		String[] questions = getQuestion(a);
		String[] answers = getAnswer(a);
		int[] answered = new int[questions.length];
		
		for(int j=0; j<questions.length; j++) {
			print("\nRound " + (j+1));
			int count = 0;
			
			for(int i=0; i<players; i++) {
				print("Player " + (i+1) + ":");
				String question = inputString(questions[j]).toLowerCase();
		
				if(question.equals(answers[j])){
					print("Congratulations, your answer is correct! :) \n");
					int points = roll();
					score += points;
					members[i][j] =  points;
					count+=1;
				} else {
					print("Sorry, your answer was wrong, it was " + answers[j] + " :( \n");
				}
				answered[j] = count;
			}
			print("\nTotal score: " + score);
			for(int n=0; n<members.length; n++){
				int total = 0;
				for(int m=0; m<members[n].length; m++){
					total+=members[n][m];
				}
				System.out.println(String.format("%-10s %s", "Player "+(n+1)+" score:", total));
			}
			scores(members, score);
			
		}
		a = setAnswerCorrect(a, answered);
		String answer = inputString("Do you want to see how well you did on each question?(yes or no)").toLowerCase();
		
		if(answer.equals("yes")) {
			sortRounds(members,a);
		}
		print("\nYour final score is: " + score);
		print("Thankyou for playing the quiz!");
	}
	
	//rolls dice to decide score
	public static int roll() {
		Random rand = new Random();
		int  n = rand.nextInt(6) + 1;
		
		if(n == 6) {
			return 6;
		}
		return 3;
	}

	//getter methods for Quiz record type
	public static String[] getQuestion (Quiz q){
		return q.question;
	}
	public static String[] getAnswer (Quiz q){
		return  q.answer;
	}
	public static int[] getAnswerCorrect (Quiz q){
		return  q.answercorrect;
	}
	
	//setter methods for Quiz record type
	public static Quiz setQuestion(Quiz q, String[] quizquestion){
		q.question = quizquestion;
		return q;
	}
	public static Quiz setAnswer (Quiz q, String[] quizanswer){
		q.answer = quizanswer;
		return q;
	}
	public static Quiz setAnswerCorrect (Quiz q, int[] quizanswercorrect){
		q.answercorrect = quizanswercorrect;
		return q;
	}
	
	//prints out players scores for each round
	public static void scores(int[][] members, int score){
		int choice = 0;
		while(choice!=2) {
			choice = inputInt("\nDo you want to.... : \n1. See player's score for each round "
						+ "\n2. Continue quiz ");
			if(choice == 1){
				int player = inputInt("What player do you want to see?");
				
				if(player<1 | player>members.length){
					print("Sorry there is no such player.");
				}else{
					print("Score for player" + player + "\n");
					player-=1;
					
					System.out.println(String.format("%-20s %s" , "Round", "Score" ));
					for(int i=0; i<members[player].length; i++){
						System.out.println(String.format("%-20s %s" , (i+1), members[player][i]));
					}
				}
			} else if(choice>2 | choice<1) {
				print("Please enter option 1 or 2 on the list.");
			}
		}
	}
	
	//questions in order of how easy they were
	public static void sortRounds(int[][] members, Quiz q){
		//bubble sort
		int[] unsorted = getAnswerCorrect(q);
		String[] question = getQuestion(q);
		boolean swap = true;
		while(swap){
			swap = false;
			for(int i=0;i<question.length-1; i++){
				if(unsorted[i]>unsorted[i+1]){
					int temp = unsorted[i+1];
					String temp2 = question[i+1];
					unsorted[i+1] = unsorted[i];
					question[i+1] = question[i];
					unsorted[i] = temp;
					question[i] = temp2;
					swap = true;
				}
			}
		}
		System.out.println(String.format("%-60s %s" , "Question", "Number correct"));
		for(int j=0; j<question.length; j++){
			System.out.println(String.format("%-60s %s" , question[j], unsorted[j]));
		}
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

class Quiz{
	String question[];
	String answer[];
	int answercorrect[];
}
