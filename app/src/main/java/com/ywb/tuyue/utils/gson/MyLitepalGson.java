package com.ywb.tuyue.utils.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @Author soonphe
 * @Date 2018-08-31 18:59
 * @Descprition
 */

public class MyLitepalGson extends MyGson {

    public MyLitepalGson() {
        super(customizeGson());
    }


    private static String[] litepalExcludes = {"baseObjId", "associatedModelsMapWithFK",
            "associatedModelsMapWithoutFK", "associatedModelsMapForJoinTable",
            "listToClearSelfFK", "listToClearAssociatedFK", "fieldsToSetToDefault"};
    private static Gson customizeGson() {
        return new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                for (String exclude : litepalExcludes) {
                    if (exclude.equals(f.getName())) {
                        return true;
                    }
                }
                return false;
            }
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create();
    }

}
