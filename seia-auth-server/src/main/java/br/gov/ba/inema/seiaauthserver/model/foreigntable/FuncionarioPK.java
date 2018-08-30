package br.gov.ba.inema.seiaauthserver.model.foreigntable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class FuncionarioPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "ide_pessoa_fisica")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ide_area")
	private Area area;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
}
