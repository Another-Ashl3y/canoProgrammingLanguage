package cano;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
			String a;
			String B;
			
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
//						System.out.println(next_path);
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
					case "ADD":
						p = getV(operation[1]);
						q = getV(operation[2]);
						v = getV(operation[3]);
						memory[(int) v] = p+q;
						break;
					case "SUB":
						p = getV(operation[1]);
						q = getV(operation[2]);
						v = getV(operation[3]);
						memory[(int) v] = p-q;
						break;
					case "MUL":
						p = getV(operation[1]);
						q = getV(operation[2]);
						v = getV(operation[3]);
						memory[(int) v] = p*q;
						break;
					case "DIV":
						p = getV(operation[1]);
						q = getV(operation[2]);
						v = getV(operation[3]);
						memory[(int) v] = p/q;
						break;
					case "MOD":
						p = getV(operation[1]);
						q = getV(operation[2]);
						v = getV(operation[3]);
						memory[(int) v] = p%q;
						break;
					case "FDV":
						p = getV(operation[1]);
						q = getV(operation[2]);
						v = getV(operation[3]);
						memory[(int) v] = Math.floorDiv((long) p, (long) q);
						break;
					case "POW":
						p = getV(operation[1]);
						q = getV(operation[2]);
						v = getV(operation[3]);
						memory[(int) v] = Math.pow(p, q);
						break;
					case "TAN":
						p = getV(operation[1]);
						v = getV(operation[2]);
						memory[(int) v] = Math.tan(p);
						break;
					case "SIN":
						p = getV(operation[1]);
						v = getV(operation[2]);
						memory[(int) v] = Math.sin(p);
						break;
					case "COS":
						p = getV(operation[1]);
						v = getV(operation[2]);
						memory[(int) v] = Math.cos(p);
						break;
					case "ROUND":
						p = getV(operation[1]);
						v = getV(operation[2]);
						memory[(int) v] = Math.round(p);
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
					case "NOT":
						p = getV(operation[1]);
						q = getV(operation[2]);
						if (p==0) {
							memory[(int) q] = 1;
						} else {
							memory[(int) q] = 0;
						}
						break;
					case "RNG":
						p = getV(operation[1]);
						memory[(int) p] = Math.random();
						break;
					case "NQU":
						p = getV(operation[1]);
						q = getV(operation[2]);
						u = getV(operation[3]);
						if (p!=q) {
							memory[(int) u] = 1;
						} else {
							memory[(int) u] = 0;
						}
						break;
					case "AND":
						p = getV(operation[1]);
						q = getV(operation[2]);
						u = getV(operation[3]);
						if (p>0 & q>0) {
							memory[(int) u] = 1;
						} else {
							memory[(int) u] = 0;
						}
						break;
					case "OR":
						p = getV(operation[1]);
						q = getV(operation[2]);
						u = getV(operation[3]);
						if (p>0 | q>0) {
							memory[(int) u] = 1;
						} else {
							memory[(int) u] = 0;
						}
						break;
					case "XOR":
						p = getV(operation[1]);
						q = getV(operation[2]);
						u = getV(operation[3]);
						if ((p>0 & q<=0)|(q>0 & p<= 0)) {
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
					case "PRINTC":
						p = getV(operation[1]);
						q = getV(operation[2])+1;
						for (int i = 0; i < q-p; i++) {
							System.out.print((char) memory[(int) (p+i)]);
						}
						break;
					case "PRINTLNC":
						p = getV(operation[1]);
						q = getV(operation[2])+1;
						for (int i = 0; i < q-p; i++) {
							System.out.println((char) memory[(int) (p+i)]);
						}
						break;
					case "GETW":
						a = getInput();
						char[] b = a.toCharArray();
						p = getV(operation[1]);
						q = getV(operation[2])+1;
						for (int i = 0; i < q-p; i++) {
							if (i < b.length) {
								memory[(int) (p+i)] = (int) b[i];
							}
							else  {
								memory[(int) (p+i)] = 0;
							}
						}
						break;
					case "GETN":
						a = getInput();
						p = getV(operation[1]);
						memory[(int) p] = Double.valueOf(a);
						break;
					case "RST":
						p = getV(operation[1]);
						q = getV(operation[2])+1;
						for (int i = 0; i < q-p; i++) {
							memory[(int) (p+i)] = 0;
						}
						break;
					case "NEWF":
						a = "";
						p = getV(operation[1]);
						q = getV(operation[2])+1;
						for (int i = 0; i < q-p; i++) {
							a += (char) (int) memory[(int) (i + p)];
						}
						makeFile(a);
						break;
					case "WRITEFC":
						a = "";
						B = "";
						p = getV(operation[1]);
						q = getV(operation[2])+1;
						u = getV(operation[3]);
						v = getV(operation[4])+1;
						for (int i = 0; i < q-p; i++) {
							a += (char) (int) memory[(int) (i + p)];
						}
						for (int i = 0; i < v-u; i++) {
							B += (char) (int) memory[(int) (i + u)];
						}
						writeFile(a, B);
						break;
					case "WRITEF":
						a = "";
						B = "";
						p = getV(operation[1]);
						q = getV(operation[2])+1;
						u = getV(operation[3]);
						v = getV(operation[4])+1;
						for (int i = 0; i < q-p; i++) {
							a += (char) (int) memory[(int) (i + p)];
						}
						for (int i = 0; i < v-u; i++) {
							B += Double.toString(memory[(int) (i + u)]) + ", ";
						}
						writeFile(a, B);
						break;
					case "READF":
						a = "";
						B = "";
						p = getV(operation[1]);
						q = getV(operation[2])+1;
						u = getV(operation[3]);
						v = getV(operation[4])+1;
						for (int i = 0; i < q-p; i++) {
							a += (char) (int) memory[(int) (i + p)];
						}
						String Q = readFile(a);
						for (int i = 0; i < v-u; i++) {
							memory[(int) (i+u)] = (int) Q.charAt(i);
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
	public String getInput() {
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	public void makeFile(String n) {
		try {
			File myObj = new File(path+n);
			if (myObj.createNewFile()) {
			} 
			else {
				System.out.println("File already exists.");
			}
		} 
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public void writeFile(String n, String d) {
		try {
			FileWriter myWriter = new FileWriter(path+n);
			myWriter.write(d);
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public String readFile(String n) {
		String out = "";
		try {
			File file = new File(path+n);
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				out += scan.nextLine() + "\n";
			}
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
} 
