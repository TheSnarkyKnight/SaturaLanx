package com.snark.saturalanx.models.items;// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ArquebusModel extends ModelBase {
	private final ModelRenderer model;
	private final ModelRenderer stock;
	private final ModelRenderer stockCurveBottom_r1;
	private final ModelRenderer stockCurveTop_r1;
	private final ModelRenderer frame;
	private final ModelRenderer barrel;
	private final ModelRenderer mechanism;
	private final ModelRenderer trigger;
	private final ModelRenderer triggerBottom_r1;
	private final ModelRenderer lever;
	private final ModelRenderer leverCurveTop_r1;
	private final ModelRenderer leverCurveBottom_r1;

	public ArquebusModel() {
		textureWidth = 64;
		textureHeight = 64;

		model = new ModelRenderer(this);
		model.setRotationPoint(0.0F, 23.0F, 7.0F);
		

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 0.0F, 0.0F);
		model.addChild(stock);
		

		stockCurveBottom_r1 = new ModelRenderer(this);
		stockCurveBottom_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		stock.addChild(stockCurveBottom_r1);
		setRotationAngle(stockCurveBottom_r1, 0.3403F, 0.0F, 0.0F);
		stockCurveBottom_r1.cubeList.add(new ModelBox(stockCurveBottom_r1, 22, 22, -2.0F, -3.6F, -2.2F, 3, 3, 2, 0.0F));

		stockCurveTop_r1 = new ModelRenderer(this);
		stockCurveTop_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		stock.addChild(stockCurveTop_r1);
		setRotationAngle(stockCurveTop_r1, 1.1781F, 0.0F, 0.0F);
		stockCurveTop_r1.cubeList.add(new ModelBox(stockCurveTop_r1, 0, 22, -2.0F, -6.0F, 1.0F, 3, 4, 2, 0.0F));
		stockCurveTop_r1.cubeList.add(new ModelBox(stockCurveTop_r1, 0, 0, -2.0F, -2.0F, -1.0F, 3, 3, 4, 0.0F));

		frame = new ModelRenderer(this);
		frame.setRotationPoint(0.0F, 0.0F, 0.0F);
		model.addChild(frame);
		frame.cubeList.add(new ModelBox(frame, 0, 8, -2.0F, -4.9F, -7.0F, 3, 2, 3, 0.0F));
		frame.cubeList.add(new ModelBox(frame, 0, 22, -2.0F, -3.9F, -22.0F, 3, 1, 15, 0.0F));

		barrel = new ModelRenderer(this);
		barrel.setRotationPoint(0.0F, 1.0F, -7.0F);
		model.addChild(barrel);
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -1.5F, -6.5F, -19.0F, 2, 2, 19, 0.0F));
		barrel.cubeList.add(new ModelBox(barrel, 24, 0, -2.0F, -6.75F, -3.0F, 3, 3, 1, 0.0F));
		barrel.cubeList.add(new ModelBox(barrel, 9, 14, -2.0F, -6.75F, -14.0F, 3, 3, 1, 0.0F));
		barrel.cubeList.add(new ModelBox(barrel, 0, 14, -2.0F, -7.0F, -19.25F, 3, 3, 1, 0.0F));

		mechanism = new ModelRenderer(this);
		mechanism.setRotationPoint(0.0F, 1.0F, -7.0F);
		model.addChild(mechanism);
		

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 0.0F, 0.0F);
		mechanism.addChild(trigger);
		trigger.cubeList.add(new ModelBox(trigger, 13, 10, -1.0F, -4.4F, 0.0F, 1, 1, 1, 0.0F));

		triggerBottom_r1 = new ModelRenderer(this);
		triggerBottom_r1.setRotationPoint(-0.5F, -3.4F, 0.0F);
		trigger.addChild(triggerBottom_r1);
		setRotationAngle(triggerBottom_r1, 0.9163F, 0.0F, 0.0F);
		triggerBottom_r1.cubeList.add(new ModelBox(triggerBottom_r1, 0, 0, -0.5F, 0.0F, 0.0F, 1, 3, 0, 0.0F));

		lever = new ModelRenderer(this);
		lever.setRotationPoint(0.0F, 0.0F, 0.0F);
		mechanism.addChild(lever);
		lever.cubeList.add(new ModelBox(lever, 10, 8, -2.0F, -3.4F, 0.0F, 2, 0, 1, 0.0F));
		lever.cubeList.add(new ModelBox(lever, 15, 3, -2.5F, -6.2F, 0.0F, 0, 2, 1, 0.0F));

		leverCurveTop_r1 = new ModelRenderer(this);
		leverCurveTop_r1.setRotationPoint(-2.5F, -5.2F, 0.5F);
		lever.addChild(leverCurveTop_r1);
		setRotationAngle(leverCurveTop_r1, -0.5672F, 0.0F, 0.0F);
		leverCurveTop_r1.cubeList.add(new ModelBox(leverCurveTop_r1, 11, 0, 0.0F, -1.1F, -2.1F, 0, 1, 2, 0.0F));

		leverCurveBottom_r1 = new ModelRenderer(this);
		leverCurveBottom_r1.setRotationPoint(-2.5F, -3.4F, 0.5F);
		lever.addChild(leverCurveBottom_r1);
		setRotationAngle(leverCurveBottom_r1, 0.0F, 0.0F, 1.0472F);
		leverCurveBottom_r1.cubeList.add(new ModelBox(leverCurveBottom_r1, 14, 0, -0.7F, -0.4F, -0.5F, 1, 0, 1, 0.0F));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		model.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}