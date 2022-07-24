# KtLog

This is a proposed design and implementation for a Kotlin Multiplatform logging facade. It takes direct inspiration
from [SLF4J](https://www.slf4j.org/) and somewhat from [kotlin-logging](https://github.com/MicroUtils/kotlin-logging).

The goal is to provide a simple logging API that libraries can log to, and an app consuming those libraries can 
externally configure logging in a central way. The design focus is less on trying to add many features that might 
make one logging library or another more appealing. For apps you write, there's little downside to picking a specific
logging library, but for library authors, picking a specific logging library, or as is often the case, writing their own, 
means a lot of incompatible configuration, if it's possible to configure library logging at all.

## Disclaimer

This is not an active project. It is a design proposal. You should definitely not try to use it.

## Design

It is mostly from SLF4J, but some from kotlin-logging and just general logging library experience. The main interface is
`Logger`. Rather than having several methods defined for each logging level, I decided to rely on default parameter values
and limit each level to 2 methods: one with a `String` argument and one with a function that returns a string (`() -> String`).

Doing it this way reduces the number of methods, but also forces a certain order on the parameters, which some users of 
Kermit object to (we do something similar in Kermit). That can, of course, be changed, but I'd like to start minimal and
expand if needed.

There are no templated messages with args. That is something that could be discussed, but it's not very common for mutliplatform loggers.

kotlin-logging has extra methods on it's `KLogger`: `enter`, `exit`, `throwing`, `catching`. These feel like convenience methods rather than core logging capabilities, at least in the context of Kotlin-logging's integrations. If there is a reason for them, can discuss.

Like SLF4J, there's one `Logger` and `LoggerFactory` at a time. Unlike SLF4J, the library does not attempt to auto-configure from the classpath. That approach likely won't work in native contexts, so for now the `LoggerFactory` needs to have it's provider set manually. That structure is called `LoggerFactoryProvider`, which is not my favorite name, but `LoggerFactoryFactory` seemed worse, and I'm not personally a big fan of "I" prefixes for interfaces. Anyway, that's the name currently.

To set up, run this:

```kotlin
LoggerFactory.setLoggingFactoryProvider(defaultKermitProvider())
```

In this case it is using Kermit as the actual logger (see the `kermit-ktlog` module).

For the JVM side, I assume we could write some kind of auto classpath config if desired.

To log, do the following:

```kotlin
val logger = LoggerFactory.getLogger("MyLogger")
logger.info { "Some Info" }
```

### Marker

Marker is one of the more difficult issues here. SLF4J supports Marker with refecences to other Marker instances, but Kotlin-logging only supports a simple String wrapped in a Marker class (for non-JVM platforms), and for the all of the loggers I've seen, they support String tagging or no tagging, certainly not Markers with references to others. In light of that, if the `Marker` instance on non-JVM platforms is just a wrapper around a string, it seems like a pointless allocation and extra boilerplate code, rather than just using a string (maybe an inline/value class https://kotlinlang.org/docs/inline-classes.html, but I don't think that would work with expect/actual to the JVM Marker). So, for now `Marker` is just a typealias to String. To discuss, but it just seemed like it wasn't necessary.

Tags definitely get weird as you log across platforms. Each multiplatform logging library handles that different, and I have yet to see any that support something like the SLF4J Marker.

### Relation to SLF4J

Kotlin-logging explicitly integrates with SLF4J on the jvm side, which may make that config easier, but I felt like it forces the design a bit, plus I don't think you'd want that by default on Android. So, with the current design, integrating with SLF4J needs to be more explicit, but can be tweaked to be easier. I added a module showing how basic integration would likely work, but my SLF4J experience is pretty limited, so there may be better ways to do that.

## Modules and Structures

The main module is `core`. It defines the `Logger`, `LoggerFactory`, and `LoggerFactoryProvider`. There are some helpers and other details, but those are the main classes to review.

Storage of the `LoggerFactoryProvider` is different between the JVM, JS, and Native. That is the only reason for multiple source sets in `core`. This all should in theory work with the old "strict" memory model on native, but I wrote it assuming the new memory model.

`kermit-ktlog` adds Kermit support. It's our logging library. I'd added that out of convenience, but any standard logging library should work. You can see how the logging integration works in the `commonTest` folder, which runs a basic logger test.

```kotlin
@Test
fun basicLogging() {
  val testLogWriter = TestLogWriter(Severity.Verbose)
  LoggerFactory.setLoggingFactoryProvider(defaultKermitProvider(testLogWriter))

  val logger = LoggerFactory.getLogger("MyLogger")

  logger.info { "Some Info" }

  assertEquals(testLogWriter.logs.size, 1)
  assertEquals(testLogWriter.logs.get(0).message, "Some Info")
}
```



`slf4j-ktlog` intends to bridge log calls to SLF4J. I have not actually tried using it, and there's config that should probably change. Consider it just a demo :)

## Name

KtLog was jlust a simple choice. Something different, and probably something that reflects what the library actually is, would make sense (although not SLF4K, please).