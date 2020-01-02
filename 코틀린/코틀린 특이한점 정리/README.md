# 코틀린의 특이한점 정리

## 자료형 검사하고 변환하기

코틀린은 변수를 사용할 때 `반드시 값이 할당 되어 있어야 한다는 원칙`이 있습니다. `만약 값이 할당되지 않은 변수(null)를 사용하면 코틀린에서 오류가 발생`합니다.

코틀린에서는 null 상태인 변수를 허용하려면 물음표(?) 기호를 사용해 선언해야 합니다.

물론 null을 허용하는 변수를 사용하려면 null을 검사하고 처리하는 방법까지 고려해야 합니다. 



**null 상태인 변수 허용하는 경우**

~~~kotlin
fun main(){
    var str1 : String = "Hello Kotlin"
    str1 = null //오류! null을 허용하지 않음.
    println("str1 : $str1")
}
~~~

**null 상태인 변수 허용하지 않는 경우**

~~~kotlin
fun main(){
    var str1 : String? = "Hello Kotlin"
    str1 = null 
    println("str1 : $str1")
    
}
~~~

출력결과

~~~
str1 : null
~~~



### 세이프콜과 non-null단정기호

~~~kotlin
fun main(){
    var str1 : String? = "Hello Kotlin"
    str1 = null
    println("str1 : $str1 length: ${str1.length}")
}
~~~

위의 경우에는 str1.length에 빨간줄이 뜬다. 

String?형에서는 세이프콜(?.)이나 non-null단정 기호(!!.)만을 허용한다는 팁을 볼 수 있습니다. 



~~~kotlin
println("str1 : $str1 length: ${str1?.length}") //str1을 세이프 콜로 안전하게 호출
~~~



~~~kotlin
println("str1 : $str1 length: ${str1!!.length}") //NPE 강제발생
~~~

non-null 단정기호는 변수에 할당된 값이 null이 아님을 단정하므로 컴파일러가 null 검사 없이 무시한다. 따라서 변수에 null이 아님을 단정하므로 컴파일러가 null 검사 없이 무시한다. 



-----

### 반환값이 없는 함수

함수의 반환값은 생략이 가능합니다. 반환값의 자료형을 Unit으로 지정하거나 생략할 수 있습니다. Unit은 코틀린에서 다루는 특수한 자료형 중 하나로 반환값이 없을 때 사용합니다. 

~~~kotlin
fun printSum(a: Int, b: Int): Unit{
    println("sum of $a and $b is ${a+b}")
}
~~~

