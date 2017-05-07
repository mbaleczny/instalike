package pl.mbaleczny.instalike.util;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;

public class Bus<T> {

    private Subject<T> subject = BehaviorSubject.create();

    public void post(T item) {
        subject.onNext(item);
    }

    public void subscribe(Consumer<T> consumer) {
        subject.subscribe(consumer);
    }

    public void clear() {
        subject.onComplete();
        subject = BehaviorSubject.create();
    }
}
