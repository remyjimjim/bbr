package net.bbr.model.domain.test.integration

import spock.lang.*
import net.bbr.model.domain.Property

/**
 *
 */
class PropertyIntegrationSpec extends Specification {
	def aProperty

    def setup() {
    }

    def cleanup() {
    }

    void "Ensure you get an integrity violation if you try to save a property not related to an address"() {
		
		given: "a new property without an address associated to it..."
			aProperty = new Property()
			
		when: "the property without an address is validated ..."
			if (aProperty.validate()) {
				println "The property is valid:"
				aProperty.save(flush: true)
			}else{
				println "The property is not valid..."
			}
			
		then: "the property should have validation errors..."
			if (aProperty.hasErrors()) {
				println "Property validation errors: "
				aProperty.errors.allErrors.each {
					println "    " + it
				}
			}
    }
}
