/*
 * Copyright (c) 2022 Touchlab
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package co.touchlab.ktlog.slf4j

import co.touchlab.ktlog.AbstractLogger
import co.touchlab.ktlog.Level
import co.touchlab.ktlog.Marker
import org.slf4j.MarkerFactory

class SLF4JLogger(name: String) : AbstractLogger(name) {
    private val slF4JLogger = org.slf4j.LoggerFactory.getLogger(name)

    override fun log(level: Level, marker: Marker?, throwable: Throwable?, message: String) {
        val m = marker?.let { MarkerFactory.getMarker(it) }

        // We're calling these methods assuming that null values are OK for Marker and Throwable. A full implementation
        // may want to call the different methods based on if there is/isn't a Marker and Throwable, in case the Logger
        // implementation overrides those methods and does something different.
        when (level) {
            Level.ERROR -> slF4JLogger.error(m, message, throwable)
            Level.WARN -> slF4JLogger.warn(m, message, throwable)
            Level.INFO -> slF4JLogger.info(m, message, throwable)
            Level.DEBUG -> slF4JLogger.debug(m, message, throwable)
            Level.TRACE -> slF4JLogger.trace(m, message, throwable)
        }
    }

    override fun isEnabledForLevel(level: Level, marker: Marker?): Boolean {
        val m = marker?.let { MarkerFactory.getMarker(it) }
        return if (m == null) {
            when (level) {
                Level.ERROR -> slF4JLogger.isErrorEnabled
                Level.WARN -> slF4JLogger.isWarnEnabled
                Level.INFO -> slF4JLogger.isInfoEnabled
                Level.DEBUG -> slF4JLogger.isDebugEnabled
                Level.TRACE -> slF4JLogger.isTraceEnabled
            }
        } else {
            when (level) {
                Level.ERROR -> slF4JLogger.isErrorEnabled(m)
                Level.WARN -> slF4JLogger.isWarnEnabled(m)
                Level.INFO -> slF4JLogger.isInfoEnabled(m)
                Level.DEBUG -> slF4JLogger.isDebugEnabled(m)
                Level.TRACE -> slF4JLogger.isTraceEnabled(m)
            }
        }
    }
}