package turboTyper.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class TurboSentence 
{
	private List<TurboChar> cArray;
	private String sentence;
	private TurboTyperWindow frame;
	public static Color BASE_COLOR = new Color(128, 128, 128);
	public static Color CORRECT_COLOR = new Color(76, 153, 0);
	public static Color INCORRECT_COLOR = new Color(204, 0, 0);
	
	public TurboSentence(String sentence, Color baseColor, Color correctColor, Color incorrectColor, TurboTyperWindow frame)
	{
		this.cArray = new ArrayList<TurboChar>();
		this.sentence = sentence;
		char[] cs = sentence.toCharArray();
		for (char c : cs)
		{
			TurboChar tc = new TurboChar(c, baseColor, correctColor, incorrectColor);
			cArray.add(tc);
			
		}
		this.frame = frame;
	}
	
	public TurboSentence(String sentence, TurboTyperWindow frame)
	{
		this(sentence, BASE_COLOR, CORRECT_COLOR, INCORRECT_COLOR, frame);
	}
	
	public String getSentence()
	{
		return this.sentence;
	}
	
	public List<TurboChar> getCharArray()
	{
		return this.cArray;
	}
	
	private static String colorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
	
	public String getStyledSentence()
	{
		double wrapPx = frame.getWidth() - 40;
		String s = "<html><body style='width:" + wrapPx + "px;'>";
		//s += 
		for (TurboChar tc : this.cArray)
		{
			s += "<font color=\"" + TurboSentence.colorToHex(tc.getCurrentColor()) + "\">" + tc.getChar() + "</font>";
		}
		s += "</body></html>";
		return s;
	}
}
