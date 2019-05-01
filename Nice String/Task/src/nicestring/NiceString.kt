package nicestring

fun String.isNice(): Boolean {
    var totalcount =3
    var inflag = 0
    val fortest = this
    var count = 0
    var flag = 0
    println(fortest)
    for(i in 0..fortest.length-2 ){
        if (inflag==0 && fortest[i] == 'b' && (fortest[i + 1] == 'a' || fortest[i + 1] == 'u' || fortest[i + 1] == 'e')) {
            totalcount -= 1
            inflag = 1
        }

        if(flag ==0 && fortest[i] == fortest[i+1])
            flag = 1

    }

    for(i in fortest.indices) {
        if (fortest[i] == 'a' || fortest[i] == 'e' || fortest[i] == 'i' || fortest[i] == 'o' || fortest[i] == 'u')
            count += 1
    }
    if(count>=3) else totalcount -=1
    if(flag==1) else totalcount -=1

    if(totalcount >= 2)
        return true
    else
        return false

}