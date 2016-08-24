package de.eydamos.backpack.network.message;

import de.eydamos.backpack.helper.BackpackHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageRenameBackpack implements IMessage, IMessageHandler<MessageRenameBackpack, IMessage> {
    protected String name;

    public MessageRenameBackpack() {
    }

    public MessageRenameBackpack(String name) {
        this.name = name;
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        name = ByteBufUtils.readUTF8String(buffer);
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        ByteBufUtils.writeUTF8String(buffer, name);
    }

    @Override
    public IMessage onMessage(MessageRenameBackpack message, MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            EntityPlayerMP entityPlayer = ctx.getServerHandler().playerEntity;
            setName(entityPlayer, message.name);
        }

        return null;
    }

    public void setName(EntityPlayer entityPlayer, String name) {
        if (entityPlayer.getCurrentEquippedItem() != null) {
            ItemStack itemStack = entityPlayer.getCurrentEquippedItem();

            if (BackpackHelper.isBackpack(itemStack)) {
                itemStack.setStackDisplayName(EnumChatFormatting.RESET + name);
            }
        }
    }
}
