package com.example.bit_puzzler;

public class Fiddler {
	public String program;
	public StringBuilder memory;
	private int programPointer;
	private int memoryPointer;
	
	public Fiddler(String program, String memory) {
		this.program = program;
		this.memory = new StringBuilder(memory);
	}

	public boolean execute(int i) {
		while (i-- > 0) {
			Boolean error = false;
			boolean hasNext = step(error);
			if (error) {
				return false;
			}
			if (!hasNext) {
				break;
			}
		}
		
		return true;
	}

	public boolean step(Boolean error) {
		if (programPointer > program.length()-1) {
			return false;
		}
		
		switch (program.charAt(programPointer)) {
		case '*':
			memory.setCharAt(memoryPointer, memory.charAt(memoryPointer) == '0' ? '1' : '0');
			break;
		case '<':
			if (memoryPointer == 0) {
				error = true;
				return false;
			}
			memoryPointer--;
			break;
		case '>':
			memoryPointer++;
			memory.append('0');
			break;
		case '[':
			if (memory.charAt(memoryPointer) == '1') {
				break;
			}
			for (int nests = 0; ; programPointer++) {
				if (programPointer > program.length()-1) {
					error = true;
					return false;
				}
				if (program.charAt(programPointer) == '[') {
					nests++;
				} else if (program.charAt(programPointer) == ']') {
					nests--;
				}
				if (nests == 0) {
					break;
				}
			}
			break;
		case ']':
			if (memory.charAt(memoryPointer) == '0') {
				break;
			}
			for (int nests = 0; ; programPointer--) {
				if (programPointer < 0) {
					error = true;
					return false;
				}
				if (program.charAt(programPointer) == '[') {
					nests--;
				} else if (program.charAt(programPointer) == ']') {
					nests++;
				}
				if (nests == 0) {
					break;
				}
			}
			break;
		}
		programPointer++;
		return true;
	}

	public String toString() {
		return memory.toString();
	}
}