/*
 * Copyright (c) 2021 Touchlab
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

plugins {
    kotlin("multiplatform") //This should be jvm instead of multiplatform, but I got lazy :)
}

kotlin {
    jvm()

    val commonMain by sourceSets.getting
    val commonTest by sourceSets.getting

    val jvmMain by sourceSets.getting {
        dependsOn(commonMain)
    }

    val jvmTest by sourceSets.getting {
        dependsOn(commonTest)
    }

    commonMain.dependencies {
        implementation(project(":core"))
    }

    commonTest.dependencies {
        implementation("org.jetbrains.kotlin:kotlin-test-common")
        implementation("org.jetbrains.kotlin:kotlin-test-annotations-common")
    }

    jvmMain.dependencies {
        implementation("org.jetbrains.kotlin:kotlin-test")
        implementation("org.jetbrains.kotlin:kotlin-test-junit")
        implementation("org.slf4j:slf4j-api:1.7.36")
    }

    jvmTest.dependencies {
        implementation("org.jetbrains.kotlin:kotlin-test")
        implementation("org.jetbrains.kotlin:kotlin-test-junit")
    }
}

apply(from = "../gradle/gradle-mvn-mpp-push.gradle")
