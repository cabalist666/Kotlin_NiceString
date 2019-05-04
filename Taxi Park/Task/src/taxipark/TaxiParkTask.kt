package taxipark

import kotlin.math.floor

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> =

    //this.trips.isNullOrEmpty<Trip>().toSet()
    this.allDrivers
            .filter { it -> it !in this.trips.map { trip: Trip -> trip.driver } }
            .toSet()


/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> =
        this.allPassengers
                .filter{it -> this.trips
                        .filter { trip:Trip -> it in trip.passengers}
                        .count() >= minTrips
                }
                .toSet()

/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> =
        this.allPassengers
                .filter{it -> this.trips
                        .filter{trip:Trip ->  trip.driver==driver && it in trip.passengers }
                        .count() >=2
                     }
                .toSet()

/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> =
        this.allPassengers
                    .filter { man ->
                        this.trips.filter { it -> man in it.passengers && it.discount != null }.count() >
                        this.trips.filter { it -> man in it.passengers && it.discount == null }.count()
                    }
                    .toSet()

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {

    var findmaxduration = this.trips.maxBy(Trip::duration)?.duration
    //val maxDuration:Int = trips.map{ it.duration }.max() ?: 0
      var range2:IntRange? = 0..9
      var maxsize = 0

   //   var test = maxOf(this.trips.filter{trip:Trip -> trip.duration in 0..9}.toSet().size,this.trips.filter{trip:Trip -> trip.duration in 10..19}.toSet().size)
if(findmaxduration == null) return  null
    for (i in 0..findmaxduration!!.div(10)) {
        var test = this.trips.filter { trip: Trip -> trip.duration in 0 + i * 10..9+i*10 }.toSet().size
        if(test > maxsize) {
            maxsize=test
            range2= 0 + i * 10..9+i*10
        }
    }
    if(maxsize == 0) return null
    return range2

   // fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
   //     if(this.trips.isEmpty()) {
   //         return null
   //     } else {
    //        val maxDuration:Int = trips.map{ it.duration }.max() ?: 0
     //       val mapByNumberOfTrips = HashMap<Int, IntRange>()
     //       for (i in 0..maxDuration step 10) {
     //           val range = IntRange(i, i + 9)
     //           val numberOfTripsInThisRange = this.trips.filter { it.duration in range }.count()
      //          mapByNumberOfTrips[numberOfTripsInThisRange] = range
      //      }
//
//            return mapByNumberOfTrips[mapByNumberOfTrips.toSortedMap().lastKey()]
 //       }





}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {
    if(this.trips.isNullOrEmpty()) return false
    else{

        var count: Double = 0.0
        var currentsum:Double = 0.0

        var totaldrivernumber = this.allDrivers.size
        var twentypercentdrive = floor(totaldrivernumber*0.2).toInt()
        var totalincome = this.trips.map{it.cost}.sum()
    //    var driverlistbyincome = trips.sortedByDescending{it.cost}

        var groupdriver = trips.groupBy { it.driver }
                .mapValues { (_,trips) -> trips.sumByDouble{it.cost} }
                .toList()
                .sortedByDescending { (_,value) -> value }.toMap()

        for (value in groupdriver.values){
            count++
            currentsum += value
            if(currentsum >= (totalincome * 0.8)) break
        }

        return count<= allDrivers.size*0.2
    }





}