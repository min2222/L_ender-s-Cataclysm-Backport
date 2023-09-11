package com.github.L_Ender.cataclysm.client.model.entity;// Made with Blockbench 4.7.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.github.L_Ender.cataclysm.entity.Deepling.Deepling_Brute_Entity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;

public class ModelDeepling_Brute extends AdvancedEntityModel<Deepling_Brute_Entity> {
	public final AdvancedModelBox root;
	private final AdvancedModelBox left_leg;
	private final AdvancedModelBox right_leg;
	public final AdvancedModelBox body;
	private final AdvancedModelBox head;
	private final AdvancedModelBox head2;
	private final AdvancedModelBox r_fin;
	private final AdvancedModelBox l_fin;
	private final AdvancedModelBox headwear;
	public final AdvancedModelBox right_arm;
	public final AdvancedModelBox left_arm;
	private final AdvancedModelBox l_armor;
	private final AdvancedModelBox left_arm_r1;
	private ModelAnimator animator;

	public ModelDeepling_Brute() {
		texWidth = 128;
		texHeight = 128;

		root = new AdvancedModelBox(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);


		left_leg = new AdvancedModelBox(this);
		left_leg.setRotationPoint(2.0F, -20.0F, 0.0F);
		root.addChild(left_leg);
		left_leg.setTextureOffset(40, 0).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 20.0F, 2.0F, 0.0F, false);

		right_leg = new AdvancedModelBox(this);
		right_leg.setRotationPoint(-2.0F, -20.0F, 0.0F);
		root.addChild(right_leg);
		right_leg.setTextureOffset(44, 22).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 20.0F, 2.0F, 0.0F, false);

		body = new AdvancedModelBox(this);
		body.setRotationPoint(0.0F, -20.0F, 0.0F);
		root.addChild(body);
		body.setTextureOffset(0, 32).addBox(-5.0F, -11.0F, -2.0F, 10.0F, 11.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(52, 29).addBox(0.0F, -11.0F, 2.0F, 0.0F, 11.0F, 4.0F, 0.0F, false);

		head = new AdvancedModelBox(this);
		head.setRotationPoint(0.0F, -11.0F, 0.0F);
		body.addChild(head);


		head2 = new AdvancedModelBox(this);
		head2.setRotationPoint(0.0F, -3.0F, 0.0F);
		head.addChild(head2);
		head2.setTextureOffset(0, 16).addBox(-6.0F, -4.0F, -4.0F, 12.0F, 8.0F, 8.0F, 0.0F, false);

		r_fin = new AdvancedModelBox(this);
		r_fin.setRotationPoint(-6.0F, 0.0F, 0.0F);
		head2.addChild(r_fin);
		r_fin.setTextureOffset(44, 44).addBox(-6.0F, -7.0F, 0.0F, 6.0F, 8.0F, 0.0F, 0.0F, false);

		l_fin = new AdvancedModelBox(this);
		l_fin.setRotationPoint(6.0F, 0.0F, 0.0F);
		head2.addChild(l_fin);
		l_fin.setTextureOffset(0, 47).addBox(0.0F, -7.0F, 0.0F, 6.0F, 8.0F, 0.0F, 0.0F, false);

		headwear = new AdvancedModelBox(this);
		headwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(headwear);
		headwear.setTextureOffset(0, 0).addBox(-6.0F, -7.0F, -4.0F, 12.0F, 8.0F, 8.0F, -0.5F, false);

		right_arm = new AdvancedModelBox(this);
		right_arm.setRotationPoint(-6.0F, -10.0F, 0.0F);
		body.addChild(right_arm);
		right_arm.setTextureOffset(36, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
		right_arm.setTextureOffset(12, 47).addBox(-4.0F, 1.0F, 0.0F, 3.0F, 12.0F, 0.0F, 0.0F, false);

		left_arm = new AdvancedModelBox(this);
		left_arm.setRotationPoint(6.0F, -10.0F, 0.0F);
		body.addChild(left_arm);
		left_arm.setTextureOffset(28, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 24.0F, 2.0F, 0.0F, false);
		left_arm.setTextureOffset(18, 47).addBox(1.0F, 1.0F, 0.0F, 3.0F, 12.0F, 0.0F, 0.0F, false);

		l_armor = new AdvancedModelBox(this);
		l_armor.setRotationPoint(1.0285F, 3.0853F, 0.0F);
		left_arm.addChild(l_armor);
		l_armor.setTextureOffset(29, 71).addBox(2.4784F, 4.2653F, -2.5F, 0.0F, 4.0F, 5.0F, 0.0F, false);

		left_arm_r1 = new AdvancedModelBox(this);
		left_arm_r1.setRotationPoint(0.9715F, -1.5853F, 0.0F);
		l_armor.addChild(left_arm_r1);
		setRotationAngle(left_arm_r1, 0.0F, 0.0F, 0.1745F);
		left_arm_r1.setTextureOffset(29, 68).addBox(-0.5F, 2.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F, false);
		left_arm_r1.setTextureOffset(45, 71).addBox(1.5F, -4.5F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
		left_arm_r1.setTextureOffset(29, 58).addBox(-4.5F, -2.5F, -2.5F, 7.0F, 5.0F, 5.0F, 0.0F, false);
		animator = ModelAnimator.create();
		this.updateDefaultPose();
	}

	@Override
	public Iterable<BasicModelPart> parts() {
		return ImmutableList.of(root);
	}

	@Override
	public Iterable<AdvancedModelBox> getAllParts() {
		return ImmutableList.of(root, left_leg,right_leg,body,headwear,head,head2,r_fin,l_fin,right_arm,left_arm,l_armor,left_arm_r1);
	}

	public void animate(Deepling_Brute_Entity entity, float f, float f1, float f2, float f3, float f4) {
		this.resetToDefaultPose();
		animator.update(entity);
		animator.setAnimation(Deepling_Brute_Entity.DEEPLING_BRUTE_TRIDENT_THROW);
		if(!entity.isInWater()) {
			if(entity.isLeftHanded()){
				animator.startKeyframe(10);
				animator.rotate(right_arm, (float) Math.toRadians(-55F), (float) Math.toRadians(10F), (float) Math.toRadians(7.5F));
				animator.rotate(left_arm, (float) Math.toRadians(-200F), (float) Math.toRadians(-15F), (float) Math.toRadians(35f));
				animator.rotate(head, (float) Math.toRadians(-7.5F), (float) Math.toRadians(10.5F), (float) Math.toRadians(7.5F));
				animator.rotate(body, (float) Math.toRadians(-10F), (float) Math.toRadians(-15F), (float) Math.toRadians(-7.5F));
				animator.endKeyframe();

				animator.startKeyframe(2);
				animator.rotate(left_arm, (float) Math.toRadians(-55.5F), (float) Math.toRadians(5F), (float) Math.toRadians(50f));
				animator.rotate(right_arm, (float) Math.toRadians(-7.5F), (float) Math.toRadians(12.5F), (float) Math.toRadians(22.5F));

				animator.rotate(head, (float) Math.toRadians(5F), (float) Math.toRadians(10F), (float) Math.toRadians(5F));
				animator.rotate(body, (float) Math.toRadians(22.5F), (float) Math.toRadians(30F), (float) Math.toRadians(-7.5F));
				animator.endKeyframe();

				animator.resetKeyframe(33);
			}else {
				animator.startKeyframe(10);
				animator.rotate(left_arm, (float) Math.toRadians(-55F), (float) Math.toRadians(-10F), (float) Math.toRadians(-7.5F));
				animator.rotate(right_arm, (float) Math.toRadians(-200F), (float) Math.toRadians(15F), (float) Math.toRadians(-35f));
				animator.rotate(head, (float) Math.toRadians(-7.5F), (float) Math.toRadians(-10.5F), (float) Math.toRadians(-7.5F));
				animator.rotate(body, (float) Math.toRadians(-10F), (float) Math.toRadians(15F), (float) Math.toRadians(7.5F));
				animator.endKeyframe();

				animator.startKeyframe(2);
				animator.rotate(left_arm, (float) Math.toRadians(-7.5F), (float) Math.toRadians(-12.5F), (float) Math.toRadians(-22.5F));
				animator.rotate(right_arm, (float) Math.toRadians(-55.5F), (float) Math.toRadians(-5F), (float) Math.toRadians(-50f));
				animator.rotate(head, (float) Math.toRadians(5F), (float) Math.toRadians(-10F), (float) Math.toRadians(-5F));
				animator.rotate(body, (float) Math.toRadians(22.5F), (float) Math.toRadians(-30F), (float) Math.toRadians(7.5F));
				animator.endKeyframe();

				animator.resetKeyframe(33);
			}
		}else{
			if(entity.isLeftHanded()){
				animator.startKeyframe(10);
				animator.rotate(left_arm, (float) Math.toRadians(-200f), (float) Math.toRadians(-17.5f), (float) Math.toRadians(72.5f));
				animator.rotate(right_arm, 0, 0, (float) Math.toRadians(-10f));

				animator.rotate(head, (float) Math.toRadians(-7.5F), (float) Math.toRadians(10.5F), (float) Math.toRadians(7.5F));
				animator.rotate(body, (float) Math.toRadians(2.5F), (float) Math.toRadians(-15F), (float) Math.toRadians(-7.5F));
				animator.endKeyframe();

				animator.startKeyframe(2);
				animator.rotate(left_arm, (float) Math.toRadians(-55.5F), (float) Math.toRadians(5F), (float) Math.toRadians(50f));
				animator.rotate(right_arm, 0, 0, (float) Math.toRadians(7.5F));

				animator.rotate(head, (float) Math.toRadians(-5F), (float) Math.toRadians(10F), (float) Math.toRadians(7.5F));
				animator.rotate(body, (float) Math.toRadians(22.5F), (float) Math.toRadians(30F), (float) Math.toRadians(-7.5F));
				animator.endKeyframe();

				animator.resetKeyframe(28);
			}else {
				animator.startKeyframe(10);
				animator.rotate(left_arm, 0, 0, (float) Math.toRadians(-10f));
				animator.rotate(right_arm, (float) Math.toRadians(-200f), (float) Math.toRadians(17.5f), (float) Math.toRadians(-72.5f));
				animator.rotate(head, (float) Math.toRadians(-7.5F), (float) Math.toRadians(-10.5F), (float) Math.toRadians(-7.5F));
				animator.rotate(body, (float) Math.toRadians(2.5F), (float) Math.toRadians(15F), (float) Math.toRadians(7.5F));
				animator.endKeyframe();

				animator.startKeyframe(2);
				animator.rotate(left_arm, 0, 0, (float) Math.toRadians(-7.5F));
				animator.rotate(right_arm, (float) Math.toRadians(-55.5F), (float) Math.toRadians(-5F), (float) Math.toRadians(-50f));
				animator.rotate(head, (float) Math.toRadians(-5F), (float) Math.toRadians(-10F), (float) Math.toRadians(-7.5F));
				animator.rotate(body, (float) Math.toRadians(22.5F), (float) Math.toRadians(-30F), (float) Math.toRadians(7.5F));
				animator.endKeyframe();

				animator.resetKeyframe(28);
			}
		}

		animator.setAnimation(Deepling_Brute_Entity.DEEPLING_BRUTE_MELEE);
		if(!entity.isInWater()) {
			if(entity.isLeftHanded()){
				animator.startKeyframe(4);
				animator.rotate(right_arm, (float) Math.toRadians(12.5F), 0, (float) Math.toRadians(10F));
				animator.rotate(left_arm, 0, 0, (float) Math.toRadians(-75f));
				animator.rotate(body, (float) Math.toRadians(-12.5F), (float) Math.toRadians(-10F), (float) Math.toRadians(12.5F));
				animator.endKeyframe();

				animator.startKeyframe(2);
				animator.rotate(right_arm, (float) Math.toRadians(15F), 0, (float) Math.toRadians(10F));
				animator.rotate(left_arm, (float) Math.toRadians(-107.5f), (float) Math.toRadians(12.5f), (float) Math.toRadians(-77.5f));
				animator.rotate(body, (float) Math.toRadians(30f), (float) Math.toRadians(30F), (float) Math.toRadians(7.5F));
				animator.endKeyframe();

				animator.resetKeyframe(14);
			}else {
				animator.startKeyframe(4);
				animator.rotate(left_arm, (float) Math.toRadians(12.5F), 0, (float) Math.toRadians(-10F));
				animator.rotate(right_arm, 0, 0, (float) Math.toRadians(75f));
				animator.rotate(body, (float) Math.toRadians(-12.5F), (float) Math.toRadians(10F), (float) Math.toRadians(-12.5F));
				animator.endKeyframe();

				animator.startKeyframe(2);
				animator.rotate(left_arm, (float) Math.toRadians(15F), 0, (float) Math.toRadians(-10F));
				animator.rotate(right_arm, (float) Math.toRadians(-107.5f), (float) Math.toRadians(-12.5f), (float) Math.toRadians(77.5f));
				animator.rotate(body, (float) Math.toRadians(30f), (float) Math.toRadians(-30F), (float) Math.toRadians(-7.5F));
				animator.endKeyframe();

				animator.resetKeyframe(14);
			}
		}else{
			if(entity.isLeftHanded()){
				animator.startKeyframe(4);
				animator.rotate(right_arm, (float) Math.toRadians(12.5F), 0, (float) Math.toRadians(-27.5F));
				animator.rotate(left_arm, 0, 0, (float) Math.toRadians(-37.5f));
				animator.rotate(body, (float) Math.toRadians(-12.5F), (float) Math.toRadians(-10F), (float) Math.toRadians(12.5F));
				animator.endKeyframe();

				animator.startKeyframe(2);
				animator.rotate(right_arm, (float) Math.toRadians(15F), 0, (float) Math.toRadians(-27.5F));
				animator.rotate(left_arm, (float) Math.toRadians(-107.5f), (float) Math.toRadians(12.5f), (float) Math.toRadians(-40f));
				animator.rotate(body, (float) Math.toRadians(30f), (float) Math.toRadians(30F), (float) Math.toRadians(7.5F));
				animator.endKeyframe();

				animator.resetKeyframe(14);
			}else {
				animator.startKeyframe(4);
				animator.rotate(left_arm, (float) Math.toRadians(12.5F), 0, (float) Math.toRadians(-10F));
				animator.rotate(right_arm, 0, 0, (float) Math.toRadians(75f));
				animator.rotate(body, (float) Math.toRadians(-12.5F), (float) Math.toRadians(10F), (float) Math.toRadians(-12.5F));
				animator.endKeyframe();

				animator.startKeyframe(2);
				animator.rotate(left_arm, (float) Math.toRadians(15F), 0, (float) Math.toRadians(-10F));
				animator.rotate(right_arm, (float) Math.toRadians(-107.5f), (float) Math.toRadians(-12.5f), (float) Math.toRadians(77.5f));
				animator.rotate(body, (float) Math.toRadians(30f), (float) Math.toRadians(-30F), (float) Math.toRadians(-7.5F));
				animator.endKeyframe();

				animator.resetKeyframe(14);
			}
		}

	}

	@Override
	public void setupAnim(Deepling_Brute_Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		animate(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float walkSpeed = 0.75F;
		float walkDegree = 0.5F;

		float swimSpeed = 1.0F;
		float swimDegree = 0.75F;
		this.faceTarget(netHeadYaw, headPitch, 1, head);

		this.walk(left_arm, 0.05f, walkDegree * 0.05F, false, 0F, 0F, ageInTicks, 1.0f);
		this.walk(right_arm, 0.05f, walkDegree * 0.05F, true, 0F, 0F, ageInTicks, 1.0f);

		this.flap(left_arm, 0.05f, walkDegree * 0.05F, false, 0F, 0, ageInTicks, 1.0f);
		this.flap(right_arm, 0.05f, walkDegree * 0.05F, true, 0F, 0F, ageInTicks, 1.0f);
		float partialTick = Minecraft.getInstance().getFrameTime();
		float swimProgress = entity.prevSwimProgress + (entity.SwimProgress - entity.prevSwimProgress) * partialTick;


		progressRotationPrev(root,swimProgress,(float)Math.toRadians(22.5F), 0, 0, 10f);
		progressRotationPrev(left_leg,swimProgress,(float)Math.toRadians(20F), 0, 0, 10f);
		progressRotationPrev(right_leg,swimProgress,(float)Math.toRadians(5F), 0, 0, 10f);
		progressRotationPrev(left_arm,swimProgress,0, 0, (float)Math.toRadians(-37.5F), 10f);
		progressRotationPrev(right_arm,swimProgress,0, 0, (float)Math.toRadians(37.5F), 10f);
		if (swimProgress > 0) {
			this.walk(left_leg, swimSpeed * 1.7F, swimDegree * 1.74F, true, 0F, 0F, limbSwing, limbSwingAmount);
			this.walk(right_leg, swimSpeed * 1.7F, swimDegree * 1.7F, false, 0F, 0F, limbSwing, limbSwingAmount);
			this.walk(left_arm, swimSpeed * 1.7F, swimDegree * 1.4F, false, 0F, 0F, limbSwing, limbSwingAmount);
			this.walk(right_arm, swimSpeed * 1.7F, swimDegree * 1.4F, true, 0F, 0F, limbSwing, limbSwingAmount);
			this.walk(left_leg, 0.05f, swimDegree * 0.3F, false, 0F, 0F, ageInTicks, 1.0f);
			this.walk(right_leg, 0.05f, swimDegree * 0.3F, true, 0F, 0F, ageInTicks, 1.0f);
		}else{
			this.walk(left_leg, walkSpeed, walkDegree * 1.2F, true, 0F, 0F, limbSwing, limbSwingAmount);
			this.walk(right_leg, walkSpeed, walkDegree * 1.2F, false, 0F, 0F, limbSwing, limbSwingAmount);
			this.walk(left_arm, walkSpeed, walkDegree * 1.2F, false, 0F, 0F, limbSwing, limbSwingAmount);
			this.walk(right_arm, walkSpeed, walkDegree * 1.2F, true, 0F, 0F, limbSwing, limbSwingAmount);
		}
		if (this.riding) {
			this.root.rotationPointY += 13;
			this.right_arm.rotateAngleX += (-(float)Math.PI / 5F);
			this.left_arm.rotateAngleX += (-(float)Math.PI / 5F);
			this.right_leg.rotateAngleX = -1.4137167F;
			this.right_leg.rotateAngleY = ((float)Math.PI / 10F);
			this.right_leg.rotateAngleZ = 0.07853982F;
			this.left_leg.rotateAngleX = -1.4137167F;
			this.left_leg.rotateAngleY = (-(float)Math.PI / 10F);
			this.left_leg.rotateAngleZ = -0.07853982F;
		}
	}

	public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
		AdvancedModelBox.rotateAngleX = x;
		AdvancedModelBox.rotateAngleY = y;
		AdvancedModelBox.rotateAngleZ = z;
	}
}
