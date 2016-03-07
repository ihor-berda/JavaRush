package com.javarush.test.level29.lesson09.bonus01;

import java.util.concurrent.*;

/* Argument and Value are generic types*/
public class CacheComputeManager<Argument, Value> implements Computable<Argument, Value> {
    private final ConcurrentHashMap<Argument, Future<Value>> cache = new ConcurrentHashMap<>();
    private final Computable<Argument, Value> computable;

    public CacheComputeManager(Computable<Argument, Value> computable) {
        this.computable = computable;
    }

    @Override
    public Value compute(final Argument arg) throws InterruptedException {
        Future<Value> f = cache.get(arg);
        if (f == null) {
            FutureTask<Value> ft = createFutureTaskForNewArgumentThatHaveToComputeValue(arg);
            cache.putIfAbsent(arg, ft);
            f = ft;
            ft.run();
            System.out.print(arg + " will be cached  ");
        } else {
            System.out.print(arg + " taken from cache  ");
        }
        try {
            return f.get();
        } catch (CancellationException e) {
            cache.remove(arg, f);
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
        return null;
    }

    public FutureTask<Value> createFutureTaskForNewArgumentThatHaveToComputeValue(final Argument arg) {

        return new FutureTask<Value>(new Callable<Value>()
        {
            @Override
            public Value call() throws Exception
            {
                return computable.compute(arg);
            }
        });
    }
}
