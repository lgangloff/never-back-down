package org.lgangloff.nbd.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "file")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class File extends AbstractAuditingEntity {
	
	@Id
    @GeneratedValue(generator = "file_seq")
    @SequenceGenerator(name = "file_seq",
            sequenceName = "file_id_seq", initialValue = 1, allocationSize = 20)
    private Long id;

    @Size(max = 36)
    @Column(name = "uuid", length = 36, nullable = false, unique = true)
	private String uuid;
    
    @Column(name = "name", length = 256, nullable = false, unique = false)
	private String name;

    @Column(name = "content_type", length = 36)
	private String contentType;
	
	private Long size;
	
	
	@JsonIgnore
	@Basic(fetch=FetchType.LAZY)
	private byte[] datas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public byte[] getDatas() {
		return datas;
	}

	public void setDatas(byte[] datas) {
		this.datas = datas;
	}
}
