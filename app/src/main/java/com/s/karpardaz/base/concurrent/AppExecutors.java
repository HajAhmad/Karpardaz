package com.s.karpardaz.base.concurrent;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

@Singleton
public class AppExecutors {

    private static final int THREAD_COUNT = 3;

    private final DiskIoThreadExecutor mDiskIo;
    private final ExecutorService mNetworkIo;
    private final MainThreadExecutor mMainThread;

    public AppExecutors() {
        mDiskIo = new DiskIoThreadExecutor();
        mNetworkIo = Executors.newFixedThreadPool(THREAD_COUNT);
        mMainThread = new MainThreadExecutor();
    }

    public Executor getDiskIo() {
        return mDiskIo;
    }

    public Executor getNetworkIo() {
        return mNetworkIo;
    }

    public Executor getMainThread() {
        return mMainThread;
    }

    public void cancelAll(){
        mNetworkIo.shutdownNow();
        mDiskIo.cancel();
        mMainThread.cancel();
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler;

        private MainThreadExecutor() {
            mainThreadHandler = new Handler(Looper.getMainLooper());
        }

        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }

        public void cancel(){
            mainThreadHandler.removeCallbacksAndMessages(null);
        }
    }

    private static class DiskIoThreadExecutor implements Executor {
        private final ExecutorService mDiskIo;

        private DiskIoThreadExecutor() {mDiskIo = Executors.newSingleThreadExecutor();}

        @Override
        public void execute(Runnable command) {
            mDiskIo.execute(command);
        }

        public void cancel(){
            mDiskIo.shutdownNow();
        }
    }


}
