package de.eydamos.backpack.model;

import de.eydamos.backpack.Backpack;
import de.eydamos.backpack.misc.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class LayerBackpack implements LayerRenderer<AbstractClientPlayer> {
    public static final ModelBiped model = new ModelBackpack();

    private final RenderPlayer renderPlayer;

    public LayerBackpack(RenderPlayer renderPlayer) {
        this.renderPlayer = renderPlayer;
    }

    @Override
    public void doRenderLayer(
        AbstractClientPlayer player,
        float p_177141_2_,
        float p_177141_3_,
        float partialTicks,
        float p_177141_5_,
        float p_177141_6_,
        float p_177141_7_,
        float scale
    ) {
        ItemStack backpack = Backpack.proxy.getClientBackpack(player);
        if (backpack == null) {
            return;
        }

        if (!backpack.isEmpty()) {
            GL11.glPushMatrix();

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

            ModelRenderer bipedBody = renderPlayer.getMainModel().bipedBody;
            bipedBody.postRender(0.0625F);

            if (player.isSneaking()) {
                GL11.glTranslatef(0.0F, 0.18F, -0.1F);
            }

            Minecraft.getMinecraft().renderEngine.bindTexture(Constants.modelTexture);

            model.render(player, 0F, 0F, 0F, 0F, 0F, 0.0625F);

            GL11.glPopMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
