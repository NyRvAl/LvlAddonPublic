package de.lvl.nyrval.events;

import de.lvl.nyrval.LvlAddonMain;
import de.lvl.nyrval.settings.Settings;
import net.labymod.main.LabyMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import scala.collection.parallel.ParIterableLike;

public class MyTickEvent {

    @SubscribeEvent
    public void MyTickEvent(TickEvent e) {
        if (Minecraft.getMinecraft().world != null &&Settings.getINSTANCE().isAddonon()&& LvlAddonMain.isOnServer()) {
            if (Minecraft.getMinecraft().player.openContainer != null) {
                for (Slot slot : Minecraft.getMinecraft().player.openContainer.inventorySlots) {
                    if (!slot.getStack().isEmpty()) {
                        String sign = "";

                        NBTTagCompound nbt = new NBTTagCompound();
                        NBTTagCompound itemsNBTTags = new NBTTagCompound();
                        NBTTagList nbtTagList = new NBTTagList();
                        if (slot.getStack().getTagCompound() != null) {
                            nbt = slot.getStack().getTagCompound();
                            itemsNBTTags = slot.getStack().getTagCompound().getCompoundTag("display");
                            nbtTagList = slot.getStack().getTagCompound().getCompoundTag("display").getTagList("Lore", 8);
                            sign = String.valueOf(slot.getStack().getTagCompound().getCompoundTag("display").getTagList("Lore", 8));
                        }
                        if (!sign.contains("§c§l[LvlAddon] §e§lLevel ")) {

                            nbtTagList.appendTag(new NBTTagString(itemsNBTTags.getString("Lore")));


                            nbtTagList.appendTag(new NBTTagString("§c§l[LvlAddon] §e§lLevel " + String.valueOf(slot.getStack().getRepairCost() > 39 ? "Zu Teuer!" : slot.getStack().getRepairCost())));

                            itemsNBTTags.setTag("Lore", nbtTagList);


                            nbt.setTag("display", itemsNBTTags);
                            System.out.println("Set NBTTag "+nbt);
                            slot.getStack().setTagCompound(nbt);
                        }
                    }

                }
            }
        } else if (Minecraft.getMinecraft().world != null && !Settings.getINSTANCE().isAddonon()) {
            if (Minecraft.getMinecraft().player.openContainer != null) {
                for (Slot slot : Minecraft.getMinecraft().player.openContainer.inventorySlots) {
                    if (!slot.getStack().isEmpty() && slot.getStack().getTagCompound() != null) {
                        NBTTagCompound nbt = slot.getStack().getTagCompound();
                        NBTTagCompound itemsNBTTags = slot.getStack().getTagCompound().getCompoundTag("display");

                        NBTTagList nbtTagList = slot.getStack().getTagCompound().getCompoundTag("display").getTagList("Lore", 8);
                        if (String.valueOf(slot.getStack().getTagCompound().getCompoundTag("display").getTagList("Lore", 8)).contains("§c§l[LvlAddon] §e§lLevel ")) {
                            if (nbtTagList.tagCount() > 1) {
                                nbtTagList.removeTag(nbtTagList.tagCount() - 1);
                            }
                            nbtTagList.removeTag(nbtTagList.tagCount() - 1);

                            if (!nbtTagList.hasNoTags())
                                itemsNBTTags.setTag("Lore", nbtTagList);

                            nbt.setTag("display", itemsNBTTags);
                            slot.getStack().setTagCompound(nbt);
                        }


                    }
                }


            }

        }

    }
}
