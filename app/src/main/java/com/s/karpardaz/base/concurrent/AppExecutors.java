package com.s.karpardaz.base.concurrent;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private static final int THREAD_COUNT = 3;

    private final Executor mDiskIo;
    private final Executor mNetworkIo;
    private final Executor mMainThread;

    AppExecutors(Executor diskIo, Executor networkIo, Executor mainThread) {
        this.mDiskIo = diskIo;
        this.mNetworkIo = networkIo;
        this.mMainThread = mainThread;
    }

    public AppExecutors() {
        this(new DiskIoThreadExecutor(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new MainThreadExecutor());
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

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler;

        private MainThreadExecutor() {
            mainThreadHandler = new Handler(Looper.getMainLooper());
        }

        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    }

    private static class DiskIoThreadExecutor implements Executor {

        private final Executor mDiskIo;

        private DiskIoThreadExecutor() {mDiskIo = Executors.newSingleThreadExecutor();}

        @Override
        public void execute(Runnable command) {
            mDiskIo.execute(command);
        }
    }


}
