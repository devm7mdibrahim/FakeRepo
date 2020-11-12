package com.devm7mdibrahim.fakeproject.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.devm7mdibrahim.fakeproject.data.model.PostsResponse;
import com.devm7mdibrahim.fakeproject.data.remote.PostsClient;
import com.devm7mdibrahim.fakeproject.utils.Resource;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PostsRepository {

    private final MutableLiveData<Resource<List<PostsResponse>>> postsLiveData = new MutableLiveData<>();

    public LiveData<Resource<List<PostsResponse>>> getPosts(CompositeDisposable compositeDisposable){
        postsLiveData.setValue(Resource.loading());

        compositeDisposable.add(PostsClient.getInstance()
                .getPosts()
                //send request in io thread = background thread
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<PostsResponse>>() {
                    @Override
                    public void onSuccess(@NonNull List<PostsResponse> postsResponse) {
                        postsLiveData.postValue(Resource.success(postsResponse));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        postsLiveData.postValue(Resource.error(e.getMessage()));
                    }
                }));

        return postsLiveData;
    }

    public void addPost(){

    }

    public void removePost(){

    }

    public void editPost(){

    }
}
