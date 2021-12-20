import java.util.*;

/**
 * This class is subclass of the Player class that gets it's moves from the input in the console
 * 
 * @author TA's
 * @updated Diego Cifuentes
 * @version 1
 */
public class HumanPlayer extends Player 
{

	private Scanner keyboard;
	
	/**
	 * Initializes the HumanPlayer object
	 * @param name			The name of the player
	 * @param keyboard		Where the input of the player will be read in
	 */
	public HumanPlayer(String name, Scanner keyboard) 
	{
		super(name);
		this.keyboard = keyboard;
	}
	
	/**
	 * Gets the move 
	 * 
	 * @param pileSizes		the pile size and the amount of the Pile object
	 */
	@Override
	public int[] getMove(int[] pileSizes) 
	{
		System.out.println("Piles: " + Arrays.toString(pileSizes));
		int pileIdx = getUserInput("Enter pile index: ");
		int numObjects = getUserInput("Enter object number: ");
		return new int[] {pileIdx, numObjects};
	}

	/**
	 * Reads and returns an input from the user (if it is an integer), if the input is not an int
	 * or can't be read in them the prompt is reprinted out and we continue to wait for a valid input
	 * 
	 * @param prompt	The String prompt to be printed out to the user
	 * @return the integer entered by the HumanPlayer
	 */
	public int getUserInput(String prompt) 
	{
		//Help from Office Hours
		//Help from Discord Discussion
		int input = 0;
		boolean flag = true;
		
		while(flag)
		{
			try
			{
				System.out.print(prompt);
				
				input = keyboard.nextInt();
				flag = false;
				return input;	
			}
			catch(InputMismatchException e)
			{
				keyboard.nextLine();
				flag = true;
			}	
		}
		return input;
	}
	
	/**
	 * Notifies the player if an IllegalMove is committed
	 */
	@Override
	public void notifyIllegalMove(String moveInfo) 
	{
		System.out.println(moveInfo);
		System.out.println();
	}
	
	/**
	 * Notifies the player that it is their opponent's move
	 */
	@Override
	public void notifyOpponentMove(String name, int[] move) 
	{
		String notification = name + " removed " + move[1] + " from pile " 
				+ move[0] + ".";
		System.out.println(notification);
		System.out.println();
	}
	
	/**
	 * Notifies the player of their win
	 */
	@Override
	public void notifyWin() 
	{
		System.out.println("Nice job, " + getName() + ". You win!");
	}
	
	/**
	 * Notifies the player of their loss
	 */
	@Override
	public void notifyLose() 
	{
		System.out.println("Sorry, " + getName() + ". You lose :(");
	}
}
