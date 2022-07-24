/*
 * Copyright (c) 2022 Touchlab
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package co.touchlab.ktlog

/**
 * Extend this abstract class to implement a Logger
 */
abstract class AbstractLogger(override val name: String) : Logger {
    override fun trace(message: String, marker: Marker?, throwable: Throwable?) {
        if (isTraceEnabled(marker))
            log(Level.TRACE, marker, throwable, message)
    }

    override fun trace(marker: Marker?, throwable: Throwable?, messageBlock: () -> String) {
        if (isTraceEnabled(marker))
            log(Level.TRACE, marker, throwable, messageBlock())
    }

    override fun debug(message: String, marker: Marker?, throwable: Throwable?) {
        if (isDebugEnabled(marker))
            log(Level.DEBUG, marker, throwable, message)
    }

    override fun debug(marker: Marker?, throwable: Throwable?, messageBlock: () -> String) {
        if (isDebugEnabled(marker))
            log(Level.DEBUG, marker, throwable, messageBlock())
    }

    override fun info(message: String, marker: Marker?, throwable: Throwable?) {
        if (isInfoEnabled(marker))
            log(Level.INFO, marker, throwable, message)
    }

    override fun info(marker: Marker?, throwable: Throwable?, messageBlock: () -> String) {
        if (isInfoEnabled(marker))
            log(Level.INFO, marker, throwable, messageBlock())
    }

    override fun warn(message: String, marker: Marker?, throwable: Throwable?) {
        if (isWarnEnabled(marker))
            log(Level.WARN, marker, throwable, message)
    }

    override fun warn(marker: Marker?, throwable: Throwable?, messageBlock: () -> String) {
        if (isWarnEnabled(marker))
            log(Level.WARN, marker, throwable, messageBlock())
    }

    override fun error(message: String, marker: Marker?, throwable: Throwable?) {
        if (isErrorEnabled(marker))
            log(Level.ERROR, marker, throwable, message)
    }

    override fun error(marker: Marker?, throwable: Throwable?, messageBlock: () -> String) {
        if (isErrorEnabled(marker))
            log(Level.ERROR, marker, throwable, messageBlock())
    }

    abstract fun log(level: Level, marker: Marker?, throwable: Throwable?, message: String)
}