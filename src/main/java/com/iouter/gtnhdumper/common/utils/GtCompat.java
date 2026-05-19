package com.iouter.gtnhdumper.common.utils;

import gregtech.api.enums.Materials;

import java.util.Map;
import java.util.stream.Collectors;

public final class GtCompat {

    private GtCompat() {}

    public static String materialName(Materials material) {
        return material == null ? "" : material.mName;
    }

    public static String formatEnabledDimensions(Map<String, Boolean> dims) {
        if (dims == null || dims.isEmpty()) {
            return "";
        }
        return dims.entrySet().stream()
            .filter(Map.Entry::getValue)
            .map(Map.Entry::getKey)
            .collect(Collectors.joining(","));
    }
}
