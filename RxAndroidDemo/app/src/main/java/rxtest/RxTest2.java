package rxtest;


import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by Jessica on 2018/5/30.
 */

public class RxTest2 {
    private static final String TAG = "RxTest2";
    List<String> list;

    public RxTest2() {
        list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        list.add("!");
    }

    public void rxDemo0_List1() {
//        将list通过一次性发出给订阅者
        Flowable.just(list).subscribe(strings -> {
            for (String str : strings) {
                Log.d(TAG, "rxDemo0_List1: " + str);
            }
        });
    }

    public void rxDemo1_List2() {
//        将list通过fromIterable一个一个发出
        Flowable.fromIterable(list)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d(TAG, "rxDemo0_List1: " + s);
                    }
                });
    }

    public void rxDemo2_List3() {
        // 一次性发出list然后通过flatmap方法将list通过fromIterable一个一个发出
        Flowable.just(list).flatMap(new Function<List<String>, Publisher<?>>() {
            @Override
            public Publisher<?> apply(List<String> strings) throws Exception {
                return Flowable.fromIterable(strings);
            }
        }).subscribe(str -> {
            Log.d(TAG, "rxDemo2_List3: " + str);
        });
    }


    public void rxDemo3_filter() {
        Flowable.fromIterable(list)
                .doOnNext(s -> Log.d(TAG, "doOnNext: " + s))//doOnNext 允许我们在每次输出一个元素之前做一些额外的事情。
                .take(1)// 表示订阅者最多收到几个数据
                .filter(s -> s.contains("o"))// 通过filter过滤器进行数据过滤:filter 是用于过滤数据的，返回false表示拦截此数据。
                .subscribe(s -> Log.d(TAG, "subscribe: " + s));
    }

}
