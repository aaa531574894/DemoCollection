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
 * @author t-xiabin
 *
 */
@Table(name = "GSOP_SYS_MENU_ROLE_MAP")
@Entity
@Data
public class GsopSysMenuRoleMap {

    @Id
    @Column(name = "MENU_ID", unique = true, length = 50)
    private String menuId;
    @Column(name = "ROLE_ID", length = 50)
    private String roleId;


}
