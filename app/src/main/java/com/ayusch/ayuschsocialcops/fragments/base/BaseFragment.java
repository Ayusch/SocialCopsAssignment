package com.ayusch.ayuschsocialcops.fragments.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import butterknife.Unbinder;

public class BaseFragment extends Fragment {

    public Context context;
    public Activity activity;
    public Unbinder unbinder;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        this.context = activity;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
