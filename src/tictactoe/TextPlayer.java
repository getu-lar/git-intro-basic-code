package tictactoe;

import java.util.*;
import java.io.*;

public class TextPlayer implements ICanMove
{
	private static HashMap<Character, Integer> Columns = new HashMap<Character, Integer>() {{
		put('a', 0);
		put('b', 1);
		put('c', 2);
	}};
	private static HashMap<Character, Integer> Rows = new HashMap<Character, Integer>() {{
		put('1', 0);
		put('2', 1);
		put('3', 2);
	}};
	
	private BufferedReader _reader;
	private BufferedWriter _writer;
	
	public TextPlayer(BufferedReader reader, BufferedWriter writer)
	{
		_reader = reader;
		_writer = writer;
	}
	
	@Override
	public Move makeMove()
	{
		String input = null;
		Boolean inputError = false;
		
		do
		{
			try
			{
				if (inputError)
				{
					_writer.write("Invalid input. Try again.");
					_writer.newLine();
				}
				
				input = _reader.readLine();
				
				if (input != null && input.charAt(0) == 'q')
					return new Move();
				
				inputError = (
						input == null ||
						input.length() < 2 ||
						!Columns.containsKey(input.charAt(0)) ||
						!Rows.containsKey(input.charAt(1))
					);
			}
			catch (IOException e)
			{
				inputError = true;
			}
		} while(inputError);

		return new Move(Columns.get(input.charAt(0)), Rows.get(input.charAt(1)));
	}

}
