package com.example.assignment.calculator.lv3

fun main() {
    var result: Double = 0.0
    while (true) {
        println("첫번째 숫자를 입력해 주세요.")
        var num1 = readLine()!!.toInt()
        println("첫번째 숫자 ${num1}")

        println("두번째 숫자를 입력해 주세요.")
        var num2 = readLine()!!.toInt()
        println("두번째 숫자 ${num2}")

        println("원하는 연산자를 입력하세요. 덧셈(+),뺄셈(-),곱셈(*),몫-(/),나머지-(%)")
        var operator2 = readLine()
        while (!(operator2 == "+" || operator2 == "*" || operator2 == "/" || operator2 == "-" || operator2 == "%")) {
            println("올바른 연산자를 입력하세요. 덧셈(+),뺄셈(-),곱셈(*),몫-(/),나머지-(%)")
            operator2 = readLine()
        } // 올바르게 입력할때까지 무한반복
        val calc = Calculator()
        when (operator2) {
            "+" -> {println("${num1}${operator2}${num2} 결과는 : ${AddOperation().Operation(num1,num2)} 입니다" ) }
            "-" -> {println("${num1}${operator2}${num2} 결과는 : ${DivideOperation().Operation(num1,num2)} 입니다" )}
            "*" -> {println("${num1}${operator2}${num2} 결과는 : ${MultiplyOperation().Operation(num1,num2) } 입니다" )}
            "/" -> {println("${num1}${operator2}${num2} 결과는 : ${SubstractOperation().Operation(num1,num2) } 입니다" )}
            "%" -> {println("${num1}${operator2}${num2} 결과는 : ${ReminderOperation().Operation(num1,num2) } 입니다" )}
            else -> {}
        }

    }
}