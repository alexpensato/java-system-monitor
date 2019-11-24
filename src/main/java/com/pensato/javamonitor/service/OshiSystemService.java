package com.pensato.javamonitor.service;

import com.pensato.javamonitor.model.HardwareInfo;
import com.pensato.javamonitor.model.OperatingSystemInfo;
import com.pensato.javamonitor.model.SystemLoadInfo;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Concrete system class using Oshi to retrieve System info via JNA
 */
public class OshiSystemService implements SystemService {

    private static final SystemInfo si = new SystemInfo();

    @Override
    public OperatingSystemInfo getOperatingSystemInfo() {
        OperatingSystem oshiOS = si.getOperatingSystem();
        return new OperatingSystemInfo(
                String.valueOf(oshiOS),
                Instant.ofEpochSecond(oshiOS.getSystemBootTime()),
                FormatUtil.formatElapsedSecs(oshiOS.getSystemUptime()),
                oshiOS.isElevated()
        );
    }

    @Override
    public HardwareInfo getHardwareInfo() {
        HardwareAbstractionLayer hal = si.getHardware();
        ComputerSystem computerSystem = hal.getComputerSystem();
        CentralProcessor centralProcessor = hal.getProcessor();
        GlobalMemory globalMemory = hal.getMemory();

        HardwareInfo hardware = new HardwareInfo();

        hardware.setComputerSystem(computerSystem.toString());
        hardware.setFirmware(computerSystem.getFirmware().toString());
        hardware.setBaseboard(computerSystem.getBaseboard().toString());
        hardware.setProcessor(centralProcessor.toString());

        List<String> physicalMemories = new ArrayList<>();
        PhysicalMemory[] pms = globalMemory.getPhysicalMemory();
        if (pms.length > 0) {
            for (PhysicalMemory pm : pms) {
                physicalMemories.add(pm.toString());
            }
        }
        hardware.setPhysicalMemories(physicalMemories);

        long freq = centralProcessor.getProcessorIdentifier().getVendorFreq();
        if (freq > 0) {
            hardware.setVendorFrequency(FormatUtil.formatHertz(freq));
        }
        freq = centralProcessor.getMaxFreq();
        if (freq > 0) {
           hardware.setMaxFrequency(FormatUtil.formatHertz(freq));
        }
        long[] freqs = centralProcessor.getCurrentFreq();
        if (freqs[0] > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < freqs.length; i++) {
                if (i > 0) {
                    sb.append(" | ");
                }
                sb.append(FormatUtil.formatHertz(freqs[i]));
            }
            hardware.setCurrentFrequencies(sb.toString());
        }

        return hardware;
    }

    @Override
    public SystemLoadInfo getSystemLoadInfo() {
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor centralProcessor = hal.getProcessor();
        GlobalMemory globalMemory = hal.getMemory();

        SystemLoadInfo systemLoadInfo = new SystemLoadInfo();

        systemLoadInfo.setGlobalMemory(globalMemory.toString());
        systemLoadInfo.setSwapMemory(globalMemory.getVirtualMemory().toString());

        systemLoadInfo.setContextSwitches(centralProcessor.getContextSwitches());
        systemLoadInfo.setInterrupts(centralProcessor.getInterrupts());

        long[] prevSystemTicks = centralProcessor.getSystemCpuLoadTicks();
        systemLoadInfo.setSystemTicksOnT0(Arrays.toString(prevSystemTicks));
        long[][] prevProcTicks = centralProcessor.getProcessorCpuLoadTicks();
        // Wait a second...
        Util.sleep(1000);
        long[] systemTicks = centralProcessor.getSystemCpuLoadTicks();
        systemLoadInfo.setSystemTicksOnT1(Arrays.toString(systemTicks));

        long user = systemTicks[CentralProcessor.TickType.USER.getIndex()] - prevSystemTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = systemTicks[CentralProcessor.TickType.NICE.getIndex()] - prevSystemTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys = systemTicks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevSystemTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = systemTicks[CentralProcessor.TickType.IDLE.getIndex()] - prevSystemTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long iowait = systemTicks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevSystemTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = systemTicks[CentralProcessor.TickType.IRQ.getIndex()] - prevSystemTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = systemTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevSystemTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = systemTicks[CentralProcessor.TickType.STEAL.getIndex()] - prevSystemTicks[CentralProcessor.TickType.STEAL.getIndex()];

        systemLoadInfo.setUserTick(user);
        systemLoadInfo.setNiceTick(nice);
        systemLoadInfo.setSystemTick(sys);
        systemLoadInfo.setIdleTick(idle);
        systemLoadInfo.setIoWaitTick(iowait);
        systemLoadInfo.setIrqTick(irq);
        systemLoadInfo.setSoftIrqTick(softirq);
        systemLoadInfo.setStealTick(steal);

        systemLoadInfo.reloadTotalCpuTicks();

        systemLoadInfo.setSystemCpuLoadsBetweenTicks(centralProcessor.getSystemCpuLoadBetweenTicks(prevSystemTicks) * 100);

        double[] loadAverage = centralProcessor.getSystemLoadAverage(3);
        systemLoadInfo.setLoadAverages((loadAverage[0] < 0 ? " N/A" : String.format(" %.2f", loadAverage[0]))
                + (loadAverage[1] < 0 ? " | N/A" : String.format(" | %.2f", loadAverage[1]))
                + (loadAverage[2] < 0 ? " | N/A" : String.format(" | %.2f", loadAverage[2])));

        // per core CPU
        StringBuilder procCpu = new StringBuilder();
        double[] cpuLoad = centralProcessor.getProcessorCpuLoadBetweenTicks(prevProcTicks);
        for (int i = 0; i < cpuLoad.length; i++) {
            if (i > 0) {
                procCpu.append(" |");
            }
            procCpu.append(String.format(" %.1f%%", cpuLoad[i] * 100));
        }
        systemLoadInfo.setCpuLoadPerProcessor(procCpu.toString());

        return systemLoadInfo;
    }

}
