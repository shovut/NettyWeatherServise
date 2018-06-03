import java.util.*;

class ThreadPrint implements Runnable {

    private Logger logger;
    private CustomQueue outQueue;

    public ThreadPrint(Logger logger, CustomQueue outQueue) {
        this.logger = logger;
        this.outQueue = outQueue;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
                logger.log(outQueue);
        }
    }
}
