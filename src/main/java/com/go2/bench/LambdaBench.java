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

public class LambdaBench {

    @Benchmark
    public void noLambda() {
        Worker w = new Worker();
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
        public void doWork() {

        }
    }
}
