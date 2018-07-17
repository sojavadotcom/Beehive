package com.sojava.beehive.framework.component.catcher.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the catch_article database table.
 * 
 */
@Entity
@Table(name="catch_article", schema="public")
@NamedQuery(name="CatchArticle.findAll", query="SELECT c FROM CatchArticle c")
public class CatchArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CATCH_ARTICLE_ID_GENERATOR", sequenceName="CATCH_ARTICLE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CATCH_ARTICLE_ID_GENERATOR")
	private Integer id;

	private String content;

	private String date;

	private String kind;

	private String title;

	private String url;

	public CatchArticle() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}