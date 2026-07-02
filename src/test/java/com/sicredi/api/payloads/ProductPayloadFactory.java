package com.sicredi.api.payloads;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ProductPayloadFactory {

    private ProductPayloadFactory() {
    }

    public static Map<String, Object> validProduct() {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("title", "Perfume Oil");
        payload.put("description", "Mega Discount, Impression of A...");
        payload.put("price", 13);
        payload.put("discountPercentage", 8.4);
        payload.put("rating", 4.26);
        payload.put("stock", 65);
        payload.put("brand", "Impression of Acqua Di Gio");
        payload.put("category", "fragrances");
        payload.put("thumbnail", "https://i.dummyjson.com/data/products/11/thumbnail.jpg");
        return payload;
    }
}
