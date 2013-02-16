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
import org.eclipse.swt.widgets.Text;

public class StaticInGoalsTab {
	TabItem tabItem;
	private Text text_numberOfTasks;
	private Text text_maxTask;
	private DateTime dateTimeBy;

	public StaticInGoalsTab(TabFolder tabFolder) {
		tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("Static");

		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tabItem.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Composite compositeInScrolledComposite = new Composite(scrolledComposite, SWT.NONE);
		compositeInScrolledComposite.setLayout(new FormLayout());

		Group grpStatic = new Group(compositeInScrolledComposite, SWT.NONE);
		grpStatic.setText("Static");
		grpStatic.setLayout(new FormLayout());
		FormData fd_grpStatic = new FormData();
		fd_grpStatic.right = new FormAttachment(100, -5);
		fd_grpStatic.top = new FormAttachment(0, 10);
		fd_grpStatic.left = new FormAttachment(0, 5);
		grpStatic.setLayoutData(fd_grpStatic);

		Button btnPerformAllTasks = new Button(grpStatic, SWT.RADIO);
		FormData fd_btnPerformAllTasks = new FormData();
		fd_btnPerformAllTasks.left = new FormAttachment(0, 5);
		btnPerformAllTasks.setLayoutData(fd_btnPerformAllTasks);
		btnPerformAllTasks.setText("Perform all tasks");

		Button btnRadioButton = new Button(grpStatic, SWT.RADIO);
		FormData fd_btnRadioButton = new FormData();
		fd_btnRadioButton.top = new FormAttachment(btnPerformAllTasks, 10, SWT.BOTTOM);
		fd_btnRadioButton.left = new FormAttachment(btnPerformAllTasks, 0, SWT.LEFT);
		btnRadioButton.setLayoutData(fd_btnRadioButton);
		btnRadioButton.setText("Don't perform tasks");

		Button btnNoMoreThan = new Button(grpStatic, SWT.RADIO);
		FormData fd_btnNoMoreThan = new FormData();
		fd_btnNoMoreThan.top = new FormAttachment(btnRadioButton, 10, SWT.BOTTOM);
		fd_btnNoMoreThan.left = new FormAttachment(btnRadioButton, 0, SWT.LEFT);
		btnNoMoreThan.setLayoutData(fd_btnNoMoreThan);
		btnNoMoreThan.setText("No more than");

		text_numberOfTasks = new Text(grpStatic, SWT.BORDER);
		FormData fd_text_numberOfTasks = new FormData();
		fd_text_numberOfTasks.left = new FormAttachment(btnNoMoreThan, 5, SWT.RIGHT);
		fd_text_numberOfTasks.bottom = new FormAttachment(btnNoMoreThan, 0, SWT.BOTTOM);
		fd_text_numberOfTasks.width = 50;
		text_numberOfTasks.setLayoutData(fd_text_numberOfTasks);

		Label lblTasksPerDeveloper = new Label(grpStatic, SWT.NONE);
		FormData fd_lblTasksPerDeveloper = new FormData();
		fd_lblTasksPerDeveloper.left = new FormAttachment(text_numberOfTasks, 5, SWT.RIGHT);
		fd_lblTasksPerDeveloper.bottom = new FormAttachment(text_numberOfTasks, 0, SWT.BOTTOM);
		lblTasksPerDeveloper.setLayoutData(fd_lblTasksPerDeveloper);
		lblTasksPerDeveloper.setText("tasks per developer by: ");

		dateTimeBy = new DateTime(grpStatic, SWT.BORDER);
		FormData fd_dateTimeBy = new FormData();
		fd_dateTimeBy.left = new FormAttachment(lblTasksPerDeveloper, 5, SWT.RIGHT);
		fd_dateTimeBy.bottom = new FormAttachment(lblTasksPerDeveloper, 0, SWT.BOTTOM);
		dateTimeBy.setLayoutData(fd_dateTimeBy);

		Button btnMaxTaskNumberButton = new Button(grpStatic, SWT.CHECK);
		btnMaxTaskNumberButton.setText("Max tasks to recommend:");
		FormData fd_btnMaxTaskNumberButton = new FormData();
		fd_btnMaxTaskNumberButton.left = new FormAttachment(btnNoMoreThan, 0, SWT.LEFT);
		fd_btnMaxTaskNumberButton.top = new FormAttachment(btnNoMoreThan, 10, SWT.BOTTOM);
		btnMaxTaskNumberButton.setLayoutData(fd_btnMaxTaskNumberButton);

		text_maxTask = new Text(grpStatic, SWT.BORDER);
		FormData fd_text_maxTask = new FormData();
		fd_text_maxTask.left = new FormAttachment(btnMaxTaskNumberButton, 5, SWT.RIGHT);
		fd_text_maxTask.bottom = new FormAttachment(btnMaxTaskNumberButton, 0, SWT.BOTTOM);
		fd_text_maxTask.width = 50;
		text_maxTask.setLayoutData(fd_text_maxTask);
		
//		Group staticGroup = new Group(compositeInScrolledComposite, SWT.None);
//		staticGroup.setText("Static");
//		staticGroup.setLayout(new FormLayout());
//
//		Group grpStatic = new Group(compositeInScrolledComposite, SWT.NONE);
//		grpStatic.setText("Static");
//		grpStatic.setLayout(new FormLayout());
//		FormData fd_grpStatic = new FormData();
//		fd_grpStatic.right = new FormAttachment(100, -5);
//		fd_grpStatic.top = new FormAttachment(0, 10);
//		fd_grpStatic.left = new FormAttachment(0, 5);
//		grpStatic.setLayoutData(fd_grpStatic);
//
//		Button btnPerformAllTasks = new Button(grpStatic, SWT.RADIO);
//		FormData fd_btnPerformAllTasks = new FormData();
//		fd_btnPerformAllTasks.left = new FormAttachment(0, 5);
//		btnPerformAllTasks.setLayoutData(fd_btnPerformAllTasks);
//		btnPerformAllTasks.setText("Perform all tasks");
//
//		Button btnRadioButton = new Button(grpStatic, SWT.RADIO);
//		FormData fd_btnRadioButton = new FormData();
//		fd_btnRadioButton.top = new FormAttachment(btnPerformAllTasks, 10, SWT.BOTTOM);
//		fd_btnRadioButton.left = new FormAttachment(btnPerformAllTasks, 0, SWT.LEFT);
//		btnRadioButton.setLayoutData(fd_btnRadioButton);
//		btnRadioButton.setText("Don't perform tasks");
//
//		Button btnNoMoreThan = new Button(grpStatic, SWT.RADIO);
//		FormData fd_btnNoMoreThan = new FormData();
//		fd_btnNoMoreThan.top = new FormAttachment(btnRadioButton, 10, SWT.BOTTOM);
//		fd_btnNoMoreThan.left = new FormAttachment(btnRadioButton, 0, SWT.LEFT);
//		btnNoMoreThan.setLayoutData(fd_btnNoMoreThan);
//		btnNoMoreThan.setText("No more than");
//
//		Text text_numberOfTasks = new Text(grpStatic, SWT.BORDER);
//		FormData fd_text_numberOfTasks = new FormData();
//		fd_text_numberOfTasks.left = new FormAttachment(btnNoMoreThan, 5, SWT.RIGHT);
//		fd_text_numberOfTasks.bottom = new FormAttachment(btnNoMoreThan, 0, SWT.BOTTOM);
//		fd_text_numberOfTasks.width = 50;
//		text_numberOfTasks.setLayoutData(fd_text_numberOfTasks);
//
//		Label lblTasksPerDeveloper = new Label(grpStatic, SWT.NONE);
//		FormData fd_lblTasksPerDeveloper = new FormData();
//		fd_lblTasksPerDeveloper.left = new FormAttachment(text_numberOfTasks, 5, SWT.RIGHT);
//		fd_lblTasksPerDeveloper.bottom = new FormAttachment(text_numberOfTasks, 0, SWT.BOTTOM);
//		lblTasksPerDeveloper.setLayoutData(fd_lblTasksPerDeveloper);
//		lblTasksPerDeveloper.setText("tasks per developer by: ");
//
//		DateTime dateTimeBy = new DateTime(grpStatic, SWT.BORDER);
//		FormData fd_dateTimeBy = new FormData();
//		fd_dateTimeBy.left = new FormAttachment(lblTasksPerDeveloper, 5, SWT.RIGHT);
//		fd_dateTimeBy.bottom = new FormAttachment(lblTasksPerDeveloper, 0, SWT.BOTTOM);
//		dateTimeBy.setLayoutData(fd_dateTimeBy);
//
//		Button btnMaxTaskNumberButton = new Button(grpStatic, SWT.CHECK);
//		btnMaxTaskNumberButton.setText("Max tasks to recommend:");
//		FormData fd_btnMaxTaskNumberButton = new FormData();
//		fd_btnMaxTaskNumberButton.left = new FormAttachment(btnNoMoreThan, 0, SWT.LEFT);
//		fd_btnMaxTaskNumberButton.top = new FormAttachment(btnNoMoreThan, 10, SWT.BOTTOM);
//		btnMaxTaskNumberButton.setLayoutData(fd_btnMaxTaskNumberButton);
//
//		Text text_maxTask = new Text(grpStatic, SWT.BORDER);
//		FormData fd_text_maxTask = new FormData();
//		fd_text_maxTask.left = new FormAttachment(btnMaxTaskNumberButton, 5, SWT.RIGHT);
//		fd_text_maxTask.bottom = new FormAttachment(btnMaxTaskNumberButton, 0, SWT.BOTTOM);
//		fd_text_maxTask.width = 50;
//		text_maxTask.setLayoutData(fd_text_maxTask);


		scrolledComposite.setContent(compositeInScrolledComposite);
		scrolledComposite.setMinSize(compositeInScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}
