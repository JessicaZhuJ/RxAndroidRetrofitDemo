package rxtest;

import android.util.Log;
import android.widget.Toast;

import com.jing.rxandroiddemo.view.activity.LoginActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * Created by Jessica on 2018/5/30.
 */

public class RxTest1 {
    private static final String TAG = "RxTest1";

    Flowable<String> flowable;
    Subscriber subscriber;

    public void rxDemo() {

        flowable = Flowable.create(emitter -> {
            // 执行异步操作然后提交数据到观察者
            emitter.onNext("Hello World!");
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
                System.out.println("onSubscribe");
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

    public void rxDemo1() {
        Flowable.just("hello world").subscribe(s -> {
            Log.d(TAG, "rxDemo1: s=" + s);
        });
    }

}
