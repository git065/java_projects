public class Main {
	String variable;

	public static void main(String[] args) {
		System.out.println("Hello World!");
		B b = new B();
	}

	public Main() {
		System.out.println("Constructor A");
		printVariable();
	}

	protected void printVariable() {
		variable = "variable is initialized in Main Class";
		System.out.println("variable value = " + variable);
	}
}
