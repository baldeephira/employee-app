/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Baldeep Hira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.bhira.sample.model;

import java.io.Serializable;
import java.util.Date;

import net.bhira.sample.common.exception.InvalidObjectException;

/**
 * The base class for all models. It implements the common properties available in all first class
 * models. All externally exposed first class models must inherit from this base model.
 * 
 * @author Baldeep Hira
 */
public abstract class BaseModel implements Serializable {

	private static final long serialVersionUID = 20140520L;

	private long id;
	private String name;
	private Date created;
	private Date modified;
	private String createdBy;
	private String modifiedBy;

	// -------------------------UTILITY METHODS----------------------------

	/**
	 * Check this model is a new instance.
	 * 
	 * @return true if the model is a new instance.
	 */
	public boolean isNew() {
		return (id == 0);
	}

	/**
	 * Initialize the modified date and user to current time and user. If it is a new model then
	 * created time and user will also be set to current time and user.
	 */
	public void initForSave() {
		modified = new Date();
		modifiedBy = "system";
		if (isNew()) {
			created = modified;
			createdBy = modifiedBy;
		}
	}

	/**
	 * Check if this instance of BaseModel is valid. It throws and exception with detailed message
	 * in case of instance is invalid.
	 * 
	 * @throws InvalidObjectException
	 *             if instance is invalid.
	 */
	public void validate() throws InvalidObjectException {
		if (name == null || name.trim().length() == 0) {
			throw new InvalidObjectException("The required attribute 'name' is missing.");
		}
		if (created == null) {
			throw new InvalidObjectException("The required attribute 'created' is missing.");
		}
		if (modified == null) {
			throw new InvalidObjectException("The required attribute 'modified' is missing.");
		}
		if (createdBy == null || createdBy.trim().length() == 0) {
			throw new InvalidObjectException("The required attribute 'createdBy' is missing.");
		}
		if (modifiedBy == null || modifiedBy.trim().length() == 0) {
			throw new InvalidObjectException("The required attribute 'modifiedBy' is missing.");
		}
	}

	// -------------------------GETTERS AND SETTERS-------------------------

	/**
	 * Get the ID for the model.
	 * 
	 * @return the ID of the model.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Set the ID for the model.
	 * 
	 * @param id
	 *            the ID of the model.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Get the name of the model.
	 * 
	 * @return the name of the model.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name for the model.
	 * 
	 * @param name
	 *            the name of the model.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the creation time for this model.
	 * 
	 * @return the time this model was created.
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Set the creation time for this model.
	 * 
	 * @param created
	 *            the time this model was created.
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Get the last modification time for this model.
	 * 
	 * @return the time this model was last modified.
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * Set the last modification time for this model.
	 * 
	 * @param modified
	 *            the time this model was last modified.
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * Get the name of the user who created this model.
	 * 
	 * @return the name of user who created this model.
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Set the name of the user who created this model.
	 * 
	 * @param createdBy
	 *            the name of the user who created this model.
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Get the name of the user who last modified this model
	 * 
	 * @return the name of the user who last modified this model
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Set the name of the user who last modified this model.
	 * 
	 * @param modifiedBy
	 *            the name of the user who last modified this model
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}