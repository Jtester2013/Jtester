package core.common.model.semantics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SemanticsStore {
	List<DeclarationSemantics> declarationStore;
	List<InferenceSemantics> inferenceStore;
	List<MethodSemantics> methodStore;
	
	public SemanticsStore(){
		declarationStore = new ArrayList<DeclarationSemantics>();
		inferenceStore = new ArrayList<InferenceSemantics>();
		methodStore = new ArrayList<MethodSemantics>();
	}
	
	public void putDeclarationStore(DeclarationSemantics semantics){
		if(!declarationStore.contains(semantics))
			declarationStore.add(semantics);
	}
	
	public void putInferenceStore(InferenceSemantics semantics){
		if(!inferenceStore.contains(semantics))
			inferenceStore.add(semantics);
	}
	
	public void putMethodStore(MethodSemantics semantics){
		if(!methodStore.contains(semantics))
			methodStore.add(semantics);
	}
	
	public Iterator<DeclarationSemantics> iterator1(){
		return declarationStore.iterator();
	}
	
	public Iterator<InferenceSemantics> iterator2(){
		return inferenceStore.iterator();
	}
	
	public Iterator<MethodSemantics> iterator3(){
		return methodStore.iterator();
	}
	
	public String toString(){
		return "Declaration: " + declarationStore + "\nInference: " + inferenceStore + "\nmethod: " + methodStore;
	}
}
