package instructions_oop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MachineTest {
	@Test
	void testMachine() {
		assertEquals(16, pow(2, 4));
		assertEquals(25, pow(5, 2));
	}
	
	static int pow(int x, int y) {
		Instruction[] program = {
				new LoadConstantInstruction(2, 1),
				new JumpIfZeroInstruction(1, 5),
				new MultiplyInstruction(2, 0),
				new AddInstruction(1, 3),
				new JumpInstruction(1),
				new HaltInstruction()
		};
		assertEquals("Hello LoadConstant(2, 1)", "Hello " + program[0]);
		assertEquals(new LoadConstantInstruction(2, 1), program[0]);
		
		int[] registers = {x, y, 0, -1};
		Machine.execute(registers, program);
		return registers[2];
	}
}