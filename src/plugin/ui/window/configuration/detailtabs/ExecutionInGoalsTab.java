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

public class ExecutionInGoalsTab {
	TabItem tabItem;
	private Text text_numberOfTasksFixUnitTestingProblems;
	private DateTime dateTimeByFixUnitTestingProblems;
	private Text text_maxTaskFixUnitTestingProblems;
	private Text text_numberOfTasksReviewUnverifiedExceptions;
	private DateTime dateTimeByReviewUnverifiedExceptions;
	private Text text_maxTaskReviewUnverifiedExceptions;
	private Text text_numberOfTasksReviewUnverifiedFailures;
	private DateTime dateTimeByReviewUnverifiedFailures;
	private Text text_maxTaskReviewUnverifiedFailures;
	private Text text_numberOfTasksReviewUnverifiedOutcomes;
	private DateTime dateTimeByReviewUnverifiedOutcomes;
	private Text text_maxTaskReviewUnverifiedOutcomes;

	public ExecutionInGoalsTab(TabFolder tabFolder) {
		tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("Execution");

		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tabItem.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Composite compositeInScrolledComposite = new Composite(scrolledComposite, SWT.NONE);
		compositeInScrolledComposite.setLayout(new FormLayout());

		// TO DO: add content into compositeInScrolledComposite
		Group grp_fixUnitTestingProblems = new Group(compositeInScrolledComposite, SWT.NONE);
		grp_fixUnitTestingProblems.setText("Fix Unit Testing Problems");
		grp_fixUnitTestingProblems.setLayout(new FormLayout());
		FormData fd_grp_fixUnitTestingProblems = new FormData();
		fd_grp_fixUnitTestingProblems.right = new FormAttachment(100, -5);
		fd_grp_fixUnitTestingProblems.top = new FormAttachment(0, 10);
		fd_grp_fixUnitTestingProblems.left = new FormAttachment(0, 5);
		grp_fixUnitTestingProblems.setLayoutData(fd_grp_fixUnitTestingProblems);

		Button btnPerformAllTasksFixUnitTestingProblems = new Button(grp_fixUnitTestingProblems, SWT.RADIO);
		FormData fd_btnPerformAllTasksFixUnitTestingProblems = new FormData();
		fd_btnPerformAllTasksFixUnitTestingProblems.left = new FormAttachment(0, 5);
		btnPerformAllTasksFixUnitTestingProblems.setLayoutData(fd_btnPerformAllTasksFixUnitTestingProblems);
		btnPerformAllTasksFixUnitTestingProblems.setText("Perform all tasks");

		Button btnPerformNoneTaskFixUnitTestingProblems = new Button(grp_fixUnitTestingProblems, SWT.RADIO);
		FormData fd_btnPerformNoneTaskFixUnitTestingProblems = new FormData();
		fd_btnPerformNoneTaskFixUnitTestingProblems.top = new FormAttachment(btnPerformAllTasksFixUnitTestingProblems, 10, SWT.BOTTOM);
		fd_btnPerformNoneTaskFixUnitTestingProblems.left = new FormAttachment(btnPerformAllTasksFixUnitTestingProblems, 0, SWT.LEFT);
		btnPerformNoneTaskFixUnitTestingProblems.setLayoutData(fd_btnPerformNoneTaskFixUnitTestingProblems);
		btnPerformNoneTaskFixUnitTestingProblems.setText("Don't perform tasks");

		Button btnNoMoreThanFixUnitTestingProblems = new Button(grp_fixUnitTestingProblems, SWT.RADIO);
		FormData fd_btnNoMoreThanFixUnitTestingProblems = new FormData();
		fd_btnNoMoreThanFixUnitTestingProblems.top = new FormAttachment(btnPerformNoneTaskFixUnitTestingProblems, 10, SWT.BOTTOM);
		fd_btnNoMoreThanFixUnitTestingProblems.left = new FormAttachment(btnPerformNoneTaskFixUnitTestingProblems, 0, SWT.LEFT);
		btnNoMoreThanFixUnitTestingProblems.setLayoutData(fd_btnNoMoreThanFixUnitTestingProblems);
		btnNoMoreThanFixUnitTestingProblems.setText("No more than");

		text_numberOfTasksFixUnitTestingProblems = new Text(grp_fixUnitTestingProblems, SWT.BORDER);
		FormData fd_text_numberOfTasksFixUnitTestingProblems = new FormData();
		fd_text_numberOfTasksFixUnitTestingProblems.left = new FormAttachment(btnNoMoreThanFixUnitTestingProblems, 5, SWT.RIGHT);
		fd_text_numberOfTasksFixUnitTestingProblems.bottom = new FormAttachment(btnNoMoreThanFixUnitTestingProblems, 0, SWT.BOTTOM);
		fd_text_numberOfTasksFixUnitTestingProblems.width = 50;
		text_numberOfTasksFixUnitTestingProblems.setLayoutData(fd_text_numberOfTasksFixUnitTestingProblems);

		Label lblTasksPerDeveloperFixUnitTestingProblems = new Label(grp_fixUnitTestingProblems, SWT.NONE);
		FormData fd_lblTasksPerDeveloperFixUnitTestingProblems = new FormData();
		fd_lblTasksPerDeveloperFixUnitTestingProblems.left = new FormAttachment(text_numberOfTasksFixUnitTestingProblems, 5, SWT.RIGHT);
		fd_lblTasksPerDeveloperFixUnitTestingProblems.bottom = new FormAttachment(text_numberOfTasksFixUnitTestingProblems, 0, SWT.BOTTOM);
		lblTasksPerDeveloperFixUnitTestingProblems.setLayoutData(fd_lblTasksPerDeveloperFixUnitTestingProblems);
		lblTasksPerDeveloperFixUnitTestingProblems.setText("tasks per developer by: ");

		dateTimeByFixUnitTestingProblems = new DateTime(grp_fixUnitTestingProblems, SWT.BORDER);
		FormData fd_dateTimeByFixUnitTestingProblems = new FormData();
		fd_dateTimeByFixUnitTestingProblems.left = new FormAttachment(lblTasksPerDeveloperFixUnitTestingProblems, 5, SWT.RIGHT);
		fd_dateTimeByFixUnitTestingProblems.bottom = new FormAttachment(lblTasksPerDeveloperFixUnitTestingProblems, 0, SWT.BOTTOM);
		dateTimeByFixUnitTestingProblems.setLayoutData(fd_dateTimeByFixUnitTestingProblems);

		Button btnMaxTaskNumberButtonFixUnitTestingProblems = new Button(grp_fixUnitTestingProblems, SWT.CHECK);
		btnMaxTaskNumberButtonFixUnitTestingProblems.setText("Max tasks to recommend:");
		FormData fd_btnMaxTaskNumberButtonFixUnitTestingProblems = new FormData();
		fd_btnMaxTaskNumberButtonFixUnitTestingProblems.left = new FormAttachment(btnNoMoreThanFixUnitTestingProblems, 0, SWT.LEFT);
		fd_btnMaxTaskNumberButtonFixUnitTestingProblems.top = new FormAttachment(btnNoMoreThanFixUnitTestingProblems, 10, SWT.BOTTOM);
		btnMaxTaskNumberButtonFixUnitTestingProblems.setLayoutData(fd_btnMaxTaskNumberButtonFixUnitTestingProblems);

		text_maxTaskFixUnitTestingProblems = new Text(grp_fixUnitTestingProblems, SWT.BORDER);
		FormData fd_text_maxTaskFixUnitTestingProblems = new FormData();
		fd_text_maxTaskFixUnitTestingProblems.left = new FormAttachment(btnMaxTaskNumberButtonFixUnitTestingProblems, 5, SWT.RIGHT);
		fd_text_maxTaskFixUnitTestingProblems.bottom = new FormAttachment(btnMaxTaskNumberButtonFixUnitTestingProblems, 0, SWT.BOTTOM);
		fd_text_maxTaskFixUnitTestingProblems.width = 50;
		text_maxTaskFixUnitTestingProblems.setLayoutData(fd_text_maxTaskFixUnitTestingProblems);

		Group grp_ReviewUnverifiedExceptions = new Group(compositeInScrolledComposite, SWT.NONE);
		grp_ReviewUnverifiedExceptions.setText("Review Unverified Unit Testing Exceptions");
		grp_ReviewUnverifiedExceptions.setLayout(new FormLayout());
		FormData fd_grp_ReviewUnverifiedExceptions = new FormData();
		fd_grp_ReviewUnverifiedExceptions.right = new FormAttachment(100, -5);
		fd_grp_ReviewUnverifiedExceptions.top = new FormAttachment(grp_fixUnitTestingProblems, 10, SWT.BOTTOM);
		fd_grp_ReviewUnverifiedExceptions.left = new FormAttachment(0, 5);
		grp_ReviewUnverifiedExceptions.setLayoutData(fd_grp_ReviewUnverifiedExceptions);

		Button btnPerformAllTasksReviewUnverifiedExceptions = new Button(grp_ReviewUnverifiedExceptions, SWT.RADIO);
		FormData fd_btnPerformAllTasksReviewUnverifiedExceptions = new FormData();
		fd_btnPerformAllTasksReviewUnverifiedExceptions.left = new FormAttachment(0, 5);
		btnPerformAllTasksReviewUnverifiedExceptions.setLayoutData(fd_btnPerformAllTasksReviewUnverifiedExceptions);
		btnPerformAllTasksReviewUnverifiedExceptions.setText("Perform all tasks");

		Button btnPerformNoneTaskReviewUnverifiedExceptions = new Button(grp_ReviewUnverifiedExceptions, SWT.RADIO);
		FormData fd_btnPerformNoneTaskReviewUnverifiedExceptions = new FormData();
		fd_btnPerformNoneTaskReviewUnverifiedExceptions.top = new FormAttachment(btnPerformAllTasksReviewUnverifiedExceptions, 10, SWT.BOTTOM);
		fd_btnPerformNoneTaskReviewUnverifiedExceptions.left = new FormAttachment(btnPerformAllTasksReviewUnverifiedExceptions, 0, SWT.LEFT);
		btnPerformNoneTaskReviewUnverifiedExceptions.setLayoutData(fd_btnPerformNoneTaskReviewUnverifiedExceptions);
		btnPerformNoneTaskReviewUnverifiedExceptions.setText("Don't perform tasks");

		Button btnNoMoreThanReviewUnverifiedExceptions = new Button(grp_ReviewUnverifiedExceptions, SWT.RADIO);
		FormData fd_btnNoMoreThanReviewUnverifiedExceptions = new FormData();
		fd_btnNoMoreThanReviewUnverifiedExceptions.top = new FormAttachment(btnPerformNoneTaskReviewUnverifiedExceptions, 10, SWT.BOTTOM);
		fd_btnNoMoreThanReviewUnverifiedExceptions.left = new FormAttachment(btnPerformNoneTaskReviewUnverifiedExceptions, 0, SWT.LEFT);
		btnNoMoreThanReviewUnverifiedExceptions.setLayoutData(fd_btnNoMoreThanReviewUnverifiedExceptions);
		btnNoMoreThanReviewUnverifiedExceptions.setText("No more than");

		text_numberOfTasksReviewUnverifiedExceptions = new Text(grp_ReviewUnverifiedExceptions, SWT.BORDER);
		FormData fd_text_numberOfTasksReviewUnverifiedExceptions = new FormData();
		fd_text_numberOfTasksReviewUnverifiedExceptions.left = new FormAttachment(btnNoMoreThanReviewUnverifiedExceptions, 5, SWT.RIGHT);
		fd_text_numberOfTasksReviewUnverifiedExceptions.bottom = new FormAttachment(btnNoMoreThanReviewUnverifiedExceptions, 0, SWT.BOTTOM);
		fd_text_numberOfTasksReviewUnverifiedExceptions.width = 50;
		text_numberOfTasksReviewUnverifiedExceptions.setLayoutData(fd_text_numberOfTasksReviewUnverifiedExceptions);

		Label lblTasksPerDeveloperReviewUnverifiedExceptions = new Label(grp_ReviewUnverifiedExceptions, SWT.NONE);
		FormData fd_lblTasksPerDeveloperReviewUnverifiedExceptions = new FormData();
		fd_lblTasksPerDeveloperReviewUnverifiedExceptions.left = new FormAttachment(text_numberOfTasksReviewUnverifiedExceptions, 5, SWT.RIGHT);
		fd_lblTasksPerDeveloperReviewUnverifiedExceptions.bottom = new FormAttachment(text_numberOfTasksReviewUnverifiedExceptions, 0, SWT.BOTTOM);
		lblTasksPerDeveloperReviewUnverifiedExceptions.setLayoutData(fd_lblTasksPerDeveloperReviewUnverifiedExceptions);
		lblTasksPerDeveloperReviewUnverifiedExceptions.setText("tasks per developer by: ");

		dateTimeByReviewUnverifiedExceptions = new DateTime(grp_ReviewUnverifiedExceptions, SWT.BORDER);
		FormData fd_dateTimeByReviewUnverifiedExceptions = new FormData();
		fd_dateTimeByReviewUnverifiedExceptions.left = new FormAttachment(lblTasksPerDeveloperReviewUnverifiedExceptions, 5, SWT.RIGHT);
		fd_dateTimeByReviewUnverifiedExceptions.bottom = new FormAttachment(lblTasksPerDeveloperReviewUnverifiedExceptions, 0, SWT.BOTTOM);
		dateTimeByReviewUnverifiedExceptions.setLayoutData(fd_dateTimeByReviewUnverifiedExceptions);

		Button btnMaxTaskNumberButtonReviewUnverifiedExceptions = new Button(grp_ReviewUnverifiedExceptions, SWT.CHECK);
		btnMaxTaskNumberButtonReviewUnverifiedExceptions.setText("Max tasks to recommend:");
		FormData fd_btnMaxTaskNumberButtonReviewUnverifiedExceptions = new FormData();
		fd_btnMaxTaskNumberButtonReviewUnverifiedExceptions.left = new FormAttachment(btnNoMoreThanReviewUnverifiedExceptions, 0, SWT.LEFT);
		fd_btnMaxTaskNumberButtonReviewUnverifiedExceptions.top = new FormAttachment(btnNoMoreThanReviewUnverifiedExceptions, 10, SWT.BOTTOM);
		btnMaxTaskNumberButtonReviewUnverifiedExceptions.setLayoutData(fd_btnMaxTaskNumberButtonReviewUnverifiedExceptions);

		text_maxTaskReviewUnverifiedExceptions = new Text(grp_ReviewUnverifiedExceptions, SWT.BORDER);
		FormData fd_text_maxTaskReviewUnverifiedExceptions = new FormData();
		fd_text_maxTaskReviewUnverifiedExceptions.left = new FormAttachment(btnMaxTaskNumberButtonReviewUnverifiedExceptions, 5, SWT.RIGHT);
		fd_text_maxTaskReviewUnverifiedExceptions.bottom = new FormAttachment(btnMaxTaskNumberButtonReviewUnverifiedExceptions, 0, SWT.BOTTOM);
		fd_text_maxTaskReviewUnverifiedExceptions.width = 50;
		text_maxTaskReviewUnverifiedExceptions.setLayoutData(fd_text_maxTaskReviewUnverifiedExceptions);

		Group grp_ReviewUnverifiedFailures = new Group(compositeInScrolledComposite, SWT.NONE);
		grp_ReviewUnverifiedFailures.setText("Review Unverified Unit Testing Exceptions");
		grp_ReviewUnverifiedFailures.setLayout(new FormLayout());
		FormData fd_grp_ReviewUnverifiedFailures = new FormData();
		fd_grp_ReviewUnverifiedFailures.right = new FormAttachment(100, -5);
		fd_grp_ReviewUnverifiedFailures.top = new FormAttachment(grp_ReviewUnverifiedExceptions, 10, SWT.BOTTOM);
		fd_grp_ReviewUnverifiedFailures.left = new FormAttachment(0, 5);
		grp_ReviewUnverifiedFailures.setLayoutData(fd_grp_ReviewUnverifiedFailures);

		Button btnPerformAllTasksReviewUnverifiedFailures = new Button(grp_ReviewUnverifiedFailures, SWT.RADIO);
		FormData fd_btnPerformAllTasksReviewUnverifiedFailures = new FormData();
		fd_btnPerformAllTasksReviewUnverifiedFailures.left = new FormAttachment(0, 5);
		btnPerformAllTasksReviewUnverifiedFailures.setLayoutData(fd_btnPerformAllTasksReviewUnverifiedFailures);
		btnPerformAllTasksReviewUnverifiedFailures.setText("Perform all tasks");

		Button btnPerformNoneTaskReviewUnverifiedFailures = new Button(grp_ReviewUnverifiedFailures, SWT.RADIO);
		FormData fd_btnPerformNoneTaskReviewUnverifiedFailures = new FormData();
		fd_btnPerformNoneTaskReviewUnverifiedFailures.top = new FormAttachment(btnPerformAllTasksReviewUnverifiedFailures, 10, SWT.BOTTOM);
		fd_btnPerformNoneTaskReviewUnverifiedFailures.left = new FormAttachment(btnPerformAllTasksReviewUnverifiedFailures, 0, SWT.LEFT);
		btnPerformNoneTaskReviewUnverifiedFailures.setLayoutData(fd_btnPerformNoneTaskReviewUnverifiedFailures);
		btnPerformNoneTaskReviewUnverifiedFailures.setText("Don't perform tasks");

		Button btnNoMoreThanReviewUnverifiedFailures = new Button(grp_ReviewUnverifiedFailures, SWT.RADIO);
		FormData fd_btnNoMoreThanReviewUnverifiedFailures = new FormData();
		fd_btnNoMoreThanReviewUnverifiedFailures.top = new FormAttachment(btnPerformNoneTaskReviewUnverifiedFailures, 10, SWT.BOTTOM);
		fd_btnNoMoreThanReviewUnverifiedFailures.left = new FormAttachment(btnPerformNoneTaskReviewUnverifiedFailures, 0, SWT.LEFT);
		btnNoMoreThanReviewUnverifiedFailures.setLayoutData(fd_btnNoMoreThanReviewUnverifiedFailures);
		btnNoMoreThanReviewUnverifiedFailures.setText("No more than");

		text_numberOfTasksReviewUnverifiedFailures = new Text(grp_ReviewUnverifiedFailures, SWT.BORDER);
		FormData fd_text_numberOfTasksReviewUnverifiedFailures = new FormData();
		fd_text_numberOfTasksReviewUnverifiedFailures.left = new FormAttachment(btnNoMoreThanReviewUnverifiedFailures, 5, SWT.RIGHT);
		fd_text_numberOfTasksReviewUnverifiedFailures.bottom = new FormAttachment(btnNoMoreThanReviewUnverifiedFailures, 0, SWT.BOTTOM);
		fd_text_numberOfTasksReviewUnverifiedFailures.width = 50;
		text_numberOfTasksReviewUnverifiedFailures.setLayoutData(fd_text_numberOfTasksReviewUnverifiedFailures);

		Label lblTasksPerDeveloperReviewUnverifiedFailures = new Label(grp_ReviewUnverifiedFailures, SWT.NONE);
		FormData fd_lblTasksPerDeveloperReviewUnverifiedFailures = new FormData();
		fd_lblTasksPerDeveloperReviewUnverifiedFailures.left = new FormAttachment(text_numberOfTasksReviewUnverifiedFailures, 5, SWT.RIGHT);
		fd_lblTasksPerDeveloperReviewUnverifiedFailures.bottom = new FormAttachment(text_numberOfTasksReviewUnverifiedFailures, 0, SWT.BOTTOM);
		lblTasksPerDeveloperReviewUnverifiedFailures.setLayoutData(fd_lblTasksPerDeveloperReviewUnverifiedFailures);
		lblTasksPerDeveloperReviewUnverifiedFailures.setText("tasks per developer by: ");

		dateTimeByReviewUnverifiedFailures = new DateTime(grp_ReviewUnverifiedFailures, SWT.BORDER);
		FormData fd_dateTimeByReviewUnverifiedFailures = new FormData();
		fd_dateTimeByReviewUnverifiedFailures.left = new FormAttachment(lblTasksPerDeveloperReviewUnverifiedFailures, 5, SWT.RIGHT);
		fd_dateTimeByReviewUnverifiedFailures.bottom = new FormAttachment(lblTasksPerDeveloperReviewUnverifiedFailures, 0, SWT.BOTTOM);
		dateTimeByReviewUnverifiedFailures.setLayoutData(fd_dateTimeByReviewUnverifiedFailures);

		Button btnMaxTaskNumberButtonReviewUnverifiedFailures = new Button(grp_ReviewUnverifiedFailures, SWT.CHECK);
		btnMaxTaskNumberButtonReviewUnverifiedFailures.setText("Max tasks to recommend:");
		FormData fd_btnMaxTaskNumberButtonReviewUnverifiedFailures = new FormData();
		fd_btnMaxTaskNumberButtonReviewUnverifiedFailures.left = new FormAttachment(btnNoMoreThanReviewUnverifiedFailures, 0, SWT.LEFT);
		fd_btnMaxTaskNumberButtonReviewUnverifiedFailures.top = new FormAttachment(btnNoMoreThanReviewUnverifiedFailures, 10, SWT.BOTTOM);
		btnMaxTaskNumberButtonReviewUnverifiedFailures.setLayoutData(fd_btnMaxTaskNumberButtonReviewUnverifiedFailures);

		text_maxTaskReviewUnverifiedFailures = new Text(grp_ReviewUnverifiedFailures, SWT.BORDER);
		FormData fd_text_maxTaskReviewUnverifiedFailures = new FormData();
		fd_text_maxTaskReviewUnverifiedFailures.left = new FormAttachment(btnMaxTaskNumberButtonReviewUnverifiedFailures, 5, SWT.RIGHT);
		fd_text_maxTaskReviewUnverifiedFailures.bottom = new FormAttachment(btnMaxTaskNumberButtonReviewUnverifiedFailures, 0, SWT.BOTTOM);
		fd_text_maxTaskReviewUnverifiedFailures.width = 50;
		text_maxTaskReviewUnverifiedFailures.setLayoutData(fd_text_maxTaskReviewUnverifiedFailures);

		Group grp_ReviewUnverifiedOutcomes = new Group(compositeInScrolledComposite, SWT.NONE);
		grp_ReviewUnverifiedOutcomes.setText("Review Unverified Outcomes");
		grp_ReviewUnverifiedOutcomes.setLayout(new FormLayout());
		FormData fd_grp_ReviewUnverifiedOutcomes = new FormData();
		fd_grp_ReviewUnverifiedOutcomes.right = new FormAttachment(100, -5);
		fd_grp_ReviewUnverifiedOutcomes.top = new FormAttachment(grp_ReviewUnverifiedFailures, 10, SWT.BOTTOM);
		fd_grp_ReviewUnverifiedOutcomes.left = new FormAttachment(0, 5);
		grp_ReviewUnverifiedOutcomes.setLayoutData(fd_grp_ReviewUnverifiedOutcomes);

		Button btnPerformAllTasksReviewUnverifiedOutcomes = new Button(grp_ReviewUnverifiedOutcomes, SWT.RADIO);
		FormData fd_btnPerformAllTasksReviewUnverifiedOutcomes = new FormData();
		fd_btnPerformAllTasksReviewUnverifiedOutcomes.left = new FormAttachment(0, 5);
		btnPerformAllTasksReviewUnverifiedOutcomes.setLayoutData(fd_btnPerformAllTasksReviewUnverifiedOutcomes);
		btnPerformAllTasksReviewUnverifiedOutcomes.setText("Perform all tasks");

		Button btnPerformNoneTaskReviewUnverifiedOutcomes = new Button(grp_ReviewUnverifiedOutcomes, SWT.RADIO);
		FormData fd_btnPerformNoneTaskReviewUnverifiedOutcomes = new FormData();
		fd_btnPerformNoneTaskReviewUnverifiedOutcomes.top = new FormAttachment(btnPerformAllTasksReviewUnverifiedOutcomes, 10, SWT.BOTTOM);
		fd_btnPerformNoneTaskReviewUnverifiedOutcomes.left = new FormAttachment(btnPerformAllTasksReviewUnverifiedOutcomes, 0, SWT.LEFT);
		btnPerformNoneTaskReviewUnverifiedOutcomes.setLayoutData(fd_btnPerformNoneTaskReviewUnverifiedOutcomes);
		btnPerformNoneTaskReviewUnverifiedOutcomes.setText("Don't perform tasks");

		Button btnNoMoreThanReviewUnverifiedOutcomes = new Button(grp_ReviewUnverifiedOutcomes, SWT.RADIO);
		FormData fd_btnNoMoreThanReviewUnverifiedOutcomes = new FormData();
		fd_btnNoMoreThanReviewUnverifiedOutcomes.top = new FormAttachment(btnPerformNoneTaskReviewUnverifiedOutcomes, 10, SWT.BOTTOM);
		fd_btnNoMoreThanReviewUnverifiedOutcomes.left = new FormAttachment(btnPerformNoneTaskReviewUnverifiedOutcomes, 0, SWT.LEFT);
		btnNoMoreThanReviewUnverifiedOutcomes.setLayoutData(fd_btnNoMoreThanReviewUnverifiedOutcomes);
		btnNoMoreThanReviewUnverifiedOutcomes.setText("No more than");

		text_numberOfTasksReviewUnverifiedOutcomes = new Text(grp_ReviewUnverifiedOutcomes, SWT.BORDER);
		FormData fd_text_numberOfTasksReviewUnverifiedOutcomes = new FormData();
		fd_text_numberOfTasksReviewUnverifiedOutcomes.left = new FormAttachment(btnNoMoreThanReviewUnverifiedOutcomes, 5, SWT.RIGHT);
		fd_text_numberOfTasksReviewUnverifiedOutcomes.bottom = new FormAttachment(btnNoMoreThanReviewUnverifiedOutcomes, 0, SWT.BOTTOM);
		fd_text_numberOfTasksReviewUnverifiedOutcomes.width = 50;
		text_numberOfTasksReviewUnverifiedOutcomes.setLayoutData(fd_text_numberOfTasksReviewUnverifiedOutcomes);

		Label lblTasksPerDeveloperReviewUnverifiedOutcomes = new Label(grp_ReviewUnverifiedOutcomes, SWT.NONE);
		FormData fd_lblTasksPerDeveloperReviewUnverifiedOutcomes = new FormData();
		fd_lblTasksPerDeveloperReviewUnverifiedOutcomes.left = new FormAttachment(text_numberOfTasksReviewUnverifiedOutcomes, 5, SWT.RIGHT);
		fd_lblTasksPerDeveloperReviewUnverifiedOutcomes.bottom = new FormAttachment(text_numberOfTasksReviewUnverifiedOutcomes, 0, SWT.BOTTOM);
		lblTasksPerDeveloperReviewUnverifiedOutcomes.setLayoutData(fd_lblTasksPerDeveloperReviewUnverifiedOutcomes);
		lblTasksPerDeveloperReviewUnverifiedOutcomes.setText("tasks per developer by: ");

		dateTimeByReviewUnverifiedOutcomes = new DateTime(grp_ReviewUnverifiedOutcomes, SWT.BORDER);
		FormData fd_dateTimeByReviewUnverifiedOutcomes = new FormData();
		fd_dateTimeByReviewUnverifiedOutcomes.left = new FormAttachment(lblTasksPerDeveloperReviewUnverifiedOutcomes, 5, SWT.RIGHT);
		fd_dateTimeByReviewUnverifiedOutcomes.bottom = new FormAttachment(lblTasksPerDeveloperReviewUnverifiedOutcomes, 0, SWT.BOTTOM);
		dateTimeByReviewUnverifiedOutcomes.setLayoutData(fd_dateTimeByReviewUnverifiedOutcomes);

		Button btnMaxTaskNumberButtonReviewUnverifiedOutcomes = new Button(grp_ReviewUnverifiedOutcomes, SWT.CHECK);
		btnMaxTaskNumberButtonReviewUnverifiedOutcomes.setText("Max tasks to recommend:");
		FormData fd_btnMaxTaskNumberButtonReviewUnverifiedOutcomes = new FormData();
		fd_btnMaxTaskNumberButtonReviewUnverifiedOutcomes.left = new FormAttachment(btnNoMoreThanReviewUnverifiedOutcomes, 0, SWT.LEFT);
		fd_btnMaxTaskNumberButtonReviewUnverifiedOutcomes.top = new FormAttachment(btnNoMoreThanReviewUnverifiedOutcomes, 10, SWT.BOTTOM);
		btnMaxTaskNumberButtonReviewUnverifiedOutcomes.setLayoutData(fd_btnMaxTaskNumberButtonReviewUnverifiedOutcomes);

		text_maxTaskReviewUnverifiedOutcomes = new Text(grp_ReviewUnverifiedOutcomes, SWT.BORDER);
		FormData fd_text_maxTaskReviewUnverifiedOutcomes = new FormData();
		fd_text_maxTaskReviewUnverifiedOutcomes.left = new FormAttachment(btnMaxTaskNumberButtonReviewUnverifiedOutcomes, 5, SWT.RIGHT);
		fd_text_maxTaskReviewUnverifiedOutcomes.bottom = new FormAttachment(btnMaxTaskNumberButtonReviewUnverifiedOutcomes, 0, SWT.BOTTOM);
		fd_text_maxTaskReviewUnverifiedOutcomes.width = 50;
		text_maxTaskReviewUnverifiedOutcomes.setLayoutData(fd_text_maxTaskReviewUnverifiedOutcomes);

		scrolledComposite.setContent(compositeInScrolledComposite);
		scrolledComposite.setMinSize(compositeInScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
}
