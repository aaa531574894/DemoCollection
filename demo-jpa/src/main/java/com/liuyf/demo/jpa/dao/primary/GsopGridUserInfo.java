package com.liuyf.demo.jpa.dao.primary;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * 网格人员信息表
 *
 * @author t-xiabin
 * @date 2019年7月22日
 */
@Table(name = "GSOP_GRID_USER_INFO")
@Entity
@Data
public class GsopGridUserInfo {

    /**
     * 编号（主键）
     */
    @Id
    private String id;
    /**
     * 4a主账号
     */
    @Column(name = "main_acct")
    private String mainAcct;
    /**
     * 网格系统子账号
     */

    @Column(name = "user_id")
    private String userId;
    /**
     * 用户姓名
     */
    @Column(name = "user_name")
    private String userName;
    /**
     * 用户手机号码
     */
    @Column(name = "user_phone")
    private String userPhone;
    /**
     * 用户邮箱
     */
    @Column(name = "user_email")
    private String userEmail;
    /**
     * 状态（0:编辑中,1:已发布）
     */
    @Column(columnDefinition = "tinyint")
    private Integer status;
    /**
     * 任务描述
     */
    private String descrp;
    /**
     * 创建人账号
     */
    private String creator;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 最后修改人账号
     */
    @Column(name = "last_updator")
    private String lastUpdator;
    /**
     * 最后修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_update_time")
    private Date lastUpdateTime;
    /**
     * 客户经理编号
     */
    @Column(name = "cust_mgr_id")
    private String custMgrId;
    /**
     * 归属组织id
     */
    @Column(name = "org_id")
    private String orgId;
    /**
     * 绑定的网格id
     */
    @Column(name = "grid_id")
    private String gridId;
    /**
     * 绑定的分公司id
     */
    @Column(name = "county_id")
    private String countyId;
    /**
     * 绑定的直辖市id
     */
    @Column(name = "prov_id")
    private String provId;
    /**
     * 区域
     */
    @Column(name = "area_id")
    private String areaId;

    /**
     * 备用拓展字段
     */
    private String ext_1;

    private String ext_2;

    private String ext_3;

}
