package com.pensato.javamonitor.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HardwareInfo {

    private String computerSystem;
    private String firmware;
    private String baseboard;
    private String processor;
    private List<String> physicalMemories;
    private String vendorFrequency;
    private String maxFrequency;
    private String currentFrequencies;

    public HardwareInfo() {
        this.computerSystem = "";
        this.firmware = "";
        this.baseboard = "";
        this.processor = "";
        this.physicalMemories = new ArrayList<>();
        this.vendorFrequency = "";
        this.maxFrequency = "";
        this.currentFrequencies = "";
    }

    public HardwareInfo(String computerSystem, String firmware, String baseboard, String processor,
                        List<String> physicalMemories, String vendorFrequency, String maxFrequency,
                        String currentFrequencies) {
        this.computerSystem = computerSystem;
        this.firmware = firmware;
        this.baseboard = baseboard;
        this.processor = processor;
        this.physicalMemories = physicalMemories;
        this.vendorFrequency = vendorFrequency;
        this.maxFrequency = maxFrequency;
        this.currentFrequencies = currentFrequencies;
    }

    @Override
    public String toString() {
        String info = "HardwareInfo{" +
                "computer system=" + computerSystem + '\'' +
                ", firmware=\'" + firmware + '\'' +
                ", baseboard=\'" + baseboard + '\'' +
                ", processor=\'" + processor + '\'' +
                ", physical memory:";
        StringBuilder pms = new StringBuilder();
        for (String pm: physicalMemories) {
           pms.append(" <").append(pm).append(">");
        }
        info += pms.toString() +
                ", vendor frequency\'=" + vendorFrequency + '\'' +
                ", max frequency=\'" + maxFrequency + '\'' +
                ", current frequencies\'=" + currentFrequencies + '\'' +
                "}";
        return info;
    }

    public String toHTML() {
        String info = "<p><b>Hardware Info</b><br/>" +
                "<u>System:</u> " + computerSystem + "<br />" +
                "<u>Firmware:</u> " + firmware + "<br />" +
                "<u>Baseboard:</u> " + baseboard + "<br />" +
                "<u>Processor:</u> " + processor + "<br />" +
                "<u>Physical memory:</u> " + "<br />";
        StringBuilder pms = new StringBuilder();
        for (String pm: physicalMemories) {
            pms.append("&nbsp;").append(pm).append("<br/>");
        }
        info += pms.toString() +
                "<u>Vendor frequency:</u> " + vendorFrequency + "<br/>" +
                "<u>Max frequency:</u> " + maxFrequency + "<br/>" +
                "<u>Current frequencies:</u> " + currentFrequencies +
                "</p>";
        return info;
    }

    public String getComputerSystem() {
        return computerSystem;
    }

    public void setComputerSystem(String computerSystem) {
        this.computerSystem = computerSystem;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public String getBaseboard() {
        return baseboard;
    }

    public void setBaseboard(String baseboard) {
        this.baseboard = baseboard;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public List<String> getPhysicalMemories() {
        return physicalMemories;
    }

    public void setPhysicalMemories(List<String> physicalMemories) {
        this.physicalMemories = physicalMemories;
    }

    public String getVendorFrequency() {
        return vendorFrequency;
    }

    public void setVendorFrequency(String vendorFrequency) {
        this.vendorFrequency = vendorFrequency;
    }

    public String getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(String maxFrequency) {
        this.maxFrequency = maxFrequency;
    }

    public String getCurrentFrequencies() {
        return currentFrequencies;
    }

    public void setCurrentFrequencies(String currentFrequencies) {
        this.currentFrequencies = currentFrequencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HardwareInfo)) return false;
        HardwareInfo that = (HardwareInfo) o;
        return Objects.equals(getComputerSystem(), that.getComputerSystem()) &&
                Objects.equals(getFirmware(), that.getFirmware()) &&
                Objects.equals(getBaseboard(), that.getBaseboard()) &&
                Objects.equals(getProcessor(), that.getProcessor()) &&
                Objects.equals(getPhysicalMemories(), that.getPhysicalMemories()) &&
                Objects.equals(getVendorFrequency(), that.getVendorFrequency()) &&
                Objects.equals(getMaxFrequency(), that.getMaxFrequency()) &&
                Objects.equals(getCurrentFrequencies(), that.getCurrentFrequencies());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getComputerSystem(), getFirmware(), getBaseboard(), getProcessor(), getPhysicalMemories(), getVendorFrequency(), getMaxFrequency(), getCurrentFrequencies());
    }
}
