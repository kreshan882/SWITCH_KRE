package com.epic.mfn.pool.upool;

import java.net.BindException;

import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import com.epic.mfn.util.PrintLogs;



/**
 * Abstract super class for creating servers
 * R.P.D Kapila shantha rajapaksha
 */
public abstract class UAbstractServer extends Thread
{
   
    protected ServerSocket serverSocket;

    
    protected boolean running;

   
    protected int port;

   
    protected int backlog;

   
    protected URequestQueue requestQueue;
    
    public URequestQueue getRequestQueue() {
		return requestQueue;
	}

	public UAbstractServer(URequestQueue uRequestQueue){
		this.requestQueue =uRequestQueue;
    }
    
	public UAbstractServer(int port, int backlog,String requestHandlerClassName, int maxQueueLength, int minThreads,int maxThreads) {
       
        this.port = port;
        this.backlog = backlog;
       
        this.requestQueue = new URequestQueue( requestHandlerClassName,maxQueueLength,minThreads,maxThreads );
        
      
		
		
    }

    
	public void startServer() {
		try {
					
			ServerSocketFactory ssf = ServerSocketFactory.getDefault();
			serverSocket = ssf.createServerSocket(this.port, this.backlog);
			
			this.start();

		} catch (BindException e) {
			PrintLogs.printLine("Address alredy in user cannot start server");
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

   

    
    public void run()
    {
        // Start the server
        PrintLogs.printLine("Server Started, listening on port: " + this.port );
        PrintLogs.printLine("Successfully started and waitting for incoming request....................");
        
        this.running = true;
        while( running )
        {
            try
            { 
                Socket s = serverSocket.accept();
               
                this.requestQueue.add( s );
            }
           
            catch( Exception e )
            {
                e.printStackTrace();
            }
        }
       
    }
}