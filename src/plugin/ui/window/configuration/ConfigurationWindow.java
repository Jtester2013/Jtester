package plugin.ui.window.configuration;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import plugin.util.Const;
import plugin.util.LayoutFactory;
import plugin.util.SWTResourceManager;

public class ConfigurationWindow {

	protected Shell shell;

	public Composite titleWindowComposite;
	public Composite bottomComposite;
	private SashForm centerSashFormComposite;
		public ConfigSelectWindow configListTreeComposite;
		public ConfigDetailWindow detailConfigComposite;
	private int[] compositePortion = new int[] { 3, 7 };

//	private Composite configListTreeComposite;
//	private Composite detailConfigComposite;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ConfigurationWindow window = new ConfigurationWindow();
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
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(800, 600);
		shell.setText("Jtester Configurations");
		FormLayout nomalCompositeLayout = LayoutFactory.getFormLayout();
		shell.setLayout(nomalCompositeLayout);
		// add top comosite
		titleWindowComposite = new TitleConfigWindow(shell, SWT.None).getComposite();
		// add bottom composite
		bottomComposite = new BottomConfigWindow(shell, SWT.None).getComposite();
		// set sash form for center composite
		centerSashFormComposite = new SashForm(shell, SWT.HORIZONTAL | SWT.SMOOTH);
		FormData fd_centerSashForm = new FormData();
		fd_centerSashForm.bottom = new FormAttachment(bottomComposite);
		fd_centerSashForm.left = new FormAttachment(0);
		fd_centerSashForm.top = new FormAttachment(titleWindowComposite);
		fd_centerSashForm.right = new FormAttachment(100);
		centerSashFormComposite.setLayoutData(fd_centerSashForm);
		configListTreeComposite = new ConfigSelectWindow(centerSashFormComposite, SWT.None);
		detailConfigComposite = new ConfigDetailWindow(centerSashFormComposite, SWT.None, "a");
		centerSashFormComposite.setWeights(compositePortion);
	}

}
