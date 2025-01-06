package org.example;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ": Hello world!");

        //Cold Observable: Observer가 subscribe를 호출할 때까지 데이터 발행하지 않음,원하는 시점에 데이터를 요청하고 처음부터 끝까지 결과를 받아옴
        //Hot Observable: Observer의 존재 여부와 관계없이 데이터를 발행, Observer는 Observable을 구독한 시점부터 발행된 데이터를 받을 수 있음

        //Scheduler: rxJava의 코드가 어느 스레드에서 실행될 것인지 지정하는 역할, 스레드 관리자
        //subscribeOn: Observable이 데이터 흐름을 발생시키고 연산하는 스레드를 지정, 여러 번 호출되더라도 맨 처음의 호출만 영향을 주며 어디에 위치하든 상광 없음
        //observeOn: Observable이 Observer에게 알림을 보내는 스레드 지정, 여러 번 호출될 수 있으며 이후에 실행되는 연산에 영향을 주므로 위치가 중요
        //Single Thread Scheduler: 단일 스레드를 계속 재사용
        String[] source = {"First", "Second", "Third"};
        Observable.fromArray(source)
                .observeOn(Schedulers.single())
                .subscribe(data -> {
                    System.out.println("Observe on: " + Thread.currentThread().getName() + " | " + "value: " + data);
                });
        Thread.sleep(100);

        //Computation Thread Scheduler: CPU에 대응하는 계산용 스케쥴러, CPU 코어 수 만큼 스레드 생성됨
        String[] strs = {"1", "3", "5", "7", "9"};

        Observable.fromArray(strs)
                .map(str -> "!!" + str + "!!")
                .observeOn(Schedulers.computation())
                .subscribe(data -> System.out.println(Thread.currentThread() + ": " + data));
        Thread.sleep(100);

        //IO Thread Scheduler: 파일 입출력등 IO 작업을 하거나 네트워크 요청 처리 시에 사용하는 스케쥴러,
        // Computation과 달리 필요할 때마다 스레드 생성
        String path = "C:\\";
        File[] files = new File(path).listFiles();

        Observable.fromArray(files)
                .map(File::getAbsolutePath)
                .observeOn(Schedulers.io())
                .subscribe(data -> System.out.println(Thread.currentThread() + ": " + data));
        Thread.sleep(100);

        //Trampoline Scheduler: 새로운 스레드를 생성하지 않고 사용하고 있는 스레드에 무한한 크기의 대기 큐를 생성
        Observable.fromArray(strs)
                .map(str -> "!!" + str + "!!")
                .observeOn(Schedulers.trampoline())
                .subscribe(data -> System.out.println(Thread.currentThread() + ": " + data));
        Thread.sleep(100);

        //NewTread Scheduler: 요청을 받을 때마다 매번 새로운 스레드 생성
        Observable.fromArray(source)
                .observeOn(Schedulers.newThread())
                .subscribe( data -> {
                    System.out.println("Observe On : "+Thread.currentThread().getName()+" | "+"value : "+data);
                });
        Thread.sleep(100);

        ArrayList<MyFigure> figures = new ArrayList<>();
        figures.add(new MyFigure("red", "ball"));
        figures.add(new MyFigure("blue", "ball"));
        figures.add(new MyFigure("green", "ball"));

        Observable.fromIterable(figures)
                .subscribeOn(Schedulers.computation()) // (A)
                .subscribeOn(Schedulers.io()) // (B)
                .doOnSubscribe(data -> MyUtil.printData("doOnSubscribe")) // 현재 스레드(main)에서 Observable을 구독
                .doOnNext(data -> MyUtil.printData("doOnNext", data)) // (A)에 의해 computation scheduler에서 데이터 흐름 발생
                .observeOn(Schedulers.newThread()) // (C)
                .map(data -> { // (C)에 의해 map 연산이 new thread에서 실행
                    data.setShape("cube");
                    return data;
                })
                .doOnNext(data -> MyUtil.printData("doOnNext", data))
                .observeOn(Schedulers.newThread()) // (D)
                .map(data -> { // (D)에 의해 map 연산이 new thread에서 실행
                    data.setShape("tetrahedron");
                    return data;
                })
                .doOnNext(data -> MyUtil.printData("doOnNext", data))
                .observeOn(Schedulers.newThread()) // (E)
                .subscribe(data -> MyUtil.printData("subscribe", data)); // (E)에 의해 new thread에서 데이터 소비(subscribe)
        Thread.sleep(100);
    }
}