package core.common.model.annotator;

import core.common.model.om.Location;

/**
 * 
 * @author Bin
 * 
 * ConstraitError is when you find conflict while execute worklist algorithm. And then catch this Exception and found where the error is.
 *
 */
public class ConstraintError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Location loc;
	
	public ConstraintError(String message, Location loc){
		super(message);
		this.loc = loc;
	}
	
	public ConstraintError(){
		
	}
	
	public Location getLocation(){
		return loc;
	}
	
	public void setLocation(Location loc){
		this.loc = loc;
	}
	
}
