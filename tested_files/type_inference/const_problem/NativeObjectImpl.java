package type_inference.const_problem;


public class NativeObjectImpl implements NativeObjectInterface {
	private int handle;
	
	private NativeObjectImpl(int handle){
		this.handle = handle;
	}

	@Override
	public boolean open() {
		return openNative(this.handle);
	}

	@Override
	public boolean close() {
		return closeNative(this.handle);
	}

	@Override
	public byte read() {
		return readNative(this.handle);
	}

	@Override
	public void write(byte b) {
		writeNative(this.handle, b);
	}
	
	public NativeObjectImpl next(){
		return new NativeObjectImpl(this.getHandle() + 4);
	}
	
	public static NativeObjectInterface create(int size){
		int handle = createObject(10);
		return new NativeObjectImpl(handle);
	}
	
	
	
	public int getHandle() {
		return handle;
	}
	
	public void setHandle(int handle){
		this.handle = handle;
	}
	
	
	private static native int createObject(int size);
	private native boolean openNative(int handle);
	private native boolean closeNative(int handle);
	private native byte readNative(int handle);
	private native void writeNative(int handle, byte b);
	
	
}
