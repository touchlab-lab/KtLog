/*
 * Copyright (c) 2022 Touchlab
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package co.touchlab.ktlog.kermit

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import co.touchlab.ktlog.AbstractLogger
import co.touchlab.ktlog.Level
import co.touchlab.ktlog.Marker

class KermitLogger(name:String, private val kermitLogger: Logger) : AbstractLogger(name) {
    override fun log(level: Level, marker: Marker?, throwable: Throwable?, message: String) {
        kermitLogger.log(level.toSeverity(), marker?:name, throwable, message)
    }

    override fun isEnabledForLevel(level: Level, marker: Marker?): Boolean = kermitLogger.config.minSeverity <= level.toSeverity()
}

private fun Level.toSeverity(): Severity = when(this){
    Level.TRACE -> Severity.Verbose
    Level.DEBUG -> Severity.Debug
    Level.INFO -> Severity.Info
    Level.WARN -> Severity.Warn
    Level.ERROR -> Severity.Error
}