package nicestring

fun String.isNice2():Boolean{
    //val noBadSubString = !contains("ba" && !contains("be") &&!contains("bu"))

    //val noBadSubString = setOf("ba","be","bu").all{!this.contains(it)}  // this equal to Sting of the fun  and it refer to setOf value
    val noBadSubString = setOf("ba","be","bu").none{this.contains(it)}  // this equal to Sting of the fun  and it refer to setOf value


    //val hasThreeVowels = count{
    //    it =='a' || it == 'e' || it =='i' || it=='o' || it='u'
    //} >=3

    //val hasThreeVowels = count{ it in setOf( 'a' , 'e' , 'i' , 'o' , 'u' )} >=3
    val hasThreeVowels = count{it in "aeiou" } >=3


 //   var hasDouble =false
  //  if (length > 1){
   //     var prevCh: Char? =null
   //     for (ch in this){
   //         if(ch == prevCh)
   //             hasDouble =true
   //         prevCh=ch
   //     }
   // }

  //  var hasDouble = (0 until lastIndex).any{ this[it] == this[it+1] }
    var hasDouble = zipWithNext().any{ it.first == it.second}
  //  var hasDouble = windowed(2).any{it[0]==it[1]}

    return listOf(noBadSubString, hasThreeVowels,hasDouble).count{it}>=2 //will count the number of True value
    //and return T/F for this whole function

    //var conditions = 0
    //if(noBadSubString) conditions++
    //if(hasThreeVowels) conditions++
    //if(hasDouble) conditions++

    //if(conditions>= 2) return true
    //return false
}