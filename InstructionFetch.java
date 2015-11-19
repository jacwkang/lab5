/**
 * Created by homecomputer on 11/16/15.
 */
public class InstructionFetch {
	public InstructionFetch(PipelineRegister if) {
		this.if = if;
	}
	public void run(String instruction) {
		if.setValue(instruction);
		programCounter += 4;
	}
}
