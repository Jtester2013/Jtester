package plugin.ui.window.configuration.detailtabs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class SeveritiesInExecutionTab {
	TabItem tabItem;
	private Table table_SeverityForUncaughtExceptionType;
	private Table table_SensitiveMethods;

	public SeveritiesInExecutionTab(TabFolder tabFolder) {
		tabItem = new TabItem(tabFolder, SWT.None);
		tabItem.setText("Severities");

		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder,SWT.H_SCROLL | SWT.V_SCROLL);
		tabItem.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		Composite compositeInScrolledComposite = new Composite(scrolledComposite, SWT.NONE);
		compositeInScrolledComposite.setLayout(new FormLayout());
		
		// set contents of group grpInitialSeverityLevels
		Group grpInitialSeverityLevels = new Group(compositeInScrolledComposite, SWT.NONE);
		grpInitialSeverityLevels.setText("Initial Severity Levels");
		grpInitialSeverityLevels.setLayout(new FormLayout());
		FormData fd_grpInitialSeverityLevels = new FormData();
		fd_grpInitialSeverityLevels.left = new FormAttachment(0, 5);
		fd_grpInitialSeverityLevels.right = new FormAttachment(100, -5);
		grpInitialSeverityLevels.setLayoutData(fd_grpInitialSeverityLevels);
		
		Composite composite_left = new Composite(grpInitialSeverityLevels, SWT.NONE);
		composite_left.setLayout(new FormLayout());
		FormData fd_composite_left = new FormData();
		fd_composite_left.width = 250;
		fd_composite_left.bottom = new FormAttachment(100, -5);
		fd_composite_left.top = new FormAttachment(0, 35);
		fd_composite_left.left = new FormAttachment(0, 5);
		composite_left.setLayoutData(fd_composite_left);
		
		table_SeverityForUncaughtExceptionType = new Table(grpInitialSeverityLevels, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table_1 = new FormData();
		fd_table_1.right = new FormAttachment(composite_left, 311, SWT.RIGHT);
		fd_table_1.top = new FormAttachment(composite_left, 0, SWT.TOP);
		fd_table_1.right = new FormAttachment(100, -228);
		fd_table_1.left = new FormAttachment(composite_left, 6);
		fd_table_1.bottom = new FormAttachment(composite_left, 0, SWT.BOTTOM);
		table_SeverityForUncaughtExceptionType.setLayoutData(fd_table_1);
		table_SeverityForUncaughtExceptionType.setHeaderVisible(true);
		table_SeverityForUncaughtExceptionType.setLinesVisible(true);
		
		TableColumn tblclmnExceptionClassName = new TableColumn(table_SeverityForUncaughtExceptionType, SWT.NONE);
		tblclmnExceptionClassName.setWidth(173);
		tblclmnExceptionClassName.setText("Exception Class Name");
		
		TableColumn tblclmnInitialSeverity = new TableColumn(table_SeverityForUncaughtExceptionType, SWT.NONE);
		tblclmnInitialSeverity.setWidth(291);
		tblclmnInitialSeverity.setText("Initial Severity");
		
		Button btnAdd = new Button(grpInitialSeverityLevels, SWT.NONE);
		FormData fd_btnAdd = new FormData();
		fd_btnAdd.width = 60;
		fd_btnAdd.top = new FormAttachment(composite_left, 0, SWT.TOP);
		
		Label lblAssertionFailures = new Label(composite_left, SWT.NONE);
		FormData fd_lblAssertionFailures = new FormData();
		fd_lblAssertionFailures.left = new FormAttachment(0);
		lblAssertionFailures.setLayoutData(fd_lblAssertionFailures);
		lblAssertionFailures.setText("Assertion Failures:");
		
		Label lblProfileProblems = new Label(composite_left, SWT.NONE);
		FormData fd_lblProfileProblems = new FormData();
		fd_lblProfileProblems.left = new FormAttachment(0);
		lblProfileProblems.setLayoutData(fd_lblProfileProblems);
		lblProfileProblems.setText("Profile Problems:");
		
		Combo combo_AssertionFailures = new Combo(composite_left, SWT.NONE);
		fd_lblAssertionFailures.top = new FormAttachment(combo_AssertionFailures, 3, SWT.TOP);
		FormData fd_combo_AssertionFailures = new FormData();
		fd_combo_AssertionFailures.top = new FormAttachment(0, 7);
		fd_combo_AssertionFailures.right = new FormAttachment(100, -10);
		combo_AssertionFailures.setLayoutData(fd_combo_AssertionFailures);
		
		Combo combo_ProfileProblems = new Combo(composite_left, SWT.NONE);
		fd_lblProfileProblems.top = new FormAttachment(combo_ProfileProblems, 3, SWT.TOP);
		FormData fd_combo_ProfileProblems = new FormData();
		fd_combo_ProfileProblems.right = new FormAttachment(combo_AssertionFailures, 0, SWT.RIGHT);
		combo_ProfileProblems.setLayoutData(fd_combo_ProfileProblems);
		
		Combo combo_DbcViolations = new Combo(composite_left, SWT.NONE);
		fd_combo_ProfileProblems.bottom = new FormAttachment(combo_DbcViolations, -6);
		FormData fd_combo_DbcViolations = new FormData();
		fd_combo_DbcViolations.top = new FormAttachment(0, 69);
		fd_combo_DbcViolations.right = new FormAttachment(combo_AssertionFailures, 0, SWT.RIGHT);
		combo_DbcViolations.setLayoutData(fd_combo_DbcViolations);
		
		Combo combo_Outcomes = new Combo(composite_left, SWT.NONE);
		FormData fd_combo_Outcomes = new FormData();
		fd_combo_Outcomes.top = new FormAttachment(combo_DbcViolations, 6);
		fd_combo_Outcomes.right = new FormAttachment(combo_AssertionFailures, 0, SWT.RIGHT);
		combo_Outcomes.setLayoutData(fd_combo_Outcomes);
		
		Combo combo_UncaughtExceptions = new Combo(composite_left, SWT.NONE);
		FormData fd_combo_UncaughtExceptions = new FormData();
		fd_combo_UncaughtExceptions.top = new FormAttachment(combo_Outcomes, 6);
		fd_combo_UncaughtExceptions.right = new FormAttachment(combo_AssertionFailures, 0, SWT.RIGHT);
		combo_UncaughtExceptions.setLayoutData(fd_combo_UncaughtExceptions);
		
		Label lblDbcViolations = new Label(composite_left, SWT.NONE);
		FormData fd_lblDbcViolations = new FormData();
		fd_lblDbcViolations.left = new FormAttachment(0);
		fd_lblDbcViolations.top = new FormAttachment(combo_DbcViolations, 3, SWT.TOP);
		lblDbcViolations.setLayoutData(fd_lblDbcViolations);
		lblDbcViolations.setText("DbC Violations:");
		
		Label lblOutcomes = new Label(composite_left, SWT.NONE);
		FormData fd_lblOutcomes = new FormData();
		fd_lblOutcomes.top = new FormAttachment(combo_Outcomes, 3, SWT.TOP);
		fd_lblOutcomes.left = new FormAttachment(lblAssertionFailures, 0, SWT.LEFT);
		lblOutcomes.setLayoutData(fd_lblOutcomes);
		lblOutcomes.setText("Outcomes:");
		
		Label lblUncaughtExceptions = new Label(composite_left, SWT.NONE);
		FormData fd_lblUncaughtExceptions = new FormData();
		fd_lblUncaughtExceptions.bottom = new FormAttachment(100, -10);
		fd_lblUncaughtExceptions.left = new FormAttachment(lblAssertionFailures, 0, SWT.LEFT);
		lblUncaughtExceptions.setLayoutData(fd_lblUncaughtExceptions);
		lblUncaughtExceptions.setText("Uncaught Exceptions:");
		fd_btnAdd.left = new FormAttachment(table_SeverityForUncaughtExceptionType, 6);
		btnAdd.setLayoutData(fd_btnAdd);
		btnAdd.setText("Add");
		
		Button btnEdit_2 = new Button(grpInitialSeverityLevels, SWT.NONE);
		FormData fd_btnEdit_2 = new FormData();
		fd_btnEdit_2.width = 60;
		fd_btnEdit_2.top = new FormAttachment(btnAdd, 6);
		fd_btnEdit_2.left = new FormAttachment(table_SeverityForUncaughtExceptionType, 6);
		btnEdit_2.setLayoutData(fd_btnEdit_2);
		btnEdit_2.setText("Edit");
		
		Button btnRemove_1 = new Button(grpInitialSeverityLevels, SWT.NONE);
		FormData fd_btnRemove_1 = new FormData();
		fd_btnRemove_1.width = 60;
		fd_btnRemove_1.top = new FormAttachment(btnEdit_2, 6);
		fd_btnRemove_1.left = new FormAttachment(table_SeverityForUncaughtExceptionType, 6);
		btnRemove_1.setLayoutData(fd_btnRemove_1);
		btnRemove_1.setText("Remove");
		
		Label lblSeverityForSpecific = new Label(grpInitialSeverityLevels, SWT.NONE);
		FormData fd_lblSeverityForSpecific = new FormData();
		fd_lblSeverityForSpecific.bottom = new FormAttachment(table_SeverityForUncaughtExceptionType, -8);
		fd_lblSeverityForSpecific.left = new FormAttachment(table_SeverityForUncaughtExceptionType, 0, SWT.LEFT);
		lblSeverityForSpecific.setLayoutData(fd_lblSeverityForSpecific);
		lblSeverityForSpecific.setText("Severity for specific uncaught exception types:");
		
		// set contents of group grpInitialSeverityLevels
		Group grpSeverityModifiersincrease = new Group(compositeInScrolledComposite, SWT.NONE);
		grpSeverityModifiersincrease.setText("Severity Modifiers (Increase or Decrease Severity Levels)");
		grpSeverityModifiersincrease.setLayout(new FormLayout());
		FormData fd_grpSeverityModifiersincrease = new FormData();
		fd_grpSeverityModifiersincrease.right = new FormAttachment(0, 475);
		fd_grpSeverityModifiersincrease.top = new FormAttachment(grpInitialSeverityLevels, 6, SWT.BOTTOM);
		fd_grpSeverityModifiersincrease.left = new FormAttachment(0, 5);
		fd_grpSeverityModifiersincrease.right = new FormAttachment(100, -5);
		grpSeverityModifiersincrease.setLayoutData(fd_grpSeverityModifiersincrease);
		
		Combo combo_RuntimeExceptions = new Combo(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_combo_RuntimeExceptions = new FormData();
		fd_combo_RuntimeExceptions.top = new FormAttachment(0, 10);
		fd_combo_RuntimeExceptions.right = new FormAttachment(0, 500);
		combo_RuntimeExceptions.setLayoutData(fd_combo_RuntimeExceptions);
		
		Combo combo_TasksForUserDefinedTests = new Combo(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_combo_TasksForUserDefinedTests = new FormData();
		fd_combo_TasksForUserDefinedTests.top = new FormAttachment(combo_RuntimeExceptions, 6);
		fd_combo_TasksForUserDefinedTests.right = new FormAttachment(combo_RuntimeExceptions, 0, SWT.RIGHT);
		combo_TasksForUserDefinedTests.setLayoutData(fd_combo_TasksForUserDefinedTests);
		
		Label lblRuntimeExceptionsValidated = new Label(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_lblRuntimeExceptionsValidated = new FormData();
		fd_lblRuntimeExceptionsValidated.top = new FormAttachment(combo_RuntimeExceptions, 0, SWT.TOP);
		fd_lblRuntimeExceptionsValidated.left = new FormAttachment(0, 10);
		lblRuntimeExceptionsValidated.setLayoutData(fd_lblRuntimeExceptionsValidated);
		lblRuntimeExceptionsValidated.setText("Runtime exceptions validated by BugDetective:");
		
		Combo combo_ExceptionDeclaredInThrows = new Combo(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_combo_ExceptionDeclaredInThrows = new FormData();
		fd_combo_ExceptionDeclaredInThrows.top = new FormAttachment(combo_TasksForUserDefinedTests, 6);
		fd_combo_ExceptionDeclaredInThrows.left = new FormAttachment(combo_RuntimeExceptions, 0, SWT.LEFT);
		combo_ExceptionDeclaredInThrows.setLayoutData(fd_combo_ExceptionDeclaredInThrows);
		
		Combo combo_DuplicateExceptions = new Combo(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_combo_DuplicateExceptions = new FormData();
		fd_combo_DuplicateExceptions.top = new FormAttachment(combo_ExceptionDeclaredInThrows, 6);
		fd_combo_DuplicateExceptions.left = new FormAttachment(combo_RuntimeExceptions, 0, SWT.LEFT);
		combo_DuplicateExceptions.setLayoutData(fd_combo_DuplicateExceptions);
		
		Combo combo_ExceptionsThrownByCode = new Combo(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_combo_ExceptionsThrownByCode = new FormData();
		fd_combo_ExceptionsThrownByCode.top = new FormAttachment(combo_DuplicateExceptions, 6);
		fd_combo_ExceptionsThrownByCode.left = new FormAttachment(combo_RuntimeExceptions, 0, SWT.LEFT);
		combo_ExceptionsThrownByCode.setLayoutData(fd_combo_ExceptionsThrownByCode);
		
		Combo combo_NullPointerORPreExceptionFromTestInput = new Combo(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_combo_NullPointerORPreExceptionFromTestInput = new FormData();
		fd_combo_NullPointerORPreExceptionFromTestInput.top = new FormAttachment(combo_ExceptionsThrownByCode, 6);
		fd_combo_NullPointerORPreExceptionFromTestInput.right = new FormAttachment(combo_RuntimeExceptions, 0, SWT.RIGHT);
		combo_NullPointerORPreExceptionFromTestInput.setLayoutData(fd_combo_NullPointerORPreExceptionFromTestInput);
		
		Combo combo_TaskForPblicMethod = new Combo(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_combo_TaskForPblicMethod = new FormData();
		fd_combo_TaskForPblicMethod.top = new FormAttachment(combo_NullPointerORPreExceptionFromTestInput, 6);
		fd_combo_TaskForPblicMethod.right = new FormAttachment(combo_RuntimeExceptions, 0, SWT.RIGHT);
		combo_TaskForPblicMethod.setLayoutData(fd_combo_TaskForPblicMethod);
		
		Combo combo_TaskForMethodStub = new Combo(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_combo_TaskForMethodStub = new FormData();
		fd_combo_TaskForMethodStub.top = new FormAttachment(combo_TaskForPblicMethod, 6);
		fd_combo_TaskForMethodStub.right = new FormAttachment(combo_RuntimeExceptions, 0, SWT.RIGHT);
		combo_TaskForMethodStub.setLayoutData(fd_combo_TaskForMethodStub);
		
		Combo combo_TaskUnverified = new Combo(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_combo_TaskUnverified = new FormData();
		fd_combo_TaskUnverified.top = new FormAttachment(combo_TaskForMethodStub, 6);
		fd_combo_TaskUnverified.right = new FormAttachment(combo_RuntimeExceptions, 0, SWT.RIGHT);
		combo_TaskUnverified.setLayoutData(fd_combo_TaskUnverified);
		
		Combo combo_SensitiveMethodException = new Combo(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_combo_SensitiveMethodException = new FormData();
		fd_combo_SensitiveMethodException.top = new FormAttachment(combo_TaskUnverified, 6);
		fd_combo_SensitiveMethodException.right = new FormAttachment(combo_RuntimeExceptions, 0, SWT.RIGHT);
		combo_SensitiveMethodException.setLayoutData(fd_combo_SensitiveMethodException);
		
		Label lblExceptionsFromSensitive = new Label(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_lblExceptionsFromSensitive = new FormData();
		fd_lblExceptionsFromSensitive.top = new FormAttachment(combo_SensitiveMethodException, 0, SWT.TOP);
		fd_lblExceptionsFromSensitive.left = new FormAttachment(lblRuntimeExceptionsValidated, 0, SWT.LEFT);
		lblExceptionsFromSensitive.setLayoutData(fd_lblExceptionsFromSensitive);
		lblExceptionsFromSensitive.setText("Exceptions from sensitive methods:");
		
		Label lblUnverifiedTasks = new Label(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_lblUnverifiedTasks = new FormData();
		fd_lblUnverifiedTasks.top = new FormAttachment(combo_TaskUnverified, 0, SWT.TOP);
		fd_lblUnverifiedTasks.left = new FormAttachment(lblRuntimeExceptionsValidated, 0, SWT.LEFT);
		lblUnverifiedTasks.setLayoutData(fd_lblUnverifiedTasks);
		lblUnverifiedTasks.setText("Unverified Tasks:");
		
		Label lblTasksForMethods = new Label(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_lblTasksForMethods = new FormData();
		fd_lblTasksForMethods.top = new FormAttachment(combo_TaskForMethodStub, 0, SWT.TOP);
		fd_lblTasksForMethods.left = new FormAttachment(lblRuntimeExceptionsValidated, 0, SWT.LEFT);
		lblTasksForMethods.setLayoutData(fd_lblTasksForMethods);
		lblTasksForMethods.setText("Tasks for methods with stubs:");
		
		Label lblTasksForPublic = new Label(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_lblTasksForPublic = new FormData();
		fd_lblTasksForPublic.top = new FormAttachment(combo_TaskForPblicMethod, 0, SWT.TOP);
		fd_lblTasksForPublic.left = new FormAttachment(0, 10);
		lblTasksForPublic.setLayoutData(fd_lblTasksForPublic);
		lblTasksForPublic.setText("Tasks for public methods:");
		
		Label lblDirectNullpointerexceptionsOr = new Label(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_lblDirectNullpointerexceptionsOr = new FormData();
		fd_lblDirectNullpointerexceptionsOr.top = new FormAttachment(combo_NullPointerORPreExceptionFromTestInput, 0, SWT.TOP);
		fd_lblDirectNullpointerexceptionsOr.left = new FormAttachment(lblRuntimeExceptionsValidated, 0, SWT.LEFT);
		lblDirectNullpointerexceptionsOr.setLayoutData(fd_lblDirectNullpointerexceptionsOr);
		lblDirectNullpointerexceptionsOr.setText("Direct NullPointerExceptions or PreExceptions from test input:");
		
		Label lblExceptionsExplicitlyThrown = new Label(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_lblExceptionsExplicitlyThrown = new FormData();
		fd_lblExceptionsExplicitlyThrown.top = new FormAttachment(combo_ExceptionsThrownByCode, 0, SWT.TOP);
		fd_lblExceptionsExplicitlyThrown.left = new FormAttachment(0, 10);
		lblExceptionsExplicitlyThrown.setLayoutData(fd_lblExceptionsExplicitlyThrown);
		lblExceptionsExplicitlyThrown.setText("Exceptions explicitly thrown by the tested code:");
		
		Label lblDuplicateExceptions = new Label(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_lblDuplicateExceptions = new FormData();
		fd_lblDuplicateExceptions.top = new FormAttachment(combo_DuplicateExceptions, 0, SWT.TOP);
		fd_lblDuplicateExceptions.left = new FormAttachment(lblRuntimeExceptionsValidated, 0, SWT.LEFT);
		lblDuplicateExceptions.setLayoutData(fd_lblDuplicateExceptions);
		lblDuplicateExceptions.setText("Duplicate exceptions:");
		
		Label lblExceptionDeclaredIn = new Label(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_lblExceptionDeclaredIn = new FormData();
		fd_lblExceptionDeclaredIn.top = new FormAttachment(combo_ExceptionDeclaredInThrows, 0, SWT.TOP);
		fd_lblExceptionDeclaredIn.left = new FormAttachment(lblRuntimeExceptionsValidated, 0, SWT.LEFT);
		lblExceptionDeclaredIn.setLayoutData(fd_lblExceptionDeclaredIn);
		lblExceptionDeclaredIn.setText("Exception declared in throws clause or @throws:");
		
		Label lblTasksForUser = new Label(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_lblTasksForUser = new FormData();
		fd_lblTasksForUser.top = new FormAttachment(combo_TasksForUserDefinedTests, 0, SWT.TOP);
		fd_lblTasksForUser.left = new FormAttachment(lblRuntimeExceptionsValidated, 0, SWT.LEFT);
		lblTasksForUser.setLayoutData(fd_lblTasksForUser);
		lblTasksForUser.setText("Tasks for user defined tests:");
		
		table_SensitiveMethods = new Table(grpSeverityModifiersincrease, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table_SensitiveMethods = new FormData();
		fd_table_SensitiveMethods.height = 200;
		fd_table_SensitiveMethods.top = new FormAttachment(lblExceptionsFromSensitive, 15);
		fd_table_SensitiveMethods.right = new FormAttachment(0, 430);
		fd_table_SensitiveMethods.left = new FormAttachment(0, 39);
		table_SensitiveMethods.setLayoutData(fd_table_SensitiveMethods);
		table_SensitiveMethods.setHeaderVisible(true);
		table_SensitiveMethods.setLinesVisible(true);
		
		TableColumn tblclmnNamesOfSensitive = new TableColumn(table_SensitiveMethods, SWT.NONE);
		tblclmnNamesOfSensitive.setWidth(183);
		tblclmnNamesOfSensitive.setText("Names of sensitive methods");
		
		Button btnAddSensitiveMethod = new Button(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_btnAddSensitiveMethod = new FormData();
		fd_btnAddSensitiveMethod.width = 60;
		fd_btnAddSensitiveMethod.top = new FormAttachment(table_SensitiveMethods, 0, SWT.TOP);
		fd_btnAddSensitiveMethod.left = new FormAttachment(table_SensitiveMethods, 6);
		btnAddSensitiveMethod.setLayoutData(fd_btnAddSensitiveMethod);
		btnAddSensitiveMethod.setText("Add");
		
		Button btnRemoveSensitiveMethod = new Button(grpSeverityModifiersincrease, SWT.NONE);
		FormData fd_btnRemoveSensitiveMethod = new FormData();
		fd_btnRemoveSensitiveMethod.width = 60;
		fd_btnRemoveSensitiveMethod.top = new FormAttachment(btnAddSensitiveMethod, 6, SWT.BOTTOM);
		fd_btnRemoveSensitiveMethod.left = new FormAttachment(btnAddSensitiveMethod, 0, SWT.LEFT);
		btnRemoveSensitiveMethod.setLayoutData(fd_btnRemoveSensitiveMethod);
		btnRemoveSensitiveMethod.setText("Remove");
		
		scrolledComposite.setContent(compositeInScrolledComposite);
		scrolledComposite.setMinSize(compositeInScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

}
