package com.yifei.demo.entity;




import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;


@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1751903709123246343L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "create_time", columnDefinition = "dateTime not null comment '创建时间'", updatable = false)
    private ZonedDateTime createTime;

    @Column(name = "update_time", columnDefinition = "dateTime not null comment  '最近修改时间'")
    private ZonedDateTime updateTime;

    @Column(name = "delete_time", columnDefinition = "dateTime comment '删除时间'")
    private ZonedDateTime deleteTime;

    @Column(name = "is_deleted", columnDefinition = "bit not null comment '是否被删除'")
    private boolean isDeleted;

    @Column(name = "sort_id", columnDefinition = "integer comment '排序id'")
    private Integer sortId;

    @Version
    @Column(columnDefinition = "integer not null default 0 comment '乐观锁版本号'", insertable = false)
    private Integer version;


}
