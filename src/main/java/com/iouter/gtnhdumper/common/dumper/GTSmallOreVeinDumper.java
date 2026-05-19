package com.iouter.gtnhdumper.common.dumper;

import com.iouter.gtnhdumper.common.base.WikiDumper;
import com.iouter.gtnhdumper.common.utils.GtCompat;
import gtneioreplugin.util.GT5OreSmallHelper;
import net.minecraft.util.ChatComponentTranslation;

import java.io.File;
import java.util.LinkedList;

public class GTSmallOreVeinDumper extends WikiDumper {

    public GTSmallOreVeinDumper() {
        super("tools.dump.gtnhdumper.gtsmallorevein");
    }

    @Override
    public String[] header() {
        return new String[] {
            "key",
            "material",
            "amountPerChunk",
            "minY",
            "maxY",
            "dims"
        };
    }

    @Override
    public int getKeyIndex() {
        return 0;
    }

    @Override
    public String getKeyStr() {
        return "key";
    }

    @Override
    public Iterable<Object[]> dumpObject(int mode) {
        LinkedList<Object[]> list = new LinkedList<>();
        for (GT5OreSmallHelper.OreSmallWrapper vein : GT5OreSmallHelper.mapOreSmallWrapper.values()) {
            String[] heightRange = vein.worldGenHeightRange.split("-");
            list.add(new Object[] {
                vein.oreGenName,
                GtCompat.materialName(vein.getOreMaterial()),
                vein.amountPerChunk,
                heightRange[0],
                heightRange[1],
                GtCompat.formatEnabledDimensions(vein.allowedDimWithOrigNames)
            });
        }
        return list;
    }

    @Override
    public ChatComponentTranslation dumpMessage(File file) {
        return new ChatComponentTranslation(
            "nei.options.tools.dump.gtnhdumper.gtsmallorevein.dumped", "dumps/" + file.getName());
    }
}
