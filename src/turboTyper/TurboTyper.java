package turboTyper;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;

import turboTyper.gui.TurboController;
import turboTyper.gui.TurboMainLabel;
import turboTyper.gui.TurboTyperWindow;
import turboTyper.utilities.TurboSessionKey;
import turboTyper.gui.TurboSentence;

public class TurboTyper implements Runnable
{
	public static ResourceBundle rb;
	
	TurboTyperWindow window;
	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		SwingUtilities.invokeAndWait(new TurboTyper());
	}

	@Override
	public void run() {
		Locale l = Locale.getDefault();
		rb = ResourceBundle.getBundle("turboTyper.languages.Strings", l);
		TurboSessionKey key = TurboSessionKey.createkey();
		TurboController controller = new TurboController();
		window = new TurboTyperWindow(key, controller);
		Container pane = window.getContentPane();
		
		
		
		
		
		/*window.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e)
			{
				int wrapPx = window.getContentPane().getWidth() - 40;
				
				tml.setText(tml.getTurboSentence().getStyledSentence());
				
				window.validate();
				window.repaint();
			}
		});*/
		System.out.println(key.getCurrSessionNum());
		
	}
}
