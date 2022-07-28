package uk.bcb.cdl.kata.shopping.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * <p>Console class, to create a wrapper around the STDOUT/STDIN/STDERR streams, and allow you to implement a command handler.</p>
 * @author <a href="mailto:chrisaclark@sky.com">Chris Clark</a>
 *
 */
public class Console
{
	private BufferedReader input = null;
	private PrintWriter output = null;
	private PrintWriter error = null;
	
	
	public Console ()
	{
		input = new BufferedReader (new InputStreamReader (System.in));
		output = new PrintWriter (new OutputStreamWriter (System.out));
		error = new PrintWriter (new OutputStreamWriter (System.err));
	}

	public final void println ()
	{
		output.println();
		output.flush();
	}

	public final void println (String line)
	{
		output.println(line);
		output.flush();
	}

	public final void error (String line)
	{
		error.println(line);
		error.flush();
	}

	public final void print (String data)
	{
		output.print(data);
		output.flush();
	}
	
	public final String readLine ()
	{
		String line;
		try 
		{
			line = input.readLine();
		} 
		catch (IOException e) 
		{
			return null;
		}		
		
		return line;
	}
}
