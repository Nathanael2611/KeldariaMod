/**
 * Copyright 2019-2021 Keldaria. Tous droits réservés.
 * Toute reproduction, diffusion, partage, distribution,
 * commercialisation sans autorisation explicite est interdite.
 */
package fr.reden.guiapi.gui;

import fr.reden.guiapi.component.panel.GuiScrollPane;
import fr.reden.guiapi.gui.list.GuiSlotList;
import fr.reden.guiapi.gui.list.slot.GuiBasicSlot;
import fr.reden.guiapi.gui.list.slot.GuiSlot;

import java.util.ArrayList;
import java.util.List;

public class GuiList extends GuiScrollPane {
	
	protected int selectedEntryId = -1;
	protected GuiSlotList slotList;
	protected List<String> entries = new ArrayList<String>();
	
	public GuiList(List<String> entries, int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
		if(entries != null) {
			this.entries.addAll(entries);
		}
		
		slotList = new GuiSlotList(this);
		slotList.updateSlotList();
		add(slotList);
	}
	
	public GuiSlot getSlotInstance(int n, String entryName) {
		return new GuiBasicSlot(this, n, entryName);
	}
	
	public void addEntry(String entry) {
		entries.add(entry);
		slotList.updateSlotList();
	}
	
	public GuiList setEntries(List<String> entries)
	{
		this.entries.clear();
		
		if(entries != null) {
			this.entries.addAll(entries);
		}
		
		slotList.updateSlotList();
		return this;
	}
	
	public GuiList removeEntry(String entry) {
		if(entry != null && entries.contains(entry)) {
			entries.remove(entry);
			slotList.updateSlotList();
		}
		return this;
	}
	
	public List<String> getEntries() {
		return entries;
	}
	
	public int getSelectedEntryId() {
		return selectedEntryId;
	}
	
	public GuiList setSelectedEntryId(int selectedEntryId) {
		this.selectedEntryId = selectedEntryId;
		return this;
	}
	
	public void updateFocus(int n) {
		slotList.updateFocus(n);
	}
	
	public int getListPaddingTop() {
		return 25;
	}
	
	public int getListPaddingBottom() {
		return 25;
	}
	
	public GuiSlotList getSlotList() {
		return slotList;
	}
}
