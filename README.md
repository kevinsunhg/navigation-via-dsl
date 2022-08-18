# navigation-via-dsl
show case of navigation via Kotlin DSK for multi module project

## Building Navigation graph via Kotlin DSL

[Building graph programmatically using Kotlin DSK](https://developer.android.com/guide/navigation/navigation-kotlin-dsl)

## High level project structure
In this sample multi module project, It is structured as single activity multi fragments structure.

App module has the MainActivity.

ModuleA, ModuleB and Module C each contains a single fragment.

ModuleNestedGraph contains three fragments constructed as nested level.

Navigation module contains string route constants that all modules can use to perform cross-module navigation.

This project has no graph.xml file and purely builds the graph via kotlin DSL.

Building the graph no longer lives in the app module. Each module is responsible for building the graph for fragments it contains.(This feels like a more scalable solution than using the traditional graph.xml approach as it will only gets bigger and bigger)

## Show case some common usage of navigation between modules

1. using NavController to directly perform navigation. (see the code in FragmentA findNavController().navigate())
2. Perform navigation animation (See the usage of defaultSlidingNavOption() in NavConstant.kt file)
3. Define arguments for Fragments and pass it in the navigation (See ModuleBNavBuilder.kt how the argument is defined)
4. How to manipulate fragment back stack while navigation(i.e PopUpTo, and inclusive) (See FragmentC.kt for example0)
5. simple deep link, try below for deeplink with/without arguments.
* **adb shell am start -W -a android.intent.action.VIEW -d "testnavigation://fragmentB"**
* **adb shell am start -W -a android.intent.action.VIEW -d "testnavigation://fragmentB?fragmentBArg={argumentToPass}"**
6. Deep link to a nested graph while maintaining fragment back stack. 
* In the ModuleNestedGraph, it contains three fragments. FragmentNestedParent, FragmentNestedChildA, FragmentNestedChildB.
   it contains two nested navigation graphs.

* ----------- parent level graph -> contains FragmentNestedParent, and a nested child graph. FragmentNestedParent is the startDestination 
* ----------- child level graph -> contains FragmentNestedChildA, FragmentNestedChildB. and FragmentNestedChildA is the startDestination.
 
* Try **adb shell am start -W -a android.intent.action.VIEW -d "testnavigation://fragmentNestedChildA"**
    
* It will deep linking to the fragmentNestedChildA, and when you keep pressing back. The back stack should look like below.
 
* fragmentNestedChildA --->(Go back) FragmentNestedParent --->(Go back) FragmentA --->(Go back) exit the app

