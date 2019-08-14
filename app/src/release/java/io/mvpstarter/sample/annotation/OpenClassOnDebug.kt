package io.mvpstarter.sample.annotation


/**
 * Annotation for kotlin-allopen plugin (make all class open for mock it).
 * In release this annotation do nothing.
 */
@Target(AnnotationTarget.CLASS)
annotation class OpenClassOnDebug
