package core.jtester.staticanalysis.svd.execution;

import java.util.UUID;

public class UUIDGenerator {

	public static UUID getUUID() {
		return UUID.randomUUID();
	}

	public static void main(String args[]) {
		UUID id = getUUID();
		System.out.println(id);
	}
}
