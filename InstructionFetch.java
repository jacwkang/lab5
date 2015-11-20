/**
 * Created by homecomputer on 11/16/15.
 */
public class InstructionFetch {
    PipelineRegister iF;
    static int programCounter = 0;
    
    public InstructionFetch(PipelineRegister iF) {
        this.iF = iF;
    }
    
    public void run(GenInstruction instruction) {
        programCounter += 4;
    }
}
