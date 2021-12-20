
/**
 * This class is a custom exception class, used to help throw and catch exceptions and to recover from these.
 * This class is thrown by the Piles method removeObjetcs if a move is deemed illegal.
 * This class also extends the Exception class
 * 
 * @author Diego Cifuentes
 * @version 1
 */
public class IllegalMoveException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * The constructor, takes a String argument about the move and then passes it to the 
	 * one argument parent constructor.
	 * 
	 * @param moveInfo 		The information about the move to be passed to the parent class
	 */
	public IllegalMoveException(String moveInfo)
	{
		super(moveInfo);
	}
}
