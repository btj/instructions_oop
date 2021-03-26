package instructions_oop;

import java.util.Objects;

abstract class Instruction extends Object {
	
	abstract void execute(Machine machine);
	
}

class LoadConstantInstruction extends Instruction {
	int registerIndex;
	int constantValue;
	public LoadConstantInstruction(int registerIndex, int constantValue) {
		this.registerIndex = registerIndex;
		this.constantValue = constantValue;
	}
	
	void execute(Machine machine) {
		machine.registers[registerIndex] = constantValue;
	}
	
	@Override
	public String toString() {
		return "LoadConstant(" + registerIndex + ", " + constantValue + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof LoadConstantInstruction i && registerIndex == i.registerIndex && constantValue == i.constantValue;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(registerIndex, constantValue);
	}
}

class DecrementInstruction extends Instruction {
	int registerIndex;
	public DecrementInstruction(int registerIndex) {
		this.registerIndex = registerIndex;
	}
	
	void execute(Machine machine) {
		machine.registers[registerIndex]--;
	}
	
	@Override
	public String toString() {
		return "Decrement(" + registerIndex + ")";
	}
}

class MultiplyInstruction extends Instruction {
	int registerIndex1;
	int registerIndex2;
	public MultiplyInstruction(int registerIndex1, int registerIndex2) {
		this.registerIndex1 = registerIndex1;
		this.registerIndex2 = registerIndex2;
	}
	
	void execute(Machine machine) {
		machine.registers[registerIndex1] *= machine.registers[registerIndex2];
	}
}

class JumpIfZeroInstruction extends Instruction {
	int registerIndex;
	int address;
	public JumpIfZeroInstruction(int registerIndex, int address) {
		super();
		this.registerIndex = registerIndex;
		this.address = address;
	}
	
	void execute(Machine machine) {
		if (machine.registers[registerIndex] == 0)
			machine.pc = address;
	}
	
}
class JumpInstruction extends Instruction {
	int address;

	public JumpInstruction(int address) {
		super();
		this.address = address;
	}
	
	void execute(Machine machine) {
		machine.pc = address;
	}
	
}
class HaltInstruction extends Instruction {
	
	void execute(Machine machine) {
		machine.pc = -1;
	}
}

class AddInstruction extends Instruction {
	int registerIndex1;
	int registerIndex2;
	
	void execute(Machine machine) {
		machine.registers[registerIndex1] += machine.registers[registerIndex2];
	}

	public AddInstruction(int registerIndex1, int registerIndex2) {
		super();
		this.registerIndex1 = registerIndex1;
		this.registerIndex2 = registerIndex2;
	}
	
	
	
}

class Machine {
	int[] registers;
	Instruction[] instructions;
	int pc;
	
	Machine(int[] registers, Instruction[] instructions) {
		this.registers = registers;
		this.instructions = instructions;
	}
	
	void run() {
		while (0 <= pc) {
			Instruction instruction = instructions[pc++];
			instruction.execute(this);
		}
	}
	
	static void execute(int[] registers, Instruction[] instructions) {
		new Machine(registers, instructions).run();
	}
}