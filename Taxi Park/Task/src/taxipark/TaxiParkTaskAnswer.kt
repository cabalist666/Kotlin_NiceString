package taxipark

import kotlin.math.floor

/*
 * Task #1. Find all the drivers who performed no trips.
 */

fun TaxiPark.findFakeDrivers1():Set<Driver> =
        allDrivers.filter{d-> trips.none{it.driver==d}}.toSet()

fun TaxiPark.findFakeDrivers2():Set<Driver> =
        //allDrivers.minus(trips.map{it.driver})
 allDrivers - trips.map{it.driver}

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */

fun TaxiPark.findFaithfulPassengers1(minTrips : Int) :Set<Passenger> =
        trips
        .flatMap(Trip::passengers)// will get passenger for each trip
                .groupBy { passenger-> passenger }
                .filterValues{ group -> group.size >= minTrips}
                .keys


fun TaxiPark.findFaithfulPassengers2(minTrips : Int) :Set<Passenger> =
        allPassengers
                .filter{ p ->
                    trips.count{ p in it.passengers
                    } >= minTrips
                }
                .toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */

fun TaxiPark.findFrequentPassenger1(driver: Driver):Set<Passenger> =
        trips
        .filter{trip -> trip.driver == driver}
          .flatMap(Trip::passengers)
                .groupBy{passenger -> passenger}
                .filterValues{group->group.size>1}
                .keys

fun TaxiPark.findFrequentPassenger2(driver: Driver):Set<Passenger> =
        allPassengers.filter{p ->
            trips.count{it.driver == driver && p in it.passengers} >1
        }.toSet()

/*
* Task #4. Find the passengers who had a discount for majority of their trips.
*/

fun TaxiPark.findSmartPassenger1():Set<Passenger> {
 //   val (first, second) = trips.partition { it.discount is Double }
    val (tripsWithDiscount, tripsWithoutDiscount) =
            trips.partition { it.discount != null }
    return allPassengers
            .filter{passenger ->
                tripsWithDiscount.count{passenger in it.passengers}>
                        tripsWithDiscount.count{passenger in it.passengers}
                //tripsWithDiscount.count{it.passengers.contains(passenger)}>
                 //       tripsWithDiscount.count{it.passengers.contains(passenger)}
            }.toSet()
}

fun TaxiPark.findSmartPassenger2():Set<Passenger> =
        allPassengers
                .associate { p-> // key is the passenger
                 p to trips.filter{ trip: Trip ->  p in trip.passengers} // associate returns as map
                }
                .filterValues{ group ->
                 val(withDiscount,withoutDiscount)=group
                         .partition { it.discount != null }
                    withDiscount.size > withoutDiscount.size
                }.keys
            //    .entries //entries returns as set of address

fun TaxiPark.findSmartPassenger3(): Set<Passenger> =
        allPassengers.filter{p ->
            val withDiscount = trips.count{ t -> p in t.passengers && t.discount !=null}
            val withoutDiscount = trips.count{ t -> p in t.passengers && t.discount ==null}
            withDiscount > withoutDiscount
        }.toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */

fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRanger? {
    return trips
            .groupBy {
                val start = it.duration / 10 * 10
                val end =  start + 9
                start.. end
            }
            .maxBy { (_,group) -> group.size }
            ?.key
           // .toList()
            //.sortedByDescending { pair -> pair.second.size } //if you use it -> it.second.size  just use it.second.size
            //.firstOrNull()
            //?.first
}
