package net.bbr

import java.util.Date;

import spock.lang.*
import net.bbr.Image
import net.bbr.Property
import net.bbr.Address

/**
 *
 */
class ImageIntegrationSpec extends Specification {

	def firstImage
	def dupImage
	def aProperty
	def anAddress

    def "saving an image without a property_id"() {
		
		given: "a new address, property and image..."
		
		    anAddress = new Address(fullAddress: '20929 Homeland Rd., Matteson, IL 60443',
				billingAddress: 1,
				city: 'Chicago Hgts',
				countryId: 1,
				county: 'Cook',
				dateCreated: new Date(),
				lastUpdated: new Date(0),
				latitude: 0,
				longitude: 0,
				mailingAddress: 0,
				province: '',
				state: 'IL',
				streetName: 'Homeland Rd.',
				zipCode: '20929',
				zipPlusFour: ''
			)
			
			aProperty = new Property()
			firstImage = new Image()
			
			/*
			firstImage = new Image( content: null,
									height: 650,
									width: 650,
									property: aProperty,
									type: 'thumb',
									dateCreated: new Date(),
									lastUpdated: new Date(0),
									updateUser: 'TEST')
			*/
			
		when: "try to save the address, the property and the image that has a null product_id..."
		
			if (anAddress.validate()) { 
				println "In Address validation:"
				anAddress.save(flush: true) 
			}
			
			aProperty.address = anAddress
			if (aProperty.validate()) { 
				println "In Property validation:"
				aProperty.save(flush: true) 
			}
			
			firstImage.content = hexStringToByteArray("e04fd020ea3a6910a2d808002b30309d")
			//firstImage.myProperty = aProperty                              SOME PATTERNS
			//if (firstImage.validate(["width"])) { 
			/*
			if (firstImage.hasErrors()) {
				if (firstImage.errors.hasFieldErrors("content")) {
					println firstImage.errors.getFieldError("content").rejectedValue
				}
			}
			OR
			if (firstImage.save()) {
				return firstImage
			}
			else {
				firstImage.errors.allErrors.each {
				println it
				}
			}
			*/
			if (firstImage.validate()) {
				println "In Image validation:"
				//firstImage.save(flush: true) 
				println "After Image save:"
			}
			
		then: "A foreign key constaint violation is thrown..."
		
			if (anAddress.hasErrors()) {
				println "Address validation errors: "
				anAddress.errors.allErrors.each {
					println "    " + it
				}
			}
			
			if (aProperty.hasErrors()) {
				println "Property validation errors: "
				aProperty.errors.allErrors.each {
					println "    " + it
				}
			}
			
			if (firstImage.hasErrors()) {
				println "firstImage validation errors: "
				firstImage.errors.allErrors.each {
					println "    " + it
				}
			}
			//firstImage.errors.errorCount != 0
		    //anAddress.errors.errorCount != 0
			println("Here I is 1:")
    }
	
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
								 + Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}
}
