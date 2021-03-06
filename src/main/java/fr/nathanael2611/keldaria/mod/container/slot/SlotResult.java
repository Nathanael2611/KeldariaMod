/**
 * Copyright 2019-2021 Keldaria. Tous droits réservés.
 * Toute reproduction, diffusion, partage, distribution,
 * commercialisation sans autorisation explicite est interdite.
 */
package fr.nathanael2611.keldaria.mod.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotResult extends Slot {

        public SlotResult(IInventory inventory, int id, int x, int y)
        {
            super(inventory, id, x, y);
        }

        @Override
        public boolean isItemValid(ItemStack stack) //Interdit la pose d'items dans le slot
        {
            return false;
        }

        public ItemStack decrStackSize(int amount)
        {
            return super.decrStackSize(amount);
        }

        public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
        {
            super.onCrafting(stack);
            super.onTake(player, stack);
        }
}

