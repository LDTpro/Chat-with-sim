package hust.java;

import java.util.Scanner;


public class Main {
	public Main() {
	}

	public static void main(String[] args) throws Exception {
		String s = null;
		boolean bo = true;
		Scanner scanner = new Scanner(System.in);
		while (bo) {
			s = scanner.next();
			if (s != ":q") {
				APISim.sendPost(s);
			}
			else {
				bo = false;
				scanner.close();
			}
		}
	}
}
