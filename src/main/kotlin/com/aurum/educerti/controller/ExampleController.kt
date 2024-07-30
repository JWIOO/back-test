package com.aurum.educerti.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.time.LocalDate

@Controller
class ExampleController {

    @GetMapping("/thymeleaf/example")
    fun thymeleafExample(model: Model): String {
        val examplePerson = com.aurum.educerti.controller.ExampleController.Person(
            id = 1L,
            name = "홍길동",
            age = 11,
            hobbies = listOf("운동", "독서")
        )

        model.addAttribute("person", examplePerson)
        model.addAttribute("today", LocalDate.now())

        return "example"
    }

    data class Person(
        val id: Long,
        val name: String,
        val age: Int,
        val hobbies: List<String>
    )
}
