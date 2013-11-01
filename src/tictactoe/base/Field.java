package tictactoe.base;

public class Field
{
	private Player _owner;
	
	public Player getOwner()
	{
		return _owner;
	}
	
	public void setOwner(Player player)
	{
		_owner = player;
	}
}
