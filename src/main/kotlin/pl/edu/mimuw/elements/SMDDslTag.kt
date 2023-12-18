package pl.edu.mimuw.elements

import pl.edu.mimuw.elements.fragments.SMDBuilder

@DslMarker
annotation class SMDDsl

fun buildSMD(action: SMDBuilder.() -> Unit) =
    SMDBuilder().apply(action).build()