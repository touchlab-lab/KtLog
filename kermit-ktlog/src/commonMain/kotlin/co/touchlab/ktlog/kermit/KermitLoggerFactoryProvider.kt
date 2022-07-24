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

import co.touchlab.kermit.LogWriter
import co.touchlab.kermit.LoggerConfig
import co.touchlab.kermit.StaticConfig
import co.touchlab.kermit.platformLogWriter
import co.touchlab.ktlog.Logger
import co.touchlab.ktlog.LoggerFactoryProvider

class KermitLoggerFactoryProvider(private val loggerConfig: LoggerConfig) : LoggerFactoryProvider {
    override fun getLogger(name: String): Logger = KermitLogger(name, co.touchlab.kermit.Logger(loggerConfig))
}

fun defaultKermitProvider(vararg writers: LogWriter = arrayOf(platformLogWriter())) = KermitLoggerFactoryProvider(StaticConfig(logWriterList = writers.toList()))