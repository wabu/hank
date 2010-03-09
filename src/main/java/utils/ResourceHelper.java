/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * class that helps with loading ressrouces
 * @author wabu
 */
public final class ResourceHelper {
    private static Logger log = LoggerFactory.getLogger(ResourceHelper.class);

    private ResourceHelper() {}

    /**
     * get the url for a ressource
     * @param name
     * @return
     * @throws IOException
     */
    public static URL loadRessouce(String name) throws IOException {
        log.debug("loading {}", name);
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL url = cl.getResource(name);
        if(url == null) {
            throw new IOException("can't find ressource "+name);
        }
        return url;
    }

    /**
     * load an image
     * @param name
     * @return
     * @throws IOException
     */
    public static Image loadImage(String name) throws IOException {
        return ImageIO.read(loadRessouce(name));
    }

    /**
     * load an image list by trying to extend pattern by String.format(pattern, i) with i=0...
     * @param pattern
     * @return
     * @throws IOException
     * @see String.format
     */
    public static List<Image> loadImageList(String pattern) throws IOException {
        ArrayList<Image> l = new ArrayList<Image>(2);
        log.debug("trying to load imagelist based on {}", pattern);
        for(int i=0;;i++) {
            String name = String.format(pattern, i);
            try {
                l.add(loadImage(name));
            } catch (IOException e) {
                if(i==0) {
                    throw e;
                } else {
                    break;
                }
            }
        }
        return l;
    }
}
