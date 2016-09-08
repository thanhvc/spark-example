package groovy.compile;
class Foo implements IFoo { 

    def x = 0
    def offerId;
    def checkUtmSource
    def checkSignature
	Foo(String paramOfferId, Boolean paramCheckUtmSource, Boolean paramCheckSignature) {
		offerId = paramOfferId
		checkUtmSource = paramCheckUtmSource
		checkSignature = paramCheckSignature
	}
	
	
	
	public Object run(Object foo) {
		
	println "hello world"

	println "offerId = " + offerId

	println "checkUTM = " + checkUtmSource

	println "checkSignature = " + checkSignature

	x = 100

	return foo; 
	
	} 
} 