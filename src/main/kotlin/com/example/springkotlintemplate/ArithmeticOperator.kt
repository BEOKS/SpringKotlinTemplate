package com.example.springkotlintemplate

interface ArithmeticOperatorForTest {
    fun plus(a:Int,b:Int): Int{
        return a+b;
    }
    fun minus(a:Int,b:Int): Int {
        return a-b
    }
    fun multiply(a:Int,b:Int): Int {
        return a*b
    }
    fun divide(a:Int,b:Int): Int {
        return a/b
    }
}