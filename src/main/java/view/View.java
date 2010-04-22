package view;

import view.StateListener;

public class View implements StateListener<MainState>{
	@Override
	public void onStateChange(MainState state) {
		switch (state) {
		case main:
			
		case level:
			//1. Rufe Entitys vom Level ab
			//2. Baue dementsprechend Renderer, mit der RendererFactory
			//3. speicher diese in einer RendererMap
			//4. Setze ViewPort
			//5. Starte RendererThread
			break;

		default:
			break;
		}	
	}
}
