package com.devm7mdibrahim.fakeproject.data.remote;

import com.devm7mdibrahim.fakeproject.data.model.PostsResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface PostService {
    @GET("posts")
    Single<List<PostsResponse>> getPosts();
}