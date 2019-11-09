package by.aleksei.acs.util

sealed class ServiceResponse<out T> {
    class Success<out T>(val data: T) : ServiceResponse<T>()
    class Error(val message: String) : ServiceResponse<Nothing>()
}