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

import co.touchlab.kermit.Severity
import co.touchlab.kermit.TestLogWriter
import co.touchlab.ktlog.LoggerFactory
import kotlin.test.Test
import kotlin.test.assertEquals

class KermitLoggerTests {
    @Test
    fun basicLogging() {
        val testLogWriter = TestLogWriter(Severity.Verbose)
        LoggerFactory.setLoggingFactoryProvider(defaultKermitProvider(testLogWriter))

        val logger = LoggerFactory.getLogger("MyLogger")

        logger.info { "Some Info" }

        assertEquals(testLogWriter.logs.size, 1)
        assertEquals(testLogWriter.logs.get(0).message, "Some Info")
    }
}