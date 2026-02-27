package poc.grails3


import grails.converters.JSON

class EtfListingController {

    static responseFormats = ['json']
    static allowedMethods = [index: "GET", show: "GET"]

    EtfListingService etfListingService

    def index(Integer max) {
        def listOfEtfs = etfListingService.getAllEtfListings(max)
        render listOfEtfs as JSON
    }

    def show(String symbol) {
        def etf = etfListingService.getEtfBySymbol(symbol)
        if (!etf) {
            render(status: 404, text: ([message: "ETF not found for symbol=${symbol}"] as JSON).toString())
            return
        }
        render etf as JSON
    }
}
