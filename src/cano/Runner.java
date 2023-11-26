package cano;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runner {
	double[] memory;
	char[] chars;
	String path;
	Runner(int size, String location) {
		memory = new double[size];
		path = location;
	}
	public void run(String file) {
		try {
			File f = new File(path+file);
			Scanner reader = new Scanner(f);
			
			String data = "";
			while (reader.hasNextLine()) {
				data += "\n"+reader.nextLine();
			}
			
			boolean running = true;
			int current_line = 0;
			String[] operations;
			String[] operation;
			String operator;
			double p;
			double q;
			double u;
			double v;
			
			operations = data.split("\n");

			while (running) {
				operation = operations[current_line].split(" ");
				operator = operation[0];
				switch(operator) {
					case "END":
						running = false;
						break;
					case "GOTO":
						p = getV(operation[1]);
						current_line = (int) p-1;
						break;
					case "RUN":
						String next_path = operation[1];
						System.out.println(next_path);
						this.run(next_path);
						break;
					case "SKP":
						p = getV(operation[1]);
						q = getV(operation[2]);
						if (p > 0) {
							current_line += (int) q;
						}
						break;
					case "STO":
						p = getV(operation[1]);
						q = getV(operation[2]);
						memory[(int) q] = p;
						break;
					case "INC":
						p = getV(operation[1]);
						memory[(int) p ] += 1;
						break;
					case "DCR":
						p = getV(operation[1]);
						memory[(int) p ] -= 1;
						break;
					case "EQU":
						p = getV(operation[1]);
						q = getV(operation[2]);
						u = getV(operation[3]);
						if (p==q) {
							memory[(int) u] = 1;
						} else {
							memory[(int) u] = 0;
						}
						break;
					case "GRT":
						p = getV(operation[1]);
						q = getV(operation[2]);
						u = getV(operation[3]);
						if (p>q) {
							memory[(int) u] = 1;
						} else {
							memory[(int) u] = 0;
						}
						break;
					case "SML":
						p = getV(operation[1]);
						q = getV(operation[2]);
						u = getV(operation[3]);
						if (p<q) {
							memory[(int) u] = 1;
						} else {
							memory[(int) u] = 0;
						}
						break;
						
					case "PRINT":
						p = getV(operation[1]);
						q = getV(operation[2])+1;
						for (int i = 0; i < q-p; i++) {
							System.out.print(memory[(int) (p+i)]);
						}
						break;
					case "PRINTLN":
						p = getV(operation[1]);
						q = getV(operation[2])+1;
						for (int i = 0; i < q-p; i++) {
							System.out.println(memory[(int) (p+i)]);
						}
						break;
				}
				current_line += 1;
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		    e.printStackTrace();
		}
	}
	public double getV(String x) {
		double out = 0;
		if (x.charAt(0) == "#".charAt(0)) {
			x = x.replace("#","");
			int y = Integer.valueOf(x);
			out = memory[y];
		} else {
			out = Double.valueOf(x);
		}
		return out;
	}
} 
