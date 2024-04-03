package com.snark.saturalanx.models.items;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Created using Tabula 4.1.1
 */
public class HandgonneModel extends ModelBase {
    public ModelRenderer handle;
    public ModelRenderer barrel;
    public ModelRenderer brace1;
    public ModelRenderer brace2;
    public ModelRenderer mouthpiece;

    public HandgonneModel() {
        this.textureWidth = 32;
        this.textureHeight = 48;
        this.barrel = new ModelRenderer(this, 0, 17);
        this.barrel.setRotationPoint(0.0F, 0.0F, 13.0F);
        this.barrel.addBox(-1.5F, -1.5F, 0.2F, 3, 3, 14, 0.0F);
        this.mouthpiece = new ModelRenderer(this, 0, 0);
        this.mouthpiece.setRotationPoint(0.0F, 0.0F, 14.0F);
        this.mouthpiece.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 2, 0.0F);
        this.handle = new ModelRenderer(this, 0, 0);
        this.handle.setRotationPoint(-0.0F, 0.0F, 0.0F);
        this.handle.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 14, 0.0F);
        this.brace1 = new ModelRenderer(this, 0, 35);
        this.brace1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.brace1.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 4, 0.0F);
        this.brace2 = new ModelRenderer(this, 0, 0);
        this.brace2.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.brace2.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 2, 0.0F);
        this.handle.addChild(this.barrel);
        this.barrel.addChild(this.mouthpiece);
        this.barrel.addChild(this.brace1);
        this.barrel.addChild(this.brace2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.handle.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
