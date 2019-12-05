package net.sppan.base.entity.support;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -250118731239275742L;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@Column(updatable = false, insertable = false, columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP")
	private Date createTime;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@Column(insertable = false, columnDefinition = "DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date UpdateTime;

}