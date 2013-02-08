package type_inference.const_problem;

import type_inference.const_problem.ConstType.Level;

enum QualifiedScope {
	This
};

@interface ConstType {
	enum Level {
		Const, Mutable
	};

	Level level();

	QualifiedScope qual();
}

public class NativeOjbectUtil {

	public static void next(
			@ConstType(level = Level.Const, qual = QualifiedScope.This) NativeObjectImpl obj) {
		obj.setHandle(obj.getHandle() + 4);
	}
}
