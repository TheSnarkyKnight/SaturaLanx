package com.snark.saturalanx.renders.item;

import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Handlers.Client.ClientTickHandler;
import com.dunk.tfc.Render.Item.SlingItemRenderer;
import com.snark.saturalanx.items.weapons.ranged.BolasItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class BolasItemRenderer extends SlingItemRenderer {
    public BolasItemRenderer() {

    }

    @Override
    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data) {
        float partialTick;
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            partialTick = 2.5F;
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        }

        GL11.glScalef(1.25F, 1.25F, 1.25F);
        partialTick = ClientTickHandler.getPartialRenderTick();
        EntityLivingBase entity = (EntityLivingBase) data[1];
        EntityPlayer player = null;
        if (entity instanceof EntityPlayer) {
            player = (EntityPlayer) entity;
        }

        float testRot = (float) TFC_Time.getTotalTicks() % 1000.0F * 3.1415927F / 217.0F;
        float offX = 0.25F;
        float offY = 0.125F;
        float offZ = 0.0F;
        GL11.glTranslatef(offX, offY, offZ);
        GL11.glRotatef(30.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(-70.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
        if (player != null && player.isUsingItem() || item.hasTagCompound() && item.stackTagCompound.hasKey("shooting")) {
            int j = item.getMaxItemUseDuration() - player.getItemInUseCount();
            float force = (float) j / ((BolasItem) item.getItem()).getUseSpeed(player);
            if ((double) force >= 0.5) {
                GL11.glScalef(1.25F, 1.25F, 1.25F);
            }
        }

        IIcon iicon = entity.getItemIcon(item, 0);
        IIcon iicon2 = iicon;
        if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
        }

        float prevSwingSpeed;
        float armRot;
        float swingSpeed;
        float swingProg;
        float f7;
        float f6;
        float pp;
        float pp2;
        if (item.hasTagCompound()) {
            float sign = type == ItemRenderType.EQUIPPED_FIRST_PERSON ? 1.0F : -1.0F;
            float curSwing = item.stackTagCompound.getFloat("prevSwingRot");
            prevSwingSpeed = item.stackTagCompound.getFloat("prevSwingSpeed");
            armRot = curSwing;
            swingSpeed = prevSwingSpeed;
            if (player.isUsingItem() && !Minecraft.getMinecraft().isGamePaused()) {
                swingSpeed = Math.max((float) Math.min(item.getMaxItemUseDuration() - player.getItemInUseCount(), 30) / 500.0F, 0.01F);
                armRot = curSwing + swingSpeed;
            } else if (!Minecraft.getMinecraft().isGamePaused()) {
                armRot = curSwing + 1.0F;
                armRot -= (float) ((int) armRot);
                swingProg = player.getSwingProgress(partialTick);
                f7 = player.limbSwing - player.limbSwingAmount * (1.0F - partialTick);
                f6 = player.prevLimbSwingAmount + (player.limbSwingAmount - player.prevLimbSwingAmount) * partialTick;
                pp = MathHelper.cos(f7 * 0.6662F + 3.1415927F) * 2.0F * f6 * 0.5F;
                pp2 = MathHelper.cos((f7 - 0.05F) * 0.6662F + 3.1415927F) * 2.0F * f6 * 0.5F;
                if (Math.abs(pp - pp2) > 0.01F) {
                    swingSpeed = prevSwingSpeed + (pp - pp2) * Math.max(Math.min(0.02F - Math.abs(prevSwingSpeed), 0.02F), 0.0F);
                }

                if (player.isSwingInProgress && swingProg > 0.675F) {
                    swingSpeed *= 0.9F;
                } else if (player.isSwingInProgress && (double) swingProg < 0.5) {
                    if (armRot > 0.15F) {
                        armRot += swingProg * (1.15F - armRot) / 100.0F;
                    } else {
                        armRot += swingProg * (0.15F - armRot) / 100.0F;
                    }

                    swingSpeed = Math.max(0.01F, swingSpeed);
                }

                if ((double) armRot < 0.5) {
                    swingSpeed -= 5.0E-4F;
                } else if ((double) armRot >= 0.5) {
                    swingSpeed += 5.0E-4F;
                }

                swingSpeed *= 0.99F;
                if (Math.abs(swingSpeed) < 0.01F && ((double) armRot < 0.01 || (double) armRot > 0.99)) {
                    swingSpeed *= 0.97F;
                    if (Math.abs(swingSpeed) < 0.001F) {
                        swingSpeed = 0.0F;
                    }
                }

                armRot += swingSpeed;
            }

            if ((double) swingSpeed > 0.04) {
                iicon = item.getItem().getIcon(item, 0, player, item, item.getItem().getMaxItemUseDuration(item) - 1000);
            }

            GL11.glRotatef(sign * 360.0F * armRot, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
            item.stackTagCompound.setFloat("prevSwingRot", armRot);
            item.stackTagCompound.setFloat("prevSwingSpeed", swingSpeed);
        }

        boolean badStick = false;
        if (iicon == null) {
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            Minecraft.getMinecraft().getTextureManager().bindTexture(Minecraft.getMinecraft().getTextureManager().getResourceLocation(item.getItemSpriteNumber()));
            TextureUtil.func_152777_a(false, false, 1.0F);
            Tessellator tessellator = Tessellator.instance;
            prevSwingSpeed = iicon.getMinU();
            armRot = iicon.getMaxU();
            swingSpeed = iicon.getMinV();
            swingProg = iicon.getMaxV();
            f7 = iicon2.getMinU();
            f6 = iicon2.getMaxU();
            pp = iicon2.getMinV();
            pp2 = iicon2.getMaxV();
            float f4 = 0.0F;
            float f5 = 0.3F;
            ItemRenderer.renderItemIn2D(tessellator, prevSwingSpeed, swingProg, armRot, swingSpeed, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625F);
            if (item.hasEffect(0)) {
                GL11.glDepthFunc(514);
                GL11.glDisable(2896);
                GL11.glEnable(3042);
                OpenGlHelper.glBlendFunc(768, 1, 1, 0);
                f7 = 0.76F;
                GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
                GL11.glMatrixMode(5890);
                GL11.glPushMatrix();
                float f8 = 0.125F;
                GL11.glScalef(f8, f8, f8);
                float f9 = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
                GL11.glTranslatef(f9, 0.0F, 0.0F);
                GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
                ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glPushMatrix();
                GL11.glScalef(f8, f8, f8);
                f9 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
                GL11.glTranslatef(-f9, 0.0F, 0.0F);
                GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
                ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
                GL11.glPopMatrix();
                GL11.glMatrixMode(5888);
                GL11.glDisable(3042);
                GL11.glEnable(2896);
                GL11.glDepthFunc(515);
            }

            GL11.glDisable(32826);
            Minecraft.getMinecraft().getTextureManager().bindTexture(Minecraft.getMinecraft().getTextureManager().getResourceLocation(item.getItemSpriteNumber()));
            TextureUtil.func_147945_b();
            GL11.glPopMatrix();
        }
    }
}
