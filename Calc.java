//Dylan Agiman
//Period 6

import javax.swing.*;
import java.awt.*;

public class Calc
{
	private boolean[] functions;
	private double[] memory;
	private boolean complete;

	public Calc()
	{
		functions = new boolean[] {false, false, false, false};
		memory = new double[] {0.0, 0.0};
		complete = false;
	}

	public void reset()
	{
		for(int i = 0; i < functions.length; i++)
			functions[i] = false;
		for(int i = 0; i < memory.length; i++)
			memory[i] = 0.0;
		complete = false;
	}

	public void resetFunctions()
	{
		for(int i = 0; i < functions.length; i++)
			functions[i] = false;
	}

	public void ceClear()
	{
		memory[1] = 0.0;
	}

	public void setAdd()
	{
		functions[0] = true;
	}

	public void setSubtract()
	{
		functions[1] = true;
	}

	public void setMultiply()
	{
		functions[2] = true;
	}

	public void setDivide()
	{
		functions[3] = true;
	}


	public void addNum(double tempNum)
	{
		if(complete)
		{
			memory[1] = tempNum;
		}
		else
		{
			memory[0] = tempNum;
			complete = true;
		}

	}

	public double calculate()
	{
		if(functions[0] == true)
			return memory[0] + memory[1];
		if(functions[1] == true)
			return memory[0] - memory[1];
		if(functions[2] == true)
			return memory[0] * memory[1];
		else
			return memory[0] / memory[1];
	}
}
