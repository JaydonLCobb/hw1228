package edu.iastate.cs228.hw1;

/**
 *  
 * @author
 *
 */

/**
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Badger (Plain p, int r, int c, int a) 
	{
		plain = p;
		row = r;
		column = c;
		age = a;
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		return State.BADGER;
	}
	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a badger. 
		int[] newPop = new int[NUM_LIFE_FORMS];
		census(newPop);

		if (age == BADGER_MAX_AGE) {
			return new Empty(pNew, row, column);
		} else if (newPop[FOX] < newPop[BADGER] ) {
			return new Badger(pNew,row,column,0);
		} else if (newPop[RABBIT] < (newPop[BADGER] + newPop[FOX])) {
			return new Empty(pNew,row,column);
		} else {
			return new Badger(pNew, row, column, age+1);
		}

	}
}
