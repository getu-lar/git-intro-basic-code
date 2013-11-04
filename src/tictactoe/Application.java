package tictactoe;

import java.io.*;
import java.util.HashMap;

import javax.activity.InvalidActivityException;

import tictactoe.base.*;

public class Application
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Application a = new Application();
		
		try
		{
			a.run();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private BufferedReader _reader;
	private BufferedWriter _writer;
	private HashMap<Player, ICanMove> _players;
	
	public Application()
	{
		_reader = new BufferedReader(new InputStreamReader(System.in));
		_writer = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	
	private void run()
		throws IOException
	{
		writeLine("Welcome to Tic-Tac-Toe");
		writeLine("Enter 'q' or 'quit' to exit");
		
		final Board board = new Board();
		
		_players = new HashMap<Player, ICanMove>() {{
			put(board.getPlayerO(), new TextPlayer(_reader, _writer));
			put(board.getPlayerX(), new AiPlayerRandom(board, board.getPlayerX()));
		}};
		
		while (!board.getIsGameOver())
		{
			writeLine(null);
			board.writeBoard(_writer);
			
			writeLine(String.format("Player '%c's move", board.getCurrentPlayer().getPlayerChar()));
			Move move = null;
			
			try
			{
				move = _players.get(board.getCurrentPlayer()).makeMove();
			}
			catch (Exception e)
			{
				writeLine("Error while searching for a valid move:");
				writeLine(e.toString());
				return;
			}
			
			if (move.getIsQuit())
				return;
			
			try
			{
				board.makeMove(board.getCurrentPlayer(), move.getColumn(), move.getRow());
			}
			catch (InvalidActivityException e)
			{
				writeLine("Illegal move");
				writeLine(e.getMessage());
			}
		}
		
		writeLine(null);
		if (board.getWinner() == null)
		{
			writeLine("The game ended in a tie :-/");
		}
		else
		{
			writeLine(String.format("Player '%c' has won the game", board.getWinner().getPlayerChar()));
		}
		
		board.writeBoard(_writer);
		writeLine(null);
		
		writeLine("Hit RETURN to quit");
		readLine();
		//writeLine(line);
		writeLine(" -- Cave Johnson; we're done here!");
	}

	private void writeLine(String line)
	{
		if (line == null)
			System.out.println("");
		else
			System.out.println(line);
	}
	
	private String readLine()
	{
		try
		{
			String line = _reader.readLine();
			return line;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
