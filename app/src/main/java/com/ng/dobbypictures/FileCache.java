package com.ng.dobbypictures;

import android.content.Context;

import java.io.File;


/**
 * FileCache class handles the creation, access, delete request for the image cache files
 */
public class FileCache {

    private File cacheDir;

    /**
     * FileCache class handles the creation, access, delete request for the image cache files
     * @param context Application Context passed on for File Manipulation
     */
    public FileCache(Context context){
        //Find the dir to save cached images
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            cacheDir=new File(android.os.Environment.getExternalStorageDirectory(),"Slideshow_Images_Aditya_Dobby");
        else
            cacheDir=context.getCacheDir();
        if(!cacheDir.exists())
            cacheDir.mkdirs();
    }

    /**
     * getFile Returns the cache file
     * @param url location of said cache file
     * @return the Image Cache File
     */
    public File getFile(String url){
        String filename=String.valueOf(url.hashCode()) + ".jpg";
        File f = new File(cacheDir, filename);
        return f;

    }

    /**
     * Deletes all cache files to free up memory
     * Clean-Slate Protocol
     */
    public void clear(){
        File[] files=cacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }

}

