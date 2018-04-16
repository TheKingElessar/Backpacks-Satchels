package de.eydamos.backpack.network;

import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.network.message.MessageBackpackData;
import de.eydamos.backpack.network.message.MessageOpenGui;
import de.eydamos.backpack.network.message.MessageRenameBackpack;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class PacketHandlerBackpack {
    private final SimpleNetworkWrapper networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(Constants.MOD_ID);
    private IMessage message;

    public void initialise() {
        // these packages are send from the client to the server
        networkWrapper.registerMessage(MessageRenameBackpack.class, MessageRenameBackpack.class, 0, Side.SERVER);
        networkWrapper.registerMessage(MessageOpenGui.class, MessageOpenGui.class, 1, Side.SERVER);

        // these packages are send from the server to the client
        networkWrapper.registerMessage(MessageBackpackData.class, MessageBackpackData.class, 10, Side.CLIENT);
    }

    @SideOnly(Side.CLIENT)
    public void renameBackpack(String name) {
        message = new MessageRenameBackpack(name);
        // send new name to server
        networkWrapper.sendToServer(message);
        // save the name on client
        ((MessageRenameBackpack) message).setName(Minecraft.getMinecraft().player, name);
    }

    @SideOnly(Side.CLIENT)
    public void sendOpenGui(byte gui) {
        message = new MessageOpenGui(gui);
        networkWrapper.sendToServer(message);
    }

    public void propagateCarriedBackpack(EntityPlayer player) {
        message = new MessageBackpackData(player, BackpackHelper.getBackpackFromPlayer(player, false));
        networkWrapper.sendToAll(message);
    }

    @SideOnly(Side.SERVER)
    public void sendCarriedBackpacks(EntityPlayerMP targetPlayer, List<EntityPlayerMP> players) {
        for (EntityPlayer player : players) {
            if (player.equals(targetPlayer)) {
                continue;
            }
            message = new MessageBackpackData(player, BackpackHelper.getBackpackFromPlayer(player, false));
            networkWrapper.sendTo(message, targetPlayer);
        }
    }
}
