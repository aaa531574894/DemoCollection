/**
 *
 */
package com.liuyf.demo.jpa.dao.primary;

import java.io.Serializable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 网格人员角色关系表
 * @author t-xiabin
 * @date 2019年7月22日
 *
 */
@Table(name = "GSOP_GRID_USER_ROLE_MAP")
@Entity
@Data
public class GsopGridUserRoleMap implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8445014039586282296L;
	/**编号（主键）*/
	@Id
	private String id;
	/**人员信息编号（对应GSOP_GRID_USER_INFO.ID）*/

	@Column(name = "grid_user_id")
	private String gridUserId;
	/**人员角色（对应GSOP_GRID_ROLE.ROLE_ID）*/
	@Column(name = "role_id")
	private String roleId;
	/**省编号*/
	@Column(name = "prov_id")
	private String provId;
	/**地市编号*/
	@Column(name = "city_id")
	private String cityId;
	/**县市编号*/
	@Column(name = "county_id")
	private String countyId;
	/**区域编号*/
	@Column(name = "area_id")
	private String areaId;
	/**网格编号*/
	@Column(name = "grid_id")
	private String gridId;
	@Column(name = "group_id")
	private String groupId;

}
