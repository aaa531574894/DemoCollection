package com.yifei.demo.entity;


import com.yifei.demo.config.TypeEnum;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.util.Comparator;
import java.util.Objects;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "update IMAGE  set  is_deleted = true, delete_time = now()  where id=? and version=?")
@Where(clause = "is_deleted = false")
@Entity(name = "IMAGE")
public class Image extends BaseEntity  {
    private static final long serialVersionUID = 7983953379794723203L;

    @Column(name = "UUID", columnDefinition = "varchar(128)  unique not null  comment '唯一id'")
    private String uuid;

    @Column(name = "URL", columnDefinition = "varchar(256) not null comment '图片链接url'")
    @NotEmpty
    private String url;

    @Column(length = 128, columnDefinition = "varchar(128) comment '图片名称'")
    private String caption;

    @Column(length = 256, columnDefinition = "varchar(256)")
    private String altTxt;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "varchar(32) not null comment '图片类别 枚举值:HOT_DESK COMMUNITY_BAR MEETING_ROOM PRIVATE_OFFICE COMMON_AREA UNKNOWN'")
    private TypeEnum.Image type;

    @Column(name = "TARGET_ID", columnDefinition = "bigint not null comment '该图片归属的entity id'")
    private Long targetId;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "varchar(32) not null comment '图片归属于哪类实体 CITY BUILDING FLOOR COMMUNITY_TEAM'")
    private TypeEnum.Image.TargetType targetType;


    @Column(name = "USED_BY_WHO", columnDefinition = " integer not null  comment '被哪些渠道所使用' ")
    private Long usedByWho;

    @Column(name = "SORT_ID_0", columnDefinition = " varchar(32) comment '楼宇详情图片展示顺序' ")
    private Integer sortId0;

    @Column(name = "SORT_ID_1", columnDefinition = " varchar(32) comment '官网图片展示顺序' ")
    private Integer sortId1;


    public static Comparator<Image> buildingDetailComparator = (a, b) -> {
        if (a.getSortId0() == null) {
            return 1;
        } else if (b.getSortId0() == null) {
            return -1;
        } else if (a.getSortId0() < b.getSortId0()) {
            return -1;
        } else {
            return 0;
        }
    };

    public static Comparator<Image> cnComparator = (a, b) -> {
        if (a.getSortId1() == null) {
            return 1;
        } else if (b.getSortId1() == null) {
            return -1;
        } else if (a.getSortId1() < b.getSortId1()) {
            return -1;
        } else {
            return 0;
        }
    };



    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Image) {
            Image theOther = (Image) obj;
            return Objects.equals(this.getUuid(), theOther.getUuid())
                    && Objects.equals(this.getUrl(), theOther.getUrl())
                    && Objects.equals(this.getCaption(), theOther.getCaption())
                    && Objects.equals(this.getType(), theOther.getType());
        }
        return false;
    }

}
