package com.ng.dobbypictures;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Loads the Images, either from cache or Web
 * Handles :
 *      1. Get Bitmap by Key
 *      2. Put Bitmap by Key
 *      3. Clear Cache function
 */
public class MemoryCache {
    private Map<String, SoftReference<Bitmap>> cache=Collections.synchronizedMap(new HashMap<String, SoftReference<Bitmap>>());

    public Bitmap get(String id){
        if(!cache.containsKey(id))
            return null;
        SoftReference<Bitmap> ref=cache.get(id);

        return ref.get();
    }

    /**
     * Put Bitmap by Key
     * @param id Key
     * @param bitmap Corresponding Bitmap for said Key
     */
    public void put(String id, Bitmap bitmap){
        cache.put(id, new SoftReference<Bitmap>(bitmap));
    }

    /**
     * Deletes all cache files to free up memory
     * Clean-Slate Protocol
     */
    public void clear() {
        cache.clear();
    }

}
