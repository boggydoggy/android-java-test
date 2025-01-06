package org.example;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");

        //Backpressure: 데이터 생산과 소비가 불균형적일 때 일어나는 현상, Observable이 데이터를 발행하는 속도를 Observer의 소비 속도가 따라잡지 못할 때
        //Flowable: 데이터가 일정량 누적되면 데이터를 더이상 발행하지 않음, 128개의 버퍼 존재
        Flowable.range(1, 10000)
                .doOnNext(data -> System.out.println("Emit Data: " + data))
                .observeOn(Schedulers.io())
                .subscribe(data -> {
                    System.out.println("Consume Data: " + data);
                    Thread.sleep(100);
                });
        Thread.sleep(10000);
    }
}