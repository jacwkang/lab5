/**
 * Created by homecomputer on 11/16/15.
 */
public class Execute {
	public long run(String instruction, int aLUSrcA, int aLUSrcB) {
		long result = 0;
		switch(String instruction) {
			case "lw":
				result = aLUSrcA = aLUSrcB;
			case "jal":
				result = aLUSrcA = aLUSrcB;
			case "or":
				result = aLUSrcA | aLUSrcB;
			case "ori":
				result = aLUSrcA | aLUSrcB;
			case "and":
				result = aLUSrcA & aLUSrcB;
			case "beq":
				result = aLUSrcA - aLUSrcA;
			case "addiu":
				result = abs(aLUSrcA) + abs(aLUSrcB);
			case "bne":
				result = aLUSrcA - aLUSrcA;
			case "jr":
				result = aLUSrcA = aLUSrcB;
		}
		return result;
	}
}
