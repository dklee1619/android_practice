package com.example.assignment.calculator.lv2

class Calculator() {
    fun addOperation(operator:String, num1: Int, num2: Int): Int {
        return (num1 + num2) ;
    }
    fun DivideOperation(operator:String, num1: Int, num2: Int): Int {
        return (num1 - num2);
    }

    fun MultiplyOperation(operator:String, num1: Int, num2: Int): Int {
        return (num1 * num2);
    }

    fun SubstractOperation(operator:String, num1: Int, num2: Int): Int {
        return (num1 % num2);
    }
    fun ReminderOperation(operator:String, num1: Int, num2: Int): Int {
        return (num1 / num2);
    }
}

