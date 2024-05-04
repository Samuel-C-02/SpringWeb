package it.pc.test.WebSpringApp.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Base Interface for Enum to retrieve their values from int
 *
 * @param <E> Enum that implements this interface
 */
public interface IEnum<E extends Enum<E> & IEnum<E>> {



    Integer getId();

    /**
     * Given the Id return the corresponding enum. Es APPLE(2) -> getEnumByValue(CustomEnumType.class, 2) -> APPLE
     *
     * @param enumClass  enum class
     * @param value      value id
     * @param <EnumTYpe>
     * @return enum value
     */
    static <EnumTYpe extends Enum<EnumTYpe> & IEnum<EnumTYpe>> EnumTYpe getEnumByValueStatic(Class<EnumTYpe> enumClass, int value) {
        Map<Integer, EnumTYpe> enumValueMap = getEnumValueMap(enumClass);
        EnumTYpe result = enumValueMap.get(value);
        if (result == null) {
            throw new IllegalArgumentException("No enum constant with value " + value);
        }
        return result;
    }

    /**
     * Get the correct Map(int, enum)
     */
    private static <EnumTYpe extends Enum<EnumTYpe> & IEnum<EnumTYpe>> Map<Integer, EnumTYpe> getEnumValueMap(Class<EnumTYpe> enumClass) {
        return EnumValueCache.getOrCache(enumClass);
    }

    class EnumValueCache {
        /**
         * Map containing all Maps -> key: Id, Value: Enum
         */
        private static final Map<Class<?>, Map<Integer, ?>> cachedEnumsMap = new HashMap<>();

        /**
         * Get Map of Enum if present, otherwise add enum to the map and return it
         *
         * @param enumClass enum class type
         * @return Map (int, enum)
         */
        @SuppressWarnings("unchecked")
        static <EnumTYpe extends Enum<EnumTYpe> & IEnum<EnumTYpe>> Map<Integer, EnumTYpe> getOrCache(Class<EnumTYpe> enumClass) {
            return (Map<Integer, EnumTYpe>) cachedEnumsMap.computeIfAbsent(enumClass, (c) -> createEnumValueMap(enumClass));
        }

        /**
         * Given the Enum Class, loop through all enums inside it and adds them in a Map (int, value)
         */
        private static <EnumTYpe extends Enum<EnumTYpe> & IEnum<EnumTYpe>> Map<Integer, EnumTYpe> createEnumValueMap(Class<EnumTYpe> enumClass) {
            Map<Integer, EnumTYpe> map = new HashMap<>();
            for (EnumTYpe enumConstant : enumClass.getEnumConstants()) {
                map.put(enumConstant.getId(), enumConstant);
            }
            return map;
        }
    }

}
