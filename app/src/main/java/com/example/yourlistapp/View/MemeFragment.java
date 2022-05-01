package com.example.yourlistapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.yourlistapp.R;
import com.example.yourlistapp.ViewModel.MemeViewModel;

import org.w3c.dom.Text;

public class MemeFragment extends Fragment {

    private ImageView imageView;
    private MemeViewModel viewModel;
    private TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstace){
        super.onCreate(savedInstace);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){

        View view = inflater.inflate(R.layout.fragment_random, container, false);
        imageView = view.findViewById(R.id.image_view_meme);
        textView = view.findViewById(R.id.text_view_meme);
        viewModel = new ViewModelProvider(getActivity()).get(MemeViewModel.class);
        viewModel.searchForMeme();
        viewModel.getSearchedMeme().observe(getViewLifecycleOwner(),meme -> {
                    textView.setText(meme.getAuthor());
                    Glide.with(getActivity())
                            .load(meme.getUrl()).into(imageView);
                });

        return view;

    }
}
