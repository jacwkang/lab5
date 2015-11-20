/**
 * Created by homecomputer on 11/16/15.
 */
public class MemoryAccess {
	private PipelineRegister memory;

	public MemoryAccess(PipelineRegister memory) {
		this.memory = memory;
	}

	public void run(long result, boolean mem_read) {
		if(mem_read) {
			memory.setValue(memory.getValue());
		} else {
			memory.setValue(result);
		}
	}
}
