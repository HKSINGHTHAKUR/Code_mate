package com.tanmay.codo.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LearnViewModel : ViewModel() {
    enum class Language { PYTHON, JAVASCRIPT, CPP }

    data class QuizQuestion(
        val question: String,
        val options: List<String>,
        val correctIndex: Int,
        val imageUrl: String? = null,
        val explanation: String? = null
    )

    data class Lesson(
        val id: String,
        val title: String,
        val type: String,
        val xp: Int,
        val section: String,
        val description: String = "",
        val exampleCode: String? = null,
        val quiz: List<QuizQuestion>? = null,
        val imageUrl: String? = null,
        val videoUrl: String? = null,
        val locked: Boolean = false,
        val completed: Boolean = false,
        val active: Boolean = false
    )
    data class Section(
        val title: String,
        val subtitle: String,
        val lessons: List<Lesson>
    )
    data class LanguageState(
        val sections: List<Section>,
        val level: Int,
        val xp: Int,
        val totalLessons: Int
    )

    var selectedLanguage by mutableStateOf(Language.PYTHON)
        private set
    private var languageStates by mutableStateOf(mapOf<Language, LanguageState>())
    val sections: List<Section> get() = languageStates[selectedLanguage]?.sections ?: emptyList()
    val level: Int get() = languageStates[selectedLanguage]?.level ?: 1
    val xp: Int get() = languageStates[selectedLanguage]?.xp ?: 0
    val totalLessons: Int get() = languageStates[selectedLanguage]?.totalLessons ?: 0

    init {
        loadInitialData()
        resetProgress()
    }

    private fun loadInitialData() {
        val pythonSections = listOf(
            Section("Control Flow", "Master if statements, loops, and logic", listOf(
                Lesson(
                    id = "if",
                    title = "If Statements",
                    type = "Lesson",
                    xp = 25,
                    section = "Control Flow",
                    description = "Learn how to use if, else if, and else to control the flow of your Python programs.",
                    exampleCode = """python\nx = 10\nif x > 5:\n    print(\"x is greater than 5\")\nelse:\n    print(\"x is 5 or less\")\n""".trimIndent(),
                    quiz = listOf(
                        QuizQuestion(
                            question = "What keyword is used for an alternative condition in Python if the first `if` is false?",
                            options = listOf("else", "elif", "elseif", "otherwise"),
                            correctIndex = 1,
                            explanation = "The correct answer is 'elif', which is short for 'else if'."
                        ),
                        QuizQuestion(
                            question = "What will be printed if x = 3 from the code `if x > 5: print('A') else: print('B')`?",
                            options = listOf("A", "B", "Nothing", "Error"),
                            correctIndex = 1,
                            explanation = "Since 3 is not greater than 5, the code inside the `else` block is executed."
                        ),
                        QuizQuestion(
                            question = "What is the output of the following code?\nx = 5\nif x > 2:\n    print(\"A\")\nelif x > 4:\n    print(\"B\")",
                            options = listOf("A", "B", "C", "A and B"),
                            correctIndex = 0,
                            explanation = "The first `if` condition `x > 2` is true, so its block is executed and the rest of the `elif` chain is skipped."
                        ),
                        QuizQuestion(
                            question = "Which operator checks for inequality in Python?",
                            options = listOf("<>", "=/=", "!=", "NOT ="),
                            correctIndex = 2,
                            explanation = "`!=` is the correct operator for checking if two values are not equal."
                        ),
                        QuizQuestion(
                            question = "What will be printed by this code?\nage = 15\nif age >= 18:\n    print(\"Adult\")\nelse:\n    print(\"Minor\")",
                            options = listOf("Adult", "Minor", "Error", "Nothing"),
                            correctIndex = 1,
                            explanation = "Since `age` (15) is not greater than or equal to 18, the `else` block is executed."
                        ),
                        QuizQuestion(
                            question = "How do you correctly check if `x` is between 10 and 20 (inclusive)?",
                            options = listOf("10 <= x <= 20", "x > 10 AND x < 20", "x.between(10, 20)", "10 < x < 20"),
                            correctIndex = 0,
                            explanation = "Python allows for chaining comparison operators, making `10 <= x <= 20` a concise way to check a range."
                        ),
                        QuizQuestion(
                            question = "What is the result of an `if` condition with the value `0`?",
                            options = listOf("The condition is True", "The condition is False", "It causes a syntax error", "It depends on the context"),
                            correctIndex = 1,
                            explanation = "In Python, `0`, `None`, and empty collections are considered 'Falsy'. The `if` block will not execute."
                        ),
                        QuizQuestion(
                            question = "What does the `and` operator do in an `if` statement?",
                            options = listOf("Returns True if at least one condition is true", "Returns True only if all conditions are true", "Returns False if any condition is true", "Inverts the result of a condition"),
                            correctIndex = 1,
                            explanation = "The `and` operator requires all connected conditions to be true for the entire expression to be true."
                        ),
                        QuizQuestion(
                            question = "What is the purpose of indentation in a Python `if` statement?",
                            options = listOf("To make the code look nice", "It is optional and good practice", "To define the block of code to be executed", "To add a comment"),
                            correctIndex = 2,
                            explanation = "Python uses indentation (whitespace) to define code blocks, unlike other languages that use curly braces `{}`."
                        ),
                        QuizQuestion(
                            question = "What will be the output of this code?\nname = \"\"\nif not name:\n    print(\"Name is empty\")\nelse:\n    print(\"Name is not empty\")",
                            options = listOf("Name is empty", "Name is not empty", "Error", "Nothing is printed"),
                            correctIndex = 0,
                            explanation = "An empty string `\"\"` is 'Falsy'. The `not` operator inverts this to `True`, so the `if` block is executed."
                        )
                    ),
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/c/c3/Python-logo-notext.svg",
                    videoUrl = "android.resource://com.tanmay.codo/raw/sumof2numbers",
                    locked = false, completed = false, active = true
                ),
                Lesson(
                    id = "loops",
                    title = "Loops and Iteration",
                    type = "Lesson",
                    xp = 30,
                    section = "Control Flow",
                    description = "Learn how to repeat actions using for and while loops in Python.",
                    exampleCode = """python\nfor i in range(5):\n    print(i)\n\nj = 0\nwhile j < 5:\n    print(j)\n    j += 1\n""".trimIndent(),
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                ),
                Lesson(
                    id = "logic",
                    title = "Logic Challenge",
                    type = "Challenge",
                    xp = 40,
                    section = "Control Flow",
                    description = "Test your understanding of if statements and loops with a coding challenge!",
                    exampleCode = null,
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                )
            )),
            Section("Functions", "Create reusable code with functions", listOf(
                Lesson(
                    id = "func_basic",
                    title = "Function Basics",
                    type = "Lesson",
                    xp = 30,
                    section = "Functions",
                    description = "Learn how to define and call functions in Python.",
                    exampleCode = """python\ndef greet(name):\n    print(f\"Hello, {name}!\")\n\ngreet(\"Alice\")\n""".trimIndent(),
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                ),
                Lesson(
                    id = "params",
                    title = "Parameters and Arguments",
                    type = "Lesson",
                    xp = 30,
                    section = "Functions",
                    description = "Understand how to use parameters and arguments in Python functions.",
                    exampleCode = """python\ndef add(a, b):\n    return a + b\n\nresult = add(2, 3)\nprint(result)\n""".trimIndent(),
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                ),
                Lesson(
                    id = "func_challenge",
                    title = "Function Challenge",
                    type = "Challenge",
                    xp = 50,
                    section = "Functions",
                    description = "Put your function skills to the test with a coding challenge!",
                    exampleCode = null,
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                )
            ))
        )
        val jsSections = listOf(
            Section("JS Basics", "Learn JavaScript fundamentals", listOf(
                Lesson(
                    id = "js_vars",
                    title = "Variables",
                    type = "Lesson",
                    xp = 20,
                    section = "JS Basics",
                    description = "Learn how to declare variables in JavaScript using var, let, and const.",
                    exampleCode = """js\nlet x = 5;\nconst y = 10;\nvar z = x + y;\nconsole.log(z);\n""".trimIndent(),
                    quiz = listOf(
                        QuizQuestion(
                            question = "Which keyword declares a variable that cannot be reassigned?",
                            options = listOf("var", "let", "const", "static"),
                            correctIndex = 2,
                            explanation = "`const` declares a block-scoped variable that must be initialized and cannot be reassigned."
                        ),
                        QuizQuestion(
                            question = "What is the scope of a variable declared with `let`?",
                            options = listOf("Function scope", "Global scope", "Block scope", "File scope"),
                            correctIndex = 2,
                            explanation = "`let` and `const` variables are block-scoped, meaning they are only accessible within the `{}` block they are defined in."
                        ),
                        QuizQuestion(
                            question = "What is the value of `x` after this code runs?\n`let x = 10; x = 20;`",
                            options = listOf("10", "20", "undefined", "Error"),
                            correctIndex = 1,
                            explanation = "A variable declared with `let` can be reassigned. Its final value is 20."
                        ),
                        QuizQuestion(
                            question = "What is 'hoisting' in JavaScript?",
                            options = listOf("Moving variables to the top of their scope before execution", "Lifting a variable to the global scope", "A type of error", "A way to declare constants"),
                            correctIndex = 0,
                            explanation = "Hoisting is JavaScript's default behavior of moving declarations to the top of the current scope."
                        ),
                        QuizQuestion(
                            question = "Which of the following is a valid JavaScript variable name?",
                            options = listOf("2myVar", "my-Var","@myVar"),
                            correctIndex = 2,
                            explanation = "JavaScript variable names can start with a letter, underscore `_`, or dollar sign `$`."
                        ),
                        QuizQuestion(
                            question = "What is the value of a variable that is declared but not initialized (e.g., `let x;`)?",
                            options = listOf("null", "0", "undefined", "Error"),
                            correctIndex = 2,
                            explanation = "If a variable is declared without being assigned a value, its value is `undefined`."
                        ),
                        QuizQuestion(
                            question = "Which keyword has function scope instead of block scope?",
                            options = listOf("let", "const", "var", "both let and var"),
                            correctIndex = 2,
                            explanation = "`var` declarations are function-scoped, which can sometimes lead to unexpected behavior compared to block-scoped `let` and `const`."
                        ),
                        QuizQuestion(
                            question = "What will this code output?\n`console.log(x); var x = 5;`",
                            options = listOf("5", "ReferenceError", "undefined", "0"),
                            correctIndex = 2,
                            explanation = "Due to hoisting, the declaration `var x` is moved to the top, so `x` exists but is `undefined` when `console.log` is called."
                        ),
                        QuizQuestion(
                            question = "Is JavaScript a case-sensitive language when it comes to variable names?",
                            options = listOf("Yes", "No", "Only for `const` variables", "Depends on the browser"),
                            correctIndex = 0,
                            explanation = "Yes, `myVar` and `myvar` would be two different variables in JavaScript."
                        ),
                        QuizQuestion(
                            question = "What happens if you try to access a `let` variable before its declaration?",
                            options = listOf("It returns `undefined`", "It throws a ReferenceError", "It returns `null`", "The code runs without error"),
                            correctIndex = 1,
                            explanation = "`let` and `const` variables are hoisted but are in a 'temporal dead zone' until their declaration is encountered, causing a ReferenceError if accessed before."
                        )
                    ),
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/6/6a/JavaScript-logo.png",
                    videoUrl = "android.resource://com.tanmay.codo/raw/js_variables",
                    locked = false, completed = false, active = true
                ),
                Lesson(
                    id = "js_ops",
                    title = "Operators",
                    type = "Lesson",
                    xp = 20,
                    section = "JS Basics",
                    description = "Explore arithmetic, comparison, and logical operators in JavaScript.",
                    exampleCode = """js\nlet a = 10;\nlet b = 3;\nconsole.log(a + b); // 13\nconsole.log(a > b); // true\nconsole.log(a === b); // false\n""".trimIndent(),
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                ),
                Lesson(
                    id = "js_func",
                    title = "Functions",
                    type = "Lesson",
                    xp = 30,
                    section = "JS Basics",
                    description = "Learn how to define and call functions in JavaScript.",
                    exampleCode = """js\nfunction greet(name) {\n  console.log('Hello, ' + name);\n}\ngreet('Bob');\n""".trimIndent(),
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                )
            )),
            Section("DOM", "Interact with the DOM", listOf(
                Lesson(
                    id = "dom_intro",
                    title = "DOM Intro",
                    type = "Lesson",
                    xp = 25,
                    section = "DOM",
                    description = "Learn what the Document Object Model (DOM) is and how to access elements.",
                    exampleCode = """js\nconst heading = document.getElementById('main-heading');\nconsole.log(heading.textContent);\n""".trimIndent(),
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                ),
                Lesson(
                    id = "dom_events",
                    title = "DOM Events",
                    type = "Lesson",
                    xp = 25,
                    section = "DOM",
                    description = "Learn how to handle events like clicks in JavaScript.",
                    exampleCode = """js\ndocument.getElementById('btn').onclick = function() {\n  alert('Button clicked!');\n};\n""".trimIndent(),
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                )
            ))
        )
        val cppSections = listOf(
            Section("C++ Basics", "Learn C++ fundamentals", listOf(
                Lesson(
                    id = "cpp_vars",
                    title = "Variables",
                    type = "Lesson",
                    xp = 20,
                    section = "C++ Basics",
                    description = "Learn how to declare and use variables in C++.",
                    exampleCode = """cpp\nint x = 5;\ndouble y = 3.14;\nstd::cout << x + y << std::endl;\n""".trimIndent(),
                    quiz = listOf(
                        QuizQuestion(
                            question = "What is the primary difference between `int` and `double` in C++?",
                            options = listOf("`int` is for integers, `double` is for floating-point numbers", "Both are for integers, but `double` has more range", "No difference, they are interchangeable", "`double` is for text, `int` is for numbers"),
                            correctIndex = 0,
                            explanation = "`int` is used for whole numbers, while `double` is used for numbers with a fractional part."
                        ),
                        QuizQuestion(
                            question = "Which is the correct way to declare and initialize an integer variable in C++?",
                            options = listOf("variable x = 10;", "int x = 10;", "x = 10;", "integer x = 10;"),
                            correctIndex = 1,
                            explanation = "In C++, you must specify the data type (`int`) followed by the variable name and then assign a value."
                        ),
                        QuizQuestion(
                            question = "What does the `const` keyword do when declaring a variable?",
                            options = listOf("It makes the variable global", "It makes the variable's value unchangeable", "It converts the variable to a string", "It allocates more memory to the variable"),
                            correctIndex = 1,
                            explanation = "A `const` variable must be initialized upon declaration and its value cannot be modified later."
                        ),
                        QuizQuestion(
                            question = "Which of the following is a valid variable name in C++?",
                            options = listOf("2_vars", "my-variable", "_my_var", "my variable"),
                            correctIndex = 2,
                            explanation = "C++ variable names can start with an underscore or a letter, but not a number. Hyphens and spaces are not allowed."
                        ),
                        QuizQuestion(
                            question = "What is the most suitable data type for storing a single character, like 'A'?",
                            options = listOf("string", "char", "character", "int"),
                            correctIndex = 1,
                            explanation = "The `char` data type is specifically designed to hold a single character."
                        ),
                        QuizQuestion(
                            question = "How would you print the value of a variable `myVar` to the console?",
                            options = listOf("print(myVar);", "console.log(myVar);", "std::cout << myVar;", "System.out.println(myVar);"),
                            correctIndex = 2,
                            explanation = "`std::cout` from the `<iostream>` library is used for standard output in C++."
                        ),
                        QuizQuestion(
                            question = "What is the likely result of `int result = 5 / 2;`?",
                            options = listOf("2.5", "2", "3", "Compilation Error"),
                            correctIndex = 1,
                            explanation = "When dividing two integers, C++ performs integer division, which truncates the decimal part. The result is 2."
                        ),
                        QuizQuestion(
                            question = "What is the purpose of the `bool` data type?",
                            options = listOf("To store text", "To store a list of items", "To store true or false values", "To store large numbers"),
                            correctIndex = 2,
                            explanation = "A `bool` variable can only hold the values `true` or `false`, which is useful for logical operations."
                        ),
                        QuizQuestion(
                            question = "Which header file is required to use `std::cout`?",
                            options = listOf("<string>", "<iostream>", "<vector>", "<cmath>"),
                            correctIndex = 1,
                            explanation = "The `<iostream>` header contains declarations for standard input/output streams, including `cout`."
                        ),
                        QuizQuestion(
                            question = "What is the value of an uninitialized local variable in C++?",
                            options = listOf("0", "null", "An indeterminate or garbage value", "It causes a compilation error"),
                            correctIndex = 2,
                            explanation = "Local variables are not automatically initialized to a default value and will contain whatever garbage value is in that memory location."
                        )
                    ),
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/1/18/ISO_C%2B%2B_Logo.svg",
                    videoUrl = "android.resource://com.tanmay.codo/raw/cpp_variables",
                    locked = false, completed = false, active = true
                ),
                Lesson(
                    id = "cpp_ops",
                    title = "Operators",
                    type = "Lesson",
                    xp = 20,
                    section = "C++ Basics",
                    description = "Explore arithmetic and logical operators in C++.",
                    exampleCode = """cpp\nint a = 10;\nint b = 3;\nstd::cout << (a + b) << std::endl;\nstd::cout << (a > b) << std::endl;\n""".trimIndent(),
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                ),
                Lesson(
                    id = "cpp_func",
                    title = "Functions",
                    type = "Lesson",
                    xp = 30,
                    section = "C++ Basics",
                    description = "Learn how to define and call functions in C++.",
                    exampleCode = """cpp\nint add(int a, int b) {\n  return a + b;\n}\nint result = add(2, 3);\nstd::cout << result << std::endl;\n""".trimIndent(),
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                )
            )),
            Section("Pointers", "Understand pointers", listOf(
                Lesson(
                    id = "ptr_intro",
                    title = "Pointer Intro",
                    type = "Lesson",
                    xp = 25,
                    section = "Pointers",
                    description = "Learn what pointers are and how to use them in C++.",
                    exampleCode = """cpp\nint x = 10;\nint* p = &x;\nstd::cout << *p << std::endl;\n""".trimIndent(),
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                ),
                Lesson(
                    id = "ptr_arith",
                    title = "Pointer Arithmetic",
                    type = "Lesson",
                    xp = 25,
                    section = "Pointers",
                    description = "Learn how to perform arithmetic on pointers in C++.",
                    exampleCode = """cpp\nint arr[3] = {1, 2, 3};\nint* p = arr;\np++;\nstd::cout << *p << std::endl; // 2\n""".trimIndent(),
                    quiz = null,
                    imageUrl = null,
                    videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4",
                    locked = true
                )
            ))
        )
        languageStates = mapOf(
            Language.PYTHON to LanguageState(pythonSections, 0, 0, 12),
            Language.JAVASCRIPT to LanguageState(jsSections, 0, 0, 8),
            Language.CPP to LanguageState(cppSections, 0, 0, 5)
        )
    }

    fun switchLanguage(lang: Language) {
        selectedLanguage = lang
    }

    fun onLessonClicked(sectionIdx: Int, lessonIdx: Int) {
        val lang = selectedLanguage
        val state = languageStates[lang] ?: return
        val updatedSections = state.sections.toMutableList()
        val section = updatedSections[sectionIdx]
        val lessons = section.lessons.toMutableList()
        val lesson = lessons[lessonIdx]
        if (!lesson.locked && !lesson.completed) {
            // Mark as completed
            lessons[lessonIdx] = lesson.copy(completed = true, active = false)
            // Unlock next lesson in this section
            if (lessonIdx + 1 < lessons.size) {
                lessons[lessonIdx + 1] = lessons[lessonIdx + 1].copy(locked = false, active = true)
            } else if (sectionIdx + 1 < updatedSections.size) {
                // Unlock first lesson of next section
                val nextSection = updatedSections[sectionIdx + 1]
                val nextLessons = nextSection.lessons.toMutableList()
                nextLessons[0] = nextLessons[0].copy(locked = false, active = true)
                updatedSections[sectionIdx + 1] = nextSection.copy(lessons = nextLessons)
            }
            // Update XP and state
            val newXP = state.xp + lesson.xp
            lessons[lessonIdx] = lessons[lessonIdx].copy(completed = true)
            updatedSections[sectionIdx] = section.copy(lessons = lessons)
            languageStates = languageStates.toMutableMap().apply {
                put(lang, state.copy(sections = updatedSections, xp = newXP))
            }
        }
    }

    fun resetProgress() {
        fun resetSections(sections: List<Section>): List<Section> {
            return sections.mapIndexed { sectionIdx, section ->
                section.copy(lessons = section.lessons.mapIndexed { lessonIdx, lesson ->
                    when {
                        sectionIdx == 0 && lessonIdx == 0 -> lesson.copy(locked = false, completed = false, active = true)
                        else -> lesson.copy(locked = true, completed = false, active = false)
                    }
                })
            }
        }
        languageStates = languageStates.mapValues { (lang, state) ->
            state.copy(
                level = 0,
                xp = 0,
                sections = resetSections(state.sections)
            )
        }
    }
} 