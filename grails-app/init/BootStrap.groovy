import poc.grails3.EtfListing
import poc.grails3.EtfPricePoint

class BootStrap {

    def init = { servletContext ->
        if (EtfListing.count() == 0) {

            def vfv = new EtfListing(
                    symbol: "VFV",
                    name: "Vanguard S&P 500 Index ETF",
                    nav: 125.1234,
                    mer: 0.0008,
                    inceptionDate: Date.parse("yyyy-MM-dd", "2012-11-02")
            ).save(failOnError: true)

            new EtfPricePoint(etf: vfv, priceDate: Date.parse("yyyy-MM-dd", "2026-02-01"), nav: 124.9000).save(failOnError: true)
            new EtfPricePoint(etf: vfv, priceDate: Date.parse("yyyy-MM-dd", "2026-02-02"), nav: 125.1234).save(failOnError: true)
            new EtfPricePoint(etf: vfv, priceDate: Date.parse("yyyy-MM-dd", "2026-02-03"), nav: 126.0100).save(failOnError: true)

            def xic = new EtfListing(
                    symbol: "XIC",
                    name: "iShares Core S&P/TSX Capped Composite Index ETF",
                    nav: 32.5521,
                    mer: 0.0006,
                    inceptionDate: Date.parse("yyyy-MM-dd", "2001-11-16")
            ).save(failOnError: true)

            new EtfPricePoint(etf: xic, priceDate: Date.parse("yyyy-MM-dd", "2026-02-01"), nav: 32.4000).save(failOnError: true)
            new EtfPricePoint(etf: xic, priceDate: Date.parse("yyyy-MM-dd", "2026-02-02"), nav: 32.5521).save(failOnError: true)
        }
    }

    def destroy = { }
}
