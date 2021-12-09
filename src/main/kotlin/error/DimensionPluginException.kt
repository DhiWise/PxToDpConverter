package error

import util.GROUP_ID

open class DimensionPluginException(
    val header: String, message: String?
) : Exception(message)

class FileWriteException(
    message: String?
) : DimensionPluginException("File creation exception:", message)

class WrongHeightException :
    DimensionPluginException(GROUP_ID, "Height values must be between 2-4 digits")

class WrongWidthException :
    DimensionPluginException(GROUP_ID, "Width values must be between 2-4 digits")

class EmptyException(msg: String) :
    DimensionPluginException(GROUP_ID, "     Please enter $msg     ")
