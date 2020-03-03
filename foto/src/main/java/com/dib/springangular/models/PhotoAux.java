package com.dib.springangular.models;

import java.io.Serializable;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
public class PhotoAux implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -1955344154884507672L;

	@Id
    private String id;
	
    private String title;
        
    private byte[] image;
    
    private String id_user;

    public String getId_user() {
		return id_user;
	}

	public void setId_user(String id_user) {
		this.id_user = id_user;
	}

	public PhotoAux() {
		super();
	}

	public PhotoAux(String id, String title, byte[] image) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Photo [id=" + id + ", title=" + title + ", image=" + image + "]";
    }
    
}