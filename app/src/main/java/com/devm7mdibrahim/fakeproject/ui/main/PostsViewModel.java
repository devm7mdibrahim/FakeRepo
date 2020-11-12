package com.devm7mdibrahim.fakeproject.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.devm7mdibrahim.fakeproject.data.model.PostsResponse;
import com.devm7mdibrahim.fakeproject.utils.Resource;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class PostsViewModel extends ViewModel {

    // composite disposal
    private final PostsRepository postsRepository;
    private final CompositeDisposable compositeDisposable;


    public PostsViewModel(){
        compositeDisposable = new CompositeDisposable();
        postsRepository = new PostsRepository();
    }


    public LiveData<Resource<List<PostsResponse>>> getPosts(){
        return postsRepository.getPosts(compositeDisposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
        compositeDisposable.dispose();
    }
}
