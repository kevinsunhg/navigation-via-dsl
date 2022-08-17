package com.example.navigation

object NavRoute {

    const val fragmentA = "fragmentA"
    const val fragmentB = "fragmentB"
    const val fragmentC = "fragmentC"

    const val nestedGraphParent = "nestedGraphParent"
    const val nestedGraphChild = "nestedGraphChild"
    const val fragmentNestedParent = "fragmentNestedParent"
    const val fragmentNestedChildA = "fragmentNestedChildA"
    const val fragmentNestedChildB = "fragmentNestedChildB"
}

object NavArguments {
    const val fragmentBArg = "fragmentBArg"
}

object NavDeepLink {
    const val fragmentBDeepLinkScheme = "testnavigation://fragmentB"

    const val fragmentNestedChildADeepLink = "testnavigation://fragmentNestedChildA"
    const val fragmentNestedChildBDeepLink = "testnavigation://fragmentNestedChildB"
}