/**
 * Created by homecomputer on 11/16/15.
 */
public class InstructionDecode {
	public InstructionDecode(PipelineRegister id) {
		this.id = id;
	}

	public void run(String instruction){
		public boolean loadAB = true;
	}

	public HashMap<String, Long> setControlLines(String instruction) {
		HashMap<String, Long> lines = new HashMap<String, Long>();

		switch(String instruction) {
			case "lw":
				values.put("ALU_src", 1l);
				values.put("REG_write", 1l);
				values.put("MEM_read", 1l);
				id.setValue(1l);
				break;
			case "jal":
				values.put("PC", 1l);
				id.setValue(1l);
				break;			
			case "or":
				values.put("ALU_src", 1l);
				values.put("REG_write", 1l);				
				id.setValue(1l);
				break;
			case "ori":
				values.put("ALU_src", 1l);
				values.put("REG_write", 1l);
				id.setValue(1l);
				break;
			case "and":
				values.put("ALU_src", 1l);
				values.put("REG_write", 1l);
				id.setValue(1l);
				break;
			case "beq":
				values.put("Branch", 1l);
				id.setValue(1l);
				break;
			case "addiu":
				values.put("ALU_src", 1l);
				values.put("REG_write", 1l);
				id.setValue(1l);
				break;
			case "bne":
				values.put("Branch", 1l);
				id.setValue(1l);
				break;
			case "jr":
				values.put("PC", 1l);
				id.setValue(1l);
				break;
		}
	}

	public long branch(long address) {
		return address * 4; 
	}

	public long jump(long address) {
		return address * 4;
	}
}
