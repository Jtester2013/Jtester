package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;

public class SearchInExecutionTab {
	TabItem tabItem;
	private Table table_RelativeToProjects;
	private Table table_Patterns;
	
	public SearchInExecutionTab(TabFolder tabFolder){
		tabItem = new TabItem(tabFolder, SWT.None);
		tabItem.setText("Search");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.H_SCROLL | SWT.V_SCROLL);
		tabItem.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Composite compositeInScrolledComposite = new Composite(scrolledComposite, SWT.NONE);
		compositeInScrolledComposite.setLayout(new FormLayout());
		// TO DO: add content into compositeInScrolledComposite
		
		Button btnTestsFromRepository = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnTestsFromRepository = new FormData();
		fd_btnTestsFromRepository.top = new FormAttachment(0,5);
		fd_btnTestsFromRepository.left = new FormAttachment(0, 5);
		btnTestsFromRepository.setLayoutData(fd_btnTestsFromRepository);
		btnTestsFromRepository.setText("Tests from repository relative to the projects in scope:");
		
		table_RelativeToProjects = new Table(compositeInScrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);;
		FormData fd_table_RelativeToProjects = new FormData();
		fd_table_RelativeToProjects.height = 120;
		fd_table_RelativeToProjects.right = new FormAttachment(100, -80);
		fd_table_RelativeToProjects.top = new FormAttachment(btnTestsFromRepository, 6);
		fd_table_RelativeToProjects.left = new FormAttachment(btnTestsFromRepository, 10, SWT.LEFT);
		table_RelativeToProjects.setLayoutData(fd_table_RelativeToProjects);
		table_RelativeToProjects.setHeaderVisible(false);
		
		Button btnNewRelativeToProjects = new Button(compositeInScrolledComposite, SWT.NONE);
		FormData fd_btnNewRelativeToProjects = new FormData();
		fd_btnNewRelativeToProjects.width = 60;
		fd_btnNewRelativeToProjects.top = new FormAttachment(btnTestsFromRepository, 6);
		fd_btnNewRelativeToProjects.left = new FormAttachment(table_RelativeToProjects, 6);
		btnNewRelativeToProjects.setLayoutData(fd_btnNewRelativeToProjects);
		btnNewRelativeToProjects.setText("New");
		
		Button btnEditRelativeToProjects = new Button(compositeInScrolledComposite, SWT.NONE);
		FormData fd_btnEditRelativeToProjects = new FormData();
		fd_btnEditRelativeToProjects.width = 60;
		fd_btnEditRelativeToProjects.top = new FormAttachment(btnNewRelativeToProjects, 6);
		fd_btnEditRelativeToProjects.left = new FormAttachment(table_RelativeToProjects, 6);
		btnEditRelativeToProjects.setLayoutData(fd_btnEditRelativeToProjects);
		btnEditRelativeToProjects.setText("Edit");
		
		Button btnRemoveRelativeToProjects = new Button(compositeInScrolledComposite, SWT.NONE);
		FormData fd_btnRemoveRelativeToProjects = new FormData();
		fd_btnRemoveRelativeToProjects.width = 60;
		fd_btnRemoveRelativeToProjects.top = new FormAttachment(btnEditRelativeToProjects, 6);
		fd_btnRemoveRelativeToProjects.left = new FormAttachment(table_RelativeToProjects, 6);
		btnRemoveRelativeToProjects.setLayoutData(fd_btnRemoveRelativeToProjects);
		btnRemoveRelativeToProjects.setText("Remove");
		
		Button btnTestClassesPatterns = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnTestClassesPatterns = new FormData();
		fd_btnTestClassesPatterns.top = new FormAttachment(table_RelativeToProjects, 6);
		fd_btnTestClassesPatterns.left = new FormAttachment(btnTestsFromRepository, 0, SWT.LEFT);
		btnTestClassesPatterns.setLayoutData(fd_btnTestClassesPatterns);
		btnTestClassesPatterns.setText("Test classes that match one of thesepatterns relative to the classes in scope:");
		
		table_Patterns = new Table(compositeInScrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);;
		FormData fd_table_Patterns = new FormData();
		fd_table_Patterns.height = 210;
		fd_table_Patterns.top = new FormAttachment(btnTestClassesPatterns, 6);
		fd_table_Patterns.left = new FormAttachment(table_RelativeToProjects, 0, SWT.LEFT);
		fd_table_Patterns.right = new FormAttachment(table_RelativeToProjects, 0, SWT.RIGHT);
		table_Patterns.setLayoutData(fd_table_Patterns);
		table_Patterns.setHeaderVisible(false);
		
		Button btnNewPatterns = new Button(compositeInScrolledComposite, SWT.NONE);
		FormData fd_btnNewPatterns = new FormData();
		fd_btnNewPatterns.width = 60;
		fd_btnNewPatterns.top = new FormAttachment(table_Patterns, 0, SWT.TOP);
		fd_btnNewPatterns.left = new FormAttachment(table_Patterns, 6);
		btnNewPatterns.setLayoutData(fd_btnNewPatterns);
		btnNewPatterns.setText("New");
		
		Button btnEditPatterns = new Button(compositeInScrolledComposite, SWT.NONE);
		FormData fd_btnEditPatterns = new FormData();
		fd_btnEditPatterns.width = 60;
		fd_btnEditPatterns.top = new FormAttachment(btnNewPatterns, 6);
		fd_btnEditPatterns.left = new FormAttachment(table_Patterns, 6);
		btnEditPatterns.setLayoutData(fd_btnEditPatterns);
		btnEditPatterns.setText("Edit");
		
		Button btnRemovePatterns = new Button(compositeInScrolledComposite, SWT.NONE);
		FormData fd_btnRemovePatterns = new FormData();
		fd_btnRemovePatterns.width = 60;
		fd_btnRemovePatterns.top = new FormAttachment(btnEditPatterns, 6);
		fd_btnRemovePatterns.left = new FormAttachment(table_Patterns, 6);
		btnRemovePatterns.setLayoutData(fd_btnRemovePatterns);
		btnRemovePatterns.setText("Remove");
		
		Button btnTestClassesIn = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnTestClassesIn = new FormData();
		fd_btnTestClassesIn.top = new FormAttachment(table_Patterns, 6);
		fd_btnTestClassesIn.left = new FormAttachment(btnTestsFromRepository, 0, SWT.LEFT);
		btnTestClassesIn.setLayoutData(fd_btnTestClassesIn);
		btnTestClassesIn.setText("Test classes in scope");
		
		Button btnTestSuitesIn = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnTestSuitesIn = new FormData();
		fd_btnTestSuitesIn.top = new FormAttachment(btnTestClassesIn, 6);
		fd_btnTestSuitesIn.left = new FormAttachment(btnTestsFromRepository, 0, SWT.LEFT);
		btnTestSuitesIn.setLayoutData(fd_btnTestSuitesIn);
		btnTestSuitesIn.setText("Test suites in scope");
		
		Button btnTestClassesWithin = new Button(compositeInScrolledComposite, SWT.CHECK);
		FormData fd_btnTestClassesWithin = new FormData();
		fd_btnTestClassesWithin.top = new FormAttachment(btnTestSuitesIn, 6);
		fd_btnTestClassesWithin.left = new FormAttachment(btnTestsFromRepository, 0, SWT.LEFT);
		btnTestClassesWithin.setLayoutData(fd_btnTestClassesWithin);
		btnTestClassesWithin.setText("Test classes within the workspace referring to classes in scope");
		
		scrolledComposite.setContent(compositeInScrolledComposite);
		scrolledComposite.setMinSize(compositeInScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	
	
}
