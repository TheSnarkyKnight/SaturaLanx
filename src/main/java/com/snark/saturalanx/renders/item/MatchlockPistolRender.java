package com.snark.saturalanx.renders.item;

import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.models.items.MatchlockPistolModel;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import static com.snark.saturalanx.SaturaLanx.MODID;

public class MatchlockPistolRender implements IItemRenderer {
    public MatchlockPistolModel model;

    public MatchlockPistolRender(){
        model = new MatchlockPistolModel();
    }

    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType type) {
        return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType itemRenderType, ItemStack itemStack, ItemRendererHelper itemRendererHelper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack itemStack, Object... objects) {
        switch(type){
            case INVENTORY:
                GL11.glPushMatrix();

                if(itemStack.getItem()== ItemSetup.brassMPistol)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/weapons/pistol/MatchlockPistolBrass.png"));
                if(itemStack.getItem()== ItemSetup.ironMPistol)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/weapons/pistol/MatchlockPistolIron.png"));
                if(itemStack.getItem()== ItemSetup.steelMPistol)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/weapons/pistol/MatchlockPistolSteel.png"));

                GL11.glRotatef(80,1,1,0);
                //GL11.glRotatef(-50,1,0,0);
                GL11.glTranslatef(0,5,5);
                GL11.glScalef(10F,10F,10F);

                model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;
            case ENTITY:
                GL11.glPushMatrix();

                if(itemStack.getItem()== ItemSetup.brassMPistol)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/weapons/pistol/MatchlockPistolBrass.png"));
                if(itemStack.getItem()== ItemSetup.ironMPistol)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/weapons/pistol/MatchlockPistolIron.png"));
                if(itemStack.getItem()== ItemSetup.steelMPistol)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/weapons/pistol/MatchlockPistolSteel.png"));

                GL11.glRotatef(180,0,1,0);
                GL11.glRotatef(180,1,0,0);
                GL11.glTranslatef(0,-1.5F,0.5F);
                GL11.glScalef(1.3F,1.3F,1.3F);


                model.render((Entity)objects[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;
            case EQUIPPED:
                GL11.glPushMatrix();

                if(itemStack.getItem()== ItemSetup.brassMPistol)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/weapons/pistol/MatchlockPistolBrass.png"));
                if(itemStack.getItem()== ItemSetup.ironMPistol)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/weapons/pistol/MatchlockPistolIron.png"));
                if(itemStack.getItem()== ItemSetup.steelMPistol)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/weapons/pistol/MatchlockPistolSteel.png"));

                GL11.glRotatef(-90,0,0,1);
                GL11.glRotatef(90,0,1,0);
                GL11.glRotatef(50,1,0,0);
                GL11.glTranslatef(0.1F,-1.1F,-1F);
                GL11.glScalef(0.9F,0.9F,0.9F);


                model.render((Entity)objects[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;

            case EQUIPPED_FIRST_PERSON:
                GL11.glPushMatrix();

                if(itemStack.getItem()== ItemSetup.brassMPistol)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/weapons/pistol/MatchlockPistolBrass.png"));
                if(itemStack.getItem()== ItemSetup.ironMPistol)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/weapons/pistol/MatchlockPistolIron.png"));
                if(itemStack.getItem()== ItemSetup.steelMPistol)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/weapons/pistol/MatchlockPistolSteel.png"));

                GL11.glRotatef(-90,0,0,1);
                GL11.glRotatef(90,0,1,0);
                GL11.glRotatef(60,1,0,0);
                GL11.glTranslatef(0.1F,-1.2F,-0.7F);
                //GL11.glScalef(0.9F,0.9F,0.9F);

                model.render((Entity)objects[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;
            default: break;
        }
    }

}
