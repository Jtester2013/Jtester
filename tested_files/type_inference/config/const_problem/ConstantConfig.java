package type_inference.const_problem;

enum QualifiedScope { This };

@interface ConstType{
	enum Level { Const, Mutable };
	Level level();
	QualifiedScope qual();
}

public class NativeObjectImpl implements NativeObjectInterface {
	
	@ConstType(level=Level.Const)
	private NativeObjectImpl(int handle){
		this.handle = handle;
	}
	
	@ConstType(level = Level.Mutable, qual=QualifiedScope.This)
	public void setHandle(int handle){
		this.handle = handle;
	}
}