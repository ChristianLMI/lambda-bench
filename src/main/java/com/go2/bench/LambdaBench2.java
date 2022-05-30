/*
 * Copyright (c) 2022 LogMeIn
 * All Rights Reserved Worldwide.
 *
 * THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO LOGMEIN
 * AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.go2.bench;

import java.util.function.Consumer;

import org.openjdk.jmh.annotations.Benchmark;

public class LambdaBench2 {

    @Benchmark
    public void noLambda_directInvocation() {
        Worker w = new Worker();
        w.doWork();
    }

    @Benchmark
    public void noLambda_indirectInvocation() {
        Worker w = new Worker();
        invokeWork(w);
    }

    private void invokeWork(Worker w) {
        w.doWork();
    }

    @Benchmark
    public void withLambdaInvocation() {
        withWorker(w -> w.doWork());
    }

    private void withWorker(Consumer<Worker> c) {
        Worker w = new Worker();
        c.accept(w);
    }

    private static class Worker {

        public Worker() {
            // pretend to do some stuff
            for (int i = 0; i < 10; i++) {
                Math.sqrt(Math.random());
            }
        }

        public void doWork() {
            // pretend to do some stuff
            for (int i = 0; i < 10; i++) {
                Math.sqrt(Math.random());
            }
        }
    }
}
