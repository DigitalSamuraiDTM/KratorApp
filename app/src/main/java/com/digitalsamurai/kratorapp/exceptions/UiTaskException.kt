package com.digitalsamurai.kratorapp.exceptions

class UiTaskException(val errorMessage : String? = null) : java.lang.Exception() {
    override val message: String
        get() = errorMessage ?: "Ui task was not completed"

    override fun getLocalizedMessage(): String {
        return errorMessage ?: message
    }
}