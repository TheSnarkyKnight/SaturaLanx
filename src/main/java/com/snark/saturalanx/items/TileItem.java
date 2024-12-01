package com.snark.saturalanx.items;

import com.dunk.tfc.Blocks.BlockRoad;
import com.dunk.tfc.TileEntities.TEPottery;
import com.dunk.tfc.TileEntities.TERoad;
import com.dunk.tfc.api.TFCBlocks;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.TE.TileTE;
import com.snark.saturalanx.core.BlockSetup;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileItem extends Pottery{
    private int num;
    public TileItem(){
        super();
    }

    public TileItem(int i){
        super();
        this.num = i;
    }

    public void setNum(int i){
        this.num = i;
    }
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {

        if(!world.isRemote&&itemstack.getItemDamage()==1&&!entityplayer.isSneaking()){
            if(world.getBlock(x,y,z) instanceof BlockRoad){
                TileEntity te = world.getTileEntity(x,y,z);
                if(te != null && te instanceof TERoad){
                    TERoad road = (TERoad) te;
                    if(road.getMortar()&&road.getStoneId()==-1) {
                        int gravelId = road.getGravelId();
                        int dirtId = road.getDirtId();

                        if(itemstack.stackSize>=4){
                            itemstack.stackSize -= 4;
                            if (itemstack.stackSize <= 0)
                                itemstack = null;


                            TileTE tile = new TileTE();
                            tile.setDirtId(dirtId);
                            tile.setGravelId(gravelId);
                            world.setTileEntity(x, y, z, tile);
                            world.setBlock(x, y, z, BlockSetup.floorTiles, this.num, 0);
                            tile.setDirtId(dirtId);
                            tile.setGravelId(gravelId);
                            world.markBlockForUpdate(x, y, z);

                            //System.out.println("DirtID:"+tile.getDirtId());
                            //System.out.println("GravelID:"+tile.getGravelId());}
                        }
                    }
                }
            }

        }
        return super.onItemUse(itemstack,entityplayer,world,x,y,z,side,hitX,hitY,hitZ);
    }


}
