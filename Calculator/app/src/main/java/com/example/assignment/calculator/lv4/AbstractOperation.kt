package com.example.assignment.lv4

abstract class AbstractOperation {
    open fun operate(num1: Int, num2: Int): Double = (num1 + num2).toDouble()
}