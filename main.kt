import java.io.File
import kotlin.random.Random

fun main() {
    println("Enter the application for which you are generating the password:")
    val application = readLine() ?: "Unknown Application"

    println("Enter the username for the application:")
    val username = readLine() ?: "Unknown User"

    println("Enter the desired length for the password:")
    val input = readLine()
    val passwordLength = input?.toIntOrNull() ?: 12 // Default to 12 if input is invalid
    if (passwordLength <= 0) {
        println("Invalid input. Password length should be greater than 0.")
        return
    }

    val password = generatePassword(passwordLength)
    println("Generated Password for $application with username $username: $password")

    // Store the application, username, and password in a file
    storePassword(application, username, password)
}

fun generatePassword(length: Int): String {
    val upperCaseLetters = ('A'..'Z')
    val lowerCaseLetters = ('a'..'z')
    val digits = ('0'..'9')
    val specialCharacters = listOf('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+')

    val allCharacters = upperCaseLetters + lowerCaseLetters + digits + specialCharacters

    return (1..length)
        .map { allCharacters.random() }
        .joinToString("")
}

fun storePassword(application: String, username: String, password: String) {
    val file = File("password.txt")
    file.appendText("\"Application: $application\n Username: $username\n Password: $password\"\n")
    println("Password for $application with username $username saved to password.txt")
}