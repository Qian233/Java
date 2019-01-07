//Xueqian Zhang 
//ID: xueqianz
package exam1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoreBoard {
	String[] players;
	int[][] playerScores;
	Scanner input = new Scanner(System.in);
	
	//do not change this method
	public static void main(String[] args){
		ScoreBoard scoreBoard = new ScoreBoard();
		scoreBoard.readScores("GameScores.txt");
		scoreBoard.showScores(scoreBoard.getMenuChoice());
	}
	
	//do not change this method
	int getMenuChoice() {
		System.out.println("*** Score Board ***");
		System.out.println("1. List all the scores of a player");
		System.out.println("2. Find a player's average score");
		System.out.println("3. Find all highest scores"); 
		System.out.println("4. Exit");
		int choice = input.nextInt();
		input.nextLine();
		return choice;
	}
	
	void readScores(String filename){
		Scanner fileInput = null;
		try {
			fileInput = new Scanner (new File(filename));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		StringBuilder fileContent = new StringBuilder();
		while (fileInput.hasNextLine()) fileContent.append(fileInput.nextLine() + "\n");
		
		String[] words = fileContent.toString().split("\n");
		
		String[] data = fileContent.toString().split("\n");
		playerScores = new int[data.length][];
		players = new String[data.length];
		for(int i= 0; i< data.length; i++) {
			players[i] = data[i].toString().split(":")[0];
			String[] score = data[i].toString().split(":")[1].split(",");
			int[] scoreNum = new int[score.length];
			for(int j = 0; j < score.length; j++) {
				scoreNum[j] = Integer.parseInt(score[j].trim());
			}
			playerScores[i] = scoreNum;
		}
		
		//write your code here
	}
	
	void showScores(int choice) {
		//write your code here
		if(choice == 1) {
			System.out.println("Enter player's name");
			Scanner input = new Scanner(System.in);
			String playname = input.nextLine();
			System.out.println(playname+ "'s scores");
			for(int i = 0; i < this.findPlayerScores(playname).length; i++) {
				System.out.println(this.findPlayerScores(playname)[i]);
			}
			
		}
		if(choice == 2) {
			System.out.println("Enter player's name");
			Scanner input2 = new Scanner(System.in);
			String playname2 = input2.nextLine();
			System.out.println(playname2 + "'s average score is");
			System.out.println(this.findPlayerAverageScore(playname2));
		}
		if(choice == 3) {
			for(int i = 0; i < players.length; i++) {
				System.out.println(players[i] + "'s highest score is " + this.findAllHighestScores()[i]);
			}
		}
		if(choice == 4) {
			System.out.println("Good bye!");
		}
		
	}

	int[] findPlayerScores(String name) {
		//write your code here
		boolean found = false;
		for(int i =0; i < players.length; i++) {
			if(players[i].toLowerCase().equals(name.toLowerCase())){
				found = true;
				return playerScores[i];
			}
		}
		return null;
	}
	
	float findPlayerAverageScore(String name) {
		//write your code here
		int[] playerSc = this.findPlayerScores(name);
		if(playerSc != null) {
			float sum = 0;
			for(int i=0; i< playerSc.length; i++) {
				sum = sum + playerSc[i];
			}
			float ave = sum/playerSc.length;
			return ave;
		}
		else {
			return 0;
		}
	}
	
	int[] findAllHighestScores() {
		//write your code here
		int[] high = new int[playerScores.length];
		for(int i = 0; i < playerScores.length; i++) {
			int max = playerScores[i][0];
			for(int j = 0; j < playerScores[i].length; j++) {
				if(playerScores[i][j] > max) {
					max = playerScores[i][j];
				}
			}
			high[i] = max;
		}
		 
		
		return high;
	}
}
