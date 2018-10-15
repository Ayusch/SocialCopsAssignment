package com.ayusch.ayuschsocialcops.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;

import com.ayusch.ayuschsocialcops.Utils.WidgetUtils;
import com.ayusch.ayuschsocialcops.mvpcontract.VideoPlayerContract;

import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;

public class VideoPlayerPresenter implements VideoPlayerContract.Presenter {
    private VideoPlayerContract.View view;
    private Activity activity;

    public VideoPlayerPresenter(VideoPlayerContract.View v, Activity act) {
        view = v;
        activity = act;
    }

    @Override
    public void checkPermission() {
        if (Nammu.checkPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            view.playVideo();
        } else {
            askPermission();
        }
    }

    @Override
    public void initP() {
        view.initV();
    }

    private void askPermission() {

        if (Nammu.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //user denied permission once, show the reason why permission is needed
            WidgetUtils.displayPermissionAlert(activity, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Nammu.askForPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionCallback() {
                        @Override
                        public void permissionGranted() {
                            view.playVideo();
                        }

                        @Override
                        public void permissionRefused() {
                            view.noPermissionError();
                        }
                    });
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    System.exit(0);
                }
            }, false);
        } else {
            //first time asking for permission
            Nammu.askForPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionCallback() {
                @Override
                public void permissionGranted() {
                    view.playVideo();
                }

                @Override
                public void permissionRefused() {
                    WidgetUtils.displayPermissionAlert(activity, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Nammu.askForPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionCallback() {
                                @Override
                                public void permissionGranted() {
                                    view.playVideo();
                                }

                                @Override
                                public void permissionRefused() {
                                    view.noPermissionError();
                                }
                            });
                        }
                    }, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            System.exit(0);
                        }
                    }, false);
                }
            });
        }
    }


}
