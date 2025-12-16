package com.snark.saturalanx.renders.item;

import com.snark.saturalanx.core.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class SpearThrowerRender implements IItemRenderer {
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.EQUIPPED;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if(type == ItemRenderType.EQUIPPED) {
            GL11.glPushMatrix();

            EntityLivingBase entity = (EntityLivingBase) data[1];
            IIcon iicon = entity.getItemIcon(item, 0);
            EntityPlayer player = entity instanceof EntityPlayer ? (EntityPlayer) entity : null;

            if (iicon == null)
                GL11.glPopMatrix();
            else {
                Minecraft.getMinecraft().getTextureManager().bindTexture(Minecraft.getMinecraft().getTextureManager().getResourceLocation(item.getItemSpriteNumber()));
                TextureUtil.func_152777_a(false, false, 1.0F);
                Tessellator tessellator = Tessellator.instance;
                float f = iicon.getMinU();
                float f1 = iicon.getMaxU();
                float f2 = iicon.getMinV();
                float f3 = iicon.getMaxV();

                GL11.glTranslatef(0.0F, 0.3F, 0.0F);
                GL11.glRotatef(-230.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-110.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(0.9375F, 0.0625F, 0.0F);

                GL11.glScalef(2F, 2F, 2F);
                GL11.glTranslatef(-1.4F, -0.8F, 0.2F);

                if(player!=null&&player.isUsingItem()){
                    int c = (player.inventory.getCurrentItem().getMaxItemUseDuration() - player.getItemInUseCount());
                    float mod = (float) Config.spearThrowerReadyTime / 4;
                    float angle = c*mod;

                    GL11.glRotatef(Float.min(angle,78F),0,0,1);

                    GL11.glTranslatef(0,-Float.min((float)  angle*2/100,0.8F),0);
                    if(angle>(float)60)
                        GL11.glTranslatef(-Float.min((float)  angle/100,0.3F),0,0);
                    //else
                    //    GL11.glTranslatef(-0.1F,0,0);
                    
                }


                ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625F);

                GL11.glPopMatrix();
            }
        }
        else if(type == ItemRenderType.EQUIPPED_FIRST_PERSON){
            GL11.glPushMatrix();

            EntityLivingBase entity = (EntityLivingBase) data[1];
            IIcon iicon = entity.getItemIcon(item, 0);
            EntityPlayer player = entity instanceof EntityPlayer ? (EntityPlayer) entity : null;

            if (iicon == null)
                GL11.glPopMatrix();
            else {
                Minecraft.getMinecraft().getTextureManager().bindTexture(Minecraft.getMinecraft().getTextureManager().getResourceLocation(item.getItemSpriteNumber()));
                TextureUtil.func_152777_a(false, false, 1.0F);
                Tessellator tessellator = Tessellator.instance;
                float f = iicon.getMinU();
                float f1 = iicon.getMaxU();
                float f2 = iicon.getMinV();
                float f3 = iicon.getMaxV();

                GL11.glScalef(3F,2.5F,3F);
                GL11.glTranslatef(0.8F, 0.0F, 0.8F);
                GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);

                if(player!=null&&player.isUsingItem()){
                    int c = -78 + (player.inventory.getCurrentItem().getMaxItemUseDuration() - player.getItemInUseCount());
                    float mod = (float) Config.spearThrowerReadyTime / 4;
                    float angle = c*mod;

                    GL11.glRotatef(Float.min(angle,0.78F),0,0,1);
                    GL11.glTranslatef(0.2F,0.5F - Float.min((float) (78 + angle) /100,0.5F),0);
                }
                else{
                    GL11.glRotatef(-58,0,0,1);
                    GL11.glTranslatef(0F,0.3F,0);
                }

                ItemRenderer.renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625F);

                GL11.glPopMatrix();
            }
        }
    }
}
