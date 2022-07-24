/*
 * Copyright (c) 2022 Touchlab
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package co.touchlab.kermit

enum class Level(private val levelInt: Int, private val levelStr: String) {
    ERROR(EventConstants.ERROR_INT, "ERROR"), WARN(
        EventConstants.WARN_INT,
        "WARN"
    ),
    INFO(EventConstants.INFO_INT, "INFO"), DEBUG(
        EventConstants.DEBUG_INT,
        "DEBUG"
    ),
    TRACE(EventConstants.TRACE_INT, "TRACE");

    fun toInt(): Int {
        return levelInt
    }

    /**
     * Returns the string representation of this Level.
     */
    override fun toString(): String {
        return levelStr
    }

    companion object {
        fun intToLevel(levelInt: Int): Level {
            return when (levelInt) {
                EventConstants.TRACE_INT -> TRACE
                EventConstants.DEBUG_INT -> DEBUG
                EventConstants.INFO_INT -> INFO
                EventConstants.WARN_INT -> WARN
                EventConstants.ERROR_INT -> ERROR
                else -> throw IllegalArgumentException("Level integer [$levelInt] not recognized.")
            }
        }
    }
}

object EventConstants {
    val ERROR_INT: Int = 40
    val WARN_INT: Int = 30
    val INFO_INT: Int = 20
    val DEBUG_INT: Int = 10
    val TRACE_INT: Int = 0
}