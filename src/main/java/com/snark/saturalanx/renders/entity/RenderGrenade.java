package com.snark.saturalanx.renders.entity;

import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.entities.EntityPotGrenade;
import com.snark.saturalanx.items.weapons.gunpowder.PotGrenadeItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderGrenade extends Render {

    public void render(EntityPotGrenade entity, double x, double y, double z, float entityYaw, float partialTicks) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(1.0F, 1.0F, 1.0F);
        this.bindEntityTexture(entity);
        switch (entity.getType()){
            case 1:
            default:
                this.renderItem(Tessellator.instance, ((PotGrenadeItem)ItemSetup.potGrenade).getLitIcon());
                break;
            case 2:
                this.renderItem(Tessellator.instance, ((PotGrenadeItem)ItemSetup.potGrenadeQuick).getLitIcon());
                break;
            case 3:
                this.renderItem(Tessellator.instance, ((PotGrenadeItem)ItemSetup.potGrenadeLong).getLitIcon());
                break;
        }
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    private void renderItem(Tessellator p_77026_1_, IIcon p_77026_2_) {
        float f = p_77026_2_.getMinU();
        float f1 = p_77026_2_.getMaxU();
        float f2 = p_77026_2_.getMinV();
        float f3 = p_77026_2_.getMaxV();
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(0.6F,0.6F,0.6F);
        p_77026_1_.startDrawingQuads();
        p_77026_1_.setNormal(0.0F, 1.0F, 0.0F);
        p_77026_1_.addVertexWithUV((double) (0.0F - f5), (double) (0.0F - f6), 0.0D, (double) f, (double) f3);
        p_77026_1_.addVertexWithUV((double) (f4 - f5), (double) (0.0F - f6), 0.0D, (double) f1, (double) f3);
        p_77026_1_.addVertexWithUV((double) (f4 - f5), (double) (f4 - f6), 0.0D, (double) f1, (double) f2);
        p_77026_1_.addVertexWithUV((double) (0.0F - f5), (double) (f4 - f6), 0.0D, (double) f, (double) f2);
        p_77026_1_.draw();
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.render((EntityPotGrenade) entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TextureMap.locationItemsTexture;
    }

}
