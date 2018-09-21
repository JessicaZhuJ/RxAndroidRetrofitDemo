package rxtest;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jessica on 2018/5/30.
 */

public class RxTest3 {
    private static final String TAG = "RxTest3";

    public RxTest3() {
    }

    Flowable<String> flowable;
    Subscriber subscriber;

    /**
     * * onComplete 和 onError 方法用来通知订阅者，数据发送完成和出现错误
     * 1.只要发生错误，onError()一定会被调用。
     * 这极大的简化了错误处理。只需要在一个地方处理错误即可以。
     * 2.操作符不需要处理异常。
     * 将异常处理交给订阅者来做，一旦有调用链中有一个抛出了异常，就会直接执行onError()方法，停止数据传送。
     * 3.你能够知道什么时候订阅者已经接收了全部的数据
     */
    public void rxDemo() {

        flowable = Flowable.create(emitter -> {
            // 执行异步操作然后提交数据到观察者
            emitter.onNext("Hello World!");
            //!   emitter.onNext("error:" + (1 / 0));
            emitter.onComplete();// 成功完成数据提交
            // emitter.onError();// 发生错误中断数据提交
        }, BackpressureStrategy.BUFFER);

        // 观察者，接收提交的数据
        subscriber = new Subscriber<String>() {
            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage(), e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: 完成数据提交");
            }

            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe: ");
                // 调用request，否则不能完成执行
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: s=" + s);
            }
        };

    }

    public void rxDemo0_Subscribe() {
        // 注册观察者，即可接收数据
        flowable.subscribe(subscriber);
    }


    /**
     * 使用 subscribeOn(Schedulers.io()) 指定了发送数据是在io线程(某个子线程)，
     * 然后调用 observeOn(AndroidSchedulers.mainThread()) 指定订阅者在主线程执行
     */
    public void rxDemo1(Context context) {
        Flowable.create((FlowableOnSubscribe<String>) emitter -> {
            emitter.onNext("hold on");
            Thread.sleep(2000);
            emitter.onNext("hello world");
            emitter.onComplete();
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Toast.makeText(context, s, Toast.LENGTH_SHORT).show());
    }
}
