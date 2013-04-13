package type_inference.const_problem;

public class ConstExample {
	public static void main(String[] args) {
		NativeObjectInterface nativeObject = NativeObjectImpl.create(10);
		NativeObjectImpl nativeObjectImpl = (NativeObjectImpl) nativeObject;

		for (int i = 0; i < 10; ++i) {
			nativeObjectImpl.open();
			byte b;
			StringBuffer sb = new StringBuffer();
			while ((b = nativeObjectImpl.read()) != -1) {
				sb.append(b);
			}
			System.out.println(sb);
			nativeObjectImpl.close();
			nativeObjectImpl.setHandle(nativeObjectImpl.getHandle() + 4);
		}
	}
	public void doit() {
		this.getClass();
	}
}
