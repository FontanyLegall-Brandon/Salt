package com.example.utilisateur.projetl3.utils;

import android.os.Handler;
import android.widget.ImageView;

import com.example.utilisateur.projetl3.R;

/**
 * Created by Utilisateur on 01/03/2018.
 */

public class LoadingScreen {

    private ImageView loading;

    LoadingScreen(ImageView loading) {
        this.loading = loading;
    }


    //Pour l'instant le loading ne s'arrete jamais, il faudra faire en sorte qu'il s'arréte quand on le souhaite. (stopLoadScreen())
    public void setLoadScreen(){
        //TMP : On pourra changer le loading et ajouter des images, c'est juste pour le test pour le moment.
        final Integer[] loadingImages = {R.mipmap.loading_frame_1, R.mipmap.loading_frame_2, R.mipmap.loading_frame_3};
        final Handler loadingHandler = new Handler();
        Runnable runnable = new Runnable() {
            int loadingImgIndex = 0;
            public void run() {
                loading.setImageResource(loadingImages[loadingImgIndex]);
                loadingImgIndex++;
                if (loadingImgIndex >= loadingImages.length)
                    loadingImgIndex = 0;
                loadingHandler.postDelayed(this, 500);
            }
        };
        loadingHandler.postDelayed(runnable, 500);
    }
}

