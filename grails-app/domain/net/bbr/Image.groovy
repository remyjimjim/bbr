package net.bbr

import java.util.Date;

class Image {  
	static belongsTo = [myProperty: Property]	//bi-directional one to many, if property is deleted then so are images.
	static mapping = {						// Note: batchSize - Lazy fetching optimization
		batchSize 10
		content sqlType: 'mediumblob'
		myProperty column: 'property_id'
	}										
	
	static constraints = {
		myProperty nullable: false
		content nullable: false
		type nullable: false
		width nullable: true
		height nullable: true
		updateUser nullable: true
	}
	
	Property 	myProperty
	byte[] 		content = []
	int			width = 0
	int			height = 0
	String		updateUser	= ""
	String		type = ""
	Date		dateCreated
	Date		lastUpdated
	
	public String toString() {
		return """Image:

		property 			--	${myProperty}
		width				--  ${width}
		height				--  ${height}
		dateCreated 		--	${dateCreated}
		lastUpdated 		--	${lastUpdated}
		updateUser 			--	${updateUser}
		type 				--	${type}			//thumb or large
		"""
	}
}

