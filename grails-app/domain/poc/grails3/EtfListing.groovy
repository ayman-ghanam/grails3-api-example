package poc.grails3

class EtfListing {
    String symbol
    String name
    BigDecimal nav
    BigDecimal mer
    Date inceptionDate

    Date dateCreated
    Date lastUpdated

    static constraints = {
        symbol blank: false, unique: true, maxSize: 12
        name blank: false, maxSize: 200
        nav nullable: false, scale: 4
        mer nullable: true, scale: 4
        inceptionDate nullable: true
    }

    static mapping = {
        table "etf_listing"
        symbol index: "idx_etf_symbol"
    }
}
