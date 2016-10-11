package org.mariusconstantin.patterns.rx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mariusconstantin.patterns.BuildConfig;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.functions.Func1;
import rx.observables.BlockingObservable;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by MConstantin on 10/5/2016.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = 19, constants = BuildConfig.class)
public class Observables {

    // Simple Observable creation where we provide the Producer. You should not use this inside
    // your code. Please use the predefined methods.
    @Test
    public void testCreateWithSubscriber() {

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("one");
                subscriber.onNext("two");
                subscriber.onNext("three");
                subscriber.onCompleted();
            }

        });

        @SuppressWarnings("unchecked")
        Observer<String> observer = mock(Observer.class);
        observable.subscribe(observer);
        verify(observer, times(1)).onNext("one");
        verify(observer, times(1)).onNext("two");
        verify(observer, times(1)).onNext("three");
        verify(observer, never()).onError(any(Throwable.class));
        verify(observer, times(1)).onCompleted();
    }

    // subscribe to a hot observable
    @Test
    @SuppressWarnings("unchecked")
    public void testObservableFromArray() {
        String[] items = new String[]{"one", "two", "three"};
        final Observable<String> observable = Observable.from(items);

        final Observer<String> observer1 = mock(Observer.class);
        final Observer<String> observer2 = mock(Observer.class);
        observable.subscribe(observer1);

        verify(observer1, times(1)).onNext("one");
        verify(observer1, times(1)).onNext("two");
        verify(observer1, times(1)).onNext("three");
        verify(observer1, never()).onError(any(Throwable.class));
        verify(observer1, times(1)).onCompleted();

        observable.subscribe(observer2);

        verify(observer2, times(1)).onNext("one");
        verify(observer2, times(1)).onNext("two");
        verify(observer2, times(1)).onNext("three");
        verify(observer2, never()).onError(any(Throwable.class));
        verify(observer2, times(1)).onCompleted();
    }

    @Test
    public void testObservableFromArray_runningOnADifferentThread() {
        final String[] items = new String[]{"one", "two", "three"};
        final CountDownLatch countDownLatch = new CountDownLatch(6);
        final Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println(Thread.currentThread().getName());
                subscriber.onNext(items[0]);
                subscriber.onNext(items[1]);
                subscriber.onNext(items[2]);
                subscriber.onCompleted();
            }
        }).observeOn(Schedulers.computation())
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        System.out.println(Thread.currentThread().getName());
                        countDownLatch.countDown();
                        return s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread());


        final String[] receivedItems = new String[3];
        final Observer<String> observer = new Observer<String>() {
            int currentIndex = 0;

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(Thread.currentThread().getName());
                System.out.println(s);
                receivedItems[currentIndex++] = s;
                countDownLatch.countDown();
            }
        };
        observable.subscribe(observer);

        try {
            countDownLatch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            fail(e.getMessage());
        }

        assertThat(receivedItems).isEqualTo(items);
    }


    @Test
    public void testObservableFromArray_runningOnADifferentThread_byBlockingIt() {
        String[] items = new String[]{"one", "two", "three"};
        final BlockingObservable<String> observable = Observable
                .from(items)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread()).toBlocking();

        final String[] receivedItems = new String[3];
        final Observer<String> observer = new Observer<String>() {
            int currentIndex = 0;

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
                receivedItems[currentIndex++] = s;
            }
        };
        observable.subscribe(observer);
        assertThat(receivedItems).isEqualTo(items);
    }

    // BehaviorSubject which can be in the same time an Observable and an Observer. It re - emits
    // the last emitted item to any subscriber
    @Test
    public void testThatObserverReceivesDefaultValueAndSubsequentEvents() {
        BehaviorSubject<String> subject = BehaviorSubject.create("default");

        @SuppressWarnings("unchecked")
        Observer<String> observer1 = mock(Observer.class);
        @SuppressWarnings("unchecked")
        Observer<String> observer2 = mock(Observer.class);
        subject.subscribe(observer1);

        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");
        subject.subscribe(observer2);

        verify(observer1, times(1)).onNext("default");
        verify(observer1, times(1)).onNext("one");
        verify(observer1, times(1)).onNext("two");
        verify(observer1, times(1)).onNext("three");
        verifyNoMoreInteractions(observer1);
        verify(observer2, times(1)).onNext("three");
        verifyNoMoreInteractions(observer2);
    }

    // when throwing an exception from a simple Observable and throwing it further from the onError
/*
    @Test
    public void testOnErrorExceptionIsThrownFromSingleSubscribe() {
        @SuppressWarnings("unchecked")
        final Observer<Integer> observer = mock(Observer.class);
        Single.create(new Single.OnSubscribe<Integer>() {
                          @Override
                          public void call(SingleSubscriber<? super Integer> s1) {
                              throw new IllegalArgumentException("original exception");
                          }
                      }
        ).subscribe(observer);

        verify(observer, times(1)).onError(any(IllegalArgumentException.class));
        verifyNoMoreInteractions(observer);
    }
*/


}
