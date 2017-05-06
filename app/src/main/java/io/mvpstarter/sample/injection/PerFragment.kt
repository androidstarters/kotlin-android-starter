package io.mvpstarter.sample.injection

import javax.inject.Scope

/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the Fragment to be memorised in the
 * correct component.
 */
@Scope @Retention annotation class PerFragment