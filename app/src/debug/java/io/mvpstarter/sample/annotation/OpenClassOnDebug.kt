package io.mvpstarter.sample.annotation

/**
 * Annotation for kotlin-allopen plugin (make all class open for mock it).
 * In debug this annotation opens classes.
 */
@OpenClass
@Target(AnnotationTarget.CLASS)
annotation class OpenClassOnDebug
