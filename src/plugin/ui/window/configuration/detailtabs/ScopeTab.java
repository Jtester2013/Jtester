package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import plugin.util.Const;
import plugin.util.SWTResourceManager;

public class ScopeTab {
	TabItem tbtmScope;
	
	private Text text_lastDaysInLineFilter;
	private Text text_authorNameInLineFilter;
	private Text text_minMethodNum;
	private Table tableMethodsPattern;

	public ScopeTab(TabFolder tabFolder, int style) {
		tbtmScope = new TabItem(tabFolder, style);
		tbtmScope.setImage(SWTResourceManager.getImage(Const.SCOPE_ICON_PATH));
		tbtmScope.setText("Scope");

		ScrolledComposite scrolledCompositeScope = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tbtmScope.setControl(scrolledCompositeScope);
		scrolledCompositeScope.setExpandHorizontal(true);
		scrolledCompositeScope.setExpandVertical(true);

		Composite compositeInScrolledCompositeScope = new Composite(scrolledCompositeScope, SWT.NONE);
		compositeInScrolledCompositeScope.setLayout(new FormLayout());

		TabFolder tabFolderInScope = new TabFolder(compositeInScrolledCompositeScope, SWT.NONE);
		FormData fd_tabFolderInScope = new FormData();
		fd_tabFolderInScope.right = new FormAttachment(100, -10);
		fd_tabFolderInScope.top = new FormAttachment(0, 10);
		fd_tabFolderInScope.left = new FormAttachment(0, 10);
		tabFolderInScope.setLayoutData(fd_tabFolderInScope);

		// add and set File Filter in Scope
		{
			TabItem tbtmFileFilter = new TabItem(tabFolderInScope, SWT.NONE);
			tbtmFileFilter.setText("File Filters");

			Composite fileFilterComposite = new Composite(tabFolderInScope, SWT.NONE);
			tbtmFileFilter.setControl(fileFilterComposite);
			fileFilterComposite.setLayout(new FormLayout());

			Group grpTimeOptions = new Group(fileFilterComposite, SWT.NONE);
			grpTimeOptions.setText("Time options");
			grpTimeOptions.setLayout(new FormLayout());
			FormData fd_grpTimeOptions = new FormData();
			fd_grpTimeOptions.right = new FormAttachment(100, -5);
			fd_grpTimeOptions.left = new FormAttachment(0, 5);
			fd_grpTimeOptions.top = new FormAttachment(0, 5);
			grpTimeOptions.setLayoutData(fd_grpTimeOptions);

			Button btnNoTimeFilters = new Button(grpTimeOptions, SWT.RADIO);
			FormData fd_btnNoTimeFilters = new FormData();
			fd_btnNoTimeFilters.top = new FormAttachment(0, 10);
			btnNoTimeFilters.setLayoutData(fd_btnNoTimeFilters);
			btnNoTimeFilters.setText("No time filters");

			Button btnSinceDate = new Button(grpTimeOptions, SWT.RADIO);
			FormData fd_btnSinceDate = new FormData();
			fd_btnSinceDate.top = new FormAttachment(btnNoTimeFilters, 5);
			btnSinceDate.setLayoutData(fd_btnSinceDate);
			btnSinceDate.setText("Test only files added or modified since the cutoff date");

			DateTime sinceDateTime = new DateTime(grpTimeOptions, SWT.BORDER);
			FormData fd_dateTime = new FormData();
			fd_dateTime.left = new FormAttachment(btnSinceDate, 15);
			fd_dateTime.top = new FormAttachment(btnNoTimeFilters);
			sinceDateTime.setLayoutData(fd_dateTime);

			Button btnTileDate = new Button(grpTimeOptions, SWT.CHECK);
			FormData fd_btnTileDate = new FormData();
			fd_btnTileDate.top = new FormAttachment(sinceDateTime, 5);
			fd_btnTileDate.left = new FormAttachment(0, 15);
			btnTileDate.setLayoutData(fd_btnTileDate);
			btnTileDate.setText("and added or modified before (now when disabled)");

			DateTime tileDateTime = new DateTime(grpTimeOptions, SWT.BORDER);
			FormData fd_TileDataTime = new FormData();
			fd_TileDataTime.top = new FormAttachment(btnSinceDate, 3);
			fd_TileDataTime.left = new FormAttachment(sinceDateTime, 0, SWT.LEFT);
			tileDateTime.setLayoutData(fd_TileDataTime);

			Button btnTestFilesInLast = new Button(grpTimeOptions, SWT.RADIO);
			FormData fd_btnTestFilesInLast = new FormData();
			fd_btnTestFilesInLast.top = new FormAttachment(btnTileDate, 5);
			btnTestFilesInLast.setLayoutData(fd_btnTestFilesInLast);
			btnTestFilesInLast.setText("Test only files added or modified in the last");

			text_lastDaysInLineFilter = new Text(grpTimeOptions, SWT.BORDER);
			FormData fd_text_lastDays = new FormData();
			fd_text_lastDays.top = new FormAttachment(btnTileDate, 3);
			fd_text_lastDays.left = new FormAttachment(btnTestFilesInLast, 5);
			text_lastDaysInLineFilter.setLayoutData(fd_text_lastDays);

			Label lblDays = new Label(grpTimeOptions, SWT.NONE);
			FormData fd_lblDays = new FormData();
			fd_lblDays.top = new FormAttachment(btnTileDate, 3);
			fd_lblDays.left = new FormAttachment(text_lastDaysInLineFilter, 5);
			lblDays.setLayoutData(fd_lblDays);
			lblDays.setText("days");

			Button btnTestLocalFile = new Button(grpTimeOptions, SWT.RADIO);
			FormData fd_btnTestLocalFile = new FormData();
			fd_btnTestLocalFile.bottom = new FormAttachment(100, -5);
			fd_btnTestLocalFile.top = new FormAttachment(btnTestFilesInLast, 5);
			btnTestLocalFile.setLayoutData(fd_btnTestLocalFile);
			btnTestLocalFile.setText("Test only files added or modified locally");

			Group grpAuthorOptions = new Group(fileFilterComposite, SWT.NONE);
			grpAuthorOptions.setText("Author options");
			grpAuthorOptions.setLayout(new FormLayout());
			FormData fd_grpAuthorOptions = new FormData();
			fd_grpAuthorOptions.left = new FormAttachment(0, 5);
			fd_grpAuthorOptions.right = new FormAttachment(100, -5);
			fd_grpAuthorOptions.bottom = new FormAttachment(100, -10);
			fd_grpAuthorOptions.top = new FormAttachment(grpTimeOptions, 10);
			grpAuthorOptions.setLayoutData(fd_grpAuthorOptions);

			Button btnNoAuthorFilters = new Button(grpAuthorOptions, SWT.RADIO);
			FormData fd_btnNoAuthorFilters = new FormData();
			fd_btnNoAuthorFilters.top = new FormAttachment(0, 10);
			btnNoAuthorFilters.setLayoutData(fd_btnNoAuthorFilters);
			btnNoAuthorFilters.setText("No author filters");

			Button btnFilesAuthoredByUser = new Button(grpAuthorOptions, SWT.RADIO);
			FormData fd_btnRadioButton = new FormData();
			fd_btnRadioButton.top = new FormAttachment(btnNoAuthorFilters, 5);
			fd_btnRadioButton.bottom = new FormAttachment(100, -5);
			btnFilesAuthoredByUser.setLayoutData(fd_btnRadioButton);
			btnFilesAuthoredByUser.setText("Test only files authored by user");

			text_authorNameInLineFilter = new Text(grpAuthorOptions, SWT.BORDER);
			FormData fd_text = new FormData();
			fd_text.top = new FormAttachment(btnNoAuthorFilters, 5);
			fd_text.left = new FormAttachment(btnFilesAuthoredByUser, 5);
			fd_text.bottom = new FormAttachment(100, -5);
			text_authorNameInLineFilter.setLayoutData(fd_text);
		}

		// add and set Line Filter in Scope
		{
			TabItem tbtmLineFilters = new TabItem(tabFolderInScope, SWT.NONE);
			tbtmLineFilters.setText("Line Filters");

			Composite lineFilterComposite = new Composite(tabFolderInScope, SWT.NONE);
			tbtmLineFilters.setControl(lineFilterComposite);
			lineFilterComposite.setLayout(new FormLayout());

			Group grpTimeOptionsInLineFilter = new Group(lineFilterComposite, SWT.NONE);
			grpTimeOptionsInLineFilter.setText("Time options");
			grpTimeOptionsInLineFilter.setLayout(new FormLayout());
			FormData fd_grpTimeOptions = new FormData();
			fd_grpTimeOptions.right = new FormAttachment(100, -5);
			fd_grpTimeOptions.left = new FormAttachment(0, 5);
			fd_grpTimeOptions.top = new FormAttachment(0, 5);
			grpTimeOptionsInLineFilter.setLayoutData(fd_grpTimeOptions);

			Button btnNoTimeFiltersInLineFilter = new Button(grpTimeOptionsInLineFilter, SWT.RADIO);
			FormData fd_btnNoTimeFilters = new FormData();
			fd_btnNoTimeFilters.top = new FormAttachment(0, 10);
			btnNoTimeFiltersInLineFilter.setLayoutData(fd_btnNoTimeFilters);
			btnNoTimeFiltersInLineFilter.setText("No time filters");

			Button btnSinceDateInLineFilter = new Button(grpTimeOptionsInLineFilter, SWT.RADIO);
			FormData fd_btnSinceDate = new FormData();
			fd_btnSinceDate.top = new FormAttachment(btnNoTimeFiltersInLineFilter, 5);
			btnSinceDateInLineFilter.setLayoutData(fd_btnSinceDate);
			btnSinceDateInLineFilter.setText("Test only lines added or modified since the cutoff date");

			DateTime sinceDateTimeInLineFilter = new DateTime(grpTimeOptionsInLineFilter, SWT.BORDER);
			FormData fd_dateTime = new FormData();
			fd_dateTime.left = new FormAttachment(btnSinceDateInLineFilter, 15);
			fd_dateTime.top = new FormAttachment(btnNoTimeFiltersInLineFilter);
			sinceDateTimeInLineFilter.setLayoutData(fd_dateTime);

			Button btnTestFilesInLastInLineFilter = new Button(grpTimeOptionsInLineFilter, SWT.RADIO);
			FormData fd_btnTestFilesInLast = new FormData();
			fd_btnTestFilesInLast.top = new FormAttachment(btnSinceDateInLineFilter, 7);
			btnTestFilesInLastInLineFilter.setLayoutData(fd_btnTestFilesInLast);
			btnTestFilesInLastInLineFilter.setText("Test only lines added or modified in the last");

			text_lastDaysInLineFilter = new Text(grpTimeOptionsInLineFilter, SWT.BORDER);
			FormData fd_text_lastDays = new FormData();
			fd_text_lastDays.top = new FormAttachment(btnSinceDateInLineFilter, 5);
			fd_text_lastDays.left = new FormAttachment(btnTestFilesInLastInLineFilter, 5);
			text_lastDaysInLineFilter.setLayoutData(fd_text_lastDays);

			Label lblDays = new Label(grpTimeOptionsInLineFilter, SWT.NONE);
			FormData fd_lblDays = new FormData();
			fd_lblDays.top = new FormAttachment(btnSinceDateInLineFilter, 7);
			fd_lblDays.left = new FormAttachment(text_lastDaysInLineFilter, 5);
			lblDays.setLayoutData(fd_lblDays);
			lblDays.setText("days");

			Button btnTestLocalFileInLineFilter = new Button(grpTimeOptionsInLineFilter, SWT.RADIO);
			FormData fd_btnTestLocalFile = new FormData();
			fd_btnTestLocalFile.bottom = new FormAttachment(100, -5);
			fd_btnTestLocalFile.top = new FormAttachment(btnTestFilesInLastInLineFilter, 5);
			btnTestLocalFileInLineFilter.setLayoutData(fd_btnTestLocalFile);
			btnTestLocalFileInLineFilter.setText("Test only lines added or modified locally");

			Group grpAuthorOptionsInLineFilter = new Group(lineFilterComposite, SWT.NONE);
			grpAuthorOptionsInLineFilter.setText("Author options");
			grpAuthorOptionsInLineFilter.setLayout(new FormLayout());
			FormData fd_grpAuthorOptions = new FormData();
			fd_grpAuthorOptions.top = new FormAttachment(grpTimeOptionsInLineFilter, 10);
			fd_grpAuthorOptions.left = new FormAttachment(0, 5);
			fd_grpAuthorOptions.right = new FormAttachment(100, -5);
			fd_grpAuthorOptions.bottom = new FormAttachment(100, -10);
			grpAuthorOptionsInLineFilter.setLayoutData(fd_grpAuthorOptions);

			Button btnNoAuthorFiltersInLineFilter = new Button(grpAuthorOptionsInLineFilter, SWT.RADIO);
			FormData fd_btnNoAuthorFilters = new FormData();
			fd_btnNoAuthorFilters.top = new FormAttachment(0, 10);
			btnNoAuthorFiltersInLineFilter.setLayoutData(fd_btnNoAuthorFilters);
			btnNoAuthorFiltersInLineFilter.setText("No author filters");

			Button btnFilesAuthoredByUserInLineFilter = new Button(grpAuthorOptionsInLineFilter, SWT.RADIO);
			FormData fd_btnRadioButton = new FormData();
			fd_btnRadioButton.top = new FormAttachment(btnNoAuthorFiltersInLineFilter, 5);
			btnFilesAuthoredByUserInLineFilter.setLayoutData(fd_btnRadioButton);
			btnFilesAuthoredByUserInLineFilter.setText("Test only lines authored by user");

			text_authorNameInLineFilter = new Text(grpAuthorOptionsInLineFilter, SWT.BORDER);
			FormData fd_text = new FormData();
			fd_text.top = new FormAttachment(btnNoAuthorFiltersInLineFilter, 5);
			fd_text.left = new FormAttachment(btnFilesAuthoredByUserInLineFilter, 5);
			text_authorNameInLineFilter.setLayoutData(fd_text);
		}
		// add and set Method Filters in Scope
		{
			TabItem tbtmMethodFilters = new TabItem(tabFolderInScope, SWT.NONE);
			tbtmMethodFilters.setText("Method Filters");

			Composite methodFiltersComposite = new Composite(tabFolderInScope, SWT.NONE);
			tbtmMethodFilters.setControl(methodFiltersComposite);
			methodFiltersComposite.setLayout(new FormLayout());

			Button btnSkipMethedByPattern = new Button(methodFiltersComposite, SWT.CHECK);
			FormData fd_btnCheckButton = new FormData();
			fd_btnCheckButton.top = new FormAttachment(0, 5);
			fd_btnCheckButton.right = new FormAttachment(100, -5);
			fd_btnCheckButton.left = new FormAttachment(0, 5);
			btnSkipMethedByPattern.setLayoutData(fd_btnCheckButton);
			btnSkipMethedByPattern.setText("Skip methods with names that match one of the following regular expressions");

			tableMethodsPattern = new Table(methodFiltersComposite, SWT.BORDER | SWT.FULL_SELECTION);
			FormData fd_table = new FormData();
			fd_table.left = new FormAttachment(btnSkipMethedByPattern, 5, SWT.LEFT);
			fd_table.right = new FormAttachment(100, -70);
			fd_table.bottom = new FormAttachment(100, -5);
			fd_table.top = new FormAttachment(btnSkipMethedByPattern, 5);
			tableMethodsPattern.setLayoutData(fd_table);
			tableMethodsPattern.setHeaderVisible(true);
			tableMethodsPattern.setLinesVisible(true);

			TableColumn tblclmnMethodPattern = new TableColumn(tableMethodsPattern, SWT.NONE);
			tblclmnMethodPattern.setWidth(210);
			tblclmnMethodPattern.setText("Method name regular expression");

			Button btnAddMethodPattern = new Button(methodFiltersComposite, SWT.NONE);
			FormData fd_btnAddMethodPattern = new FormData();
			fd_btnAddMethodPattern.left = new FormAttachment(tableMethodsPattern, 5);
			fd_btnAddMethodPattern.width = 60;
			fd_btnAddMethodPattern.top = new FormAttachment(btnSkipMethedByPattern, 5);
			btnAddMethodPattern.setLayoutData(fd_btnAddMethodPattern);
			btnAddMethodPattern.setText("Add");

			Button btnRemoveMethodPattern = new Button(methodFiltersComposite, SWT.NONE);
			FormData fd_btnRemoveMethodPattern = new FormData();
			fd_btnRemoveMethodPattern.width = 60;
			fd_btnRemoveMethodPattern.left = new FormAttachment(btnAddMethodPattern, 0, SWT.LEFT);
			fd_btnRemoveMethodPattern.top = new FormAttachment(btnAddMethodPattern, 5);
			btnRemoveMethodPattern.setLayoutData(fd_btnRemoveMethodPattern);
			btnRemoveMethodPattern.setText("Remove");
		}

		// add and set bottom composite in Scope
		{
			Composite bottomCompositeInScope = new Composite(compositeInScrolledCompositeScope, SWT.NONE);
			bottomCompositeInScope.setLayout(new FormLayout());
			FormData fd_bottomCompositeInScope = new FormData();
			fd_bottomCompositeInScope.top = new FormAttachment(tabFolderInScope, 10);
			fd_bottomCompositeInScope.right = new FormAttachment(100, -10);
			fd_bottomCompositeInScope.left = new FormAttachment(0, 10);
			bottomCompositeInScope.setLayoutData(fd_bottomCompositeInScope);

			Button btnLimitSimpleMethods = new Button(bottomCompositeInScope, SWT.CHECK);
			FormData fd_btnLimitSimpleMethods = new FormData();
			fd_btnLimitSimpleMethods.top = new FormAttachment(0, 3);
			btnLimitSimpleMethods.setLayoutData(fd_btnLimitSimpleMethods);
			btnLimitSimpleMethods.setText("Do not test methods with cyclomatic complexity less than");

			Button btnLimitsDeprecatedClassMethod = new Button(bottomCompositeInScope, SWT.CHECK);
			FormData fd_btnLimitsDeprecatedClassMethod = new FormData();
			fd_btnLimitsDeprecatedClassMethod.bottom = new FormAttachment(100, -10);
			fd_btnLimitsDeprecatedClassMethod.top = new FormAttachment(btnLimitSimpleMethods, 5);
			btnLimitsDeprecatedClassMethod.setLayoutData(fd_btnLimitsDeprecatedClassMethod);
			btnLimitsDeprecatedClassMethod.setText("Do not test @deprecated classes or methods");

			text_minMethodNum = new Text(bottomCompositeInScope, SWT.BORDER);
			FormData fd_text_minMethodNum = new FormData();
			fd_text_minMethodNum.left = new FormAttachment(btnLimitSimpleMethods, 5);
			text_minMethodNum.setLayoutData(fd_text_minMethodNum);

			scrolledCompositeScope.setContent(compositeInScrolledCompositeScope);
			scrolledCompositeScope.setMinSize(compositeInScrolledCompositeScope.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		}
	}

	public TabItem getTabItem(){
		return this.tbtmScope;
	}

}
