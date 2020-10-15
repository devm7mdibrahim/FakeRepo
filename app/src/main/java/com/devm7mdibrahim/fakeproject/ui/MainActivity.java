package com.devm7mdibrahim.fakeproject.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.devm7mdibrahim.fakeproject.R;
import com.devm7mdibrahim.fakeproject.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private PostsViewModel postsViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initViewModel();
        getPosts();
    }

    private void initViewModel() {
        postsViewModel = new ViewModelProvider(this).get(PostsViewModel.class);
    }

    private void getPosts() {
        postsViewModel.getPosts()
                .observe(this, response -> {
                    switch (response.status) {
                        case LOADING: {
                            showProgress(View.VISIBLE);
                            showDataTV(View.GONE);
                            break;
                        }
                        case SUCCESS: {
                            showProgress(View.GONE);
                            showDataTV(View.VISIBLE);
                            displayData(response.data.get(0).getTitle());
                            break;
                        }
                        case ERROR: {
                            showProgress(View.GONE);
                            showDataTV(View.VISIBLE);
                            displayData(response.message);
                            break;
                        }
                    }
                });
    }


    private void showProgress(int visibility) {
        binding.progressBar.setVisibility(visibility);
    }


    private void showDataTV(int visibility) {
        binding.dataTv.setVisibility(visibility);
    }

    private void displayData(String data) {
        binding.dataTv.setText(data);
    }
}