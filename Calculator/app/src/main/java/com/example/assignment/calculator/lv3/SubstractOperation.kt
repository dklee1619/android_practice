package com.example.assignment.calculator.lv3

class SubstractOperation : Calculator() {
    override fun Operation(num1: Int, num2: Int): Int {
        return (num1 % num2);
    }
}