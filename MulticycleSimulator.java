import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by homecomputer on 11/16/15.
 */
public class MulticycleSimulator {

    static HashMap<String, Integer> registers = new HashMap<String, Integer>(); // Registers and respective values
    static ArrayList<GenInstruction> memory = new ArrayList<GenInstruction>(); // Memory
    static long programCounter = 0;
    PipelineRegister input;

    /**
     * Helper function to set up registers
     */
    public static void setUpRegisters(){
        for (int i = 0; i < 32; i++) {
            registers.put("i", 0); // Put 0s in all the registers
        }
    }

    public static void main(String[] args) {
        System.out.println("Simulation Mode");
        String curLine;
        int clock = 0;
        MulticycleSimulator test = new MulticycleSimulator();

        setUpRegisters();

        try {
            FileReader fileReader = new FileReader("/Users/homecomputer/IdeaProjects/315Lab4/src/countbits.bin"); // File name supplied as first command line arg
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            Scanner reader = new Scanner(System.in); // Takes user input
            String input;

            while ((curLine = bufferedReader.readLine()) != null) {
                System.out.println("Next command: ");
                input = reader.nextLine();

                if (input.equals("S")) { // STEP
                    if ((curLine = bufferedReader.readLine()) != null) {
                        System.out.println("CURLINE: " + curLine);
                        if (curLine.trim().length() > 0) { // Ensure that it's not all whitespaces
                            GenInstruction instruction = readMachineCode(curLine.trim());
                            test.run(instruction);
                            //simulate(instruction);
                            clock += clockCycle(instruction.opcode);
                            programCounter += 4;
                        }
                        System.out.println("Program counter is: " + programCounter);
                    }
                }
                else if (input.equals("R")) { // RUN
                    while ((curLine = bufferedReader.readLine()) != null) {
                        GenInstruction instruction = readMachineCode(curLine);
                        test.run(instruction);
                        //simulate(instruction);
                        clock += clockCycle(instruction.opcode);
                        //programCounter += 4;

                        System.out.println("Clock Cycle: " + clock);
                        System.out.println("Program Counter: " + programCounter);
                    }
                    displayRegisters();
                }
                else if (input.equals("P")) { // PRINT REGS
                    displayRegisters();
                }
                else {
                    System.out.println("Invalid command. Enter R, S, or P to continue.");
                }
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + e.toString());
        }
        catch (IOException e) {
            System.out.println("IOException: " + e.toString());
        }

    }

    /**
     * Reads the assembly file line by line
     * @param line
     * @return
     */
    public static int readCode(String line) {
        int inc = 0;
        int temp = 0;
        if (line.length() > 1) {
            System.out.println(line);
            if (line.substring(0, 3).contains("#")) {
                System.out.println("This line is only a comment.\n");
                inc = 0;
            } else if (line.contains("lw")) {
                int test1 = 0x0001004F;
                registers.put("$a0", test1);
                inc = 4;
            } else if (line.contains("jal")) {
                int countbits = 24;
                registers.put("$ra", 12);
                inc = countbits;
            } else if (line.contains("or") && !line.contains("ori")) {
                temp = registers.get("$v0");
                registers.put("$t0", temp);
                inc = 4;
            } else if (line.contains("ori")) {
                //needs work
                inc = 4;
            } else if (line.contains("addiu")) {
                int sum = 0;
                sum = registers.get("$v0") + 1;
                registers.put("$v0", sum);
                inc = 4;
            } else if (line.contains("and")) {
                temp = registers.get("$a0");
                temp = temp & registers.get("$t0").intValue();
                registers.put("$t1", temp);
            } else if (line.contains("move")) {
                registers.put("$v0", 0);
            } else if (line.contains("beq")) {
                if (registers.get("$t1") == 0) {
                    inc = 1;
                }
            } else if (line.contains("sll")) {
                temp = registers.get("$t1");
                temp <<= 1;
            } else if (line.contains("bne")) {
                temp = registers.get("$t0");
                if (temp != 0) {
                    inc = -11;
                }
            } else if (line.contains("jr")) {
                inc = registers.get("$ra");
            }
        }
        return inc;
    }

    /**
     * Reads a line of machine code and returns an instruction in an int array
     *
     * for J:
     * 0 opcode, 1 index
     * for R:
     * 0 opcode, 1 rs, 2 rt, 3 rd, 4 shamt, 5 func
     * for I:
     * 0 opcode, 1 rs, 2 rt, 3 imm
     * @param machine - line of machine code
     * @return instruction
     */
    public static GenInstruction readMachineCode(String machine) {
        GenInstruction genInstruction = new GenInstruction(); //

        if (machine.substring(0,6).contains("100011") || // I INSTRUCTION
        machine.substring(0,6).contains("001101") ||
        machine.substring(0,6).contains("000100") ||
        machine.substring(0,6).contains("001001") ||
        machine.substring(0,6).contains("000101")) {
            //immediate
            int opcode = Integer.parseInt(machine.substring(0,6), 2); //opcode
            int rs = Integer.parseInt(machine.substring(6, 11), 2); //rs
            int rt = Integer.parseInt(machine.substring(11,16), 2); //rt
            int imm = Integer.parseInt(machine.substring(17,32), 2); //imm

            IInstruction iinstruction = new IInstruction(opcode, rs, rt, imm);
            genInstruction = iinstruction;
            iinstruction.type = "IInstruction";

        } else if(machine.substring(0,6).contains("000000")) { // R INSTRUCTION
            if(machine.substring(26,32).contains("100101") ||
            machine.substring(26,32).contains("100000") ||
            machine.substring(26,32).contains("100100") ||
            machine.substring(26,32).contains("000000") ||
            machine.substring(26,32).contains("101000")) {
                //register
                int opcode = Integer.parseInt(machine.substring(0,6), 2); //opcode
                int rs = Integer.parseInt(machine.substring(6, 11), 2); //rs
                int rt = Integer.parseInt(machine.substring(11,16), 2); //rt
                int rd = Integer.parseInt(machine.substring(17,22), 2); //rd
                int shamt = Integer.parseInt(machine.substring(21,26), 2); //shamt
                int func = Integer.parseInt(machine.substring(26,32), 2); //func

                RInstruction rinstruction = new RInstruction(opcode, rs, rt, rd, shamt, func);
                genInstruction = rinstruction;
                rinstruction.type = "RInstruction";
            }
        } else if (machine.substring(0,6).contains("000010")) { // J INSTRUCTION
            int opcode = Integer.parseInt(machine.substring(0,6), 2);
            int index = Integer.parseInt(machine.substring(6,32), 2);

            JInstruction jinstruction = new JInstruction(opcode, index);
            genInstruction = jinstruction;
            jinstruction.type = "JInstruction";
        }

        return genInstruction;
    }

    /**
     * Calculates the clock cycles of different instructions
     * @param opcode
     * @return
     */
    public static int clockCycle(int opcode) {
        int clock = 0;

        if(opcode == 0) {
            //all r type instructions have opcode of 0
            clock = 4;
        } else if (opcode == 0x02) {
            //jump instructions
            clock = 3;
        } else {
            clock = 4;
        }

        return clock;
    }

    InstructionFetch fetch = new InstructionFetch(input);
    InstructionDecode decode = new InstructionDecode(input);
    Execute execute = new Execute(input);
    MemoryAccess memoryaccess = new MemoryAccess(input);
    WriteBack writeback = new WriteBack();
    
    public void run(GenInstruction instruction) {
        long result;
        fetch.run(instruction);
        decode.setControlLines(instruction);
        result = execute.run((RInstruction) instruction);
        memoryaccess.run(input.getValue(), true);
        writeback.run(result);
    }

    /**
     * Displays the registers at the end of the run
     */
    public static void displayRegisters() {
        Object registerArray[] = registers.entrySet().toArray();
        Integer value;
        String register;

        for (int i = 0; i < registerArray.length; i++) {
            System.out.println(registerArray[i].toString());
        }
    }

    /**
     * Reads the code as assembly
     * @param line
     * @return
     */
    /**/

}
