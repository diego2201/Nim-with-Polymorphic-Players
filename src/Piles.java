import java.util.*;

/**
 * This class creates and is meant to represent the piles in the game of Nim. The pile sizes are
 * encapsulated and will check if moves are valid, if not an Exception is thrown.
 * 
 * @author Diego Cifuentes
 * @version 1
 */
public class Piles 
{
	/**
	 * Where the new initialized Piles object will be stored.
	 */
	private int[] sizes;
	
	/**
	 * Initializes a new Piles object using the passed values and stores in the instance variable sizes. 
	 * Checks if the array is valid, if not an exception is thrown.
	 * 
	 * @param initSizes		The number(s) to be added to the new Piles object
	 */
	public Piles(int... initSizes) 
	{
		//Checks to see if the array reference is null or if the length is 0
			//if so then an IllegalArgumentException is thrown.
		if(initSizes == null || initSizes.length == 0)
		{
			throw new IllegalArgumentException();
		}
		
		//For loop to go through the array, if an element of said array is <= then an Exception is thrown.
		for(int i : initSizes) 
		{
			if(i <= 0)
			{
				throw new IllegalArgumentException();
			}
		}
		
		//If prior conditions are passed then a copy of the array is made and stored in sizes.
		sizes = Arrays.copyOf(initSizes, initSizes.length);
	}

	/**
	 * Returns an array with a copy of the current pile sizes.
	 * 
	 * @return An array with a copy of the current pile sizes
	 */
	public int[] getSizes() 
	{
		int[] sizeOf = Arrays.copyOf(sizes, sizes.length);
		
		return sizeOf;
	}

	/**
	 * Checks if any of the given conditions are true, if so an IllegalMoveException is thrown. If all 
	 * checks are false, then remove objects from one of the piles, dictated by the passed int[] move array.
	 * 
	 * @param move		this array contains the index (pile) of which the pile will be removed, and how much to be removed [pile, amount].
	 * @throws IllegalMoveException	if certain conditions are not met, therefore making the move illegal.
	 */
	public void removeObjects(int[] move) throws IllegalMoveException 
	{
		//Check if the array is null
		if(move == null)
		{
			throw new IllegalMoveException("null move");
		}
		
		//Check if the array has a length other than 2
		if(move.length > 2 || move.length < 2)
		{
			throw new IllegalMoveException("Invalid length: " + move.length);
		}
		
		//Check if the pile index is out of bounds
		if((move[0] >= sizes.length || move[0] < 0))
		{
			throw new IllegalMoveException("Index out of bounds: " + move[0]);
		}
		
		//Checks if the pile has a size of 0
		if(sizes[move[0]] == 0)
		{
			throw new IllegalMoveException("Pile " + move[0] + " is empty.");
		}
		
		//Checks if the object number is less <= 0
		if(move[1] <= 0)
		{
			throw new IllegalMoveException("Nonpositive object number: " 
					+ move[1]);
		}
		
		//Checks if the object number is greater than the pile size
		if(move[1] > sizes[move[0]])
		{
			throw new IllegalMoveException("Object number greater than pile size: " 
					+ move[1] + " > " + sizes[move[0]]);
		}
	
		//Removes the a given amount from the object from a given index.
		sizes[move[0]] = sizes[move[0]]- move[1];
	}
	
	/**
	 * Checks if the piles are empty, if all are empty true is returned, else false
	 * 
	 * @return true if all the piles are empty, otherwise false is returned
	 */
	public boolean isEmpty() 
	{
		//For loop to go through the sizes array
		for(int i = 0; i < sizes.length; i++)
		{
			//Checks if there is something in the given index
			if(sizes[i] != 0)
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Checks for piles that are not empty, if a pile is not empty then it is added to the ArrayList and returned.
	 * 
	 * @return An ArrayList with the indices of piles that are not empty
	 */
	public ArrayList<Integer> nonEmptyPiles()
	{
		//ArrayList where the indices will be stored
		ArrayList<Integer> notEmpty = new ArrayList<Integer>();
		//For loop to go through the sizes array to check for non empty indices
		for(int i = 0; i < sizes.length; i++)
		{
			if(sizes[i] != 0)
			{
				notEmpty.add(i);
			}
		}
		
		return notEmpty;
	}

}
