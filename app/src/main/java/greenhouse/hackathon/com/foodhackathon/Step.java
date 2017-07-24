package greenhouse.hackathon.com.foodhackathon;

/**
 * Created by KSH on 2017-07-21.
 */

public class Step{
    private String instruction;
    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
