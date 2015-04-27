package net.bbr


import net.bbr.Address
import spock.lang.*

/**
 *
 */
class AddressIntegrationSpec extends Specification {
	def firstHome
	def dupHome

    def "saving our first address to the db"() {
		given: "A brand new Address"
			firstHome = new Address(fullAddress: '20929 Homeland Rd., Matteson, IL 60443',
										billingAddress: 1,
										city: 'Matteson',
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
		when: "the address is saved"
			firstHome.save(flush: true)
		
		then: "it saved successfully and can be found in the db"
			firstHome.errors.errorCount == 0
			firstHome.id != null
			println("The firstHome id is: ${firstHome.id}")
			println("The firstHome fullAddress is: ${firstHome.fullAddress}")
			Address.get(firstHome.id).zipCode == firstHome.zipCode
    }
	
	def "test unique full address"() {
		given: "A second address with duplicate full address"
			firstHome = new Address(fullAddress: '20929 Homeland Rd., Matteson, IL 60443',
				billingAddress: 1,
				city: 'Matteson',
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
			
			dupHome = new Address(fullAddress: '20929 Homeland Rd., Matteson, IL 60443',
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
			
		when: "the duplicate address is saved"
			firstHome.save(flush: true)
			//dupHome.save(flush: true)
			dupHome.validate()
		
		then: "duplicate saved with errors"
		 	println("The dupHome.fullAddress error code is: " + dupHome.errors.getFieldError("fullAddress").code)
			dupHome.errors.errorCount != 0
			"unique" == dupHome.errors.getFieldError("fullAddress").code
			
			
			/*
			println("The firstHome fullAddress is: ${firstHome.fullAddress}")
			println("The dupHome id is: ${dupHome.id}")
		  	println("The dupHome fullAddress is: ${dupHome.fullAddress}")
			//Address.get(dupHome.id).fullAddress == firstHome.fullAddress
			*/
	}
}
