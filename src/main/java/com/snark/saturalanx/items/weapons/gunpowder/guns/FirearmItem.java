package com.snark.saturalanx.items.weapons.gunpowder.guns;

import com.dunk.tfc.api.Crafting.AnvilManager;
import com.dunk.tfc.api.Enums.EnumDamageType;
import com.dunk.tfc.api.Enums.EnumItemReach;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.TFCItems;
import com.snark.saturalanx.API.ammo.AmmoData;
import com.snark.saturalanx.SaturaLanx;
import com.snark.saturalanx.core.ItemSetup;
import com.snark.saturalanx.core.Util;
import com.snark.saturalanx.entities.EntityBullet;
import com.snark.saturalanx.items.weapons.SwordItemSL;
import com.snark.saturalanx.items.weapons.gunpowder.PaperCartridgeItem;
import com.snark.saturalanx.items.tools.PowderFlaskItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

import static com.snark.saturalanx.SaturaLanx.MODID;
import static com.snark.saturalanx.core.ItemSetup.WEAPONPATH;
import static com.snark.saturalanx.core.ItemSetup.bulletSL;

public class FirearmItem extends SwordItemSL {
    protected boolean oneHanded; //Whetever the gun can be fire one-handed
    protected boolean mounted; //Whetever the gun can be fired while mounted
    protected int loadingTime; //How many ticks it takes for the gun to load
    protected int firingTime; //How many ticks it takes for the gun to fire
    protected int bulletNumber; //How many bullets are fired from the gun
    protected int bulletDamage; //The individual damage of each bullet

    public FirearmItem(ToolMaterial par2EnumToolMaterial, float damage) {
        super(par2EnumToolMaterial, damage);
        this.damageType = EnumDamageType.CRUSHING;
        this.setCreativeTab(SaturaLanx.tab);
        this.setMaxStackSize(1);
        this.setAttackSpeed(30);
        this.setCrushDamageShape("3X5_MACE");
        setupParameters();
    }

    protected void setupParameters(){
        oneHanded = false;
        mounted = false;
        loadingTime = 20;
        firingTime = 4;
        bulletNumber = 1;
        bulletDamage = 1;
    }

    public void setupNBT(ItemStack stack){

        NBTTagCompound tag = stack.getTagCompound();
        if(tag == null)
            tag = new NBTTagCompound();

        tag.setTag("ammoItem",(new ItemStack(bulletSL,1,0).writeToNBT(new NBTTagCompound())));
        tag.setInteger("ammoPosition",-1);
        tag.setInteger("powder",-1);
        tag.setInteger("powderPosition",-1);
        tag.setInteger("wadding",-1);
        tag.setInteger("waddingPosition",-1);
        tag.setBoolean("loaded",false);
        tag.setBoolean("sound",true);

        stack.stackTagCompound = tag;
    }

    @Override
    public void registerIcons(IIconRegister registerer) {
        this.itemIcon = registerer.registerIcon(MODID + ":weapons/guns/" + this.getUnlocalizedName().replace("item.", ""));
    }

    @Override
    public EnumAction getItemUseAction(ItemStack p_77661_1_) {
        return EnumAction.bow;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        super.onCreated(stack, world, player);
        setupNBT(stack);
    }


    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player){

        if(stack.stackTagCompound == null){
            setupNBT(stack);
        }

        boolean loaded = stack.stackTagCompound.getBoolean("loaded");

        if((!loaded&&canLoad(player,stack))||(loaded&&canFire(player,stack))){

            ArrowNockEvent event = new ArrowNockEvent(player, stack);
            MinecraftForge.EVENT_BUS.post(event);
            if (event.isCanceled()) {
                return event.result;
            }

            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }

        return stack;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int inUseCount){

        stack.stackTagCompound.setBoolean("sound",true);
        boolean state = stack.stackTagCompound.getBoolean("loaded");
        int elapsedTime = stack.getMaxItemUseDuration()-inUseCount;

        if(!state&&elapsedTime>=loadingTime){
            loadGun(player,stack);

        }
        else if(state&&(elapsedTime>=firingTime)) {
            fireGun(player, stack,world);
        }
    }


    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {

        boolean b;
        if(stack.stackTagCompound.hasKey("sound"))
            b = stack.stackTagCompound.getBoolean("sound");
        else{
            b = true;
            stack.stackTagCompound.setBoolean("sound",true);
        }

        if(stack.getMaxItemUseDuration()-count >= loadingTime && b) {
            player.worldObj.playSoundAtEntity(player, MODID + ":load", 1, 1);
            b = false;
            stack.stackTagCompound.setBoolean("sound",false);
        }
    }


    public boolean canLoad(EntityPlayer player, ItemStack itemStack){
        return hasRamrod(player) && hasAmmo(player, itemStack) && hasPowder(player, itemStack) &&
                hasWadding(player, itemStack) && respectOneHanded(player,true) && respectMounted(player);
    }

    public boolean hasRamrod(EntityPlayer player){
        for(int i = 0;i<9;i++){
            ItemStack is = player.inventory.mainInventory[i];
            if(is != null && is.getItem() == TFCItems.stick)
                return true;
        }
        return false;
    }

    public boolean isValidAmmo(ItemStack itemStack){
        return getAmmoData(itemStack) != null;
    }

    public AmmoData getAmmoData(ItemStack itemStack){
        return ItemSetup.bulletRegistry.getAmmoDataFromItemStack(itemStack);
    }

    public boolean hasAmmo(EntityPlayer player, ItemStack itemStack){

        for(int i = 0; i< player.inventory.mainInventory.length; i++){
            if(player.inventory.mainInventory[i] != null){
                if(isValidAmmo(player.inventory.mainInventory[i])){
                    itemStack.stackTagCompound.setInteger("ammoPosition",i);
                    return true;
                }
            }
        }
        return false;

    }

    public boolean hasPowder(EntityPlayer player, ItemStack itemStack){

        //Paper cartridge handling
        int ammoPos = itemStack.stackTagCompound.getInteger("ammoPosition");
        if(ammoPos >= 0 && player.inventory.mainInventory[ammoPos].getItem() instanceof PaperCartridgeItem) {
            itemStack.stackTagCompound.setInteger("powderPosition",ammoPos);
            return true;
        }

        for(int i = 0; i < player.inventory.mainInventory.length; i++){
            ItemStack is = player.inventory.mainInventory[i];
            if(is != null && (is.getItem() == Items.gunpowder ||
                    (is.getItem() instanceof PowderFlaskItem && is.stackTagCompound != null && is.stackTagCompound.getInteger("powder") > 0))){
                itemStack.stackTagCompound.setInteger("powderPosition",i);
                return true;
            }
        }

        return false;
    }

    public boolean hasWadding(EntityPlayer player, ItemStack itemStack){

        //Only handled by blunderbuss-type weapons
        return true;

    }

    //Currently does nothing, in the future will check for shields and the like
    public boolean respectOneHanded(EntityPlayer player, boolean forceTwoHanded){
        return true;
    }


    public boolean respectMounted(EntityPlayer player){
        return (player.ridingEntity == null || player.ridingEntity instanceof EntityBoat);
    }

    public void loadGun(EntityPlayer player, ItemStack itemStack){

        int ammoPosition = itemStack.stackTagCompound.getInteger("ammoPosition");
        int powderPosition = itemStack.stackTagCompound.getInteger("powderPosition");
        boolean cartridge = false;

        //Ammo handling
        if(ammoPosition >= 0){
            ItemStack is = player.inventory.mainInventory[ammoPosition];

            if(isValidAmmo(is)){

                NBTTagCompound itemTag = new NBTTagCompound();
                is.writeToNBT(itemTag);
                itemStack.stackTagCompound.setTag("ammoItem", itemTag);

                itemStack.stackTagCompound.setInteger("ammoPosition",-2);

                if(is.getItem() instanceof PaperCartridgeItem){
                    itemStack.stackTagCompound.setInteger("powder",1);
                    itemStack.stackTagCompound.setInteger("powderPosition",-2);
                    cartridge = true;
                }

                if(!player.capabilities.isCreativeMode){
                    is.stackSize--;
                    if(is.stackSize <= 0)
                        player.inventory.setInventorySlotContents(ammoPosition,null);
                }
            }
        }

        //Powder handling
        if(powderPosition >= 0 && !cartridge){
            ItemStack is = player.inventory.mainInventory[powderPosition];

            if(is.getItem() == Items.gunpowder){

                itemStack.stackTagCompound.setInteger("powder",1);
                itemStack.stackTagCompound.setInteger("powderPosition",-2);

                if(!player.capabilities.isCreativeMode){
                    is.stackSize--;
                    if(is.stackSize <= 0)
                        player.inventory.setInventorySlotContents(powderPosition,null);
                }

            }
            else if(is.getItem() instanceof PowderFlaskItem && is.stackTagCompound != null && is.stackTagCompound.getInteger("powder") > 0){

                itemStack.stackTagCompound.setInteger("powder",1);
                itemStack.stackTagCompound.setInteger("powderPosition",-2);

                ((PowderFlaskItem)is.getItem()).decreasePowderCounter(is,1);
            }

        }

        //Wadding handling
        //Wadding is only handled by blunderbuss-type items

        //Load finalization
        if(itemStack.stackTagCompound.getInteger("ammoPosition")==-2&&
                itemStack.stackTagCompound.getInteger("powderPosition")==-2){

            itemStack.stackTagCompound.setBoolean("loaded",true);
        }
        else{
            SaturaLanx.log.error("Something went wrong in the loadGun process, resetting NBT. Notify this to the mod author.");
            setupNBT(itemStack);
        }
    }

    public boolean canFire(EntityPlayer player, ItemStack itemStack){
        return itemStack.stackTagCompound.getBoolean("loaded") && canLight(player,itemStack) &&
                respectOneHanded(player,false) && respectMounted(player);
    }

    public boolean canLight(EntityPlayer player, ItemStack itemStack){
        return Util.canPlayerLight(player);
    }

    public void fireGun(EntityPlayer player, ItemStack itemStack, World world){

        NBTTagCompound itemTag = itemStack.stackTagCompound.getCompoundTag("ammoItem");

        //Resetting NBT as the gun is fired
        //itemStack.stackTagCompound.setTag("ammoItem",itemStack.writeToNBT(new NBTTagCompound()));
        itemStack.stackTagCompound.setInteger("ammoPosition",-1);
        itemStack.stackTagCompound.setInteger("powder",-1);
        itemStack.stackTagCompound.setInteger("powderPosition",-1);
        itemStack.stackTagCompound.setBoolean("loaded",false);

        //Getting ammodata from the gun's nbt
        ItemStack ammoItem = ItemStack.loadItemStackFromNBT(itemTag);
        if(ammoItem.getItem() instanceof PaperCartridgeItem)
            ammoItem = new ItemStack(((PaperCartridgeItem) ammoItem.getItem()).getContainedItem(),1,ammoItem.getItemDamage());

        AmmoData ammoData = getAmmoData(ammoItem);

        //Preparing the bullet entity
        EntityBullet bullet = new EntityBullet(world, player, 2.5F);
        bullet.setShouldPickup((byte)1);
        bullet.setPickupItem(ammoItem.getItem());
        bullet.setDamageTaken((short) ammoItem.getItemDamage());
        float damage = bulletDamage * ammoData.getDamageModifier();
        float extraDamage = (bulletDamage * ammoData.getDamageModifier()) * AnvilManager.getDamageBuff(itemStack);
        bullet.setDamage(damage + extraDamage);

        world.playSoundAtEntity(player,MODID + ":handgonne",1.0F,1.0F);
        spawnSmoke(player);
        if(!world.isRemote){
            world.spawnEntityInWorld(bullet);
        }

        if(!player.capabilities.isCreativeMode)
            itemStack.damageItem(1,player);

    }

    private void spawnSmoke(EntityPlayer player) {
        for(int i = 0;i<15;i++) {
            int x = Math.random() < 0.5 ? 1 : -1;
            int z = Math.random() < 0.5 ? 1 : -1;
            Vec3 vecDir = player.getLookVec().addVector(player.getRNG().nextGaussian()*0.2,player.getRNG().nextGaussian()*0.2,player.getRNG().nextGaussian()*0.2);
            player.worldObj.spawnParticle("smoke", player.posX + vecDir.xCoord, player.posY + vecDir.yCoord + player.getEyeHeight(), player.posZ + vecDir.zCoord, 0.025*x,0.025,0.025*z);
        }
    }

    @Override
    public EnumSize getSize(ItemStack stack) {
        return EnumSize.LARGE;
    }

    @Override
    public EnumWeight getWeight(ItemStack stack) {
        return EnumWeight.HEAVY;
    }

    @Override
    public EnumItemReach getReach(ItemStack itemStack) {
        return EnumItemReach.MEDIUM;
    }

    @Override
    public boolean canStack() {
        return false;
    }

    public boolean isOneHanded() {
        return oneHanded;
    }

    public void setOneHanded(boolean oneHanded) {
        this.oneHanded = oneHanded;
    }

    public boolean isMounted() {
        return mounted;
    }

    public void setMounted(boolean mounted) {
        this.mounted = mounted;
    }

    public int getLoadingTime() {
        return loadingTime;
    }

    public void setLoadingTime(int loadingTime) {
        this.loadingTime = loadingTime;
    }

    public int getFiringTime() {
        return firingTime;
    }

    public void setFiringTime(int firingTime) {
        this.firingTime = firingTime;
    }

    public int getBulletNumber() {
        return bulletNumber;
    }

    public void setBulletNumber(int bulletNumber) {
        this.bulletNumber = bulletNumber;
    }

    public int getBulletDamage() {
        return bulletDamage;
    }

    public void setBulletDamage(int bulletDamage) {
        this.bulletDamage = bulletDamage;
    }
}
