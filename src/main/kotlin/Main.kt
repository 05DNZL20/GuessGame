import java.util.*
import kotlin.random.Random

fun main() {
    val tmp: MutableList<Char> = arrayListOf('0','1','2','3')
    var check = true
    var checkIfWin = false
    var userGuess: MutableList<Char> = mutableListOf()
    val generatedNumber: MutableList<Char> = randomCharList()

    fillList(tmp,generatedNumber)

   //println("${generatedNumber[0]}${generatedNumber[1]}${generatedNumber[2]}${generatedNumber[3]}")

    while (!checkIfWin) {
        while (check) {
            val guessedNum: Int = makeGuess()

            if ((guessedNum >= 1000) && (guessedNum <= 9999)) {
                userGuess = guessedCharArray(guessedNum)
                check = false
            }
        }

        val corr = checkNumbers(generatedNumber, userGuess)
        fillList(generatedNumber, tmp)
        println("Output: $corr")

        checkIfWin = checkWin(corr)

        check = true
    }

    println("${generatedNumber[0]}${generatedNumber[1]}${generatedNumber[2]}${generatedNumber[3]}")
    println("Congratulations you guessed the number!!!")
}

fun fillList( List1: MutableList<Char>, List2: MutableList<Char>){
    for (i in 0..3){
        List1[i] = List2[i]
    }
}

fun checkWin( nm: String): Boolean {
    return (nm == "4:4")
}

val makeGuess: () -> Int = {
    val userInput = Scanner(System.`in`)
    print("User input: ")
    userInput.nextInt()
}

val randomCharList: () -> MutableList<Char> = {
    ((Random.nextInt(1000,10000)).toString()).toCharArray().toMutableList()
}

val guessedCharArray: (Int) -> MutableList<Char> = {
    (it.toString()).toCharArray().toMutableList()
}

val checkNumbers: (MutableList<Char>, MutableList<Char>) -> String =
    { generatedNumber: MutableList<Char>, userGuess: MutableList<Char> ->
        var ch = 'a'
        var m = 0
        var n = 0

        for (i in 0..3) {
            if (generatedNumber[i] == userGuess[i]) {
                generatedNumber[i] = ch++
                userGuess[i] = ch++
                m++
                n++
            }
        }

        for (i in 0..3) {
            for (j in 0..3) {
                if (generatedNumber[i] == userGuess[j]) {
                    generatedNumber[i] = ch++
                    userGuess[j] = ch++
                    n++
                }
            }
        }
        "$n:$m"
    }
