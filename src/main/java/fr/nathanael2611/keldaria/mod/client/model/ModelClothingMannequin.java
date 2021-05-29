package fr.nathanael2611.keldaria.mod.client.model;

import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelClothingMannequin extends ModelPlayer
{

    public ModelClothingMannequin(boolean slim)
    {
        super(0F, false);
        this.isChild = false;
    }


    private void resetRotationsOnPart(ModelRenderer mr)
    {
        mr.rotateAngleX = 0;
        mr.rotateAngleY = 0;
        mr.rotateAngleZ = 0;
    }

    private void resetRotations()
    {
        resetRotationsOnPart(this.bipedHead);
        resetRotationsOnPart(this.bipedHeadwear);
        resetRotationsOnPart(this.bipedBody);
        resetRotationsOnPart(this.bipedLeftArm);
        resetRotationsOnPart(this.bipedRightArm);
        resetRotationsOnPart(this.bipedLeftLeg);
        resetRotationsOnPart(this.bipedRightLeg);
    }

    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale, boolean headOverlay)
    {
        resetRotations();
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, scale, p_78088_1_);
        GL11.glColor3f(1F, 1F, 1F);
        this.bipedHead.render(scale);
        this.bipedBody.render(scale);
        this.bipedRightArm.render(scale);
        this.bipedLeftArm.render(scale);
        this.bipedRightLeg.render(scale);
        this.bipedLeftLeg.render(scale);
        if (headOverlay)
        {
            GL11.glDisable(GL11.GL_CULL_FACE);
            this.bipedHeadwear.render(scale);
            GL11.glEnable(GL11.GL_CULL_FACE);
        }
        GL11.glColor3f(1F, 1F, 1F);
    }
/*
    public void render(BipedRotations bipedRotations, boolean headOverlay, float scale)
    {
        GL11.glColor3f(1F, 1F, 1F);
        bipedRotations.applyRotationsToBiped(this);
        if (isChild)
        {
            float f6 = 2.0F;
            GL11.glPushMatrix();
            GL11.glScalef(1.5F / f6, 1.5F / f6, 1.5F / f6);
            GL11.glTranslatef(0.0F, 16.0F * scale, 0.0F);
            this.bipedHead.render(scale);
            if (headOverlay)
            {
                GL11.glDisable(GL11.GL_CULL_FACE);
                this.bipedHeadwear.render(scale);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0F / f6, 1.0F / f6, 1.0F / f6);
            GL11.glTranslatef(0.0F, 24.0F * scale, 0.0F);
            this.bipedBody.render(scale);
            this.bipedRightArm.render(scale);
            this.bipedLeftArm.render(scale);
            this.bipedRightLeg.render(scale);
            this.bipedLeftLeg.render(scale);
            GL11.glPopMatrix();
        } else
        {
            this.bipedHead.render(scale);
            this.bipedBody.render(scale);
            this.bipedRightArm.render(scale);
            this.bipedLeftArm.render(scale);
            this.bipedRightLeg.render(scale);
            this.bipedLeftLeg.render(scale);
            if (headOverlay)
            {
                GL11.glDisable(GL11.GL_CULL_FACE);
                this.bipedHeadwear.render(scale);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
        }
    }*/
}