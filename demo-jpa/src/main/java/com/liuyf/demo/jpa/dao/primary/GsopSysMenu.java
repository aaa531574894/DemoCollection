/**
 *
 */
package com.liuyf.demo.jpa.dao.primary;

import java.util.Date;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author t-xiabin
 *
 */
@Table(name = "GSOP_SYS_MENU")
@Entity
@Data
public class GsopSysMenu {

	@Id
	@Column(name = "id" )
	private String id;
	@Column(name = "code" )
	private String code;

	@Column(name = "name" )
	private String name;

	@Column(name = "type" )
	private Integer type;

	@Column(name = "parent_id" )
	private String parentId;

	@Column(name = "url" )
	private String url;

	@Column(name = "vue_path" )
	private String vuePath;

	@Column(name = "icon_type" )
	private String iconType;

	@Column(name = "icon_value" )
	private String iconValue;

	@Column(name = "outside_type" )
	private Integer outsideType;

	@Column(name = "order_id" )
	private Long orderId;

	@Column(name = "status" )
	private Integer status;

	@Column(name = "creator" )
	private String creator;

	@Column(name = "create_time" )
	private Date createTime;

	@Column(name = "last_updator" )
	private String lastUpdator;

	@Column(name = "last_update_time" , columnDefinition = " datetime(6) comment '最后更新时间' ")
	private Date lastUpdateTime;

	@Column(name = "menu_type" )
	private String menuType;

	/**
	 * 是否限制某区域层级不可访问
	 */
	@Column(name = "is_level_limit" )
	private Integer isLevelLimit;


	/**
	 * 不可访问的区域层级
	 */
	@Column(name = "limit_level" )
	private String limitLevel;

	@Column(name = "is_audit" )
	private String isAudit;

	@Column(name = "audit_code" )
	private String auditCode;


}
