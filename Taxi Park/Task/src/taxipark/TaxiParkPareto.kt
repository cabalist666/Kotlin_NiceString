package taxipark

fun TaxiPark.checkParetoPrinciple(): Boolean {
    if (trips.isEmpty()) return false

    val totalIncome = trips.sumByDouble(Trip::cost)

    val sortedDriversIncome: List<Double> = trips
            .groupBy{Trip::driver}
            .map { (_, tripsByDriver) -> tripsByDriver.sumByDouble(Trip.cost) }
            .sortedDescending()

    val numberOfTopDrivers = (0.2 * allDrivers.size).toInt()
    val incomeByTopDrivers = sortedDriversIncome
            .take(numberOfTopDrivers)
            .sum()

    return incomeByTopDrivers >= 0.8 * totalIncome
}

fun main(args: Array<String>) {
    taxiPark(1..5, 1..4,
            trip(1, 1, 20, 20.0),
            trip(1, 2, 20, 20.0),
            trip(1, 3, 20, 20.0),
            trip(1, 4, 20, 20.0),
            trip(2, 1, 20, 19.0))
            .checkParetoPrinciple() eq true

    taxiPark(1..5, 1..4,
            trip(1, 1, 20, 20.0),
            trip(1, 2, 20, 20.0),
            trip(1, 3, 20, 20.0),
            trip(1, 4, 20, 20.0),
            trip(2, 1, 20, 21.0))
            .checkParetoPrinciple() eq false
}
