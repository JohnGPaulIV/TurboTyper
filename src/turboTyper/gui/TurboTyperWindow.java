package turboTyper.gui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;

import turboTyper.utilities.TurboSessionKey;

import static turboTyper.TurboTyper.*;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class TurboTyperWindow extends JFrame implements KeyListener
{
	private int sessionNum;
	private TurboSessionKey tsk;
	private TurboController tc;
	private static final long serialVersionUID = 1L;
	
	public TurboTyperWindow(TurboSessionKey key, TurboController controller) {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		this.tsk = key;
		this.tc = controller;
		setUp();
		setFocusable(true);
		addKeyListener(this);
		
	}
	
	public void setUp()
	{
		Locale l = Locale.getDefault();
		rb = ResourceBundle.getBundle("turboTyper.languages.Strings", l);
		TurboSessionKey key = TurboSessionKey.createkey();
		int top = 20;
		int left = 0;
		int bottom = 0;
		int right = 0;
		TurboSentence turbo = new TurboSentence("The quick brown fox jumps over the lazy dog.", this);
		//TurboSentence turbo = new TurboSentence("The quick brown fox jumps over the lazy dog. He then goes to the grocery store and buys some of that yummy food and then gives it to his mom.", this);
		TurboMainLabel tml = new TurboMainLabel(turbo);
		TurboController controller = new TurboController();
		controller.setUpController(tml);
		this.tc = controller;
		Container pane = getContentPane();
		pane.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		pack();
		setLocationRelativeTo(null);
		setSize(getWidth() + 800, getHeight() + 400);
		setMinimumSize(new Dimension(200, 200));
		
		tml.setFont(tml.getFont().deriveFont(36f));
		tml.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
		tml.setVisible(true);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(20, 0, 0, 0);
		
		pane.add(tml, gbc);
		
		
		pack();
		setLocationRelativeTo(null);
		setSize(getWidth() + 100, getHeight() + 50);
		setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		char keyChar = e.getKeyChar();
		ActionEvent ae = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, String.valueOf(keyChar));
		System.out.println(keyChar);
		tc.actionPerformed(ae);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
