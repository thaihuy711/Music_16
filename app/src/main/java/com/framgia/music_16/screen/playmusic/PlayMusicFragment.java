package com.framgia.music_16.screen.playmusic;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.framgia.music_16.R;
import com.framgia.music_16.data.model.Song;
import com.framgia.music_16.screen.BaseFragment;
import com.framgia.music_16.screen.main.MainActivity;
import com.framgia.music_16.utils.Constant;
import java.util.ArrayList;
import java.util.List;

public class PlayMusicFragment extends BaseFragment implements View.OnClickListener {

    private static final String ARGUMENT_SONGS = "ARGUMENT_SONGS";

    private View mView;
    private ImageView mImageBack;

    public static PlayMusicFragment newInstance(List<Song> songs, int position) {
        PlayMusicFragment fragment = new PlayMusicFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARGUMENT_SONGS, (ArrayList<? extends Parcelable>) songs);
        args.putInt(Constant.ARGUMENT_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_play_song, container, false);
        initViews(mView);
        ((MainActivity) getActivity()).setNavigationVisibility(false);
        return mView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_button_back:
                getActivity().onBackPressed();
                break;
        }
    }

    private void initViews(View view) {
        mImageBack = view.findViewById(R.id.image_button_back);
        mImageBack.setOnClickListener(this);
    }
}
