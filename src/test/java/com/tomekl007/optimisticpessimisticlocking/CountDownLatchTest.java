package com.tomekl007.optimisticpessimisticlocking;


import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CountDownLatchTest {

    @Test
    public void givenNActions_whenStartThen_thenShouldExecuteThemInCoordinatedWay() throws InterruptedException {
        //given
        List<Runnable> actions = List.of(this::longRunningAction, this::longRunningAction);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //when
        List<Runnable> runnableList = actions.stream().map((r) -> (Runnable) () -> {
            try {
                System.out.println("Before countDownLatch: " + System.currentTimeMillis());
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r.run();
            System.out.println("After run: " + System.currentTimeMillis());
        }).collect(Collectors.toList());

        runnableList.forEach(executorService::execute);
        countDownLatch.countDown();

        //then
        executorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
        executorService.shutdown();


    }

    private void longRunningAction() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
