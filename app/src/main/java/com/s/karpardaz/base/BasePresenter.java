package com.s.karpardaz.base;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.s.karpardaz.KarpardazApplication;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static java.util.Objects.requireNonNull;

public class BasePresenter<V> implements BasePresenterContract<V> {

    private static final String TAG = BasePresenter.class.getSimpleName();

    private V mView;

    private final CompositeDisposable mDisposable;

    @Inject
    public BasePresenter() {
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void takeView(@NonNull V view) {
        mView = requireNonNull(view);
    }

    @Override
    public void dropView() {
        mDisposable.clear();
        mView = null;
    }

    protected <T> void addFlowable(Flowable<T> flowable, Consumer<T> onSuccess) {
        mDisposable.add(flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess));
    }

    protected <T> void addFlowable(Flowable<T> flowable, Consumer<T> onSuccess,
            Consumer<Throwable> onError) {
        mDisposable.add(flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError));
    }

    protected <T> void addSingle(Single<T> single, Consumer<T> onSuccess) {
        mDisposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess));
    }

    protected <T> void addSingle(Single<T> single, Consumer<T> onSuccess,
            Consumer<Throwable> onError) {
        mDisposable.add(single.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError));
    }


    protected <T> void addMaybe(Maybe<T> maybe, Consumer<T> onSuccess, Action onComplete) {
        mDisposable.add(maybe.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, this::logThrowable, onComplete));
    }

    private void logThrowable(Throwable t) {
        Log.e(TAG, "addMaybe: ", t);
    }

    protected boolean isViewAttached() {
        return mView != null;
    }

    protected V getView() {
        if (!isViewAttached()) throw new ViewNotAttachedException();

        return mView;
    }

    protected String getString(@StringRes int resId) {
        return KarpardazApplication.getInstance().getString(resId);
    }

    private static class ViewNotAttachedException extends RuntimeException {
        ViewNotAttachedException() {
            super("you should call Presenter.attachView(view) before" +
                    " requesting data to the presenter.");
        }
    }
}
