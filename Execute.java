import java.lang.Math;

/**
 * Created by homecomputer on 11/16/15.
 */
public class Execute {
	private PipelineRegister ex;

	private static final int ADD_FUNCT = 0x20;
	private static final int SUB_FUNCT = 0x22;
	private static final int AND_FUNCT = 0x24;
	private static final int OR_FUNCT = 0x25;
	private static final int NOR_FUNCT = 0x27;
	private static final int SLT_FUNCT = 0x2a;
	private static final int ADDIU = 0x9;
	private static final int ANDI = 0xC;
	private static final int ORI = 0xD;
	private static final int SLTI = 0xA;
	private static final int BEQ = 0x4;
	private static final int BNE = 0x5;
	private static final int JAL = 0x3;
	private static final int JR_FUNCT = 0x8;
	private static final int LW = 0x23;
	
	public Execute (PipelineRegister ex) {
		this.ex = ex;
	} 

	public long run(RInstruction instruction) {
		long result = 0;
		int aLUSrcA = instruction.getRs();
		int aLUSrcB = instruction.getRt();
		switch(instruction.getOpcode()) {
			case 0:
				switch(instruction.getFunc()) {
					case ADD_FUNCT:
						result = aLUSrcA + aLUSrcB;
						break;
					case OR_FUNCT:
						result = aLUSrcA | aLUSrcB;
						break;
					case AND_FUNCT:
						result = aLUSrcA & aLUSrcB;
						break;
					case JR_FUNCT:
						result = aLUSrcA = aLUSrcB;
						break;

				}
				break;
			case LW:
				result = aLUSrcA = aLUSrcB;
				break;
			case JAL:
				result = aLUSrcA = aLUSrcB;
				break;
			case ORI:
				result = aLUSrcA | aLUSrcB;
				break;				
			case BEQ:
				result = aLUSrcA - aLUSrcA;
				break;
			case ADDIU:
				result = Math.abs(aLUSrcA) + Math.abs(aLUSrcB);
				break;
			case BNE:
				result = aLUSrcA - aLUSrcA;
				break;
		}
		return result;
	}
}
