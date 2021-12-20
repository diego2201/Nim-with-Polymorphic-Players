
/**
 * This class uses both the Piles and the various Player classes to simulate the Nims game
 * 
 * @author TA's
 * @updated Diego Cifuentes
 * @version 1
 */
public class Nim 
{
	private Player currentPlayer;
	private Player waitingPlayer;
	private Piles piles;
	private Player winner = null;
	private Player loser = null;

	/**
	 * Initalizes a new game of Nim
	 * 
	 * @param p1			Player 1
	 * @param p2			Player 2
	 * @param initSizes		The size of the piles and amount allocated in each
	 */
	public Nim(Player p1, Player p2, int[] initSizes) 
	{
		currentPlayer = p1;
		waitingPlayer = p2;
		piles = new Piles(initSizes);		
	}

	/**
	 * Returns the current player
	 * @return the current player
	 */
	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}

	/**
	 * Returns the waiting player (i.e. the non active player)
	 * @return the waiting player
	 */
	public Player getWaitingPlayer() 
	{
		return waitingPlayer;
	}

	/**
	 * Gets the size of the piles
	 * @return the size of the piles
	 */
	public int[] getPileSizes() 
	{
		return piles.getSizes();
	}

	/**
	 * Gets the winner of the game
	 * @return the winner of the game
	 */
	public Player getWinner() 
	{
		return winner;
	}

	/**
	 * Gets the loser of the game
	 * @return the loser of the game
	 */
	public Player getLoser() 
	{
		return loser;
	}

	/**
	 * Gets the next move from the current player and then applies that move to the corresponding piles
	 * However, if the move is illegal then the current play is notified and will have to enter another move
	 * Repeats until a valid move is entered.
	 */
	public void takeTurn()
	{
		//Help from Discord Discussion 
		boolean flag = false;
		while(flag == false)
		{
			try
			{
				int[] move = currentPlayer.getMove(getPileSizes());

				piles.removeObjects(move);
				waitingPlayer.notifyOpponentMove(currentPlayer.getName(), move);
				flag = true;
			}
			catch(IllegalMoveException e)
			{
				currentPlayer.notifyIllegalMove(e.getMessage());
			}
		}
	}

	/**
	 * Checks if piles is empty, if so then game is over, and assigned players to winner or loser
	 */
	public void checkGameOver() 
	{
		if (piles.isEmpty()) 
		{
			winner = waitingPlayer;
			loser = currentPlayer;
		}
	}

	/**
	 * Swaps the players positions, depending on whose turn it is
	 */
	public void swapPlayers() 
	{
		Player temp = currentPlayer;
		currentPlayer = waitingPlayer;
		waitingPlayer = temp;
	}

	/**
	 * Moves along the game, using the takeTurn method and so on, to help the game go along.
	 */
	public void play()
	{
		while (winner == null || loser == null)
		{
			takeTurn();
			checkGameOver();
			swapPlayers();
		}
		winner.notifyWin();
		loser.notifyLose();
	}
}
