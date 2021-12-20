import java.util.*;

/**
 * This class is a subclass of the Player class that makes random choices using a random number generator.
 * 
 * @author Diego Cifuentes
 * @version 1
 */
public class RandomPlayer extends Player
{
	/** The Random object */
	private Random generator;
	
	/**
	 * Passes the given name to the parent constructor, and then initializes the generator 
	 * variable by calling new Random()
	 * 
	 * @param name		The name to be passed to the parent constructor
	 */
	public RandomPlayer(String name)
	{
		super(name);
		generator = new Random();
	}
	
	/**
	 * Returns a legal move based on the current size of the piles, by first finding out the indices with the
	 * nonempty piles. Depending on this it will return a random move that will leave the HumanPlayer with no other option
	 * but to remove the last element from the last pile and losing.
	 */
	@Override
	public int[] getMove(int[] pileSizes)
	{
		//Copy and pasted code from nonEmptyPiles from Piles class, modified to fit this case
		ArrayList<Integer> notEmpty = new ArrayList<Integer>();
		
		//Help from Discord Discussion
		//Creates a copy of the pileSize array which will come in handy later on
		int[] copyOfPiles = Arrays.copyOf(pileSizes, pileSizes.length);
		for(int i = 0; i < pileSizes.length; i++)
		{
			//Checks to see if the pile is not empty
			if(pileSizes[i] != 0)
			{
				//If empty then add to the notEmpty ArrayList
				notEmpty.add(i);
			}
		}
		
		//Checks conditions to decide to return the (pileSize - 1) as dictated in the README
		if(notEmpty.size() == 1 && copyOfPiles[notEmpty.get(0)] > 1) 
		{
			int[] newMove = new int[] {0, copyOfPiles[notEmpty.get(0)] - 1}; 
			return newMove;
		}
		//Help from Discord Discussion
		//Help from Office Hours
		else
		{
			int randomIndex = generator.nextInt(notEmpty.size());
			int i = notEmpty.get(randomIndex);
			int randomBound = pileSizes[i];
			int randomNumber = generator.nextInt(randomBound) + 1;
			int[] newMove = {i, randomNumber};
			
			return newMove;
		}
	}
}