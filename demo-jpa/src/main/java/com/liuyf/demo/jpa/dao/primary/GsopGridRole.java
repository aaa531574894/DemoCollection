/**
 *
 */
package com.liuyf.demo.jpa.dao.primary;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 网格人员角色表
 *
 */
@Table(name = "GSOP_GRID_ROLE")
@Entity
@Data
public class GsopGridRole {

    /**角色编号*/
    @Id
    @Column(name = "role_id")
    private String roleId;
    /**角色名称*/
    @Column(name = "role_name")
    private String roleName;
    /**生效的地域级别,  用 , 分割*/
    @Column(name = "effect_level")
    private String effectLevel;
    /**层级角色是否唯一?  Y 唯一,N 不唯一   即在某个分公司或网格内指定角色只可有一个人 */
    @Column(name = "is_unique")
    private String isUnique;
    /***是否可见*/
    @Column(name = "is_visible")
    private String isVisible;
    /**角色级别（1:省级,2:地市级,3:县市级,4:区域级,5:网格级）*/


    @Column(name = "role_level")
    private String roleLevel;

    @Column(name = "grid_org_level")
    private String gridOrgLevel;


}
