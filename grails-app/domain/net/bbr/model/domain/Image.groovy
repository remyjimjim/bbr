package net.bbr.model.domain

import java.util.Date;

class Image {  
	static belongsTo = [property: Property]	//bi-directional one to many, if property is deleted then so are images.
	static mapping = {						// Note: batchSize - Lazy fetching optimization
		batchSize 10
		content sqlType: 'mediumblob'
	}										
	
	static constraints = {
		property nullable: false
		content nullable: false
		type nullable: false
		width nullable: true
		height nullable: true
		updateUser nullable: true
	}
	
	Property 	property
	byte[] 		content = []
	int			width = 0
	int			height = 0
	String		updateUser	= ""
	String		type = ""
	Date		dateCreated
	Date		lastUpdated
	
	public String toString() {
		return """Image:
		property 			--	${property}
		width				--  ${width}
		height				--  ${height}
		dateCreated 		--	${dateCreated}
		lastUpdated 		--	${lastUpdated}
		updateUser 			--	${updateUser}
		type 				--	${type}			//thumb or large
		"""
	}
}

