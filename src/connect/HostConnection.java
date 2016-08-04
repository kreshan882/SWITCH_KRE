package com.epic.mfn.connect;

import java.net.Socket;

public class HostConnection {
	
	private String hostName;
	private boolean active;
	private boolean permenetConnectionMode;
	private String hostIp;
	private int hostPort;
	private int timeOut;
	
	private Socket permentSocekt;
	private boolean socektConnected;
	
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public int getHostPort() {
		return hostPort;
	}
	public void setHostPort(int hostPort) {
		this.hostPort = hostPort;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isActive() {
		return active;
	}
	public void setPermenetConnectionMode(boolean permenetConnect) {
		this.permenetConnectionMode = permenetConnect;
	}
	public boolean isPermenetConnectionMode() {
		return permenetConnectionMode;
	}
	public void setSocektConnected(boolean socektConnected) {
		this.socektConnected = socektConnected;
	}
	public boolean isSocektConnected() {
		return socektConnected;
	}
	public void setPermentSocekt(Socket permentSocekt) {
		this.permentSocekt = permentSocekt;
	}
	public Socket getPermentSocekt() {
		return permentSocekt;
	}
	
}
