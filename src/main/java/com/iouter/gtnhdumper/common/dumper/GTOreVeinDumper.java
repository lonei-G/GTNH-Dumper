package com.iouter.gtnhdumper.common.dumper;

import com.iouter.gtnhdumper.common.base.WikiDumper;
import com.iouter.gtnhdumper.common.utils.GtCompat;
import gtneioreplugin.util.GT5OreLayerHelper;
import net.minecraft.util.ChatComponentTranslation;

import java.io.File;
import java.util.LinkedList;

public class GTOreVeinDumper extends WikiDumper {

    public GTOreVeinDumper() {
        super("tools.dump.gtnhdumper.gtorevein");
    }

    @Override
    public String[] header() {
        return new String[] {
            "key",
            "localizedName",
            "primary",
            "secondary",
            "between",
            "sporadic",
            "size",
            "density",
            "weight",
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
        for (GT5OreLayerHelper.OreLayerWrapper vein : GT5OreLayerHelper.mapOreLayerWrapper.values()) {
            String[] heightRange = vein.worldGenHeightRange.split("-");
            list.add(new Object[]{
                vein.veinName,
                vein.localizedName,
                GtCompat.materialName(vein.mPrimaryVeinMaterial),
                GtCompat.materialName(vein.mSecondaryMaterial),
                GtCompat.materialName(vein.mBetweenMaterial),
                GtCompat.materialName(vein.mSporadicMaterial),
                vein.size,
                vein.density,
                vein.randomWeight,
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
            "nei.options.tools.dump.gtnhdumper.gtorevein.dumped", "dumps/" + file.getName());
    }
}
