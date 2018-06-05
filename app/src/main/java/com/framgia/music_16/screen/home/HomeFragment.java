package com.framgia.music_16.screen.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.framgia.music_16.R;
import com.framgia.music_16.screen.BaseFragment;
import com.framgia.music_16.screen.songgenres.SongGenresFragment;
import com.framgia.music_16.utils.Constant;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View mView;
    private ImageView mImageViewAlternativeRock;
    private ImageView mImageViewAmbient;
    private ImageView mImageViewClassical;
    private ImageView mImageViewCountry;
    private FragmentTransaction mTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(mView);
        return mView;
    }

    private void initViews(View view) {
        mImageViewAlternativeRock = view.findViewById(R.id.image_song_alternativerock);
        mImageViewAmbient = view.findViewById(R.id.image_song_ambient);
        mImageViewClassical = view.findViewById(R.id.image_song_classical);
        mImageViewCountry = view.findViewById(R.id.image_song_country);

        mImageViewAlternativeRock.setOnClickListener(this);
        mImageViewAmbient.setOnClickListener(this);
        mImageViewCountry.setOnClickListener(this);
        mImageViewClassical.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.image_song_alternativerock:
                fragment = SongGenresFragment.getGenreFragment(Constant.Genres.ALTERNATIVEROCK);
                break;
            case R.id.image_song_country:
                fragment = SongGenresFragment.getGenreFragment(Constant.Genres.COUNTRY);
                break;
            case R.id.image_song_ambient:
                fragment = SongGenresFragment.getGenreFragment(Constant.Genres.AMBIENT);
                break;
            case R.id.image_song_classical:
                fragment = SongGenresFragment.getGenreFragment(Constant.Genres.CLASSICAL);
                break;
        }
        mTransaction = getChildFragmentManager().beginTransaction();
        mTransaction.add(R.id.frame_content_home, fragment);
        mTransaction.addToBackStack(null);
        mTransaction.commit();
    }
}
