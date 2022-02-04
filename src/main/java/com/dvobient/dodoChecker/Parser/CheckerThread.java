package com.dvobient.dodoChecker.Parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class CheckerThread {
    private static final Logger logger = LoggerFactory.getLogger(CheckerThread.class);
    private boolean updateCheck = false;
    boolean threadNeedInit = true;
    private final HtmlParser htmlParser;

    public CheckerThread(HtmlParser htmlParser) {
        this.htmlParser = htmlParser;
    }

    private void checkerTread() {
        new Thread(() -> {
            logger.info("checker thread " + Thread.currentThread().getName() + " started");
            while (updateCheck) {
                logger.info("checker thread " + Thread.currentThread().getName() + " is working:");
                try {
                    TimeUnit.SECONDS.sleep(5);
                    htmlParser.getBot().sendUpdates(htmlParser.getUpdateData());
                    logger.info("checker thread " + Thread.currentThread().getName() + " sleep 5 min");
                    TimeUnit.MINUTES.sleep(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            threadNeedInit = true;
        }).start();
    }

    public void disableThread() {
        updateCheck = false;
    }

    public void enableThread() {
        updateCheck = true;
        if (threadNeedInit) {
            startThread();
            threadNeedInit = false;
        }
    }

    public void startThread() {
        checkerTread();
    }
}