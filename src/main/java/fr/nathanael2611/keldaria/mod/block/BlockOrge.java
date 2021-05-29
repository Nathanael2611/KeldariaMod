package fr.nathanael2611.keldaria.mod.block;

import fr.nathanael2611.keldaria.mod.registry.KeldariaItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class BlockOrge extends BlockCrops {

    @Override
    protected Item getSeed() {
        return KeldariaItems.ORGE_SEEDS;
    }

    @Override
    protected Item getCrop() {
        return KeldariaItems.ORGE;
    }
}
