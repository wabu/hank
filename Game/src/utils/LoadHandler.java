package utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Vector;

import javax.imageio.ImageIO;

public class LoadHandler {
	public static int loadPics(String pfad,Vector<Image> pics){
		try {
			File directory = new File(pfad);
			
			for(String dateiname : directory.list()){
				
				File Bilddatei=new File(directory.getAbsoluteFile()+"/"+dateiname);
				URLConnection url = Bilddatei.getAbsoluteFile().toURI().toURL().openConnection();
				//falls die Datei, laut header kein Bild enthält skippe, 
				//und probiers mit der nächsten Datei im Ordner 
				if(!url.getContentType().contains("image"))continue;
				System.out.println(url.getContentType()+" "+Bilddatei.getAbsolutePath());	
					Image tmp = ImageIO.read(new File(pfad+dateiname));
					
					pics.add(tmp);
				
			}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return pics.size();
	}
	public static Image loadPic(String pfad){
		Vector<Image> pics = new Vector<Image>();
		loadPics(pfad, pics);
		return pics.get(0);
	}
}
