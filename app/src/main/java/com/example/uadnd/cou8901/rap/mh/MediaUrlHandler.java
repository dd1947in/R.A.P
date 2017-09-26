package com.example.uadnd.cou8901.rap.mh;

import android.net.Uri;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by dd2568 on 9/26/2017.
 */

public class MediaUrlHandler {
    public static final int EXOPAYER_MEDIA = 1;
    public static final int IMAGEVIEW_MEDIA = 2;
    public static final int BROWSER_MEDIA = 3;

    private static final HashSet<String>  exoPlayerHashSet = new HashSet<String>(Arrays.asList("3gpp", "3gp", "mpeg4", "mp4", "m4a", "ts",
            "flac", "mid", "xmf", "mmxmf", "rtttl", "rtx", "ota", "imy", "mp3", "mkv", "wav",  "ts", "webm"));

    private static final HashSet<String>  imageViewHashSet = new HashSet<String>(Arrays.asList("jpg", "jpeg", "png", "gif", "svg"));

    public static int getMediaPlayer(String url) {
        Uri uri = Uri.parse(url);
        String [] x = Uri.parse(url).getPath().split("\\.");
        String fileExtension = x[x.length-1].toLowerCase();

        if(exoPlayerHashSet.contains(fileExtension)) return EXOPAYER_MEDIA ;
        if(imageViewHashSet.contains(fileExtension)) return IMAGEVIEW_MEDIA ;
        return BROWSER_MEDIA ;
    }

}
