package com.javalenciab90.domain.mapper

interface DataMapper<INPUT, OUTPUT> {
    fun map(input: INPUT): OUTPUT
}