package de.eydamos.backpack.item;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.helper.GuiHelper;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.Localizations;
import de.eydamos.backpack.tier.TierFrame;
import de.eydamos.backpack.tier.TierLeather;
import de.eydamos.backpack.util.GeneralUtil;
import de.eydamos.backpack.util.NBTItemStackUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemBackpack extends Item {
    public ItemBackpack() {
        setMaxStackSize(1);
        setHasSubtypes(true);
        setCreativeTab(Constants.tabBackpacks);
        setRegistryName(Constants.DOMAIN);
        setUnlocalizedName(Constants.DOMAIN);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subItems) {
        for (EBackpack backpack : EBackpack.values()) {
            subItems.add(new ItemStack(item, 1, backpack.getDamage()));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        String name = super.getUnlocalizedName(itemStack);

        name += '_' + EBackpack.getIdentifierByDamage(itemStack.getItemDamage());

        return name;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean advanced) {
        TierFrame.addTooltip(itemStack, tooltip);
        TierLeather.addTooltip(itemStack, tooltip);

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            String label = ChatFormatting.BLUE + I18n.format(Localizations.TOOLTIP_SLOTS_USED);
            String value = ChatFormatting.YELLOW.toString();
            value += BackpackHelper.getSlotsUsed(itemStack);
            value += " / ";
            value += BackpackHelper.getSlots(itemStack);
            value += ChatFormatting.RESET;
            tooltip.add(label.trim() + ' ' + value);
        } else {
            tooltip.add(I18n.format(Localizations.TOOLTIP_MORE_INFORMATION));
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand) {
        if (!NBTItemStackUtil.hasTag(itemStack, Constants.NBT.UID)) {
            // TODO if OP show gui to configure settings
            // TODO else show warning that data is missing and item should be handed to OP
            return new ActionResult(EnumActionResult.PASS, itemStack);
        }

        if (!GeneralUtil.isServerSide(world)) {
            // display rename GUI if player is sneaking
            if (player.isSneaking()) {
                GuiHelper.displayRenameGui(player);
            }

            return new ActionResult(EnumActionResult.PASS, itemStack);
        }

        // when the player is not sneaking
        if (!player.isSneaking()) {
            GuiHelper.displayBackpack(player);
        }

        return new ActionResult(EnumActionResult.PASS, itemStack);
    }
}
