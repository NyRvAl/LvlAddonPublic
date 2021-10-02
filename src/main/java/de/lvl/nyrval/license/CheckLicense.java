package de.lvl.nyrval.license;

import javax0.license3j.License;
import javax0.license3j.io.IOFormat;
import javax0.license3j.io.LicenseReader;
import net.labymod.addon.AddonLoader;
import net.labymod.utils.UUIDFetcher;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;

public class CheckLicense {
    private License lic;
    private static CheckLicense INSTANCE;
    private Version version;

    public CheckLicense() {
        INSTANCE = this;
    }

    public boolean isOkay() {
        try (LicenseReader reader = new LicenseReader(new File(AddonLoader.getAddonsDirectory()+"\\LevelAddon.key"))) {
            lic = reader.read(IOFormat.BASE64);
        } catch (IOException e) {
            System.out.println("keinen Key gefunden!");
            setVersion(Version.FREE);
            return false;
        }
        catch (IllegalArgumentException e){
            System.out.println("der Key stimmt nicht!");
            setVersion(Version.FREE);
            return false;
        }

// encode the public key into your application
// (you can copy paste this from License3jRepl after key generation, see later)
        byte [] key = new byte[] {
                (byte)0x52,
                (byte)0x53, (byte)0x41, (byte)0x00, (byte)0x30, (byte)0x82, (byte)0x01, (byte)0x22, (byte)0x30,
                (byte)0x0D, (byte)0x06, (byte)0x09, (byte)0x2A, (byte)0x86, (byte)0x48, (byte)0x86, (byte)0xF7,
                (byte)0x0D, (byte)0x01, (byte)0x01, (byte)0x01, (byte)0x05, (byte)0x00, (byte)0x03, (byte)0x82,
                (byte)0x01, (byte)0x0F, (byte)0x00, (byte)0x30, (byte)0x82, (byte)0x01, (byte)0x0A, (byte)0x02,
                (byte)0x82, (byte)0x01, (byte)0x01, (byte)0x00, (byte)0xAD, (byte)0xA0, (byte)0xB5, (byte)0x92,
                (byte)0x97, (byte)0xEB, (byte)0x5F, (byte)0xBB, (byte)0xFC, (byte)0x14, (byte)0xED, (byte)0x32,
                (byte)0x6E, (byte)0x64, (byte)0xCE, (byte)0xBF, (byte)0x23, (byte)0xA2, (byte)0xEC, (byte)0xE1,
                (byte)0x12, (byte)0x28, (byte)0x82, (byte)0x22, (byte)0xC7, (byte)0x5B, (byte)0xDE, (byte)0xB9,
                (byte)0xFF, (byte)0x82, (byte)0xE2, (byte)0x3F, (byte)0x35, (byte)0x35, (byte)0x47, (byte)0x8D,
                (byte)0xBA, (byte)0x05, (byte)0x53, (byte)0x08, (byte)0xA5, (byte)0xAC, (byte)0x73, (byte)0x91,
                (byte)0x5D, (byte)0x9A, (byte)0x09, (byte)0x8B, (byte)0x27, (byte)0xC7, (byte)0xF5, (byte)0xB2,
                (byte)0x72, (byte)0xB8, (byte)0xC5, (byte)0xBB, (byte)0xF2, (byte)0x4E, (byte)0xE5, (byte)0xAA,
                (byte)0x74, (byte)0xFE, (byte)0x7E, (byte)0x0E, (byte)0x78, (byte)0x5D, (byte)0x91, (byte)0x15,
                (byte)0x8F, (byte)0x6B, (byte)0xF0, (byte)0x46, (byte)0xC6, (byte)0xAD, (byte)0x24, (byte)0xDA,
                (byte)0x20, (byte)0xFA, (byte)0xAF, (byte)0x68, (byte)0x8F, (byte)0x08, (byte)0x12, (byte)0x12,
                (byte)0x2E, (byte)0xB1, (byte)0x75, (byte)0xDA, (byte)0x39, (byte)0xDB, (byte)0x42, (byte)0x6A,
                (byte)0x75, (byte)0xA8, (byte)0xD5, (byte)0x74, (byte)0x0A, (byte)0x0A, (byte)0x15, (byte)0xCD,
                (byte)0x61, (byte)0x68, (byte)0x52, (byte)0xF2, (byte)0x94, (byte)0xE4, (byte)0x80, (byte)0x87,
                (byte)0xEA, (byte)0xDA, (byte)0x3C, (byte)0xB2, (byte)0x20, (byte)0xE2, (byte)0xA9, (byte)0x1C,
                (byte)0x33, (byte)0xCB, (byte)0xC1, (byte)0xFD, (byte)0x2B, (byte)0xF5, (byte)0x00, (byte)0xDF,
                (byte)0x02, (byte)0x62, (byte)0x94, (byte)0xD0, (byte)0xA3, (byte)0x70, (byte)0x77, (byte)0x92,
                (byte)0x81, (byte)0x7D, (byte)0x5A, (byte)0x5A, (byte)0x28, (byte)0x55, (byte)0xC8, (byte)0x08,
                (byte)0xE0, (byte)0xF3, (byte)0xB1, (byte)0xEC, (byte)0xD1, (byte)0xD3, (byte)0x8E, (byte)0xF7,
                (byte)0x48, (byte)0x6F, (byte)0x1A, (byte)0x4B, (byte)0x6F, (byte)0x5D, (byte)0x81, (byte)0xB8,
                (byte)0xA0, (byte)0x6D, (byte)0x85, (byte)0x1E, (byte)0x31, (byte)0xF9, (byte)0x2B, (byte)0x7A,
                (byte)0x6D, (byte)0x47, (byte)0xEA, (byte)0xEE, (byte)0x9E, (byte)0x94, (byte)0xFB, (byte)0xB4,
                (byte)0xD0, (byte)0x71, (byte)0x15, (byte)0x8C, (byte)0x4F, (byte)0xF1, (byte)0xDD, (byte)0xEF,
                (byte)0xD3, (byte)0xE4, (byte)0x0E, (byte)0x1E, (byte)0x93, (byte)0x6B, (byte)0xDD, (byte)0x76,
                (byte)0xF1, (byte)0x08, (byte)0xE3, (byte)0x0D, (byte)0x1E, (byte)0x08, (byte)0x5C, (byte)0x25,
                (byte)0xED, (byte)0xB5, (byte)0x81, (byte)0x62, (byte)0x08, (byte)0x55, (byte)0x4F, (byte)0xA4,
                (byte)0xBC, (byte)0x8F, (byte)0xDE, (byte)0xB9, (byte)0xB9, (byte)0x29, (byte)0x4B, (byte)0x49,
                (byte)0xE1, (byte)0x6F, (byte)0x09, (byte)0x47, (byte)0xA6, (byte)0x3D, (byte)0xFD, (byte)0xE2,
                (byte)0xFD, (byte)0x6E, (byte)0xB7, (byte)0x6E, (byte)0x42, (byte)0x49, (byte)0xC0, (byte)0x8C,
                (byte)0x46, (byte)0xFC, (byte)0x83, (byte)0xEF, (byte)0xDF, (byte)0xE1, (byte)0xED, (byte)0x02,
                (byte)0x54, (byte)0x55, (byte)0xCD, (byte)0x56, (byte)0x5B, (byte)0xF0, (byte)0x0C, (byte)0x27,
                (byte)0xF7, (byte)0xA5, (byte)0x2C, (byte)0x4F, (byte)0xA8, (byte)0xD2, (byte)0x70, (byte)0x16,
                (byte)0xE4, (byte)0x0C, (byte)0x83, (byte)0xEB, (byte)0x02, (byte)0x03, (byte)0x01, (byte)0x00,
                (byte)0x01,
        };
// check that the license is signed properly
        if (lic.isOK(key)&&validUUID().equals(String.valueOf(UUIDFetcher.getUUID(Minecraft.getMinecraft().getSession().getUsername())).replace("-",""))&&isPremium()){
            System.out.println(lic.get("uuid").getString());
            setVersion(Version.PREMIUM);
            System.out.println("LevelAddon Premium License");
            System.out.println("-------------------------------------------");

            System.out.println(validUUID());
            System.out.println(lic.get("product").getString());
            System.out.println(isPremium());
            System.out.println("-------------------------------------------");
            return true;
        }
        System.out.println("LevelAddon Free License");


        setVersion(Version.FREE);
        return false;
    }
    public String validUUID() {
        return lic.get("uuid").getString();
    }
    public Boolean isPremium() {
        return (lic.get("product").getString()).equals("2");
    }
    private void setVersion(Version version){
        this.version = version;
    }
    public Version getVersion(){
        return this.version;
    }
}
