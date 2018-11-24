package de.eydamos.backpack.item;

import com.mojang.realmsclient.gui.ChatFormatting;
import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.data.BackpackSave;
import de.eydamos.backpack.helper.BackpackHelper;
import de.eydamos.backpack.helper.GuiHelper;
import de.eydamos.backpack.init.BackpackItems;
import de.eydamos.backpack.init.Configurations;
import de.eydamos.backpack.misc.Constants;
import de.eydamos.backpack.misc.Localizations;
import de.eydamos.backpack.tier.TierFrame;
import de.eydamos.backpack.tier.TierLeather;
import de.eydamos.backpack.util.GeneralUtil;
import de.eydamos.backpack.util.NBTItemStackUtil;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ItemBackpack extends Item {
    public ItemBackpack() {
        setMaxStackSize(1);
        setHasSubtypes(true);
        setCreativeTab(Constants.tabBackpacks);
        setRegistryName("backpack");
        setUnlocalizedName("backpack");
    }

    @Override
    @SideOnly(Side.CLIENT)
    @ParametersAreNonnullByDefault
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (!isInCreativeTab(tab)) {
            return;
        }

        for (EBackpack backpack : EBackpack.values()) {
            if (backpack.isEnabled()) {
                subItems.add(new ItemStack(BackpackItems.backpack, 1, backpack.getDamage()));
            }
        }
    }

    @Override
    @MethodsReturnNonnullByDefault
    public String getUnlocalizedName(ItemStack itemStack) {
        String name = super.getUnlocalizedName(itemStack);

        name += '_' + EBackpack.getIdentifierByDamage(itemStack.getItemDamage());

        return name;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(
        ItemStack itemStack,
        @Nullable World world,
        List<String> tooltip,
        ITooltipFlag advanced
    ) {
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
    @ParametersAreNonnullByDefault
    @MethodsReturnNonnullByDefault
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemStack = player.getHeldItem(hand);

        if (!NBTItemStackUtil.hasTag(itemStack, Constants.NBT.UID)) {
            // other idea send package to server to open gui. Server checks if op and opens gui or sends back chat message

            if (player.capabilities.isCreativeMode) {
                // TODO if OP show gui to configure settings
                // TODO else show warning that data is missing and item should be handed to OP
            }
            return new ActionResult<>(EnumActionResult.PASS, itemStack);
        }

        if (!GeneralUtil.isServerSide(world)) {
            // display rename GUI if player is sneaking
            if (player.isSneaking()) {
                GuiHelper.displayRenameGui(player);
            }

            return new ActionResult<>(EnumActionResult.PASS, itemStack);
        }

        // when the player is not sneaking
        if (!player.isSneaking() && !Configurations.OPEN_ONLY_EQUIPPED_BACKPACK) {
            GuiHelper.displayBackpack(player);
        }

        return new ActionResult<>(EnumActionResult.PASS, itemStack);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new ICapabilityProvider() {

            @Override
            public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
                return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && !stack.isEmpty();
            }

            @Override
            public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
                if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && !stack.isEmpty()) {
                    return (T) new InvWrapper(BackpackSave.loadBackpack(Backpack.proxy.getWorldForMapStorage(), stack, null, false));
                }
                return null;
            }
        };
    }
}
