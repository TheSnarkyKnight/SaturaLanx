package com.snark.saturalanx.renders.item;

import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.models.items.HandgonneModel;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

import static com.snark.saturalanx.SaturaLanx.MODID;

public class HandgonneRender implements IItemRenderer {

    protected HandgonneModel model;

    public HandgonneRender(){
        model = new HandgonneModel();
    }

    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack itemStack, ItemRendererHelper itemRendererHelper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack itemStack, Object... objects) {
        switch(type){
            case INVENTORY:
                GL11.glPushMatrix();

                if(itemStack.getItem()== ItemSetup.brassHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BrassHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.bronzeHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BronzeHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.blackBronzeHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BlackBronzeHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.bismuthBronzeHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BismuthBronzeHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.ironHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/IronHandgonne.png"));

                GL11.glRotatef(80,1,1,0);
                //GL11.glRotatef(-50,1,0,0);
                GL11.glTranslatef(0,17,-7F);
                GL11.glScalef(10F,10F,10F);

                model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;
            case ENTITY:
                GL11.glPushMatrix();

                if(itemStack.getItem()== ItemSetup.brassHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BrassHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.bronzeHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BronzeHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.blackBronzeHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BlackBronzeHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.bismuthBronzeHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BismuthBronzeHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.ironHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/IronHandgonne.png"));

                GL11.glRotatef(80,0,1,0);
                //GL11.glRotatef(-50,1,0,0);
                GL11.glTranslatef(0,0,-0.5F);
                GL11.glScalef(1F,1F,1F);


                model.render((Entity)objects[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;
            case EQUIPPED:
                GL11.glPushMatrix();

                if(itemStack.getItem()== ItemSetup.brassHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BrassHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.bronzeHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BronzeHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.blackBronzeHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BlackBronzeHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.bismuthBronzeHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BismuthBronzeHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.ironHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/IronHandgonne.png"));

                GL11.glRotatef(80,0,1,0);
                GL11.glRotatef(-30,1,0,0);
                GL11.glTranslatef(0.1F,0,0);
                GL11.glScalef(0.7F,0.7F,0.8F);


                model.render((Entity)objects[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;

            case EQUIPPED_FIRST_PERSON:
                GL11.glPushMatrix();

                if(itemStack.getItem()== ItemSetup.brassHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BrassHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.bronzeHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BronzeHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.blackBronzeHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BlackBronzeHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.bismuthBronzeHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/BismuthBronzeHandgonne.png"));
                if(itemStack.getItem()== ItemSetup.ironHandgonne)
                    Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(MODID +":textures/models/items/IronHandgonne.png"));

                GL11.glRotatef(90,0,1,0);
                GL11.glRotatef(-30,1,0,0);
                GL11.glRotatef(-5,0,0,1);
                GL11.glTranslatef(0,0.2F,-0.2F);

                model.render((Entity)objects[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
                GL11.glPopMatrix();
                break;
            default: break;
        }
    }
}
