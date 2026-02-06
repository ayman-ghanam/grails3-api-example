package com.mackenzie.marketing

class EtfPricePoint {

    Date priceDate
    BigDecimal nav

    Date dateCreated
    Date lastUpdated

    static belongsTo = [etf: EtfListing]

    static constraints = {
        priceDate nullable: false
        nav nullable: false, scale: 4
        etf nullable: false
    }

    static mapping = {
        table "etf_price_point"
        priceDate index: "idx_etf_price_date"
    }
}
