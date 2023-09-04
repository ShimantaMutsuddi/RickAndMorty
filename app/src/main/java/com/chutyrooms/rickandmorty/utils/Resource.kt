package com.chutyrooms.rickandmorty.utils

/*data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val totalPages: Int?,   // Total number of pages in the paginated data
    val currentPage: Int?,  // Current page being displayed
    val isLastPage: Boolean  // Indicates whether the current page is the last page
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T, totalPages: Int, currentPage: Int): Resource<T> {
            return Resource(Status.SUCCESS, data, null, totalPages, currentPage, false)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message, null, null, false)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null, null, null, false)
        }

        fun <T> lastPage(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null, null, true)
        }
    }
}*/



data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
