package entity;

import java.util.Objects;


/**
 * @author Hafiz Chew Hoe Leong
 */
public class Programme implements Comparable<Programme> {

    private String code;
    private String level;
    private String name;
    private int duration;
    private int fees;
    private Date dateIntroduce;

    public Programme(String code, String level, String name, int duration, int fees, Date dateIntroduce) {
        this.code = code;
        this.level = level;
        this.name = name;
        this.duration = duration;
        this.fees = fees;
        this.dateIntroduce = dateIntroduce;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public Date getDateIntroduce() {
        return dateIntroduce;
    }

    public void setDateIntroduce(Date dateIntroduce) {
        this.dateIntroduce = dateIntroduce;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Programme other = (Programme) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (level.equals("Diploma")) {
            return String.format("%-8s %-8s In %s", code, level, name);
        } else {
            return String.format("%-8s %-8s Of %s", code, level, name);
        }
    }

    @Override
    public int compareTo(Programme otherProgramme) {
        return  this.getName().compareTo(otherProgramme.getName());
    }

}
