package com.android.mazhengyang.minichat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;

import com.android.mazhengyang.minichat.R;
import com.android.mazhengyang.minichat.util.ImageLoaderUtils;
import com.android.mazhengyang.minichat.util.daynightmodeutils.ChangeModeController;
import com.android.mazhengyang.minichat.util.daynightmodeutils.ChangeModeHelper;
import com.android.mazhengyang.minichat.widget.WaveView;
import com.suke.widget.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mazhengyang on 18-12-25.
 */

public class MeFragment extends Fragment {

    private static final String TAG = "MiniChat." + MeFragment.class.getSimpleName();

    @BindView(R.id.wave_view)
    WaveView waveView;
    @BindView(R.id.img_logo)
    ImageView ivLogo;
    @BindView(R.id.daynight_switch_button)
    SwitchButton switchButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_me, null);
        ButterKnife.bind(this, view);

        Context context = getContext();

        ImageLoaderUtils.displayRound(getContext(),ivLogo,R.drawable.github);
       // ivLogo.setImageResource(R.drawable.github);
        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2, -2);
        lp.gravity = Gravity.CENTER;
        waveView.setOnWaveAnimationListener(new WaveView.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                lp.setMargins(0, 0, 0, (int) y + 2);
                ivLogo.setLayoutParams(lp);
            }
        });

        if (ChangeModeHelper.getChangeMode(context) == ChangeModeHelper.MODE_NIGHT) {
            switchButton.setChecked(true);
        } else {
            switchButton.setChecked(false);
        }
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                ChangeModeController.toggleThemeSetting(getActivity());
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView: ");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

}