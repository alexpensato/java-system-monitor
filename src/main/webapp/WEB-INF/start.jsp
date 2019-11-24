<%@ page import="com.pensato.javamonitor.model.OperatingSystemInfo" %>
<%@ page import="com.pensato.javamonitor.model.HardwareInfo" %>
<%@ page import="com.pensato.javamonitor.model.SystemLoadInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    OperatingSystemInfo osInfo = (OperatingSystemInfo) request.getAttribute("osInfo");
    HardwareInfo hardware = (HardwareInfo) request.getAttribute("hardware");
    SystemLoadInfo systemLoadInfo = (SystemLoadInfo) request.getAttribute("systemLoadInfo");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>System Information</title>
</head>
<body>
<div><%=osInfo.toHTML()%></div>
<div><%=hardware.toHTML()%></div>
<div><%=systemLoadInfo.toHTML()%></div>
</body>
</html>