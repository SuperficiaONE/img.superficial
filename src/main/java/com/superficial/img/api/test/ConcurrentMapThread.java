package com.superficial.img.api.test;

public class ConcurrentMapThread  extends  Thread{
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            TestService.testConMap();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
