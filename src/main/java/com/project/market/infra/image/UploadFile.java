package com.project.market.infra.image;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UploadFile {
	
	private String originalFileName;
	private String storeFileName;
	private String fileUploadUrl;
	
	public UploadFile(String originalFileName,String storeFileName,String fileUploadUrl) {
		this.originalFileName=originalFileName;
		this.storeFileName=storeFileName;
		this.fileUploadUrl=fileUploadUrl;
	}
}
