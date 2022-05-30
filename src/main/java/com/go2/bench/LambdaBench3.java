/*
 * Copyright (c) 2022 LogMeIn
 * All Rights Reserved Worldwide.
 *
 * THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO LOGMEIN
 * AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.go2.bench;

import java.util.UUID;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openjdk.jmh.annotations.Benchmark;

public class LambdaBench3 {

    @Benchmark
    public void noLambda() {
        WorkerWithoutLambda w = new WorkerWithoutLambda(UUID.randomUUID().toString());
        w.doWork();
    }

    @Benchmark
    public void withLambdaInvocation() {
        WorkerWithLambda w = new WorkerWithLambda(UUID.randomUUID().toString());
        w.doWork();
    }

    private static class WorkerWithoutLambda {

        String myPattern = ".*";
        String myValue;

        public WorkerWithoutLambda(String value) {
            this.myValue = value;
        }

        public boolean doWork() {
            return match(myValue).matches();
        }

        Matcher match(String str) {
            return Pattern.compile(myPattern).matcher(str);
        }
    }

    private static class WorkerWithLambda {

        String myPattern = ".*";
        String myValue;

        public WorkerWithLambda(String value) {
            this.myValue = value;
        }

        public boolean doWork() {
            return withPattern(p -> p.matcher(myValue).matches());
        }

        boolean withPattern(Function<Pattern, Boolean> fn) {
            return fn.apply(Pattern.compile(myPattern));
        }
    }
}
