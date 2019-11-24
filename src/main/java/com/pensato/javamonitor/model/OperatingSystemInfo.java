package com.pensato.javamonitor.model;

import java.time.Instant;
import java.util.Objects;

public class OperatingSystemInfo {
    private String name;
    private Instant bootTime;
    private String uptime;
    private boolean elevatedPermissions;

    public OperatingSystemInfo() {
        this.name = "";
        this.bootTime = Instant.now();
        this.uptime = "";
        this.elevatedPermissions = false;
    }

    public OperatingSystemInfo(String name, Instant bootTime, String uptime, boolean elevatedPermissions) {
        this.name = name;
        this.bootTime = bootTime;
        this.uptime = uptime;
        this.elevatedPermissions = elevatedPermissions;
    }

    @Override
    public String toString() {
        return "OperatingSystemInfo{" +
                "name=\'" + name + '\'' +
                ", booted=\'" + bootTime + '\'' +
                ", uptime=\'" + uptime + '\'' +
                ", running with" + (elevatedPermissions ? "" : "out") + " elevated permissions." +
                '}';
    }

    public String toHTML() {
        return "<p><b>Operating System Info</b><br/>" +
                "<u>System:</u> " + name + "<br/>" +
                "<u>Booted:</u> " + bootTime + "<br/>" +
                "<u>Uptime:</u> " + uptime + "<br/>" +
                "<i>Running with" + (elevatedPermissions ? "" : "out") + " elevated permissions.</i>" +
                "</p>";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getBootTime() {
        return bootTime;
    }

    public void setBootTime(Instant bootTime) {
        this.bootTime = bootTime;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public boolean isElevatedPermissions() {
        return elevatedPermissions;
    }

    public void setElevatedPermissions(boolean elevatedPermissions) {
        this.elevatedPermissions = elevatedPermissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OperatingSystemInfo)) return false;
        OperatingSystemInfo that = (OperatingSystemInfo) o;
        return isElevatedPermissions() == that.isElevatedPermissions() &&
                getName().equals(that.getName()) &&
                getBootTime().equals(that.getBootTime()) &&
                getUptime().equals(that.getUptime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBootTime(), getUptime(), isElevatedPermissions());
    }

}
