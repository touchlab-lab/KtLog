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

@Suppress("unused")
interface Logger {

    val name: String

    fun isEnabledForLevel(level: Level, marker: Marker?): Boolean

    fun isTraceEnabled(marker: Marker? = null): Boolean = isEnabledForLevel(Level.TRACE, marker)

    fun trace(message: String, marker: Marker? = null, throwable: Throwable? = null)

    fun trace(marker: Marker? = null, throwable: Throwable? = null, messageBlock: () -> String)

    fun isDebugEnabled(marker: Marker? = null): Boolean = isEnabledForLevel(Level.DEBUG, marker)

    fun debug(message: String, marker: Marker? = null, throwable: Throwable? = null)

    fun debug(marker: Marker? = null, throwable: Throwable? = null, messageBlock: () -> String)

    fun isInfoEnabled(marker: Marker? = null): Boolean = isEnabledForLevel(Level.INFO, marker)

    fun info(message: String, marker: Marker? = null, throwable: Throwable? = null)

    fun info(marker: Marker? = null, throwable: Throwable? = null, messageBlock: () -> String)

    fun isWarnEnabled(marker: Marker? = null): Boolean = isEnabledForLevel(Level.WARN, marker)

    fun warn(message: String, marker: Marker? = null, throwable: Throwable? = null)

    fun warn(marker: Marker? = null, throwable: Throwable? = null, messageBlock: () -> String)

    fun isErrorEnabled(marker: Marker? = null): Boolean = isEnabledForLevel(Level.ERROR, marker)

    fun error(message: String, marker: Marker? = null, throwable: Throwable? = null)

    fun error(marker: Marker? = null, throwable: Throwable? = null, messageBlock: () -> String)
}

