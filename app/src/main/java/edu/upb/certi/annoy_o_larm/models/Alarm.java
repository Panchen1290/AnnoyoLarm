package edu.upb.certi.annoy_o_larm.models;

/**
 * Created by Andrew on 18-Dec-17.
 */

public class Alarm {
    private int time;
    private String description;
    private boolean enabled;

    public Alarm(int time, String description, boolean enabled) {
        this.time = time;
        this.description = description;
        this.enabled = enabled;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
