package com.hantsylabs.restexample.springmvc.model;

import java.io.Serializable;
import java.util.Date;

import org.hdiv.services.SecureIdentifiable;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 *
 */
public class CommentDetails implements Serializable, SecureIdentifiable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String content;

	private Date createdDate;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(final Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "CommentDetails{" + "id=" + id + ", content=" + content + ", createdDate=" + createdDate + '}';
	}

}
