/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pupys
 */
public class ThreadGenerator implements Runnable {

    private TaskGenerator taskGenerator;
    CustomQueue inQ;
    private boolean isThreadOn = true;
    private int queueSize;

    public ThreadGenerator(TaskGenerator taskGenerator, int queueSize) {
        this.taskGenerator = taskGenerator;
        this.inQ = taskGenerator.queue;
        this.queueSize = queueSize;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < queueSize) {
            try {
                taskGenerator.generate(i/*, inQ*/);
                i++;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
