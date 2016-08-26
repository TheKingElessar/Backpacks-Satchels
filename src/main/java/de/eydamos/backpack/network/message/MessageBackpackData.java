package de.eydamos.backpack.network.message;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.misc.BackpackItems;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.util.NBTItemStackUtil;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageBackpackData implements IMessage, IMessageHandler<MessageBackpackData, IMessage> {
    private int slots;
    private int slotsPerRow;
    private String customName;

    public MessageBackpackData() {}

    public MessageBackpackData(ItemStack itemStack) {
        slots = BackpackHelper.getSlots(itemStack);
        slotsPerRow = BackpackHelper.getSlotsPerRow(itemStack);
        if (itemStack.hasDisplayName()) {
            customName = itemStack.getDisplayName();
        } else {
            customName = "";
        }
    }

    @Override
    public void fromBytes(ByteBuf buffer) {
        slots = buffer.readInt();
        slotsPerRow = buffer.readInt();
        customName = ByteBufUtils.readUTF8String(buffer);
    }

    @Override
    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(slots);
        buffer.writeInt(slotsPerRow);
        ByteBufUtils.writeUTF8String(buffer, customName);
    }

    @Override
    public IMessage onMessage(MessageBackpackData message, MessageContext ctx) {
        ItemStack backpack = new ItemStack(BackpackItems.backpack);
        NBTItemStackUtil.setInteger(backpack, Constants.NBT.SLOTS, message.slots);
        NBTItemStackUtil.setInteger(backpack, Constants.NBT.SLOTS_PER_ROW, message.slotsPerRow);
        if (!message.customName.isEmpty()) {
            backpack.setStackDisplayName(message.customName);
        }

        Backpack.proxy.setClientBackpack(backpack);

        return null;
    }
}
