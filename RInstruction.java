
/**
 * Write a description of class RInstruction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RInstruction extends GenInstruction
{
    int opcode;
    int rs;
    int rt;
    int rd;
    int sa;
    int function;

    public RInstruction(int opcode, int rs, int rt, int rd, int sa, int function) {
        this.opcode = opcode;
        this.rs = rs;
        this.rt = rt;
        this.rd = rd;
        this.sa = sa;
        this.function = function;
    }
    
    public int getRs() {
        return rs;
    }
    
    public int getRt() {
        return rt;
    }
}
