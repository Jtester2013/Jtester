package ConstantProblem;

public interface NativeObjectInterface {
	public boolean open();
	public boolean close();
	public byte read();
	public void write(byte b);
}
