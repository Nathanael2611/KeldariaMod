/**
 * Copyright 2019-2021 Keldaria. Tous droits réservés.
 * Toute reproduction, diffusion, partage, distribution,
 * commercialisation sans autorisation explicite est interdite.
 */
package fr.reden.guiapi.event.listeners.mouse;

public interface IMouseExtraClickListener {
	
	void onMouseDoubleClicked(int mouseX, int mouseY, int mouseButton);
	void onMousePressed(int mouseX, int mouseY, int mouseButton);
	void onMouseReleased(int mouseX, int mouseY, int mouseButton);
	
}
