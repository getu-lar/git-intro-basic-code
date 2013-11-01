package tictactoe;

import java.io.*;

public class Application
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Application a = new Application();
		a.run();
	}
	
	private BufferedReader _reader;
	
	public Application()
	{
		_reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	private void run()
	{
		writeLine("Welcome to Tic-Tac-Toe");
		writeLine("Enter 'q' or 'quit' to exit");
		//writeLine("Hit RETURN to quit");
		//String line = readLine();
		//writeLine(line);
		writeLine(" -- Cave Johnson; we're done here!");
	}

	private void writeLine(String line)
	{
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
