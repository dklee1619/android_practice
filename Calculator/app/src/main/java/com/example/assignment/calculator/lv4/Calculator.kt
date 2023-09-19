package com.example.assignment.lv4

class Calculator() {
    fun addOperation(operator: AddOperation, num1: Int, num2: Int): Double {
        return operator.operate(num1, num2)
    }
    fun DivideOperation(operator: DivideOperation, num1: Int, num2: Int): Double {
        return operator.operate(num1, num2)
    }

    fun MultiplyOperation(operator: MultiplyOperation, num1: Int, num2: Int): Double {
        return operator.operate(num1, num2)
    }

    fun SubstractOperation(operator: SubstractOperation, num1: Int, num2: Int): Double {
        return operator.operate(num1, num2)
    }
    fun ReminderOperation(operator: ReminderOperation, num1: Int, num2: Int): Double {
        return operator.operate(num1, num2)
    }
}

// 클래스를 넣을때 operator: 이란 이름을 넣어줌 여기서는 () 안넣어줘도됨. 클래스명만.
// 호출하는거라기보단 그냥 저게 들어온다고 알려주는?? 클래스명만
// 그 operator는 var a =3 에서의 a 같은 변수명같은거라 아무거나 내원하는대로 넣으면됨
// 생각해보면 num1 num2도 var num1 var num2 이런 변수명 같은거엿지 똑같은거임