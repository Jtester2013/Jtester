package plugin.ui.window.configuration.detailtabs;

import  org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class MetricsTabInStatic {

	TabItem tabItem;
	Table metricsTable;

	public MetricsTabInStatic(TabFolder tabFolder) {
		// add components into tabItem
		tabItem = new TabItem(tabFolder, SWT.None);
		tabItem.setText("Metrics");
		ScrolledComposite scrolledComposite = new ScrolledComposite(tabFolder, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		tabItem.setControl(scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite compositeInScrolledComposite = new Composite(scrolledComposite, SWT.None);
		compositeInScrolledComposite.setLayout(new FormLayout());
		
		// to be complete
		Button btn_publish = new Button(compositeInScrolledComposite,SWT.CHECK);
		btn_publish.setText("Publish metrics statistics in reports");
		FormData fd_btn_publishButton = new FormData();
		fd_btn_publishButton.left = new FormAttachment(0,5);
		btn_publish.setLayoutData(fd_btn_publishButton);
		
		Button btn_report = new Button(compositeInScrolledComposite, SWT.CHECK);
		btn_report.setText("Report tasks for metrics values out of acceptable ranges");
		FormData fd_btn_report = new FormData();
		fd_btn_report.left = new FormAttachment(0,5);
		fd_btn_report.top = new FormAttachment(btn_publish,5,SWT.BOTTOM);
		btn_report.setLayoutData(fd_btn_report);
		
		Composite tableComposite = new Composite(compositeInScrolledComposite, SWT.NONE);
		tableComposite.setLayout(new FormLayout());
		FormData fd_tableCompositeData = new FormData();
		fd_tableCompositeData.left = new FormAttachment(0,5);
		fd_tableCompositeData.top = new FormAttachment(btn_report, 5, SWT.BOTTOM);
		fd_tableCompositeData.right = new FormAttachment(100,-5);
		fd_tableCompositeData.bottom = new FormAttachment(100,-5);
		tableComposite.setLayoutData(fd_tableCompositeData);
		
		metricsTable = new Table(tableComposite, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION | SWT.MULTI);
		FormData fd_metricsTable = new FormData();
		fd_metricsTable.bottom = new FormAttachment(100, 0);
		fd_metricsTable.right = new FormAttachment(100, 0);
		fd_metricsTable.top = new FormAttachment(0, 0);
		fd_metricsTable.left = new FormAttachment(0, 0);
		metricsTable.setLayoutData(fd_metricsTable);
		metricsTable.setHeaderVisible(true);
		metricsTable.setLinesVisible(true);
		
		TableColumn tblclmnMetricName = new TableColumn(metricsTable, SWT.NONE);
		tblclmnMetricName.setWidth(126);
		tblclmnMetricName.setText("Metric Name");
		
		TableColumn tblclmnLevel = new TableColumn(metricsTable, SWT.NONE);
		tblclmnLevel.setWidth(121);
		tblclmnLevel.setText("Level");
		
		TableColumn tblclmnAcceptableRanges = new TableColumn(metricsTable, SWT.NONE);
		tblclmnAcceptableRanges.setWidth(153);
		tblclmnAcceptableRanges.setText("Acceptable ranges");
		
		TableItem tableItem = new TableItem(metricsTable, SWT.NONE);
		tableItem.setText("New TableItem");
		
		
		scrolledComposite.setContent(compositeInScrolledComposite);
		scrolledComposite.setMinSize(compositeInScrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

}
