package com.surodeev.currenciestestapplication.entity

data class Currency(
    var code: String,
    var value: Double,
    var favorite: Boolean = false
) {

    fun copyWith(
        code: String = this.code,
        value: Double = this.value,
        favorite: Boolean = this.favorite
    ): Currency {
        return Currency(code, value, favorite)
    }
    override fun equals(other: Any?): Boolean {
        return if (other !is Currency) {
            false
        } else other.code == code &&
                other.value == value &&
                other.favorite == favorite
    }
}