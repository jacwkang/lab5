/**
 * Created by homecomputer on 11/16/15.
 */
public class Execute {
	private PipelineRegister ex;
	private PipelineRegister mem;

	public Execute (PipelineRegister ex, PipelineRegister mem) {
		this.ex = ex;
		this.mem = mem;
	} 

	public void run(String instruction, int aLUSrcA, int aLUSrcB) {
		long result = 0;
		switch(String instruction) {
			case "lw":
				result = aLUSrcA = aLUSrcB;
				break;
			case "jal":
				result = aLUSrcA = aLUSrcB;
				break;
			case "or":
				result = aLUSrcA | aLUSrcB;
				break;
			case "ori":
				result = aLUSrcA | aLUSrcB;
				break;
			case "and":
				result = aLUSrcA & aLUSrcB;
				break;
			case "beq":
				result = aLUSrcA - aLUSrcA;
				break;
			case "addiu":
				result = abs(aLUSrcA) + abs(aLUSrcB);
				break;
			case "bne":
				result = aLUSrcA - aLUSrcA;
				break;
			case "jr":
				result = aLUSrcA = aLUSrcB;
				break;
		}
		ex.setValue(result);
	}
}
