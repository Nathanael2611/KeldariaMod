/**
 * Copyright 2019-2021 Keldaria. Tous droits réservés.
 * Toute reproduction, diffusion, partage, distribution,
 * commercialisation sans autorisation explicite est interdite.
 */
package fr.reden.guiapi.component;

import fr.reden.guiapi.component.panel.GuiFrame;
import fr.reden.guiapi.event.listeners.IResizableButtonListener;
import fr.reden.guiapi.event.listeners.mouse.IMouseMoveListener;
import net.minecraft.util.math.MathHelper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiResizableButton extends GuiButton implements IMouseMoveListener {
	
	private final List<IResizableButtonListener> resizableButtonListeners = new ArrayList<IResizableButtonListener>();
	
	public enum ENUM_RESIZE_SIDE { LEFT, RIGHT, TOP, BOTTOM }
	
	protected boolean leftInnerResizable = true;
	protected boolean leftOutsideResizable = true;
	protected boolean rightInnerResizable = true;
	protected boolean rightOutsideResizable = true;
	protected boolean topInnerResizable = true;
	protected boolean topOutsideResizable = true;
	protected boolean bottomInnerResizable = true;
	protected boolean bottomOutsideResizable = true;
	
	protected int resizeBorderSize = 1;
	
	protected boolean leftHovered;
	protected boolean rightHovered;
	protected boolean topHovered;
	protected boolean bottomHovered;
	
	protected int resizeBorderColor = Color.LIGHT_GRAY.getRGB();
	
	protected int lastWidth, lastHeight;
	protected int lastX, lastY;
	protected int minWidth = 4, minHeight = 4, maxWidth = Integer.MAX_VALUE, maxHeight = Integer.MAX_VALUE;
	
	public GuiResizableButton(int x, int y, int width, int height) {
		this(x, y, width, height, "", true);
	}
	
	public GuiResizableButton(int x, int y, int width, int height, String text) {
		this(x, y, width, height, text, true);
	}
	
	public GuiResizableButton(int x, int y, int width, int height, String text, boolean background) {
		super(x, y, width, height, text, background);
		addMoveListener(this);
	}
	
	public GuiResizableButton addResizableButtonListener(IResizableButtonListener resizableButtonListener) {
		resizableButtonListeners.add(resizableButtonListener);
		return this;
	}
	
	@Override
	@Deprecated
	public GuiResizableButton setRelativeWidth(float relativeWidth) {
		return this;
	}
	
	@Override
	@Deprecated
	public GuiResizableButton setRelativeHeight(float relativeHeight) {
		return this;
	}
	
	@Override
	@Deprecated
	public GuiResizableButton setRelativeX(float relativeX) {
		return this;
	}
	
	@Override
	@Deprecated
	public GuiResizableButton setRelativeY(float relativeY) {
		return this;
	}
	
	@Override
	public void drawForeground(int mouseX, int mouseY, float partialTicks) {
		super.drawForeground(mouseX, mouseY, partialTicks);
		
		if(isLeftHovered()) {
			drawRect(getScreenX(), getScreenY(), getScreenX() + getResizeBorderSize(), getScreenY() + getHeight(), getResizeBorderColor());
		} else if(isRightHovered()) {
			drawRect(getScreenX() + getWidth() - getResizeBorderSize(), getScreenY(), getScreenX() + getWidth(), getScreenY() + getHeight(), getResizeBorderColor());
		} else if(isTopHovered()) {
			drawRect(getScreenX(), getScreenY(), getScreenX() + getWidth(), getScreenY() + getResizeBorderSize(), getResizeBorderColor());
		} else if(isBottomHovered()) {
			drawRect(getScreenX(), getScreenY() + getHeight() - getResizeBorderSize(), getScreenX() + getWidth(), getScreenY() + getHeight(), getResizeBorderColor());
		}
	}
	
	@Override
	public void onMouseClicked(int mouseX, int mouseY, int mouseButton)
	{
		super.onMouseClicked(mouseX, mouseY, mouseButton);
		lastWidth = getWidth();
		lastHeight = getHeight();
		lastX = getX();
		lastY = getY();
	}
	
	@Override
	public void onMouseMoved(int mouseX, int mouseY)
	{
		boolean leftHovered = mouseX >= getScreenX() && mouseX <= getScreenX() + getResizeBorderSize();
		boolean rightHovered = mouseX >= getScreenX() + getWidth() - getResizeBorderSize() && mouseX <= getScreenX() + getWidth();
		boolean topHovered = mouseY >= getScreenY() && mouseY <= getScreenY() + getResizeBorderSize();
		boolean bottomHovered = mouseY >= getScreenY() + getHeight() - getResizeBorderSize() && mouseY <= getScreenY() + getHeight();
		
		if(!isPressed()) {
			setLeftHovered(isHovered() && (isLeftOutsideResizable() || isLeftInnerResizable()) && leftHovered);
			setRightHovered(isHovered() && (isRightOutsideResizable() || isRightInnerResizable()) && rightHovered);
			setTopHovered(isHovered() && (isTopOutsideResizable() || isTopInnerResizable()) && topHovered);
			setBottomHovered(isHovered() && (isBottomOutsideResizable() || isBottomInnerResizable()) && bottomHovered);
		} else {
			
			int newWidth = MathHelper.clamp(getLastWidth() + (mouseX - GuiFrame.lastPressedX) * (isRightHovered() ? 1 : -1), getMinWidth(), getMaxWidth());
			int newHeight = MathHelper.clamp(getLastHeight() + (mouseY - GuiFrame.lastPressedY) * (isBottomHovered() ? 1 : -1), getMinHeight(), getMaxHeight());
			
			float wDelta = getWidth() - newWidth;
			float hDelta = getHeight() - newHeight;
			
			boolean leftFlag = isLeftHovered() && ((isLeftInnerResizable() && wDelta > 0) || (isLeftOutsideResizable() && wDelta < 0));
			boolean rightFlag = isRightHovered() && ((isRightInnerResizable() && wDelta > 0) || (isRightOutsideResizable() && wDelta < 0));
			boolean topFlag = isTopHovered() && ((isTopInnerResizable() && hDelta > 0) || (isTopOutsideResizable() && hDelta < 0));
			boolean bottomFlag = isBottomHovered() && ((isBottomInnerResizable() && hDelta > 0) || (isBottomOutsideResizable() && hDelta < 0));
			
			if(leftFlag || rightFlag) {
				setWidth(newWidth);
				
				if(leftFlag)
					setX(getLastX() + (getLastWidth() - getWidth()));
			}
			
			if(topFlag || bottomFlag) {
				setHeight(newHeight);
				
				if(topFlag)
					setY(getLastY() + (getLastHeight() - getHeight()));
			}
			
			for(IResizableButtonListener resizableButtonListener : resizableButtonListeners) {
				if(isLeftHovered())
					resizableButtonListener.onButtonUpdated(ENUM_RESIZE_SIDE.LEFT);
				else if(isRightHovered())
					resizableButtonListener.onButtonUpdated(ENUM_RESIZE_SIDE.RIGHT);
				else if(isTopHovered())
					resizableButtonListener.onButtonUpdated(ENUM_RESIZE_SIDE.TOP);
				else if(isBottomHovered())
					resizableButtonListener.onButtonUpdated(ENUM_RESIZE_SIDE.BOTTOM);
			}
		}
	}
	
	public int getLastWidth() {
		return lastWidth;
	}
	
	public int getLastHeight() {
		return lastHeight;
	}
	
	public int getLastX() {
		return lastX;
	}
	
	public int getLastY() {
		return lastY;
	}
	
	@Override public void onMouseHover(int mouseX, int mouseY) {}
	
	@Override public void onMouseUnhover(int mouseX, int mouseY) {}
	
	public boolean isLeftInnerResizable() {
		return leftInnerResizable;
	}
	
	public GuiResizableButton setLeftInnerResizable(boolean leftInnerResizable) {
		this.leftInnerResizable = leftInnerResizable;
		return this;
	}
	
	public boolean isLeftOutsideResizable() {
		return leftOutsideResizable;
	}
	
	public GuiResizableButton setLeftOutsideResizable(boolean leftOutsideResizable) {
		this.leftOutsideResizable = leftOutsideResizable;
		return this;
	}
	
	public boolean isRightInnerResizable() {
		return rightInnerResizable;
	}
	
	public GuiResizableButton setRightInnerResizable(boolean rightInnerResizable) {
		this.rightInnerResizable = rightInnerResizable;
		return this;
	}
	
	public boolean isRightOutsideResizable() {
		return rightOutsideResizable;
	}
	
	public GuiResizableButton setRightOutsideResizable(boolean rightOutsideResizable) {
		this.rightOutsideResizable = rightOutsideResizable;
		return this;
	}
	
	public boolean isTopInnerResizable() {
		return topInnerResizable;
	}
	
	public GuiResizableButton setTopInnerResizable(boolean topInnerResizable) {
		this.topInnerResizable = topInnerResizable;
		return this;
	}
	
	public boolean isTopOutsideResizable() {
		return topOutsideResizable;
	}
	
	public GuiResizableButton setTopOutsideResizable(boolean topOutsideResizable) {
		this.topOutsideResizable = topOutsideResizable;
		return this;
	}
	
	public boolean isBottomInnerResizable() {
		return bottomInnerResizable;
	}
	
	public GuiResizableButton setBottomInnerResizable(boolean bottomInnerResizable) {
		this.bottomInnerResizable = bottomInnerResizable;
		return this;
	}
	
	public boolean isBottomOutsideResizable() {
		return bottomOutsideResizable;
	}
	
	public GuiResizableButton setBottomOutsideResizable(boolean bottomOutsideResizable) {
		this.bottomOutsideResizable = bottomOutsideResizable;
		return this;
	}
	
	public int getResizeBorderSize() {
		return resizeBorderSize;
	}
	
	public GuiResizableButton setResizeBorderSize(int resizeBorderSize) {
		this.resizeBorderSize = resizeBorderSize;
		return this;
	}
	
	public boolean isLeftHovered() {
		return leftHovered;
	}
	
	public GuiResizableButton setLeftHovered(boolean leftHovered) {
		this.leftHovered = leftHovered;
		return this;
	}
	
	public boolean isRightHovered() {
		return rightHovered;
	}
	
	public GuiResizableButton setRightHovered(boolean rightHovered) {
		this.rightHovered = rightHovered;
		return this;
	}
	
	public boolean isTopHovered() {
		return topHovered;
	}
	
	public GuiResizableButton setTopHovered(boolean topHovered) {
		this.topHovered = topHovered;
		return this;
	}
	
	public boolean isBottomHovered() {
		return bottomHovered;
	}
	
	public GuiResizableButton setBottomHovered(boolean bottomHovered) {
		this.bottomHovered = bottomHovered;
		return this;
	}
	
	public int getResizeBorderColor() {
		return resizeBorderColor;
	}
	
	public GuiResizableButton setResizeBorderColor(int resizeBorderColor) {
		this.resizeBorderColor = resizeBorderColor;
		return this;
	}
	
	public int getMinWidth() {
		return minWidth;
	}
	
	public GuiResizableButton setMinWidth(int minWidth) {
		this.minWidth = minWidth;
		return this;
	}
	
	public int getMinHeight() {
		return minHeight;
	}
	
	public GuiResizableButton setMinHeight(int minHeight) {
		this.minHeight = minHeight;
		return this;
	}
	
	public int getMaxWidth() {
		return maxWidth;
	}
	
	public GuiResizableButton setMaxWidth(int maxWidth) {
		this.maxWidth = maxWidth;
		return this;
	}
	
	public int getMaxHeight() {
		return maxHeight;
	}
	
	public GuiResizableButton setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
		return this;
	}
	
}
