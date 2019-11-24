package com.pensato.javamonitor.model;

import java.util.Objects;

public class SystemLoadInfo {

    private String globalMemory;
    private String swapMemory;
    private Long contextSwitches;
    private Long interrupts;
    private String systemTicksOnT0;
    private String systemTicksOnT1;
    private Long userTick;
    private Long niceTick;
    private Long systemTick;
    private Long idleTick;
    private Long ioWaitTick;
    private Long irqTick;
    private Long softIrqTick;
    private Long stealTick;
    private Long totalCpuTicks;
    private Double systemCpuLoadsBetweenTicks;
    private String loadAverages;
    private String cpuLoadPerProcessor;

    public SystemLoadInfo() {
        this.globalMemory = "";
        this.swapMemory = "";
        this.contextSwitches = 0L;
        this.interrupts = 0L;
        this.systemTicksOnT0 = "";
        this.systemTicksOnT1 = "";
        this.userTick = 0L;
        this.niceTick = 0L;
        this.systemTick = 0L;
        this.idleTick = 0L;
        this.ioWaitTick = 0L;
        this.irqTick = 0L;
        this.softIrqTick = 0L;
        this.stealTick = 0L;
        this.totalCpuTicks = 0L;
        this.systemCpuLoadsBetweenTicks = 0.0;
        this.loadAverages = "";
        this.cpuLoadPerProcessor = "";
    }

    public SystemLoadInfo(String globalMemory, String swapMemory, Long contextSwitches, Long interrupts,
                          String systemTicksOnT0, String systemTicksOnT1, Long userTick, Long niceTick, Long systemTick,
                          Long idleTick, Long ioWaitTick, Long irqTick, Long softIrqTick, Long stealTick,
                          Long totalCpuTicks, Double systemCpuLoadsBetweenTicks, String loadAverages,
                          String cpuLoadPerProcessor) {
        this.globalMemory = globalMemory;
        this.swapMemory = swapMemory;
        this.contextSwitches = contextSwitches;
        this.interrupts = interrupts;
        this.systemTicksOnT0 = systemTicksOnT0;
        this.systemTicksOnT1 = systemTicksOnT1;
        this.userTick = userTick;
        this.niceTick = niceTick;
        this.systemTick = systemTick;
        this.idleTick = idleTick;
        this.ioWaitTick = ioWaitTick;
        this.irqTick = irqTick;
        this.softIrqTick = softIrqTick;
        this.stealTick = stealTick;
        this.totalCpuTicks = totalCpuTicks;
        this.systemCpuLoadsBetweenTicks = systemCpuLoadsBetweenTicks;
        this.loadAverages = loadAverages;
        this.cpuLoadPerProcessor = cpuLoadPerProcessor;
    }

    @Override
    public String toString() {
        String info = "SystemLoadInfo{" +
                "global memory=\'" + globalMemory + '\'' +
                ", swap memory=\'" + swapMemory + '\'' +
                ", context switches=\'" + contextSwitches + '\'' +
                ", interrupts=\'" + interrupts + '\'' +
                ", cpu, IO wait, and IRQ ticks @ 0 sec=\'" + systemTicksOnT0 + '\'' +
                ", cpu, IO wait, and IRQ ticks @ 1 sec=\'" + systemTicksOnT1 + '\'' +
                ", Ticks: " +
                String.format("<user: %.1f%%> ", 100d * userTick / totalCpuTicks) +
                String.format("<nice: %.1f%%> ", 100d * niceTick / totalCpuTicks) +
                String.format("<system: %.1f%%> ", 100d * systemTick / totalCpuTicks) +
                String.format("<idle: %.1f%%> ", 100d * idleTick / totalCpuTicks) +
                String.format("<IO Wait: %.1f%%> ", 100d * ioWaitTick / totalCpuTicks) +
                String.format("<IRQ: %.1f%%> ", 100d * irqTick / totalCpuTicks) +
                String.format("<soft IRQ: %.1f%%> ", 100d * softIrqTick / totalCpuTicks) +
                String.format("<steal: %.1f%%> ", 100d * stealTick / totalCpuTicks) +
                String.format(", CPU load=\'%.1f%%\'", systemCpuLoadsBetweenTicks) +
                ", CPU load averages=\'" + loadAverages + '\'' +
                ", CPU load per processor=\'" + cpuLoadPerProcessor + '\'' +
                "}";
        return info;
    }

    public String toHTML() {
        String info = "<p><b>System Load Info</b><br/>" +
                "<u>Global memory:</u> " + globalMemory + "<br/>" +
                "<u>Swap memory:</u> " + swapMemory + "<br/>" +
                "<u>Physical memory:</u> " + "<br/>" +
                "<u>Context Switches / Interrupts:</u> " + contextSwitches + " / " + interrupts + "<br/>" +
                "<i>CPU, IO wait, and IRQ ticks @ 0 sec:</i> " + systemTicksOnT0 + "<br/>" +
                "<i>CPU, IO wait, and IRQ ticks @ 1 sec:</i> " + systemTicksOnT1 + "<br/>" +
                "<u>System ticks:</u> " +
                String.format("User: %.1f%%", 100d * userTick / totalCpuTicks) + "&nbsp;|&nbsp;" +
                String.format("Nice: %.1f%%", 100d * niceTick / totalCpuTicks) + "&nbsp;|&nbsp;" +
                String.format("System: %.1f%%", 100d * systemTick / totalCpuTicks) + "&nbsp;|&nbsp;" +
                String.format("Idle: %.1f%%", 100d * idleTick / totalCpuTicks) + "&nbsp;|&nbsp;" +
                String.format("IO Wait: %.1f%%", 100d * ioWaitTick / totalCpuTicks) + "&nbsp;|&nbsp;" +
                String.format("IRQ: %.1f%%", 100d * irqTick / totalCpuTicks) + "&nbsp;|&nbsp;" +
                String.format("Soft IRQ: %.1f%%", 100d * softIrqTick / totalCpuTicks) + "&nbsp;|&nbsp;" +
                String.format("Steal: %.1f%%", 100d * stealTick / totalCpuTicks) + "<br/>" +
                String.format("<u>CPU load:</u> %.1f%%", systemCpuLoadsBetweenTicks) + "<br/>" +
                "<u>CPU load averages:</u> " + loadAverages + "<br/>" +
                "<u>CPU load per processor:</u> " + cpuLoadPerProcessor + "<br/>" +
                "</p>";
        return info;
    }

    public void reloadTotalCpuTicks() {
        this.totalCpuTicks = userTick + niceTick + systemTick + idleTick + ioWaitTick + irqTick + softIrqTick + stealTick;
    }

    public String getGlobalMemory() {
        return globalMemory;
    }

    public void setGlobalMemory(String globalMemory) {
        this.globalMemory = globalMemory;
    }

    public String getSwapMemory() {
        return swapMemory;
    }

    public void setSwapMemory(String swapMemory) {
        this.swapMemory = swapMemory;
    }

    public Long getContextSwitches() {
        return contextSwitches;
    }

    public void setContextSwitches(Long contextSwitches) {
        this.contextSwitches = contextSwitches;
    }

    public Long getInterrupts() {
        return interrupts;
    }

    public void setInterrupts(Long interrupts) {
        this.interrupts = interrupts;
    }

    public String getSystemTicksOnT0() {
        return systemTicksOnT0;
    }

    public void setSystemTicksOnT0(String systemTicksOnT0) {
        this.systemTicksOnT0 = systemTicksOnT0;
    }

    public String getSystemTicksOnT1() {
        return systemTicksOnT1;
    }

    public void setSystemTicksOnT1(String systemTicksOnT1) {
        this.systemTicksOnT1 = systemTicksOnT1;
    }

    public Long getUserTick() {
        return userTick;
    }

    public void setUserTick(Long userTick) {
        this.userTick = userTick;
    }

    public Long getNiceTick() {
        return niceTick;
    }

    public void setNiceTick(Long niceTick) {
        this.niceTick = niceTick;
    }

    public Long getSystemTick() {
        return systemTick;
    }

    public void setSystemTick(Long systemTick) {
        this.systemTick = systemTick;
    }

    public Long getIdleTick() {
        return idleTick;
    }

    public void setIdleTick(Long idleTick) {
        this.idleTick = idleTick;
    }

    public Long getIoWaitTick() {
        return ioWaitTick;
    }

    public void setIoWaitTick(Long ioWaitTick) {
        this.ioWaitTick = ioWaitTick;
    }

    public Long getIrqTick() {
        return irqTick;
    }

    public void setIrqTick(Long irqTick) {
        this.irqTick = irqTick;
    }

    public Long getSoftIrqTick() {
        return softIrqTick;
    }

    public void setSoftIrqTick(Long softIrqTick) {
        this.softIrqTick = softIrqTick;
    }

    public Long getStealTick() {
        return stealTick;
    }

    public void setStealTick(Long stealTick) {
        this.stealTick = stealTick;
    }

    public Long getTotalCpuTicks() {
        return totalCpuTicks;
    }

    public void setTotalCpuTicks(Long totalCpuTicks) {
        this.totalCpuTicks = totalCpuTicks;
    }

    public Double getSystemCpuLoadsBetweenTicks() {
        return systemCpuLoadsBetweenTicks;
    }

    public void setSystemCpuLoadsBetweenTicks(Double systemCpuLoadsBetweenTicks) {
        this.systemCpuLoadsBetweenTicks = systemCpuLoadsBetweenTicks;
    }

    public String getLoadAverages() {
        return loadAverages;
    }

    public void setLoadAverages(String loadAverages) {
        this.loadAverages = loadAverages;
    }

    public String getCpuLoadPerProcessor() {
        return cpuLoadPerProcessor;
    }

    public void setCpuLoadPerProcessor(String cpuLoadPerProcessor) {
        this.cpuLoadPerProcessor = cpuLoadPerProcessor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemLoadInfo)) return false;
        SystemLoadInfo that = (SystemLoadInfo) o;
        return Objects.equals(getGlobalMemory(), that.getGlobalMemory()) &&
                Objects.equals(getSwapMemory(), that.getSwapMemory()) &&
                Objects.equals(getContextSwitches(), that.getContextSwitches()) &&
                Objects.equals(getInterrupts(), that.getInterrupts()) &&
                Objects.equals(getSystemTicksOnT0(), that.getSystemTicksOnT0()) &&
                Objects.equals(getSystemTicksOnT1(), that.getSystemTicksOnT1()) &&
                Objects.equals(getUserTick(), that.getUserTick()) &&
                Objects.equals(getNiceTick(), that.getNiceTick()) &&
                Objects.equals(getSystemTick(), that.getSystemTick()) &&
                Objects.equals(getIdleTick(), that.getIdleTick()) &&
                Objects.equals(getIoWaitTick(), that.getIoWaitTick()) &&
                Objects.equals(getIrqTick(), that.getIrqTick()) &&
                Objects.equals(getSoftIrqTick(), that.getSoftIrqTick()) &&
                Objects.equals(getStealTick(), that.getStealTick()) &&
                Objects.equals(getTotalCpuTicks(), that.getTotalCpuTicks()) &&
                Objects.equals(getSystemCpuLoadsBetweenTicks(), that.getSystemCpuLoadsBetweenTicks()) &&
                Objects.equals(getLoadAverages(), that.getLoadAverages()) &&
                Objects.equals(getCpuLoadPerProcessor(), that.getCpuLoadPerProcessor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGlobalMemory(), getSwapMemory(), getContextSwitches(), getInterrupts(), getSystemTicksOnT0(), getSystemTicksOnT1(), getUserTick(), getNiceTick(), getSystemTick(), getIdleTick(), getIoWaitTick(), getIrqTick(), getSoftIrqTick(), getStealTick(), getTotalCpuTicks(), getSystemCpuLoadsBetweenTicks(), getLoadAverages(), getCpuLoadPerProcessor());
    }
}
