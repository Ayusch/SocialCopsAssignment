package com.ayusch.ayuschsocialcops.mvpcontract;

public interface VideoPlayerContract {

    interface View {
        void initV();

        void playVideo();

        void noPermissionError();
    }

    interface Presenter {
        void checkPermission();

        void initP();
    }

}
