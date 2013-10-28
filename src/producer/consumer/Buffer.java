/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package producer.consumer;

import java.util.concurrent.BlockingQueue;
import javax.swing.JProgressBar;
import javax.swing.JSlider;

/**
 *
 * @author cojoj
 */

public class Buffer {
    private int bufferSize;
    private JSlider bufferSlider;
    
    public Buffer(int bufferSize, JSlider slider) {
        this.bufferSize = bufferSize;
        this.bufferSlider = slider;
        bufferSlider.setMaximum(bufferSize);
    }
    
    public void update(BlockingQueue<String> queue) {
        int i = (bufferSize- 1) - queue.remainingCapacity();
        bufferSlider.setValue(i);
    }
    
    public void zero() {
        bufferSlider.setValue(0);
    }
}
