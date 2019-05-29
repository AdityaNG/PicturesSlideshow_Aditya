package com.ng.dobbypictures;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Utility for Copy Stream
 * Necessary for the image downloading in ImageLoader Class
 */
public class Utils {

    /**
     * Stream Copy mechanism
     * @param is Input Stream - (From Web)
     * @param os Output Stream - (To Disk)
     */
    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int bufferSize=1024;
        try
        {
            byte[] bytes=new byte[bufferSize];
            for(;;)
            {
                int count=is.read(bytes, 0, bufferSize);
                if(count==-1)
                    break;
                os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
}