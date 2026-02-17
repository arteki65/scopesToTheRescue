package dev.aptewicz.scopestotherescue.library.preview

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object PreviewScope {
    @PublishedApi
    internal fun <T> create(interfaceClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return Proxy.newProxyInstance(
            interfaceClass.classLoader,
            arrayOf(interfaceClass),
            DefaultInvocationHandler(),
        ) as T
    }
}

private class DefaultInvocationHandler : InvocationHandler {
    override fun invoke(
        proxy: Any,
        method: Method,
        args: Array<out Any>?,
    ): Any? =
        when (method.name) {
            "toString" -> ""
            "equals" -> proxy === args?.get(0)
            "hashCode" -> System.identityHashCode(proxy)
            else -> handleReturnTypes(method.returnType)
        }

    private fun handleReturnTypes(returnType: Class<*>?): Any? =
        when (returnType) {
            Void.TYPE -> Unit
            String::class.java -> ""
            Boolean::class.java -> false
            Int::class.java -> 0
            Long::class.java -> 0L
            Float::class.java -> 0.0f
            Double::class.java -> 0.0
            List::class.java -> emptyList<Any>()
            Map::class.java -> emptyMap<Any, Any>()
            Set::class.java -> emptySet<Any>()
            else -> null
        }
}

inline fun <reified T : Any> previewScope(): T = PreviewScope.create(T::class.java)
