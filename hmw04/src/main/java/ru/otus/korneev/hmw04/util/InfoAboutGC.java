package ru.otus.korneev.hmw04.util;

public class InfoAboutGC {

    private String nameGC = "";

    private long time = 0;

    private int count = 0;

    public void setNameGC(String nameGC) {
        this.nameGC = nameGC;
    }

    public void incrementCount() {
        count++;
    }

    public void summTime(long duration) {
        time = time + duration;
    }

    @Override
    public String toString() {
        return nameGC + " GC run " + count + " times, " + "time duration: " + time + " ms.";
    }
}
