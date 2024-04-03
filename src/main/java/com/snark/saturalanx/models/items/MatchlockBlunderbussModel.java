package com.snark.saturalanx.models.items;// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.7 - 1.12
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MatchlockBlunderbussModel extends ModelBase {
	private final ModelRenderer model;
	private final ModelRenderer stock;
	private final ModelRenderer stockCurve_r1;
	private final ModelRenderer stockTop_r1;
	private final ModelRenderer stockBottom_r1;
	private final ModelRenderer frame;
	private final ModelRenderer barrel;
	private final ModelRenderer muzzle;
	private final ModelRenderer flareRight_r1;
	private final ModelRenderer flareLeft_r1;
	private final ModelRenderer flareBottom_r1;
	private final ModelRenderer flareTop_r1;
	private final ModelRenderer mechanism;
	private final ModelRenderer trigger;
	private final ModelRenderer triggerBottom_r1;
	private final ModelRenderer lever;
	private final ModelRenderer leverCurveTop_r1;
	private final ModelRenderer leverCurveBottom_r1;

	public MatchlockBlunderbussModel() {
		textureWidth = 64;
		textureHeight = 64;

		model = new ModelRenderer(this);
		model.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(model, 0.0F, -1.5708F, 0.0F);
		

		stock = new ModelRenderer(this);
		stock.setRotationPoint(0.0F, 0.0F, 0.0F);
		model.addChild(stock);
		

		stockCurve_r1 = new ModelRenderer(this);
		stockCurve_r1.setRotationPoint(6.285F, -4.8299F, 0.5F);
		stock.addChild(stockCurve_r1);
		setRotationAngle(stockCurve_r1, 0.0F, 0.0F, 1.1781F);
		stockCurve_r1.cubeList.add(new ModelBox(stockCurve_r1, 24, 2, 0.0F, -0.7F, -1.5F, 1, 2, 3, 0.0F));

		stockTop_r1 = new ModelRenderer(this);
		stockTop_r1.setRotationPoint(8.1327F, -4.0646F, 0.5F);
		stock.addChild(stockTop_r1);
		setRotationAngle(stockTop_r1, 0.0F, 0.0F, 0.3927F);
		stockTop_r1.cubeList.add(new ModelBox(stockTop_r1, 21, 13, -1.5F, -0.5F, -1.5F, 3, 1, 3, 0.0F));

		stockBottom_r1 = new ModelRenderer(this);
		stockBottom_r1.setRotationPoint(5.249F, -3.6355F, 0.5F);
		stock.addChild(stockBottom_r1);
		setRotationAngle(stockBottom_r1, 0.0F, 0.0F, 0.3927F);
		stockBottom_r1.cubeList.add(new ModelBox(stockBottom_r1, 0, 10, -4.0F, -1.0F, -1.5F, 8, 2, 3, 0.0F));

		frame = new ModelRenderer(this);
		frame.setRotationPoint(0.0F, 0.0F, 0.0F);
		model.addChild(frame);
		frame.cubeList.add(new ModelBox(frame, 11, 16, -1.0F, -6.1F, -1.0F, 3, 2, 3, 0.0F));
		frame.cubeList.add(new ModelBox(frame, 0, 5, -10.0F, -5.1F, -1.0F, 9, 1, 3, 0.0F));

		barrel = new ModelRenderer(this);
		barrel.setRotationPoint(0.5F, -5.1F, 0.5F);
		model.addChild(barrel);
		barrel.cubeList.add(new ModelBox(barrel, 0, 0, -12.5F, -1.6F, -1.0F, 11, 2, 2, 0.0F));
		barrel.cubeList.add(new ModelBox(barrel, 19, 25, -6.5F, -1.8F, -1.5F, 1, 3, 3, 0.0F));

		muzzle = new ModelRenderer(this);
		muzzle.setRotationPoint(0.0F, 0.0F, 0.0F);
		barrel.addChild(muzzle);
		muzzle.cubeList.add(new ModelBox(muzzle, 0, 16, -15.0F, -2.8F, -2.0F, 1, 4, 4, 0.0F));

		flareRight_r1 = new ModelRenderer(this);
		flareRight_r1.setRotationPoint(-12.0706F, -0.5506F, 0.0F);
		muzzle.addChild(flareRight_r1);
		setRotationAngle(flareRight_r1, 0.0F, 0.3491F, 0.0F);
		flareRight_r1.cubeList.add(new ModelBox(flareRight_r1, 11, 22, -2.5F, -1.0F, -0.8F, 3, 2, 2, 0.0F));

		flareLeft_r1 = new ModelRenderer(this);
		flareLeft_r1.setRotationPoint(-12.0706F, -0.5506F, 0.0F);
		muzzle.addChild(flareLeft_r1);
		setRotationAngle(flareLeft_r1, 0.0F, -0.4102F, 0.0F);
		flareLeft_r1.cubeList.add(new ModelBox(flareLeft_r1, 22, 20, -2.5F, -1.0F, -1.1F, 3, 2, 2, 0.0F));

		flareBottom_r1 = new ModelRenderer(this);
		flareBottom_r1.setRotationPoint(-11.5F, -0.6F, 0.0F);
		muzzle.addChild(flareBottom_r1);
		setRotationAngle(flareBottom_r1, 0.0F, 0.0F, -0.288F);
		flareBottom_r1.cubeList.add(new ModelBox(flareBottom_r1, 23, 8, -3.2F, -1.1F, -1.0F, 3, 2, 2, 0.0F));

		flareTop_r1 = new ModelRenderer(this);
		flareTop_r1.setRotationPoint(-11.5F, -0.6F, 0.0F);
		muzzle.addChild(flareTop_r1);
		setRotationAngle(flareTop_r1, 0.0F, 0.0F, 0.3927F);
		flareTop_r1.cubeList.add(new ModelBox(flareTop_r1, 0, 25, -3.2F, -1.0F, -1.0F, 3, 2, 2, 0.0F));

		mechanism = new ModelRenderer(this);
		mechanism.setRotationPoint(-1.0F, 0.0F, 0.0F);
		model.addChild(mechanism);
		setRotationAngle(mechanism, 0.0F, 1.5708F, 0.0F);
		

		trigger = new ModelRenderer(this);
		trigger.setRotationPoint(0.0F, 0.0F, 0.0F);
		mechanism.addChild(trigger);
		trigger.cubeList.add(new ModelBox(trigger, 25, 25, -1.0F, -4.4F, 0.0F, 1, 1, 1, 0.0F));

		triggerBottom_r1 = new ModelRenderer(this);
		triggerBottom_r1.setRotationPoint(-0.5F, -3.4F, 0.0F);
		trigger.addChild(triggerBottom_r1);
		setRotationAngle(triggerBottom_r1, 0.9163F, 0.0F, 0.0F);
		triggerBottom_r1.cubeList.add(new ModelBox(triggerBottom_r1, 0, 16, -0.5F, 0.0F, 0.0F, 1, 3, 0, 0.0F));

		lever = new ModelRenderer(this);
		lever.setRotationPoint(0.0F, 0.0F, 0.0F);
		mechanism.addChild(lever);
		lever.cubeList.add(new ModelBox(lever, 7, 16, -2.0F, -3.4F, 0.0F, 2, 0, 1, 0.0F));
		lever.cubeList.add(new ModelBox(lever, 16, 27, -2.5F, -6.2F, 0.0F, 0, 2, 1, 0.0F));

		leverCurveTop_r1 = new ModelRenderer(this);
		leverCurveTop_r1.setRotationPoint(-2.5F, -5.2F, 0.5F);
		lever.addChild(leverCurveTop_r1);
		setRotationAngle(leverCurveTop_r1, -0.5672F, 0.0F, 0.0F);
		leverCurveTop_r1.cubeList.add(new ModelBox(leverCurveTop_r1, 11, 27, 0.0F, -1.1F, -2.1F, 0, 1, 2, 0.0F));

		leverCurveBottom_r1 = new ModelRenderer(this);
		leverCurveBottom_r1.setRotationPoint(-2.5F, -3.4F, 0.5F);
		lever.addChild(leverCurveBottom_r1);
		setRotationAngle(leverCurveBottom_r1, 0.0F, 0.0F, 1.0472F);
		leverCurveBottom_r1.cubeList.add(new ModelBox(leverCurveBottom_r1, 24, 18, -0.7F, -0.4F, -0.5F, 1, 0, 1, 0.0F));
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