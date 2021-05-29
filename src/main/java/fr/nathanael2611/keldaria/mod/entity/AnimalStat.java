package fr.nathanael2611.keldaria.mod.entity;

import fr.nathanael2611.keldaria.mod.util.Helpers;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class AnimalStat implements INBTSerializable<NBTTagCompound>
{

    private double size;
    private double speed;
    private double resistance;

    public AnimalStat(double size, double speed, double resistance)
    {
        this.size = size;
        this.speed = speed;
        this.resistance = resistance;
    }

    public AnimalStat(NBTTagCompound compound)
    {
        this.deserializeNBT(compound);
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setDouble("Size", this.size);
        compound.setDouble("Speed", this.speed);
        compound.setDouble("Resistance", this.resistance);
        return compound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        this.size = nbt.getDouble("Size");
        this.speed = nbt.getDouble("Speed");
        this.resistance = nbt.getDouble("Resistance");
    }

    public double getSize()
    {
        return size;
    }

    public double getSpeed()
    {
        return speed;
    }

    public double getResistance()
    {
        return resistance;
    }

    public double getSize(long bornTime, long growTime)
    {

        long diff = (System.currentTimeMillis() - bornTime);
        double percent = Math.min(Helpers.getPercent(diff, growTime), 100);

        return this.size * (percent * 0.01);
    }
}
