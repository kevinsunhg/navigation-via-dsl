package com.example.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.navOptions

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

fun Fragment.defaultSlidingNavOption(): NavOptions {
    return navOptions {
        anim {
            enter = R.anim.translate_from_right
            exit = R.anim.translate_to_left
            popEnter = R.anim.translate_from_left
            popExit = R.anim.translate_to_right
        }
    }
}