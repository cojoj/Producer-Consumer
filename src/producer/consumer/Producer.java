/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package producer.consumer;

import java.util.concurrent.BlockingQueue;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 *
 * @author cojoj
 */

public class Producer implements Runnable {
    private BlockingQueue<String> queue;
    private int steps;
    private NormalDistribution distribution;
    private Buffer buf;

    public Producer(BlockingQueue<String> queue, int steps, double mean, double deviation, Buffer buf) {
        this.queue = queue;
        this.steps = steps;
        this.distribution = new NormalDistribution(mean, deviation);
        this.buf = buf;
    }
    
    @Override
    public void run() {
        long sleepTime;
        for (int i = 0; i < steps + 1; i++) {
            try {
                sleepTime = Math.abs((long)distribution.sample());
                Thread.sleep(sleepTime);
                queue.put(String.valueOf(i));
                buf.update(queue);
                
                // Logs for development
                System.out.println("Produced: " + i);
            } catch (InterruptedException e) {
                System.out.println("Producer exception: " + e);
            }
        }
        
        try {
            queue.put("exit");
        } catch (InterruptedException e) {
            System.out.println("Buffer exception: " + e);
        }
    }
}
