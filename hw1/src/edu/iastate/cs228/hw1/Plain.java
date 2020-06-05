package edu.iastate.cs228.hw1;
/**
 *  
 * @author
 *
 */

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain {
	private int width; // grid size: width X width
	public Living[][] grid;

	/**
	 * Default constructor reads from a file
	 */
	public Plain(String inputFileName) throws FileNotFoundException {
		// TODO
		// 
		// Assumption: The input file is in correct format. 
		// 
		// You may create the grid plain in the following steps: 
		// 
		// 1) Reads the first line to determine the width of the grid.
		// 
		// 2) Creates a grid object. 
		// 
		// 3) Fills in the grid according to the input file. 
		// 
		// Be sure to close the input file when you are done.
		File inFile = new File(inputFileName);
		Scanner sc = new Scanner(inFile);

		String count;
		int rowCount = 1;
		width = 0;
		int age;

		// Width protocol + check has next line
		while (sc.nextLine() != null) {
			width++;
			sc.nextLine();
		}

		//grid creation
		grid = new Living[width][width];

		//
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				count = sc.next();

				if (count.charAt(0) == 'B') {

					age = Integer.parseInt(String.valueOf(count.charAt(1)));

					Badger b = new Badger(this, i, j, age);
					grid[i][j] = b;
				}
				if (count.charAt(0) == 'F') {

					age = Integer.parseInt(String.valueOf(count.charAt(1)));

					Fox f = new Fox(this, i, j, age);
					grid[i][j] = f;
				}
				if (count.charAt(0) == 'R') {

					age = Integer.parseInt(String.valueOf(count.charAt(1)));

					Rabbit r = new Rabbit(this, i, j, age);
					grid[i][j] = r;
				}
				if (count.charAt(0) == 'G') {
					Grass g = new Grass(this, i, j);
					grid[i][j] = g;
				}
				if (count.charAt(0) == 'E') {
					Empty e = new Empty(this, i, j);
					grid[i][j] = e;
				}
				count = String.valueOf(Integer.parseInt(count) + 1);
			}
		}
		sc.close();
	}

	/**
	 * Constructor that builds a w x w grid without initializing it.
	 *
	 * @param w the grid
	 */
	public Plain(int w) {
		width = w;
		Living[][] grid = new Living[width][width];
	}


	public int getWidth() {
		return width;
	}

	/**
	 * Initialize the plain by randomly assigning to every square of the grid
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.
	 * <p>
	 * Every animal starts at age 0.
	 */
	public void randomInit() {
		Random random = new Random();

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {

				int g = random.nextInt(Living.NUM_LIFE_FORMS);

				switch (g) {
					case 0:
						grid[i][j] = new Badger(this, i, j, 0);
						break;
					case 1:
						grid[i][j] = new Empty(this, i, j);
						break;
					case 2:
						grid[i][j] = new Grass(this, i, j);
						break;
					case 3:
						grid[i][j] = new Fox(this, i, j, 0);
						break;
					case 4:
						grid[i][j] = new Rabbit(this, i, j, 0);
						break;
				}
			}
		}

	}


	/**
	 * Output the plain grid. For each square, output the first letter of the living form
	 * occupying the square. If the living form is an animal, then output the age of the animal
	 * followed by a blank space; otherwise, output two blanks.
	 */
	public String toString() {
		String newString = "";

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {

				if (grid[i][j].who() == State.BADGER) {
					newString += "B" + ((Animal) grid[i][j]).myAge() + " ";
				}
				if (grid[i][j].who() == State.FOX) {
					newString += "F" + ((Animal) grid[i][j]).myAge() + " ";
				}
				if (grid[i][j].who() == State.RABBIT) {
					newString += "R" + ((Animal) grid[i][j]).myAge() + " ";
				}
				if (grid[i][j].who() == State.GRASS) {
					newString += "G  ";
				}
				if (grid[i][j].who() == State.EMPTY) {
					newString += "E  ";
				}
			}
			newString += "\n";
		}
		return newString;
	}


	/**
	 * Write the plain grid to an output file.  Also useful for saving a randomly
	 * generated plain for debugging purpose.
	 *
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException {
		// TODO 
		// 
		// 1. Open the file. 
		// 
		// 2. Write to the file. The five life forms are represented by characters 
		//    B, E, F, G, R. Leave one blank space in between. Examples are given in
		//    the project description. 
		// 
		// 3. Close the file
		File newInFile = new File(outputFileName);
		PrintWriter newPrinter = new PrintWriter(newInFile);

		newPrinter.println(this.toString());
		newPrinter.close();

	}
}