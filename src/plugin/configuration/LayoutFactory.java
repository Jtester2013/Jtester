package plugin.configuration;

import org.eclipse.swt.layout.FormLayout;

public class LayoutFactory {
	private static FormLayout usualFormLayout = null;
	private static int margin_value = 3;
	
	public static synchronized FormLayout getFormLayout(){
		if(null==usualFormLayout){
			usualFormLayout = new FormLayout();
			usualFormLayout.marginLeft = margin_value;
//			usualFormLayout.marginRight = margin_value;
			usualFormLayout.marginTop = margin_value;
//			usualFormLayout.marginBottom = margin_value;
			usualFormLayout.spacing = margin_value;
		}
		return usualFormLayout;
	}
}
