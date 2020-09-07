package com.isoldier.reactivex;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscription;

/**
 * java 异步实现的一种方式 基于观察者模式
 * 参考文档： https://maxwell-nc.github.io/android/rxjava2-1.html
 *
 * @author jinmeng on 2018/7/26.
 * @version 1.0
 */
public class ReactiveDemo {

    public static void main(String[] args){

        observableDemo();

//        flowableDemo();

    }

    public static void observableDemo(){

        //观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //订阅时候的操作，无需request
                System.out.println("call onSubscribe");
            }
            @Override
            public void onNext(String s) {
                //onNext事件处理
                System.out.println("observer: "+s);
            }
            @Override
            public void onError(Throwable e) {
                //onError事件处理
                System.out.println("call onError");
            }
            @Override
            public void onComplete() {
                //onComplete事件处理
                System.out.println("call onComplete");
            }
        };

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                //订阅观察者时的操作
                e.onNext("test1");
                e.onNext("test2");
                e.onComplete();
            }
        });//没有背压设置

        observable.subscribe(observer);

    }

    public static void flowableDemo(){

        //相当于观察者
        FlowableSubscriber<String> subscriber = new FlowableSubscriber<String>() {

            @Override
            public void onSubscribe(Subscription s) {
                //订阅时候的操作
                //请求多少事件，这里表示不限制
                s.request(Long.MAX_VALUE);
                System.out.println("call onSubscribe");
            }
            @Override
            public void onNext(String s) {
                //onNext事件处理
                System.out.println("tag: "+s);
            }
            @Override
            public void onError(Throwable t) {
                //onError事件处理
                System.out.println("call onError");
            }
            @Override
            public void onComplete() {
                //onComplete事件处理
                System.out.println("call onComplete");
            }
        };

        //相当于被观察者
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {
                //订阅观察者时的操作
                e.onNext("test1");
                e.onNext("test2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        flowable.subscribe(subscriber);

    }



}
