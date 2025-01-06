package org.example;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Observable.just(1, 2, 3)
                .map( x -> x*10)
                .subscribe(System.out::println);
        Observable.just(11, 12, 13)
                .map( x -> {
                    if (x == 12) throw new IllegalStateException();
                    else return x;
                })
                .subscribe(
                        integer -> System.out.println("onNext: " + integer),
                        throwable -> System.out.println("onError: " + throwable),
                        () -> System.out.println("onComplete")
                );
        //create(), 프로그래머가 직접 onNext, onComplete, onError를 호출해야함
        Observable.create(emitter -> {
            emitter.onNext(10);
            emitter.onNext(20);
            emitter.onNext(30);
            emitter.onComplete();
        }).subscribe(System.out::println);

        //fromXXX(), 특정 타입의 데이터를 Observable로 바꿈
        //fromArray: array를 Observable로 바꿈
        //fromIterable: Iterable을 구현한 class를 Observable로 바꿈
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(100);
        arrayList.add(200);
        arrayList.add(300);
        Observable.fromIterable(arrayList)
                .subscribe(System.out::println);

        //Single: Observable의 변형된 형태, 한 개의 데이터 혹은 에러만을 발행
        Single.just(1)
                .subscribe(System.out::println);

        //Observable을 Single로 변환 가능, 다만 발행을 하나의 데이터만을 해야함
        Observable<Integer> observable = Observable.just(1);
        Single.fromObservable(observable)
                .subscribe(System.out::println);
        Observable.just(2)
                .single(0) //default value
                .subscribe(System.out::println);

        //Maybe: 최대 데이터 하나만을 발행, 여러 개가 와도 처음 온 하나만을 발행하고 종료
        Maybe.create(emitter -> {
            emitter.onSuccess("Maybe 1");
            emitter.onSuccess("Maybe 2");
            emitter.onComplete();
        }).subscribe(integer -> System.out.println("onNext: " + integer),
                throwable -> System.out.println("onError:" + throwable),
                () -> System.out.println("onComplete"));

        //Completable: 데이터 발행의 완료/에러 신호만 보내는 특수한 형태
        Completable completed = Completable.create(emitter -> emitter.onComplete());
        completed.subscribe(() -> System.out.println("onComplete"),
                throwable -> System.out.println("onError"));


    }
}