package io.mvpstarter.sample.annotation

/**
 * Annotation for kotlin-allopen plugin (make all class open for mock it).
 *
 * Remember to add in build.gradle:
 *
 * buildscript {
 *  repositories {
 *      jcenter()
 *  }
 *  dependencies {
 *      classpath "org.jetbrains.kotlin:kotlin-allopen:$kotlin_version"
 *  }
 * }
 *
 * apply plugin: 'kotlin-allopen'
 *
 * allOpen {
 *  annotation 'io.mvpstarter.sample.annotation.OpenClass'
 * }
 */
@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class OpenClass
