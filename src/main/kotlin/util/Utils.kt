package util

import java.io.File

fun createFilePath(vararg components: String): String {
    return components.joinToString(File.separator)
}

fun Float.roundTo(n : Int) : Double {
    return "%.${n}f".format(this).toDouble()
}
fun Double.roundTo(n : Int) : Double {
    return "%.${n}f".format(this).toDouble()
}