package fudex.bonyad.Helper
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * <h1>Implement all Validations in data</h1>
 * Validations class for validate data and animate errors
 *
 *
 *
 * @author  kemo94
 * @version 1.0
 * @since   2017-08-9
 */

object Validations {


    /**
     * This method is used to validate mobile.
     * called when handle validation of user mobile.
     * @param name of mobile user
     */
    fun isValidName(name: String): Boolean {
        return name.length > 2
    }


    /**
     * This method is used to validate mobile.
     * called when handle validation of user mobile.
     * @param mobile of mobile user
     */

    fun isValidMobile(mobile: String): Boolean {
        var isValid = false

        val expression = "[0-9]+"

        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(mobile)
        if (matcher.matches()) {
            isValid = true
        }

        return mobile.length > 7 && mobile.length <= 15 && isValid
    }


    fun isValidCode(code: String): Boolean {
        return code.length == 0
    }

    /**
     * This method is used to validate password.
     * called when handle validation of user password.
     * @param password of password user
     */
    fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }

    fun isMatchPassword(password: String, confPassword: String): Boolean {
        return confPassword == password
    }


    fun isValidSpinnerItem(pos: Int): Boolean {
        return pos != 0
    }


    fun isValidGender(gender: String): Boolean {
        return gender.toLowerCase() != "Gender".toLowerCase()
    }

    fun isValidDate(date: String): Boolean {
        return date.length > 0
    }

    fun isValidStr(str: String): Boolean {
        return str.length > 0
    }


    /**
     * This method is used to validate email.
     * called when handle validation of user email.
     * @param email of email user
     */
    fun isValidEmail(email: String): Boolean {
        var isValid = false

        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"

        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        if (matcher.matches()) {
            isValid = true
        }
        return isValid
    }
    fun isValidnumber(text: String): Boolean {
        var isValid = false

        if (text.contains("0") || text.contains("1") || text.contains("2") || text.contains("3") || text.contains("4") || text.contains("5") || text.contains("6") || text.contains("7") || text.contains("8") || text.contains("9")) {
            isValid = true
        }
        return isValid
    }
    fun isValidspecial(text: String): Boolean {
        var isValid = false
        if (text.contains("*") || text.contains("&") || text.contains("^") || text.contains("%") || text.contains("$") || text.contains("#") || text.contains("@") ) {
            isValid = true
        }
        return isValid
    }
}
