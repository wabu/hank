package utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Vector;

import javax.imageio.ImageIO;

public class LoadHandler {
	public static int loadPics(final String pfad, final Vector<Image> pics) {
		try {
			final File directory = new File(pfad);

			for (final String dateiname : directory.list()) {

				final File Bilddatei = new File(directory.getAbsoluteFile()
						+ "/" + dateiname);
				final URLConnection url = Bilddatei.getAbsoluteFile().toURI()
						.toURL().openConnection();
				// falls die Datei, laut header kein Bild enthält skippe,
				// und probiers mit der nächsten Datei im Ordner
				if (!url.getContentType().contains("image")) {
					continue;
				}
				System.out.println(url.getContentType() + " "
						+ Bilddatei.getAbsolutePath());
				final Image tmp = ImageIO.read(new File(pfad + dateiname));

				pics.add(tmp);

			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return pics.size();
	}

	public static Image loadPic(final String pfad) {
		final Vector<Image> pics = new Vector<Image>();
		loadPics(pfad, pics);
		return pics.get(0);
	}
}
