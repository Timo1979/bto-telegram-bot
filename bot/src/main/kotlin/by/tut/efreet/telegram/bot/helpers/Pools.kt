package by.tut.efreet.telegram.bot.helpers

import java.text.SimpleDateFormat
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.math.max

// по мотивам https://www.javacodegeeks.com/2013/08/simple-and-lightweight-pool-implementation.html
/**
 * @param startSize - начальный размер пула
 * @param maxSize - максимальный размер пула
 */
abstract class ObjectPool<T>(startSize: Int = 5, maxSize: Int = 10) {
    private var pool: ConcurrentLinkedQueue<T> = ConcurrentLinkedQueue<T>()
    private val maxxSize = max(max(0, startSize), maxSize)

    init {
        for (i in 0 until startSize) {
            pool.add(createObject())
        }
    }

    /**
     * Gets the next free object from the pool. If the pool doesn't contain any objects,
     * a new object will be created and given to the caller of this method back.
     *
     * @return T borrowed object
     */
    fun borrowObject(): T {
        return pool.poll() ?: createObject()
    }

    /**
     * Returns object back to the pool.
     *
     * @param obj object to be returned
     */
    fun returnObject(obj: T?) {
        if (obj == null || pool.size > maxxSize) {
            return
        }
        pool.offer(obj)
    }

    /**
     * Creates a new object.
     *
     * @return T new object
     */
    protected abstract fun createObject(): T

}

val formatDDMMYYYY_Pool: ObjectPool<SimpleDateFormat> = object : ObjectPool<SimpleDateFormat>(4) {
    override fun createObject() = SimpleDateFormat("dd.MM.yyyy")
}

val formatDate4Json_Pool: ObjectPool<SimpleDateFormat> = object : ObjectPool<SimpleDateFormat>(4) {
    override fun createObject(): SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd")
    }
}
