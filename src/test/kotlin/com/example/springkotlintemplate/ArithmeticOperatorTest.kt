package com.example.springkotlintemplate

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringKotlinTemplateApplicationTests: DescribeSpec({
    val arithmeticOperator: ArithmeticOperator=TestArithmeticOperator()
    context("arithmetic logic"){
        it("plus operation"){
            arithmeticOperator.plus(1,1) shouldBe 2
        }
        it("minus operation"){
            arithmeticOperator.minus(1,1) shouldBe 0
        }
        it("divide operation"){
            arithmeticOperator.divide(1,1) shouldBe 1
        }
        it("multiply operation"){
            arithmeticOperator.multiply(1,1) shouldBe 1
        }
    }
})