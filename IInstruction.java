
/**
 * Write a description of class IInstruction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class IInstruction extends GenInstruction
{
    int opcode;
    int rs;
    int rt;
    int immediate;

    public IInstruction (int opcode, int rs, int rt, int immediate) {
        this.opcode = opcode;
        this.rs = rs;
        this.rt = rt;
        this.immediate = immediate;
    }
}
