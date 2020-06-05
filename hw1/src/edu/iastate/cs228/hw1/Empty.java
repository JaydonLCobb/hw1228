package edu.iastate.cs228.hw1;

/**
 *  
 * @author
 *
 */

/** 
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	public Empty (Plain p, int r, int c) 
	{
		plain = p;
		row = r;
		column = c;
	}
	
	public State who()
	{

		return State.EMPTY;
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or remain empty. 
	 * @param pNew     plain of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(Plain pNew)
	{
		// TODO 
		// 
		// See Living.java for an outline of the function. 
		// See the project description for corresponding survival rules.
		int[] newPop = new int[NUM_LIFE_FORMS];
		census(newPop);

		if (newPop[RABBIT] >= 2) {
			return new Rabbit(pNew, row, column, 0);
		} else if (newPop[FOX] >= 2) {
				return new Fox(pNew, row, column, 0);
		}  else if (newPop[BADGER] >= 2) {
			return new Badger(pNew, row, column, 0);
		} else if (newPop[GRASS] >= 1) {
			return new Grass(pNew,row,column);
		} else {
			return new Empty(pNew, row, column);
		}




	}
}
