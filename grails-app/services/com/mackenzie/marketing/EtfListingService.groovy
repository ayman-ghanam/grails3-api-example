package com.mackenzie.marketing

import grails.transaction.Transactional

@Transactional(readOnly = true)
class EtfListingService {

    List<Map> getAllEtfListings(Integer max = 100) {
        def listings = EtfListing.list([sort: 'symbol', order: 'asc', max: Math.min(max ?: 100, 500)])

        return listings.collect { EtfListing e ->
            def points = EtfPricePoint.findAllByEtf(
                    e,
                    [sort: 'priceDate', order: 'desc', max: 3]
            )

            [
                    id    : e.id,
                    symbol: e.symbol,
                    name  : e.name,
                    nav   : e.nav,
                    mer   : e.mer,
                    inceptionDate: e.inceptionDate,
                    latestPricePoints: points.collect { p ->
                        [priceDate: p.priceDate, nav: p.nav]
                    }
            ]
        }
    }

    Map getEtfBySymbol(String symbol) {
        def e = EtfListing.findBySymbol(symbol?.trim()?.toUpperCase())
        if (!e) return null

        def points = EtfPricePoint.findAllByEtf(
                e,
                [sort: 'priceDate', order: 'desc', max: 20]
        )

        return [
                id    : e.id,
                symbol: e.symbol,
                name  : e.name,
                nav   : e.nav,
                mer   : e.mer,
                inceptionDate: e.inceptionDate,
                pricePoints: points.collect { p ->
                    [priceDate: p.priceDate, nav: p.nav]
                }
        ]
    }
}
