package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import plugin.util.Const;
import plugin.util.SWTResourceManager;

public class GenerationTab {

	private TabItem tbtmGeneration;
	
	public GenerationInputTab inputTab;
	public GenerationFilterTab filterTab;
	public GenerationTestClassTab testClassTab;

	public GenerationTab(TabFolder tabFolder, int style) {
		// add Generation tab into right part of sashForm
		tbtmGeneration = new TabItem(tabFolder, SWT.NONE);
		tbtmGeneration.setImage(SWTResourceManager.getImage(Const.GENERATION_ICON_PATH));
		tbtmGeneration.setText("Generation");

		ScrolledComposite scrolledCompositeGeneration = new ScrolledComposite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL);
		tbtmGeneration.setControl(scrolledCompositeGeneration);
		scrolledCompositeGeneration.setExpandHorizontal(true);
		scrolledCompositeGeneration.setExpandVertical(true);

		Composite compositeInScrolledCompositeGeneration = new Composite(scrolledCompositeGeneration, SWT.NONE);
		compositeInScrolledCompositeGeneration.setLayout(new FormLayout());

		Button btnEnableUnitTestGeneration = new Button(compositeInScrolledCompositeGeneration, SWT.CHECK);
		FormData fd_btnEnableUnitTestGeneration = new FormData();
		fd_btnEnableUnitTestGeneration.top = new FormAttachment(0, 5);
		fd_btnEnableUnitTestGeneration.left = new FormAttachment(0, 5);
		btnEnableUnitTestGeneration.setLayoutData(fd_btnEnableUnitTestGeneration);
		btnEnableUnitTestGeneration.setText("Enable Unit Test Generation");

		TabFolder tabFolderInGeneration = new TabFolder(compositeInScrolledCompositeGeneration, SWT.NONE);
		FormData fd_tabFolderInGeneration = new FormData();
		fd_tabFolderInGeneration.top = new FormAttachment(btnEnableUnitTestGeneration, 10);
		fd_tabFolderInGeneration.left = new FormAttachment(btnEnableUnitTestGeneration, 0, SWT.LEFT);
		fd_tabFolderInGeneration.bottom = new FormAttachment(100, -5);
		fd_tabFolderInGeneration.right = new FormAttachment(100, -5);
		tabFolderInGeneration.setLayoutData(fd_tabFolderInGeneration);
		// add tabs into generation
		inputTab = new GenerationInputTab(tabFolderInGeneration);
		filterTab = new GenerationFilterTab(tabFolderInGeneration);
		testClassTab = new GenerationTestClassTab(tabFolderInGeneration);
		
		scrolledCompositeGeneration.setContent(compositeInScrolledCompositeGeneration);
		scrolledCompositeGeneration.setMinSize(compositeInScrolledCompositeGeneration.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

}
