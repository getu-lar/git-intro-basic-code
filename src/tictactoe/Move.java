package tictactoe;

public class Move
{
	private Boolean _isQuit;
	private Integer _column;
	private Integer _row;
	
	public Move()
	{
		_isQuit = true;
	}
	
	public Move(Integer column, Integer row)
	{
		_column = column;
		_row = row;
	}
	
	public Boolean getIsQuit()
	{
		return _isQuit;
	}
	
	public Integer getColumn()
	{
		return _column;
	}
	
	public Integer getRow()
	{
		return _row;
	}
}
