package com.gianghv.android.util.ext

// Extension function for parsing Object to Int
fun Any?.parseIntOrNull(): Int? {
    return (this as? String)?.toIntOrNull()
}

// Extension function for parsing Object to Float
fun Any?.parseFloatOrNull(): Float? {
    return (this as? String)?.toFloatOrNull()
}

// Extension function for parsing Object to Long
fun Any?.parseLongOrNull(): Long? {
    return (this as? String)?.toLongOrNull()
}

// Extension function for parsing Object to Double
fun Any?.parseDoubleOrNull(): Double? {
    return (this as? String)?.toDoubleOrNull()
}

// Extension function for parsing Object to Boolean
fun Any?.parseBooleanOrNull(): Boolean? {
    return (this as? String)?.toBoolean()
}

// Extension function for parsing Object to String
fun Any?.parseStringOrNull(): String? {
    return this as? String
}
