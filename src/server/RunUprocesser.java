package com.epic.mfn.server;
import com.epic.mfn.pool.upool.UAbstractServer;

public class RunUprocesser extends UAbstractServer{
	public RunUprocesser(int port, int backlog, String requestHandlerClassName,int maxQueueLength, int minThreads, int maxThreads) {
		
		super( port, backlog, requestHandlerClassName, maxQueueLength, minThreads,maxThreads);
		
		startServer();
	}
}
