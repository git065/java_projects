public class Main {
	String variable;

	/*public static void main(String[] args) {
		System.out.println("Hello World!");
		B b = new B();
	}*/
	
	public enum Members {JERRY, BOBBY, PHIL};
	
	public static void main(String[] args) {
		Members n = Members.BOBBY;		
		Members ifName = Members.PHIL;
		switch (ifName) {
		case PHIL:
			System.out.println("1");
		case BOBBY:
			System.out.println("2");
		case JERRY:
			System.out.println("3");
		}
		//System.out.println("Hello World!");
		//B b = new B();
	}

	public Main() {
		System.out.println("Constructor A");
		printVariable();
		Object a = new float[2];
		//short[] x2 = [5];
		char c = 49;
	}

	protected void printVariable() {
		variable = "variable is initialized in Main Class";
		System.out.println("variable value = " + variable);
	}
}
