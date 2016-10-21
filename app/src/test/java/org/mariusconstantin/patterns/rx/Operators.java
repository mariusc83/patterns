package org.mariusconstantin.patterns.rx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mariusconstantin.patterns.BuildConfig;
import org.mockito.InOrder;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import rx.Observable;
import rx.Observer;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.functions.Func3;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by MConstantin on 10/5/2016.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class Operators {

    // The Map Operator
    // http://reactivex.io/documentation/operators/map.html
    @Test
    public void testMap() {
        @SuppressWarnings("unchecked")
        Observer<String> observer = mock(Observer.class);
        Observable<String> observable = Observable.just("Top", "Bottom");

        Observable<String> m = observable.map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "Corner";
            }
        });
        m.subscribe(observer);

        verify(observer, never()).onError(any(Throwable.class));
        verify(observer, times(1)).onNext("TopCorner");
        verify(observer, times(1)).onNext("BottomCorner");
        verify(observer, times(1)).onCompleted();
    }

    // the Flat Map Operator
    // http://reactivex.io/documentation/operators/flatmap.html
    @Test
    public void testFlatMapMaxConcurrent() {
        Observable<Integer> source = Observable
                .range(1, 10)
                .flatMap(new Func1<Integer, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Integer t1) {
                        return Observable.range(t1 * 10, 2);
                    }
                }).subscribeOn(Schedulers.computation());

        TestSubscriber<Integer> ts = new TestSubscriber<>();

        source.subscribe(ts);

        ts.awaitTerminalEvent();
        ts.assertNoErrors();
        Set<Integer> expected = new HashSet<Integer>(Arrays.asList(
                10, 11, 20, 21, 30, 31, 40, 41, 50, 51, 60, 61, 70, 71, 80, 81, 90, 91, 100, 101
        ));
        Assert.assertEquals(expected.size(), ts.getOnNextEvents().size());
        Assert.assertTrue(expected.containsAll(ts.getOnNextEvents()));
    }

    // the Combine Latest Operator
    // http://reactivex.io/documentation/operators/combinelatest.html

    @Test
    public void testCombineLatestDifferentLengthObservableSequences() {
        @SuppressWarnings("unchecked")
        // mock calls don't do generic
                Observer<String> w = mock(Observer.class);

        PublishSubject<String> w1 = PublishSubject.create();
        PublishSubject<String> w2 = PublishSubject.create();
        PublishSubject<String> w3 = PublishSubject.create();

        Observable<String> combineLatestW = Observable.combineLatest(w1, w2, w3, getConcat3StringsCombineLatestFunction());
        combineLatestW.subscribe(w);

        /* simulate sending data */

        // once for w1
        w1.onNext("1a"); // waits for w2
        w2.onNext("2a"); // waits for w3
        w3.onNext("3a"); // return 1a2a3a

        w1.onCompleted(); // keeps 1a

        // twice for w2
        w2.onNext("2b"); // returns 1a2b3a
        w2.onCompleted(); // keeps 2b

        // 4 times for w3
        w3.onNext("3b"); // returns 1a2b3b
        w3.onNext("3c"); // returns 1a2b3c
        w3.onNext("3d"); // returns 1a2b3d
        w3.onCompleted();

        /* we should have been called 5 times on the Observer */

        InOrder inOrder = inOrder(w);
        inOrder.verify(w).onNext("1a2a3a");
        inOrder.verify(w).onNext("1a2b3a");
        inOrder.verify(w).onNext("1a2b3b");
        inOrder.verify(w).onNext("1a2b3c");
        inOrder.verify(w).onNext("1a2b3d");
        inOrder.verify(w, never()).onNext(anyString());
        inOrder.verify(w, times(1)).onCompleted();
    }

    private Func3<String, String, String, String> getConcat3StringsCombineLatestFunction() {
        return new Func3<String, String, String, String>() {

            @Override
            public String call(String a1, String a2, String a3) {
                return a1 + a2 + a3;
            }

        };
    }

    private static <T> Observable<T> compose(Observable<T> source) {
        return source.doOnSubscribe(new Action0() {
            @Override
            public void call() {
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
            }
        });
    }


}
