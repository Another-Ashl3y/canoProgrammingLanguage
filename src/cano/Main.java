package cano;

public class Main {

	public static void main(String[] args) {
		Runner r = new Runner(8000000,"PATH_TO_PROJECT"); // Replace "PATH_TO_PROJECT" with the actual path to your project
		r.run("main.cano");
		System.out.println("Complete");
	}

}
