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

public class Consumer implements Runnable {
    private BlockingQueue<String> queue;
    private NormalDistribution distribution;
    private Buffer buf;

    public Consumer(BlockingQueue<String> queue, double mean, double deviation, Buffer buf) {
        this.queue = queue;
        this.distribution = new NormalDistribution(mean, deviation);
        this.buf = buf;
    }
    
    @Override
    public void run() {
        try {
            String queueValue;
            long sleepTime;
            
            while (!"exit".equals(queueValue = queue.take())) {                
                sleepTime = Math.abs((long)distribution.sample());
                Thread.sleep(sleepTime);
                
                // Log for Dev
                System.out.println("Consumed: " + queueValue);
            }
            buf.zero();
        } catch (InterruptedException e) {
            System.out.println("Consumer exception: " + e);
        }
    }
}
