package turboTyper.gui;

import static turboTyper.TurboTyper.*;

public class TurboMainLabel extends TurboLabel
{
	private TurboSentence sentence;
	private static final long serialVersionUID = 1L;
	
	public TurboMainLabel(TurboSentence sentence)
	{
		super(sentence.getStyledSentence());
		this.sentence = sentence;
	}
	
	public TurboSentence getTurboSentence()
	{
		return this.sentence;
	}
	
}
