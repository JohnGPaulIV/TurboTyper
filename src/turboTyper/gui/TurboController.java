package turboTyper.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

public class TurboController implements ActionListener {

	private TurboMainLabel tml;
	private TurboSentence ts;
	private List<TurboChar> tcl;
	private long totalWords;
	private int cursor;
	private long start;
	private long end;
	private long totalTime;
	private long wpm;
	
	public TurboController()
	{
		super();
	}
	
	public void setUpController(TurboMainLabel tml)
	{
		this.tml = tml;
		this.tcl = this.tml.getTurboSentence().getCharArray();
		this.ts = this.tml.getTurboSentence();
		this.totalWords = this.tml.getTurboSentence().getSentence().split("\\s").length;
		this.cursor = 0;
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String ac = ae.getActionCommand();
		char currChar = this.tcl.get(cursor).getChar();
		if (ac.equals("\b") && !(cursor <= 0))
		{
			cursor--;
			this.tcl.get(cursor).backspace();
			this.tml.setText(ts.getStyledSentence());
		}else if (ac.equals(String.valueOf(currChar)) && canContinue())
		{
			if (cursor == 0)
			{
				this.start = System.currentTimeMillis();
			}
			if (cursor == this.tcl.size() - 1)
			{
				System.out.println("ending");
				this.end = System.currentTimeMillis();
				this.totalTime = end - start;
				this.wpm = ((this.totalWords * 1000 * 60)/this.totalTime);
				System.out.println(this.wpm);
				
			}
			this.tcl.get(cursor).setCorrect();
			this.tml.setText(ts.getStyledSentence());
			cursor++;
		} else if (canContinue() && !(ac.equals("\b")))
		{
			this.tcl.get(cursor).setIncorrect();
			this.tml.setText(ts.getStyledSentence());
			cursor++;
		} 
	}
		
	private boolean canContinue()
	{
		if (cursor == 0)
		{
			return true;
		}
		return this.tcl.get(cursor - 1).isCorrect();
	}

}
