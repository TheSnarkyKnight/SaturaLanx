package com.snark.saturalanx.models.items;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class EruptorModel extends ModelBase {
	private final ModelRenderer handle;
	private final ModelRenderer barrel;
	private final ModelRenderer mouthpiece;
	private final ModelRenderer flareRight_r1;
	private final ModelRenderer flareLeft_r1;
	private final ModelRenderer flareDown_r1;
	private final ModelRenderer flareUp_r1;
	private final ModelRenderer brace1;
	private final ModelRenderer brace2;

	public EruptorModel() {
		textureWidth = 32;
		textureHeight = 48;

		handle = new ModelRenderer(this);
		handle.setRotationPoint(0.0F, 0.0F, 0.0F);
		handle.cubeList.add(new ModelBox(handle, 0, 0, -1.0F, -1.0F, 0.0F, 2, 2, 14, 0.0F));

		barrel = new ModelRenderer(this);
		barrel.setRotationPoint(0.0F, 0.0F, 13.0F);
		handle.addChild(barrel);
		barrel.cubeList.add(new ModelBox(barrel, 0, 17, -1.5F, -1.5F, 0.2F, 3, 3, 14, 0.0F));

		mouthpiece = new ModelRenderer(this);
		mouthpiece.setRotationPoint(0.0F, 0.0F, 14.0F);
		barrel.addChild(mouthpiece);
		mouthpiece.cubeList.add(new ModelBox(mouthpiece, 16, 40, -3.0F, -3.0F, -1.0F, 6, 6, 2, 0.0F));

		flareRight_r1 = new ModelRenderer(this);
		flareRight_r1.setRotationPoint(1.6717F, 0.0F, -1.5186F);
		mouthpiece.addChild(flareRight_r1);
		setRotationAngle(flareRight_r1, 0.0F, 0.5236F, 0.0F);
		flareRight_r1.cubeList.add(new ModelBox(flareRight_r1, 3, 22, -0.5F, -1.5F, -1.0F, 1, 3, 2, 0.0F));

		flareLeft_r1 = new ModelRenderer(this);
		flareLeft_r1.setRotationPoint(-2.0F, 1.5F, -1.75F);
		mouthpiece.addChild(flareLeft_r1);
		setRotationAngle(flareLeft_r1, 0.0F, -0.5236F, 0.0F);
		flareLeft_r1.cubeList.add(new ModelBox(flareLeft_r1, 3, 22, 0.0F, -3.0F, -1.0F, 1, 3, 2, 0.0F));

		flareDown_r1 = new ModelRenderer(this);
		flareDown_r1.setRotationPoint(0.0F, 1.75F, -1.5F);
		mouthpiece.addChild(flareDown_r1);
		setRotationAngle(flareDown_r1, 0.9152F, -0.0053F, -0.0084F);
		flareDown_r1.cubeList.add(new ModelBox(flareDown_r1, 1, 17, -1.5F, -1.0F, -0.5F, 3, 2, 2, 0.0F));

		flareUp_r1 = new ModelRenderer(this);
		flareUp_r1.setRotationPoint(0.0F, -1.0F, -1.5F);
		mouthpiece.addChild(flareUp_r1);
		setRotationAngle(flareUp_r1, 0.6097F, -0.0053F, -0.0084F);
		flareUp_r1.cubeList.add(new ModelBox(flareUp_r1, 1, 17, -1.5F, -1.0F, -0.5F, 3, 2, 2, 0.0F));

		brace1 = new ModelRenderer(this);
		brace1.setRotationPoint(0.0F, 0.0F, 0.0F);
		barrel.addChild(brace1);
		brace1.cubeList.add(new ModelBox(brace1, 0, 35, -2.0F, -2.0F, 0.0F, 4, 4, 4, 0.0F));

		brace2 = new ModelRenderer(this);
		brace2.setRotationPoint(0.0F, 0.0F, 8.0F);
		barrel.addChild(brace2);
		brace2.cubeList.add(new ModelBox(brace2, 0, 0, -2.0F, -2.0F, 0.0F, 4, 4, 2, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		handle.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}