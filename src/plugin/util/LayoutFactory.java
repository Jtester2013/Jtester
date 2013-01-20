package plugin.util;

import java.io.ObjectInputStream.GetField;

import org.eclipse.swt.layout.FormLayout;

public class LayoutFactory {
	private static FormLayout usualFormLayout = null;
	private static FormLayout usualMaxLayout = null;
	private static int margin_value = 3;
	private static int zero_value = 0;
	
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
	
	public static synchronized FormLayout getMaxLayout(){
		if(null==usualMaxLayout){
			usualMaxLayout = new FormLayout();
			usualMaxLayout.marginLeft = zero_value;
			usualFormLayout.marginRight = zero_value;
			usualMaxLayout.marginTop = zero_value;
			usualFormLayout.marginBottom = zero_value;
			usualFormLayout.spacing = margin_value;
		}
		return usualFormLayout;
	}
	
//	public static FormLayout getFullFilledFormLayout(){
//		return null;
//	}
}
