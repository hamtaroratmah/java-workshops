package cuisine;

import static util.Util.*;
import java.time.Duration;

public class Instruction implements  Cloneable{

    public Instruction clone() throws CloneNotSupportedException{
        try {
            return (Instruction) super.clone();
        }catch (CloneNotSupportedException e){
            throw new InternalError();
        }
    }

    private String description;
    private Duration dureeEnMinutes;

    public Instruction(String description, int dureeEnMinute) {
        checkString(description);
        checkObject(dureeEnMinute);
        this.description = description;
        this.dureeEnMinutes = Duration.ofMinutes(dureeEnMinute);
    }

    public String getDescription() {
        return description;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDureeEnMinutes(Duration dureeEnMinute) {
        this.dureeEnMinutes = dureeEnMinute;
    }

    public String toString(){
        return "" + String.format("(%02d:%02d)", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutesPart()) + " " + description;
    }
}
