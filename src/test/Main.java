package test;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

public class Main {
	public static void main(String[] args) {
		System.out.println("java.version: " + System.getProperty("java.version"));
		System.out.println();

		checkSwitchExpressions();
		checkTextBlock();
		checkLocalTypeInference();
		checkAnonymousLocalTypeInference();
		checkPrivateInterfaceMethod();
		checkGenericSubclass();
		checkEffectivelyFinalCloseable();
		checkPatternMatching();
	}

	private static int switchExpression(String value) {
		return switch (value) {
			case "a" -> 1;
			case "b", "c" -> 2;
			default -> 0;
		};
	}

	private static void checkSwitchExpressions() {
		System.out.println("Switch expression a -> " + switchExpression("a"));
		System.out.println("Switch expression b -> " + switchExpression("b"));
		System.out.println("Switch expression c -> " + switchExpression("c"));
		System.out.println("Switch expression d -> " + switchExpression("d"));
		System.out.println();
	}

	private static void checkTextBlock() {
		System.out.println("Text block:");
		System.out.println(
				"""
				  Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
				sed do eiusmod tempor incididunt ut labore et dolore magna 
				aliqua. Ut enim ad minim veniam, quis nostrud exercitation 
				ullamco laboris nisi ut aliquip ex ea commodo consequat.
				"""
				);
	}

	private static void checkLocalTypeInference() {
		System.out.println("Local type inference: ");
		var list = new ArrayList<Integer>();
		list.add(1);
		// compilation error:
		//list.add("str");
		System.out.println("The following number is 1: " + Integer.toString(list.get(0)));
		System.out.println();
	}

	private static void checkAnonymousLocalTypeInference() {
		System.out.println("Anonymous local type inference: ");
		var run = new Runnable() {
			int val = 0;

			@Override
			public void run() {
				val = 33;
			}
		};
		run.run();
		System.out.println("The following number is 33: " + run.val);
		System.out.println();
	}

	private static void checkPrivateInterfaceMethod() {
		ItfWithPrivateMethods itf = new ItfWithPrivateMethods() {
			@Override
			public int getInt() {
				return 9;
			}
		};
		System.out.println("Private interface methods: ");
		System.out.println("The following number is 9: " + itf.getInt());
		System.out.println("The following number is 9123: " + itf.defaultInt());
		System.out.println();
	}

	private interface ItfWithPrivateMethods {
		public int getInt();

		public default int defaultInt() {
			return computePrivateInt();
		}

		private int computePrivateInt() {
			return getInt() * 1000 + getPrivateStaticInt();
		}

		private static int getPrivateStaticInt() {
			return 123;
		}
	}

	private static void checkGenericSubclass() {
		System.out.println("Generic subclass with diamond operator: ");
		Function<? super Integer, ?> f = new Function<>() {
			@Override
			public Integer apply(Integer in) {
				return in * 3;
			}
		};
		System.out.println("The following number is 24: " + f.apply(8));
		System.out.println();
	}

	private static void checkEffectivelyFinalCloseable() {
		System.out.println("Effectively final closeable: ");
		var mycloseable = new Closeable() {
			@Override
			public void close() {
				System.out.println("Closing.");
			}
		};
		try (mycloseable) {
			System.out.println("Inside try-with-resources.");
		}
		//notice that there's no need for a catch block
		//as close() doesn't throw an exception, and javac knows this 
		//as the type is inferred 
		//if we would've used the 
		//    Closeable mycloseable = 
		//declaration, then the catch must've been declared

		System.out.println();
	}

	private static void checkPatternMatching() {
		System.out.println("Pattern matching:");
		System.out.println("Integer 123: " + patternMatch(123));
		System.out.println("String __abc: " + patternMatch("__abc"));
		System.out.println();
	}

	private static String patternMatch(Object o) {
		if (o instanceof String s) {
			return s.substring(2);
		}
		return "Not string. (" + o + ")";
	}

}
