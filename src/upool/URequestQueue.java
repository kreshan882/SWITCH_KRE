package com.epic.mfn.pool.upool;

import java.net.Socket;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;

import com.epic.mfn.util.PrintLogs;



/**
 * A Request Queue accepts new requests and processes them with its associated
 * thread pool
 * R.P.D Kapila shantha rajapaksha
 */
public class URequestQueue
{
    private LinkedList<Socket> queue = new LinkedList<Socket>();

    private int maxQueueLength;
    private int minThreads;
    private int maxThreads;
    
    protected int currentThreads = 0;
    protected String requestHandlerClassName;

    
    List<Thread> threadPool = new ArrayList<Thread>();

    private boolean running = true;

    
    public URequestQueue( String requestHandlerClassName, int maxQueueLength,  int minThreads, int maxThreads )
    {
       
        this.requestHandlerClassName = requestHandlerClassName;
        this.maxQueueLength = maxQueueLength;
        this.minThreads = minThreads;
        this.maxThreads = maxThreads;

        for( int i=0; i<this.minThreads; i++ )
        {
            URequestThread thread = new URequestThread( this, currentThreads ++, requestHandlerClassName );
            thread.start();
            this.threadPool.add( thread );
        }
    }

    
    public String getRequestHandlerClassName()
    {
        return this.requestHandlerClassName;
    }


    public synchronized void add( Socket socket ) 
    {
        
        if( queue.size() > this.maxQueueLength )
        {
            PrintLogs.printLine( "The Request Queue is full. Max size = " + this.maxQueueLength );
            return;
        }
        
        boolean availableThread = false;
        
        for (int i = 0; i < threadPool.size(); i++) {
            if( !((URequestThread)threadPool.get(i)).isProcessing() )
            {
                PrintLogs.printLine( "Found an available thread" );
                
                availableThread = true;
                break;
            }
            PrintLogs.printLine( "Thread is busy " );
		}

        
        

        
        if( !availableThread )
        {
            if( this.currentThreads < this.maxThreads )
            {
                System.out.println( "Creating a new thread to satisfy the incoming request" );
                URequestThread thread = new URequestThread( this, currentThreads++, this.requestHandlerClassName );
                thread.start();
                this.threadPool.add( thread );
            }
            else
            {
                System.out.println( "Can't grow the thread pool, guess you have to wait" );
            }
        }

        queue.addLast( socket );

     
        notifyAll();
		
    }

    public synchronized Object getNextObject(int thred)
    {
        while( queue.isEmpty() )
        {
            try
            {
                if( !running )
                {
                    
                    return null;
                }
                this.wait();
            }
            catch( InterruptedException ie ) {}
        }
        return queue.removeFirst();
    }

    

    

    
}            