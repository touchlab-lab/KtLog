/*
 * Copyright (c) 2022 Touchlab
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

@file:Suppress("VARIABLE_IN_SINGLETON_WITHOUT_THREAD_LOCAL")

package co.touchlab.ktlog

import kotlin.reflect.KClass

object LoggerFactory {
    fun setLoggingFactoryProvider(provider: LoggerFactoryProvider) {
        ProviderConfig.provider = provider
    }

    fun getLogger(name: String): Logger = ProviderConfig.provider.getLogger(name)

    fun getLogger(clazz: KClass<*>): Logger = ProviderConfig.provider.getLogger(clazz.simpleName ?: "(UnknownType)")
}

internal expect object ProviderConfig {
    var provider: LoggerFactoryProvider
}