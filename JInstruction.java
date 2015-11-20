
/**
 * Write a description of class JInstruction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class JInstruction extends GenInstruction
{
    int opcode;
    long target;

    public JInstruction (int opcode, long target) {
        this.opcode = opcode;
        this.target = target;
    }
}
