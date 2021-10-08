package de.lvl.nyrval.events;

import de.lvl.nyrval.LvlAddonMain;
import de.lvl.nyrval.settings.Settings;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MyToolTipEvent {

    @SubscribeEvent
    public void onToolTip(ItemTooltipEvent e) {
        if (Minecraft.getMinecraft().world != null && Settings.getINSTANCE().isAddonOn() && LvlAddonMain.isOnServer()) {

            if (!e.getItemStack().isEmpty()) {
                String sign = "";
                NBTTagCompound nbt = new NBTTagCompound();
                NBTTagCompound itemsNBTTags = new NBTTagCompound();
                NBTTagList nbtTagList = new NBTTagList();
                if (e.getItemStack().getTagCompound() != null) {
                    nbt = e.getItemStack().getTagCompound();
                    itemsNBTTags = e.getItemStack().getTagCompound().getCompoundTag("display");
                    nbtTagList = e.getItemStack().getTagCompound().getCompoundTag("display").getTagList("Lore", 8);
                    sign = String.valueOf(e.getItemStack().getTagCompound().getCompoundTag("display").getTagList("Lore", 8));
                }
                if (!sign.contains("§c§l[LvlAddon] §e§lLevel ")) {

                    nbtTagList.appendTag(new NBTTagString(itemsNBTTags.getString("Lore")));

                    nbtTagList.appendTag(new NBTTagString("§c§l[LvlAddon] §e§lLevel " + (e.getItemStack().getRepairCost() > 39 ? "Zu Teuer!" : e.getItemStack().getRepairCost())));

                    itemsNBTTags.setTag("Lore", nbtTagList);


                    nbt.setTag("display", itemsNBTTags);
                    e.getItemStack().setTagCompound(nbt);
                }
            }


        } else if (Minecraft.getMinecraft().world != null && !Settings.getINSTANCE().isAddonOn()) {

            if (!e.getItemStack().isEmpty() && e.getItemStack().getTagCompound() != null) {
                NBTTagCompound nbt = e.getItemStack().getTagCompound();
                NBTTagCompound itemsNBTTags = e.getItemStack().getTagCompound().getCompoundTag("display");

                NBTTagList nbtTagList = e.getItemStack().getTagCompound().getCompoundTag("display").getTagList("Lore", 8);
                if (String.valueOf(e.getItemStack().getTagCompound().getCompoundTag("display").getTagList("Lore", 8)).contains("§c§l[LvlAddon] §e§lLevel ")) {
                    if (nbtTagList.tagCount() > 1) {
                        nbtTagList.removeTag(nbtTagList.tagCount() - 1);
                    }
                    nbtTagList.removeTag(nbtTagList.tagCount() - 1);

                    if (!nbtTagList.hasNoTags())
                        itemsNBTTags.setTag("Lore", nbtTagList);

                    nbt.setTag("display", itemsNBTTags);
                    e.getItemStack().setTagCompound(nbt);
                }


            }
        }


    }

}

