/**
 * Copyright 2019-2021 Keldaria. Tous droits réservés.
 * Toute reproduction, diffusion, partage, distribution,
 * commercialisation sans autorisation explicite est interdite.
 */
package fr.nathanael2611.keldaria.mod.command;

import fr.nathanael2611.keldaria.mod.server.chat.SoundResistance;
import net.minecraft.command.CommandException;

public class CommandReloadSoundResistance extends KeldariaCommand
{
    public CommandReloadSoundResistance()
    {
        super("reloadsoundresistance", "/reloadsoundresistance", createAliases());
    }

    @Override
    public void run(CommandUser user, String[] args) throws CommandException
    {
        SoundResistance.init();
        user.sendMessage("Successfully reloaded SoundResistance");
    }
}
