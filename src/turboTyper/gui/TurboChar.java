package turboTyper.gui;

import java.awt.Color;

public class TurboChar {
	private char c;
	private Color baseColor;
	private Color correctColor;
	private Color incorrectColor;
	private Color currentColor;
	private boolean correct = false;
	
	public TurboChar(char c, Color baseColor, Color correctColor,
			Color incorrectColor)
	{
		this.c = c;
		this.baseColor = baseColor;
		this.correctColor = correctColor;
		this.incorrectColor = incorrectColor;
		this.currentColor = baseColor;
	}
	
	public TurboChar(char c)
	{
		this.c = c;
		this.baseColor = new Color(0, 0, 0);
		this.correctColor = new Color(0, 125, 0);
		this.incorrectColor = new Color(125, 0, 0);
	}
	
	public char getChar()
	{
		return this.c;
	}
	
	public Color getBaseColor()
	{
		return this.baseColor;
	}
	
	public Color getCorrectColor()
	{
		return this.correctColor;
	}
	
	public Color getIncorrectColor()
	{
		return this.incorrectColor;
	}
	
	public Color getCurrentColor()
	{
		return this.currentColor;
	}
	
	public void setBaseColor(Color c)
	{
		this.baseColor = c;
	}
	
	public void setCorrectColor(Color c)
	{
		this.correctColor = c;
	}
	
	public void setIncorrectColor(Color c)
	{
		this.incorrectColor = c;
	}
	
	public void setCurrentColor(Color c)
	{
		this.currentColor = c;
	}
	
	public void setCorrect()
	{
		this.correct = true;
		setCurrentColor(correctColor);
	}
	
	public void setIncorrect()
	{
		this.correct = false;
		setCurrentColor(incorrectColor);
	}
	
	public boolean isCorrect()
	{
		return this.correct;
	}
	
	public void backspace()
	{
		setCurrentColor(baseColor);
	}
}
