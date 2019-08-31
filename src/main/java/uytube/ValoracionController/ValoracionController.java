package uytube.ValoracionController;

import uytube.models.ValoracionVideo;
import uytube.models.manager.Manager;

public class ValoracionController implements IValoracion {
	private Manager mana;
    private Object session;

	public ValoracionController() {
		mana = Manager.getInstance();
	}
	
	public void valorarVideo(ValoracionVideo vv) {
		mana.startTransaction("ValoracionVideo", vv);
		mana.closeSession();
	}
}
