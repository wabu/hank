package de.javauni.softspiele;

import java.awt.Image;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import utils.ResourceHelper;
import static org.junit.Assert.*;


/**
 * A simple junit 4 test
 */
public class ResourceHelperTest {
    @Test
    public void testLoadImage() throws IOException {
        assertNotNull("loaded an image is not null",
                ResourceHelper.loadImage("Pics/Gras/gras1.JPG"));
    }

    @Test(expected=IOException.class)
    public void testLoadImageFail() throws IOException {
        ResourceHelper.loadImage("not a file");
    }

    @Test
    public void testLoadImageList() throws IOException {
        List<Image> ls = ResourceHelper.loadImageList("Pics/Player/mario%02d.jpg");

        assertTrue("some pictures to animate hank are loaded", ls.size() == 2);
        assertNotNull("image is not null", ls.get(0));
    }
}
