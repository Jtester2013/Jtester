package core.common.model.om;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Bin
 *
 * All information will record to here.
 */
public class OMShared {
	private static Map<Integer, String> annotationTypeConstant = new Hashtable<Integer, String>();
	private static Map<Integer, Map<Integer, String>> annotationValueConstant = new Hashtable<Integer, Map<Integer, String>>();
	private static Map<String, Integer> annotationTypeReverse = new Hashtable<String, Integer>();
	private static Map<String, Map<String, Integer>> annotationValueReverse = new Hashtable<String, Map<String, Integer>>();
	private static int annotationTypeNum = 0;
	private static Map<String, ConstraintVariable> constraintVariableTable = new Hashtable<String, ConstraintVariable>();
	private static Map<String, List<ConstraintVariable>> methodParameters = new Hashtable<String, List<ConstraintVariable>>();

	public static int putNewAnnotationType(String constraintTypeName,
			String[] constaintTypeValue) {
		int result = annotationTypeNum;
		OMShared.annotationTypeConstant.put(annotationTypeNum,
				constraintTypeName);
		OMShared.annotationTypeReverse.put(constraintTypeName, annotationTypeNum);
		Map<Integer, String> newTypeValues = new Hashtable<Integer, String>();
		Map<String, Integer> newTypeValuesReverse = new Hashtable<String, Integer>();
		for (int value = 0; value < constaintTypeValue.length; ++value) {
			newTypeValues.put(value, constaintTypeValue[value]);
			newTypeValuesReverse.put(constaintTypeValue[value], value);
		}
		OMShared.annotationValueConstant.put(annotationTypeNum, newTypeValues);
		OMShared.annotationValueReverse.put(constraintTypeName, newTypeValuesReverse);
		OMShared.annotationTypeNum++;
		return result;
	}

	public static String getAnnotationTypeName(int typeid) {
		if (typeid < 0 || typeid > annotationTypeNum - 1)
			throw new InvalidParameterException(
					"This typeid is not belong to any annotation");
		return OMShared.annotationTypeConstant.get(typeid);
	}

	public static String getAnnotationValueName(int typeid, int valueid) {
		if (typeid < 0 || typeid > annotationTypeNum - 1)
			throw new InvalidParameterException(
					"This typeid is not belong to any annotation");
		Map<Integer, String> annotation = OMShared.annotationValueConstant
				.get(typeid);
		return annotation.get(valueid);
	}

	public static int getAnnotationTypeCount() {
		return annotationTypeNum;
	}

	public static Map<String, ConstraintVariable> getConstraintTable() {
		return constraintVariableTable;
	}
	
	public static void putConstraintVariable(String key, ConstraintVariable value){
		constraintVariableTable.put(key, value);
	}
	
	public static void reset(){
		OMShared.annotationTypeConstant.clear();
		OMShared.annotationTypeNum = 0;
		OMShared.annotationTypeReverse.clear();
		OMShared.annotationValueConstant.clear();
		OMShared.annotationValueReverse.clear();
		OMShared.constraintVariableTable.clear();
		OMShared.methodParameters.clear();
	}

	public static int getTypeIDByName(String typeName) {
		if(typeName == null)
			throw new  NullPointerException("Type name is null");
		if(!OMShared.annotationTypeReverse.containsKey(typeName))
			throw new RuntimeException(String.format("Cannot find typename : %s", typeName));
		return OMShared.annotationTypeReverse.get(typeName);
	}
	
	public static int getValueIDByName(String typeName, String valueName){
		if(typeName == null || valueName == null)
			throw new  NullPointerException("Type name is null");
		if(!OMShared.annotationTypeReverse.containsKey(typeName))
			throw new RuntimeException(String.format("Cannot find typename : %s", typeName));
		Map<String, Integer> values = OMShared.annotationValueReverse.get(typeName);
		if(!values.containsKey(valueName))
			throw new RuntimeException(String.format("Cannot find value : %s in type : %s", valueName, typeName));
		return values.get(valueName);
	}

	public static void putMethodParameter(String methodKey,
			ConstraintVariable cv) {
		List<ConstraintVariable> methodParameters = OMShared.methodParameters.get(methodKey);
		if(methodParameters == null){
			methodParameters = new ArrayList<ConstraintVariable>();
			OMShared.methodParameters.put(methodKey, methodParameters);
		}
		methodParameters.add(cv);
	}

	public static Map<String, List<ConstraintVariable>> getMethods() {
		// TODO Auto-generated method stub
		return OMShared.methodParameters;
	}

	public static ConstraintVariable getConstraintByKey(String key) {
		return OMShared.constraintVariableTable.get(key);
	}

	public static List<ConstraintVariable> getMethodParametersByKey(String key) {
		List<ConstraintVariable> parameters = OMShared.methodParameters.get(key);
		if(parameters == null)
			System.err.printf("Do not have the method of the key %s", key);
		return parameters;
	}

}
