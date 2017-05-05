package pl.mbaleczny.instalike.util.scheduler;

import io.reactivex.Scheduler;

public interface BaseSchedulerProvider {

    Scheduler io();

    Scheduler ui();

    Scheduler computation();
}
