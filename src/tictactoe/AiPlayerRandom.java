package tictactoe;

import java.util.*;

import tictactoe.base.*;

public class AiPlayerRandom implements ICanMove
{
	private Board _board;
	private Random _random;
	private Player _player;
	
	public AiPlayerRandom(Board board, Player player)
	{
		_board = board;
		_random = new Random();
		_player = player;
	}
	
	public Player getPlayer()
	{
		return _player;
	}

	@Override
	public Move makeMove()
			throws Exception
	{
		List<String> errors;
		int col, row, count = 0;
		
		do
		{
			col = _random.nextInt(3);
			row = _random.nextInt(3);
			if (count++ > 100)
				throw new Exception("Could not find a valid move in 100 tries");
			
			errors = _board.validateMove(_player, col, row);
		} while (errors.size() > 0);
		
		return new Move(col, row);
	}
}
