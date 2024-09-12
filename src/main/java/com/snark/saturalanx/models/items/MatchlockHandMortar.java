package com.snark.saturalanx.models.items;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MatchlockHandMortar extends ModelBase {
	private final ModelRenderer stock;
	private final ModelRenderer stockButt_r1;
	private final ModelRenderer barrel;
	private final ModelRenderer mechanism;
	private final ModelRenderer trigger;
	private final ModelRenderer triggerBottom_r1;
	private final ModelRenderer lever;
	private final ModelRenderer leverCurveTop_r1;
	private final ModelRenderer leverCurveBottom_r1;

	public MatchlockHandMortar() {
		textureWidth = 64;
		textureHeight = 64;

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 24.0F, 0.0F);
		stock.cubeList.add(new ModelBox(stock, 0, 0, -2.0F, -6.7F, -7.2F, 4, 3, 13, 0.0F));
		stock.cubeList.add(new ModelBox(stock, 22, 0, -2.0F, -8.0F, 0.0F, 4, 2, 3, 0.0F));

		stockButt_r1 = new ModelRenderer(this);
		stockButt_r1.setRotationPoint(0.0F, -5.5F, 11.5F);
		stock.addChild(stockButt_r1);
		setRotationAngle(stockButt_r1, -0.4363F, 0.0F, 0.0F);
		stockButt_r1.cubeList.add(new ModelBox(stockButt_r1, 21, 17, -2.0F, 4.3F, -0.8F, 4, 2, 3, 0.0F));
		stockButt_r1.cubeList.add(new ModelBox(stockButt_r1, 22, 23, -2.0F, 1.3F, -8.8F, 4, 3, 11, 0.0F));

		barrel = new ModelRenderer(this);
		barrel.setRotationPoint(0.0F, 24.0F, 0.0F);
		barrel.cubeList.add(new ModelBox(barrel, 0, 34, -1.5F, -8.0F, -3.0F, 3, 3, 3, 0.0F));
		barrel.cubeList.add(new ModelBox(barrel, 0, 17, -2.0F, -9.0F, -14.5F, 4, 4, 12, 0.0F));
		barrel.cubeList.add(new ModelBox(barrel, 22, 6, -2.5F, -9.5F, -15.5F, 5, 5, 1, 0.0F));
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -2.5F, -9.5F, -5.5F, 5, 6, 1, 0.0F));

		mechanism = new ModelRenderer(this);
		mechanism.setRotationPoint(0.5F, 24.0F, 0.0F);
		

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 0.0F, 0.0F);
		mechanism.addChild(trigger);
		trigger.cubeList.add(new ModelBox(trigger, 0, 10, -1.0F, -4.4F, 0.0F, 1, 1, 1, 0.0F));

		triggerBottom_r1 = new ModelRenderer(this);
		triggerBottom_r1.setRotationPoint(-0.5F, -3.4F, 0.0F);
		trigger.addChild(triggerBottom_r1);
		setRotationAngle(triggerBottom_r1, 0.9163F, 0.0F, 0.0F);
		triggerBottom_r1.cubeList.add(new ModelBox(triggerBottom_r1, 8, 17, -0.5F, 0.0F, 0.0F, 1, 3, 0, 0.0F));

		lever = new ModelRenderer(this);
		lever.setRotationPoint(0.0F, 0.0F, 0.0F);
		mechanism.addChild(lever);
		lever.cubeList.add(new ModelBox(lever, 0, 8, -3.0F, -3.4F, 0.0F, 3, 0, 1, 0.0F));
		lever.cubeList.add(new ModelBox(lever, 0, 17, -3.4F, -7.2F, 0.0F, 0, 3, 1, 0.0F));

		leverCurveTop_r1 = new ModelRenderer(this);
		leverCurveTop_r1.setRotationPoint(-2.5F, -5.2F, 0.5F);
		lever.addChild(leverCurveTop_r1);
		setRotationAngle(leverCurveTop_r1, -0.5672F, 0.0F, 0.0F);
		leverCurveTop_r1.cubeList.add(new ModelBox(leverCurveTop_r1, 7, 8, -0.9F, -1.9F, -2.6F, 0, 1, 2, 0.0F));

		leverCurveBottom_r1 = new ModelRenderer(this);
		leverCurveBottom_r1.setRotationPoint(-2.5F, -3.4F, 0.5F);
		lever.addChild(leverCurveBottom_r1);
		setRotationAngle(leverCurveBottom_r1, 0.0F, 0.0F, 1.0472F);
		leverCurveBottom_r1.cubeList.add(new ModelBox(leverCurveBottom_r1, 3, 17, -1.2F, 0.4F, -0.5F, 1, 0, 1, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		stock.render(f5);
		barrel.render(f5);
		mechanism.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}