package nicestring

fun main(){
    val str = "abcdefg"
    println(str)
    println("4: " + str.windowed(4))
    println("2: " + str.windowed(2))

    println("3, step 4" + str.windowed(3,step=4))
}