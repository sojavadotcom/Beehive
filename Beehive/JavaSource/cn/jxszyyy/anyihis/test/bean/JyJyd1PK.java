package cn.jxszyyy.anyihis.test.bean;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the aaa database table.
 * 
 */
@Embeddable
public class JyJyd1PK implements Serializable {
	private static final long serialVersionUID = 6868522694382826144L;

	@Column(name="JYDH")
	private Integer jydh;

	@Column(name="XH")
	private Short xh;

	public JyJyd1PK() {}

	public JyJyd1PK(Integer jydh, Short xh) {
		this.jydh = jydh;
		this.xh = xh;
	}

	public Integer getJydh() {
		return jydh;
	}

	public void setJydh(Integer jydh) {
		this.jydh = jydh;
	}

	public Short getXh() {
		return xh;
	}

	public void setXh(Short xh) {
		this.xh = xh;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof JyJyd1PK)) {
			return false;
		}
		JyJyd1PK castOther = (JyJyd1PK)other;
		return 
			this.jydh.equals(castOther.jydh)
			&& this.xh.equals(castOther.xh);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.jydh.hashCode();
		hash = hash * prime + this.xh.hashCode();
		
		return hash;
	}
}