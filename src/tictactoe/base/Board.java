package tictactoe.base;

import java.util.*;
import java.io.*;

import javax.activity.InvalidActivityException;

public class Board
{
	private static String join(List<String> list, String conjunction)
	{
	   StringBuilder sb = new StringBuilder();
	   boolean first = true;
	   for (String item : list)
	   {
	      if (first)
	         first = false;
	      else
	         sb.append(conjunction);
	      sb.append(item);
	   }
	   return sb.toString();
	}
	
	private List<Field> _fields;
	private Player _playerX;
	private Player _playerO;
	private Player _currentPlayer;
	private Boolean _isGameOver = false;
	private Player _winner;
	
	public Board()
	{
		_fields = new ArrayList<Field>(9);
		for (Integer i = 0; i < 9; i++)
		{
			_fields.add(new Field());
		}
		
		_playerX = new Player('X');
		_playerO = new Player('O');
		_currentPlayer = _playerO;
	}
	
	public void makeMove(Player player, int column, int row)
			throws InvalidActivityException
	{
		List<String> errors = validateMove(player, column, row);
		if (errors.size() > 0)
			throw new InvalidActivityException(join(errors, System.getProperty("line.separator")));
		
		getField(column, row).setOwner(player);
		checkVictoryCondition();
		_currentPlayer = (_currentPlayer == _playerX ? _playerO : _playerX);
	}
	
	private Field getField(int column, int row)
	{
		return _fields.get(3 * row + column);
	}
	
	public void writeBoard(BufferedWriter writer)
			throws IOException
	{
		writer.write("   a   b   c");
		writer.newLine(); writer.newLine();
		writeRow(writer, 0);
		writer.write("  ---+---+--- ");
		writer.newLine();
		writeRow(writer, 1);
		writer.write("  ---+---+--- ");
		writer.newLine();
		writeRow(writer, 2);
		writer.flush();
	}
	
	private void writeRow(BufferedWriter writer, Integer row)
			throws IOException
	{
		writer.write(Integer.toString(row + 1) + "  ");
		for (Integer i = 0; i < 3; i++)
		{
			Player owner = getField(i, row).getOwner();
			writer.write((owner == null ? ' ' : owner.getPlayerChar()) + "");
			if (i < 2)
				writer.write(" | ");
		}
		writer.newLine();
	}
	
	public List<String> validateMove(Player player, Integer column, Integer row)
	{
		List<String> errors = new ArrayList<>();
		
		if (column < 0)
			errors.add("Column must be >= 0");
		if (column > 2)
			errors.add("Column must be <= 2");
		if (row < 0)
			errors.add("Row must be >= 0");
		if (row > 2)
			errors.add("Row must be <= 2");

		if (player != _currentPlayer)
			errors.add(String.format("It is currently Player%c's turn", _currentPlayer.getPlayerChar()));

		if (getField(column, row).getOwner() != null)
			errors.add(String.format("The field (%d/%d) is already occupied by player '%c'", column, row, getField(column, row).getOwner().getPlayerChar()));

		return errors;
	}
	
	private Boolean isFull()
	{
		for (Field field : _fields)
		{
			if (field.getOwner() == null)
				return false;
		}
		
		return true;
	}
	
	private void checkVictoryCondition()
	{
		for (Integer i = 0; i < 3; i++)
		{
			checkThree(3 * i, 1);
			checkThree(i, 3);
		}
		
		checkThree(0, 4);
		checkThree(2, 2);
		
		if (_winner != null || isFull())
			_isGameOver = true;
	}
	
	private Player getThreeOwner(Integer start, Integer increment)
	{
		Player owner = null;
		for (Integer i = 0; i < 3; i++)
		{
			 Player currentOwner = _fields.get(start + i * increment).getOwner();
			 if (currentOwner == null)
				 return null;
			 if (owner != null && currentOwner != owner)
				 return null;
			 
			 owner = currentOwner;
		}
		return owner;
	}
	
	private void checkThree(Integer start, Integer increment)
	{
		Player owner = getThreeOwner(start, increment);
		if (owner != null)
		{
			Player victoryPlayer = new Player('*');
			for (Integer i = 0; i < 3; i++)
			{
				_fields.get(start + i * increment).setOwner(victoryPlayer);
			}
			_winner = owner;
		}
	}
	
	public Player getPlayerX()
	{
		return _playerX;
	}
	
	public Player getPlayerO()
	{
		return _playerO;
	}
	
	public Player getCurrentPlayer()
	{
		return _currentPlayer;
	}
	
	public Player getWinner()
	{
		return _winner;
	}
	
	public Boolean getIsGameOver()
	{
		return _isGameOver;
	}
}
