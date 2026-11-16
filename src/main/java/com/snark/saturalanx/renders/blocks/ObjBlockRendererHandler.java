package com.snark.saturalanx.renders.blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.model.obj.*;

public interface ObjBlockRendererHandler extends ISimpleBlockRenderingHandler {

    public static void renderWithOBJ(WavefrontObject model, IIcon icon, Tessellator tes, float scale, float rotation) {

        float cos = (float) Math.cos(rotation);
        float sin = (float) Math.sin(rotation);
        float x, z;

        for(GroupObject go : model.groupObjects){
            for (Face f : go.faces){
                System.out.println(f.toString());
                Vertex n = f.faceNormal;

                tes.setNormal(n.x,n.y,n.z);
                for (int i = 0; i < f.vertices.length; i++) {
                    Vertex v = f.vertices[i];
                    TextureCoordinate t = f.textureCoordinates[i];
                    // rotate
                    x = cos * v.x - sin * v.z;
                    z = sin * v.x + cos * v.z;
                    // scale
                    x *= scale;
                    z *= scale;
                    tes.addVertexWithUV(x, v.y * scale, z, icon.getInterpolatedU(t.u * 16), icon.getInterpolatedV(t.v * 16));
                }
                // render other side of face
                tes.setNormal(-n.x, -n.y, -n.z); // negative normal
                for (int i = f.vertices.length - 1; i >= 0; i--) {
                    Vertex v = f.vertices[i];
                    TextureCoordinate t = f.textureCoordinates[i];
                    // rotate
                    x = cos * v.x - sin * v.z;
                    z = sin * v.x + cos * v.z;
                    // scale
                    x *= scale;
                    z *= scale;
                    tes.addVertexWithUV(x, v.y * scale, z, icon.getInterpolatedU(t.u * 16), icon.getInterpolatedV(t.v * 16));
                }
            }
        }
    }
}
