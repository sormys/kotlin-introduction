package pl.edu.mimuw.elements

import pl.edu.mimuw.elements.fragments.SMDBuilder2

@DslMarker
annotation class SMDDsl2

fun buildSMD(action: SMDBuilder2.() ->Unit) =
    SMDBuilder2().apply(action).build()