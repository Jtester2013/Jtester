package plugin.ui.window.configuration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.SimpleWildcardTester;

public class ConfigWin {

	protected Shell shell;
	
	public static ConfigWindowComposite configWinComposite = null;
	
	private ConfigWin(){
		super();
//		configWinComposite = new ConfigWindowComposite(parent, style)
	}
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ConfigWin window = new ConfigWin();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Jtester");
		// set layout
		configWinComposite = new ConfigWindowComposite(shell, SWT.NONE);
		
		
	}

}
