package com.yifei.demo.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * please add the description
 * <p>
 * <p>
 * Created by liuyf on 2021/1/5.
 */


public enum TypeEnum {
    ;

    //货币类型
    @Getter
    @AllArgsConstructor
    public enum Currency {
        CNY("CNY", "人民币"),
        USD("USD", "美元"),
        TWD("TWD", "新台币"),
        HKD("HKD", "港元");

        private final String type;
        private final String desc;
    }

    //楼宇类型
    @Getter
    @AllArgsConstructor
    public enum Building {
        VIRTUAL("VIRTUAL", "虚拟楼宇"),
        PHYSICAL("PHYSICAL", "物理楼宇"),
        ;

        private final String type;
        private final String desc;
    }

    //楼层产品类型
    @Getter
    @AllArgsConstructor
    public enum Product {
        WE_WORK("WE_WORK", "WE_WORK", 1),
        NON_WEWORK("NON_WEWORK", "NON_WEWORK", 1 << 1),
        ;

        private final String type;
        private final String desc;
        private final int    v;


        public static Product[] parseValue(final int v) {
            List<Product> result = new ArrayList<>();
            for (Product p : Product.values()) {
                if ((p.getV() & v) > 0) {
                    result.add(p);
                }
            }
            return result.toArray(new Product[0]);
        }

        public static int parseType(Product[] types) {
            Objects.requireNonNull(types);
            int value = 0;
            for (Product p : types) {
                value |= p.getV();
            }
            return value;
        }
    }

    //交通工具类别
    @Getter
    @AllArgsConstructor
    public enum Transportation {
        METRO("METRO", "地铁线路"),
        BUS("BUS", "公交线路");

        private final String type;
        private final String desc;


    }

    //tag 类型
    @Getter
    @AllArgsConstructor
    public enum Tag {
        FEATURE("FEATURE", "特色标签"),
        INFRASTRUCTURE("INFRASTRUCTURE", "基础设施标签"),
        CHARACTERISTIC_SPACE("CHARACTERISTIC_SPACE", "特色空间标签"),
        COMMERCIAL("COMMERCIAL", "商圈标签"),
        METRO("METRO", "METROMETRO"),
        BUS("BUS", "公交标签");

        private final String type;
        private final String desc;


        @Getter
        @AllArgsConstructor
        public enum Binding {
            UNKNOWN("UNKNOWN", "未知的绑定关系"),
            BUILDING("BUILDING", "楼宇与tag绑定关系"),
            FLOOR("FLOOR", "楼层与tag的绑定关系");

            private final String type;
            private final String desc;
        }
    }

    //停车场类型
    @Getter
    @AllArgsConstructor
    public enum ParkingLot {
        BUILDING_INSIDE("BUILDING_INSIDE", "楼内停车场"),
        BUILDING_OUTSIDE("BUILDING_OUTSIDE", "楼外附近停车场");

        private final String type;
        private final String desc;
    }

    //图片分类
    @Getter
    @AllArgsConstructor
    public enum Image {
        CITY_AVATAR("CITY_AVATAR", "城市头图"),

        BUILDING_AVATAR("BUILDING_AVATAR", "楼宇头图"),

        HOT_DESK("HOT_DESK", "HD"),
        COMMUNITY_BAR("COMMUNITY_BAR", "社区吧台"),
        MEETING_ROOM("MEETING_ROOM", "会议室"),
        PRIVATE_OFFICE("PRIVATE_OFFICE", "办公室"),
        COMMON_AREA("COMMON_AREA", "公共空间"),

        CM_AVATAR("CM_AVATAR", "社区经理头像"),

        FLOOR_MAP("FLOOR_MAP", "楼层设计图"),

        UNKNOWN("UNKNOWN", "未知");

        private final String type;
        private final String desc;

        // 楼宇宣传图片库 group
        public final static Set<Image> BUILDING_PROPAGANDA_GROUP = new HashSet<Image>() {{
            add(HOT_DESK);
            add(COMMUNITY_BAR);
            add(MEETING_ROOM);
            add(PRIVATE_OFFICE);
            add(COMMON_AREA);

            add(UNKNOWN);
        }};

        @Getter
        @AllArgsConstructor
        public enum TargetType {
            CITY("CITY", "图片归属城市"),
            BUILDING("BUILDING", "图片归属楼宇"),
            FLOOR("FLOOR", "图片归属楼层"),
            COMMUNITY_TEAM("COMMUNITY_TEAM", "图片归属社区团队"),
            ;

            private final String type;
            private final String desc;
        }


    }
}
