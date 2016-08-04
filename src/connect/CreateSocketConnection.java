package com.epic.mfn.connect;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import com.epic.mfn.util.PrintLogs;

public class CreateSocketConnection implements Runnable{
	
	private HostConnection hostConnection;

	public CreateSocketConnection(HostConnection hostConnection){
		this.hostConnection = hostConnection;
	}

	public HostConnection getHostConnection() {
		return hostConnection;
	}


	public static Socket getUnpermentConnection(HostConnection hostConnection) throws Exception {
		InetAddress addr = InetAddress.getByName(hostConnection.getHostIp());
		SocketAddress sockaddr = new InetSocketAddress(addr, hostConnection.getHostPort());
		Socket bankSocket = new Socket();
		bankSocket.connect(sockaddr, hostConnection.getTimeOut());
		return bankSocket;
	}
	
	public Socket getUnpermentConnection() throws Exception {
		return CreateSocketConnection.getUnpermentConnection(hostConnection);
	}

	
	public void run() {
		while(true){
			
			try {
				Thread.sleep(5000);
				if(! hostConnection.isSocektConnected()){
					 hostConnection.setSocektConnected(true);
					 hostConnection.setPermentSocekt(getUnpermentConnection());
					 PrintLogs.printLine("Successfully connected with  "+hostConnection.getHostIp() +" and reading port "+hostConnection.getHostPort());
				}
			} catch (Exception e) {
				hostConnection.setSocektConnected(false);
				PrintLogs.printLine("Trying to connect with "+hostConnection.getHostIp() +" and reading port "+hostConnection.getHostPort());
				continue;
			}
		}
		
	}

}
