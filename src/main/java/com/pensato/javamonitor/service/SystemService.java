package com.pensato.javamonitor.service;

import com.pensato.javamonitor.model.HardwareInfo;
import com.pensato.javamonitor.model.OperatingSystemInfo;
import com.pensato.javamonitor.model.SystemLoadInfo;

/**
 * Interface for Operating System and Hardware Information
 */
public interface SystemService {

    OperatingSystemInfo getOperatingSystemInfo();
    HardwareInfo getHardwareInfo();
    SystemLoadInfo getSystemLoadInfo();
}
