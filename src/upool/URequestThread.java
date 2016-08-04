package com.epic.mfn.pool.upool;

import java.net.Socket;

import com.epic.mfn.logs.LogFileCreator;
import com.epic.mfn.util.PrintLogs;





/**
 * A Request thread handles incoming requests
 * R.P.D Kapila shantha rajapaksha
 */
public class URequestThread extends Thread
{
   
    private URequestQueue queue;

   
    private boolean running;

    
    private boolean processing = false;

    
    private int threadNumber;

    
    private URequestHandler requestHandler;

    
    public URequestThread( URequestQueue queue, int threadNumber, String requestHandlerClassName )
    {
        this.queue = queue;
        this.threadNumber = threadNumber;
        try
        {
            this.requestHandler = ( URequestHandler )( Class.forName( requestHandlerClassName ).newInstance() );
        }
        catch( Exception e )
        {
        	
        	LogFileCreator.writErrorTologs(e);
        }
    }

   
    public boolean isProcessing()
    {
        return this.processing;
    }

    
    public void killThread()
    {
        PrintLogs.printLine( "[" + threadNumber + "]: Attempting to kill thread..." );
        this.running = false;
      
    }

    
    public void run()
    {
        this.running = true;
        while( running )
        {
            try
            {
            	
                Object o = queue.getNextObject(threadNumber);
                if( running )
                {
                    Socket socket = ( Socket )o;
                    this.processing = true;
                    PrintLogs.printLine( "Processing thread number     [" + threadNumber + "]: Processing request..." );
                    
                    this.requestHandler.handleRequest( socket );
                    PrintLogs.printLine( "Processing thread number   [" + threadNumber + "]: Finished Processing request..." );
                    
         
                    this.processing = false;
                }
            }
            catch( Exception e )
            {
            	this.processing = false;
            }finally{
            	 PrintLogs.printLine("Processing thread returns back to the thread pool....");
            	 this.processing = false;
            }
        }

        PrintLogs.printLine( "[" + threadNumber + "]: Thread shutting down..." );
    }
}