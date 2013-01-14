package core.common.model.om;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import core.common.model.annotator.ConstraintError;

/**
 * 
 * @author Bin
 * 
 * Constraint Variable Model.
 * For worklist algorithm it must implement Job
 *
 */
public class ConstraintVariable implements Job {
	private String key;
	private Location createLoc;
	private Map<Integer, Integer> annotations;
	private Map<ConstraintVariable, Location> constraintLocation;
	private Map<Integer, Location> annotationChangeLocation;
	private Set<ConstraintVariable> constraints;

	public String getKey() {
		return key;
	}

	public void addConstraint(ConstraintVariable cv, Location loc) {
		if (cv != null) {
			this.constraints.add(cv);
			this.constraintLocation.put(cv, loc);
		}
	}

	public Set<ConstraintVariable> getConstraint() {
		return constraints;
	}

	public ConstraintVariable(String key, Location loc) {
		this.key = key;
		this.annotations = new Hashtable<Integer, Integer>();
		this.constraints = new HashSet<ConstraintVariable>();
		this.constraintLocation = new Hashtable<ConstraintVariable, Location>();
		this.annotationChangeLocation = new Hashtable<Integer, Location>();
	}

	public boolean addAnnotation(int annotationTypeId, int annotationValue,
			Location loc) {
		if (this.annotations.containsKey(annotationTypeId)) {
			int currentAnnotationValue = this.annotations.get(annotationTypeId);
			if (currentAnnotationValue > annotationValue) {
				StringBuffer sb = new StringBuffer();
				String currValue = OMShared.getAnnotationValueName(
						annotationTypeId, currentAnnotationValue);
				sb.append(String.format("%s : %s\n", this.key, currValue));
				String confValue = OMShared.getAnnotationValueName(
						annotationTypeId, annotationValue);
				sb.append(String.format("But receive a : %s\n", confValue));
				throw new ConstraintError(sb.toString(), loc);
			}
			return false;
		} else {
			this.annotations.put(annotationTypeId, annotationValue);
			this.annotationChangeLocation.put(annotationTypeId, loc);
			return true;
		}
	}

	public Location getCreateLoc() {
		return createLoc;
	}

	public void setLocation(Location loc) {
		this.createLoc = loc;
	}

	public Map<Integer, Integer> getAnnotations() {
		return this.annotations;
	}

	public boolean updateSelf() {
		boolean isChange = false;
		for (ConstraintVariable constraint : this.constraints) {
			for (int typeId : constraint.annotations.keySet()) {
				int value = constraint.annotations.get(typeId);
				if (this.addAnnotation(typeId, value,
						this.constraintLocation.get(constraint)))
					isChange = true;
			}
		}
		return isChange;
	}
}
