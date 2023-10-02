package com.github.L_Ender.cataclysm.client.model.entity;// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


import com.github.L_Ender.cataclysm.entity.BossMonsters.Ancient_Remnant_Entity;
import com.github.alexthe666.citadel.client.model.AdvancedEntityModel;
import com.github.alexthe666.citadel.client.model.AdvancedModelBox;
import com.github.alexthe666.citadel.client.model.ModelAnimator;
import com.github.alexthe666.citadel.client.model.basic.BasicModelPart;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class ModelAncient_Remnant extends AdvancedEntityModel<Ancient_Remnant_Entity> {
	private final AdvancedModelBox root;
	private final AdvancedModelBox pelvis;
	private final AdvancedModelBox mid_pivot;
	private final AdvancedModelBox left_long_bone;
	private final AdvancedModelBox right_long_bone;
	private final AdvancedModelBox spine_sail2;
	private final AdvancedModelBox left_bone;
	private final AdvancedModelBox right_bone;
	private final AdvancedModelBox left_big_bone;
	private final AdvancedModelBox right_big_bone;
	private final AdvancedModelBox tail1;
	private final AdvancedModelBox tail2;
	private final AdvancedModelBox tail3;
	private final AdvancedModelBox tail4;
	private final AdvancedModelBox spine1;
	private final AdvancedModelBox spine2;
	private final AdvancedModelBox spine_sail1;
	private final AdvancedModelBox right_shoulder;
	private final AdvancedModelBox left_shoulder;
	private final AdvancedModelBox neck1;
	private final AdvancedModelBox neck2;
	private final AdvancedModelBox head;
	private final AdvancedModelBox jaw;
	private final AdvancedModelBox left_arm;
	private final AdvancedModelBox left_front_arm;
	private final AdvancedModelBox left_hand;
	private final AdvancedModelBox left_finger3;
	private final AdvancedModelBox left_finger1;
	private final AdvancedModelBox left_finger2;
	private final AdvancedModelBox right_arm;
	private final AdvancedModelBox right_front_arm;
	private final AdvancedModelBox right_hand;
	private final AdvancedModelBox right_finger1;
	private final AdvancedModelBox right_finger2;
	private final AdvancedModelBox right_finger3;
	private final AdvancedModelBox legs;
	private final AdvancedModelBox left_leg;
	private final AdvancedModelBox left_deco1;
	private final AdvancedModelBox left_front_leg;
	private final AdvancedModelBox left_ankel_joint;
	private final AdvancedModelBox left_mini_bone;
	private final AdvancedModelBox left_deco2;
	private final AdvancedModelBox left_deco3;
	private final AdvancedModelBox left_ankel;
	private final AdvancedModelBox left_foot;
	private final AdvancedModelBox left_toe;
	private final AdvancedModelBox left_toe2;
	private final AdvancedModelBox left_toe3;
	private final AdvancedModelBox right_leg;
	private final AdvancedModelBox right_deco1;
	private final AdvancedModelBox right_front_leg;
	private final AdvancedModelBox right_ankel_joint;
	private final AdvancedModelBox right_mini_bone;
	private final AdvancedModelBox right_deco2;
	private final AdvancedModelBox right_deco3;
	private final AdvancedModelBox right_ankel;
	private final AdvancedModelBox right_foot;
	private final AdvancedModelBox right_toe;
	private final AdvancedModelBox right_toe2;
	private final AdvancedModelBox right_toe3;
	private ModelAnimator animator;

	public ModelAncient_Remnant() {
		texWidth = 256;
		texHeight = 256;

		root = new AdvancedModelBox(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);


		mid_pivot = new AdvancedModelBox(this);
		mid_pivot.setRotationPoint(0.0F, -69.0F, -10.0F);
		root.addChild(mid_pivot);


		pelvis = new AdvancedModelBox(this);
		pelvis.setRotationPoint(0.0F, 0.0F, 2.0F);
		mid_pivot.addChild(pelvis);
		setRotationAngle(pelvis, -0.2182F, 0.0F, 0.0F);
		pelvis.setTextureOffset(111, 43).addBox(-5.0F, -4.2211F, -9.2432F, 10.0F, 12.0F, 24.0F, 0.0F, false);

		left_long_bone = new AdvancedModelBox(this);
		left_long_bone.setRotationPoint(6.5F, -0.2211F, -8.2432F);
		pelvis.addChild(left_long_bone);
		setRotationAngle(left_long_bone, -0.4185F, 0.1274F, 0.2783F);
		left_long_bone.setTextureOffset(50, 0).addBox(0.0F, -4.0F, 6.0F, 5.0F, 28.0F, 7.0F, 0.0F, false);

		right_long_bone = new AdvancedModelBox(this);
		right_long_bone.setRotationPoint(-6.5F, -0.2211F, -8.2432F);
		pelvis.addChild(right_long_bone);
		setRotationAngle(right_long_bone, -0.4185F, -0.1274F, -0.2783F);
		right_long_bone.setTextureOffset(50, 0).addBox(-5.0F, -4.0F, 6.0F, 5.0F, 28.0F, 7.0F, 0.0F, true);

		spine_sail2 = new AdvancedModelBox(this);
		spine_sail2.setRotationPoint(0.0F, -0.2211F, 12.7568F);
		pelvis.addChild(spine_sail2);
		setRotationAngle(spine_sail2, 0.1745F, 0.0F, 0.0F);
		spine_sail2.setTextureOffset(65, 50).addBox(0.0F, -28.916F, -25.98F, 0.0F, 30.0F, 32.0F, 0.01F, false);

		left_bone = new AdvancedModelBox(this);
		left_bone.setRotationPoint(3.5F, -0.2211F, -8.2432F);
		pelvis.addChild(left_bone);
		setRotationAngle(left_bone, 0.7811F, -0.0924F, 0.0928F);
		left_bone.setTextureOffset(210, 144).addBox(1.0F, 3.0F, 0.0F, 7.0F, 33.0F, 7.0F, 0.0F, false);

		right_bone = new AdvancedModelBox(this);
		right_bone.setRotationPoint(-3.5F, -0.2211F, -8.2432F);
		pelvis.addChild(right_bone);
		setRotationAngle(right_bone, 0.7811F, 0.0924F, -0.0928F);
		right_bone.setTextureOffset(210, 144).addBox(-8.0F, 3.0F, 0.0F, 7.0F, 33.0F, 7.0F, 0.0F, true);

		left_big_bone = new AdvancedModelBox(this);
		left_big_bone.setRotationPoint(0.0F, -0.2211F, -8.2432F);
		pelvis.addChild(left_big_bone);
		setRotationAngle(left_big_bone, 0.3186F, 0.1451F, -0.413F);
		left_big_bone.setTextureOffset(65, 121).addBox(1.0F, -2.0F, -1.0F, 9.0F, 14.0F, 27.0F, 0.0F, false);

		right_big_bone = new AdvancedModelBox(this);
		right_big_bone.setRotationPoint(0.0F, -0.2211F, -8.2432F);
		pelvis.addChild(right_big_bone);
		setRotationAngle(right_big_bone, 0.3186F, -0.1451F, 0.413F);
		right_big_bone.setTextureOffset(65, 121).addBox(-10.0F, -2.0F, -1.0F, 9.0F, 14.0F, 27.0F, 0.0F, true);

		tail1 = new AdvancedModelBox(this);
		tail1.setRotationPoint(0.0F, 6.7789F, 10.7568F);
		pelvis.addChild(tail1);
		setRotationAngle(tail1, 0.0436F, 0.0F, 0.0F);
		tail1.setTextureOffset(98, 0).addBox(-4.0F, -5.916F, 1.02F, 8.0F, 10.0F, 32.0F, 0.0F, false);
		tail1.setTextureOffset(0, 109).addBox(0.0F, -24.916F, -0.98F, 0.0F, 19.0F, 32.0F, 0.0F, false);
		tail1.setTextureOffset(184, 161).addBox(0.0F, 4.084F, 6.02F, 0.0F, 6.0F, 25.0F, 0.0F, false);

		tail2 = new AdvancedModelBox(this);
		tail2.setRotationPoint(0.0F, -2.8F, 32.0F);
		tail1.addChild(tail2);
		setRotationAngle(tail2, 0.0873F, 0.0F, 0.0F);
		tail2.setTextureOffset(98, 81).addBox(-3.5F, -3.7067F, -0.3098F, 7.0F, 7.0F, 32.0F, 0.0F, false);
		tail2.setTextureOffset(106, 131).addBox(0.0F, 3.2933F, -0.3098F, 0.0F, 4.0F, 32.0F, 0.0F, false);
		tail2.setTextureOffset(139, 121).addBox(0.0F, -6.7067F, -0.3098F, 0.0F, 3.0F, 32.0F, 0.0F, false);

		tail3 = new AdvancedModelBox(this);
		tail3.setRotationPoint(0.0F, -1.5762F, 31.5254F);
		tail2.addChild(tail3);
		setRotationAngle(tail3, -0.0436F, 0.0F, 0.0F);
		tail3.setTextureOffset(0, 0).addBox(-3.0F, -2.7686F, -0.1825F, 6.0F, 6.0F, 37.0F, 0.0F, false);
		tail3.setTextureOffset(156, 32).addBox(0.0F, -6.7686F, -0.1825F, 0.0F, 4.0F, 29.0F, 0.0F, false);
		tail3.setTextureOffset(187, 7).addBox(0.0F, 3.2314F, -0.1825F, 0.0F, 4.0F, 25.0F, 0.0F, false);

		tail4 = new AdvancedModelBox(this);
		tail4.setRotationPoint(0.5F, 1.3649F, 34.6065F);
		tail3.addChild(tail4);
		setRotationAngle(tail4, -0.2618F, 0.0F, 0.0F);
		tail4.setTextureOffset(50, 7).addBox(-3.0F, -2.8005F, 0.2055F, 5.0F, 5.0F, 37.0F, 0.0F, false);

		spine1 = new AdvancedModelBox(this);
		spine1.setRotationPoint(0.0F, 5.0F, -5.0F);
		pelvis.addChild(spine1);
		spine1.setTextureOffset(147, 0).addBox(-4.5F, -5.0F, -19.0F, 9.0F, 8.0F, 23.0F, 0.0F, false);
		spine1.setTextureOffset(53, 163).addBox(3.0F, -3.0F, -19.0F, 11.0F, 18.0F, 15.0F, 0.0F, false);
		spine1.setTextureOffset(0, 161).addBox(-14.0F, -3.0F, -19.0F, 11.0F, 18.0F, 15.0F, 0.0F, false);

		spine2 = new AdvancedModelBox(this);
		spine2.setRotationPoint(0.0F, 0.0F, -19.0F);
		spine1.addChild(spine2);
		setRotationAngle(spine2, 0.3491F, 0.0F, 0.0F);
		spine2.setTextureOffset(145, 80).addBox(-4.5F, -5.0F, -23.0F, 9.0F, 8.0F, 23.0F, 0.0F, false);
		spine2.setTextureOffset(146, 186).addBox(3.0F, -3.0F, -18.0F, 10.0F, 16.0F, 15.0F, 0.0F, false);
		spine2.setTextureOffset(172, 112).addBox(-13.0F, -3.0F, -18.0F, 10.0F, 16.0F, 15.0F, 0.0F, false);
		spine2.setTextureOffset(215, 46).addBox(1.8F, -7.1199F, -17.5887F, 4.0F, 5.0F, 13.0F, 0.0F, false);
		spine2.setTextureOffset(213, 0).addBox(-5.8F, -7.1199F, -17.5887F, 4.0F, 5.0F, 13.0F, 0.0F, false);

		spine_sail1 = new AdvancedModelBox(this);
		spine_sail1.setRotationPoint(0.0F, -5.2211F, 36.7568F);
		spine2.addChild(spine_sail1);
		setRotationAngle(spine_sail1, -0.0436F, 0.0F, 0.0F);
		spine_sail1.setTextureOffset(0, 44).addBox(0.0F, -18.916F, -52.98F, 0.0F, 32.0F, 32.0F, 0.0F, false);

		right_shoulder = new AdvancedModelBox(this);
		right_shoulder.setRotationPoint(0.0F, 0.0F, 0.0F);
		spine2.addChild(right_shoulder);
		setRotationAngle(right_shoulder, 0.5314F, -0.5844F, -0.3136F);
		right_shoulder.setTextureOffset(106, 168).addBox(-23.0F, -3.0F, -17.0F, 5.0F, 5.0F, 22.0F, 0.0F, true);

		left_shoulder = new AdvancedModelBox(this);
		left_shoulder.setRotationPoint(0.0F, 0.0F, 0.0F);
		spine2.addChild(left_shoulder);
		setRotationAngle(left_shoulder, 0.5314F, 0.5844F, 0.3136F);
		left_shoulder.setTextureOffset(106, 168).addBox(18.0F, -3.0F, -17.0F, 5.0F, 5.0F, 22.0F, 0.0F, false);

		neck1 = new AdvancedModelBox(this);
		neck1.setRotationPoint(-0.5F, -1.0F, -20.0F);
		spine2.addChild(neck1);
		setRotationAngle(neck1, -0.4363F, 0.0F, 0.0F);
		neck1.setTextureOffset(90, 196).addBox(-3.5F, -4.0937F, -15.5774F, 8.0F, 9.0F, 16.0F, 0.0F, false);
		neck1.setTextureOffset(127, 210).addBox(0.5F, -10.0937F, -15.5774F, 0.0F, 6.0F, 12.0F, 0.0F, false);

		neck2 = new AdvancedModelBox(this);
		neck2.setRotationPoint(0.0F, -0.0937F, -17.5774F);
		neck1.addChild(neck2);
		setRotationAngle(neck2, 0.0873F, 0.0F, 0.0F);
		neck2.setTextureOffset(186, 37).addBox(-3.0F, -4.0038F, -10.0872F, 7.0F, 8.0F, 13.0F, 0.0F, false);
		neck2.setTextureOffset(139, 121).addBox(0.5F, -10.0038F, -10.0872F, 0.0F, 6.0F, 12.0F, 0.0F, false);

		head = new AdvancedModelBox(this);
		head.setRotationPoint(0.0F, -1.0F, -8.0F);
		neck2.addChild(head);
		setRotationAngle(head, 0.3054F, 0.0F, 0.0F);
		head.setTextureOffset(0, 195).addBox(-6.0F, -10.0F, -14.0F, 13.0F, 10.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(150, 157).addBox(-3.5F, -7.0F, -35.0F, 8.0F, 7.0F, 21.0F, 0.0F, false);
		head.setTextureOffset(33, 50).addBox(-3.5F, 0.0F, -35.0F, 8.0F, 4.0F, 21.0F, 0.0F, false);

		jaw = new AdvancedModelBox(this);
		jaw.setRotationPoint(0.0F, 2.0F, -7.0F);
		head.addChild(jaw);
		jaw.setTextureOffset(208, 101).addBox(-4.0F, -2.0F, -6.0F, 9.0F, 7.0F, 11.0F, 0.0F, false);
		jaw.setTextureOffset(33, 113).addBox(-3.0F, 0.0F, -27.0F, 7.0F, 4.0F, 21.0F, 0.0F, false);
		jaw.setTextureOffset(187, 66).addBox(-2.5F, -3.0F, -27.0F, 6.0F, 3.0F, 21.0F, 0.0F, false);

		left_arm = new AdvancedModelBox(this);
		left_arm.setRotationPoint(13.2F, 10.0F, -17.0F);
		spine2.addChild(left_arm);
		setRotationAngle(left_arm, 0.3491F, 0.0F, 0.0F);
		left_arm.setTextureOffset(98, 0).addBox(-6.0F, -4.0637F, -3.0436F, 6.0F, 20.0F, 5.0F, 0.0F, false);

		left_front_arm = new AdvancedModelBox(this);
		left_front_arm.setRotationPoint(-4.0F, 15.2977F, -1.9036F);
		left_arm.addChild(left_front_arm);
		setRotationAngle(left_front_arm, -0.829F, 0.0F, 0.0F);
		left_front_arm.setTextureOffset(98, 50).addBox(-3.0F, -0.1465F, -2.2077F, 6.0F, 11.0F, 5.0F, 0.0F, false);

		left_hand = new AdvancedModelBox(this);
		left_hand.setRotationPoint(1.0F, 10.1551F, -0.5133F);
		left_front_arm.addChild(left_hand);
		setRotationAngle(left_hand, 1.6144F, 0.0F, 0.0F);


		left_finger3 = new AdvancedModelBox(this);
		left_finger3.setRotationPoint(1.0F, 0.0049F, 0.3079F);
		left_hand.addChild(left_finger3);
		setRotationAngle(left_finger3, -0.7216F, -0.2324F, 0.2F);
		left_finger3.setTextureOffset(0, 109).addBox(-0.2215F, -3.2106F, -9.8849F, 0.0F, 7.0F, 12.0F, 0.0F, false);

		left_finger1 = new AdvancedModelBox(this);
		left_finger1.setRotationPoint(-3.0F, 0.0049F, 0.3079F);
		left_hand.addChild(left_finger1);
		setRotationAngle(left_finger1, -0.7216F, 0.2324F, -0.2F);
		left_finger1.setTextureOffset(111, 121).addBox(0.2215F, -3.2106F, -9.8849F, 0.0F, 7.0F, 12.0F, 0.0F, false);

		left_finger2 = new AdvancedModelBox(this);
		left_finger2.setRotationPoint(-1.0F, 0.0049F, 0.3079F);
		left_hand.addChild(left_finger2);
		setRotationAngle(left_finger2, -0.7258F, 0.0F, 0.0F);
		left_finger2.setTextureOffset(0, 109).addBox(-0.2215F, -3.2106F, -9.8849F, 0.0F, 7.0F, 12.0F, 0.0F, false);

		right_arm = new AdvancedModelBox(this);
		right_arm.setRotationPoint(-13.2F, 10.0F, -17.0F);
		spine2.addChild(right_arm);
		setRotationAngle(right_arm, 0.3491F, 0.0F, 0.0F);
		right_arm.setTextureOffset(98, 0).addBox(0.0F, -4.0637F, -3.0436F, 6.0F, 20.0F, 5.0F, 0.0F, true);

		right_front_arm = new AdvancedModelBox(this);
		right_front_arm.setRotationPoint(4.0F, 15.2977F, -1.9036F);
		right_arm.addChild(right_front_arm);
		setRotationAngle(right_front_arm, -0.829F, 0.0F, 0.0F);
		right_front_arm.setTextureOffset(98, 50).addBox(-3.0F, -0.1465F, -2.2077F, 6.0F, 11.0F, 5.0F, 0.0F, true);

		right_hand = new AdvancedModelBox(this);
		right_hand.setRotationPoint(-1.0F, 10.1551F, -0.5133F);
		right_front_arm.addChild(right_hand);
		setRotationAngle(right_hand, 1.6144F, 0.0F, 0.0F);


		right_finger1 = new AdvancedModelBox(this);
		right_finger1.setRotationPoint(-1.0F, 0.0049F, 0.3079F);
		right_hand.addChild(right_finger1);
		setRotationAngle(right_finger1, -0.7216F, 0.2324F, -0.2F);
		right_finger1.setTextureOffset(0, 109).addBox(0.2215F, -3.2106F, -9.8849F, 0.0F, 7.0F, 12.0F, 0.0F, true);

		right_finger2 = new AdvancedModelBox(this);
		right_finger2.setRotationPoint(1.0F, 0.0049F, 0.3079F);
		right_hand.addChild(right_finger2);
		setRotationAngle(right_finger2, -0.7258F, 0.0F, 0.0F);
		right_finger2.setTextureOffset(0, 109).addBox(0.2215F, -3.2106F, -9.8849F, 0.0F, 7.0F, 12.0F, 0.0F, true);

		right_finger3 = new AdvancedModelBox(this);
		right_finger3.setRotationPoint(3.0F, 0.0049F, 0.3079F);
		right_hand.addChild(right_finger3);
		setRotationAngle(right_finger3, -0.7216F, -0.2324F, 0.2F);
		right_finger3.setTextureOffset(111, 121).addBox(-0.2215F, -3.2106F, -9.8849F, 0.0F, 7.0F, 12.0F, 0.0F, true);

		legs = new AdvancedModelBox(this);
		legs.setRotationPoint(0.0F, 4.0F, 7.0F);
		mid_pivot.addChild(legs);


		left_leg = new AdvancedModelBox(this);
		left_leg.setRotationPoint(15.0F, 0.0F, 6.0F);
		legs.addChild(left_leg);
		setRotationAngle(left_leg, -0.3927F, 0.0F, 0.0F);
		left_leg.setTextureOffset(55, 197).addBox(-6.0F, -2.0261F, -4.1809F, 8.0F, 34.0F, 6.0F, 0.0F, false);

		left_deco1 = new AdvancedModelBox(this);
		left_deco1.setRotationPoint(2.0F, 10.9739F, -8.1809F);
		left_leg.addChild(left_deco1);
		setRotationAngle(left_deco1, 0.0959F, -0.4349F, -0.0329F);
		left_deco1.setTextureOffset(0, 0).addBox(-3.0F, -8.0F, 1.0F, 8.0F, 28.0F, 6.0F, 0.0F, false);

		left_front_leg = new AdvancedModelBox(this);
		left_front_leg.setRotationPoint(-2.0F, 32.9739F, -2.1809F);
		left_leg.addChild(left_front_leg);
		setRotationAngle(left_front_leg, 1.0908F, 0.0F, 0.0F);
		left_front_leg.setTextureOffset(0, 44).addBox(-5.0F, -2.5649F, -1.6913F, 8.0F, 24.0F, 5.0F, 0.0F, false);

		left_ankel_joint = new AdvancedModelBox(this);
		left_ankel_joint.setRotationPoint(-1.0F, 20.6148F, 1.661F);
		left_front_leg.addChild(left_ankel_joint);


		left_mini_bone = new AdvancedModelBox(this);
		left_mini_bone.setRotationPoint(-1.0F, 11.0F, -6.0F);
		left_ankel_joint.addChild(left_mini_bone);
		setRotationAngle(left_mini_bone, -0.3928F, -0.0035F, 0.0001F);
		left_mini_bone.setTextureOffset(29, 0).addBox(0.0209F, -3.1113F, 0.0913F, 0.0F, 6.0F, 3.0F, 0.0F, false);

		left_deco2 = new AdvancedModelBox(this);
		left_deco2.setRotationPoint(0.0F, 6.0F, -4.9602F);
		left_ankel_joint.addChild(left_deco2);
		setRotationAngle(left_deco2, 0.5236F, 0.0F, 0.0F);
		left_deco2.setTextureOffset(197, 193).addBox(-2.0F, -2.7487F, 0.1981F, 4.0F, 6.0F, 18.0F, 0.0F, false);

		left_deco3 = new AdvancedModelBox(this);
		left_deco3.setRotationPoint(-2.0F, 9.0F, -7.0F);
		left_ankel_joint.addChild(left_deco3);
		setRotationAngle(left_deco3, -0.3927F, 0.0F, 0.0F);
		left_deco3.setTextureOffset(33, 45).addBox(-0.2F, -11.7017F, -4.0722F, 4.0F, 13.0F, 5.0F, 0.0F, false);

		left_ankel = new AdvancedModelBox(this);
		left_ankel.setRotationPoint(0.0F, -1.0F, 0.0F);
		left_ankel_joint.addChild(left_ankel);
		setRotationAngle(left_ankel, -0.5672F, 0.0F, 0.0F);
		left_ankel.setTextureOffset(69, 113).addBox(-3.0F, -0.2489F, -2.0393F, 6.0F, 15.0F, 5.0F, 0.0F, false);

		left_foot = new AdvancedModelBox(this);
		left_foot.setRotationPoint(0.0F, 12.0F, -8.0F);
		left_ankel_joint.addChild(left_foot);


		left_toe = new AdvancedModelBox(this);
		left_toe.setRotationPoint(0.0F, -1.8218F, -0.2377F);
		left_foot.addChild(left_toe);
		setRotationAngle(left_toe, -0.6981F, 0.0F, 0.0F);
		left_toe.setTextureOffset(71, 50).addBox(0.0F, 0.2465F, -9.1823F, 0.0F, 7.0F, 12.0F, 0.0F, false);

		left_toe2 = new AdvancedModelBox(this);
		left_toe2.setRotationPoint(-2.0F, -1.8218F, -0.2377F);
		left_foot.addChild(left_toe2);
		setRotationAngle(left_toe2, -0.7216F, 0.2324F, -0.2F);
		left_toe2.setTextureOffset(71, 50).addBox(0.0F, 0.2465F, -9.1823F, 0.0F, 7.0F, 12.0F, 0.0F, false);

		left_toe3 = new AdvancedModelBox(this);
		left_toe3.setRotationPoint(2.0F, -1.8218F, -0.2377F);
		left_foot.addChild(left_toe3);
		setRotationAngle(left_toe3, -0.7216F, -0.2324F, 0.2F);
		left_toe3.setTextureOffset(71, 50).addBox(0.0F, 0.2465F, -9.1823F, 0.0F, 7.0F, 12.0F, 0.0F, false);

		right_leg = new AdvancedModelBox(this);
		right_leg.setRotationPoint(-15.0F, 0.0F, 6.0F);
		legs.addChild(right_leg);
		setRotationAngle(right_leg, -0.3927F, 0.0F, 0.0F);
		right_leg.setTextureOffset(55, 197).addBox(-2.0F, -2.0261F, -4.1809F, 8.0F, 34.0F, 6.0F, 0.0F, true);

		right_deco1 = new AdvancedModelBox(this);
		right_deco1.setRotationPoint(-2.0F, 10.9739F, -8.1809F);
		right_leg.addChild(right_deco1);
		setRotationAngle(right_deco1, 0.0959F, 0.4349F, 0.0329F);
		right_deco1.setTextureOffset(0, 0).addBox(-5.0F, -8.0F, 1.0F, 8.0F, 28.0F, 6.0F, 0.0F, true);

		right_front_leg = new AdvancedModelBox(this);
		right_front_leg.setRotationPoint(2.0F, 32.9739F, -2.1809F);
		right_leg.addChild(right_front_leg);
		setRotationAngle(right_front_leg, 1.0908F, 0.0F, 0.0F);
		right_front_leg.setTextureOffset(0, 44).addBox(-3.0F, -2.5649F, -1.6913F, 8.0F, 24.0F, 5.0F, 0.0F, true);

		right_ankel_joint = new AdvancedModelBox(this);
		right_ankel_joint.setRotationPoint(1.0F, 20.6148F, 1.661F);
		right_front_leg.addChild(right_ankel_joint);


		right_mini_bone = new AdvancedModelBox(this);
		right_mini_bone.setRotationPoint(1.0F, 11.0F, -6.0F);
		right_ankel_joint.addChild(right_mini_bone);
		setRotationAngle(right_mini_bone, -0.3928F, 0.0035F, -0.0001F);
		right_mini_bone.setTextureOffset(29, 0).addBox(-0.0209F, -3.1113F, 0.0913F, 0.0F, 6.0F, 3.0F, 0.0F, true);

		right_deco2 = new AdvancedModelBox(this);
		right_deco2.setRotationPoint(0.0F, 6.0F, -4.9602F);
		right_ankel_joint.addChild(right_deco2);
		setRotationAngle(right_deco2, 0.5236F, 0.0F, 0.0F);
		right_deco2.setTextureOffset(197, 193).addBox(-2.0F, -2.7487F, 0.1981F, 4.0F, 6.0F, 18.0F, 0.0F, true);

		right_deco3 = new AdvancedModelBox(this);
		right_deco3.setRotationPoint(2.0F, 9.0F, -7.0F);
		right_ankel_joint.addChild(right_deco3);
		setRotationAngle(right_deco3, -0.3927F, 0.0F, 0.0F);
		right_deco3.setTextureOffset(33, 45).addBox(-3.8F, -11.7017F, -4.0722F, 4.0F, 13.0F, 5.0F, 0.0F, true);

		right_ankel = new AdvancedModelBox(this);
		right_ankel.setRotationPoint(0.0F, -1.0F, 0.0F);
		right_ankel_joint.addChild(right_ankel);
		setRotationAngle(right_ankel, -0.5672F, 0.0F, 0.0F);
		right_ankel.setTextureOffset(69, 113).addBox(-3.0F, -0.2489F, -2.0393F, 6.0F, 15.0F, 5.0F, 0.0F, true);

		right_foot = new AdvancedModelBox(this);
		right_foot.setRotationPoint(0.0F, 12.0F, -8.0F);
		right_ankel_joint.addChild(right_foot);


		right_toe = new AdvancedModelBox(this);
		right_toe.setRotationPoint(0.0F, -1.8218F, -0.2377F);
		right_foot.addChild(right_toe);
		setRotationAngle(right_toe, -0.6981F, 0.0F, 0.0F);
		right_toe.setTextureOffset(71, 50).addBox(0.0F, 0.2465F, -9.1823F, 0.0F, 7.0F, 12.0F, 0.0F, true);

		right_toe2 = new AdvancedModelBox(this);
		right_toe2.setRotationPoint(2.0F, -1.8218F, -0.2377F);
		right_foot.addChild(right_toe2);
		setRotationAngle(right_toe2, -0.7216F, -0.2324F, 0.2F);
		right_toe2.setTextureOffset(71, 50).addBox(0.0F, 0.2465F, -9.1823F, 0.0F, 7.0F, 12.0F, 0.0F, true);

		right_toe3 = new AdvancedModelBox(this);
		right_toe3.setRotationPoint(-2.0F, -1.8218F, -0.2377F);
		right_foot.addChild(right_toe3);
		setRotationAngle(right_toe3, -0.7216F, 0.2324F, -0.2F);
		right_toe3.setTextureOffset(71, 50).addBox(0.0F, 0.2465F, -9.1823F, 0.0F, 7.0F, 12.0F, 0.0F, true);
		animator = ModelAnimator.create();
		this.updateDefaultPose();
	}


	@Override
	public Iterable<AdvancedModelBox> getAllParts() {
		return ImmutableList.of(
				root,mid_pivot,pelvis,tail1,tail2,tail3,tail4,spine1,spine2,neck1,neck2,head,jaw,left_arm,left_front_arm,left_hand,left_finger1,left_finger2,left_finger3,right_arm,right_hand,right_finger1,right_finger2,right_finger3,
				right_front_arm,legs,left_leg,left_front_leg,left_ankel,left_ankel_joint,left_foot,left_toe,left_toe2,left_toe3,right_leg,right_front_leg,right_ankel,right_ankel_joint,right_foot,right_toe,right_toe2,right_toe3);
	}

	@Override
	public Iterable<BasicModelPart> parts() {
		return ImmutableList.of(root);
	}

	public void animate(Ancient_Remnant_Entity entity, float f, float f1, float f2, float f3, float f4) {
		this.resetToDefaultPose();
		animator.update(entity);
		animator.setAnimation(Ancient_Remnant_Entity.REMNANT_BITE1);
		animator.startKeyframe(15);
		animator.rotate(mid_pivot, (float) Math.toRadians(-2.5F), (float) Math.toRadians(2.5F), (float) Math.toRadians(2.5F));
		animator.move(mid_pivot, 0,0.5F, 2);
		animator.rotate(pelvis, (float) Math.toRadians(-22.5F),(float) Math.toRadians(17.5F), 0);
		animator.rotate(tail1, (float) Math.toRadians(5F),0, 0);
		animator.rotate(tail2, (float) Math.toRadians(10F),0, 0);
		animator.rotate(tail3, (float) Math.toRadians(17.5F),(float) Math.toRadians(-1.5F), (float) Math.toRadians(1.5F));
		animator.rotate(tail4, (float) Math.toRadians(25F),0, 0);
		animator.rotate(spine1, (float) Math.toRadians(5F),(float) Math.toRadians(15F), 0);
		animator.rotate(spine2, (float) Math.toRadians(15F),(float) Math.toRadians(22.5F), (float) Math.toRadians(10F));
		animator.rotate(neck1, (float) Math.toRadians(-7.5F),(float) Math.toRadians(7.5F), (float) Math.toRadians(-10F));
		animator.rotate(neck2, (float) Math.toRadians(-27.5F),(float) Math.toRadians(-32.5F), (float) Math.toRadians(5F));
		animator.rotate(head, (float) Math.toRadians(-22.5F),(float) Math.toRadians(-12.5F),0);
		animator.rotate(jaw, (float) Math.toRadians(87.5F),0,0);
		animator.rotate(right_leg, 0,0,(float) Math.toRadians(5F));
		animator.rotate(right_front_leg, 0,0,(float) Math.toRadians(-7.5F));
		animator.rotate(right_ankel_joint, (float) Math.toRadians(-7.5F),0,0);
		animator.rotate(right_foot, (float) Math.toRadians(10F),0,0);
		animator.move(left_leg, 0,-5F,-13F);
		animator.rotate(left_leg, 0,0,(float) Math.toRadians(-5F));
		animator.rotate(left_front_leg, (float) Math.toRadians(-22.5F),0,0);
		animator.rotate(left_ankel_joint, (float) Math.toRadians(7.5F),0,0);
		animator.rotate(left_foot, (float) Math.toRadians(15F),0,0);
		animator.endKeyframe();
		animator.setStaticKeyframe(10);

		animator.startKeyframe(4);
		animator.rotate(mid_pivot, (float) Math.toRadians(62.5F), (float) Math.toRadians(-60F), (float) Math.toRadians(-45F));
		animator.move(mid_pivot, 0, 17, 2);
		animator.rotate(pelvis, (float) Math.toRadians(-22.5F),(float) Math.toRadians(7.5F), (float) Math.toRadians(7.5F));
		animator.rotate(tail1, (float) Math.toRadians(15F),(float) Math.toRadians(-27.5F), (float) Math.toRadians(-2.5F));
		animator.rotate(tail2, (float) Math.toRadians(27.5F),(float) Math.toRadians(-37.5F), (float) Math.toRadians(-12.5F));
		animator.rotate(tail3, (float) Math.toRadians(32.5F),(float) Math.toRadians(-35F), (float) Math.toRadians(-7.5F));
		animator.rotate(tail4, (float) Math.toRadians(35F),(float) Math.toRadians(-30F), (float) Math.toRadians(-2.5F));
		animator.rotate(spine1, (float) Math.toRadians(17.5F),(float) Math.toRadians(-2.5F), (float) Math.toRadians(-5F));
		animator.rotate(spine2, (float) Math.toRadians(20F),(float) Math.toRadians(-20F), (float) Math.toRadians(-17.5F));
		animator.rotate(neck1, (float) Math.toRadians(5F),(float) Math.toRadians(-37.5F), (float) Math.toRadians(5F));
		animator.rotate(neck2, (float) Math.toRadians(37.5F),(float) Math.toRadians(-37.5F), (float) Math.toRadians(2.5F));
		animator.rotate(head, (float) Math.toRadians(-15F),(float) Math.toRadians(-47.5F),(float) Math.toRadians(2.5F));
		animator.rotate(jaw, (float) Math.toRadians(5F),0,0);
		animator.rotate(right_arm, (float) Math.toRadians(-7.5F),(float) Math.toRadians(-15F),(float) Math.toRadians(30F));
		animator.rotate(right_front_arm, (float) Math.toRadians(-70F),0,0);
		animator.rotate(right_hand, (float) Math.toRadians(72.5F),0,0);
		animator.rotate(left_arm, 0,0,(float) Math.toRadians(30F));
		animator.rotate(left_front_arm, (float) Math.toRadians(-17.5F),0,0);
		animator.rotate(left_hand, (float) Math.toRadians(70F),0,0);
		animator.move(right_leg, 0,1,2);
		animator.rotate(right_leg, (float) Math.toRadians(-52.5F),(float) Math.toRadians(10F),(float) Math.toRadians(27.5F));
		animator.rotate(right_front_leg, (float) Math.toRadians(47.5F),0,(float) Math.toRadians(-7.5F));
		animator.rotate(right_ankel_joint, (float) Math.toRadians(-22.5F),0,0);
		animator.rotate(right_foot, (float) Math.toRadians(5F),0,0);
		animator.move(left_leg, 0,-3F,0);
		animator.rotate(left_leg, (float) Math.toRadians(-45F),(float) Math.toRadians(-12.5F),(float) Math.toRadians(22.5F));
		animator.rotate(left_front_leg, (float) Math.toRadians(25F),0,0);
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-5F),0,0);
		animator.rotate(left_foot, (float) Math.toRadians(10F),0,(float) Math.toRadians(17.5F));
		animator.endKeyframe();
		animator.setStaticKeyframe(10);


		animator.startKeyframe(20);
		animator.rotate(mid_pivot, (float) Math.toRadians(27.5F), (float) Math.toRadians(-27.5F), (float) Math.toRadians(-20F));
		animator.move(mid_pivot, 0, 7, 2);
		animator.rotate(pelvis, (float) Math.toRadians(-10F),(float) Math.toRadians(2.5F), (float) Math.toRadians(5F));
		animator.rotate(tail1, (float) Math.toRadians(7.5F),(float) Math.toRadians(-12.5F), (float) Math.toRadians(-2.5F));
		animator.rotate(tail2, (float) Math.toRadians(12.5F),(float) Math.toRadians(-17.5F), (float) Math.toRadians(-5F));
		animator.rotate(tail3, (float) Math.toRadians(20F),(float) Math.toRadians(-15F), (float) Math.toRadians(-5F));
		animator.rotate(tail4, (float) Math.toRadians(27.5F),(float) Math.toRadians(-12.5F), (float) Math.toRadians(-2.5F));
		animator.rotate(spine1, (float) Math.toRadians(7.5F),0, (float) Math.toRadians(-2.5F));
		animator.rotate(spine2, (float) Math.toRadians(10F),(float) Math.toRadians(-7.5F), (float) Math.toRadians(-7.5F));
		animator.rotate(neck1, 0,(float) Math.toRadians(-17.5F), (float) Math.toRadians(2.5F));
		animator.rotate(neck2, (float) Math.toRadians(15F),(float) Math.toRadians(-17.5F), 0);
		animator.rotate(head, (float) Math.toRadians(-7.5F),(float) Math.toRadians(-20F),0);
		animator.rotate(jaw, (float) Math.toRadians(2.5F),0,0);
		animator.rotate(right_arm, (float) Math.toRadians(-2.5F),(float) Math.toRadians(-5F),(float) Math.toRadians(12.5F));
		animator.rotate(right_front_arm, (float) Math.toRadians(-30F),0,0);
		animator.rotate(right_hand, (float) Math.toRadians(30F),0,0);
		animator.rotate(left_arm, 0,0,(float) Math.toRadians(12.5F));
		animator.rotate(left_front_arm, (float) Math.toRadians(-7.5F),0,0);
		animator.rotate(left_hand, (float) Math.toRadians(30F),0,0);
		animator.move(right_leg, 0,-5,1);
		animator.rotate(right_leg, (float) Math.toRadians(-55F),(float) Math.toRadians(10F),(float) Math.toRadians(15F));
		animator.rotate(right_front_leg, (float) Math.toRadians(57.5F),0,(float) Math.toRadians(-5F));
		animator.rotate(right_ankel_joint, (float) Math.toRadians(-25F),0,0);
		animator.rotate(right_foot, (float) Math.toRadians(37.5F),0,0);
		animator.move(left_leg, 0,4F,0);
		animator.rotate(left_leg, (float) Math.toRadians(-20F),(float) Math.toRadians(5F),(float) Math.toRadians(17.5F));
		animator.rotate(left_front_leg, (float) Math.toRadians(10F),0,0);
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-5F),0,0);
		animator.rotate(left_foot, (float) Math.toRadians(-5F),0,(float) Math.toRadians(7.5F));
		animator.endKeyframe();

		animator.resetKeyframe(10);

		animator.setAnimation(Ancient_Remnant_Entity.REMNANT_BITE2);
		animator.startKeyframe(13);
		animator.rotate(mid_pivot, (float) Math.toRadians(-2.5F), (float) Math.toRadians(-2.5F), (float) Math.toRadians(-2.5F));
		animator.move(mid_pivot, 0,0.5F, 2);
		animator.rotate(pelvis, (float) Math.toRadians(-22.5F),(float) Math.toRadians(-17.5F), 0);
		animator.rotate(tail1, (float) Math.toRadians(5F),0, 0);
		animator.rotate(tail2, (float) Math.toRadians(10F),0, 0);
		animator.rotate(tail3, (float) Math.toRadians(17.5F),(float) Math.toRadians(1.5F), (float) Math.toRadians(-1.5F));
		animator.rotate(tail4, (float) Math.toRadians(25F),0, 0);
		animator.rotate(spine1, (float) Math.toRadians(5F),(float) Math.toRadians(-15F), 0);
		animator.rotate(spine2, (float) Math.toRadians(15F),(float) Math.toRadians(-22.5F), (float) Math.toRadians(-10F));
		animator.rotate(neck1, (float) Math.toRadians(-7.5F),(float) Math.toRadians(-7.5F), (float) Math.toRadians(10F));
		animator.rotate(neck2, (float) Math.toRadians(-27.5F),(float) Math.toRadians(32.5F), (float) Math.toRadians(-5F));
		animator.rotate(head, (float) Math.toRadians(-22.5F),(float) Math.toRadians(12.5F),0);
		animator.rotate(jaw, (float) Math.toRadians(87.5F),0,0);
		animator.rotate(left_leg, 0,0,(float) Math.toRadians(-5F));
		animator.rotate(left_front_leg, 0,0,(float) Math.toRadians(7.5F));
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-7.5F),0,0);
		animator.rotate(left_foot, (float) Math.toRadians(10F),0,0);
		animator.move(right_leg, 0,-5F,-13F);
		animator.rotate(right_leg, 0,0,(float) Math.toRadians(5F));
		animator.rotate(right_front_leg, (float) Math.toRadians(-22.5F),0,0);
		animator.rotate(right_ankel_joint, (float) Math.toRadians(7.5F),0,0);
		animator.rotate(right_foot, (float) Math.toRadians(15F),0,0);
		animator.endKeyframe();

		animator.setStaticKeyframe(8);

		animator.startKeyframe(4);
		animator.rotate(mid_pivot, (float) Math.toRadians(62.5F), (float) Math.toRadians(60F), (float) Math.toRadians(45F));
		animator.move(mid_pivot, 0, 17, 2);
		animator.rotate(pelvis, (float) Math.toRadians(-22.5F),(float) Math.toRadians(-7.5F), (float) Math.toRadians(-7.5F));
		animator.rotate(tail1, (float) Math.toRadians(15F),(float) Math.toRadians(27.5F), (float) Math.toRadians(2.5F));
		animator.rotate(tail2, (float) Math.toRadians(27.5F),(float) Math.toRadians(37.5F), (float) Math.toRadians(12.5F));
		animator.rotate(tail3, (float) Math.toRadians(32.5F),(float) Math.toRadians(35F), (float) Math.toRadians(7.5F));
		animator.rotate(tail4, (float) Math.toRadians(35F),(float) Math.toRadians(30F), (float) Math.toRadians(2.5F));
		animator.rotate(spine1, (float) Math.toRadians(17.5F),(float) Math.toRadians(2.5F), (float) Math.toRadians(5F));
		animator.rotate(spine2, (float) Math.toRadians(20F),(float) Math.toRadians(20F), (float) Math.toRadians(17.5F));
		animator.rotate(neck1, (float) Math.toRadians(5F),(float) Math.toRadians(37.5F), (float) Math.toRadians(-5F));
		animator.rotate(neck2, (float) Math.toRadians(37.5F),(float) Math.toRadians(37.5F), (float) Math.toRadians(-2.5F));
		animator.rotate(head, (float) Math.toRadians(-15F),(float) Math.toRadians(47.5F),(float) Math.toRadians(-2.5F));
		animator.rotate(jaw, (float) Math.toRadians(5F),0,0);
		animator.rotate(left_arm, (float) Math.toRadians(-7.5F),(float) Math.toRadians(15F),(float) Math.toRadians(-30F));
		animator.rotate(left_front_arm, (float) Math.toRadians(-70F),0,0);
		animator.rotate(left_hand, (float) Math.toRadians(72.5F),0,0);
		animator.rotate(right_arm, 0,0,(float) Math.toRadians(-30F));
		animator.rotate(right_front_arm, (float) Math.toRadians(-17.5F),0,0);
		animator.rotate(right_hand, (float) Math.toRadians(70F),0,0);
		animator.move(left_leg, 0,1,2);
		animator.rotate(left_leg, (float) Math.toRadians(-52.5F),(float) Math.toRadians(-10F),(float) Math.toRadians(-27.5F));
		animator.rotate(left_front_leg, (float) Math.toRadians(47.5F),0,(float) Math.toRadians(7.5F));
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-22.5F),0,0);
		animator.rotate(left_foot, (float) Math.toRadians(5F),0,0);
		animator.move(right_leg, 0,-3F,0);
		animator.rotate(right_leg, (float) Math.toRadians(-45F),(float) Math.toRadians(12.5F),(float) Math.toRadians(-22.5F));
		animator.rotate(right_front_leg, (float) Math.toRadians(25F),0,0);
		animator.rotate(right_ankel_joint, (float) Math.toRadians(-5F),0,0);
		animator.rotate(right_foot, (float) Math.toRadians(10F),0,(float) Math.toRadians(-17.5F));
		animator.endKeyframe();
		animator.setStaticKeyframe(10);


		animator.startKeyframe(20);
		animator.rotate(mid_pivot, (float) Math.toRadians(27.5F), (float) Math.toRadians(27.5F), (float) Math.toRadians(20F));
		animator.move(mid_pivot, 0, 7, 2);
		animator.rotate(pelvis, (float) Math.toRadians(-10F),(float) Math.toRadians(-2.5F), (float) Math.toRadians(-5F));
		animator.rotate(tail1, (float) Math.toRadians(7.5F),(float) Math.toRadians(12.5F), (float) Math.toRadians(2.5F));
		animator.rotate(tail2, (float) Math.toRadians(12.5F),(float) Math.toRadians(17.5F), (float) Math.toRadians(5F));
		animator.rotate(tail3, (float) Math.toRadians(20F),(float) Math.toRadians(15F), (float) Math.toRadians(5F));
		animator.rotate(tail4, (float) Math.toRadians(27.5F),(float) Math.toRadians(12.5F), (float) Math.toRadians(2.5F));
		animator.rotate(spine1, (float) Math.toRadians(7.5F),0, (float) Math.toRadians(2.5F));
		animator.rotate(spine2, (float) Math.toRadians(10F),(float) Math.toRadians(7.5F), (float) Math.toRadians(7.5F));
		animator.rotate(neck1, 0,(float) Math.toRadians(17.5F), (float) Math.toRadians(-2.5F));
		animator.rotate(neck2, (float) Math.toRadians(15F),(float) Math.toRadians(17.5F), 0);
		animator.rotate(head, (float) Math.toRadians(-7.5F),(float) Math.toRadians(20F),0);
		animator.rotate(jaw, (float) Math.toRadians(2.5F),0,0);
		animator.rotate(left_arm, (float) Math.toRadians(-2.5F),(float) Math.toRadians(5F),(float) Math.toRadians(-12.5F));
		animator.rotate(left_front_arm, (float) Math.toRadians(-30F),0,0);
		animator.rotate(left_hand, (float) Math.toRadians(30F),0,0);
		animator.rotate(right_arm, 0,0,(float) Math.toRadians(-12.5F));
		animator.rotate(right_front_arm, (float) Math.toRadians(-7.5F),0,0);
		animator.rotate(right_hand, (float) Math.toRadians(30F),0,0);
		animator.move(left_leg, 0,-5,1);
		animator.rotate(left_leg, (float) Math.toRadians(-55F),(float) Math.toRadians(-10F),(float) Math.toRadians(-15F));
		animator.rotate(left_front_leg, (float) Math.toRadians(57.5F),0,(float) Math.toRadians(5F));
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-25F),0,0);
		animator.rotate(left_foot, (float) Math.toRadians(37.5F),0,0);
		animator.move(right_leg, 0,4F,0);
		animator.rotate(right_leg, (float) Math.toRadians(-20F),(float) Math.toRadians(-5F),(float) Math.toRadians(-17.5F));
		animator.rotate(right_front_leg, (float) Math.toRadians(10F),0,0);
		animator.rotate(right_ankel_joint, (float) Math.toRadians(-5F),0,0);
		animator.rotate(right_foot, (float) Math.toRadians(-5F),0,(float) Math.toRadians(-7.5F));
		animator.endKeyframe();

		animator.resetKeyframe(10);

		animator.setAnimation(Ancient_Remnant_Entity.REMNANT_CHARGE_PREPARE);
		animator.startKeyframe(13);
		animator.move(root, 0,5, 2.5F);
		animator.rotate(pelvis, (float) Math.toRadians(10F),(float) Math.toRadians(-5F), 0);
		animator.rotate(tail1, (float) Math.toRadians(-17.5F),0, 0);
		animator.rotate(tail2, (float) Math.toRadians(2.5F),(float) Math.toRadians(-5F), 0);
		animator.rotate(tail3, (float) Math.toRadians(15F),(float) Math.toRadians(-5F), 0);
		animator.rotate(tail4, (float) Math.toRadians(25F),(float) Math.toRadians(-5F), 0);
		animator.rotate(spine1, (float) Math.toRadians(2.5F),(float) Math.toRadians(2.5F), 0);
		animator.rotate(spine2, (float) Math.toRadians(2.5F),(float) Math.toRadians(2.5F), 0);
		animator.rotate(neck1, (float) Math.toRadians(40F),0,0);
		animator.rotate(neck2, (float) Math.toRadians(-10F),0, 0);
		animator.rotate(head, (float) Math.toRadians(-40F),0,0);
		animator.rotate(jaw, (float) Math.toRadians(15F),0,0);
		animator.rotate(right_arm, (float) Math.toRadians(-15F),0,0);
		animator.rotate(right_front_arm, (float) Math.toRadians(-3.3F),0,0);
		animator.rotate(left_arm, (float) Math.toRadians(-15F),0,0);
		animator.rotate(left_front_arm, (float) Math.toRadians(3.3F),0,0);
		animator.rotate(right_leg, (float) Math.toRadians(-47.5F),0,0);
		animator.rotate(right_front_leg,(float) Math.toRadians(35F),0,0);
		animator.rotate(right_ankel_joint, (float) Math.toRadians(7.5F),0,0);
		animator.rotate(right_foot, (float) Math.toRadians(17.5F),0,0);
		animator.move(right_leg, 0,-4F,0);
		animator.rotate(left_leg, (float) Math.toRadians(-7.5F),0,0);
		animator.rotate(left_front_leg, (float) Math.toRadians(20F),0,0);
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-10F),0,0);
		animator.endKeyframe();

		animator.startKeyframe(2);
		animator.move(root, 0,7, 2);
		animator.rotate(pelvis, (float) Math.toRadians(10F),(float) Math.toRadians(-5F), 0);
		animator.rotate(tail1, (float) Math.toRadians(-17.5F),0, 0);
		animator.rotate(tail2, (float) Math.toRadians(2.5F),(float) Math.toRadians(-5F), 0);
		animator.rotate(tail3, (float) Math.toRadians(15F),(float) Math.toRadians(-5F), 0);
		animator.rotate(tail4, (float) Math.toRadians(25F),(float) Math.toRadians(-5F), 0);
		animator.rotate(spine1, (float) Math.toRadians(2.5F),(float) Math.toRadians(2.5F), 0);
		animator.rotate(spine2, (float) Math.toRadians(2.5F),(float) Math.toRadians(2.5F), 0);
		animator.rotate(neck1, (float) Math.toRadians(37.5F),0,0);
		animator.rotate(neck2, (float) Math.toRadians(-10F),0, 0);
		animator.rotate(head, (float) Math.toRadians(-37.5F),0,0);
		animator.rotate(jaw, (float) Math.toRadians(17.5F),0,0);
		animator.rotate(right_arm, (float) Math.toRadians(-10F),0,0);
		animator.rotate(right_front_arm, (float) Math.toRadians(7.5F),0,0);
		animator.rotate(right_hand, (float) Math.toRadians(2.5F),0,0);
		animator.rotate(left_arm, (float) Math.toRadians(-10F),0,0);
		animator.rotate(left_front_arm, (float) Math.toRadians(7.5F),0,0);
		animator.rotate(left_hand, (float) Math.toRadians(2.5F),0,0);
		animator.rotate(right_leg, (float) Math.toRadians(-10F),0,0);
		animator.rotate(right_front_leg,(float) Math.toRadians(20F),0,0);
		animator.rotate(right_ankel_joint, (float) Math.toRadians(-52.5F),0,0);
		animator.rotate(right_foot, (float) Math.toRadians(42.5F),0,0);
		animator.move(right_leg, 0,1F,5f);
		animator.rotate(left_leg, (float) Math.toRadians(-7.5F),0,0);
		animator.rotate(left_front_leg, (float) Math.toRadians(25F),0,0);
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-12.5F),0,0);
		animator.endKeyframe();

		animator.startKeyframe(2);
		animator.move(root, 0,5, 2);
		animator.rotate(pelvis, (float) Math.toRadians(5F),(float) Math.toRadians(-3.5F), 0);
		animator.rotate(tail1, (float) Math.toRadians(-10F),0, 0);
		animator.rotate(tail2, (float) Math.toRadians(1.5F),(float) Math.toRadians(-2.5F), 0);
		animator.rotate(tail3, (float) Math.toRadians(15F),(float) Math.toRadians(-2.5F), 0);
		animator.rotate(tail4, (float) Math.toRadians(22.5F),(float) Math.toRadians(-2.5F), 0);
		animator.rotate(spine1, (float) Math.toRadians(2.5F),(float) Math.toRadians(2.5F), 0);
		animator.rotate(spine2, (float) Math.toRadians(2.5F),(float) Math.toRadians(2.5F), 0);
		animator.rotate(neck1, (float) Math.toRadians(25F),0,0);
		animator.rotate(neck2, (float) Math.toRadians(-7.5F),0, 0);
		animator.rotate(head, (float) Math.toRadians(-25F),0,0);
		animator.rotate(jaw, (float) Math.toRadians(12.5F),0,0);
		animator.rotate(right_arm, (float) Math.toRadians(5F),0,0);
		animator.rotate(right_front_arm, (float) Math.toRadians(32.5F),0,0);
		animator.rotate(right_hand, (float) Math.toRadians(7.5F),0,0);
		animator.rotate(left_arm, (float) Math.toRadians(5F),0,0);
		animator.rotate(left_front_arm, (float) Math.toRadians(32.5F),0,0);
		animator.rotate(left_hand, (float) Math.toRadians(7.5F),0,0);
		animator.rotate(right_leg, (float) Math.toRadians(-5F),0,0);
		animator.move(right_leg, 0,1F,6f);
		animator.rotate(right_front_leg,(float) Math.toRadians(5F),0,0);
		animator.rotate(right_ankel_joint, (float) Math.toRadians(-47.5F),0,0);
		animator.rotate(right_foot, (float) Math.toRadians(47.5F),0,0);
		animator.rotate(left_leg, (float) Math.toRadians(-2.5F),0,0);
		animator.rotate(left_front_leg, (float) Math.toRadians(15F),0,0);
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-7.5F),0,0);
		animator.endKeyframe();

		animator.startKeyframe(10);
		animator.move(root, 0,0, 2);
		animator.rotate(tail3, (float) Math.toRadians(12.5F),0, 0);
		animator.rotate(tail4, (float) Math.toRadians(20F),0, 0);
		animator.endKeyframe();

		animator.startKeyframe(10);
		animator.move(root, 0,3, 3);
		animator.rotate(pelvis, (float) Math.toRadians(10F),(float) Math.toRadians(5F), 0);
		animator.rotate(tail1, (float) Math.toRadians(-17.5F),0, 0);
		animator.rotate(tail2, (float) Math.toRadians(2.5F),(float) Math.toRadians(5F), 0);
		animator.rotate(tail3, (float) Math.toRadians(15F),(float) Math.toRadians(5F), 0);
		animator.rotate(tail4, (float) Math.toRadians(25F),(float) Math.toRadians(5F), 0);
		animator.rotate(spine1, (float) Math.toRadians(2.5F),(float) Math.toRadians(2.5F), 0);
		animator.rotate(spine2, (float) Math.toRadians(2.5F),(float) Math.toRadians(2.5F), 0);
		animator.rotate(neck1, (float) Math.toRadians(35F),0,0);
		animator.rotate(neck2, (float) Math.toRadians(-7.5F),0, 0);
		animator.rotate(head, (float) Math.toRadians(-35F),0,0);
		animator.rotate(jaw, (float) Math.toRadians(10F),0,0);
		animator.rotate(right_arm, (float) Math.toRadians(-17.5F),0,0);
		animator.rotate(right_front_arm, (float) Math.toRadians(-12.5F),0,0);
		animator.rotate(right_hand, (float) Math.toRadians(-2.5F),0,0);
		animator.rotate(left_arm, (float) Math.toRadians(-17.5F),0,0);
		animator.rotate(left_front_arm, (float) Math.toRadians(-12.5F),0,0);
		animator.rotate(left_hand, (float) Math.toRadians(-2.5F),0,0);
		animator.rotate(right_leg, (float) Math.toRadians(-7.5F),0,0);
		animator.rotate(right_front_leg,(float) Math.toRadians(12.5F),0,0);
		animator.rotate(left_leg, (float) Math.toRadians(-35F),0,0);
		animator.move(left_leg, 0,-3.5f,0);
		animator.rotate(left_front_leg, (float) Math.toRadians(42.5F),0,0);
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-12.5F),0,0);
		animator.rotate(left_foot, (float) Math.toRadians(12.5F),0,0);
		animator.endKeyframe();

		animator.startKeyframe(2);
		animator.move(root, 0,5, 3);
		animator.rotate(pelvis, (float) Math.toRadians(10F),(float) Math.toRadians(5F), 0);
		animator.rotate(tail1, (float) Math.toRadians(-17.5F),0, 0);
		animator.rotate(tail2, (float) Math.toRadians(2.5F),(float) Math.toRadians(5F), 0);
		animator.rotate(tail3, (float) Math.toRadians(15F),(float) Math.toRadians(5F), 0);
		animator.rotate(tail4, (float) Math.toRadians(25F),(float) Math.toRadians(5F), 0);
		animator.rotate(spine1, (float) Math.toRadians(2.5F),(float) Math.toRadians(-2.5F), 0);
		animator.rotate(spine2, (float) Math.toRadians(2.5F),(float) Math.toRadians(-2.5F), 0);
		animator.rotate(neck1, (float) Math.toRadians(40F),0,0);
		animator.rotate(neck2, (float) Math.toRadians(-10F),0, 0);
		animator.rotate(head, (float) Math.toRadians(-40F),0,0);
		animator.rotate(jaw, (float) Math.toRadians(15F),0,0);
		animator.rotate(right_arm, (float) Math.toRadians(-15F),0,0);
		animator.rotate(right_front_arm, (float) Math.toRadians(-2.5F),0,0);
		animator.rotate(left_arm, (float) Math.toRadians(-15F),0,0);
		animator.rotate(left_front_arm, (float) Math.toRadians(-2.5F),0,0);
		animator.rotate(right_leg, (float) Math.toRadians(-7.5F),0,0);
		animator.rotate(right_front_leg,(float) Math.toRadians(20F),0,0);
		animator.rotate(left_leg, (float) Math.toRadians(-40F),0,0);
		animator.move(left_leg, 0,-4f,0);
		animator.rotate(left_front_leg, (float) Math.toRadians(47.5F),0,0);
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-17.5F),0,0);
		animator.rotate(left_foot, (float) Math.toRadians(17.5F),0,0);
		animator.endKeyframe();

		animator.startKeyframe(2);
		animator.move(root, 0,7, 2);
		animator.rotate(pelvis, (float) Math.toRadians(7.5F),(float) Math.toRadians(5F), 0);
		animator.rotate(tail1, (float) Math.toRadians(-15F),0, 0);
		animator.rotate(tail2, (float) Math.toRadians(2.5F),(float) Math.toRadians(5F), 0);
		animator.rotate(tail3, (float) Math.toRadians(15F),(float) Math.toRadians(5F), 0);
		animator.rotate(tail4, (float) Math.toRadians(25F),(float) Math.toRadians(5F), 0);
		animator.rotate(spine1, (float) Math.toRadians(2.5F),(float) Math.toRadians(-2.5F), 0);
		animator.rotate(spine2, (float) Math.toRadians(2.5F),(float) Math.toRadians(-2.5F), 0);
		animator.rotate(neck1, (float) Math.toRadians(37.5F),0,0);
		animator.rotate(neck2, (float) Math.toRadians(-10F),0, 0);
		animator.rotate(head, (float) Math.toRadians(-37.5F),0,0);
		animator.rotate(jaw, (float) Math.toRadians(17.5F),0,0);
		animator.rotate(right_arm, (float) Math.toRadians(-7.5F),0,0);
		animator.rotate(right_front_arm, (float) Math.toRadians(12.5F),0,0);
		animator.rotate(right_hand, (float) Math.toRadians(2.5F),0,0);
		animator.rotate(left_arm, (float) Math.toRadians(-7.5F),0,0);
		animator.rotate(left_front_arm, (float) Math.toRadians(12.5F),0,0);
		animator.rotate(left_hand, (float) Math.toRadians(2.5F),0,0);
		animator.rotate(right_leg, (float) Math.toRadians(-5F),0,0);
		animator.rotate(right_front_leg,(float) Math.toRadians(25F),0,0);
		animator.rotate(left_leg, (float) Math.toRadians(-10F),0,0);
		animator.move(left_leg, 0,1f,5f);
		animator.rotate(left_front_leg, (float) Math.toRadians(25F),0,0);
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-45F),0,0);
		animator.rotate(left_foot, (float) Math.toRadians(35F),0,0);
		animator.endKeyframe();

		animator.startKeyframe(2);
		animator.move(root, 0,5, 2);
		animator.rotate(pelvis, (float) Math.toRadians(5F),(float) Math.toRadians(3.3F), 0);
		animator.rotate(tail1, (float) Math.toRadians(-10F),0, 0);
		animator.rotate(tail2, (float) Math.toRadians(2.5F),(float) Math.toRadians(2.5F), 0);
		animator.rotate(tail3, (float) Math.toRadians(15F),(float) Math.toRadians(2.5F), 0);
		animator.rotate(tail4, (float) Math.toRadians(25F),(float) Math.toRadians(2.5F), 0);
		animator.rotate(spine1, (float) Math.toRadians(2.5F),(float) Math.toRadians(-2.5F), 0);
		animator.rotate(spine2, (float) Math.toRadians(2.5F),(float) Math.toRadians(-2.5F), 0);
		animator.rotate(neck1, (float) Math.toRadians(27.5F),0,0);
		animator.rotate(neck2, (float) Math.toRadians(-7.5F),0, 0);
		animator.rotate(head, (float) Math.toRadians(-27.5F),0,0);
		animator.rotate(jaw, (float) Math.toRadians(12.5F),0,0);
		animator.rotate(right_arm, (float) Math.toRadians(5F),0,0);
		animator.rotate(right_front_arm, (float) Math.toRadians(32.5F),0,0);
		animator.rotate(right_hand, (float) Math.toRadians(7.5F),0,0);
		animator.rotate(left_arm, (float) Math.toRadians(5F),0,0);
		animator.rotate(left_front_arm, (float) Math.toRadians(32.5F),0,0);
		animator.rotate(left_hand, (float) Math.toRadians(7.5F),0,0);
		animator.rotate(right_leg, (float) Math.toRadians(-3.5F),0,0);
		animator.rotate(right_front_leg,(float) Math.toRadians(20F),0,0);
		animator.rotate(right_foot,(float) Math.toRadians(-2.5F),0,0);
		animator.rotate(left_leg, (float) Math.toRadians(-7.5F),0,0);
		animator.move(left_leg, 0,1f,6f);
		animator.rotate(left_front_leg, (float) Math.toRadians(20F),(float) Math.toRadians(-2.5F),0);
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-45F),0,0);
		animator.rotate(left_foot, (float) Math.toRadians(35F),0,0);
		animator.endKeyframe();


		animator.startKeyframe(2);
		animator.move(root, 0,0, 2);
		animator.rotate(tail3, (float) Math.toRadians(12.5F),0, 0);
		animator.rotate(tail4, (float) Math.toRadians(20F),0, 0);
		animator.rotate(spine1, (float) Math.toRadians(2.5F),(float) Math.toRadians(-2.5F), 0);
		animator.rotate(spine2, (float) Math.toRadians(2.5F),(float) Math.toRadians(-2.5F), 0);
		animator.rotate(neck1, (float) Math.toRadians(27.5F),0,0);
		animator.rotate(neck2, (float) Math.toRadians(-7.5F),0, 0);
		animator.rotate(head, (float) Math.toRadians(-27.5F),0,0);
		animator.rotate(jaw, (float) Math.toRadians(12.5F),0,0);
		animator.rotate(right_arm, (float) Math.toRadians(5F),0,0);
		animator.rotate(right_front_arm, (float) Math.toRadians(32.5F),0,0);
		animator.rotate(right_hand, (float) Math.toRadians(7.5F),0,0);
		animator.rotate(left_arm, (float) Math.toRadians(5F),0,0);
		animator.rotate(left_front_arm, (float) Math.toRadians(32.5F),0,0);
		animator.rotate(left_hand, (float) Math.toRadians(7.5F),0,0);
		animator.rotate(right_leg, (float) Math.toRadians(-3.5F),0,0);
		animator.rotate(right_front_leg,(float) Math.toRadians(20F),0,0);
		animator.rotate(right_foot,(float) Math.toRadians(-2.5F),0,0);
		animator.rotate(left_leg, (float) Math.toRadians(-7.5F),0,0);
		animator.move(left_leg, 0,1f,6f);
		animator.rotate(left_front_leg, (float) Math.toRadians(20F),(float) Math.toRadians(-2.5F),0);
		animator.rotate(left_ankel_joint, (float) Math.toRadians(-45F),0,0);
		animator.rotate(left_foot, (float) Math.toRadians(35F),0,0);
		animator.endKeyframe();

		animator.startKeyframe(10);
		animator.move(root, 0,1, 2);
		animator.rotate(tail3, (float) Math.toRadians(12.5F),0, 0);
		animator.rotate(tail4, (float) Math.toRadians(20F),0, 0);
		animator.endKeyframe();

		animator.setStaticKeyframe(60);

		animator.resetKeyframe(10);


		animator.setAnimation(Ancient_Remnant_Entity.REMNANT_TAIL_ATTACK1);
		animator.startKeyframe(13);
		animator.rotate(mid_pivot, 0,(float) Math.toRadians(50F), 0);
		animator.move(mid_pivot, 2,17, 10);
		animator.rotate(tail1, (float) Math.toRadians(-22.5F),(float) Math.toRadians(50F), 0);
		animator.rotate(tail2, 0,(float) Math.toRadians(30F), 0);
		animator.rotate(tail3, (float) Math.toRadians(12.5F),(float) Math.toRadians(22.5F), (float) Math.toRadians(5F));
		animator.rotate(tail4, (float) Math.toRadians(22.5F),(float) Math.toRadians(27.5F), (float) Math.toRadians(5F));
		animator.rotate(spine1, 0,(float) Math.toRadians(-20F),0);
		animator.rotate(spine2, 0,(float) Math.toRadians(-15F),0);
		//animator.rotate(neck1, 0,(float) Math.toRadians(-15F),0);
		animator.rotate(neck2, 0,(float) Math.toRadians(-12.5F),0);
		animator.rotate(head, (float) Math.toRadians(7.5F),(float) Math.toRadians(-15F),(float) Math.toRadians(-12.5F));
		animator.rotate(jaw,(float) Math.toRadians(12.5F),0,0);
		animator.rotate(left_arm,0,0,(float) Math.toRadians(-10F));
		animator.rotate(left_front_arm,(float) Math.toRadians(17.5F),0,0);
		animator.rotate(right_arm,0,0,(float) Math.toRadians(-2.5F));
		animator.rotate(right_front_arm,(float) Math.toRadians(7.5F),0,0);
		animator.rotate(left_leg,(float) Math.toRadians(-15F),(float) Math.toRadians(-22.5F),(float) Math.toRadians(-22.5F));
		animator.move(left_leg,0,-6,0);
		animator.rotate(right_leg,(float) Math.toRadians(-30F),(float) Math.toRadians(22.5F),(float) Math.toRadians(7.5F));
		animator.move(right_leg,0,-4,0);
		animator.rotate(left_front_leg,(float) Math.toRadians(35F),(float) Math.toRadians(-17.5F),(float) Math.toRadians(5F));
		animator.rotate(left_ankel_joint,(float) Math.toRadians(-10F),0,0);
		animator.rotate(right_front_leg,(float) Math.toRadians(32.5F),0,0);
		animator.rotate(right_ankel_joint,0,0,(float) Math.toRadians(-12.5F));
		animator.endKeyframe();

		animator.startKeyframe(3);
		animator.rotate(mid_pivot, (float) Math.toRadians(-5F),(float) Math.toRadians(-100F), 0);
		animator.move(mid_pivot, 2,-5, 10);
		animator.rotate(tail1, (float) Math.toRadians(-35F),(float) Math.toRadians(-17.5F), (float) Math.toRadians(25F));
		animator.rotate(tail2, 0,(float) Math.toRadians(7.5F), 0);
		animator.rotate(tail3, (float) Math.toRadians(27.5F),(float) Math.toRadians(7.5F), (float) Math.toRadians(7.5F));
		animator.rotate(tail4, (float) Math.toRadians(27.5F),(float) Math.toRadians(2.5F), (float) Math.toRadians(2.5F));
		animator.rotate(spine1, (float) Math.toRadians(2.5F),(float) Math.toRadians(-7.5F),0);
		animator.rotate(spine2, (float) Math.toRadians(2.5F),(float) Math.toRadians(12.5F),(float) Math.toRadians(12.5F));
		//animator.rotate(neck1, 0,(float) Math.toRadians(-15F),0);
		animator.rotate(neck2, 0,(float) Math.toRadians(22.5F),0);
		animator.rotate(head, (float) Math.toRadians(7.5F),0,(float) Math.toRadians(-7.5F));
		animator.rotate(jaw,(float) Math.toRadians(15F),0,0);
		animator.rotate(left_arm,(float) Math.toRadians(17.5F),0,(float) Math.toRadians(-10F));
		animator.rotate(left_front_arm,(float) Math.toRadians(25F),0,0);
		animator.rotate(right_arm,(float) Math.toRadians(22.5F),0,(float) Math.toRadians(17.5F));
		animator.rotate(right_front_arm,(float) Math.toRadians(25F),0,0);
		//animator.rotate(left_leg,(float) Math.toRadians(-15F),(float) Math.toRadians(-22.5F),(float) Math.toRadians(-22.5F));
		//animator.rotate(right_leg,(float) Math.toRadians(-30F),(float) Math.toRadians(22.5F),(float) Math.toRadians(7.5F));
		animator.move(right_leg,0,2,0);
		animator.rotate(left_front_leg,(float) Math.toRadians(30F),(float) Math.toRadians(-17.5F),(float) Math.toRadians(5F));
		animator.rotate(left_ankel_joint,(float) Math.toRadians(-10F),(float) Math.toRadians(1.5F),(float) Math.toRadians(10F));
		animator.rotate(right_front_leg,(float) Math.toRadians(-7.5F),0,0);
		animator.rotate(right_ankel_joint,(float) Math.toRadians(2.5F),0,(float) Math.toRadians(-12.5F));
		animator.endKeyframe();

		animator.startKeyframe(3);
		animator.rotate(mid_pivot, (float) Math.toRadians(-5F),(float) Math.toRadians(-200F), 0);
		animator.move(mid_pivot, 2,8, 10);
		animator.rotate(tail1, (float) Math.toRadians(-22.5F),(float) Math.toRadians(-47.5F), (float) Math.toRadians(-10F));
		animator.rotate(tail2, (float) Math.toRadians(-15F),(float) Math.toRadians(-32.5F), 0);
		animator.rotate(tail3, (float) Math.toRadians(10F),(float) Math.toRadians(-15F), (float) Math.toRadians(-5F));
		animator.rotate(tail4, (float) Math.toRadians(22.5F),(float) Math.toRadians(-27.5F), (float) Math.toRadians(-5F));
		animator.rotate(spine1, (float) Math.toRadians(2.5F),(float) Math.toRadians(-2.5F),0);
		animator.rotate(spine2, (float) Math.toRadians(2.5F),(float) Math.toRadians(12.5F),(float) Math.toRadians(10F));
		animator.rotate(neck1, 0,(float) Math.toRadians(5F),0);
		animator.rotate(neck2, 0,(float) Math.toRadians(32.5F),0);
		animator.rotate(head, (float) Math.toRadians(10F),0,(float) Math.toRadians(-7.5F));
		animator.rotate(jaw,(float) Math.toRadians(17.5F),0,0);
		animator.rotate(left_arm,(float) Math.toRadians(15F),(float) Math.toRadians(-2.5F),(float) Math.toRadians(-5F));
		animator.rotate(left_front_arm,(float) Math.toRadians(20F),0,0);
		animator.rotate(right_arm,(float) Math.toRadians(25F),(float) Math.toRadians(5F),(float) Math.toRadians(15F));
		animator.rotate(right_front_arm,(float) Math.toRadians(10F),0,0);
		animator.rotate(left_leg,0,0,(float) Math.toRadians(-32.5F));
		animator.rotate(right_leg,(float) Math.toRadians(-20F),(float) Math.toRadians(-7.5F),(float) Math.toRadians(-10F));
		animator.move(right_leg,0,2,0);
		animator.rotate(left_front_leg,(float) Math.toRadians(32.5F),(float) Math.toRadians(-17.5F),(float) Math.toRadians(5F));
		animator.rotate(left_ankel_joint,(float) Math.toRadians(-10F),(float) Math.toRadians(3F),(float) Math.toRadians(17.5F));
		animator.rotate(right_front_leg,(float) Math.toRadians(25F),0,0);
		animator.rotate(right_ankel_joint,(float) Math.toRadians(2.5F),0,(float) Math.toRadians(2.5F));
		animator.endKeyframe();

		animator.startKeyframe(5);
		animator.rotate(mid_pivot, (float) Math.toRadians(-5F),(float) Math.toRadians(-250F), 0);
		animator.move(mid_pivot, 2,15, 10);
		animator.rotate(tail1, (float) Math.toRadians(-7.5F),(float) Math.toRadians(-47.5F), (float) Math.toRadians(-10F));
		animator.rotate(tail2, (float) Math.toRadians(-17.5F),(float) Math.toRadians(-50F), (float) Math.toRadians(5F));
		animator.rotate(tail3, (float) Math.toRadians(12.5F),(float) Math.toRadians(-37.5F), (float) Math.toRadians(-7.5F));
		animator.rotate(tail4, (float) Math.toRadians(22.5F),(float) Math.toRadians(-32.5F), (float) Math.toRadians(-5F));
		animator.rotate(spine1, (float) Math.toRadians(2.5F),(float) Math.toRadians(7.5F),(float) Math.toRadians(1F));
		animator.rotate(spine2, (float) Math.toRadians(1.5F),(float) Math.toRadians(10F),(float) Math.toRadians(7.5F));
		animator.rotate(neck1, 0,(float) Math.toRadians(12.5F),0);
		animator.rotate(neck2, 0,(float) Math.toRadians(40F),0);
		animator.rotate(head, (float) Math.toRadians(17.5F),0,(float) Math.toRadians(-7.5F));
		animator.rotate(jaw,(float) Math.toRadians(20F),0,0);
		animator.rotate(left_arm,(float) Math.toRadians(5F),(float) Math.toRadians(-7.5F),(float) Math.toRadians(2.5F));
		animator.rotate(left_front_arm,(float) Math.toRadians(2.5F),0,0);
		animator.rotate(right_arm,(float) Math.toRadians(22.5F),(float) Math.toRadians(12.5F),(float) Math.toRadians(5F));
		animator.rotate(right_front_arm,(float) Math.toRadians(-30F),0,0);
		animator.rotate(left_leg,(float) Math.toRadians(-27.5F),0,(float) Math.toRadians(-32.5F));
		animator.rotate(right_leg,(float) Math.toRadians(-37.5F),(float) Math.toRadians(10F),(float) Math.toRadians(-7.5F));
		animator.move(right_leg,0,1.5f,0);
		animator.rotate(left_front_leg,(float) Math.toRadians(45F),(float) Math.toRadians(-17.5F),(float) Math.toRadians(5F));
		animator.rotate(left_ankel_joint,(float) Math.toRadians(-1.5F),(float) Math.toRadians(2.5F),(float) Math.toRadians(12.5F));
		animator.rotate(right_front_leg,(float) Math.toRadians(35F),0,0);
		animator.rotate(right_ankel_joint,(float) Math.toRadians(1.5F),0,(float) Math.toRadians(2.5F));
		animator.endKeyframe();

		animator.setStaticKeyframe(10);

		animator.startKeyframe(8);
		animator.rotate(mid_pivot, (float) Math.toRadians(-3.5F),(float) Math.toRadians(-290F), 0);
		animator.move(mid_pivot, 1,10, 7f);
		animator.rotate(tail1, (float) Math.toRadians(-5F),(float) Math.toRadians(-30F), (float) Math.toRadians(-7.5F));
		animator.rotate(tail2, (float) Math.toRadians(-12.5F),(float) Math.toRadians(-32.5F), (float) Math.toRadians(3.5F));
		animator.rotate(tail3, (float) Math.toRadians(12.5F),(float) Math.toRadians(-25F), (float) Math.toRadians(-5F));
		animator.rotate(tail4, (float) Math.toRadians(22.5F),(float) Math.toRadians(-22.5F), (float) Math.toRadians(-3.5F));
		animator.rotate(spine1, (float) Math.toRadians(1.5F),(float) Math.toRadians(5F),0);
		animator.rotate(spine2, 0,(float) Math.toRadians(5F),(float) Math.toRadians(5F));
		animator.rotate(neck1, 0,(float) Math.toRadians(10F),0);
		animator.rotate(neck2, 0,(float) Math.toRadians(27.5F),0);
		animator.rotate(head, (float) Math.toRadians(12.5F),0,(float) Math.toRadians(-5F));
		animator.rotate(jaw,(float) Math.toRadians(12.5F),0,0);
		animator.rotate(left_arm,(float) Math.toRadians(3F),(float) Math.toRadians(-5F),(float) Math.toRadians(2.5F));
		animator.rotate(right_arm,(float) Math.toRadians(12.5F),(float) Math.toRadians(7.5F),(float) Math.toRadians(2.5F));
		animator.rotate(right_front_arm,(float) Math.toRadians(-22.5F),0,0);
		animator.rotate(left_leg,(float) Math.toRadians(-37.5F),0,(float) Math.toRadians(-20F));
		animator.move(left_leg,0,-5,0);
		animator.rotate(left_foot,(float) Math.toRadians(7.5F),0,0);
		animator.rotate(right_leg,(float) Math.toRadians(-22.5F),(float) Math.toRadians(17.5F),(float) Math.toRadians(5F));
		animator.move(right_leg,0,1f,0);
		animator.rotate(left_front_leg,(float) Math.toRadians(45F),(float) Math.toRadians(-10F),(float) Math.toRadians(3F));
		animator.rotate(left_ankel_joint,(float) Math.toRadians(3.5F),(float) Math.toRadians(1.5F),(float) Math.toRadians(7.5F));
		animator.rotate(right_front_leg,(float) Math.toRadians(22.5F),0,0);
		animator.rotate(right_ankel_joint,(float) Math.toRadians(1.5F),0,(float) Math.toRadians(2.5F));
		animator.endKeyframe();

		animator.startKeyframe(0);
		animator.rotate(mid_pivot, (float) Math.toRadians(-3.5F),(float) Math.toRadians(70F), 0);
		animator.move(mid_pivot, 1,10, 7f);
		animator.rotate(tail1, (float) Math.toRadians(-5F),(float) Math.toRadians(-30F), (float) Math.toRadians(-7.5F));
		animator.rotate(tail2, (float) Math.toRadians(-12.5F),(float) Math.toRadians(-32.5F), (float) Math.toRadians(3.5F));
		animator.rotate(tail3, (float) Math.toRadians(12.5F),(float) Math.toRadians(-25F), (float) Math.toRadians(-5F));
		animator.rotate(tail4, (float) Math.toRadians(22.5F),(float) Math.toRadians(-22.5F), (float) Math.toRadians(-3.5F));
		animator.rotate(spine1, (float) Math.toRadians(1.5F),(float) Math.toRadians(5F),0);
		animator.rotate(spine2, 0,(float) Math.toRadians(5F),(float) Math.toRadians(5F));
		animator.rotate(neck1, 0,(float) Math.toRadians(10F),0);
		animator.rotate(neck2, 0,(float) Math.toRadians(27.5F),0);
		animator.rotate(head, (float) Math.toRadians(12.5F),0,(float) Math.toRadians(-5F));
		animator.rotate(jaw,(float) Math.toRadians(12.5F),0,0);
		animator.rotate(left_arm,(float) Math.toRadians(3F),(float) Math.toRadians(-5F),(float) Math.toRadians(2.5F));
		animator.rotate(right_arm,(float) Math.toRadians(12.5F),(float) Math.toRadians(7.5F),(float) Math.toRadians(2.5F));
		animator.rotate(right_front_arm,(float) Math.toRadians(-22.5F),0,0);
		animator.rotate(left_leg,(float) Math.toRadians(-37.5F),0,(float) Math.toRadians(-20F));
		animator.move(left_leg,0,-5,0);
		animator.rotate(left_foot,(float) Math.toRadians(7.5F),0,0);
		animator.rotate(right_leg,(float) Math.toRadians(-22.5F),(float) Math.toRadians(17.5F),(float) Math.toRadians(5F));
		animator.move(right_leg,0,1f,0);
		animator.rotate(left_front_leg,(float) Math.toRadians(45F),(float) Math.toRadians(-10F),(float) Math.toRadians(3F));
		animator.rotate(left_ankel_joint,(float) Math.toRadians(3.5F),(float) Math.toRadians(1.5F),(float) Math.toRadians(7.5F));
		animator.rotate(right_front_leg,(float) Math.toRadians(22.5F),0,0);
		animator.rotate(right_ankel_joint,(float) Math.toRadians(1.5F),0,(float) Math.toRadians(2.5F));
		animator.endKeyframe();


		animator.resetKeyframe(15);



		animator.setAnimation(Ancient_Remnant_Entity.REMNANT_LEFT_STOMP);
		animator.startKeyframe(12);
		animator.rotate(mid_pivot, (float) Math.toRadians(-7.5F),(float) Math.toRadians(-5F), 0);
		animator.move(mid_pivot, 0,2, 0);
		animator.rotate(tail1, 0,(float) Math.toRadians(5F), 0);
		animator.rotate(tail2, 0,(float) Math.toRadians(5F), 0);
		animator.rotate(tail3, (float) Math.toRadians(12.5F),(float) Math.toRadians(7.5F), (float) Math.toRadians(1.5F));
		animator.rotate(tail4, (float) Math.toRadians(22.5F),(float) Math.toRadians(5F), (float) Math.toRadians(5F));
		animator.rotate(spine1, 0,(float) Math.toRadians(-5F),0);
		animator.rotate(spine2, 0,(float) Math.toRadians(-5F),0);
		animator.rotate(neck1, 0,(float) Math.toRadians(-10F),0);
		animator.rotate(neck2, 0,(float) Math.toRadians(-10F),0);
		animator.rotate(head, (float) Math.toRadians(7.5F),(float) Math.toRadians(15F),0);
		animator.rotate(jaw,(float) Math.toRadians(7.5F),0,0);
		animator.rotate(left_leg,0,0,(float) Math.toRadians(-10F));
		//animator.move(left_leg,0,-6,0);
		animator.rotate(right_leg,(float) Math.toRadians(12.5F),0,0);
		//animator.move(right_leg,0,-4,0);
		animator.rotate(left_front_leg,(float) Math.toRadians(10F),0,0);
		animator.rotate(left_ankel_joint,0,0,(float) Math.toRadians(7.5F));
		animator.rotate(right_front_leg,(float) Math.toRadians(12.5F),0,0);
		animator.rotate(right_ankel_joint,(float) Math.toRadians(-12.5F),0,0);
		animator.endKeyframe();


		animator.startKeyframe(12);
		animator.rotate(mid_pivot, (float) Math.toRadians(-17.5F),(float) Math.toRadians(-7.5F), (float) Math.toRadians(-7.5F));
		animator.move(mid_pivot, 0,-1, 0);
		animator.rotate(tail1, 0,(float) Math.toRadians(-5F), 0);
		animator.rotate(tail2, 0,(float) Math.toRadians(-20F), 0);
		animator.rotate(tail3, (float) Math.toRadians(12.5F),(float) Math.toRadians(-15F), (float) Math.toRadians(-2.5F));
		animator.rotate(tail4, (float) Math.toRadians(22.5F),(float) Math.toRadians(-7.5F), (float) Math.toRadians(-7.5F));
		animator.rotate(spine1, (float) Math.toRadians(10F),(float) Math.toRadians(12.5F),(float) Math.toRadians(2.5F));
		animator.rotate(spine2, (float) Math.toRadians(7.5F),(float) Math.toRadians(-10F),(float) Math.toRadians(-2.5F));
		animator.rotate(neck1, (float) Math.toRadians(7.5F),(float) Math.toRadians(-10F),0);
		animator.rotate(neck2, (float) Math.toRadians(10F),(float) Math.toRadians(-10F),0);
		animator.rotate(head, (float) Math.toRadians(-2.5F),(float) Math.toRadians(15F),0);
		animator.rotate(jaw,(float) Math.toRadians(12.5F),0,0);
		animator.rotate(left_leg,(float) Math.toRadians(-50F),(float) Math.toRadians(-12.5F),(float) Math.toRadians(-15F));
		animator.move(left_leg,0,-4,0);
		animator.rotate(left_front_leg,(float) Math.toRadians(50F),0,0);
		animator.rotate(left_ankel_joint,(float) Math.toRadians(15F),0,(float) Math.toRadians(7.5F));
		animator.rotate(right_leg,(float) Math.toRadians(17.5F),0,(float) Math.toRadians(7.5F));
		animator.move(right_leg,0,3,0);
		animator.rotate(right_front_leg,(float) Math.toRadians(20F),0,0);
		animator.rotate(right_ankel_joint,(float) Math.toRadians(-15F),0,0);
		animator.endKeyframe();

		animator.setStaticKeyframe(3);

		animator.startKeyframe(2);
		animator.rotate(mid_pivot, (float) Math.toRadians(-15F),(float) Math.toRadians(-7.5F), (float) Math.toRadians(-6.0F));
		animator.move(mid_pivot, 0,12.4F, 0);
		animator.rotate(pelvis, 0,(float) Math.toRadians(10F), 0);
		animator.rotate(tail1, 0,(float) Math.toRadians(5F), 0);
		animator.rotate(tail2, 0,(float) Math.toRadians(-2.5F), 0);
		animator.rotate(tail3, (float) Math.toRadians(12.5F),0, 0);
		animator.rotate(tail4, (float) Math.toRadians(22.5F),(float) Math.toRadians(2.5F), 0);
		animator.rotate(spine1, (float) Math.toRadians(2.5F),(float) Math.toRadians(17.5F),(float) Math.toRadians(7.5F));
		animator.rotate(spine2, (float) Math.toRadians(3.5F),(float) Math.toRadians(-12.5F),(float) Math.toRadians(-5F));
		animator.rotate(neck1, (float) Math.toRadians(15F),(float) Math.toRadians(-10F),0);
		animator.rotate(neck2, (float) Math.toRadians(12.5F),(float) Math.toRadians(-10F),0);
		animator.rotate(head, (float) Math.toRadians(2.5F),(float) Math.toRadians(15F),0);
		animator.rotate(jaw,(float) Math.toRadians(2.5F),0,0);
		animator.rotate(left_leg,0,0,(float) Math.toRadians(-5F));
		animator.move(left_leg,0,4,-12.5F);
		animator.rotate(left_front_leg,(float) Math.toRadians(42.5F),0,0);
		animator.rotate(left_ankel_joint,(float) Math.toRadians(-25F),0,(float) Math.toRadians(7.5F));
		animator.rotate(right_leg,(float) Math.toRadians(5F),0,(float) Math.toRadians(7.5F));
		animator.move(right_leg,0,-5f,0);
		animator.rotate(right_front_leg,(float) Math.toRadians(40F),0,0);
		animator.rotate(right_ankel_joint,(float) Math.toRadians(-30F),0,0);
		animator.endKeyframe();

		animator.setStaticKeyframe(10);

		animator.resetKeyframe(15);
	}

	@Override
	public void setupAnim(Ancient_Remnant_Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		animate(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		faceTarget(netHeadYaw, headPitch, 1, head);
		float globalSpeed = 0.25f;
		float globalDegree = 0.5F;

		float chargeSpeed = 0.5f;

		AdvancedModelBox[] tailBoxes = new AdvancedModelBox[]{tail1, tail2, tail3,tail4};
		AdvancedModelBox[] neckBoxes = new AdvancedModelBox[]{neck1, neck2, head};
		//groinJoint.rotationPointY -= 1 * limbSwingAmount;

		float partialTick = Minecraft.getInstance().getFrameTime();
		float chargeProgress = entityIn.prevchargeProgress + (entityIn.chargeProgress - entityIn.prevchargeProgress) * partialTick;

		if (chargeProgress <= 0F) {
			this.bob(pelvis, 2 * globalSpeed, globalDegree * 5, false, limbSwing, limbSwingAmount);

			walk(left_leg, 1F * globalSpeed, globalDegree * 1.4f, true, (float) Math.PI * 0.4f, 0F, limbSwing, limbSwingAmount);
			walk(right_leg, 1F * globalSpeed, globalDegree * 1.4f, true, (float) Math.PI * 1.4f, 0F, limbSwing, limbSwingAmount);

			this.left_leg.rotationPointY += Mth.clamp(Mth.sin(limbSwing * globalSpeed) * limbSwingAmount * 10, -Float.POSITIVE_INFINITY, 1);
			this.left_leg.rotationPointZ += Mth.cos(limbSwing * globalSpeed + (float) Math.PI) * limbSwingAmount * 10 + limbSwingAmount * -8;

			this.right_leg.rotationPointY += Mth.clamp(Mth.sin(limbSwing * globalSpeed + (float) Math.PI) * limbSwingAmount * 10, -Float.POSITIVE_INFINITY, 1);
			this.right_leg.rotationPointZ += Mth.cos(limbSwing * globalSpeed) * limbSwingAmount * 10 + limbSwingAmount * -8;
			//this.right_leg.rotationPointZ += Mth.cos(limbSwing * speed + offset) * degree * limbSwingAmount + weight * limbSwingAmount;

			walk(left_front_leg, 1F * globalSpeed, 1.2F * globalDegree, false, (float) Math.PI * 0.4f + 0.1f, 0.6F * globalDegree, limbSwing, limbSwingAmount);
			walk(right_front_leg, 1F * globalSpeed, 1.2F * globalDegree, false, (float) Math.PI * 1.4f + 0.1f, 0.6F * globalDegree, limbSwing, limbSwingAmount);

			walk(left_ankel_joint, 1F * globalSpeed, 1.2F * globalDegree, false, (float) Math.PI * 0.4f + 0.2f, -1.0F * globalDegree, limbSwing, limbSwingAmount);
			walk(right_ankel_joint, 1F * globalSpeed, 1.2F * globalDegree, false, (float) Math.PI * 1.4f + 0.2f, -1.0F * globalDegree, limbSwing, limbSwingAmount);

			walk(left_foot, 1F * globalSpeed, 1F * globalDegree, true, (float) Math.PI * 0.4f + 0.3f, -globalDegree * 1F, limbSwing, limbSwingAmount);
			walk(right_foot, 1F * globalSpeed, 1F * globalDegree, true, (float) Math.PI * 1.4f + 0.3f, -globalDegree * 1F, limbSwing, limbSwingAmount);

			this.walk(neck1, 0.6F * globalSpeed, globalDegree * 0.1F, false, 0.0f, -globalDegree * 0.1F, limbSwing, limbSwingAmount);
			this.walk(neck2, 0.6F * globalSpeed, globalDegree * 0.2F, true, 0.1f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);
			this.walk(head, 0.6F * globalSpeed, globalDegree * 0.1F, true, 0.2f, -globalDegree * 0.1F, limbSwing, limbSwingAmount);
			this.walk(jaw, 0.6F * globalSpeed, globalDegree * 0.25F, true, 0.2f, -globalDegree * 0.25F, limbSwing, limbSwingAmount);


			this.walk(right_arm, 0.6F * globalSpeed, globalDegree, false, (float) Math.PI * 0.4f, -globalDegree, limbSwing, limbSwingAmount);

			this.right_arm.rotationPointZ += Mth.cos(limbSwing * globalSpeed + (float) Math.PI) * limbSwingAmount * 8 + limbSwingAmount * 8;

			this.walk(left_arm, 0.6F * globalSpeed, globalDegree, false, (float) Math.PI * 1.4f, -globalDegree, limbSwing, limbSwingAmount);

			this.left_arm.rotationPointZ += Mth.cos(limbSwing * globalSpeed) * limbSwingAmount * 8 + limbSwingAmount * 8;


			this.walk(right_front_arm, 0.6F * globalSpeed, globalDegree * 0.3F, false, 0.1f, -globalDegree * 0.3F, limbSwing, limbSwingAmount);

			this.walk(left_front_arm, 0.6F * globalSpeed, globalDegree * 0.3F, false, 0.1f, -globalDegree * 0.3F, limbSwing, limbSwingAmount);

			this.walk(right_hand, 0.6F * globalSpeed, globalDegree * 0.4F, false, 0.1f, -globalDegree * 0.4F, limbSwing, limbSwingAmount);

			this.walk(left_hand, 0.6F * globalSpeed, globalDegree * 0.4F, false, 0.1f, -globalDegree * 0.4F, limbSwing, limbSwingAmount);

			this.walk(right_finger1, 0.6F * globalSpeed, globalDegree * 0.2F, false, 0.125f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);

			this.walk(right_finger2, 0.6F * globalSpeed, globalDegree * 0.2F, false, 0.125f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);

			this.walk(right_finger3, 0.6F * globalSpeed, globalDegree * 0.2F, false, 0.125f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);

			this.walk(left_finger1, 0.6F * globalSpeed, globalDegree * 0.2F, false, 0.125f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);

			this.walk(left_finger2, 0.6F * globalSpeed, globalDegree * 0.2F, false, 0.125f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);

			this.walk(left_finger3, 0.6F * globalSpeed, globalDegree * 0.2F, false, 0.125f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);

			this.swing(spine1, 1F * globalSpeed, globalDegree * 0.2F, false, 0.0f, 0, limbSwing, limbSwingAmount);

			this.swing(spine2, 1F * globalSpeed, globalDegree * 0.2F, false, 0.1f, 0, limbSwing, limbSwingAmount);

			this.chainSwing(tailBoxes, 1.2F * globalSpeed, globalDegree * 0.15F, 1, limbSwing, limbSwingAmount);

			this.chainSwing(neckBoxes, 0.6F * globalSpeed, globalDegree * 0.1F, 1, limbSwing, limbSwingAmount);
		}else{

			this.bob(pelvis, 2 * chargeSpeed, globalDegree * 5, false, limbSwing, limbSwingAmount);

			walk(left_leg, 0.75F * chargeSpeed, globalDegree * 1.4f, true, (float) Math.PI * 0.4f, globalDegree * 0.3f, limbSwing, limbSwingAmount);
			walk(right_leg, 0.75F * chargeSpeed, globalDegree * 1.4f, true, (float) Math.PI * 1.4f, globalDegree * 0.3f, limbSwing, limbSwingAmount);

			this.left_leg.rotationPointY += Mth.clamp(Mth.sin(limbSwing * 0.75F * chargeSpeed) * limbSwingAmount * 4, -Float.POSITIVE_INFINITY, 1);
			this.left_leg.rotationPointZ += Mth.cos(limbSwing * 0.75F * chargeSpeed + (float) Math.PI) * limbSwingAmount * 10 + limbSwingAmount * -8;

			this.right_leg.rotationPointY += Mth.clamp(Mth.sin(limbSwing * 0.75F * chargeSpeed + (float) Math.PI) * limbSwingAmount * 4, -Float.POSITIVE_INFINITY, 1);
			this.right_leg.rotationPointZ += Mth.cos(limbSwing * 0.75F * chargeSpeed) * limbSwingAmount * 10 + limbSwingAmount * -8;
			//this.right_leg.rotationPointZ += Mth.cos(limbSwing * speed + offset) * degree * limbSwingAmount + weight * limbSwingAmount;

			walk(left_front_leg, 0.75F * chargeSpeed, 0.8F * globalDegree, false, (float) Math.PI * 0.4f + 0.1f, 0.8F * globalDegree, limbSwing, limbSwingAmount);
			walk(right_front_leg, 0.75F * chargeSpeed, 0.8F * globalDegree, false, (float) Math.PI * 1.4f + 0.1f, 0.8F * globalDegree, limbSwing, limbSwingAmount);

			walk(left_ankel_joint, 0.75F * chargeSpeed, 1.2F * globalDegree, false, (float) Math.PI * 0.4f + 0.2f, -1.0F * globalDegree, limbSwing, limbSwingAmount);
			walk(right_ankel_joint, 0.75F * chargeSpeed, 1.2F * globalDegree, false, (float) Math.PI * 1.4f + 0.2f, -1.0F * globalDegree, limbSwing, limbSwingAmount);

			walk(left_foot, 0.75F * chargeSpeed, 1F * globalDegree, true, (float) Math.PI * 0.4f + 0.3f, -globalDegree * 1F, limbSwing, limbSwingAmount);
			walk(right_foot, 0.75F * chargeSpeed, 1F * globalDegree, true, (float) Math.PI * 1.4f + 0.3f, -globalDegree * 1F, limbSwing, limbSwingAmount);

			this.walk(neck1, 0.6F * chargeSpeed, globalDegree * 0.1F, false, 0.0f, -globalDegree * 0.1F, limbSwing, limbSwingAmount);
			this.walk(neck2, 0.6F * chargeSpeed, globalDegree * 0.2F, true, 0.1f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);
			this.walk(head, 0.6F * chargeSpeed, globalDegree * 0.1F, true, 0.2f, -globalDegree * 0.1F, limbSwing, limbSwingAmount);

			this.walk(right_arm, 2f * chargeSpeed, 0.05f, true, 0, -0.05f, limbSwing, limbSwingAmount);
			this.walk(left_arm, 2f * chargeSpeed, 0.05f, true, 0, -0.05f, limbSwing, limbSwingAmount);


			this.walk(right_front_arm, 2f * chargeSpeed, 0.125f, false, 0.1f, -0.125f, limbSwing, limbSwingAmount);

			this.walk(left_front_arm, 2f * chargeSpeed, 0.125f, false, 0.1f, -0.125f, limbSwing, limbSwingAmount);

			this.walk(right_hand, 2f * chargeSpeed, 0.125f, true, 0.1f, -0.125f, limbSwing, limbSwingAmount);

			this.walk(left_hand, 2f * chargeSpeed, 0.125f, true, 0.1f, -0.125f, limbSwing, limbSwingAmount);

			this.walk(right_finger1, 0.6F * chargeSpeed, globalDegree * 0.2F, false, 0.125f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);

			this.walk(right_finger2, 0.6F * chargeSpeed, globalDegree * 0.2F, false, 0.125f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);

			this.walk(right_finger3, 0.6F * chargeSpeed, globalDegree * 0.2F, false, 0.125f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);

			this.walk(left_finger1, 0.6F * chargeSpeed, globalDegree * 0.2F, false, 0.125f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);

			this.walk(left_finger2, 0.6F * chargeSpeed, globalDegree * 0.2F, false, 0.125f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);

			this.walk(left_finger3, 0.6F * chargeSpeed, globalDegree * 0.2F, false, 0.125f, -globalDegree * 0.2F, limbSwing, limbSwingAmount);

			this.walk(spine1, 2F * chargeSpeed, globalDegree * 0.05F, false, 0.0f, -globalDegree * 0.05F, limbSwing, limbSwingAmount);

			this.walk(spine2, 2F * chargeSpeed, globalDegree * 0.05F, false, 0.1f, -globalDegree * 0.05F, limbSwing, limbSwingAmount);


			this.swing(spine1, 0.35F * chargeSpeed, globalDegree * 0.2F, false, 0.0f, 0, limbSwing, limbSwingAmount);

			this.swing(spine2, 0.35F * chargeSpeed, globalDegree * 0.2F, false, 0.1f, 0, limbSwing, limbSwingAmount);

			this.chainSwing(tailBoxes, 1.2F * chargeSpeed, globalDegree * 0.15F, 1, limbSwing, limbSwingAmount);

			this.chainSwing(neckBoxes, 0.6F * chargeSpeed, globalDegree * 0.1F, 1, limbSwing, limbSwingAmount);


		}

		this.walk(tail1, 0.1F * globalSpeed, globalDegree * 0.3F, true, 0,0,ageInTicks, 1);
		this.walk(tail2, 0.1F * globalSpeed, globalDegree * 0.3F, false, 0.1f,0,ageInTicks, 1);
		this.walk(tail3, 0.1F * globalSpeed, globalDegree * 0.3F, false, 0.2f,0,ageInTicks, 1);
		this.walk(tail4, 0.1F * globalSpeed, globalDegree * 0.3F, false, 0.3f,0,ageInTicks, 1);

		this.walk(neck1, 0.1F * globalSpeed, globalDegree * 0.1F, false, 0.0f,-globalDegree * 0.1F,ageInTicks, 1);
		this.walk(neck2, 0.1F * globalSpeed, globalDegree * 0.2F, true, 0.1f,-globalDegree * 0.2F,ageInTicks, 1);
		this.walk(head, 0.1F * globalSpeed, globalDegree * 0.1F, true, 0.2f,-globalDegree * 0.1F,ageInTicks, 1);
		this.walk(jaw, 0.1F * globalSpeed, globalDegree * 0.25F, true, 0.2f,-globalDegree * 0.25F,ageInTicks, 1);


		this.walk(right_arm, 0.1F * globalSpeed, globalDegree * 0.2F, true, 0.0f,-globalDegree * 0.2F,ageInTicks, 1);

		this.walk(left_arm, 0.1F * globalSpeed, globalDegree * 0.2F, true, 0.0f,-globalDegree * 0.2F,ageInTicks, 1);


		this.walk(right_front_arm, 0.1F * globalSpeed, globalDegree * 0.3F, false, 0.1f,-globalDegree * 0.3F,ageInTicks, 1);

		this.walk(left_front_arm, 0.1F * globalSpeed, globalDegree * 0.3F, false, 0.1f,-globalDegree * 0.3F,ageInTicks, 1);

		this.walk(right_hand, 0.1F * globalSpeed, globalDegree * 0.4F, false, 0.1f,-globalDegree * 0.4F,ageInTicks, 1);

		this.walk(left_hand, 0.1F * globalSpeed, globalDegree * 0.4F, false, 0.1f,-globalDegree * 0.4F,ageInTicks, 1);

		this.walk(right_finger1, 0.1F * globalSpeed, globalDegree * 0.2F, false, 0.125f,-globalDegree * 0.2F,ageInTicks, 1);

		this.walk(right_finger2, 0.1F * globalSpeed, globalDegree * 0.2F, false, 0.125f,-globalDegree * 0.2F,ageInTicks, 1);

		this.walk(right_finger3, 0.1F * globalSpeed, globalDegree * 0.2F, false, 0.125f,-globalDegree * 0.2F,ageInTicks, 1);

		this.walk(left_finger1, 0.1F * globalSpeed, globalDegree * 0.2F, false, 0.125f,-globalDegree * 0.2F,ageInTicks, 1);

		this.walk(left_finger2, 0.1F * globalSpeed, globalDegree * 0.2F, false, 0.125f,-globalDegree * 0.2F,ageInTicks, 1);

		this.walk(left_finger3, 0.1F * globalSpeed, globalDegree * 0.2F, false, 0.125f,-globalDegree * 0.2F,ageInTicks, 1);

		this.bob(pelvis, 0.2F * globalSpeed, globalDegree * 2, false, ageInTicks, 1);

		//this.left_leg.rotateAngleX += Mth.cos(limbSwing * cycle + (float) Math.PI * 1.4f) * limbSwingAmount * 0.5f;

		//this.right_leg.rotateAngleX += Mth.cos(limbSwing * cycle + (float) Math.PI * 0.4f) * limbSwingAmount * 0.5f;


		progressRotationPrev(root,chargeProgress,(float) Math.toRadians(-2.5F), 0, 0, 3f);
		progressPositionPrev(root,chargeProgress,0, 1, 2, 3f);
		progressRotationPrev(pelvis,chargeProgress,(float)Math.toRadians(25F), 0, 0, 3f);
		progressRotationPrev(tail1,chargeProgress,(float) Math.toRadians(-12.5F),(float) Math.toRadians(5F), 0, 3f);
		progressRotationPrev(tail2,chargeProgress,0, (float) Math.toRadians(5F), 0, 3f);
		progressRotationPrev(tail3,chargeProgress,(float) Math.toRadians(12.5F),(float) Math.toRadians(7.5F), 0, 3f);
		progressRotationPrev(tail4,chargeProgress,(float) Math.toRadians(20F),(float) Math.toRadians(7.5F), 0, 3f);
		progressRotationPrev(spine1,chargeProgress,(float)Math.toRadians(-10F), (float)Math.toRadians(-5F), 0, 3f);
		progressRotationPrev(spine2,chargeProgress,0, (float)Math.toRadians(5F), 0, 3f);
		progressRotationPrev(neck1,chargeProgress,(float) Math.toRadians(22.5F),(float) Math.toRadians(2.5F), 0, 3f);
		progressRotationPrev(neck2,chargeProgress,(float) Math.toRadians(-15F),(float) Math.toRadians(2.5F), 0, 3f);
		progressRotationPrev(head,chargeProgress,(float) Math.toRadians(-20F),(float) Math.toRadians(-2.5F), 0, 3f);
		progressRotationPrev(jaw,chargeProgress, (float) Math.toRadians(62.5F),0, 0, 3f);
		progressRotationPrev(right_front_arm,chargeProgress, (float) Math.toRadians(-7.5F),0, 0, 3f);
		progressRotationPrev(right_hand,chargeProgress, (float) Math.toRadians(22.5F),0, 0, 3f);
		progressRotationPrev(left_front_arm,chargeProgress, (float) Math.toRadians(-7.5F),0, 0, 3f);
		progressRotationPrev(left_hand,chargeProgress, (float) Math.toRadians(22.5F),0, 0, 3f);
		progressRotationPrev(legs,chargeProgress, (float) Math.toRadians(10F),0, 0, 3f);

		/***
		progressRotationPrev(right_leg,chargeProgress, (float) Math.toRadians(-25F),0, 0, 3f);
		progressPosition(right_leg,chargeProgress,0, -4, -4, 3f);
		progressRotationPrev(right_front_leg,chargeProgress, (float) Math.toRadians(15F),0, 0, 3f);
		progressRotationPrev(right_foot,chargeProgress, (float) Math.toRadians(35F),0, 0, 3f);
		progressRotationPrev(left_leg,chargeProgress, (float) Math.toRadians(22.5F),0, 0, 3f);
		progressPosition(left_leg,chargeProgress,0, 0, 6, 3f);
		progressRotationPrev(left_front_leg,chargeProgress, (float) Math.toRadians(-17.5F),0, 0, 3f);
		progressRotationPrev(left_ankel_joint,chargeProgress, (float) Math.toRadians(-20F),0, 0, 3f);
		progressRotationPrev(left_foot,chargeProgress, (float) Math.toRadians(2.5F),0, 0, 3f);
		 ***/

	}

	private float calculateRotation(float speed, float degree, boolean invert, float offset, float weight, float limbswing, float limbSwingAmount) {
		float rotation = Mth.cos(limbswing * speed + offset) * degree * limbSwingAmount + weight * limbSwingAmount;
		return invert ? -rotation : rotation;
	}


	public void setRotationAngle(AdvancedModelBox AdvancedModelBox, float x, float y, float z) {
		AdvancedModelBox.rotateAngleX = x;
		AdvancedModelBox.rotateAngleY = y;
		AdvancedModelBox.rotateAngleZ = z;
	}

}