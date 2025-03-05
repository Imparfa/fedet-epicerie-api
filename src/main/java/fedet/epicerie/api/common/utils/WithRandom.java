package fedet.epicerie.api.common.utils;

import org.jeasy.random.EasyRandom;

import java.util.UUID;

public interface WithRandom {

    EasyRandom EASY_RANDOM = new EasyRandom();

    default <T> T random(Class<T> clazz) {
        return EASY_RANDOM.nextObject(clazz);
    }

    default String randomString() {
        return EASY_RANDOM.nextObject(String.class);
    }

    default Integer randomInt() {
        return EASY_RANDOM.nextObject(Integer.class);
    }

    default Boolean randomBoolean() {
        return EASY_RANDOM.nextObject(Boolean.class);
    }

    default UUID randomUUID() {
        return UUID.randomUUID();
    }
}