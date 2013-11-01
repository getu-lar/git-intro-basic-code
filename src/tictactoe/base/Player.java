package tictactoe.base;

public class Player
{
	private Character _playerChar;
	
	public Player(Character playerChar)
	{
		_playerChar = playerChar;
	}
	
	public Character getPlayerChar()
	{
		return _playerChar;
	}
}
