package de.eydamos.backpack.network.message;

import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.helper.GuiHelper;
import de.eydamos.backpack.misc.Constants;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageOpenGui implements IMessage, IMessageHandler<MessageOpenGui, IMessage> {
    private byte guiToOpen;

    public MessageOpenGui() {
    }

    public MessageOpenGui(byte toOpen) {
        guiToOpen = toOpen;
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        guiToOpen = buffer.readByte();
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeByte(guiToOpen);
    }

    @Override
    public IMessage onMessage(MessageOpenGui message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        switch (message.guiToOpen) {
            case Constants.Guis.SPECIAL_SLOTS:
                GuiHelper.displaySpecialSlots(player);
                break;
            case Constants.Guis.CARRIED_BACKPACK:
                GuiHelper.displayCarriedBackpack(player);
                return new MessageBackpackData(BackpackHelper.getBackpackFromPlayer(player, false));
        }

        return null;
    }
}
